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

//    private static ReadParam readParam;

    @BeforeClass
    public static void setup() {
        webdriver = CreateDriver.createDriver();
        logging = CreatorLogging.getLogging();
        writeResult = logging.createWriteResult();
//        readParam = logging.createReadParam();

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
            registrationPage.sendKeyName();
            registrationPage.sendKeySurname();
            registrationPage.sendKeyEmail();
            registrationPage.sendKeyPhone();
            registrationPage.sendKeyPassword();
            registrationPage.sendKeyCorrectPassword();
            registrationPage.registration();
            writeResult.writeResult("Заполнение формы регистраций и регистрироваться в сайте по имени \"Kudaybergen Zhandos\"", "", "Registration test", true); // TODO: исправить acutal
        } catch (Exception e) {
            writeResult.writeResult("Заполнение формы регистраций и регистрироваться в сайте по имени \"Kudaybergen Zhandos\"", "", "Registration test", false);
            throw e;
        }
    }

    @Test
    public void exitTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
            mainPage.closeWindows();
            mainPage.goToLogin();
            loginPage.sendKeysLogin();
            loginPage.sendKeysPassword();
            mainPage.goToExit();
            writeResult.writeResult("Выход из аккаунта", "", "Log out test", true); // TODO: исправить acutal
            Assert.assertEquals(mainPage.textLogin(), "Выход"); // здесь тоже постарайтесь, но здесь намного сложнее
        } catch (Exception e) {
            writeResult.writeResult("Выход из аккаунта", "", "Log out test", false);
            throw e;
        }
    }

    @Test
    public void loginTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
            mainPage.goToLogin();
            loginPage.sendKeysLogin();
            loginPage.sendKeysPassword();
            Assert.assertEquals(mainPage.getUsername(), "Zhandos Kudaybergen");
            mainPage.goToExit();
            writeResult.writeResult("Вход в аккаунт", "", "Log in test", true); // TODO: исправить acutal
        } catch (Exception e) {
            writeResult.writeResult("Вход в аккаунт", "", "Log in test", false);
            throw e;
        }
    }
}
