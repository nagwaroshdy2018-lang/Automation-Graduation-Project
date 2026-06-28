package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** /product_details/<id> page. */
public class ProductDetailsPage extends BasePage {

    private static final By QUANTITY     = By.id("quantity");
    private static final By ADD_TO_CART  = By.cssSelector("button.cart");

    // Modal after Add to cart
    private static final By MODAL_VIEW_CART = By.xpath("//u[normalize-space()='View Cart']");

    public ProductDetailsPage(WebDriver driver) { super(driver); }

    @Step("Set product quantity to {0}")
    public ProductDetailsPage setQuantity(int qty) {
        type(QUANTITY, String.valueOf(qty));
        return this;
    }

    @Step("Click Add to cart from product details")
    public ProductDetailsPage addToCart() {
        click(ADD_TO_CART);
        return this;
    }

    @Step("Click View Cart on the modal")
    public CartPage viewCartFromModal() {
        click(MODAL_VIEW_CART);
        return new CartPage(driver);
    }
}