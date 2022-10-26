package kz.akvelon.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kz.akvelon.pages.*;
import kz.akvelon.services.ConfProperties;
import kz.akvelon.utils.driver.CreateDriver;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;


public class ShopkzReport {
    private WebDriver webDriver;

    private MainPage mainPage;

    private FindProductPage findProductPage;

    private FilterPage filterPage;

    private CatalogGadgetsPage catalogGadgetsPage;

    private CatalogSmartphonesPage catalogSmartphonesPage;

    private ListOfSmartphonesPage listOfSmartphonesPage;

    private ComparePage comparePage;
    private LoginPage loginPage;

    @When("open browser")
    public void open_browser() {
        webDriver = CreateDriver.createDriver();
        mainPage = new MainPage(webDriver);
        findProductPage = new FindProductPage(webDriver);
        filterPage = new FilterPage(webDriver);
        catalogGadgetsPage = new CatalogGadgetsPage(webDriver);
        listOfSmartphonesPage = new ListOfSmartphonesPage(webDriver);
        catalogSmartphonesPage = new CatalogSmartphonesPage(webDriver);
        comparePage = new ComparePage(webDriver);
        loginPage = new LoginPage(webDriver);
    }

    @Given("go to main")
    public void go_to_main() {
        webDriver.navigate().to(ConfProperties.getProperty("mainpage"));
    }

    @Given("go to filter")
    public void go_to_filter() {
        webDriver.navigate().to(ConfProperties.getProperty("filterpage"));
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


    @When("set min price as {string} and max price as {string}")
    public void set_Min_Price_And_Max_PriceAs(String arg0, String arg1) {
        filterPage.sendReqMin(arg0);
        filterPage.sendReqMax(arg1);
    }

    @Then("set filter")
    public void set_filter() {
        filterPage.setFilter();
    }



    @Then("text of filter title should be as {string}")
    public void textOfFilterTitleShouldBeAs(String arg0) {
        assertEquals(arg0, filterPage.getPrice());
    }

    @When("go to catalog")
    public void go_to_catalog() {
        webDriver.navigate().to(ConfProperties.getProperty("catalogpage")); }

    @Then("click to link to gadgets")
    public void click_to_link_to_gadgets() {
        catalogGadgetsPage.clickLinkToGadgets();
        catalogSmartphonesPage.clickLinkToGadgets();
    }

    @Then("add to compare gadgets")
    public void add_to_order() {
        listOfSmartphonesPage.clickCompareToFirstGadgets();
        listOfSmartphonesPage.clickCompareToSecondGadgets();
        webDriver.navigate().refresh();
        listOfSmartphonesPage.setCompare();
    }

    @Then("comparison should be as {string}")
    public void comparison_should_be(String expected) {
        assertEquals(comparePage.getString(), expected);
    }


    @When("go to login page")
    public void goToLoginPage() {
        mainPage.goToLogin();
    }

    @Then("set username as {string} and password as {string}")
    public void setUsernameAsAndPasswordAs(String arg0, String arg1) {
        loginPage.sendKeysLogin(arg0);
        loginPage.sendKeysPassword(arg1);
    }

    @Then("username should be as {string}")
    public void usernameShouldBeAs(String arg0) {
        assertEquals(arg0, loginPage.getUsername());
    }
}