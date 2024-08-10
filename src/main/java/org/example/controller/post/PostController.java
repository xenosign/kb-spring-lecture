package org.example.controller.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.member.MemberRepository;
import org.example.dto.post.PostRepository;
import org.example.mapper.PostMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post/v1")
public class PostController {
    private final PostRepository postRepository;
    ;
    private String context = "/post";

    @GetMapping("/show")
    public String postList(HttpServletRequest request, Model model) {
        log.info("================> 게시글 목록 페이지 호출, " + request.getRequestURI());

        model.addAttribute("postList", postRepository.findAll());
        return context + "/post-show";
    }

    @GetMapping("/search")
    public String postSearch(@RequestParam("query") String query, HttpServletRequest request, Model model) {
        log.info("================> 게시글 검색 기능 호출, " + request.getRequestURI());

        model.addAttribute("postList", postRepository.findByTitle(query));
        return context + "/post-show";
    }

    @GetMapping("/new")
    public String postNew(HttpServletRequest request) {
        log.info("================> 게시글 추가 페이지 호출, " + request.getRequestURI());
        return context + "/post-new";
    }

    @PostMapping("/new")
    public String postSave(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request
    ) {
        log.info("================> 게시글 추가 기능 호출, " + request.getRequestURI());
        postRepository.save(title, content);

        return "redirect:/post/v1/show";
    }
}
