package com.qa.api.post;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePosts {

    /*
     * This test is for deleteing a post
     * using DELETE method
     * */
    @Test
    public void test_deletePosts() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .pathParam("postId", 1)
                .when()
                .delete("/posts/{postId}")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
