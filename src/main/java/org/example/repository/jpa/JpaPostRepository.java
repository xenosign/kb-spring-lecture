package org.example.repository.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Repository
public class JpaPostRepository {
    private final EntityManager em;

    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    public List<Post> findAll() {
        String jpql = "select p from Post p";
        List<Post> postList = em.createQuery(jpql, Post.class).getResultList();
        return postList;
    }

    public int delete(Long id) {
        Post post = em.find(Post.class, id);
        if (post != null) {
            em.remove(post);
            return 1;
        }
       return 0;
    }
}
