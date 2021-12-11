package Helpers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model_POJO.userRegister;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class LoginHelper {

    public static String getUserToken() {
        userRegister userLogin = new userRegister("Jose", "123ques", "jacuna@test.com");

        Response response = given()
                .body(userLogin)
                .post("/v1/user/login");

        JsonPath jsonPathEvaluator = response.jsonPath();
        String token = jsonPathEvaluator.get("token.access_token");

        System.out.println("TOKEN: "+ token);

        return token;

    }

    public static String getUserInvalidToken(){return "invalid";}
}
