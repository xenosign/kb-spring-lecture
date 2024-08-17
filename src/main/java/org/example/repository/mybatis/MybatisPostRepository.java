package org.example.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.example.dto.post.PostDto;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class MybatisPostRepository {
    private final PostMapper postMapper;


    List<PostDto> findByCond(String title, String content) {
        return postMapper.findByCond(title, content);
    }

    List<PostDto> findAll() {
        return postMapper.findAll();
    }
    PostDto findById(Long id) {
        return postMapper.findById(id);
    }
    void save(String title,String content) {
        postMapper.save(title, content);
    }
    int delete(@Param("id") Long id) {
        return postMapper.delete(id);
    }
    int update(@Param("id") Long id, @Param("title") String title, @Param("content") String content) {
        return postMapper.update(id, title, content);
    }
}
