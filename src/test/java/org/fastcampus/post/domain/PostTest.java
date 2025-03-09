package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostTest {

    private final UserInfo userInfo = new UserInfo("TestA","url");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);

    private final Post post = new Post(1L, user, new PostContent("content"));

    @Test
    @DisplayName("다른 사용자가 좋아요를 클릭하면 해당 글의 좋아요가 1 증가한다.")
    public void givenPostCreated_WhenLikeByOtherUser_ThenLikeCountShouldBe1() throws Exception{
        //given
        post.like(otherUser);
        //when

        //then
        Assertions.assertEquals(post.getLikeCount(), 1);
    }

    @Test
    @DisplayName("글 작성자가 좋아요를 클릭하면 예외를 반환한다.")
    public void givenPostCreated_WhenLikeByAuthor_ThenThrowException() throws Exception{
        //given
        //when

        //then`
        Assertions.assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    @DisplayName("글 작성자가 좋아요를 해제하면 좋아요가 1 감소한다.")
    public void givenPostCreated_WhenUnLikeByOtherUser_ThenLikeCountShouldBe0() throws Exception{
        //given
        //when
        post.like(otherUser);
        post.unlike(otherUser);
        //then`
        Assertions.assertEquals(post.getLikeCount(), 0);
    }

    @Test
    @DisplayName("글 수정에 성공한다.")
    public void givenPostCreated_WhenUpdated_ThenContentShouldBeUpdated() throws Exception{
        //given
        String newPostConetnt = "new content";

        //when
        post.updatePost(user, newPostConetnt, PostPublicationState.PUBLIC);

        //then
        Assertions.assertEquals(newPostConetnt, post.getContent());
    }
    @Test
    @DisplayName("글 수정에 실패한다.")
    public void givenPostCreated_WhenUpdatedByOtherUser_ThenContentShouldBeUpdated() throws Exception{
        //given
        String newPostConetnt = "new content";

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, newPostConetnt, PostPublicationState.PUBLIC));
    }







}
