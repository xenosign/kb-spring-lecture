package org.example.mapper;

import org.apache.ibatis.annotations.Select;
import org.example.board.BoardVO;

import java.util.List;

public interface BoardMapper {
    public List<BoardVO> getList();
    public BoardVO get(Long no);
    public void create(BoardVO board);
    public int update(BoardVO board);
    public int delete(Long no);
}
