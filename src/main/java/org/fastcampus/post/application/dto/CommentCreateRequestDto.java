package org.fastcampus.post.application.dto;

public record CommentCreateRequestDto (Long postId, Long userId, String content){
}
