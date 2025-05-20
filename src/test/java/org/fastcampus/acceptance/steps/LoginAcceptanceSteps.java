package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import org.fastcampus.auth.application.dto.LoginRequestDto;
import org.fastcampus.auth.application.dto.UserAccessTokenResponseDto;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {

    //public static String requestLoginGetToken(LoginRequestDto dto){
    //    UserAccessTokenResponseDto res = getLoginResponse(dto);
    //    return res.accessToken();
    //}

//    public static Integer requestLoginGetCode

    private static UserAccessTokenResponseDto getLoginResponse(LoginRequestDto dto){
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("login")
                .then()
                .extract()
                .jsonPath()
                .getObject("value", UserAccessTokenResponseDto.class);

    }

    public static String requestLoginGetToken(LoginRequestDto dto) {
        UserAccessTokenResponseDto res = RestAssured.given()
                .body(dto)
                .contentType("application/json")
                .when()
                .post("/login")
                .then()
                .extract()
                .jsonPath()
                .getObject("value", UserAccessTokenResponseDto.class);
        return res.accessToken();
    }

    public static Integer requestLoginGetCode(LoginRequestDto dto) {
        return RestAssured.given()
                .body(dto)
                .contentType("application/json")
                .when()
                .post("/login")
                .then()
                .extract()
                .jsonPath()
                .getObject("code", Integer.class);
    }


}
