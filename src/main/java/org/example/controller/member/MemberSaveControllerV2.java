package org.example.controller.member;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.MemberDtoListV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class MemberSaveControllerV2 {
    private final MemberDtoListV2 memberList;

    @Autowired
    public MemberSaveControllerV2(MemberDtoListV2 memberList) {
        this.memberList = memberList;
    }

    @RequestMapping("/member/v2/form/save")
    public String process(HttpServletRequest request, HttpServletResponse response) {
        log.info("================> 회원 추가 Request 호출, /member/form/save");

        String id = request.getParameter("id");
        String name = request.getParameter("name");

        memberList.addList(id, name);

        request.setAttribute("memberList", memberList.getList());
        return "member-show2";
    }
}
