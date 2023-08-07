package GoogleMapAPI;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    // Lecture No 36: Example on Parametrization of APi Tests with multiple data sets
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-type", "application/json").
                // sending parameters to payload from test
                body(payload.addBook(isbn, aisle)).
                when().post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

    }
    // Understand TestNg data Provider for Parameterization
    @DataProvider(name= "BooksData")
    public Object[][] getDataSet(){
        // Create multi multidimensional array
        //Create object for multidimensional array // link of parameterization with multidimensional array
        return new Object[][] {{"sh", "11990"}, {"shyio", "7667"}, {"bahqwe", "76543"}};

    }
}