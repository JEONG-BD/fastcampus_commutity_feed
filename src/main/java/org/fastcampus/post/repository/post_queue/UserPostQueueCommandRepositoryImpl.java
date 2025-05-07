package org.fastcampus.post.repository.post_queue;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.entity.post.UserPostQueueEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.fastcampus.post.repository.jpa.JpaUserPostQueueRepository;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRelationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {


    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    //private final JpaUserPostQueueRepository jpaUserPostQueueRepository;
    private final UserQueueRedisRepository redisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
        //List<UserPostQueueEntity> userPostQueueEntities = followersIds.stream()
        //        .map(userId -> new UserPostQueueEntity(postEntity.getId(), userId, userEntity.getId()))
        //        .toList();
        //jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
        redisRepository.publishPostToFollowingUserList(postEntity, followersIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postIdList = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        //List<UserPostQueueEntity> userPostQueueEntities = postIdList.stream()
        //        .map(postId -> new UserPostQueueEntity(postId, userId, targetId)).toList();
        //jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
        redisRepository.publishPostListToFollowerUserList(postIdList, userId);
    }

    @Override
    @Transactional
    public void deleteUnfollowPost(Long userId, Long targetId) {
        //jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
        redisRepository.deleteDeleteFeed(userId, targetId);
    }
}
