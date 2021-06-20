package com.qa.api.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostPosts {

    /*
     * This test is for creating a post
     * using POST method
     * */
    @Test
    public void test_postPosts() throws JsonProcessingException {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("title", "test title");
        jsonMap.put("body", "bar");
        jsonMap.put("userId", 59);

        String requestBody = new ObjectMapper().writeValueAsString(jsonMap);

        ValidatableResponse response = given()
                .header("Content-Type", "application/json")    // Setting request header
                .header("charset", "UTF-8")                    // Setting request header
                .and()
                .body(requestBody)                                  // Setting request body
                .when()
                .post("/posts")
                .then();

        // Verify the status code of response received
        response.assertThat().statusCode(201);

        // Verifies the content type of response received
        response.contentType(ContentType.JSON);

        // verify the attributes of response json
        response.assertThat().body("userId", equalTo(59));
        response.assertThat().body("title", equalTo("test title"));
        response.assertThat().body("id", equalTo(101));
    }
}
