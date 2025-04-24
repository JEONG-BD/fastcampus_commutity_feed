package org.fastcampus.post.repository.post_queue;

import org.fastcampus.post.ui.dto.GetPostResponseDto;

import java.util.List;

public interface UserPostQueueQueryRepository {
    List<GetPostResponseDto> getContentResponse(Long userId, Long lastPostId);
}
