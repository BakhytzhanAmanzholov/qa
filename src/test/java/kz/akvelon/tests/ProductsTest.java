package kz.akvelon.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import kz.akvelon.services.*;
import kz.akvelon.listeners.ListenerTest;
import kz.akvelon.pages.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.*;
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
public class ProductsTest {

    public static CatalogGadgetsPage catalogGadgetsPage;
    public static MainPage mainPage;
    public static CatalogSmartphonesPage catalogSmartphonesPage;
    public static ListOfSmartphonesPage listOfSmartphonesPage;
    public static ComparePage comparePage;
    public static OrderPage orderPage;

    public static FindProductPage findProductPage;

    public static FilterPage filterPage;
    public static SmartphonePage smartphonePage;

    public static WebDriver webdriver;

    static Sheet worksheet;
    static Workbook workbook;
    static FileInputStream inStream;
    static FileOutputStream outStream;

    private static WriteResult writeResult;

    private static ReadParam readParam;


    @BeforeClass
    public static void setup() throws Exception {
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
        webdriver.get(ConfProperties.getProperty("mainpage"));

        catalogGadgetsPage = new CatalogGadgetsPage(webdriver);
        mainPage = new MainPage(webdriver);
        catalogSmartphonesPage = new CatalogSmartphonesPage(webdriver);
        listOfSmartphonesPage = new ListOfSmartphonesPage(webdriver);
        comparePage = new ComparePage(webdriver);
        orderPage = new OrderPage(webdriver);
        mainPage = new MainPage(webdriver);
        findProductPage = new FindProductPage(webdriver);
        filterPage = new FilterPage(webdriver);
        smartphonePage = new SmartphonePage(webdriver);

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
    }

    @Test
    public void comparisonProductsTest() {
        webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
        mainPage.goToCatalogGadgets();
        catalogGadgetsPage.clickLinkToGadgets();
        catalogSmartphonesPage.clickLinkToGadgets();
        listOfSmartphonesPage.clickCompareToFirstGadgets();
        listOfSmartphonesPage.clickCompareToSecondGadgets();
        webdriver.navigate().refresh();
        listOfSmartphonesPage.clickCompareToFirstGadgets();
        Assert.assertEquals(comparePage.getString(), "Цена по прайсу");
    }

    @Test
    public void makingOrder() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));

            mainPage.closeWindows();
            mainPage.goToCatalogGadgets();

            catalogGadgetsPage.clickLinkToGadgets();
            catalogSmartphonesPage.clickLinkToGadgets();
            listOfSmartphonesPage.addToOrder();
            webdriver.navigate().refresh();
            listOfSmartphonesPage.clickToOrder();

            System.out.println(readParam.readParam("hello"));

            writeResult.writeResult(worksheet, "Общая стоимость:", orderPage.getString(),
                    "Making Order", true);
            Assert.assertEquals(orderPage.getString(), "Общая стоимость:");
        } catch (Exception e) {
            writeResult.writeResult(worksheet, "Общая стоимость:", "",
                    "Making Order", false);
            throw e;

        }

    }

    @Test
    public void orderCancellation() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("m"));

            listOfSmartphonesPage.clickToOrder();
            orderPage.clickToCancelOrder();

            writeResult.writeResult(worksheet, "", orderPage.getString(),
                    "Order Cancellation", true);
            Assert.assertEquals(orderPage.getString(), "");
        } catch (Exception e) {
            writeResult.writeResult(worksheet, "", "",
                    "Order Cancellation", false);
            throw e;

        }
    }

    @Test
    public void findProductTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("m"));

            mainPage.clearQuery();
            mainPage.clickToQuery();
            mainPage.sendRequest();

            System.out.println(readParam.readParam("hello"));

            writeResult.writeResult(worksheet, "Результаты поиска по запросу \"Acer nitro 5\"", findProductPage.getString(),
                    "Find Product test", true);
            Assert.assertEquals(findProductPage.getString(), "Результаты поиска по запросу \"Acer nitro 5\"");
        } catch (Exception e) {
            writeResult.writeResult(worksheet, "Результаты поиска по запросу \"Acer nitro 5\"", "",
                    "Find Product test", false);
            throw e;
        }

    }

    @Test
    public void filterProductTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("filterpage"));
            filterPage.sendReqMin();
            filterPage.sendReqMax();
            filterPage.setFilter();
            writeResult.writeResult(worksheet, "Фильтрация результатов поиска по \"min:40000, max:120000\"", findProductPage.getString(),
                    "Filtering Search Results Test", true);
            Assert.assertEquals(filterPage.howMany(), "46");
        } catch (Exception e) {
            writeResult.writeResult(worksheet, "Фильтрация результатов поиска по \"min:40000, max:120000\"", "",
                    "Filtering Search Results Test", false);
            throw e;
        }

    }

    @Test
    public void notifyTest() {
        try {
            webdriver.navigate().to("https://shop.kz/offer/proigryvatel-vinilovykh-plastinok-ritmix-lp-160b-blue/");

            smartphonePage.clickToButtonSubscribe();
            smartphonePage.sendKeysToUserData();
            smartphonePage.sendToEmailNot();

            System.out.println(readParam.readParam("hello"));

            writeResult.writeResult(worksheet, "Вы успешно подписались", smartphonePage.getTextSubscribe(),
                    "Notify Test", true);
            Assert.assertEquals(smartphonePage.getTextSubscribe(), "Вы успешно подписались");

        } catch (Exception e) {
            writeResult.writeResult(worksheet, "Вы успешно подписались", "",
                    "Notify Test", false);
            throw e;
        }


    }

    @Test
    public void wishlistTest() {
        webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
        mainPage.goToCatalogGadgets();
        catalogGadgetsPage.clickLinkToGadgets();
        catalogSmartphonesPage.clickLinkToGadgets();
        listOfSmartphonesPage.addToWithList();
        listOfSmartphonesPage.goToWishList();
        Assert.assertEquals(listOfSmartphonesPage.getTextHeading(), "Мой список желаний");
    }

}
