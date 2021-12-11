package api_test;

import Helpers.LoginHelper;
import api_test.BaseTests;
import model_POJO.userRegister;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Login extends BaseTests {


    @Test
    public void Test_Login_User() {

        userRegister userLogin = new userRegister("Jose", "123ques", "jacuna@test.com");

        given()
                .body(userLogin)
                .post("/v1/user/login")
                .then()
                .body("message", equalTo("User signed in"))
                .statusCode(200);

    }

    @Test
    public void Test_Logout_User() {

        given()
                .header("Authorization", String.format("Bearer %s", LoginHelper.getUserToken()))
                .get("/v1/user/logout")
                .then()
                .body("message", equalTo("Successfully logged out"))
                .statusCode(200);

    }

}
