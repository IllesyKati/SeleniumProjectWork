package hu.masterfield.apitestcases;

import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.utils.GlobalTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUser_APITest extends BaseAPITest {

    /**
     * Ebben a tesztben lekérdezünk egy usert id alapján, majd kitöröljük és újra lekérdezzük hogy ellenõrizzük hogy tényleg kitörlõdött-e.
     */

    protected static Logger logger = LogManager.getLogger(DeleteUser_APITest.class);

    int userId = 1296;
    String expectedError = "Not Found";


    @Test
    public void testDeleteUser() {

        /* GET /api/v1/user/{id} - GET metódussal lekérdezek egy usert id alapján.         */

        logger.info("Starting GET /api/v1/user/{id}");

        Response response1 = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("id", userId)
                .when()
                .get("/api/v1/user/{id}");
        response1.prettyPrint();
        response1.then()
                .statusCode(200)
                .body("id", equalTo(userId));

        logger.info("Ending GET /api/v1/user/{id}");

        /* DELETE /api/v1/user/{id} - DELETE metódussal kitörlöm a usert id alapján. */

        logger.info("Starting DELETE /api/v1/user/{id}");

        Response response2 = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("id", userId)
                .when()
                .delete("/api/v1/user/{id}");
        response2.prettyPrint();
        response2.then()
                .statusCode(204);

        logger.info("Ending DELETE /api/v1/user/{id}");


        /* GET /api/v1/user/{id} - GET metódussal újra lekérdezem ugyanazt a usert akit kitöröltem.         */

        logger.info("Starting GET /api/v1/user/{id}");

        Response response3 = given()
                .contentType(ContentType.JSON)
                .header(AUTH_HEADER, "Bearer " + authToken)
                .pathParam("id", userId)
                .when()
                .get("/api/v1/user/{id}");
        response3.prettyPrint();
        response3.then()
                .statusCode(404);

        //Ellenõrzés hogy nem található már ez a user.

        assertEquals(expectedError, response3.path("error"));

        logger.info("Ending GET /api/v1/user/{id}");


    }
}
