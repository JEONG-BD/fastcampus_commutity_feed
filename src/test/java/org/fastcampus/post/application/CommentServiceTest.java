package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CommentUpdateRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class CommentServiceTest extends PostApplicationTestTemplate {


    @Test
    public void givenCreateComment_wheCreateComment_thenReturnComment() throws Exception{
        //given
        Comment comment = commentService.createComment(commentDto);
        //when 
        String content = comment.getContent();
        //then
        Assertions.assertEquals(comment.getContent(), content);
    }

    @Test
    public void givenCreateComment_whenUpdateComment_thenReturnUpdateComment() throws Exception{
        //given
        Comment comment = commentService.createComment(commentDto);
        String updateContent = "update content";

        //when
        CommentUpdateRequestDto updateDto = new CommentUpdateRequestDto( user.getId(), updateContent);
        Comment updatedComment = commentService.update(comment.getId(), updateDto);

        //then
        Assertions.assertEquals(updatedComment.getContent(), updateContent);
    }

    @DisplayName("")
    @Test
    public void givenCreateComment_whenLike_thenReturnShouldBe1() throws Exception{
        //given
        Comment comment = commentService.createComment(commentDto);
        LikeRequestDto likeDto = new LikeRequestDto(post.getId(), otherUser.getId());
        //when
        commentService.likeComment(likeDto);

        //then
        Assertions.assertEquals(comment.getLikeCount(), 1);
    }

}
