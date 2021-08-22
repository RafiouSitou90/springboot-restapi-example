package com.rafdev.springboot.restapi.example.repository;

import com.rafdev.springboot.restapi.example.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>
{
}
