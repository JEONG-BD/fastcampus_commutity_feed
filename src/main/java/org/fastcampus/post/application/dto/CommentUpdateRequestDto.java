package org.fastcampus.post.application.dto;

public record CommentUpdateRequestDto(Long commentId, Long userId, String content) {
}
