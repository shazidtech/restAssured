import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {
    @Test (priority = 2)
    void test1(){
        Response res= get("https://reqres.in/api/users?page=2");

        System.out.println("Status code: " + res.getStatusCode());
        System.out.println("Response:" + "\n" + res.asString());
        System.out.println("Body: " +res.getBody().asString());
        System.out.println("Header: "+ res.getHeader("content-type"));
        System.out.println("Time taken " + res.getTime());

        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, 200);

   }
    @Test(priority = 1)
    void test2(){
        given().
                get("https://reqres.in/api/users?page=2").
                then().statusCode(200);
        System.out.println("code commit");
    }

}
