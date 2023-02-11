import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basic {
    public static void main(String[] args) {

        // validate if add place API is working as expected
        // given - all input details
        // when - submit the API - resource, http method
        // then - validate the response
        // Add Place -> Update Place with New Address -> Get Place to validate if New Address is present in the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(payload.AddPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.41 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); // For parsing Json
        String placeId = js.getString("place_id");
        System.out.println(placeId);


        // Update place
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"70 winter walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


        // Get Place


    }

}
