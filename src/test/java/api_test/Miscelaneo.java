package api_test;

import api_test.BaseTests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class Miscelaneo extends BaseTests {


    @Test
    public void Test_Home(){

        given()
                .get()
        .then()
                .body(containsString("Gin Boilerplate"))
                .statusCode(200);

    }
    @Test
    public void Test_Ping(){

        given()
                .get("/ping")
                .then()
                .body("response",equalTo("pong"))
                .statusCode(200);

    }
}
