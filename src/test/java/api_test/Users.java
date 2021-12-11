package api_test;

import Helpers.DataHelper;
import api_test.BaseTests;
import model_POJO.userRegister;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Users extends BaseTests {


    @Test
    public void Test_User_Registration(){

        userRegister newUser = new userRegister(DataHelper.generateRandomName(),
                "pass",
                DataHelper.generateRandomEmail());

        given()
                .body(newUser)
                .post("/v1/user/register")
         .then()
                .body("message", equalTo("Successfully registered"))
                .statusCode(200);

    }

    @Test
    public void Test_User_Registration_Existing_User(){

        userRegister userExisting = new userRegister("Jose","123ques","jacuna@test.com");

        given()
                .body(userExisting)
                .post("/v1/user/register")
                .then()
                .body("message", equalTo("User already exists"))
                .statusCode(406);

    }
}
