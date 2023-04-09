package mz.sixsense.security;

import mz.sixsense.member.entity.Members;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private static final long serialVersionUID = 950124221215L;
    private Members members;


    public SecurityUser(Members members){
        super(members.getMemberID(), members.getPassword(),
                AuthorityUtils.createAuthorityList(members.getRole().toString()));
        this.members = members;
    }
    public Members getMember() {
        return members;
    }
}