package org.fastcampus.post.repository.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.TimeBaseEntity;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.repository.entity.UserEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "community_post")
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Integer likeCount;

    @Convert(converter = PostPublicationStateConverter.class)
    private PostPublicationState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id", foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private UserEntity author;

    public PostEntity(Post post){
        this.id = post.getId();
        this.author = new UserEntity(post.getAuthor());
        this.content = post.getContent();
        this.state = post.getState();
        this.likeCount = post.getLikeCount();
    }

    public Post toPost(){
        return Post.builder()
                .id(id)
                .author(author.toUser())
                .content(new PostContent(content))
                .state(state)
                .likeCount(new PositiveIntegerCounter(likeCount))
                .build();
    }
}
