package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.application.CommentService;
import org.fastcampus.post.application.dto.CommentCreateRequestDto;
import org.fastcampus.post.application.dto.CommentUpdateRequestDto;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CommentCreateRequestDto dto){

        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name= "commentId") Long commentId,  @RequestBody CommentUpdateRequestDto dto){
        Comment comment = commentService.update(commentId, dto);
        return Response.ok(comment.getId());
    }

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto dto){
        commentService.likeComment(dto);
        return Response.ok(null);
    }

}
