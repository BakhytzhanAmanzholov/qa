package kz.akvelon.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmartphonePage {
    public WebDriver driver;

    @FindBy(xpath = "//span[@id=\"bx_117848907_1054050_subscribe\"]")
    private WebElement buttonToSubscribe;

    @FindBy(xpath = "//input[@id=\"userContact\"]")
    private WebElement userData;

    @FindBy(xpath = "//span[@class=\"btn btn-primary\"]")
    private WebElement buttonToSend;

    @FindBy(xpath = "//p[contains(text(),'Вы успешно подписались')]")
    private WebElement labelSubscribe;

    public SmartphonePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickToButtonSubscribe() {
        buttonToSubscribe.click();
    }

    public void sendKeysToUserData() {
        userData.click();
        userData.sendKeys("kamilakiubaeva@gmail.com");
    }

    public void sendToEmailNot() {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click()", buttonToSend);
    }

    public String getTextSubscribe() {
        return labelSubscribe.getText();
    }

}
