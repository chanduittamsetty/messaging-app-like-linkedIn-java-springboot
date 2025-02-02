package com.codingshuttle.linkedin.posts_service.repositories;

import com.codingshuttle.linkedin.posts_service.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<PostEntity,Long> {
    List<PostEntity> findByUserId(Long userId);
}
