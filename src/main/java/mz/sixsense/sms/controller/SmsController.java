package mz.sixsense.sms.controller;

import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.service.MemberService;
import mz.sixsense.security.SecurityUser;
import mz.sixsense.sms.service.SmsService;
import mz.sixsense.sms.service.UserPhoneService;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PointHistoryService pointHistoryService;

    @Autowired
    private UserPhoneService userPhoneService;

    @GetMapping("/system/phone")
    public void phone(@AuthenticationPrincipal SecurityUser principal, Model model) {
        Members member = memberService.findMember(principal.getMember().getMemberID());

        model.addAttribute("member", member);
    }

    @PostMapping("/memberPhoneCheck")
    @ResponseBody
    public String memberPhoneCheck(@RequestParam(value = "to") String to) throws CoolsmsException {
//        System.out.println("===================-=-=-=-=-=+++++");
//        System.out.println("==================================" + principal.getMember());
//        Members member = principal.getMember();
        System.out.println("===============>" + userPhoneService.checkUserPhone(to));

        if(userPhoneService.checkUserPhone(to) == 0){
            return "이미 휴대폰 인증이 완료된 번호 입니다.";
        }else{
            return smsService.phoneNumberCheck(to);
        }


    }

    @GetMapping("/phoneConfirm")
    @Transactional
    public String phoneConfirm(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam String userphone) {
//        System.out.println("이동이동이동이동이동이동이동이동이동이동이동");
        System.out.println("=======================================" + principal.getMember());
        System.out.println("=======================================>>" + userphone);
        Members member = principal.getMember();
        String pointReson = "휴대폰 본인 인증";
        Long point = 1000L;

        userPhoneService.insertUserPhone(userphone);
        memberService.changRole(member);
        memberService.updatePoint(member, point);
        pointHistoryService.insertPointHistory(member, pointReson, point);


        return "redirect:/system/login";
    }

}
