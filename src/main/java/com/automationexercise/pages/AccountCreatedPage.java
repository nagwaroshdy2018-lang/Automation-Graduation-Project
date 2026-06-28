package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Confirmation page after a successful signup. */
public class AccountCreatedPage extends BasePage {

    private static final By ACCOUNT_CREATED = By.cssSelector("h2[data-qa='account-created'] b");
    private static final By CONTINUE_BTN    = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) { super(driver); }

    public boolean isDisplayed()    { return isVisible(ACCOUNT_CREATED); }
    public String  getMessage()     { return textOf(ACCOUNT_CREATED); }

    @Step("Click Continue after account creation")
    public HomePage clickContinue() {
        click(CONTINUE_BTN);
        return new HomePage(driver);
    }
}