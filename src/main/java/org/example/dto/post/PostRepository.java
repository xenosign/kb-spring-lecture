package org.example.dto.post;

import lombok.RequiredArgsConstructor;
//import org.apache.ibatis.annotations.Param;
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

    public PostDto findById(Long id) {
        return postMapper.findById(id);
    }

    public List<PostDto> findAll() {
        return postMapper.findAll();
    }

    public void save(String title, String content) {
        postMapper.save(title, content);
    }

    public int update(Long id, String title, String content) {
        return postMapper.update(id, title, content);
    }

    public int delete(Long id) {
       return postMapper.delete(id);
    }
}
