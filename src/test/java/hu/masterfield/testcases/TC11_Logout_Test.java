package hu.masterfield.testcases;

import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
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
 * TC11 - Sikeres kijelentkezés vizsgálata
 */
public class TC11_Logout_Test extends BaseTest{

    protected static Logger logger = LogManager.getLogger(TC11_Logout_Test.class);
    GlobalTestData globalTestData = new GlobalTestData();
    RegistrationData registrationData = new RegistrationData();

    @Test
    @DisplayName("TC11_Logout_Test")
    @Description("TC11 - Sikeres kijelentkezés vizsgálata")
    @Tag("TC11")
    @Tag("Logout")

    public void TC11_Logout_Test(TestInfo testInfo) throws InterruptedException, IOException {
        Thread.sleep(5000);
        logger.info(testInfo + " Started.");

        //GDPR nyilatkozat elfogadása

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        assertTrue(gdprBannerPage.isLoaded());
        assertTrue(gdprBannerPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprBannerPage.acceptCookies();


        //Ellenõrzés, hogy a Login oldal minden eleme betöltõdött.

        LoginPage loginPage = new LoginPage(driver);

        logger.info("Login");
        assertTrue(loginPage.isLoaded());
        Screenshot.takesScreenshot(driver);

        // Érvényes, a rendszerben már regisztrált belépési adatok megadása

        String username = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);
        logger.info("Homepage will load.");

        HomePage homePage = loginPage.login(username, password);
        Screenshot.takesScreenshot(driver);

        //Ellenõrzés, hogy a HomePage oldal minden eleme betöltõdött.

        homePage.validateHomePage();
        Screenshot.takesScreenshot(driver);
        logger.info("Homepage loaded");

        // Kijelentkezés elindítása.

        logger.info("Logout will start");
        homePage.logut();

        // Ellenõrzés, hogy a Login oldal minden eleme megjelent és a felbukkanó, a sikeres kijelentkezést megerõsítõ elem ellenõrzése.

        assertTrue(loginPage.validateLogout());

        if (loginPage.validateLogout()){
            logger.info("TEST PASSED");
        } else {
            fail("LOGOUT FAILED");
        }


    }

}
