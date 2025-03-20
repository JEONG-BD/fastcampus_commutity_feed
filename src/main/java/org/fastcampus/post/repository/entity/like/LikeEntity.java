package org.fastcampus.post.repository.entity.like;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.repository.TimeBaseEntity;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.comment.Comment;
import org.fastcampus.user.domain.User;

@Entity
@NoArgsConstructor
@Table(name="community_like")
@Getter
public class LikeEntity extends TimeBaseEntity {

    @EmbeddedId
    private LikeIdEntity id;

    public LikeEntity(Post post, User likedUser){
        this.id = new LikeIdEntity(post.getId(), likedUser.getId(), LikeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likedUser){
        this.id = new LikeIdEntity(comment.getId(), likedUser.getId(), LikeTarget.POST.name());
    }
}
