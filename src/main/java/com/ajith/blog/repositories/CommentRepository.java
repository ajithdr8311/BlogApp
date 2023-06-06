package com.ajith.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajith.blog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
