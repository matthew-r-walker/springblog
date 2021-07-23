package com.codeup.springblog.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(long id);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    void deleteById(long id);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Post p SET p.title = ?2, p.body = ?3 WHERE p.id = ?1")
//    void editById(Long id, String title, String body);

    Post save(Post post);

//    @Query("FROM Ad a WHERE a.title LIKE %:term%")
//    Post findFirstByTitle(String term);
}
