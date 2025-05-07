package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import org.fastcampus.post.application.dto.PostCreateRequestDto;
import org.fastcampus.post.ui.dto.GetPostResponseDto;
import org.springframework.http.MediaType;

import java.util.List;

public class FeedAcceptanceSteps {

    public static Long requestCreatePost(PostCreateRequestDto dto){
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("value", Long.class);
    }

    public static List<GetPostResponseDto> requestFeed(Long userId){
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/feed/{userId}", userId)
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("value", GetPostResponseDto.class);
    }
}
