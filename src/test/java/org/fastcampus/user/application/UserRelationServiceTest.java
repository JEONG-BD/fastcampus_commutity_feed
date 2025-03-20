package org.fastcampus.user.application;

import org.fastcampus.fake.FakeObjectFactory;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.dto.UserCreateRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRelationServiceTest {
    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

    private User userA;
    private User userB;
    private FollowUserRequestDto requestDto;
    @BeforeEach
    void init(){
        UserCreateRequestDto dto1 = new UserCreateRequestDto("testA", "");
        UserCreateRequestDto dto2 = new UserCreateRequestDto("testB", "");
        this.userA = userService.createUser(dto1);
        this.userB = userService.createUser(dto2);
        this.requestDto = new FollowUserRequestDto(userA.getId(), userB.getId());

    }
    @Test
    @DisplayName("사용자A가 사용자 B를 Following에 성공한다.")
    public void givenCreateTwoUser_whenFollowing_thenUserFollowSaved () throws Exception{
        //given
        //when
        //then
        Assertions.assertDoesNotThrow(() -> userRelationService.follow(requestDto));
        Assertions.assertTrue(userA.getFollowingCounter()==1);
        Assertions.assertTrue(userB.getFollowerCounter()==1);
        Assertions.assertFalse(userA.getFollowingCounter() == 0);
        Assertions.assertFalse(userB.getFollowerCounter() == 0);
    }
    @Test
    @DisplayName("사용자A가 사용자 B를 Following에 실패한다.")
    public void givenCreateTwoUserFollowed_whenFollowing_thenThrowError () throws Exception{
        //given
        //when
        //then
        userRelationService.follow(requestDto);

        Assertions.assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    @DisplayName("사용자A가 본인의 Following에 실패한다.")
    public void givenCreateOneUserFollowed_whenFollowing_thenThrowError () throws Exception{
        //given
        //when
        //then
        FollowUserRequestDto requestDto = new FollowUserRequestDto(userA.getId(), userA.getId());

        Assertions.assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    @DisplayName("사용자A가 사용자 B를 UnFollowing에 성공한다.")
    public void givenCreateTwoUser_whenUnFollowing_thenUserFollowRemoved () throws Exception{
        //given
        //when
        //then
        userRelationService.follow(requestDto);
        userRelationService.unfollow(requestDto);
        Assertions.assertTrue(userA.getFollowingCounter() == 0);
        Assertions.assertTrue(userB.getFollowerCounter() == 0);
        Assertions.assertFalse(userA.getFollowingCounter() == 1);
        Assertions.assertFalse(userB.getFollowerCounter() == 1);
    }
}
