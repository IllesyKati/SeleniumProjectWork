package hu.masterfield.pages;

import hu.masterfield.utils.Consts;
import hu.masterfield.utils.GlobalTestData;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Az oldalon tal�lhat� elemek azonos�t�sa. Ezek sz�ks�gesek a HomePager�l indul�\n" +
        "        m�veletekhez.")

public class HomePage extends BasePage{

    /*  Az oldalon tal�lhat� elemek azonos�t�sa. Ezek sz�ks�gesek a HomePager�l indul�
        m�veletekhez.
     */

    protected static Logger logger = LogManager.getLogger(HomePage.class);
    protected static GlobalTestData globalTestData = new GlobalTestData();

    // Checking menu
    @FindBy(id="checking-menu")
    private WebElement checkingMenu;

    // Checking menu -> View Checking
    @FindBy(id="view-checking-menu-item")
    private WebElement viewCheckingMenuItem;

    // Checking menu -> New Checking
    @FindBy(id="new-checking-menu-item")
    private WebElement newCheckingMenuItem;

    // Savings menu
    @FindBy(id="savings-menu")
    private WebElement savingsMenu;

    // Savings menu -> View Savings
    @FindBy(id="view-savings-menu-item")
    private WebElement viewSavingsMenuItem;

    // Savings menu -> New Savings
    @FindBy(id="new-savings-menu-item")
    private WebElement newSavingsMenuItem;

    // Wlcome messgae
    @FindBy(xpath="//div[@class='page-title']//li")
    //@FindBy(css="div.page-title li")
    //@FindBy(css="div[class='page-title'] li")
    private WebElement labelTitle;

    // User avatar / picture
    @FindBy(css="img.user-avatar.rounded-circle")
    private WebElement avatarDropdownMenuButton;

    // Avatar lenyit�sa -> My Profile
    // @FindBy(css="a.nav-link[href='/bank/user/profile']")
    @FindBy(xpath="//a[contains(text(),'My Profile')]")
    private WebElement avatartDropdownMyProfileLink;

    // Avatar lenyit�sa -> Change Password
    // @FindBy(css="a.nav-link[href='/bank/user/password']")
    @FindBy(xpath="//a[contains(text(),'Change Password')]")
    private WebElement avatarDropdownChangePasswordLink;

    // Avatar lenyit�sa -> Create Data
    // @FindBy(css="a.nav-link[href='/bank/user/create-data']")
    @FindBy(xpath="//a[contains(text(),'Create Data')]")
    private WebElement avatarDropDownCreateDataLink;

    // Avatar lenyit�sa -> Delete Data
    // @FindBy(css="a.nav-link[href=\"/bank/user/delete-data\"]")
    @FindBy(xpath="//a[contains(text(),'Delete Data')]")
    private WebElement avatarDropdownDeleteDataLink;

    // Avatar lenyit�sa -> Logout
//    @FindBy(css="a.nav-link[href=\"/bank/user/logout\"]")
    @FindBy(xpath="//a[contains(text(),'Logout')]")
    private WebElement avatarDropdownLogoutLink;

    // TRANSACTIONS / TRANSFERS -> Deposit
    @FindBy(id="deposit-menu-item")
    private WebElement depositLink;

    // TRANSACTIONS / TRANSFERS -> Withdraw
    @FindBy(id="withdraw-menu-item")
    private WebElement withdrawLink;

    /**
     * A HomePager�l elnavig�l a Create Savings oldalra a men�ben.
     *
     * @return a megnyitott Create Savings oldal objektuma
     */
    @Step("Navig�l�s a Create Savings oldalra.")
    public CreateSavingsPage gotoNewSavingsPage() {
        logger.info("gotoNewSavingsPage() called.");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("newSavingsMenuItem.click()");
        newSavingsMenuItem.click();

        takesScreenshot();

        return new CreateSavingsPage(driver);
    }

    /**
     * HomePager�l elnavig�l a View Savings oldalra.
     *
     * @return a megnyitott View Savings oldal objektuma
     */
    @Step("Navig�l�s a View Savings oldalra.")
    public ViewSavingsAccountsPage gotoViewSavingsPage() {
        logger.info("gotoViewSavingsPage() called.");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("viewSavingsMenuItem.click()");
        viewSavingsMenuItem.click();

        takesScreenshot();

        return new ViewSavingsAccountsPage(driver);
    }

    /**
     * HomePager�l elnavig�lunk a Deposit oldalra.
     *
     * @return a megnyitott Deposit oldal objektuma
     */
    @Step("Navig�l�s a Deposit oldalra.")
    public DepositPage gotoDepositPage() {
        logger.info("gotoDepositPage() called.");

        logger.trace("depositLink.click()");
        depositLink.click();

        takesScreenshot();

        return new DepositPage(driver);
    }

    /**
     * HomePager�l elnavig�lunk a Savings/Transactions oldalra.
     *
     * @return Savings/Transactions oldal objektuma
     */
    @Step("Navig�l�s a 'View Savings' oldalon tal�lhat� tranzakci�s t�bl�zat oldal�ra.")
    public TransactionsPage gotoTransactionsPage() {
        logger.info("gotoTransactionsPage() called");

        logger.trace("savingsMenu.click()");
        savingsMenu.click();

        logger.trace("viewSavingsMenuItem.click();");
        viewSavingsMenuItem.click();

        takesScreenshot();

        return new TransactionsPage(driver);
    }

    /**
     * HomePager�l elnavig�lunk a MyProfile oldalra.
     *
     * @return MyProfile oldal objektuma
     */
    @Step("Navig�l�s a profil adatokat megjelen�t� �s m�dos�t� oldalra.")
    public MyProfilePage gotoMyProfilePage() {
        logger.info("gotoMyProfilePage() called");

        logger.trace("avatarDropdownMenuBotton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownMyProfileLink.click()");
        avatartDropdownMyProfileLink.click();

        takesScreenshot();

        return new MyProfilePage(driver);

    }

    /**
     * Adatok t�rl�se
     */
    @Step("Adatok t�rl�se.")
    public void deleteData() {
        logger.info("deleteData() called");

        logger.trace("avatarDropdownMenuButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatarDropdownDeleteDataLink.click()");
        avatarDropdownDeleteDataLink.click();

        takesScreenshot();
    }

    /**
     * Ellen�rzi a HomePage bet�lt�d�s�t
     *
     * @return true, ha bet�lt�d�tt a HomePage
     */
    @Step("HomePage bet�lt�d�s�nek ellen�rz�se.")
    public boolean isLoaded() {
        logger.info("HomePage.isLoaded() called");
        boolean isLoaded = isLoaded(savingsMenu) && isLoaded(checkingMenu)
                && isLoaded(avatarDropdownMenuButton);
        logger.trace("isLoaded= " + isLoaded);

        return isLoaded;
    }

    /**
     * Logout megval�s�t�sa
     * @return LoginPage t�pus� objektum
     */
    @Step("Logout megval�s�t�sa.")
    public LoginPage logut() {
        logger.info("logout() called");

        logger.trace("avatarMenuDropdownButton.click()");
        avatarDropdownMenuButton.click();

        logger.trace("avatartDropdownLogoutLink.click()");
        avatarDropdownLogoutLink.click();

        takesScreenshot();

        return new LoginPage(driver);
    }

    /**
     * HomePage ellen�rz�se.
     */
    @Step("HomePage ellen�rz�se.")
    public void validateHomePage() {
        logger.info("validateHomePage called");
        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/home"));
        assertEquals("Welcome " + globalTestData.getProperty(Consts.REG_FIRST_NAME)
                , labelTitle.getText());
    }

    /**
     * HomePage ellen�rz�se profil m�dos�t�sa ut�n.
     */
    @Step("HomePage ellen�rz�se profil m�sos�t�sa ut�n.")
    public void validateHomePageAfterModifyProfile() {
        logger.info("validateHomePageAfterModifyProfile called");
        assertEquals("Digital Bank", driver.getTitle());
        assertTrue(driver.getCurrentUrl().endsWith("/bank/home"));
        assertEquals("Welcome " + globalTestData.getProperty(Consts.MOD_FIRST_NAME)
                , labelTitle.getText());
    }

    public HomePage(WebDriver driver)
    {
        super(driver);
    }
}