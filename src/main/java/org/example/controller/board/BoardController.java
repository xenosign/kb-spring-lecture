package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import org.example.mapper.BoardMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Transactional
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardMapper boardMapper;

//    @GetMapping("")
//    public List<BoardVO> getList() {
//        return boardMapper.getList();
//    }



    @GetMapping("")
    public ResponseEntity<List<BoardVO>> getList() {
        return ResponseEntity.ok(boardMapper.getList());
    }

    @GetMapping("/{no}")
    public ResponseEntity<BoardVO> getById(@PathVariable Long no) {
        System.out.println(no);

        BoardVO board = new BoardVO();
        board.setNo(1L);
        board.setTitle("Spring 프레임워크 소개");
        board.setContent("Spring은 자바 엔터프라이즈 애플리케이션 개발을 위한 오픈소스 프레임워크입니다.");
        board.setWriter("홍길동");
        board.setRegDate(new Date(System.currentTimeMillis()));
        board.setUpdateDate(new Date(System.currentTimeMillis()));

        return ResponseEntity.ok(boardMapper.get(no));


    }

    @PostMapping("")
    public ResponseEntity<BoardVO> create(@RequestBody BoardVO newBoard) {
        boardMapper.create(newBoard);
        return ResponseEntity.ok(newBoard);
    }

    @PutMapping("/{no}")
    public ResponseEntity<BoardVO> update(@PathVariable("no") Long no, @RequestBody BoardVO updateBoard) {
        updateBoard.setNo(no);
        boardMapper.update(updateBoard);
        return ResponseEntity.ok(boardMapper.get(no));
    }

    @DeleteMapping("/{no}")
    public ResponseEntity update(@PathVariable("no") Long no) {
        int affectedRows = boardMapper.delete(no);

        if (affectedRows > 0) return  ResponseEntity.ok().build();
        return ResponseEntity.noContent().build();
    }
}
