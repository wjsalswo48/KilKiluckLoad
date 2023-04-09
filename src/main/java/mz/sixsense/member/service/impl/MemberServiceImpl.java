package mz.sixsense.member.service.impl;

import mz.sixsense.enums.Role;
import mz.sixsense.member.entity.Members;
import mz.sixsense.member.repository.MemberRepository;
import mz.sixsense.member.service.MemberService;
import mz.sixsense.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void signupMember(Members members) {
        memberRepository.save(members);
    }

    @Override
    public String checkID(String memberID, String type) {
        if (type.equals("user")) {
            return memberRepository.checkMemberID(memberID);
        } else if (type.equals("com")) {
            return memberRepository.checkMemberID(memberID);
        }
        return null;
    }

    @Override
    public String findMemberID(String mname, String memailID) {
        return memberRepository.findMemberID(mname, memailID);
    }

    @Override
    public String findMemberID(String mname) {

        return memberRepository.findMemberID(mname);
    }

    @Override
    public Members findMember(String memberID) {
        return memberRepository.findMember(memberID);
    }

    @Override
    public Members findMemberPwd(String mid, String memail) {
        return memberRepository.findMemberPwd(mid, memail);
    }

    @Override
    public void changPassword(Members member, String tempPassword) {

        member.setPassword(tempPassword);
        memberRepository.save(member);

    }

    @Override
    public void updatePoint(Members member, Long pointVar) {
        member.setTotalPoint(member.getTotalPoint() + pointVar);
        memberRepository.save(member);
    }

    @Override
    public void changRole(Members member) {
        member.setRole(Role.ROLE_MEMBER_PHONE);
//        member.setTotalPoint(member.getTotalPoint() + 1000);
        memberRepository.save(member);
    }

    @Override
    public Members currentPasswordCheck(String encoderPwd, String member) {
        Members currentMember = memberRepository.findPassword(member);
        System.out.println("========================" + currentMember.getPassword());
        System.out.println("========================" + encoderPwd);

        return currentMember;
    }

    @Override
    public void passwordChange(String newPassword, SecurityUser principal) {
        Members member = principal.getMember();
        member.setPassword(newPassword);
        memberRepository.save(member);
    }

    @Override
    public void updateMmeber(String newName, String newNickName, String newEmai, SecurityUser principal) {

        Members member = principal.getMember();
        member.setName(newName);
        member.setNickName(newNickName);
        member.setEmail(newEmai);

        memberRepository.save(member);

    }

    @Override
    public void dropMember(SecurityUser principal) {

        Members member = principal.getMember();
//        memberRepository.delete(member);
        memberRepository.deleteById(member.getMemberID());

    }

    @Override
    public void dropMember(Members dropMember) {
        memberRepository.deleteById(dropMember.getMemberID());
    }

//    @Override
//    public void recentMmeber(Members member) {
//
//
//    }

    @Override
    public void recentMember(Members member) {

        member.setRecentLogin(new Date());
        memberRepository.save(member);

    }
}
