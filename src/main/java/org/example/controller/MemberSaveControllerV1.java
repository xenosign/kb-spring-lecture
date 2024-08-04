package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.MemberDtoListV1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class MemberSaveControllerV1 {
    private MemberDtoListV1 memberList = MemberDtoListV1.getInstance();

    @RequestMapping("/member/form/save")
    public String process(HttpServletRequest request, HttpServletResponse response) {
        log.info("================> 회원 추가 Request 호출, /member/form/save");

        String id = request.getParameter("id");
        String name = request.getParameter("name");

        memberList.addList(id, name);

        request.setAttribute("memberList", memberList.getList());
        return "member-show";
    }
}
