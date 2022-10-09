package kz.akvelon.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import kz.akvelon.listeners.ListenerTest;
import kz.akvelon.pages.LoginPage;
import kz.akvelon.pages.MainPage;
import kz.akvelon.pages.RegistrationPage;
import kz.akvelon.services.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Listeners(ListenerTest.class)
public class AuthenticationTest {

    public static WebDriver webdriver;

    public static RegistrationPage registrationPage;
    public static MainPage mainPage;
    public static LoginPage loginPage;

    static Sheet worksheet;
    static Workbook workbook;
    static FileInputStream inStream;
    static FileOutputStream outStream;

    private static WriteResult writeResult;

    private static ReadParam readParam;

    @BeforeClass
    public static void setup() throws Exception{
        String web = ConfProperties.getProperty("web");
        if(Objects.equals(web, "firefox")){
            WebDriverManager.firefoxdriver().setup();
            webdriver = new FirefoxDriver();
            System.setProperty("webdriver.gecko.driver", "geckodriver");
        }
        else {
            WebDriverManager.chromedriver().setup();
            webdriver = new ChromeDriver();
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        }
        webdriver.manage().window().maximize();
        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        registrationPage = new RegistrationPage(webdriver);
        mainPage = new MainPage(webdriver);
        loginPage = new LoginPage(webdriver);

        inStream =
                new FileInputStream(ConfProperties.getProperty("shopkz.testdata.path"));
        workbook = WorkbookFactory.create(inStream);
        worksheet = workbook.getSheetAt(0);

        writeResult = new WriteResultToExcel();
        readParam = new ReadParamFromExcel(ConfProperties.getProperty("params.testdata.path"));
    }

    @AfterClass
    public void tearDown() throws IOException {
        outStream = new FileOutputStream(
                ConfProperties.getProperty("shopkz.testdata.path"));
        workbook.write(outStream);
        outStream.close();
        inStream.close();
        workbook.close();
        webdriver.close();
//        driver.close();
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
            writeResult.writeResult(worksheet, "Заполнение формы регистраций и регистрироваться в сайте по имени \"Kudaybergen Zhandos\"", "", "Registration test", true);
        }catch (Exception e){
            writeResult.writeResult(worksheet, "Заполнение формы регистраций и регистрироваться в сайте по имени \"Kudaybergen Zhandos\"", "", "Registration test", false);
            throw e;
        }
    }

    @Test
    public void exitTest(){
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
            mainPage.closeWindows();
            mainPage.goToLogin();
            loginPage.sendKeysLogin();
            loginPage.sendKeysPassword();
            mainPage.goToExit();
            writeResult.writeResult(worksheet, "Выход из аккаунта", "", "Log out test", true);
            Assert.assertEquals(mainPage.textLogin(), "Выход");
    }catch (Exception e){
            writeResult.writeResult(worksheet, "Выход из аккаунта", "", "Log out test", false);
            throw e;
    }
    }

    @Test
    public void loginTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
    //        mainPage.closeWindows();
            mainPage.goToLogin();
            loginPage.sendKeysLogin();
            loginPage.sendKeysPassword();
            Assert.assertEquals(mainPage.getUsername(), "Zhandos Kudaybergen");
            mainPage.goToExit();
            writeResult.writeResult(worksheet, "Вход в аккаунт", "", "Log in test", true);
    }catch (Exception e){
        writeResult.writeResult(worksheet, "Вход в аккаунт", "", "Log in test", false);
        throw e;
    }
    }


}
