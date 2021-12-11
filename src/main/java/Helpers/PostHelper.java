package Helpers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model_POJO.Post;


import static io.restassured.RestAssured.given;

public class PostHelper {

    public static String getIdPost() {
        Post newPost = new Post(DataHelper.generateTitlePost(),DataHelper.generateContentPost());

        Response response = given()
                .header("Authorization", String.format("Bearer %s", LoginHelper.getUserToken()))
                .body(newPost)
                .post("/v1/post");

        JsonPath jsonPathEvaluator = response.jsonPath();
        String PostId = jsonPathEvaluator.get("id").toString();

        System.out.println("Id Post: "+ PostId);


        return PostId;

    }

    public static String getIdPostInvalid(){return "invalid";}
}
