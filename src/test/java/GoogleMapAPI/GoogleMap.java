package GoogleMapAPI;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoogleMap {
    public static void main(String[] args) {
        //add place  -> update place -> get place if new place is available

        RestAssured.baseURI="https://rahulshettyacademy.com/";

        String response = given().log().all().queryParam("key", "qaclick123").
                    header("Content-Type", "application/json").body(payload.AddPlace()).
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

        JsonPath  js1 = new JsonPath(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);

    }
}
