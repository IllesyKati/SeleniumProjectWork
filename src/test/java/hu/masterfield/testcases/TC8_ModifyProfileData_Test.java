package hu.masterfield.testcases;

import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.MyProfilePage;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TC8 - Profil adatok sikeres módosításának vizsgálata
 */
public class TC8_ModifyProfileData_Test extends BaseTest{
    protected static Logger logger = LogManager.getLogger(TC8_ModifyProfileData_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC8_ModifyProfileData_Test")
    @Description("TC8 - Profil adatok sikeres módosításának vizsgálata")
    @Tag("TC8")
    @Tag("Modify")
    @Tag("Profile")

    public void testModifyProfileData(TestInfo testInfo) throws InterruptedException {
        Thread.sleep(5000);

        logger.info(testInfo + " Started.");

        // Sütik elfogadása.

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        gdprBannerPage.acceptCookies();

        // Ellenõrzés, hogy a Login oldal minden eleme betöltõdött.

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        // Érvényes, a rendszerben már regisztrált belépési adatok megadása

        String userName = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("Starting login.");

        HomePage homePage = loginPage.login(userName, password);

        // Ellenõrzés, hogy a HomePage oldal minden eleme betöltõdött.

        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();

        // Navigálás a profil adatok megjelenítése oldalra.

        logger.info("Going to the profile page.");

        homePage.gotoMyProfilePage();

        // Ellenõrzés, hogy a profil adatait tartalmazó oldal minden eleme betöltõdött.

        MyProfilePage myProfilePage = new MyProfilePage(driver);
        assertTrue(myProfilePage.isLoaded());

        // Ellenõrzés, hogy a módosítani kívánt mezõk jelenleg az érvényes, tehát a regisztrációkor megadott adatokat tartalmazzák

    //    myProfilePage.validateProfileData();

        // A **Firstname** és **Lastname** mezõk módosítása a **globalTestData.properties** fájlban leírt adatok alapján.

        myProfilePage.modifyProfileData();

        // Ellenõrzés, hogy a módosított mezõk felvették-e az elvárt értéket.

     // myProfilePage.validateProfileData();

        // Ellenõrzés, hogy megjelenik-e a sikeres profil módosítását visszaigazoló figyelmeztetés.

        assertTrue(myProfilePage.successMessageIsLoaded());



    }



}
