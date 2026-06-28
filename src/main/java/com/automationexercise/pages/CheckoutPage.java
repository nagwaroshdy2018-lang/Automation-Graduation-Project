package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** /checkout page — address review, comment, Place Order. */
public class CheckoutPage extends BasePage {

    private static final By ADDRESS_DETAILS = By.id("address_delivery");
    private static final By REVIEW_HEADER   = By.xpath("//h2[normalize-space()='Review Your Order']");
    private static final By COMMENT         = By.cssSelector("textarea[name='message']");
    private static final By PLACE_ORDER     = By.xpath("//a[normalize-space()='Place Order']");

    public CheckoutPage(WebDriver driver) { super(driver); }

    public boolean isAddressVisible() { return isVisible(ADDRESS_DETAILS); }
    public boolean isReviewVisible()  { return isVisible(REVIEW_HEADER); }

    @Step("Add order comment: {0}")
    public CheckoutPage addComment(String comment) {
        scrollTo(COMMENT);
        type(COMMENT, comment);
        return this;
    }

    @Step("Click Place Order")
    public PaymentPage placeOrder() {
        click(PLACE_ORDER);
        return new PaymentPage(driver);
    }
}