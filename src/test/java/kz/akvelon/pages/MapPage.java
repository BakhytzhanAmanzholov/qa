package kz.akvelon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MapPage {
    public WebDriver driver;

    @FindBy(xpath = "//a[@href=\"#map-266460\"]")
    private WebElement linkToMap;

    @FindBy(xpath = "//ymaps[contains(text(),'Ещё')]")
    private WebElement textMap;

    public MapPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void goToMap() {
        linkToMap.click();
    }

    public String getTextMap() {
        return textMap.getText();
    }
}
