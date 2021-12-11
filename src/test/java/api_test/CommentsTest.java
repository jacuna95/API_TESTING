package api_test;

import Helpers.CommentHelper;
import Helpers.DataHelper;
import Helpers.PostHelper;
import api_test.BaseTests;
import io.restassured.http.ContentType;
import model_POJO.Comment;
import model_POJO.WrongComment;
import org.testng.annotations.Test;
import specifications.RequestSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

public class CommentsTest extends BaseTests {


    @Test
    public void Test_Create_Comments() {

        Comment newComment = new Comment(DataHelper.generateNameComment(), DataHelper.generateComment());

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .post("/v1/comment/" + PostHelper.getIdPost())
        .then()
                .statusCode(200)
                .body("message", equalTo("Comment created"));

    }
    //-----*-----Se envía información incompleta al comment-----*-----

    @Test
    public void Test_Create_Comments_Negative() {

        WrongComment newComment = new WrongComment(DataHelper.generateComment());

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .post("/v1/comment/" + PostHelper.getIdPost())
        .then()
                .statusCode(406)
                .body("message", equalTo("Invalid form"));
    }

    @Test
    public void Test_Comment_All() {

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .get("/v1/comments/" + PostHelper.getIdPost())
        .then()
                .statusCode(200)
                .body(containsString("results"))
                .contentType(ContentType.JSON);

    }

    //-----*-----Se utiliza un URI erroneo-----*-----
    @Test
    public void Test_Comment_All_Negative() {

        given()
                .spec(RequestSpecs.generateBasicAuth())
                .get("/v1/comment/" + PostHelper.getIdPost())
        .then()
                .statusCode(404)
                .body(containsString("Opss!! 404 again?"));


    }

    @Test
    public void Test_Comment_One() {
        String postId = PostHelper.getIdPost();
        given()
                .spec(RequestSpecs.generateBasicAuth())
                .get("/v1/comment/" + postId + "/" + CommentHelper.getIdComment(postId))
        .then()
                .statusCode(200)
                .body(containsString("id"))
                .contentType(ContentType.JSON);
    }

    //-----*-----Se utiliza un IdPost erroneo-----*-----
    @Test
    public void Test_Comment_One_Negative() {
        String postId = PostHelper.getIdPost();
        given()
                .spec(RequestSpecs.generateBasicAuth())
                .get("/v1/comment/505078" + "/" + CommentHelper.getIdComment(postId))
        .then()
                .statusCode(404)
                .body("error", equalTo("sql: no rows in result set"))
                .contentType(ContentType.JSON);
    }

    @Test
    public void Test_Comment_Update() {
        String postId = PostHelper.getIdPost();
        Comment newComment = new Comment(DataHelper.generateNameComment(), DataHelper.generateComment());
        given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .put("/v1/comment/" + postId + "/" + CommentHelper.getIdComment(postId))
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Comment updated"));
    }

    //-----*-----Se valida la seguridad utilizando Bearer Token cuando requiere autenticación Básica -----*-----
    @Test
    public void Test_Comment_Update_Negative() {
        String postId = PostHelper.getIdPost();
        Comment newComment = new Comment(DataHelper.generateNameComment(), DataHelper.generateComment());
        given()
                .spec(RequestSpecs.generateToken())
                .body(newComment)
                .put("/v1/comment/" + postId + "/" + CommentHelper.getIdComment(postId))
        .then()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Please login first"));
    }

    @Test
    public void Test_Comment_Delete() {
        String postId = PostHelper.getIdPost();
        given()
                .spec(RequestSpecs.generateBasicAuth())
                .delete("/v1/comment/" + postId + "/" + CommentHelper.getIdComment(postId))
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Comment deleted"));
    }


    //-----*-----Se utiliza un IdPost/IdComment erroneo-----*-----
    @Test
    public void Test_Comment_Delete_Negative() {
        given()
                .spec(RequestSpecs.generateBasicAuth())
                .delete("/v1/comment/1515/85858")
        .then()
                .statusCode(406)
                .contentType(ContentType.JSON)
                .body("message", equalTo("Comment could not be deleted"));
    }
}