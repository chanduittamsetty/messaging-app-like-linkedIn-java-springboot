package com.codingshuttle.linkedin.posts_service.services;

import com.codingshuttle.linkedin.posts_service.dto.PostCreateRequestDto;
import com.codingshuttle.linkedin.posts_service.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostsService {
    PostDto createPost(PostCreateRequestDto postCreateRequestDto,Long userId);
    PostDto getPostById(Long postId);
    List<PostDto> getAllPostsOfUser(Long userId);
}
