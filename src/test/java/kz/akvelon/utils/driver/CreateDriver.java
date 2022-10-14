package kz.akvelon.utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import kz.akvelon.services.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CreateDriver {

    public static WebDriver createDriver() {
        WebDriver webDriver;
        if (Objects.equals(ConfProperties.getProperty("driver"), "firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
            System.setProperty("webdriver.gecko.driver", "geckodriver");
        } else {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }
}
