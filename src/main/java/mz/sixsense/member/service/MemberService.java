package mz.sixsense.member.service;

import mz.sixsense.member.entity.Members;
import mz.sixsense.security.SecurityUser;

public interface MemberService {

    void signupMember(Members members);

    String checkID(String memberID, String type);

    String findMemberID(String mname, String memailID);

    String findMemberID(String mname);

    Members findMember(String memberID);

    Members findMemberPwd(String mid, String memail);
//
//    String findMemberPwdPull(String mname, String memail);

    //    String changPassword(String mid, String memail, String tempPassword);
    void changPassword(Members member, String tempPassword);

	void updatePoint(Members member, Long pointVar);
    void changRole(Members member);

    Members currentPasswordCheck(String encoderPwd, String member);

    void passwordChange(String newPassword, SecurityUser principal);

    void updateMmeber(String newName, String newNickName, String newEmail, SecurityUser principal);

    void dropMember(SecurityUser principal);

    void dropMember(Members dropMember);

    void recentMember(Members member);

}
