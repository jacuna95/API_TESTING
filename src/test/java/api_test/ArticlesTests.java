package api_test;

import Helpers.DataHelper;
import Helpers.LoginHelper;
import api_test.BaseTests;
import model_POJO.Article;
import org.testng.annotations.Test;
import specifications.RequestSpecs;
import specifications.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class ArticlesTests extends BaseTests {

    String resourcePath = "/v1/article";

    @Test
    public void Test_Create_Article() {

       Article newArticle = new Article(DataHelper.generateRandomTitle(),DataHelper.generateRandomContent());

        given()
                .header("Authorization",String.format("Bearer %s", LoginHelper.getUserToken()))
                .body(newArticle)
                .post(resourcePath)
//Validaciones
                .then()
                .body("message", equalTo("Article created"))
                .statusCode(200);

    }

    @Test
    public void Test_Create_Article_RequestSpecification() {

        Article newArticle = new Article(DataHelper.generateRandomTitle(),DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateToken())
                .body(newArticle)
        .when()
                .post(resourcePath)
        .then()
                .spec(ResponseSpecs.defaultSpec())
                .body("message", equalTo("Article created"))
                .statusCode(200);

    }

}