package kz.akvelon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {
    public WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Общая стоимость')]")
    private WebElement stringOrder;

    @FindBy(xpath = "//button[contains(@onclick, \"itemBasketDelete\")]")
    private WebElement cancelOrder;



    public OrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public String getString() {
        return stringOrder.getText();
    }

    public void clickToCancelOrder(){
        cancelOrder.click();
    }
}
