package kz.akvelon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogSmartphonesPage {

    public WebDriver driver;
    @FindBy(xpath = "//a[@href=\"https://shop.kz/smartfony/\"]")
    private WebElement linkToGadget;

    public CatalogSmartphonesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickLinkToGadgets() {
        linkToGadget.click();
    }
}
