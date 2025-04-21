package org.fastcampus.post.repository.entity.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.domain.User;

@Entity
@Table(name = "community_user_post_queue")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPostQueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long postId;

    private Long authorId;

    public UserPostQueueEntity(Long postId, Long userId, Long authorId){
        this.userId = userId;
        this.postId = postId;
        this.authorId = authorId;
    }
}
