package com.codingshuttle.linkedin.posts_service.services;

public interface PostLikeService {
    public void likePost(Long postId,Long userId);

    void unlikePost(Long postId, Long l);
}
