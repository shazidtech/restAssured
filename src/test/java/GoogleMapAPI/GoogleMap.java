package GoogleMapAPI;
import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoogleMap {
    public static void main(String[] args) throws IOException {
        //add place  -> update place -> get place if new place is available
        // Lecture no 38 - How to handle with static json payload
        // content of the file to string  -> conetent of file can convert into Byte  -> Byte data to String
        //
        RestAssured.baseURI="https://rahulshettyacademy.com/";

        String response = given().log().all().queryParam("key", "qaclick123").
                    header("Content-Type", "application/json").
                    body(new String(Files.readAllBytes(Paths.get("C:\\Users\\ENT18010731\\Downloads\\JSON\\addPlace.json")))).
                when().post("maps/api/place/add/json").
                then().assertThat().statusCode(200).
                    body("scope",equalTo("APP")).
                    header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String placeID= js.getString("place_id");
        System.out.println("Place id for the new place is : " + placeID);
        // update place
        String newAddress = "House no 150, Lahore pakistan";
        given().
                log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
                body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").
                when().put("/maps/api/place/update/json").
                then().
                    log().all().assertThat().statusCode(200).
                    body("msg", equalTo("Address successfully updated"));

        //Get place Verify the new address

        String getPlaceResponse = given().
                log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).
                when().get("/maps/api/place/get/json").
                then().
                log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);

    }
}
