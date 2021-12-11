package api_test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTests {

   @Parameters("baseUrl")
    @BeforeTest
    public void setup(@Optional("https://api-coffee-testing.herokuapp.com") String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

}
