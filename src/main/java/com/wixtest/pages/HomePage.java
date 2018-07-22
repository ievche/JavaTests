package com.wixtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class HomePage extends BasePage {
    @FindBy(css = "#comp-jhalo8eilink")
    public WebElement shopButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ShopPage clickShopButton() {
        shopButton.click();
        return new ShopPage(driver);
    }

    public void verifyItemsAmountOnCartIcon(String amount) {
        switchToCartHeaderFrame();
        assertEquals(cartButton.getAttribute("aria-label"), "Cart with " + amount +" items");
        switchToDefaultFrame();
    }

    public void waitForItemsAmountUpdated(String expectedAmount) throws InterruptedException {
        switchToCartHeaderFrame();
        for(int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            i += 1;
            boolean isUpdated = cartButton.getAttribute("aria-label") == "Cart with " + expectedAmount +" items";
            if(isUpdated == true) {
                break;
            }
        }
        switchToDefaultFrame();
    }
}
