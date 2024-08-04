package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.MemberDtoListV1;
import org.example.dto.MemberDtoListV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class MemberShowControllerV2 {
    private final MemberDtoListV2 memberDtoList;

    @Autowired
    public MemberShowControllerV2(MemberDtoListV2 memberDtoList) {
        this.memberDtoList = memberDtoList;
    }

    @GetMapping("/member/show2")
    public String process(HttpServletRequest request, HttpServletResponse response) {
        log.info("================> 회원 조회 페이지 호출, /member/list");

        System.out.println(memberDtoList.getList());

        request.setAttribute("memberList", memberDtoList.getList());
        return "member-show2";
    }
}
