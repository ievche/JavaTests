package com.wixtest.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class BasePage {
    @FindBy(css = "#comp-jh99ppou0label")
    public WebElement homeLink;

    @FindBy(css = "#comp-jh99ppou1label")
    public WebElement contactFormLink;

    @FindBy(css = "#comp-jh99ppou2label")
    public WebElement storesLink;

    @FindBy(css = "button#comp-jh9ace6ulogin")
    public WebElement loginButton;

    @FindBy(css = "#comp-jh9acbuwiframe")
    public WebElement cartFrame;

    @FindBy(css = "#cart-widget-button")
    public WebElement cartButton;

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    public ShopPage clickStoresLink() {
        storesLink.click();
        return new ShopPage(driver);
    }

    public CartModal clickCartButton() throws InterruptedException {
        switchToCartHeaderFrame();
        Thread.sleep(3000);
        cartButton.click();
        switchToDefaultFrame();

        return new CartModal(driver);
    }

    public void verifyHeaderIsDisplayed() {
        assertEquals(homeLink.isDisplayed(), true);
        assertEquals(contactFormLink.isDisplayed(), true);
        assertEquals(storesLink.isDisplayed(), true);
        assertEquals(loginButton.isDisplayed(), true);

        switchToCartHeaderFrame();
        assertEquals(cartButton.isDisplayed(), true);
        driver.switchTo().defaultContent();
    }

    public void switchToCartHeaderFrame() {
        driver.switchTo().frame(this.cartFrame);
    }

    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    public boolean isElementPresent(WebElement element) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            element.isDisplayed();
        } catch (NoSuchElementException e) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return false;
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return true;
    }
}
