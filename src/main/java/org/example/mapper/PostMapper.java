package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.dto.member.MemberDto;
import org.example.dto.post.PostDto;

import java.util.List;

@Mapper
public interface PostMapper {
    List<MemberDto> findByTitle(String title);
    List<MemberDto> findAll();
    void save(String title, String content);
}
