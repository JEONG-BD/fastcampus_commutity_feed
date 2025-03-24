package org.fastcampus.user.repository.jpa;

import org.fastcampus.user.application.dto.GetUserListResponseDto;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON u.followerCount = u.id "
            + "WHERE ur.followingUserId = :userId")
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value = "SELECT new org.fastcampus.user.application.dto.GetUserListResponseDto(u.name, u.profileImage) "
            + "FROM UserRelationEntity ur "
            + "INNER JOIN UserEntity u ON u.followingCount = u.id "
            + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);

}
