package org.fastcampus.post.domain.comment;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommentTest {

    private final  UserInfo userInfo = new UserInfo("test", "url");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);
    private final PostContent postContent = new PostContent("abcddddd");
    private final Post post = new Post(1L, user, postContent);

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userServiceTest = new UserService(userRepository);



    @Test
    @DisplayName("빈 사용자로 Comment 작성에 실패한다")
    public void givenCreated_whenAuthorNull_thenThrowException () throws Exception{
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Comment(1L, post, null, postContent));
    }
    @Test
    @DisplayName("사용자로 Comment 작성에 성공한다")
    public void givenCreated_whenAuthorNull_thenNothing () throws Exception{
        //given
        //when
        //then
        Assertions.assertDoesNotThrow(() -> new Comment(1L, post, user, postContent));
    }
    @Test
    @DisplayName("사용자로 Comment 좋아요를 해제 하면 좋아요가 0이 된다.")
    public void givenCreated_whenUnLike_thenLikeCountShouldBe0 () throws Exception{
        //given
        Comment comment = new Comment(1L, post, user, postContent);
        //when
        comment.like(otherUser);
        comment.unlike(otherUser);
        //then
        Assertions.assertEquals(comment.getLikeCount(), 0);
    }

    @Test
    @DisplayName("사용자로 Comment 좋아요를 누르면 1을 반환한다")
    public void givenCreated_whenLike_thenLikeCountShouldBe1 () throws Exception{
        //given
        Comment comment = new Comment(1L, post, user, postContent);
        //when
        comment.like(otherUser);
        //then
        Assertions.assertEquals(comment.getLikeCount(), 1);
    }

    @Test
    @DisplayName("글쓴이로 Comment 좋아요를 누르면 에러를 반환한다")
    public void givenCreated_whenLikeByAuthor_thenThrowsException () throws Exception{
        //given
        Comment comment = new Comment(1L, post, user, postContent);
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    @DisplayName("글쓴이로 Comment 수정하면 성공한다")
    public void givenCreated_whenUpdatedByAuthor_thenNothing () throws Exception{
        //given
        Comment comment = new Comment(1L, post, user, postContent);
        String newComment = "new Comment";
        //when
        comment.updateComment(user, newComment);
        //then
        Assertions.assertEquals(newComment,comment.getContent());
    }

}
