package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.example.controller.board.BoardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardVO> getList();
    public BoardVO get(Long no);
    public int create(BoardVO board);
    public int update(BoardVO board);
    public int delete(Long no);
}
