package BasicRestAssured;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class PutPacthDelete {
    @Test(priority = 2)
    public void Test_PUT(){


        JSONObject request = new JSONObject();
        request.put("name", "ahmad1");
        request.put("job", "hussain1");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/2").
                then().statusCode(200).log().all();

    }
    @Test(priority = 1)
    public void Test_PATCH(){


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
                patch("/users/2").
                then().statusCode(200).log().all();

    }

    @Test(priority = 3)
    public void Test_Delete(){

        baseURI = "https://reqres.in/api";

        when().
                delete("/users/2").
                then().statusCode(204).log().all();
    }
    @Test(priority = 4)
    public void Tes_get(){
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().statusCode(200).
                body("data[1].id", equalTo(8)).log().all();
    }

}
