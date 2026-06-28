package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** /view_cart page. */
public class CartPage extends BasePage {

    private static final By CART_ROWS         = By.cssSelector("#cart_info_table tbody tr");
    private static final By FIRST_QUANTITY    = By.cssSelector("#cart_info_table tbody tr:nth-child(1) .cart_quantity button");
    private static final By PROCEED_CHECKOUT  = By.cssSelector(".check_out");
    private static final By EMPTY_CART        = By.id("empty_cart");
    private static final By EMPTY_CART_TEXT   = By.xpath("//b[normalize-space()='Cart is empty!']");

    public CartPage(WebDriver driver) { super(driver); }

    @Step("Get cart item count")
    public int getItemCount() {
        return driver.findElements(CART_ROWS).size();
    }

    @Step("Get quantity of first cart item")
    public String getFirstItemQuantity() {
        return textOf(FIRST_QUANTITY);
    }

    public boolean isEmptyCartVisible() {
        return isVisible(EMPTY_CART) || isVisible(EMPTY_CART_TEXT);
    }

    public boolean isProceedToCheckoutVisible() {
        try {
            return driver.findElement(PROCEED_CHECKOUT).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click Proceed To Checkout")
    public CheckoutPage proceedToCheckout() {
        click(PROCEED_CHECKOUT);
        return new CheckoutPage(driver);
    }
}