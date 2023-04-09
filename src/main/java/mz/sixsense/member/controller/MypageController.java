package mz.sixsense.member.controller;

import mz.sixsense.board.Search;
import mz.sixsense.board.entity.FreeBoard;
import mz.sixsense.board.entity.ReportBoard;
import mz.sixsense.board.reply.entity.Reply;
import mz.sixsense.board.reply.service.ReplyService;
import mz.sixsense.board.service.FreeBoardService;
import mz.sixsense.board.service.ReportBoardService;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.entity.OrderHistory;
import mz.sixsense.member.history.entity.PointHistory;
import mz.sixsense.member.history.service.OrderHistoryService;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.service.MemberService;
import mz.sixsense.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PointHistoryService pointHistoryService;

    @Autowired
    private FreeBoardService freeBoardService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReportBoardService reportBoardService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    /*==============================================myboard 리스트=====================================================*/
//    @RequestMapping("/mypage/myboardList")
//    public String myboardList(@AuthenticationPrincipal SecurityUser principal, Model model, Search search){
//
//        if (search.getSearchCategory() == null)
//            search.setSearchCategory("");
//        if (search.getSearchCondition() == null)
//            search.setSearchCondition("TITLE");
//        if (search.getSearchKeyword() == null)
//            search.setSearchKeyword("");
//
//        int currentPage = search.getPage();
//        Members member = memberService.findMember(principal.getMember().getMemberID());
//
//        Page<FreeBoard> myfreeBoardList = freeBoardService.myfreeBoardList(search, member, currentPage);
////        Page<PointHistory> pointHistoryList = poinHistoryService.getPointHistoryList(search,member, currentPage);
//
//        if(myfreeBoardList.getNumberOfElements() == 0){
//            search.setPage(1);
//        } else {
//            search.setPage(myfreeBoardList.getTotalPages());
//        }
//        model.addAttribute("currentPage", currentPage);
//        model.addAttribute("totalNum", myfreeBoardList.getTotalElements());
//        model.addAttribute("myfreeBoardList", myfreeBoardList);
//        model.addAttribute("member", principal.getMember());
//        model.addAttribute("searchResult", search);
//
//        return "mypage/myboardList";
//    }

    @RequestMapping("/mypage/myboardList")
    public String myboardList(@AuthenticationPrincipal SecurityUser principal, Model model, Search searchFree, Search searchReport) {

        if (searchFree.getSearchCategory() == null)
            searchFree.setSearchCategory("");
        if (searchFree.getSearchCondition() == null)
            searchFree.setSearchCondition("TITLE");
        if (searchFree.getSearchKeyword() == null)
            searchFree.setSearchKeyword("");

        if (searchReport.getSearchCategory() == null)
            searchReport.setSearchCategory("");
        if (searchReport.getSearchCondition() == null)
            searchReport.setSearchCondition("TITLE");
        if (searchReport.getSearchKeyword() == null)
            searchReport.setSearchKeyword("");

        int currentPageFree = searchFree.getPage();
        int currentPageReport = searchReport.getPage();

        Members member = memberService.findMember(principal.getMember().getMemberID());

        Page<FreeBoard> myfreeBoardList = freeBoardService.myfreeBoardList(searchFree, member, currentPageFree);
        Page<ReportBoard> myreportBoardList = reportBoardService.myreportBoardList(searchReport, member, currentPageReport);

        if (myfreeBoardList.getNumberOfElements() == 0) {
            searchFree.setPage(1);
        } else {
            searchFree.setPage(myfreeBoardList.getTotalPages());
        }

        if (myreportBoardList.getNumberOfElements() == 0) {
            searchReport.setPage(1);
        } else {
            searchReport.setPage(myreportBoardList.getTotalPages());
        }

        model.addAttribute("currentPage", currentPageFree);
        model.addAttribute("totalNum", myfreeBoardList.getTotalElements());
        model.addAttribute("myfreeBoardList", myfreeBoardList);
        model.addAttribute("member", principal.getMember());
        model.addAttribute("searchResult", searchFree);

        model.addAttribute("currentPageReport", currentPageReport);
        model.addAttribute("totalNumReport", myreportBoardList.getTotalElements());
        model.addAttribute("myreportBoardList", myreportBoardList);
        model.addAttribute("searchResultReport", searchReport);

        return "mypage/myboardList";
    }

    /*==============================================myreply 리스트=====================================================*/
    @RequestMapping("/mypage/myreplyList")
    public String myreplyList(@AuthenticationPrincipal SecurityUser principal, Model model, Search search) {

        if (search.getSearchCategory() == null)
            search.setSearchCategory("");
        if (search.getSearchCondition() == null)
            search.setSearchCondition("TITLE");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");

        int currentPage = search.getPage();
        Members member = memberService.findMember(principal.getMember().getMemberID());

        Page<Reply> myreplyList = replyService.myreplyList(search, member, currentPage);
//        Page<PointHistory> pointHistoryList = poinHistoryService.getPointHistoryList(search,member, currentPage);

        if (myreplyList.getNumberOfElements() == 0) {
            search.setPage(1);
        } else {
            search.setPage(myreplyList.getTotalPages());
        }
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalNum", myreplyList.getTotalElements());
        model.addAttribute("myreplyList", myreplyList);
        model.addAttribute("member", principal.getMember());
        model.addAttribute("searchResult", search);

        return "mypage/myreplyList";
    }

    /*==============================================포인트 리스트=======================================================*/
    @RequestMapping("/mypage/pointList")
    public String pointList(@AuthenticationPrincipal SecurityUser principal, Model model, Search search) {
        if (search.getSearchCategory() == null)
            search.setSearchCategory("");
        if (search.getSearchCondition() == null)
            search.setSearchCondition("TITLE");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");

        int currentPage = search.getPage();
        Members member = memberService.findMember(principal.getMember().getMemberID());
        Page<PointHistory> pointHistoryList = pointHistoryService.getPointHistoryList(search, member, currentPage);

        if (pointHistoryList.getNumberOfElements() == 0) {
            search.setPage(1);
        } else {
            search.setPage(pointHistoryList.getTotalPages());
        }

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalNum", pointHistoryList.getTotalElements());
        model.addAttribute("pointHistoryList", pointHistoryList);
        model.addAttribute("member", member);
        model.addAttribute("searchResult", search);

        return "mypage/pointList";
    }

    @RequestMapping("/mypage/itemList")
    public String itemList(@AuthenticationPrincipal SecurityUser principal, Model model, Search search) {
        if (search.getSearchCategory() == null)
            search.setSearchCategory("");
        if (search.getSearchCondition() == null)
            search.setSearchCondition("TITLE");
        if (search.getSearchKeyword() == null)
            search.setSearchKeyword("");

        int currentPage = search.getPage();
        Members member = memberService.findMember(principal.getMember().getMemberID());
        Page<OrderHistory> orderhistoryList = orderHistoryService.getOrderHistoryList(search, member, currentPage);

        System.out.println("==============> orderList " + orderhistoryList.getTotalElements());
        if (orderhistoryList.getNumberOfElements() == 0) {
            search.setPage(1);
        } else {
            search.setPage(orderhistoryList.getTotalPages());
        }

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalNum", orderhistoryList.getTotalElements());
        model.addAttribute("orderhistoryList", orderhistoryList);
        model.addAttribute("member", member);
        model.addAttribute("searchResult", search);

        return "mypage/itemList";
    }

}
