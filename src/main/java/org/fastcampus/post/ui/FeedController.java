package org.fastcampus.post.ui;


import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetPostResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    //private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    //@GetMapping("/{userId}")
    //public Response<List<GetPostResponseDto>> getPostFeed(@PathVariable(name = "userId") Long userId, Long lastPostId){
    //    List<GetPostResponseDto> result = userPostQueueQueryRepository.getContentResponse(userId, lastPostId);
    //    return Response.ok(result);
    //}
}
