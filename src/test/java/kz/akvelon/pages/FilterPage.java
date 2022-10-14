package kz.akvelon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterPage {

    public WebDriver driver;
    @FindBy(xpath = "//input[@id=\"filter_02_P7_MIN\"]")
    private WebElement inputMin;

    @FindBy(xpath = "//input[@id=\"filter_02_P7_MAX\"]")
    private WebElement inputMax;
    @FindBy(xpath = "//a[contains(text(),'Показать')]")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//strong[contains(text(),'46')]")
    private WebElement countFilter;

    public FilterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void sendReqMin(String min){
        inputMin.sendKeys(min);
    }
    public void sendReqMax(String max){
        inputMax.sendKeys(max);
    }

    public void setFilter(){
        buttonSubmit.click();
    }
    public String howMany(){
        return countFilter.getText();
    }
}
