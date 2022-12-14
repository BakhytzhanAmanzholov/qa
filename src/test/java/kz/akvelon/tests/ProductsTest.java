package kz.akvelon.tests;

import kz.akvelon.services.*;
import kz.akvelon.listeners.ListenerTest;
import kz.akvelon.pages.*;
import kz.akvelon.utils.driver.CreateDriver;
import kz.akvelon.utils.logging.CreatorLogging;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


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

    private static WriteResult writeResult;

    private static ReadParam readParam;

    private static CreatorLogging logging;


    @BeforeClass
    public static void setup() {
        webdriver = CreateDriver.createDriver();
        logging = CreatorLogging.getLogging();
        writeResult = logging.createWriteResult();
        readParam = logging.createReadParam();


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

    }

    @AfterClass
    public void tearDown() {
        logging.closeLogging();
        webdriver.close();
    }

    @Test
    public void comparisonProductsTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
            mainPage.goToCatalogGadgets();
            catalogGadgetsPage.clickLinkToGadgets();
            catalogSmartphonesPage.clickLinkToGadgets();
            listOfSmartphonesPage.clickCompareToFirstGadgets();
            listOfSmartphonesPage.clickCompareToSecondGadgets();
            webdriver.navigate().refresh();
            listOfSmartphonesPage.setCompare();
            System.out.println(readParam.readParam("hello"));
            writeResult.writeResult( "???????? ???? ????????????", comparePage.getString(),
                    "Product comparison", true);
            Assert.assertEquals(comparePage.getString(), "???????? ???? ????????????");
        }catch (Exception e) {
            writeResult.writeResult( "???????? ???? ????????????", "",
                    "Product comparison", false);
            throw e;
        }
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

            writeResult.writeResult("?????????? ??????????????????:", orderPage.getString(),
                    "Making Order", true);
            Assert.assertEquals(orderPage.getString(), "?????????? ??????????????????:");
        } catch (Exception e) {
            writeResult.writeResult("?????????? ??????????????????:", "",
                    "Making Order", false);
            throw e;

        }

    }

    @Test
    public void orderCancellation() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));

            listOfSmartphonesPage.clickToOrder();
            orderPage.clickToCancelOrder();

            writeResult.writeResult("", "",
                    "Order Cancellation", true);
//            Assert.assertEquals(orderPage.getString(), "");
        } catch (Exception e) {
            writeResult.writeResult("", "",
                    "Order Cancellation", false);
            throw e;

        }
    }

    @Test
    public void findProductTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));

            mainPage.clearQuery();
            mainPage.clickToQuery();
            mainPage.sendRequest("Acer nitro 5");


            writeResult.writeResult("???????????????????? ???????????? ???? ?????????????? \"Acer nitro 5\"", findProductPage.getString(),
                    "Find Product test", true);
            Assert.assertEquals(findProductPage.getString(), "???????????????????? ???????????? ???? ?????????????? \"Acer nitro 5\"");
        } catch (Exception e) {
            writeResult.writeResult("???????????????????? ???????????? ???? ?????????????? \"Acer nitro 5\"", "",
                    "Find Product test", false);
            throw e;
        }

    }

    @Test
    public void filterProductTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("filterpage"));
            filterPage.sendReqMin("40000");
            filterPage.sendReqMax("120000");
            filterPage.setFilter();
            writeResult.writeResult("40000", filterPage.getPrice(),
                    "Filtering Search Results Test", true);
            Assert.assertEquals(filterPage.getPrice(), "40000");
        } catch (Exception e) {
            writeResult.writeResult("40000", "",
                    "Filtering Search Results Test", false);
            throw e;
        }

    }

    @Test
    public void notifyTest() {
        try {
            webdriver.navigate().to("https://shop.kz/offer/proigryvatel-vinilovykh-plastinok-ritmix-lp-160b-blue/");

            smartphonePage.clickToButtonSubscribe();
            smartphonePage.sendKeysToUserData("kamilakiubaeva@gmail.com");
            smartphonePage.sendToEmailNot();

            System.out.println(readParam.readParam("hello"));

            writeResult.writeResult("???? ?????????????? ??????????????????????", smartphonePage.getTextSubscribe(),
                    "Notify Test", true);
            Assert.assertEquals(smartphonePage.getTextSubscribe(), "???? ?????????????? ??????????????????????");

        } catch (Exception e) {
            writeResult.writeResult("???? ?????????????? ??????????????????????", "",
                    "Notify Test", false);
            throw e;
        }


    }

    @Test
    public void wishlistTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
            mainPage.goToCatalogGadgets();
            catalogGadgetsPage.clickLinkToGadgets();
            catalogSmartphonesPage.clickLinkToGadgets();
            listOfSmartphonesPage.addToWithList();
            listOfSmartphonesPage.goToWishList();
            System.out.println(readParam.readParam("hello"));
            writeResult.writeResult("?????? ???????????? ??????????????", listOfSmartphonesPage.getTextHeading(),
                    "Adding wish list", true);
            Assert.assertEquals(listOfSmartphonesPage.getTextHeading(), "?????? ???????????? ??????????????");
        } catch (Exception e) {
            writeResult.writeResult("?????? ???????????? ??????????????", "",
                    "Adding wish list", false);
            throw e;
        }
    }

}
