package com.wixtest.tests;

import com.wixtest.pages.CartModal;
import com.wixtest.pages.CartPage;
import com.wixtest.pages.products.GlassesProductPage;
import com.wixtest.pages.HomePage;
import com.wixtest.pages.ShopPage;
import com.wixtest.pages.products.ScarfProductPage;
import org.testng.annotations.*;

public class CartTest extends BaseTest {
    @Test
    public void cartTest() throws InterruptedException {
        // 1. Go to Shop
        HomePage homePage = new HomePage(driver);
        ShopPage shopPage = homePage.clickShopButton();
        verifyShopPage(shopPage);

        String glassesPrice = shopPage.takeGlassesPrice();
        Float glassesPriceFloat = Float.parseFloat(glassesPrice.replaceAll("[$]", ""));

        // 2. Select product GLASSES from gallery shown
        GlassesProductPage glassesProductPage = shopPage.clickGlassesLabel();
        verifyGlassesProductPage(glassesProductPage, glassesPrice);

        // 3. Add item to Cart
        CartModal cartModal = glassesProductPage.clickAddToCart();
        verifyCartModal(cartModal, 1, glassesPrice);
        homePage.verifyItemsAmountOnCartIcon("1");

        // 4. Remove item from Cart
        cartModal = cartModal.clickRemoveForItem("Premium Glasses");
        verifyEmptyCartModal(cartModal);
        homePage.verifyItemsAmountOnCartIcon("0");

        // 5. Minimize the Cart
        cartModal.minimizeCartModal();
        verifyGlassesProductPage(glassesProductPage, glassesPrice);

        // 6. Add product to Cart again
        cartModal = glassesProductPage.clickAddToCart();
        verifyCartModal(cartModal, 1, glassesPrice);
        homePage.verifyItemsAmountOnCartIcon("1");

        // 7. Minimize the Cart
        cartModal.minimizeCartModal();
        verifyGlassesProductPage(glassesProductPage, glassesPrice);

        // 8. Go back to main STORES gallery
        shopPage = glassesProductPage.clickStoresLink();
        verifyShopPage(shopPage);

        // 9. Go to Bag of Items and expect glasses items
        cartModal = shopPage.clickCartButton();
        verifyCartModal(cartModal, 1, glassesPrice);
        homePage.verifyItemsAmountOnCartIcon("1");

        // 10. Minimize the Cart
        cartModal.minimizeCartModal();
        verifyShopPage(shopPage);

        String scarfPrice = shopPage.takeScarfPrice();
        Float scarfPriceFloat = Float.parseFloat(scarfPrice.replaceAll("[$]", ""));

        // 11. Select product SCARF from the gallery shown
        ScarfProductPage scarfProductPage = shopPage.clickScarfLabel();
        verifyScarfProductPage(scarfProductPage, scarfPrice);

        Float totalPriceFloat = glassesPriceFloat + scarfPriceFloat;
        String totalPrice = Float.toString(totalPriceFloat);
        totalPrice = "$" + totalPrice + "0";

        // 12. Add item to Cart
        cartModal = scarfProductPage.clickAddToCart();
        verifyCartModal(cartModal, 2, totalPrice);
        homePage.verifyItemsAmountOnCartIcon("2");

        // 13. Go to Cart View
        CartPage cartPage = cartModal.clickViewCart();

        // 14. Change quantity of glasses to 3
        cartPage = cartPage.updateProductQuantity("Premium Glasses", "3");
        homePage.waitForItemsAmountUpdated("4");
        homePage.verifyItemsAmountOnCartIcon("4");

        // 15.	Remove Scarf from the Cart View
        cartPage.removeProduct("I'm a product");
        homePage.waitForItemsAmountUpdated("3");
        homePage.verifyItemsAmountOnCartIcon("3");
    }

    public void verifyShopPage(ShopPage shopPage) {
        shopPage.verifyHeaderIsDisplayed();
        shopPage.virifyProductsContainerIsDisplayed();
    }

    public void verifyGlassesProductPage(GlassesProductPage glassesProductPage, String glassesPrice) {
        glassesProductPage.verifyHeaderIsDisplayed();
        glassesProductPage.verifyProductPageElementsDisplayed();
        glassesProductPage.verifyProductName("Premium Glasses");
        glassesProductPage.verifyProductPrice(glassesPrice);
    }

    public void verifyScarfProductPage(ScarfProductPage scarfProductPage, String scarfPrice) {
        scarfProductPage.verifyHeaderIsDisplayed();
        scarfProductPage.verifyProductPageElementsDisplayed();
        scarfProductPage.verifyProductName("I'm a product");
        scarfProductPage.verifyProductPrice(scarfPrice);
    }

    public void verifyCartModal(CartModal cartModal, int itemsAmount, String totalPrice) {
        cartModal.verifyCartModalIsOpened();
        cartModal.verifyViewCartButtonDisplayed();
        cartModal.verifyItemsAmount(itemsAmount);
        cartModal.verifySubtotalPrice(totalPrice);
    }

    public void verifyEmptyCartModal(CartModal cartModal) {
        cartModal.verifyCartIsEmptyMessageIsDisplayed();
        cartModal.verifyCartModalIsOpened();
        cartModal.verifySubtotalPriceIsNotDisplayed();
        cartModal.verifyViewCartButtonIsNotDisplayed();
        cartModal.verifyItemsAmount(0);
    }
}
