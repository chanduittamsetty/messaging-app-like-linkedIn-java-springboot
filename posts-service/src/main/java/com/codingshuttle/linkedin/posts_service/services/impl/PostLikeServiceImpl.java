package com.codingshuttle.linkedin.posts_service.services.impl;

import com.codingshuttle.linkedin.posts_service.entities.PostEntity;
import com.codingshuttle.linkedin.posts_service.entities.PostLikeEntity;
import com.codingshuttle.linkedin.posts_service.exceptions.BadRequestException;
import com.codingshuttle.linkedin.posts_service.exceptions.ResourceNotFoundException;
import com.codingshuttle.linkedin.posts_service.repositories.PostLikeRepository;
import com.codingshuttle.linkedin.posts_service.repositories.PostsRepository;
import com.codingshuttle.linkedin.posts_service.services.PostLikeService;
import com.codingshuttle.linkedin.posts_service.services.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostLikeServiceImpl implements PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostsRepository postsRepository;

    @Override
    public void likePost(Long postId, Long userId) {
        log.info("Attempting to like with id: "+postId);
        boolean exists = postsRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found with Id: "+postId);
        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(alreadyLiked) throw new BadRequestException("Post is already liked");
        PostLikeEntity postLikeEntity = new PostLikeEntity();
        postLikeEntity.setUserId(userId);
        postLikeEntity.setPostId(postId);
        postLikeRepository.save(postLikeEntity);
        log.info("post with id: {} liked successfully",postId);
    }

    @Override
    public void unlikePost(Long postId, Long userId) {
        log.info("Attempting to unlike post id : "+postId);
        boolean exists = postsRepository.existsById(postId);
        if(!exists) throw new ResourceNotFoundException("Post not found with Id: "+postId);
        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(!alreadyLiked) throw new BadRequestException("Post is not liked");
        postLikeRepository.deleteByUserIdAndPostId(userId,postId);
        log.info("post with id: {} liked successfully",postId);
    }
}
