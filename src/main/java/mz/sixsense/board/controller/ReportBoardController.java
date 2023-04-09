package mz.sixsense.board.controller;

import mz.sixsense.board.ScriptUtils;
import mz.sixsense.board.Search;
import mz.sixsense.board.entity.BoardPage;
import mz.sixsense.board.entity.ReportBoard;
import mz.sixsense.board.file.entity.ReportBoardFile;
import mz.sixsense.board.file.service.ReportBoardFileService;
import mz.sixsense.board.service.ReportBoardService;
import mz.sixsense.enums.Role;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.repository.MemberRepository;
import mz.sixsense.member.service.MemberService;

import mz.sixsense.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class ReportBoardController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReportBoardService reportboardService;

    @Autowired
    private ReportBoardFileService reportBoardFileService;

    @Autowired
    private PointHistoryService pointHistoryService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/board/insertReportBoard")
    public String insertBoardView() {
        return "board/insertReportBoard";
    }

    @PostMapping("/board/insertReportBoard")
    public String insertBoard(HttpServletResponse response, ReportBoard reportboard,
                              @AuthenticationPrincipal SecurityUser principal, @RequestParam("files") List<MultipartFile> files,
                              ReportBoardFile reportboardfile, Model model, MultipartFile file) throws Exception {
        if (reportboard.getBoardCategory() == null) {
            ScriptUtils.alertAndMovePage(response, "작성하려는 글의 카테고리를 선택해주세요!", "/board/insertReportBoard");
            return "board/insertReportBoard";
        } else {
            Members member = principal.getMember();
            reportboard.setMemberID(member);
            if (reportboard.getMemberID().getRole() == Role.ROLE_MEMBER_PHONE)
                reportboard.setReportStatus("처리중");
            if (reportboard.getMemberID().getRole() == Role.ROLE_ADMIN)
                reportboard.setReportStatus("공지");
            reportboardService.insertBoard(reportboard);

            for (MultipartFile multipartFile : files) {
                reportboard.setFileID(reportBoardFileService.saveFile(multipartFile, reportboard));
            }
            return "redirect:getReportBoardList";
        }
    }

    @RequestMapping("/board/getReportBoardList")
    public String getReportBoardList(Model model, Search search, String page, ReportBoard reportboard) {
        if (search.getSearchCategory() == null)
            search.setSearchCategory("");
        if (search.getSearchCondition() == null)
            search.setSearchCondition("TITLE");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");

        int currentPage = search.getPage(); // 처음 0

        Page<ReportBoard> reportboardList = reportboardService.getReportBoardList(search, currentPage);

        if (reportboardList.getNumberOfElements() == 0) {
            search.setPage(1);
        } else {
            search.setPage(reportboardList.getTotalPages());
        }

        int page_ = page != null ? Integer.parseInt(page) - 1 : 0;
        BoardPage boardPage = new BoardPage();

        try {
            page_ = page_ + 1;

            // 현재 페이지
            boardPage.setCurrentPage(page_);
            // 한 블록당 보여줄 페이지 수
            boardPage.setPageBlock(reportboardList.getSize());
            // 전체 게시글 수
            boardPage.setTotalPage(reportboardList.getTotalPages());
            // 블록 당 첫 페이지 번호
            boardPage.setStartPage(((page_ - 1) / reportboardList.getSize()) * reportboardList.getSize() + 1);
            // 블록 당 마지막 페이지 번호
            boardPage.setEndPage(boardPage.getStartPage() + reportboardList.getSize() - 1);
            if (boardPage.getEndPage() > boardPage.getTotalPage()) {
                boardPage.setEndPage(boardPage.getTotalPage());
            }
        } catch (Exception e) {

        }
        model.addAttribute("boardPage", boardPage);

        model.addAttribute("reportboardList", reportboardList);
        model.addAttribute("totalNum", reportboardList.getTotalElements());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("searchResult", search);
        return "board/getReportBoardList";
    }

    @GetMapping("/board/getReportBoard")
    public String getReportBoard(ReportBoard reportboard, Model model, ReportBoardFile reportBoardFile,
                                 @AuthenticationPrincipal SecurityUser principal) {
        System.out.println("reportboard====>" + reportboard);

        /* 파일 */
        List<ReportBoardFile> files = reportBoardFileService.fileAllView(reportboard.getReportID());
        /* 파일 */

        model.addAttribute("reportBoard", reportboardService.getReportBoard(reportboard));
        model.addAttribute("principal", principal);
        System.out.println("reportboard====>" + reportboard);
        /* 파일 */
        model.addAttribute("all", files);
        /* 파일 */

        return "board/getReportBoard";
    }   //getFreeBoard

//    @PostMapping("/updateReportBoard")
//    public String updateReportBoard(ReportBoard reportBoard,ReportBoardFile reportboardfile, MultipartFile file
//    		,Model model, @RequestParam("files") List<MultipartFile> files) throws Exception{
//
//        reportboardService.updateBoard(reportBoard);
//
//        return "redirect:getReportBoardList";
//    }   //updateFreeBoard

    @PostMapping("/board/updateReportBoard")
    public String updateReportBoard(@AuthenticationPrincipal SecurityUser principal, Members members,
                                    ReportBoard reportBoard, ReportBoardFile reportboardfile, MultipartFile file
            , Model model, @RequestParam(value = "files", required = false) List<MultipartFile> files) throws Exception {

        //   	reportBoardFileService.deleteFile(reportBoard);

        ReportBoard test = reportboardService.getReportBoard(reportBoard);

        if (reportBoard.getReportStatus().equals("처리완료")) {
            Members member = memberRepository.findMember(test.getMemberID().getMemberID());
            String reason = "신고 처리";
            Long point = 5000L;
            memberService.updatePoint(member, point);
            pointHistoryService.insertPointHistory(member, reason, point);
        }
        reportboardService.updateBoard(reportBoard);
        return "redirect:getReportBoardList";
    }   //updateFreeBoard

    @GetMapping("/board/deleteReportBoard")
    public String deleteReportBoard(ReportBoard reportBoard) {
        reportboardService.deleteBoard(reportBoard);
        return "forward:getReportBoardList";
    }   //deleteFreeBoard

    @GetMapping("/board/image/{fileID}")
    @ResponseBody
    public Resource imageView(@PathVariable("fileID") Long fileID, Model model) throws IOException {
        ReportBoardFile reportBoardFile = reportBoardFileService.downloadFile(fileID);

        String savPath = reportBoardFile.getSavPath();
        if (savPath.substring(savPath.length() - 3, savPath.length()).equals("png")
                || savPath.substring(savPath.length() - 3, savPath.length()).equals("jpg")
                || savPath.substring(savPath.length() - 3, savPath.length()).equals("gif")
                || savPath.substring(savPath.length() - 3, savPath.length()).equals("mp4")
                || savPath.substring(savPath.length() - 3, savPath.length()).equals("jfif")
        ) {
            UrlResource url = new UrlResource("file", savPath);
            model.addAttribute("test", url);
            return url;
        } else {
            model.addAttribute("test", "not");
        }
        return null;
    }   //imageView

    @GetMapping("/board/downloads/{fileID}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileID) throws MalformedURLException {
        ReportBoardFile reportBoardFile = reportBoardFileService.downloadFile(fileID);

        UrlResource resource = new UrlResource("file:" + reportBoardFile.getSavPath());

        String encodedFileName = UriUtils.encode(reportBoardFile.getOriginName(), StandardCharsets.UTF_8);

        String contentDisposition = "download; fileName=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }   //downloadFile

    @GetMapping("/board/reportRegisterForm")
    public String insertUpdateForm(Model model, Long reportID, ReportBoard reportboard) {
        System.out.println("============>" + reportboard);

        ReportBoard reportBoard = reportboardService.getReportBoard(reportboard);
        System.out.println("============>" + reportboard);
        model.addAttribute("reportBoard", reportBoard);

        return "board/reportRegisterForm";
    }
}
    