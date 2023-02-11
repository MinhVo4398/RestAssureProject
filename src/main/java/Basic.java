import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basic {
    public static void main(String[] args) {

        // validate if add place API is working as expected
        // given - all input details
        // when - submit the API - resource, http method
        // then - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.AddPlace()). when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("server","Apache/2.4.41 (Ubuntu)");

        // Add Place -> Update Place with New Address -> Get Place to validate if New Address is present in the response


    }

}
