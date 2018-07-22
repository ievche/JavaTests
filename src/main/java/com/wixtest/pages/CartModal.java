package com.wixtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CartModal extends BasePage {
    @FindBy(xpath = "//iframe[contains(@src,'cartwidgetPopup')]")
    public WebElement cartModalFrame;

    @FindBy(css = ".minicart.active")
    public WebElement cartModal;

    @FindBy(css = "#cart-widget-close")
    public WebElement closeCartButton;

    @FindBy(xpath = "//span[text()='VIEW CART']//ancestor::a[not(@ng-if)]")
    public WebElement viewCartButton;

    @FindBy(css = "#subtotal")
    public WebElement subtotalLabel;

    @FindBy(css = "[data-hook='cart-widget-total']")
    public WebElement subtotalAmountLabel;

    @FindBy(css = "li[data-hook='cart-widget-item']")
    public List<WebElement> cartItems;

    @FindBy(xpath = "//span[text()='Cart is empty']")
    public WebElement cartIsEmptyLabel;

    public CartModal(WebDriver driver) {
        super(driver);
    }

    public CartModal clickRemoveForItem(String item) {
        switchToCartModalFrame();
        WebElement removeButton = driver.findElement(By.xpath("//div[text()='" + item + "']//ancestor::li//button[@title='Remove Item']"));
        wait.until(ExpectedConditions.visibilityOf(closeCartButton));
        Actions builder = new Actions(driver);
        builder.moveToElement(removeButton).perform();
        builder.moveToElement(removeButton).click().perform();
        switchToDefaultFrame();
        return new CartModal(driver);
    }

    public CartPage clickViewCart() {
        switchToCartModalFrame();
        viewCartButton.click();
        switchToDefaultFrame();
        return new CartPage(driver);
    }

    public void minimizeCartModal() {
        switchToCartModalFrame();
        closeCartButton.click();
        wait.until(ExpectedConditions.invisibilityOf(closeCartButton));
        switchToDefaultFrame();
    }

    public void switchToCartModalFrame() {
        driver.switchTo().frame(cartModalFrame);
    }

    public void verifyCartModalIsOpened() {
        switchToCartModalFrame();
        wait.until(ExpectedConditions.visibilityOf(cartModal));
        cartModal.isDisplayed();
        switchToDefaultFrame();
    }

    public void verifyViewCartButtonDisplayed() {
        switchToCartModalFrame();

        assertEquals(viewCartButton.isDisplayed(), true);

        switchToDefaultFrame();
    }
    public void verifyViewCartButtonIsNotDisplayed() {
        switchToCartModalFrame();

        assertEquals(isElementPresent(viewCartButton), false);

        switchToDefaultFrame();
    }

    public void verifySubtotalPrice(String expectedAmount) {
        switchToCartModalFrame();
        assertEquals(subtotalLabel.isDisplayed(), true);
        assertEquals(subtotalAmountLabel.getText(), expectedAmount);
        switchToDefaultFrame();
    }

    public void verifySubtotalPriceIsNotDisplayed() {
        switchToCartModalFrame();
        assertEquals(isElementPresent(subtotalLabel), false);
        switchToDefaultFrame();
    }

    public void verifyItemsAmount(int amount) {
        switchToCartModalFrame();
        assertEquals(cartItems.size(), amount);
        switchToDefaultFrame();
    }

    public void verifyCartIsEmptyMessageIsDisplayed() {
        switchToCartModalFrame();
        assertEquals(cartIsEmptyLabel.isDisplayed(), true);
        switchToDefaultFrame();
    }
}
