package kz.akvelon.tests;

import kz.akvelon.listeners.ListenerTest;
import kz.akvelon.pages.MainPage;
import kz.akvelon.pages.MapPage;
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
public class BasicTest {

    public static WebDriver webdriver;

    public static MapPage mapPage;

    public static MainPage mainPage;

    private static WriteResult writeResult;

    private static CreatorLogging logging;

    @BeforeClass
    public static void setup() {
        webdriver = CreateDriver.createDriver();
        logging = CreatorLogging.getLogging();
        writeResult = logging.createWriteResult();

        webdriver.get(ConfProperties.getProperty("mappage"));

        mapPage = new MapPage(webdriver);
        mainPage = new MainPage(webdriver);

    }

    @AfterClass
    public void tearDown() {
        logging.closeLogging();
        webdriver.close();
    }

    @Test
    public void mapTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mappage"));
            mapPage.goToMap();

            if(mapPage.isDisplayedMap()){
                writeResult.writeResult("Ещё", "Ещё",
                        "Map test", true);
            }
            else {
                writeResult.writeResult("Ещё", "",
                        "Map test", true);
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            writeResult.writeResult("Ещё", "",
                    "Map test", false);
            throw e;
        }
    }

    @Test
    public void changeCityTest() {
        try {
            webdriver.navigate().to(ConfProperties.getProperty("mainpage"));
            mainPage.closeWindows();
            mainPage.changeCity();
            mainPage.changeCityToAstana();
            mainPage.getTextCity();
            writeResult.writeResult("Астана", mainPage.getTextCity(),
                    "Change City", true);
            Assert.assertEquals("Астана", mainPage.getTextCity());
        } catch (Exception e) {
            writeResult.writeResult( "Астана", "",
                    "Change City", false);
            throw e;
        }

    }
}
