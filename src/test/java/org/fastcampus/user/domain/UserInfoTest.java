package org.fastcampus.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserInfoTest {

    @Test
    @DisplayName("사용자 정보를 생성한다")
    public void givenNamedAndProfileImage_whenCreated_thenThrowNothing() throws Exception{
        //given
        String name = "abcd";
        String profileImageUrl = "";

        //when
        //then
        assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
    }
    @Test
    @DisplayName("빈 이름으로 사용자 정보를 생성한다")
    public void givenBlankNamedAndProfileImage_whenCreated_thenThrowError() throws Exception{
        //given
        String name = "";
        String profileImageUrl = "";

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));
    }
}
