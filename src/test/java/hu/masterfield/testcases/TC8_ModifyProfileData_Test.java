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
 * TC8 - Profil adatok sikeres m�dos�t�s�nak vizsg�lata
 */
public class TC8_ModifyProfileData_Test extends BaseTest{
    protected static Logger logger = LogManager.getLogger(TC8_ModifyProfileData_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    @Test
    @DisplayName("TC8_ModifyProfileData_Test")
    @Description("TC8 - Profil adatok sikeres m�dos�t�s�nak vizsg�lata")
    @Tag("TC8")
    @Tag("Modify")
    @Tag("Profile")

    public void testModifyProfileData(TestInfo testInfo) throws InterruptedException {
        Thread.sleep(5000);

        logger.info(testInfo + " Started.");

        // S�tik elfogad�sa.

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        gdprBannerPage.acceptCookies();

        // Ellen�rz�s, hogy a Login oldal minden eleme bet�lt�d�tt.

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoaded());

        // �rv�nyes, a rendszerben m�r regisztr�lt bel�p�si adatok megad�sa

        String userName = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        logger.info("Starting login.");

        HomePage homePage = loginPage.login(userName, password);

        // Ellen�rz�s, hogy a HomePage oldal minden eleme bet�lt�d�tt.

        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();

        // Navig�l�s a profil adatok megjelen�t�se oldalra.

        logger.info("Going to the profile page.");

        homePage.gotoMyProfilePage();

        // Ellen�rz�s, hogy a profil adatait tartalmaz� oldal minden eleme bet�lt�d�tt.

        MyProfilePage myProfilePage = new MyProfilePage(driver);
        assertTrue(myProfilePage.isLoaded());

        // Ellen�rz�s, hogy a m�dos�tani k�v�nt mez�k jelenleg az �rv�nyes, teh�t a regisztr�ci�kor megadott adatokat tartalmazz�k

    //    myProfilePage.validateProfileData();

        // A **Firstname** �s **Lastname** mez�k m�dos�t�sa a **globalTestData.properties** f�jlban le�rt adatok alapj�n.

        myProfilePage.modifyProfileData();

        // Ellen�rz�s, hogy a m�dos�tott mez�k felvett�k-e az elv�rt �rt�ket.

     // myProfilePage.validateProfileData();

        // Ellen�rz�s, hogy megjelenik-e a sikeres profil m�dos�t�s�t visszaigazol� figyelmeztet�s.

        assertTrue(myProfilePage.successMessageIsLoaded());



    }



}
