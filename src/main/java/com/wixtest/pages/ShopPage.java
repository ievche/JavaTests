package com.wixtest.pages;

import com.wixtest.pages.products.GlassesProductPage;
import com.wixtest.pages.products.ScarfProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

import java.util.List;

public class ShopPage extends BasePage {
    @FindBy(css = "div#PAGES_CONTAINER")
    public WebElement poroductsContainer;

    @FindBy(css = "iframe[title=Shop]")
    public WebElement shopFrame;

    @FindBy(css = "a[class='product-item']")
    public List<WebElement> productItems;

    @FindBy(xpath = "//product-item//a[contains(@href,'premium-glasses')]")
    public WebElement glassesProductLabel;

    @FindBy(xpath = "//product-item//a[contains(@href,'premium-glasses')]//span[@data-hook='price']")
    public WebElement glassesProductPriceLabel;

    @FindBy(xpath = "//product-item//a[contains(@href,'i-m-a-product')]")
    public WebElement scarfProductLabel;

    @FindBy(xpath = "//product-item//a[contains(@href,'i-m-a-product')]//span[@data-hook='price']")
    public WebElement scarfProductPriceLabel;

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public GlassesProductPage clickGlassesLabel() {
        driver.switchTo().frame(shopFrame);
        glassesProductLabel.click();
        driver.switchTo().defaultContent();
        return new GlassesProductPage(driver);
    }

    public ScarfProductPage clickScarfLabel() {
        driver.switchTo().frame(shopFrame);
        scarfProductLabel.click();
        driver.switchTo().defaultContent();
        return new ScarfProductPage(driver);
    }

    public String takeGlassesPrice() {
        driver.switchTo().frame(shopFrame);
        String price = glassesProductPriceLabel.getText();
        driver.switchTo().defaultContent();
        return price;
    }

    public String takeScarfPrice() {
        driver.switchTo().frame(shopFrame);
        String price = scarfProductPriceLabel.getText();
        driver.switchTo().defaultContent();
        return price;
    }

    public void virifyProductsContainerIsDisplayed() {
        assertEquals(poroductsContainer.isDisplayed(), true);
    }

}
