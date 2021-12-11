package api_test;

import Helpers.DataHelper;
import Helpers.PostHelper;
import api_test.BaseTests;
import io.restassured.http.ContentType;
import model_POJO.Post;
import model_POJO.WrongPost;
import org.testng.annotations.Test;
import specifications.RequestSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostTest extends BaseTests {


    @Test
    public void Test_Create_Post() {

        Post newPost = new Post(DataHelper.generateTitlePost(), DataHelper.generateContentPost());

        given()
                .spec(RequestSpecs.generateToken())
                .body(newPost)
                .post("/v1/post")
        .then()
                .statusCode(200)
                .body("message", equalTo("Post created"));

    }

    //-----*-----Se valida la seguridad utilizando autenticación Básica cuando requiere Bearer Token-----*-----
    @Test
    public void Test_Create_Post_Negative() {

        Post newPost = new Post(DataHelper.generateTitlePost(), DataHelper.generateContentPost());
        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newPost)
                .post("/v1/post")
        .then()
                .statusCode(401)
                .body("message", equalTo("Please login first"));

    }

    @Test
    public void Test_Post_All() {
        given()
                .spec(RequestSpecs.generateToken())
                .get("/v1/posts")
        .then()
                .statusCode(200)
                .body(containsString("results"))
                .contentType(ContentType.JSON);

    }

    //-----*-----Se utiliza un URI erroneo-----*-----
    @Test
    public void Test_Post_All_Negative() {

        given()
                .spec(RequestSpecs.generateToken())
                .get("/v1/post")
        .then()
                .statusCode(404)
                .body(containsString("Opss!! 404 again?"));

    }

    @Test
    public void Test_Post_One() {

        given()
                .spec(RequestSpecs.generateToken())
                .get("/v1/post/" + PostHelper.getIdPost())
        .then()
                .statusCode(200)
                .body(containsString("id"))
                .contentType(ContentType.JSON);
    }

    //-----*-----Se utiliza un IdPost erroneo-----*-----
    @Test
    public void Test_Post_One_Negative() {

        given()
                .spec(RequestSpecs.generateToken())
                .get("/v1/post/5050")
        .then()
                .statusCode(404)
                .body("error", equalTo("sql: no rows in result set"));

    }

    @Test
    public void Test_Post_Update() {
        Post updatePost = new Post(DataHelper.generateTitlePost(), DataHelper.generateContentPost());
        given()
                .spec(RequestSpecs.generateToken())
                .body(updatePost)
                .put("/v1/post/" + PostHelper.getIdPost())
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Post updated"));
    }

    //-----*-----Se envía información incompleta al post-----*-----
    @Test
    public void Test_Post_Update_Negative() {
        WrongPost updatePost = new WrongPost(DataHelper.generateTitlePost());
        given()
                .spec(RequestSpecs.generateToken())
                .body(updatePost)
                .put("/v1/post/" + PostHelper.getIdPost())
        .then()
                .statusCode(406)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Invalid form"));
    }

    @Test
    public void Test_Post_Delete() {
        given()
                .spec(RequestSpecs.generateToken())
                .delete("/v1/post/" + PostHelper.getIdPost())
        .then()
                .statusCode(200)
                .body("message", equalTo("Post deleted"));
    }

    //-----*-----Se utiliza autenticación Básica cuando requiere Bearer Token-----*-----
    @Test
    public void Test_Post_Delete_Negative() {
        given()
                .spec(RequestSpecs.generateBasicAuth())
                .delete("/v1/post/" + PostHelper.getIdPost())
        .then()
                .statusCode(401)
                .body("message", equalTo("Please login first"));
    }

}