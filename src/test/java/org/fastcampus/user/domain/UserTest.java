package org.fastcampus.user.domain;

import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    private final UserInfo userInfo = new UserInfo("회원A", "url");
    private User userA;
    private User userB;

    @BeforeEach
    void init(){
        userA = new User(1L, userInfo);
        userB = new User(2L, userInfo);
    }

    @Test
    @DisplayName("사용자를 생성한다")
    @Order(1)
    public void givenUserInfo_when_then() throws Exception{
        //given
        String name = "회훤A";
        String profileImageUrl = "url";
        UserInfo userInfo = new UserInfo(name, profileImageUrl);
        //when

        //then
        Assertions.assertDoesNotThrow(() -> new User(1L, userInfo));
    }

    @Test
    @DisplayName("사용자의 동등성 비교를 통과한다")
    @Order(4)
    public void givenTwoUser_whenEquals_thenNotEquals() throws Exception{
        //given
        String name = "회훤A";
        String profileImageUrl = "url";

        UserInfo userInfo = new UserInfo(name, profileImageUrl);

        //when
        User userA = new User(1L, userInfo);
        User userB = new User(1L, userInfo);

        //then
        Assertions.assertTrue(userA.equals(userB));
    }

    @Test
    @DisplayName("사용자의 동등성 비교를 실패한다")
    @Order(5)
    public void givenTwoUser_whenEquals_thenFalse() throws Exception{
        //given
        String name = "회훤A";
        String profileImageUrl = "url";
        UserInfo userInfo = new UserInfo(name, profileImageUrl);

        //when
        User userA = new User(1L, userInfo);
        User userB = new User(2L, userInfo);
        //then
        Assertions.assertFalse(userA.equals(userB));
    }

    @Test
    @DisplayName("사용자가 본인 팔로우에 실패 한다")
    @Order(6)
    public void givenFollow_whenUser1FollowUser1_thenThrowError() throws Exception{
        //given
        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> userA.follow(userA));
    }

    @Test
    @DisplayName("사용자가 다른 사용자를 팔로우 하면 사용자의 팔로잉이 1 증가한다.")
    @Order(9)
    public void givenFollowing_whenUser1FollowUser2_thenFollowingCounterIncrase() throws Exception{
        //given
        userA.follow(userB);
        //when
        int followingCounter = userA.getFollowingCounter();
        //then
        Assertions.assertEquals(followingCounter, 1);
    }

    @Test
    @DisplayName("사용자가 다른 사용자를 언팔로우 하면 사용자의 팔로잉이 1 감소한다.")
    @Order(11)
    public void givenUnFollowing_whenUser1FollowUser2_thenFollowingCounterDecrease() throws Exception{
        //given
        userA.follow(userB);
        userA.unfollow(userB);
        //when
        int followingCounter = userA.getFollowingCounter();
        //then
        Assertions.assertEquals(followingCounter, 0);
    }


    @Test
    @DisplayName("사용자가 다른 사용자를 팔로우 하면 팔로워의 팔로워가 1 증가한다.")
    @Order(10)
    public void givenFollowing_whenUser1FollowUser2_thenFollowerCounterIncrease() throws Exception{
        //given
        userA.follow(userB);
        //when
        int followerCounter = userB.getFollowerCounter();
        //then
        Assertions.assertEquals(followerCounter, 1);
    }

    @Test
    @DisplayName("사용자가 다른 사용자를 언팔로우 하면 팔로워의 팔로워가 1 감소한다.")
    @Order(12)
    public void givenUnFollowing_whenUser1FollowUser2_thenFollowerCounterDecrease() throws Exception{
        //given
        userA.follow(userB);
        userA.unfollow(userB);
        //when
        int followingCounter = userB.getFollowerCounter();
        //then
        Assertions.assertEquals(followingCounter, 0);
    }

    @Test
    @DisplayName("hashCode 비교에 실패한다")
    @Order(2)
    public void givenTwoUser_whenHashCode_thenReturnFalse() throws Exception{
        //given
        int hashCode1 = userA.hashCode();
        int hashCode2 = userB.hashCode();

        //when
        //then
        Assertions.assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("사용자가 다른 사용자를 팔로우 한다")
    @Order(8)
    public void givenFollow_whenUser1FollowUser2_thenThrowNothing() throws Exception{
        //given
        //when
        //then
        Assertions.assertDoesNotThrow(() -> userA.follow(userB));
    }
    @Test
    @DisplayName("hashCode 비교에 성공한다")
    @Order(3)
    public void givenTwoUser_whenHashCode_thenReturnTrue() throws Exception{
        //given
        int hashCode1 = userA.hashCode();
        int hashCode2 = userA.hashCode();

        //when
        //then
        Assertions.assertEquals(hashCode1, hashCode2);
    }
}

