package hu.masterfield.testcases;

import hu.masterfield.pages.BasePage;
import hu.masterfield.pages.GDPRBannerPage;
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

/**
 * TC1 - GDPR nyilatkozat sikeres elfogad�sa
 */

public class TC1_GDPR_Test extends BaseTest{
    protected static Logger logger = LogManager.getLogger(TC1_GDPR_Test.class);

    @Test
    @DisplayName("TC1_GDPR_Test")
    @Description("TC1 - GDPR nyilatkozat sikeres elfogad�sa")
    @Tag("TC1")
    @Tag("GDPR")
    public void test_TC1_GDPR(TestInfo testInfo) throws IOException, InterruptedException {
//        Thread.sleep(5000);
        logger.info(testInfo + " started");

        GDPRBannerPage gdprPage = new GDPRBannerPage(driver);

        /* a s�ti elfogad�s�ra szolg�l� ablak megjelen�s�nek ellen�rz�se */
        assertTrue(gdprPage.isCookieMessageVisible());
        Screenshot.takesScreenshot(driver);
        gdprPage.acceptCookies();
        Screenshot.takesScreenshot(driver);
        logger.info("Login page will be opened...");
        logger.info("Login");
//        Thread.sleep(5000);
    }
}