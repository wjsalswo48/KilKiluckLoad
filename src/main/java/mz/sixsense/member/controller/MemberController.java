package mz.sixsense.member.controller;

import mz.sixsense.enums.Role;
import mz.sixsense.mail.service.MailService;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.history.service.PointHistoryService;
import mz.sixsense.member.service.MemberService;
import mz.sixsense.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PointHistoryService poinHistoryService;

    @Autowired
    private PasswordEncoder encoder;


    /*============================================signup========================================*/
    @PostMapping("/signup")
    public String signup(Members member) {
        System.out.println("membe==================>" + member);
        System.out.println("adminCode ==================>" + member.getAdminCode());
        String adminCode = member.getAdminCode();


        if (adminCode.equals("sixsense")) {
            member.setRole(Role.ROLE_ADMIN);
        } else if (adminCode.equals("")) {
            member.setRole(Role.ROLE_MEMBER);
        }

        member.setPassword(encoder.encode(member.getPassword()));
        memberService.signupMember(member);

        return "redirect:/";
    }

    /*============================================check========================================*/
    @PostMapping("login/checkid")
    @ResponseBody
    public int checkid(@RequestParam("id") String id, @RequestParam("type") String type) {
        if (id.equals(memberService.checkID(id, type))) {
            return 1;
        }
        return 0;
    }

    /*============================================mail========================================*/
    @PostMapping("/system/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam("email") String email) throws Exception {
        System.out.println("==============메일==============");
        String code = mailService.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);

        return code;
    }

    @PostMapping("/login/findMemberID")
    @ResponseBody
    public String findMemberID(@RequestParam("mname") String mname, @RequestParam("memailID") String memailID) {
        System.out.println("===========FINDMEMBER========>" + mname + "//" + memailID);
        return memberService.findMemberID(mname, memailID);
    }

    @PostMapping("/login/findMemberPwd")
    @ResponseBody
    public String findMemberPwd(@RequestParam("mid") String mid, @RequestParam("memail") String memail, @RequestParam("memailconfirm") String memailconfirm) throws Exception {
        System.out.println("======>>" + mid + "/" + memail + "/" + memailconfirm);

        Members member = memberService.findMemberPwd(mid, memail);
        System.out.println("=-=====-=-=-=-=-=-=>> " + member);
        if (member != null) {
            String tempPassword = encoder.encode(mailService.passwordFindMessage(memail));
            System.out.println("========================>>" + tempPassword);
            memberService.changPassword(member, tempPassword);
            return "변경완료";
        }
        return null;

    }

    @GetMapping("/system/mypage")
    public void mypage(@AuthenticationPrincipal SecurityUser principal, Model model) {
        String checkRole = String.valueOf(principal.getMember().getRole());
        System.out.println("--------------------------" + checkRole);
        if (checkRole.equals("ROLE_MEMBER")) {
            model.addAttribute("checkrole", "휴대폰 미인증");
        } else {
            model.addAttribute("checkrole", "휴대폰 인증완료");
        }
        Members member = memberService.findMember(principal.getMember().getMemberID());
        model.addAttribute("member", member);
    }

    @GetMapping("/system/passwordChange")
    public void passwordChange(@AuthenticationPrincipal SecurityUser principal, Model model) {
        model.addAttribute("member", principal.getMember());
    }

    @GetMapping("/system/infoChange")
    public void infoChange(@AuthenticationPrincipal SecurityUser principal, Model model) {
        model.addAttribute("member", principal.getMember());
    }

    @GetMapping("/system/memberDrop")
    public void memberDrop(@AuthenticationPrincipal SecurityUser principal, Model model) {
        model.addAttribute("member", principal.getMember());
    }

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
//        Members member = principal.getMember();
//        Page<PointHistory> pointHistoryList = poinHistoryService.getPointHistoryList(search,member, currentPage);
//
//        if(pointHistoryList.getNumberOfElements() == 0){
//            search.setPage(1);
//        } else {
//            search.setPage(pointHistoryList.getTotalPages());
//        }
//        model.addAttribute("currentPage", currentPage);
//        model.addAttribute("totalNum", pointHistoryList.getTotalElements());
//        model.addAttribute("pointHistoryList", pointHistoryList);
//        model.addAttribute("member", principal.getMember());
//        model.addAttribute("searchResult", search);
//
//        return "mypage/pointList";
//    }
//
//    /*==============================================포인트 리스트=======================================================*/
//    @RequestMapping("/mypage/pointList")
//    public String pointList(@AuthenticationPrincipal SecurityUser principal, Model model, Search search) {
//        if (search.getSearchCategory() == null)
//            search.setSearchCategory("");
//        if (search.getSearchCondition() == null)
//            search.setSearchCondition("TITLE");
//        if (search.getSearchKeyword() == null)
//            search.setSearchKeyword("");
//
//        int currentPage = search.getPage();
//        Members member = principal.getMember();
//        Page<PointHistory> pointHistoryList = poinHistoryService.getPointHistoryList(search,member, currentPage);
//
//        if(pointHistoryList.getNumberOfElements() == 0){
//            search.setPage(1);
//        } else {
//            search.setPage(pointHistoryList.getTotalPages());
//        }
//        model.addAttribute("currentPage", currentPage);
//        model.addAttribute("totalNum", pointHistoryList.getTotalElements());
//        model.addAttribute("pointHistoryList", pointHistoryList);
//        model.addAttribute("member", principal.getMember());
//        model.addAttribute("searchResult", search);
//
//        return "mypage/pointList";
//    }
    /*================================================================================================================*/

    @PostMapping("/currentPasswordChe")
    @ResponseBody
    public int currentPasswordChe(@RequestParam("pwd") String pwd, @RequestParam("member") String member) {
        System.out.println("pwd : " + pwd + "type : " + member);
        Members currentmembers = memberService.currentPasswordCheck(pwd, member);
        if (encoder.matches(pwd, currentmembers.getPassword())) {
            return 0;
        }
        return 1;
    }

    /*==============================================비밀번호 변경=======================================================*/
    @GetMapping("/passwordChange")
    public String passwordChange(@RequestParam("newPassword") String newPassword, @AuthenticationPrincipal SecurityUser principal) {
        System.out.println("================password : " + newPassword);
        System.out.println("================principal : " + principal);

        memberService.passwordChange(encoder.encode(newPassword), principal);

        return "redirect:/system/mypage";
    }

    /*==============================================회원정보 변경=======================================================*/
    @GetMapping("/infoChange")
    public String infoChange(@RequestParam("name") String newName, @RequestParam("nickName") String newNickName, @RequestParam("email") String newEmail, @AuthenticationPrincipal SecurityUser principal) {
        System.out.println("================" + newName + "/" + newNickName + "/" + newEmail);
        memberService.updateMmeber(newName, newNickName, newEmail, principal);
        return "redirect:/system/mypage";
    }

    /*================================================회원탈퇴==========================================================*/
//    @GetMapping("/memberDrop")
//    public String memberDrop(@RequestParam("password") String password, @AuthenticationPrincipal SecurityUser principal) {
//        System.out.println("===============" + password);
//        Members member = principal.getMember();
//
//        if(encoder.matches(password, member.getPassword())){
//            memberService.dropMember(principal);
//        }
//
//        return "redirect:/system/logout";
//    }

    @GetMapping("/memberDrop")
    public String memberDrop(@RequestParam("member") String member) {
//        System.out.println("===============" + password);
        Members dropMember = memberService.findMember(member);
        memberService.dropMember(dropMember);
//        Members member = principal.getMember();

//        if(encoder.matches(password, member.getPassword())){
//            memberService.dropMember(principal);
//        }

        return "redirect:/system/logout";
    }


}










































