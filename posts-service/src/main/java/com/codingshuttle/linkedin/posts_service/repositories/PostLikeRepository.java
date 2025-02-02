package com.codingshuttle.linkedin.posts_service.repositories;

import com.codingshuttle.linkedin.posts_service.entities.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity,Long> {
    boolean existsByUserIdAndPostId(Long userId,Long postId);

    @Transactional
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
