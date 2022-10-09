package kz.akvelon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindProductPage {
    public WebDriver driver;

    @FindBy(xpath = "//h1[@id=\"pagetitle\"]")
    private WebElement titlePage;



    public FindProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getString(){
        return titlePage.getText();
    }
}
