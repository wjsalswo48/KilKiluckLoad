package mz.sixsense.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/system/login")
    public void login() {
    }

    @GetMapping("/system/accessDenied")
    public void accessDenied() {
    }

    @GetMapping("/system/logout")
    public void logout() {
    }

    @GetMapping("/admin/adminPage")
    public void admin() {
    }

    @GetMapping("/system/basic")
    public void basic() {
    }

    @GetMapping("/system/loginSuccess")
    public void loginSuccess() {
    }

    @GetMapping("/system/signup")
    public void signup1() {
    }

    @GetMapping("/system/findMember")
    public void findMember() {
    }

//    @GetMapping("/system/phone")
//    public void phone() {
//    }

}