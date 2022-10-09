package kz.akvelon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogGadgetsPage {
    public WebDriver driver;
    @FindBy(xpath = "//a[@href=\"/catalog/sotovye-telefony/\"]")
    private WebElement linkToGadgets;

    public CatalogGadgetsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickLinkToGadgets() {
        linkToGadgets.click();
    }
}
