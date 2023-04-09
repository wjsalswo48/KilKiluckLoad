package mz.sixsense.security.service;

import mz.sixsense.member.entity.Members;
import mz.sixsense.member.repository.MemberRepository;
import mz.sixsense.member.service.MemberService;
import mz.sixsense.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepo;
    @Autowired
    private MemberService memberService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Members> optional = memberRepo.findById(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException(username + "사용자 없음");
        } else {
            Members members = optional.get();
            memberService.recentMember(members);
            return new SecurityUser(members);
        }
    }
}