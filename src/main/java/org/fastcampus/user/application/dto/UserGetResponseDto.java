package org.fastcampus.user.application.dto;

import org.fastcampus.user.domain.User;

public record UserGetResponseDto (Long id, String name, String profileImage, Integer followingCount, Integer followerCount){

    public UserGetResponseDto(User user){
        this(user.getId(), user.getUserName(), user.getProfileImageUrl(), user.getFollowingCounter(), user.getFollowerCounter());
    }
}
