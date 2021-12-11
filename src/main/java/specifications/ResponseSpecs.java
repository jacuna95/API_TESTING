package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecs {

    // Access-Control-Allow-Origin: http://localhost

    public static ResponseSpecification defaultSpec(){

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectHeader("Access-Control-Allow-Origin","http://localhost");

        return responseSpecBuilder.build();
    }



}
