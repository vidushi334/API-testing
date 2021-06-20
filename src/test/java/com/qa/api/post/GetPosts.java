package com.qa.api.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;

public class GetPosts {
    String url = "https://jsonplaceholder.typicode.com";

    /*
     * This test is for fetching all posts
     * using GET method
     * */
    @Test
    public void test_getPosts() throws JsonProcessingException {
        RestAssured.baseURI = url;
        given()
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("$", hasItem(allOf(hasEntry("userId", 1))));
    }


    /*
     * This test is for fetching post by post id
     * using GET method
     * */
    @Test
    public void test_getPostsByPostId() {
        RestAssured.baseURI = url;
        given()
                .pathParam("postId", 1)
                .log()
                .all()
                .when()
                .get("/posts/{postId}")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("$", notNullValue())
                .body("$", hasEntry("id", 1));
    }


    /*
     * This test is for fetching post comments by post id
     * using GET method
     * */
    @Test
    public void test_getPostCommentsByPostId() {
        RestAssured.baseURI = url;
        given()
                .pathParam("postId", 1)
                .when()
                .get("/posts/{postId}/comments")
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", "application/json; charset=utf-8")
                .body("$", notNullValue())
                .body("$", hasItem(allOf(hasEntry("postId", 1))));

    }
}
