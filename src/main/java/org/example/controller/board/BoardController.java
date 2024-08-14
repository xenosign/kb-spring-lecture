package org.example.controller.board;

import lombok.RequiredArgsConstructor;
import org.example.mapper.BoardMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
