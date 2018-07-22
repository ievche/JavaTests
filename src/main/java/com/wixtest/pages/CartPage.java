package com.wixtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    @FindBy(css = "[title='Cart Page']")
    public WebElement cartPageFrame;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage updateProductQuantity(String productName, String amount) {
        driver.switchTo().frame(cartPageFrame);
        WebElement quantityInput = driver.findElement(By.xpath("//section[contains(.,'" + productName + "')]//input[@aria-label='quantity']"));
        quantityInput.clear();
        quantityInput.sendKeys(amount);
        driver.switchTo().defaultContent();
        return this;
    }

    public CartPage removeProduct(String productName) {
        driver.switchTo().frame(cartPageFrame);
        WebElement removeLink = driver.findElement(By.xpath("//section[contains(.,\"" + productName + "\")]//button[@data-hook='remove-button']"));
        removeLink.click();
        driver.switchTo().defaultContent();
        return this;
    }

}
