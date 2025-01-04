package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class FirstTest {

    @Test    // Example test to validate API setup and basic request structure
    public void testGetRequest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = RestAssured
                .given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .extract().response();

        System.out.println("Response: " + response.prettyPrint());
    }
}


