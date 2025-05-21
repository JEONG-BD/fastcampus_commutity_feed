package org.fastcampus.post.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.idempotency.Idempotent;
import org.fastcampus.common.ui.Response;
import org.fastcampus.post.application.PostService;
import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.PostCreateRequestDto;
import org.fastcampus.post.application.dto.PostUpdateRequestDto;
import org.fastcampus.post.domain.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor

public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody PostCreateRequestDto dto){
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @GetMapping("/{postId}")
    public Response<Long> getPost(@PathVariable(name = "postId") Long postId){
        Post post = postService.getPost(postId);
        return Response.ok(post.getId());
    }

    @PatchMapping()
    public Response<Long> updatePost(@RequestBody  PostUpdateRequestDto dto){
        Post post = postService.updatePost(dto);
        return Response.ok(post.getId());
    }

    @Idempotent
    @PostMapping("/like")
    public Response<Void> likePost(@RequestBody LikeRequestDto dto){
        postService.likePost(dto);
        return Response.ok(null);

    }

    @PostMapping("/unlike")
    public Response<Void> unlikePost(@RequestBody LikeRequestDto dto){
        postService.likePost(dto);
        return Response.ok(null);

    }
}
