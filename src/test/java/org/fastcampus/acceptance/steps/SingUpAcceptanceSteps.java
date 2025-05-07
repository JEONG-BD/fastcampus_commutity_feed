package org.fastcampus.acceptance.steps;

import io.restassured.RestAssured;
import org.fastcampus.auth.application.dto.SendEmailRequestDto;
import org.springframework.http.MediaType;

public class SingUpAcceptanceSteps {

    public  static Integer requestSendMail(SendEmailRequestDto dto){
        return RestAssured
                .given()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/signup/send-verification-email")
                .then()
                .extract()
                .jsonPath().get("code");
    }


}
