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

    public List<PostDto> findByCond(String title, String content) {
        return postMapper.findByCond(title, content);
    }

    public List<PostDto> findAll() {
        return postMapper.findAll();
    }

    public void save(String title, String content) {
        System.out.println("PostRepository " + title);
        System.out.println("PostRepository " + content);
        postMapper.save(title, content);
    }
}
