package mz.sixsense.calendar.controller;

import mz.sixsense.calendar.service.TodayCheckService;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.repository.MemberRepository;
import mz.sixsense.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@RestController
public class CalendarController {
    private final MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PointHistoryService pointHistoryService;

    @Autowired
    private TodayCheckService todayCheckService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public CalendarController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/todayCheck")
    @Transactional
    public Long todayCheck(@RequestParam("memberID")String memberID, Model model) {
        System.out.println("=============>"+ memberID);

        Members member = memberService.findMember(memberID);
        String pointReason = "출석 체크";

        Long point = 100L;

        String resultDate = new SimpleDateFormat("yyyy-MM-dd").format(Timestamp.valueOf(LocalDate.now().atStartOfDay()));

        System.out.println("=================>" + member);

        memberService.updatePoint(member, point);
        pointHistoryService.insertPointHistory(member, pointReason, point);
        member = memberService.findMember(memberID);
        System.out.println("++++++++++++++++++++++++++++++>" + member);

//        model.addAttribute("member", member);
        todayCheckService.insertTodayCheck(member, resultDate);

//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getMemberID(), member.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        System.out.println("------------>>>>>" + authentication);

        return member.getTotalPoint();
    }

    @GetMapping("/todayCheckALL")
    public int todayCheckAll(@RequestParam("memberID") String memberID){

        Members member = memberService.findMember(memberID);
        String resultDate = new SimpleDateFormat("yyyy-MM-dd").format(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
        String check = todayCheckService.memberTodayCheck(member, resultDate);

        if(check.equals(resultDate)){
            return 1;
        } else {
            return 0;
        }
    }



//    @RequestMapping(value = "/todayCheck", method = RequestMethod.GET)
//    @ResponseBody
//    public List<String> getCalendarList(HttpServletRequest request){
//
//        return null;
//    }

//    @GetMapping("/todayCheck")
//    @ResponseBody
//    public String clickCalender(@AuthenticationPrincipal SecurityUser member){
////        System.out.println("======================" + member);
//        String resultDate = new SimpleDateFormat("yyyy-MM-dd").format(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
//        System.out.println("+++++++++" + resultDate);
//        //db줘장 구현
//
//        return "{\"resultDate\":\""+resultDate+"\"}";
//    }

//    @GetMapping("/todayCheckAll")
//    @ResponseBody
//    public List<String> clickCalender(@AuthenticationPrincipal SecurityUser member) {
////        System.out.println("======================" + member);
//        String resultDate = new SimpleDateFormat("yyyy-MM-dd").format(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
////        List<> resultDate = new SimpleDateFormat("yyyy-MM-dd").format(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
//        System.out.println("+++++++++" + resultDate);
//        //db줘장 구현
//        List<String> aaa = new ArrayList<>();
//        aaa.add("2022-12-18");
//        aaa.add("2022-12-19");
//        aaa.add("2022-12-20");
//
//        return aaa;
//    }


}
