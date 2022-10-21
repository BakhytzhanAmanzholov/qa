package kz.akvelon.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kz.akvelon.pages.FindProductPage;
import kz.akvelon.pages.MainPage;
import kz.akvelon.services.ConfProperties;
import kz.akvelon.utils.driver.CreateDriver;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;


public class ShopkzReport {
    private WebDriver webDriver;

    private MainPage mainPage;

    private FindProductPage findProductPage;

    @When("open browser")
    public void open_browser() {
        webDriver = CreateDriver.createDriver();
        mainPage = new MainPage(webDriver);
        findProductPage = new FindProductPage(webDriver);
    }

    @Given("go to main")
    public void go_to_main() {
        webDriver.navigate().to(ConfProperties.getProperty("mainpage"));
    }

    @When("close windows")
    public void close_windows() {
        mainPage.closeWindows();
    }

    @Given("click to query")
    public void click_to_query() {
        mainPage.clearQuery();
        mainPage.clickToQuery();
    }

    @When("send request")
    public void send_request() {
        mainPage.sendRequest("Acer nitro 5");
    }

    @Then("text of title should be")
    public void text_of_title_should_be(String expected) {
        assertEquals(expected, findProductPage.getString());
//        mainPage.closeWindows();
    }


}