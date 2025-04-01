package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.PostUpdateRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostServiceTest extends PostApplicationTestTemplate{

//    private final PostService postService = FakeObjectFactory.getPostService();
//    private final UserService userService = FakeObjectFactory.getUserService();
//
//    private final User user = userService.createUser(new UserCreateRequestDto("user1", null));
//    private final User otherUser = userService.createUser(new UserCreateRequestDto("user1", null));
//
//    private final PostCreateRequestDto dto = new PostCreateRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);

    @Test
    @DisplayName("Post 생성에 성공한다")
    public void givenCreated_whenPost_thenEquals() throws Exception{
        //given
        Post savedPost = postService.createPost(dto);

        //when
        Post post = postService.getPost(savedPost.getId());
        //then
        Assertions.assertEquals(savedPost, post);
    }

    @Test
    @DisplayName("Post 수정에 성공한다")
    public void givenCreated_whehUpdate_thenReturnUpdatePost() throws Exception{
        //given
        String newConent = "this is test content";
        Post savedPost = postService.createPost(dto);
        PostUpdateRequestDto updateDto = new PostUpdateRequestDto(savedPost.getId(),
                user.getId(),
                newConent,
                PostPublicationState.PUBLIC);

        //when
        Post updatedPost = postService.updatePost(updateDto);

        //then
        Assertions.assertEquals(newConent, updatedPost.getContent());
    }

    @Test
    @DisplayName("작성자가 좋아요 클릭에 실패한다. ")
    public void givenCreatedPost_whenLiked_thenThrowsException() throws Exception{
        //given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), user.getId());

        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> postService.likePost(likeDto));
    }

    @Test
    @DisplayName("작성자가 좋아요 클릭에 성공한다. ")
    public void givenCreatedPost_whenLiked_thenThrowNothing() throws Exception{
        //given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());

        //when
        //then
        Assertions.assertDoesNotThrow(() -> postService.likePost(likeDto));
        Assertions.assertEquals(savedPost.getLikeCount(), 1);
    }

    @Test
    @DisplayName("작성자가 좋아요를 두번 클릭에 실패한다. ")
    public void givenCreatedPost_whenTwoLiked_thenThrowException() throws Exception{
        //given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);
        //when
        //then
        Assertions.assertDoesNotThrow(() -> postService.likePost(likeDto));
    }

    @Test
    @DisplayName("작성자가 좋아요 해제에 성공한다.")
    public void givenCreatedPost_whenUnLiked_thenThrowException() throws Exception{
        //given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeDto);
        //then

        Assertions.assertEquals(savedPost.getLikeCount(), 1);
        Assertions.assertDoesNotThrow(() -> postService.unlikePost(likeDto));

        Assertions.assertEquals(savedPost.getLikeCount(), 0);
    }

    @Test
    @DisplayName("작성자가 좋아요 누르지않고 좋아요 해제에 실패한다.")
    public void givenCreatedPost_whenLiked_thenThrowException() throws Exception{
        //given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());

        //then

        Assertions.assertDoesNotThrow(() -> postService.unlikePost(likeDto));
    }
}
