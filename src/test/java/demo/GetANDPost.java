package demo;

import io.restassured.http.ContentType;
import io.restassured.internal.mapping.JsonbMapper;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetANDPost {
    @Test(priority = 1)
    public void Test_GET() {
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().statusCode(200).
                body("data[3].first_name", equalTo("Byron")).
                body("data.first_name", hasItems("Michael", "George"));
    }

    @Test(priority = 2)
    public void Test_POST(){

        Map<String, Object> map = new HashMap<String, Object>();

/*        map.put("name", "shahzad");
        map.put("job", "hussain");
        System.out.println(map);*/

        JSONObject request = new JSONObject();
        request.put("name", "shahzad");
        request.put("job", "hussain");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                    post("/users/2").
                    then().statusCode(201);

    }
}