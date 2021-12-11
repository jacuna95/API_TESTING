package specifications;

import Helpers.LoginHelper;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {
    //Bearer token
    public static RequestSpecification generateToken(){
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    String token = LoginHelper.getUserToken();

    requestSpecBuilder.addHeader("Authorization","Bearer "+token);

    return requestSpecBuilder.build();
    }

    public static RequestSpecification generateInvalidToken(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        String token = LoginHelper.getUserInvalidToken();

        requestSpecBuilder.addHeader("Authorization", "Bearer "+token);

        return requestSpecBuilder.build();
    }

    //Basic Authorization

    public static RequestSpecification generateBasicAuth(){
        BasicAuthScheme basicAuth = new BasicAuthScheme();
        basicAuth.setUserName("testuser");
        basicAuth.setPassword("testpass");

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setAuth(basicAuth);

        return requestSpecBuilder.build();

    }


}
