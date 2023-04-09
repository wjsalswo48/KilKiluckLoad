package mz.sixsense.board.controller;

import mz.sixsense.board.ScriptUtils;
import mz.sixsense.board.Search;
import mz.sixsense.board.entity.BoardPage;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.file.entity.FreeBoardFile;
import mz.sixsense.board.file.service.FreeBoardFileService;
import mz.sixsense.board.like.entity.LikeCount;
import mz.sixsense.board.reply.entity.Reply;
import mz.sixsense.board.reply.service.ReplyService;
import mz.sixsense.board.repository.FreeBoardRepository;
import mz.sixsense.board.service.FreeBoardService;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.service.MemberService;
import mz.sixsense.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FreeBoardController {

    @Autowired
    private FreeBoardService freeBoardService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private FreeBoardFileService freeBoardFileService;

    @Autowired
    private MemberService memberService;
    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @ModelAttribute("members")
    public Members setMember() {
        return new Members();
    }

    @RequestMapping("/board/getFreeBoardList")
    public String getFreeBoardList(Model model, Search search, FreeBoard freeBoard, String page) {
        if (search.getSearchCategory() == null)
            search.setSearchCategory("");
        if (search.getSearchCondition() == null)
            search.setSearchCondition("TITLE");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");

        int currentPage = search.getPage();
        Page<FreeBoard> boardList = freeBoardService.getFreeBoardList(search, currentPage, freeBoard);
        if (boardList.getNumberOfElements() == 0) {
            search.setPage(1);
        } else {
            search.setPage(boardList.getTotalPages());
        }
        int page_ = page != null ? Integer.parseInt(page) - 1 : 0;
        BoardPage boardPage = new BoardPage();
        try {
            page_ = page_ + 1;

            // 현재 페이지
            boardPage.setCurrentPage(page_);
            // 한 블록당 보여줄 페이지 수
            boardPage.setPageBlock(boardList.getSize());
            // 전체 게시글 수
            boardPage.setTotalPage(boardList.getTotalPages());
            // 블록 당 첫 페이지 번호
            boardPage.setStartPage(((page_ - 1) / boardList.getSize()) * boardList.getSize() + 1);
            // 블록 당 마지막 페이지 번호
            boardPage.setEndPage(boardPage.getStartPage() + boardList.getSize() - 1);
            if (boardPage.getEndPage() > boardPage.getTotalPage()) {
                boardPage.setEndPage(boardPage.getTotalPage());
            }
        } catch (Exception e) {

        }

        model.addAttribute("boardPage", boardPage);

        model.addAttribute("boardList", boardList);
        model.addAttribute("totalNum", boardList.getTotalElements());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("searchResult", search);
        return "board/getFreeBoardList";
    }   //getFreeBoardList


    @GetMapping("/board/getFreeBoard")
    public String getFreeBoard(FreeBoard freeBoard, Model model, Search search, FreeBoardFile freeBoardFile, Members members,
                               @AuthenticationPrincipal SecurityUser principal, HttpServletResponse response) throws IOException {
        if(principal == null){
            ScriptUtils.alertAndMovePage(response, "회원만 게시글을 볼 수 있습니다. 로그인을 먼저 해주세요!", "/system/login");
        }else{
        FreeBoard findFreeBoard = freeBoardService.getFreeBoard(freeBoard);
        Members findMember = memberService.findMember(principal.getMember().getMemberID());


        /* 댓글 */
        int currentPage = search.getPage();
        Page<Reply> replyList = replyService.getReplyList(freeBoard.getFreeBID(), currentPage);
        if (replyList.getNumberOfElements() == 0) {
            search.setPage(1);
        } else {
            search.setPage(replyList.getTotalPages());
        }
        model.addAttribute("replyList", replyList);
        /* 댓글 */

        /* 파일 */
        List<FreeBoardFile> files = freeBoardFileService.fileAllView(freeBoard.getFreeBID());
        /* 파일 */

        /* 좋아요 */
        List<LikeCount> likeCountListByMember = findMember.getLikeCountList();
        List<Long> likeFreeBoardList = new ArrayList<>();

        for (LikeCount likeCount : likeCountListByMember) {
            if (likeCount.getFreeBoard().getFreeBID().equals(freeBoard.getFreeBID())) {
                likeFreeBoardList.add(likeCount.getFreeBoard().getFreeBID());
            }
        }
        /* 좋아요 */

        model.addAttribute("freeBoard", findFreeBoard);
        model.addAttribute("principal", principal);
        /* 댓글 */
        model.addAttribute("replyList", replyList);
        model.addAttribute("searchResult", search);
        /* 댓글 */

        /* 파일 */
        model.addAttribute("all", files);
        /* 파일 */

        /* 좋아요 */
        model.addAttribute("freeBoardListLikedByMember", likeFreeBoardList);
        /* 좋아요 */
        }

        return "board/getFreeBoard";
    }   //getFreeBoard


    @RequestMapping("/board/likeUp")
    public String likeUp(FreeBoard freeBoard, LikeCount likeCount, @Param("Check") String likeCheck) {
        FreeBoard findFreeBoard = freeBoardService.getFreeBoard(freeBoard);

        likeCount.setLikeCheck(Long.parseLong(likeCheck));
        likeCount.setFreeBoard(findFreeBoard);
        freeBoardService.insertLike(likeCount);
        freeBoardService.likeUp(findFreeBoard);

        return "redirect:/board/getFreeBoard?freeBID=" + findFreeBoard.getFreeBID();
    }

    @RequestMapping("/board/likeDown")
    public String likeDown(FreeBoard freeBoard, LikeCount likeCount, @Param("Check") String likeCheck) {
        FreeBoard findFreeBoard = freeBoardService.getFreeBoard(freeBoard);

//        likeCount.setLikeID();
        likeCount.setLikeCheck(Long.parseLong(likeCheck));
        likeCount.setFreeBoard(findFreeBoard);
        freeBoardService.deleteLike(likeCount.getLikeID());
        freeBoardService.likeDown(findFreeBoard);

        return "redirect:/board/getFreeBoard?freeBID=" + findFreeBoard.getFreeBID();
    }

    @GetMapping("/board/insertFreeBoard")
    public String insertFreeBoardView() {

        return "board/insertFreeBoard";
    }   //insertFreeBoardView

    @GetMapping("/board/registerForm")
    public String registerForm(Model model, Long freeBID) {
        FreeBoard freeBoard = freeBoardService.getFreeBoard(freeBID);
        model.addAttribute("freeBoard", freeBoard);

        return "board/registerForm";
    }

    @PostMapping("/board/insertFreeBoard")
    @Transactional
    public String insertFreeBoard(FreeBoard freeBoard, @AuthenticationPrincipal SecurityUser principal, HttpServletResponse response,
                                  HttpServletRequest request, @RequestParam("files") List<MultipartFile> files) throws Exception {

        Members member = principal.getMember();

        if (freeBoard.getBoardCategory() == null) {
            ScriptUtils.alertAndBackPage(response, "작성하려는 글의 카테고리를 선택해주세요!");
            String referer = request.getHeader("Referer");
            return "redirect"+referer;
        } else if (freeBoard.getTitle().equals("")) {
            ScriptUtils.alertAndBackPage(response, "제목은 필수입력사항입니다!");
            String referer = request.getHeader("Referer");
            return "redirect"+referer;
        } else if (freeBoard.getContent().equals("")){
            ScriptUtils.alertAndBackPage(response, "내용은 필수입력사항입니다!");
            String referer = request.getHeader("Referer");
            return "redirect"+referer;
        }else

            freeBoard.setMemberID(principal.getMember());
        freeBoardService.insertFreeBoard(freeBoard);

        for (MultipartFile multipartFile : files) {
            freeBoard.setFileID(freeBoardFileService.saveFile(multipartFile, freeBoard));
        }

        return "redirect:/board/getFreeBoardList";
    }   //insertFreeBoard


    @PostMapping("/board/updateFreeboard")
    public String updateFreeBoard(FreeBoard freeBoard, HttpServletResponse response, HttpServletRequest request) throws IOException {

        if (freeBoard.getBoardCategory() == null) {
            ScriptUtils.alertAndBackPage(response, "수정하려는 글의 분류를 선택해주세요!");
            String referer = request.getHeader("Referer");
            return "redirect"+referer;
        } else


            freeBoardService.updateFreeBoard(freeBoard);
        return "forward:getFreeBoardList";
    }   //updateFreeBoard

    @PostMapping("/board/disUpdate")
    public String disUpdate(FreeBoard freeBoard) {
        FreeBoard findFreeBoard = freeBoardService.getFreeBoard(freeBoard);

        return "redirect:/board/getFreeBoard?freeBID=" + findFreeBoard.getFreeBID();
    }

    @GetMapping("/board/deleteFreeBoard")
    public String deleteFreeBoard(FreeBoard freeBoard) {
        freeBoardService.deleteFreeBoard(freeBoard);
        return "forward:getFreeBoardList";
    }   //deleteFreeBoard

    @GetMapping("/board/images/{fileID}")
    @ResponseBody
    public Resource imageView(@PathVariable("fileID") Long fileID, Model model) throws IOException {
        FreeBoardFile freeBoardFile = freeBoardFileService.downloadFile(fileID);

        String savePath = freeBoardFile.getSavePath();
        if (savePath.substring(savePath.length() - 3, savePath.length()).equals("png")
                || savePath.substring(savePath.length() - 3, savePath.length()).equals("jpg")
                || savePath.substring(savePath.length() - 3, savePath.length()).equals("gif")
                || savePath.substring(savePath.length() - 3, savePath.length()).equals("mp4")
        ) {
            UrlResource url = new UrlResource("file", savePath);
            model.addAttribute("test", url);
            return url;
        } else {
            model.addAttribute("test", "not");
        }
        return null;
    }   //imageView

    @GetMapping("/board/download/{fileID}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileID) throws MalformedURLException {
        FreeBoardFile freeBoardFile = freeBoardFileService.downloadFile(fileID);

        UrlResource resource = new UrlResource("file:" + freeBoardFile.getSavePath());

        String encodedFileName = UriUtils.encode(freeBoardFile.getOriginName(), StandardCharsets.UTF_8);

        String contentDisposition = "download; fileName=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
    }   //downloadFile


}   //class