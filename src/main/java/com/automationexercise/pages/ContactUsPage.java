package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** /contact_us page. */
public class ContactUsPage extends BasePage {

    private static final By PAGE_HEADER = By.xpath(
            "//h2[contains(translate(normalize-space()," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," +
                    "'get in touch')]"
    );

    private static final By NAME    = By.cssSelector("input[data-qa='name']");
    private static final By EMAIL   = By.cssSelector("input[data-qa='email']");
    private static final By SUBJECT = By.cssSelector("input[data-qa='subject']");
    private static final By MESSAGE = By.cssSelector("textarea[data-qa='message']");
    private static final By SUBMIT  = By.cssSelector("input[data-qa='submit-button']");
    private static final By SUCCESS = By.cssSelector(".status.alert.alert-success");

    public ContactUsPage(WebDriver driver) { super(driver); }

    public boolean isPageLoaded() { return isVisible(PAGE_HEADER); }

    @Step("Fill contact form: {0}, {1}, {2}")
    public ContactUsPage fillForm(String name, String email, String subject, String message) {
        scrollTo(NAME);
        type(NAME, name);
        type(EMAIL, email);
        type(SUBJECT, subject);
        type(MESSAGE, message);
        return this;
    }

    @Step("Click Submit on contact form")
    public ContactUsPage clickSubmit() {
        scrollTo(SUBMIT);
        click(SUBMIT);
        return this;
    }

    @Step("Accept browser confirmation alert")
    public ContactUsPage acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return this;
    }

    public boolean isSuccessDisplayed() { return isVisible(SUCCESS); }
    public String  getSuccessText()     { return textOf(SUCCESS); }

    @Step("Get HTML5 validation for the Name field (first required field)")
    public String getNameValidation() {
        WebElement nameField = waitPresent(NAME);
        return (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", nameField);
    }

    /** Checks if the Name field has the 'required' attribute and is empty (HTML5 invalid). */
    @Step("Check if Name field is HTML5 invalid (empty + required)")
    public boolean isNameFieldInvalid() {
        WebElement nameField = waitPresent(NAME);
        Boolean result = (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return !arguments[0].checkValidity();", nameField);
        return result != null && result;
    }
}