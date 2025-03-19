package org.fastcampus.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.repository.TimeBaseEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="community_user_relation")
@Getter
@IdClass(UserRelationIdEntity.class)
public class UserRelationEntity extends TimeBaseEntity {

    @Id
    private Long followingUserId;

    @Id
    private Long followerUserId;



}
