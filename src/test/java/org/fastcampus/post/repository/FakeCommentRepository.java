package org.fastcampus.post.repository;

import org.fastcampus.post.application.interfaces.CommentRepository;
import org.fastcampus.post.domain.comment.Comment;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {
    private final Map<Long, Comment> map = new HashMap<>();

    @Override
    public Comment save(Comment comment) {

        if(comment.getId() != null){
            map.put(comment.getId(), comment);
        }
        long id = map.size() + 1;
        Comment newComment = new Comment(id, comment.getPost(), comment.getAuthor(),  comment.getContentObj());
        map.put(id, newComment);
        return newComment;
    }

    @Override
    public Comment findById(Long commentId) {
        return map.get(commentId);
    }
}
