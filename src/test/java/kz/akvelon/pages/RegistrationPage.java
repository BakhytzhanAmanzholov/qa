package kz.akvelon.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.Key;

public class RegistrationPage {
    public WebDriver driver;
    @FindBy(xpath = "//input[@name=\"USER_NAME\"]")
    private WebElement inputName;

    @FindBy(xpath = "//input[@name=\"USER_LAST_NAME\"]")
    private WebElement inputSurname;

    @FindBy(xpath = "//input[@name=\"USER_EMAIL\"]")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@name=\"PERSONAL_PHONE\"]")
    private WebElement inputPhone;

    @FindBy(xpath = "//input[@id=\"REG_USER_PASSWORD\"]")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@name=\"USER_CONFIRM_PASSWORD\"]")
    private WebElement inputCorrectPassword;

    @FindBy(xpath = "//input[@name=\"Register\"]")
    private WebElement linkToRegistration;
    @FindBy(xpath = "//input[@id=\"subscribe_email\"]")
    private WebElement cancelSpam;


    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void sendKeyName() {
        inputName.clear();
        inputName.sendKeys("Zhandos");
    }

    public void sendKeySurname() {
        inputSurname.clear();
        inputSurname.sendKeys("Kudaybergen");
    }

    public void sendKeyEmail() {
        inputEmail.clear();
        inputEmail.sendKeys("janjan.06.kz@gmail.com");
    }

    public void sendKeyPassword() {
//        inputPassword.clear();
        inputPassword.sendKeys("5nFy2UWCJ2b");
    }

    public void sendKeyCorrectPassword() {
        inputCorrectPassword.clear();
        inputCorrectPassword.sendKeys("5nFy2UWCJ2b", Keys.ENTER);
    }

    public void sendKeyPhone() {
        inputPhone.clear();
        inputPhone.sendKeys("77476357012");
    }

    public void registration() {
        linkToRegistration.click();
    }
}
