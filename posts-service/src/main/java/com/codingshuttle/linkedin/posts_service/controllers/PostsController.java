package com.codingshuttle.linkedin.posts_service.controllers;

import com.codingshuttle.linkedin.posts_service.dto.PostCreateRequestDto;
import com.codingshuttle.linkedin.posts_service.dto.PostDto;
import com.codingshuttle.linkedin.posts_service.services.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postCreateRequestDto, HttpServletRequest httpServletRequest){
        PostDto createdPost = postsService.createPost(postCreateRequestDto,1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        PostDto post = postsService.getPostById(postId);
        return ResponseEntity.ok(post);
    }
    @GetMapping("/users/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsOfUser(@PathVariable Long userId){
        List<PostDto> posts = postsService.getAllPostsOfUser(userId);
        return ResponseEntity.ok(posts);
    }
}
