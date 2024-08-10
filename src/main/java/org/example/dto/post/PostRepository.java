package org.example.dto.post;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.example.dto.member.MemberDto;
import org.example.mapper.MemberMapper;
import org.example.mapper.PostMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final PostMapper postMapper;

    public List<MemberDto> findByTitle(String title) {
        return postMapper.findByTitle(title);
    }
    public List<MemberDto> findAll() {
        return postMapper.findAll();
    }
    public void save(@Param("title") String title, @Param("content") String content) {
        System.out.println(title);
        System.out.println(content);
        postMapper.save(title, content);
    }
}
