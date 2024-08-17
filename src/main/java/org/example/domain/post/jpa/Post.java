package org.example.domain.post.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or another appropriate strategy
    private Long id;

    public Post() {
    }

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
}
