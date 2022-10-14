package kz.akvelon.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void sendKeyName(String name) {
        inputName.clear();
        inputName.sendKeys(name);
    }

    public void sendKeySurname(String surname) {
        inputSurname.clear();
        inputSurname.sendKeys(surname);
    }

    public void sendKeyEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void sendKeyPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void sendKeyCorrectPassword(String password) {
        inputCorrectPassword.clear();
        inputCorrectPassword.sendKeys(password, Keys.ENTER);
    }

    public void sendKeyPhone(String phone) {
        inputPhone.clear();
        inputPhone.sendKeys(phone);
    }

    public void registration() {
        linkToRegistration.click();
    }
}
