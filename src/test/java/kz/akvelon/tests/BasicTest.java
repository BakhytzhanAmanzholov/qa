package kz.akvelon.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import kz.akvelon.listeners.ListenerTest;
import kz.akvelon.pages.MainPage;
import kz.akvelon.pages.MapPage;
import kz.akvelon.services.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Listeners(ListenerTest.class)
public class BasicTest {

    public static WebDriver webdriver;

    public static MapPage mapPage;

    public static MainPage mainPage;

    static Sheet worksheet;
    static Workbook workbook;
    static FileInputStream inStream;
    static FileOutputStream outStream;

    private static WriteResult writeResult;


    private static ReadParam readParam;

    @BeforeClass
    public static void setup() throws Exception{
        String web = ConfProperties.getProperty("web");
        if (Objects.equals(web, "firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webdriver = new FirefoxDriver();
            System.setProperty("webdriver.gecko.driver", "geckodriver");
        } else {
            WebDriverManager.chromedriver().setup();
            webdriver = new ChromeDriver();
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        }
        webdriver.manage().window().maximize();
        webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webdriver.get(ConfProperties.getProperty("mappage"));


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
    public void mapTest() {
        try{
            webdriver.navigate().to(ConfProperties.getProperty("mappage"));
            mapPage.goToMap();

            writeResult.writeResult(worksheet, "Ещё", mapPage.getTextMap(),
                    "Map test", true);
            Assert.assertEquals(mapPage.getTextMap(), "Ещё");
        }
        catch (Exception e){
            writeResult.writeResult(worksheet, "Ещё","",
                    "Map test", false);
            throw e;
        }


    }

    @Test
    public void changeCityTest() {
        webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
        mainPage.closeWindows();
        mainPage.changeCity();
        mainPage.changeCityToAstana();
        webdriver.navigate().refresh();

        Assert.assertEquals(mainPage.getTextCity(), "Астана");
    }
}
