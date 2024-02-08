package hu.masterfield.testcases;

import hu.masterfield.datatypes.RegistrationData;
import hu.masterfield.datatypes.Saving;
import hu.masterfield.pages.GDPRBannerPage;
import hu.masterfield.pages.HomePage;
import hu.masterfield.pages.LoginPage;
import hu.masterfield.pages.ViewSavingsAccountsPage;
import hu.masterfield.utils.Consts;
import hu.masterfield.utils.DataSource;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

// TC9 - L�tez� "Savings" t�pus� sz�ml�k adatainak lement�se a fel�letr�l
public class TC9_SavingDataToCSV_Test extends BaseTest{
    protected static Logger logger = LogManager.getLogger(TC9_SavingDataToCSV_Test.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();
    protected static RegistrationData registrationData = new RegistrationData();

    @Test
    @DisplayName("TC9_SavingDataToCSV_Test")
    @Description("TC9 - L�tez� \"Savings\" t�pus� sz�ml�k adatainak lement�se a fel�letr�l")
    @Tag("TC9")
    @Tag("Savings")

    public void TC9_SavingDataToCSV(TestInfo testInfo) throws InterruptedException {
        Thread.sleep(5000);


        //GDPR nyilatkozat elfogad�sa

        GDPRBannerPage gdprBannerPage = new GDPRBannerPage(driver);
        assertTrue(gdprBannerPage.isLoaded());
        gdprBannerPage.acceptCookies();

        //Ellen�rz�s, hogy a Login oldal minden eleme bet�lt�d�tt.

        String username = globalTestData.getProperty(Consts.REG_EMAIL_ADDRESS);
        String password = globalTestData.getProperty(Consts.REG_PASSWORD);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.isLoaded();

        // �rv�nyes, a rendszerben m�r regisztr�lt bel�p�si adatok megad�sa

        HomePage homePage = loginPage.login(username, password);

        //Ellen�rz�s, hogy a HomePage oldal minden eleme bet�lt�d�tt.

        assertTrue(homePage.isLoaded());
        homePage.validateHomePage();

        // A l�trehozott "Savings" t�pus� sz�ml�k adatainak (account name, type, balance, ownership) lement�se egy **dumpSavings.csv** f�jlba, a **target** k�nyvt�rban kialak�tott helyre

        /*

        ViewSavingsAccountsPage viewSavingsAccountsPage = homePage.gotoViewSavingsPage();

        ArrayList<Saving>saving = new ArrayList<>();

        for

        //viewSavingsAccountsPage.isCardsLoaded();

       // viewSavingsAccountsPage.getAllSavings();

        //DataSource dataSource = new DataSource();

       // DataSource.saveSavings();


         */


















    }

}
