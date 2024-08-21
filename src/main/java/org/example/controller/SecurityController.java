//package org.example.controller;
//
//import lombok.extern.log4j.Log4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Log4j
//@Controller
//@RequestMapping("/security")
//public class SecurityController {
//    @GetMapping("/all")
//    public void doAll() {
//        log.info("누구나 접속 가능");
//    }
//
//    @GetMapping("/member") // MEMBER 또는 ADMIN 권한 필요
//    public void doMember() {
//        log.info("로그인 필요");
//    }
//    @GetMapping("/admin") // ADMIN 권한 필요
//    public void doAdmin() {
//        log.info("관리자 권한 필요");
//    }
//}
