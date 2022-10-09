package kz.akvelon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComparePage {
    public WebDriver driver;

    @FindBy(xpath = "//*[@id=\"iblock45\"]/div[2]/div/div[2]/div[1]")
    private WebElement stringPrice;

    public ComparePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getString() {
        return stringPrice.getText();
    }
}
