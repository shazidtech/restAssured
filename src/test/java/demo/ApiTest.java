package demo;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class ApiTest {
    @Test (priority = 2)
    public void Test_1(){
        Response res= get("https://reqres.in/api/users?page=2");
        System.out.println(res.statusCode());
        System.out.println(res.getBody().asString());

        int statusCodes = res.statusCode();
        Assert.assertEquals(statusCodes,200);
    }

    @Test(priority = 1)
    public void Test_2(){
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().statusCode(200).
                body("data[1].id", equalTo(8)).log().all();
    }

    @Test(priority = 3)
    public void Test_3(){
        baseURI = "https://reqres.in/api";
        given().
                get("/users/2").
                then().statusCode(200).
                body("data.email", is("janet.weaver@reqres.in"));

    }
}



