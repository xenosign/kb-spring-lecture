package org.example.controller.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.post.PostDto;
import org.example.dto.post.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/post/v2/rest")
public class RestPostControllerV2 {
    private final PostRepository postRepository;
    private String context = "/post/v1";

    // 게시글 목록
    @GetMapping("/show")
    public ResponseEntity<List<PostDto>> postList(HttpServletRequest request, Model model) {
        log.info("================> 게시글 목록 페이지 호출, " + request.getRequestURI());

        List<PostDto> allList = postRepository.findAll();

        return ResponseEntity.ok(allList);
    }

    // ResponseEntity 테스트
    @GetMapping(value = "/test", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청을 처리할 수 없습니다");
    }

    // 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> postSearch(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpServletRequest request,
            Model model
    ) {
        log.info("================> 게시글 검색 기능 호출, " + request.getRequestURI());

        List<PostDto> findList = postRepository.findByCond(title, content);

        return ResponseEntity.ok(findList);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> postDelete(@RequestParam("id") Long id, HttpServletRequest request) {
        log.info("================> 게시글 삭제 기능 호출, " + request.getRequestURI());

        int affectedRows = postRepository.delete(id);

        if (affectedRows > 0) {
            return ResponseEntity.ok("삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청을 처리할 수 없습니다");
        }
    }
}
