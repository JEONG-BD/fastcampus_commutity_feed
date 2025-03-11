package org.fastcampus.post.application.dto;

import org.fastcampus.post.domain.content.PostPublicationState;

public record PostCreateRequestDto(Long userId, String content, PostPublicationState state) {
}
