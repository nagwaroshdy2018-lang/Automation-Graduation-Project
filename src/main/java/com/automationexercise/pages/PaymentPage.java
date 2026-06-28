package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** /payment — card form + confirm. */
public class PaymentPage extends BasePage {

    private static final By NAME_ON_CARD = By.name("name_on_card");
    private static final By CARD_NUMBER  = By.name("card_number");
    private static final By CVC          = By.name("cvc");
    private static final By EXP_MONTH    = By.name("expiry_month");
    private static final By EXP_YEAR     = By.name("expiry_year");
    private static final By PAY_BUTTON   = By.id("submit");

    private static final By ORDER_PLACED_HEADER = By.xpath("//h2[contains(@data-qa,'order-placed')]/b | //b[normalize-space()='Order Placed!']");
    private static final By ORDER_SUCCESS_TEXT  = By.xpath("//p[contains(normalize-space(),'Congratulations! Your order has been confirmed') or contains(normalize-space(),'order has been placed')]");

    public PaymentPage(WebDriver driver) { super(driver); }

    @Step("Fill card details and confirm payment")
    public PaymentPage payAndConfirm(String name, String number, String cvc, String month, String year) {
        type(NAME_ON_CARD, name);
        type(CARD_NUMBER, number);
        type(CVC, cvc);
        type(EXP_MONTH, month);
        type(EXP_YEAR, year);
        click(PAY_BUTTON);
        return this;
    }

    public boolean isOrderPlaced() {
        return isVisible(ORDER_PLACED_HEADER) || isVisible(ORDER_SUCCESS_TEXT);
    }
}