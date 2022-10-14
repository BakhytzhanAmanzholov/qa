package kz.akvelon.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;

    @FindBy(xpath = "//input[@name=\"query\"]")
    private WebElement queryInput;

    @FindBy(xpath = "//input[@name=\"USER_LOGIN\"]")
    private WebElement userLogin;

    @FindBy(xpath = "//input[@name=\"USER_PASSWORD\"]")
    private WebElement userPassword;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void sendKeysLogin(String email) {
        userLogin.clear();
        userLogin.sendKeys(email);
    }

    public void sendKeysPassword(String password) {
        userPassword.sendKeys(password, Keys.ENTER);
    }
}
