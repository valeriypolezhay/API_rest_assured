package core;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CustomApiClient {

    public static Response doGetResonse(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .when().get(endpoint)
                .then().contentType(ContentType.JSON).extract().response();
    }
}
