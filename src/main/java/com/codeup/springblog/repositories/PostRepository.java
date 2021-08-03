package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(long id);

    Post save(Post post);

//    @Query("FROM Post p WHERE p.title LIKE %:term%")
//    Post findFirstByTitle(String term);

    @Query("FROM Post p WHERE p.title LIKE %:query% OR p.body LIKE %:query%")
    List<Post> findAllQuery(String query);

    Post findByTitle(String title);
}
