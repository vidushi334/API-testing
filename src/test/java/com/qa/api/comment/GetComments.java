package com.qa.api.comment;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

public class GetComments {
    String url = "https://jsonplaceholder.typicode.com";

    /*
     * This test is for fetching all comments
     * using GET method
     * */
    @Test
    public void test_getComments() {
        RestAssured.baseURI = url;
        given().when().get("/comments")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .header("Server", equalTo("cloudflare"))
                .and()
                .body("$", notNullValue())
                .and()
                .body("$", hasSize(500))
                .body("$", hasItem(allOf(hasKey("id"))));
    }


    /*
     * This test is for fetching comments by post id
     * using GET method
     * */
    @Test
    public void test_getCommentsByPostId() {
        RestAssured.baseURI = url;
        given()
                .queryParam("postId", 1)
                .when()
                .get("/comments")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("$", notNullValue())
                .body("$", hasItem(allOf(hasEntry("postId", 1))));
    }
}
