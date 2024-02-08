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
 * TC11 - Sikeres kijelentkez�s vizsg�lata
 */
public class TC11_Logout_Test extends BaseTest{

    protected static Logger logger = LogManager.getLogger(TC11_Logout_Test.class);
    GlobalTestData globalTestData = new GlobalTestData();
    RegistrationData registrationData = new RegistrationData();

    @Test
    @DisplayName("TC11_Logout_Test")
    @Description("TC11 - Sikeres kijelentkez�s vizsg�lata")
    @Tag("TC11")
    @Tag("Logout")

    public void TC11_Logout_Test(TestInfo testInfo) throws InterruptedException, IOException {
        Thread.sleep(5000);
        logger.info(testInfo + " Started.");

        //GDPR nyilatkozat elfogad�sa

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        assertTrue(gdprBannerPage.isLoaded());
        assertTrue(gdprBannerPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprBannerPage.acceptCookies();


        //Ellen�rz�s, hogy a Login oldal minden eleme bet�lt�d�tt.

        LoginPage loginPage = new LoginPage(driver);

        logger.info("Login");
        assertTrue(loginPage.isLoaded());
        Screenshot.takesScreenshot(driver);

        // �rv�nyes, a rendszerben m�r regisztr�lt bel�p�si adatok megad�sa

        String username = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);
        logger.info("Homepage will load.");

        HomePage homePage = loginPage.login(username, password);
        Screenshot.takesScreenshot(driver);

        //Ellen�rz�s, hogy a HomePage oldal minden eleme bet�lt�d�tt.

        homePage.validateHomePage();
        Screenshot.takesScreenshot(driver);
        logger.info("Homepage loaded");

        // Kijelentkez�s elind�t�sa.

        logger.info("Logout will start");
        homePage.logut();

        // Ellen�rz�s, hogy a Login oldal minden eleme megjelent �s a felbukkan�, a sikeres kijelentkez�st meger�s�t� elem ellen�rz�se.

        assertTrue(loginPage.validateLogout());

        if (loginPage.validateLogout()){
            logger.info("TEST PASSED");
        } else {
            fail("LOGOUT FAILED");
        }


    }

}
