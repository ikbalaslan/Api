package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {

    public static String generateToken(){
    String url =  "https://restful-booker.herokuapp.com/auth";
     Map<String,String> postBody = new HashMap<>();
       postBody.put("username","admin");
       postBody.put("password","password123");
     Response res = given().contentType(ContentType.JSON)
                .body(postBody).when().post(url);
           res.prettyPrint();
           return res.jsonPath().getString("token");

    }
}
//Java loves atomic structure as always been