package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * /signup page — full account information form.
 * Reached after clicking "Signup" on LoginPage with a fresh email.
 */
public class SignupPage extends BasePage {

    private static final By TITLE_MR    = By.id("id_gender1");
    private static final By PASSWORD    = By.id("password");
    private static final By DAYS        = By.id("days");
    private static final By MONTHS      = By.id("months");
    private static final By YEARS       = By.id("years");
    private static final By NEWSLETTER  = By.id("newsletter");
    private static final By OPTIN       = By.id("optin");
    private static final By FIRST_NAME  = By.id("first_name");
    private static final By LAST_NAME   = By.id("last_name");
    private static final By COMPANY     = By.id("company");
    private static final By ADDRESS1    = By.id("address1");
    private static final By ADDRESS2    = By.id("address2");
    private static final By COUNTRY     = By.id("country");
    private static final By STATE       = By.id("state");
    private static final By CITY        = By.id("city");
    private static final By ZIPCODE     = By.id("zipcode");
    private static final By MOBILE      = By.id("mobile_number");
    private static final By CREATE_ACCOUNT = By.cssSelector("button[data-qa='create-account']");

    public SignupPage(WebDriver driver) { super(driver); }

    @Step("Fill the full account information form")
    public SignupPage fillAccountInfo(String password) {
        click(TITLE_MR);
        type(PASSWORD, password);

        new Select(driver.findElement(DAYS)).selectByValue("15");
        new Select(driver.findElement(MONTHS)).selectByValue("6");
        new Select(driver.findElement(YEARS)).selectByValue("1998");

        click(NEWSLETTER);

        type(FIRST_NAME, "Nagwa");
        type(LAST_NAME, "Roshdy");
        type(COMPANY, "QA Tester");
        type(ADDRESS1, "123 Main St");
        type(ADDRESS2, "Apt 4B");

        new Select(driver.findElement(COUNTRY)).selectByVisibleText("United States");

        type(STATE, "California");
        type(CITY, "San Francisco");
        type(ZIPCODE, "94016");
        type(MOBILE, "+1234567890");
        return this;
    }

    @Step("Submit account creation form")
    public AccountCreatedPage submit() {
        click(CREATE_ACCOUNT);
        return new AccountCreatedPage(driver);
    }
}