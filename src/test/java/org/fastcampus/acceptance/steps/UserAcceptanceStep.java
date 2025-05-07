package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.dto.UserCreateRequestDto;
import org.springframework.http.MediaType;

public class UserAcceptanceStep {
    public static ExtractableResponse<Response> createUser(UserCreateRequestDto dto){
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/user")
                .then()
                .extract();

    }

    public static ExtractableResponse<Response> followUser(FollowUserRequestDto dto){
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/relation/follow")
                .then()
                .extract();

    }
}
