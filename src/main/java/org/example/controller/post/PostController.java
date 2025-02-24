package org.example.controller.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.member.MemberRepository;
import org.example.dto.post.PostDto;
import org.example.dto.post.PostRepository;
import org.example.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/post/v1")
public class PostController {
    private final PostRepository postRepository;
    private String context = "/post/v1";

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 목록
    @GetMapping("/show")
    public String postList(HttpServletRequest request, Model model) {
        log.info("================> 게시글 목록 페이지 호출, " + request.getRequestURI());

        model.addAttribute("postList", postRepository.findAll());
        return context + "/post-show";
    }
    // 게시글 검색
    @GetMapping("/search")
    public String postSearch(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request,
            Model model
    ) {
        log.info("================> 게시글 검색 기능 호출, " + request.getRequestURI());

        model.addAttribute("postList", postRepository.findByCond(title, content));
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

        postRepository.save(title, content);

        return "redirect:/post/v1/show";
    }

    // 게시글 수정 페이지 호출
    @GetMapping("/update")
    public String postEdit(@RequestParam("id") String id, HttpServletRequest request, Model model) {
        log.info("================> 게시글 수정 페이지 호출, " + request.getRequestURI());

        long postId = Long.parseLong(id);

        System.out.println("##### " + postId);
        PostDto post = postRepository.findById(postId);

        model.addAttribute("post", post);
        return context + "/post-update";
    }

    // 게시글 수정
    @PostMapping("/update")
    public String postUpdate(
            @RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request
    ) {
        log.info("================> 게시글 수정 기능 호출, " + request.getRequestURI());

        long postId = Long.parseLong(id);
        int affectedRows = postRepository.update(postId, title, content);

        if (affectedRows > 0) log.info("수정 성공");

        return "redirect:/post/v1/show";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam("id") String id, HttpServletRequest request) {
        log.info("================> 게시글 삭제 기능 호출, " + request.getRequestURI());

        long postId = Long.parseLong(id);
        int affectedRows = postRepository.delete(postId);

        if (affectedRows > 0) log.info("삭제 성공");

        return "redirect:/post/v1/show";
    }

    // 에러
    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        log.info("================> 게시글 목록 페이지 호출, " + request.getRequestURI());

        if (true) {
            throw new RuntimeException("의도적으로 발생시킨 예외");
        }
        return context + "/post-show";
    }
}
