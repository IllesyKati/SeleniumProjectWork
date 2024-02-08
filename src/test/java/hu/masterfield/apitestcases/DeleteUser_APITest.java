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
     * Ebben a tesztben lek�rdez�nk egy usert id alapj�n, majd kit�r�lj�k �s �jra lek�rdezz�k hogy ellen�rizz�k hogy t�nyleg kit�rl�d�tt-e.
     */

    protected static Logger logger = LogManager.getLogger(DeleteUser_APITest.class);

    int userId = 1296;
    String expectedError = "Not Found";


    @Test
    public void testDeleteUser() {

        /* GET /api/v1/user/{id} - GET met�dussal lek�rdezek egy usert id alapj�n.         */

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

        /* DELETE /api/v1/user/{id} - DELETE met�dussal kit�rl�m a usert id alapj�n. */

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


        /* GET /api/v1/user/{id} - GET met�dussal �jra lek�rdezem ugyanazt a usert akit kit�r�ltem.         */

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

        //Ellen�rz�s hogy nem tal�lhat� m�r ez a user.

        assertEquals(expectedError, response3.path("error"));

        logger.info("Ending GET /api/v1/user/{id}");


    }
}
