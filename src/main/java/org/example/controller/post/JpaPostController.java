package org.example.controller.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.repository.jpa.JpaPostRepository;
import org.example.domain.Post;
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
@RequestMapping("/post/v2")
public class JpaPostController {
    private final JpaPostRepository jpaPostRepository;
    private String context = "/post/v2";

    @GetMapping("/show")
    public String postList(HttpServletRequest request, Model model) {
        log.info("================> 게시글 목록 페이지 호출, " + request.getRequestURI());

        model.addAttribute("postList", jpaPostRepository.findAll());
        return context + "/post-show";
    }

    // 게시글 추가 페이지
    @GetMapping("/new")
    public String postNew(HttpServletRequest request) {
        log.info("================> 게시글 추가 페이지 호출, " + request.getRequestURI());
        return context + "/post-new";
    }

    // 게시글 추가
    @PostMapping("/new")
    public String postSave(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request
    ) {
        log.info("================> 게시글 추가 기능 호출, " + request.getRequestURI());

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);

        Post result = jpaPostRepository.save(post);

        log.info("Post saved with ID: {}", post.getId());
        
        if (result != null) log.info("게시글 추가 성공");

        return "redirect:/post/v2/show";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam("id") String id, HttpServletRequest request) {
        log.info("================> 게시글 삭제 기능 호출, " + request.getRequestURI());

        Long postId = Long.parseLong(id);
        jpaPostRepository.delete(postId);

        log.info("Post with ID {} deleted", id);
        
        return "redirect:/post/v2/show";
    }
}