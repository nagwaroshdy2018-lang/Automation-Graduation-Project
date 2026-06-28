package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Confirmation page after deleting an account. */
public class AccountDeletedPage extends BasePage {

    private static final By ACCOUNT_DELETED = By.cssSelector("h2[data-qa='account-deleted'] b");
    private static final By CONTINUE_BTN    = By.cssSelector("a[data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver) { super(driver); }

    public boolean isDisplayed() { return isVisible(ACCOUNT_DELETED); }
    public String  getMessage()  { return textOf(ACCOUNT_DELETED); }

    @Step("Click Continue after account deletion")
    public HomePage clickContinue() {
        click(CONTINUE_BTN);
        return new HomePage(driver);
    }
}