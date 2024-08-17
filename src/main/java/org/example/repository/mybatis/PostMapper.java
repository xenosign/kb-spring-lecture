package org.example.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.dto.post.PostDto;

import java.util.List;

@Mapper
public interface PostMapper {
    List<PostDto> findByCond(@Param("title") String title, @Param("content") String content);
    List<PostDto> findAll();
    PostDto findById(@Param("id") Long id);
    void save(@Param("title") String title, @Param("content") String content);
    int delete(@Param("id") Long id);
    int update(@Param("id") Long id, @Param("title") String title, @Param("content") String content);
}
