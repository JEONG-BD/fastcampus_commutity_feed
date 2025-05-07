package org.fastcampus.acceptance;

import org.fastcampus.acceptance.utils.AcceptanceTestTemplate;
import org.fastcampus.post.application.dto.PostCreateRequestDto;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.post.ui.dto.GetPostResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.fastcampus.acceptance.steps.FeedAcceptanceSteps.requestFeed;

public class FeedAcceptTest extends AcceptanceTestTemplate {

    @BeforeEach
    void setUp(){
        super.init();
    }

    @Test
    @DisplayName("사용자가 팔로우 하면 팔로우의 피드를 확인할 수 있다.")
    public void givenUserHasFollowerAndCreatePost_whenFollowerUserCreatePost_thewFollowerCanGetPostFromFeed() throws Exception{
        //given
        PostCreateRequestDto dto = new PostCreateRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        //when
        List<GetPostResponseDto> result = requestFeed(1L);

        //then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(createdPostId, result.get(0).getId());
    }


}
