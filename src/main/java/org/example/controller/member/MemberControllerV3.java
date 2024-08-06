package org.example.controller.member;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.MemberDtoListV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequestMapping("/member/v3")
public class MemberControllerV3 {
    private final MemberDtoListV2 memberDtoList;

    @Autowired
    public MemberControllerV3(MemberDtoListV2 memberDtoList) { // 생성자 이름 수정
        this.memberDtoList = memberDtoList;
    }

    @RequestMapping("/show")
    public String process(Model model) {
        log.info("================> 회원 조회 페이지 호출, /member/list");

        model.addAttribute("memberList", memberDtoList.getList());
        return "member-show2";
    }

    @RequestMapping("/form")
    public String home(HttpServletRequest request, HttpServletResponse response) {
        log.info("================> 회원 추가 페이지 호출, /member/register");

        return "member-form";
    }

    @RequestMapping("/form/save")
    public String process(HttpServletRequest request, HttpServletResponse response) {
        log.info("================> 회원 추가 Request 호출, /member/form3/save2"); // 경로 수정

        String id = request.getParameter("id");
        String name = request.getParameter("name");

        memberDtoList.addList(id, name); // memberList -> memberDtoList로 수정

        request.setAttribute("memberList", memberDtoList.getList()); // memberList -> memberDtoList로 수정
        return "member-show2";
    }
}
