package org.fastcampus.post.repository.jpa;

import org.fastcampus.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {


    @Modifying
    @Query(value ="UPDATE PostEntity p " +
            " SET p.content = :#{#postEntity.getContent()}, " +
            " p.state = :#{#postEntity.getState()}, " +
            " p.updDt = now() " +
            " WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);


    //@Modifying
    //@Query(value = "UPDATE PostEntity  p " +
    //        " SET p.likeCount = :#{#postEntity.likeCount}, " +
    //        " p.updDt = now()" +
    //        " WHERE p.id = :#{#postEntity.getId()}")
    //void updatePostLike(PostEntity postEntity);
    //
    //

    @Modifying
    @Query(value = "UPDATE PostEntity  p " +
            " SET p.likeCount = p.likeCount + :likeCount, " +
            " p.updDt = now()" +
            " WHERE p.id = :postId")
    void updatePostLike(Long postId, Integer likeCount);


    @Modifying
    @Query(value = "UPDATE PostEntity  p " +
            " SET p.commentCount = p.commentCount + 1, " +
            " p.updDt = now()" +
            " WHERE p.id = :postId")
    void increaseCommentCount(Long postId);

    @Query(value = " SELECT p " +
            " FROM PostEntity  p " +
            " WHERE p.author.id = :authorId")
    List<PostEntity> findAllPostIdsByAuthorId(Long authorId);
}
