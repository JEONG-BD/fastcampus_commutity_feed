package org.fastcampus.post.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.post.application.dto.CommentCreateRequestDto;
import org.fastcampus.post.application.dto.PostCreateRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.UserCreateRequestDto;
import org.fastcampus.user.domain.User;

public class PostApplicationTestTemplate {
    final PostService postService = FakeObjectFactory.getPostService();
    final UserService userService = FakeObjectFactory.getUserService();

    final User user = userService.createUser(new UserCreateRequestDto("user1", null));
    final User otherUser = userService.createUser(new UserCreateRequestDto("user1", null));

    final PostCreateRequestDto dto = new PostCreateRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(dto);

    final CommentCreateRequestDto commentDto = new CommentCreateRequestDto(post.getId(), user.getId(), "this is test comment");

    final CommentService commentService = FakeObjectFactory.getCommentService();

}
