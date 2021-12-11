package Helpers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model_POJO.Comment;
import specifications.RequestSpecs;

import static io.restassured.RestAssured.given;

public class CommentHelper {

    public static String getIdComment(String postId) {
        Comment newComment = new Comment(DataHelper.generateNameComment(),DataHelper.generateComment());
        Response response = given()
                .spec(RequestSpecs.generateBasicAuth())
                .body(newComment)
                .post("/v1/comment/"+postId);

        JsonPath jsonPathEvaluator = response.jsonPath();
        String commentId = jsonPathEvaluator.get("id").toString();

        System.out.println("Id Comment: "+ commentId);


        return commentId;

    }

    public static String getIdPostInvalid(){return "invalid";}
}
