package com.codingshuttle.linkedin.posts_service.services.impl;

import com.codingshuttle.linkedin.posts_service.auth.UserContextHolder;
import com.codingshuttle.linkedin.posts_service.client.ConnectionsClient;
import com.codingshuttle.linkedin.posts_service.dto.PersonDto;
import com.codingshuttle.linkedin.posts_service.dto.PostCreateRequestDto;
import com.codingshuttle.linkedin.posts_service.dto.PostDto;
import com.codingshuttle.linkedin.posts_service.entities.PostEntity;
import com.codingshuttle.linkedin.posts_service.exceptions.ResourceNotFoundException;
import com.codingshuttle.linkedin.posts_service.repositories.PostsRepository;
import com.codingshuttle.linkedin.posts_service.services.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;
    private final ModelMapper modelMapper;
    private final ConnectionsClient connectionsClient;
    @Override
    public PostDto createPost(PostCreateRequestDto postCreateRequestDto) {
        Long userId = UserContextHolder.getCurrentUserId();
        PostEntity post = modelMapper.map(postCreateRequestDto,PostEntity.class);
        post.setUserId(userId);
        return modelMapper.map(postsRepository.save(post),PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Long userId = UserContextHolder.getCurrentUserId();
        List<PersonDto> firstConnections = connectionsClient.getFirstConnections();
        log.info("Received user first connections: {}",firstConnections.toArray().length);
        log.debug("Retrieving post with Id: {}",postId);
        PostEntity post =  postsRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post not found with id:"+ postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostsOfUser(Long userId) {

        List<PostEntity> posts = postsRepository.findByUserId(userId);
        return posts
                .stream()
                .map((element)->modelMapper.map(element,PostDto.class))
                .collect(Collectors.toList());
    }
}
