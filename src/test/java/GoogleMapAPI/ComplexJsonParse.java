package GoogleMapAPI;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {

        JsonPath js = new JsonPath(payload.coursePrice());

        // Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        // Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        // Print Title of the first course
        String titleFirstcousr = js.get("courses[0].title");
        System.out.println(titleFirstcousr);

        // Print All course titles and their respective Prices
        for(int i=0; i<count; i++)
        {
            String courseTitle = js.get("courses["+i+"].title");
            System.out.println(js.get("courses["+i+"].price").toString());
            System.out.println(courseTitle);
        }
    }
}
