package com.wixtest.pages.products;

import com.wixtest.pages.BasePage;
import com.wixtest.pages.CartModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;

class ProductPage extends BasePage {
    @FindBy(css = "[title='Product Page']")
    public WebElement productPageFrame;

    @FindBy(css = "button[focus-on='add-to-cart']" )
    public WebElement addToCartButton;

    @FindBy(css = "#quantity-input")
    public WebElement quantityInput;

    @FindBy(css = "product-name > h2")
    public WebElement productNameLabel;

    @FindBy(css = "product-sku > div")
    public WebElement productSkuLabel;

    @FindBy(css = "span[data-hook='product-price']")
    public WebElement productPriceLabel;

    ProductPage(WebDriver driver) {
        super(driver);
    }

    public void switchToProductPageFrame() {
        driver.switchTo().frame(productPageFrame);
    }

    public CartModal clickAddToCart() throws InterruptedException {
        switchToProductPageFrame();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        switchToDefaultFrame();
        return new CartModal(driver);
    }

    public void verifyProductPageElementsDisplayed() {
        switchToProductPageFrame();
        assertEquals(quantityInput.isDisplayed(), true);
        assertEquals(productNameLabel.isDisplayed(), true);
        assertEquals(productSkuLabel.isDisplayed(), true);
        assertEquals(productPriceLabel.isDisplayed(), true);
        switchToDefaultFrame();
    }

    public void verifyProductName(String expectedName) {
        switchToProductPageFrame();
        assertEquals(productNameLabel.getText(), expectedName);
        switchToDefaultFrame();
    }

    public void verifyProductPrice(String expectedPrice) {
        switchToProductPageFrame();
        assertEquals(productPriceLabel.getText(), expectedPrice);
        switchToDefaultFrame();
    }

}
