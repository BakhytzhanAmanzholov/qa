package kz.akvelon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ListOfSmartphonesPage {
    public WebDriver driver;
    @FindBy(xpath = "//a[@id=\"bx_3966226736_1451597_compare_link\"]")
    private WebElement buttonToFirstSmartphone;

    @FindBy(xpath = "//a[@id=\"bx_3966226736_1451573_compare_link\"]")
    private WebElement buttonToSecondSmartphone;

    @FindBy(xpath = "//a[@id=\"bx_3966226736_1451597_buy_link\"]")
    private WebElement buttonAddToOrder;
    ///<a class="sprite-side novisited hub-i-link hub-i-cart-link-count" href="/personal/basket/">
    //            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
    //            <span class="xhr">Корзина</span>
    //            <span class="hub-i-count">1</span> </a>
    @FindBy(xpath = "//a[@href=\"/personal/basket/\"]")
    private WebElement linkGoToOrder;

    @FindBy(xpath = "//a[@id=\"bx_3966226736_1451597_wishlist_link\"]")
    private WebElement buttonAddToWishList;

    @FindBy(xpath = "//a[@href=\"/personal/wishlist/\"]")
    private WebElement buttonGoToWishList;

//<h1 class="bx-title dbg_title" id="pagetitle">Мой список желаний</h1>
    @FindBy(xpath = "//h1[@id=\"pagetitle\"]")
    private WebElement headingText;

    public ListOfSmartphonesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickCompareToFirstGadgets() {
        buttonToFirstSmartphone.click();
    }

    public void clickCompareToSecondGadgets() {
        buttonToSecondSmartphone.click();
    }


    public void addToOrder() {
        buttonAddToOrder.click();
    }

    public void clickToOrder(){
        linkGoToOrder.click();
    }

    public void addToWithList(){
        buttonAddToWishList.click();
    }

    public void goToWishList(){
        buttonGoToWishList.click();
    }

    public String getTextHeading(){
        return headingText.getText();
    }

}
