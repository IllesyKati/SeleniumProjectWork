package hu.masterfield.apitestcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerGetUserTest extends BaseAPITest {

    /*
            Ellenõrzi a userünk adatait.
     */

    String expectedResult = "{\n" +
            "  \"id\": 4,\n" +
            "  \"username\": \"admin@demo.io\",\n" +
            "  \"enabled\": true,\n" +
            "  \"accountNonExpired\": true,\n" +
            "  \"accountNonLocked\": true,\n" +
            "  \"credentialsNonExpired\": true,\n" +
            "  \"userProfile\": {\n" +
            "    \"id\": 5,\n" +
            "    \"firstName\": \"Darnell\",\n" +
            "    \"lastName\": \"Murray\",\n" +
            "    \"title\": \"Mr.\",\n" +
            "    \"gender\": \"M\",\n" +
            "    \"ssn\": \"534-04-7680\",\n" +
            "    \"dob\": \"08/07/1996\",\n" +
            "    \"dom\": \"02/03/2024\",\n" +
            "    \"emailAddress\": \"admin@demo.io\",\n" +
            "    \"homePhone\": \"573-124-9562\",\n" +
            "    \"mobilePhone\": \"721-679-0107\",\n" +
            "    \"workPhone\": \"699-529-9203\",\n" +
            "    \"address\": \"33083 Joshua Hill\",\n" +
            "    \"locality\": \"New Judyton\",\n" +
            "    \"region\": \"MA\",\n" +
            "    \"postalCode\": \"65174\",\n" +
            "    \"country\": \"US\"\n" +
            "  }\n" +
            "}";

    @Test
    public void testGetUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .when()
                .get("/api/v1/user");
        logger.trace(response);
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .body("id", is(4))
                .body("username", equalTo("admin@demo.io"))
                .body("userProfile.firstName", equalTo("Darnell"))
                .body("userProfile.lastName", equalTo("Murray"))
                .body("userProfile.emailAddress", equalTo("admin@demo.io"));


//    assertEquals(response.prettyPrint(), expectedResult);
    }
}