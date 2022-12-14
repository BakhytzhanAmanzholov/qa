package kz.akvelon.tests;

import kz.akvelon.listeners.ListenerTest;
import kz.akvelon.pages.LoginPage;
import kz.akvelon.pages.MainPage;
import kz.akvelon.pages.RegistrationPage;
import kz.akvelon.services.*;
import kz.akvelon.utils.driver.CreateDriver;
import kz.akvelon.utils.logging.CreatorLogging;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(ListenerTest.class)
public class AuthenticationTest {

    public static WebDriver webdriver;

    public static RegistrationPage registrationPage;
    public static MainPage mainPage;
    public static LoginPage loginPage;

    private static CreatorLogging logging;

    private static WriteResult writeResult;
    private static ReadParam readParam;

    @BeforeClass
    public static void setup() {
        webdriver = CreateDriver.createDriver();
        logging = CreatorLogging.getLogging();
        writeResult = logging.createWriteResult();
        readParam = logging.createReadParam();

        registrationPage = new RegistrationPage(webdriver);
        mainPage = new MainPage(webdriver);
        loginPage = new LoginPage(webdriver);
    }

    @AfterClass
    public void tearDown() {
        logging.closeLogging();
        webdriver.close();
    }


    @Test
    public void registrationTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("registrationpage"));
            registrationPage.sendKeyName(readParam.readParam("name"));
            registrationPage.sendKeySurname(readParam.readParam("surname"));
            registrationPage.sendKeyEmail(readParam.readParam("email"));
            registrationPage.sendKeyPhone(readParam.readParam("phone"));
            registrationPage.sendKeyPassword(readParam.readParam("password"));
            registrationPage.sendKeyCorrectPassword(readParam.readParam("correctPassword"));
            registrationPage.registration();
            writeResult.writeResult("???????????????????? ?????????? ?????????????????????? ?? ???????????????????????????????? ?? ?????????? ???? ?????????? \"Kudaybergen Zhandos\"", "Enter the marked coordinates and write the text in this area", "Registration test", true); // TODO: Enter the marked coordinates and write the text in this area
        } catch (Exception e) {
            writeResult.writeResult("???????????????????? ?????????? ?????????????????????? ?? ???????????????????????????????? ?? ?????????? ???? ?????????? \"Kudaybergen Zhandos\"", "The marked coordinates have not been entered and the text in this area has not been recorded", "Registration test", false);
            throw e;
        }
    }

    @Test
    public void exitTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
            mainPage.closeWindows();
            mainPage.goToLogin();
            loginPage.sendKeysLogin(readParam.readParam("email"));
            loginPage.sendKeysPassword(readParam.readParam("password"));
            mainPage.goToExit();
            writeResult.writeResult("?????????? ???? ????????????????", "Go to the main page, find the path to the \"??????????\" button and click on the", "Log out test", true); // TODO: Go to the main page, find the path to the "Exit" button and click on the
            Assert.assertEquals(mainPage.textLogin(), "??????????"); // ?????????? ???????? ????????????????????????, ???? ?????????? ?????????????? ??????????????
        } catch (Exception e) {
            writeResult.writeResult("?????????? ???? ????????????????", "In the main page, the path to the \"Exit\" button was not found", "Log out test", false);
            throw e;
        }
    }

    @Test
    public void loginTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
            mainPage.goToLogin();
            loginPage.sendKeysLogin(readParam.readParam("login"));
            loginPage.sendKeysPassword(readParam.readParam("password"));
            Assert.assertEquals(mainPage.getUsername(), readParam.readParam("username"));
            mainPage.goToExit();
            writeResult.writeResult("???????? ?? ??????????????", "Find xpath and write down the login parameters", "Log in test", true); // TODO: ?????????????????? acutal
        } catch (Exception e) {
            writeResult.writeResult("???????? ?? ??????????????", "Find xpath and write down the login parameters", "Log in test", false);
            throw e;
        }
    }
}
