package kz.akvelon.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public WebDriver driver;

    @FindBy(xpath = "//input[@name=\"query\"]")
    private WebElement queryInput;

    @FindBy(xpath = "//a[@href=\"/catalog/smartfony-i-gadzhety/\"]")
    private WebElement linkToCatalog;
    @FindBy(xpath = "//a[@id=\"btn_show_auth\"]")
    private WebElement linkToLogin;
    @FindBy(xpath = "//button[@id=\"onesignal-slidedown-cancel-button\"]")
    private WebElement closeWindow;

    @FindBy(xpath = "//span[contains(text(),'Zhandos Kudaybergen')]")
    private WebElement usernameLabel;

    @FindBy(xpath = "//a[@href=\"/?logout=yes\"]")
    private WebElement linkToExit;

    @FindBy(xpath = "//a[contains(text(),'Алматы')]")
    private WebElement linkChangeCity;

    @FindBy(xpath = "//a[contains(text(),'Астана')]")
    private WebElement linkChangeCityAstana;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clearQuery() {
        queryInput.clear();
    }

    public void clickToQuery() {
        queryInput.click();
    }

    public void sendRequest(String request) {
        queryInput.sendKeys(request);
        queryInput.sendKeys(Keys.ENTER);
    }

    public void goToCatalogGadgets() {
        linkToCatalog.click();
    }

    public void goToLogin() {
        linkToLogin.click();
    }

    public void closeWindows() {
        closeWindow.click();
    }

    public String getUsername() {
        return usernameLabel.getText();
    }

    public void goToExit() {
        linkToExit.click();
    }

    public String textLogin() {
        return linkToLogin.getText();
    }

    public void changeCity() {
        linkChangeCity.click();
    }

    public void changeCityToAstana() {
        linkChangeCityAstana.click();
    }

    public String getTextCity() {
        return linkChangeCityAstana.getText();
    }

}
