package hu.Masterfield.utils;

/**
 * Ez az oszt�ly konstansokat tartalmaz, amelyeket k�ls� forr�sb�l, prooperty file-b�l (.prop) olvasunk be.
 */
public class Consts {
    //Konstansok az el�r�si utakhoz
    public static final String GLOBAL_TEST_DATA_PROPERTIES = "/globalTestData.properties";
    public static final String SCREENSHOTS_FOLDER = System.getProperty("user.dir").replace("\\", "/") + "target/screenshots";

    //Konstansok a loginhez
    public static final String LOGIN_USER_NAME = "login.username";
    public static final String LOGIN_PASSWORD = "login.password";


    //Konstansok a regisztr�ci�hoz
    public static final String REG_TITLE = "reg.title";
    public static final String REG_FIRST_NAME = "reg.firstName";
    public static final String REG_LAST_NAME = "reg.lastName";
    public static final String REG_GENDER = "reg.gender";
    public static final String REG_DATE_OF_BIRTH = "reg.dateOfBirth";
    public static final String REG_SOCIAL_SECURITY_NUMBER = "reg.socialSecurityNumber";
    public static final String REG_EMAIL_ADDRESS = "reg.emailAddress";
    public static final String REG_PASSWORD = "reg.password";
    public static final String REG_CONFIRM_PASSWORD = "reg.password";
    public static final String REG_ADDRESS = "reg.address";
    public static final String REG_LOCALTY = "reg.locality";
    public static final String REG_REGION = "reg.region";
    public static final String REG_POSTAL_CODE = "reg.postalCode";
    public static final String REG_COUNTRY = "reg.country";
    public static final String REG_HOME_PHONE = "reg.homePhone";
    public static final String REG_MOBILE_PHONE = "reg.mobilePhone";
    public static final String REG_WORK_PHONE = "reg.workPhone";

    //Konstansok a profil adatok m�dos�t�s�hoz

    public static final String MOD_TITLE = "mod.title";
    public static final String MOD_FIRST_NAME = "mod.firstName";
    public static final String MOD_LAST_NAME = "mod.lastName";
    public static final String MOD_ADDRESS = "mod.address";
    public static final String MOD_REGION = "mod.region";
    public static final String MOD_POSTAL_CODE = "mod.postalCode";
    public static final String MOD_COUNTRY = "mod.country";
    public static final String MOD_HOME_PHONE = "mod.homePhone";
    public static final String MOD_MOBILE_PHONE = "mod.mobilePhone";
    public static final String MOD_WORK_PHONE = "mod.workPhone";




}
