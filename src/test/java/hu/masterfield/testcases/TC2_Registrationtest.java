package hu.masterfield.testcases;


import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.RegistrationFirstPage;
import hu.masterfield.pages.RegistrationSecondPage;
import hu.masterfield.utils.Screenshot;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 * TC2 - Sikeres regisztr�ci� �rv�nyes adatok megad�s�val.
 *
 */

public class TC2_Registrationtest extends BaseTest{

    private static Logger logger = LogManager.getLogger(TC2_Registrationtest.class);

    @Test
    @DisplayName("Tc2_Registration")
    @Description("TC2 - Sikeres regisztr�ci� tesztel�se �rv�nyes adatokkal")
    @Tag("TC2")
    @Tag("Regisztr�ci�")
    public void TC2_Registrationtest(TestInfo testInfo) throws InterruptedException, IOException {
        Thread.sleep(10000);
        logger.info(testInfo + " started");

        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);

        /* a s�ti elfogad�s�ra szolg�l� ablak megjelen�s�nek ellen�rz�se */
        assertTrue(gdprPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprPage.acceptCookies();
        Screenshot.takesScreenshot(driver);
        logger.info("Login page will be opened...");

        LoginPage loginPageOne = new LoginPage(driver);
        assertTrue(loginPageOne.isLoaded());
        loginPageOne.registrationStart();


        RegistrationData registrationData = new RegistrationData();
        logger.info(registrationData);

        // Regisztr�ci�s �rlap els� oldal�nak kit�lt�se
        logger.info("RegistrationFirstPage bet�lt�se");
        RegistrationFirstPage registrationFirstPage = new RegistrationFirstPage(driver);
        assertTrue(registrationFirstPage.isLoaded());
        Screenshot.takesScreenshot(driver);
        RegistrationSecondPage registrationSecondPage = registrationFirstPage
                .registrationFirstPage();

        // Regisztr�ci�s �rlap m�sodik oldal�nak kit�lt�se
        logger.info("RegistrationSecondPage bet�lt�se");
        assertTrue(registrationSecondPage.isLoaded());
        Screenshot.takesScreenshot(driver);
        LoginPage loginPageTwo = registrationSecondPage.registrationSecondPage();

        // Ellen�rzi, hogy a regisztr�ci� sikeres volt-e, err�l megjelent-e a sz�veg
        logger.info("Regisztr�ci� sikeress�g�nek ellen�rz�se");
        assertTrue(loginPageTwo.registrationIsSuccessful());
        Screenshot.takesScreenshot(driver);

        //    /\
        //    ||
        //    \/

        if (loginPageTwo.registrationIsSuccessful()) {
            logger.info("TEST PASSED");
            // TEST PASSED
        } else {
            fail("Registration failed");
        }




    }


}