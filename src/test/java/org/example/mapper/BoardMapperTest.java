package org.example.mapper;

import lombok.extern.log4j.Log4j;
import org.example.controller.board.BoardVO;
import org.example.config.RootConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j
class BoardMapperTest {
//    @Autowired
//    private BoardMapper boardMapper;
//
//    @Test
//    @DisplayName("Board 목록 부르기")
//    public void getList() {
//        List<BoardVO> lists = boardMapper.getList();
//        for (BoardVO board : lists) {
//            log.info(board);
//        }
//    }
//
//    @Test
//    @DisplayName("Board 의 특정 id 게시글 읽기")
//    public void get() {
//        BoardVO board = boardMapper.get(1L);
//        log.info(board);
//    }
//
//    @Test
//    @DisplayName("Board 글 작성")
//    public void create() {
//        BoardVO board = new BoardVO();
//        board.setTitle("새로작성하는글");
//        board.setContent("새로작성하는내용");
//        board.setWriter("user0");
//        boardMapper.create(board);
//        log.info(board);
//    }
//
//    @Test
//    @DisplayName("BoardMapper의 글 수정")
//    public void update() {
//        BoardVO board = new BoardVO();
//
//        board.setNo(5L);
//        board.setTitle("수정된 제목");
//        board.setContent("수정된 내용");
//        board.setWriter("user00");
//
//        int affectedRows = boardMapper.update(board);
//
//        log.info("UPDATE COUNT: " + affectedRows);
//    }
//
//    @Test
//    @DisplayName("Board 글 삭제 테스트")
//    public void delete() {
//        int affectedRows = boardMapper.delete(3L);
//        log.info("DELETE COUNT: " + affectedRows);
//    }
}

