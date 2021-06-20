package com.qa.api.user;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

public class GetUsers {
    @Test
    public void testRequest() {

        RestAssured.baseURI= "https://jsonplaceholder.typicode.com";
        given().
                when().
                get("/users").
                then().
                assertThat()
                .statusCode(200)
                .and()
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .and()
                .header("Connection", equalTo("keep-alive"))
                .and()
                .body("$", notNullValue())
                .and()
                .body("$", hasSize(10))
                .and()
                .body("$", hasItem(allOf(hasKey("id"))));
    }

}
