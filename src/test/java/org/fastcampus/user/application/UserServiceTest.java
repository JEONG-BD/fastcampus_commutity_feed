package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.UserCreateRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


public class UserServiceTest {
    private final FakeUserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);


    @Test
    @DisplayName("사용자를 저장하고 조회한다")
    @Order(1)
    public void givenUserInfoDto_whenCreateUser_thenCanFindUser() throws Exception{
        //given
        UserCreateRequestDto dto = new UserCreateRequestDto("userA", "url");

        //when
        User saveUser = userService.createUser(dto);
        User foundUser = userService.getUser(saveUser.getId());
        UserInfo userInfo = foundUser.getUserInfo();

        //then
        Assertions.assertEquals(saveUser.getId(), foundUser.getId());
        Assertions.assertEquals(userInfo.getName(), "userA");
    }

}

