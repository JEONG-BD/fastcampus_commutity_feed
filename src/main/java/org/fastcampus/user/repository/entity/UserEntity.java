package org.fastcampus.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.TimeBaseEntity;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate regDate;


    public UserEntity(User user){
        this.id = user.getId();
        this.name = user.getUserName();
        this.profileImage = user.getProfileImageUrl();
        this.followerCount = user.getFollowerCounter();
        this.followingCount = user.getFollowingCounter();

    }

    public User toUser(){
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, profileImage))
                .followerCounter(new PositiveIntegerCounter(followerCount))
                .followingCounter(new PositiveIntegerCounter(followingCount))
                .build();
    }
}
