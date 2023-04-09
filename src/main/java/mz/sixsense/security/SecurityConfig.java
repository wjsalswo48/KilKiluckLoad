package mz.sixsense.security;

import mz.sixsense.security.service.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.userDetailsService(userDetailsService);
        security.authorizeRequests().antMatchers("/", "/system/**","/shop/**").permitAll();
        security.authorizeRequests().antMatchers("/board/insertFreeBoard", "/board/replyWrite", "/board/insertReportBoard","/shop/**").authenticated();
        security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        security.authorizeRequests().antMatchers("/QR/**").hasRole("MEMBER_PHONE");
        security.authorizeRequests().antMatchers("/QR/**").hasAnyRole("ADMIN", "MEMBER_PHONE");
        security.csrf().disable();
        security.formLogin().loginPage("/system/login")
                .defaultSuccessUrl("/", true);
        security.exceptionHandling().accessDeniedPage("/system/accessDenied");
        security.logout().logoutUrl("/system/logout")
                .invalidateHttpSession(true).logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}