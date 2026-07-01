package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By LOGIN_SECTION  = By.xpath("//h2[normalize-space()='Login to your account']");
    private static final By SIGNUP_SECTION = By.xpath("//h2[normalize-space()='New User Signup!']");

    private static final By LOGIN_EMAIL    = By.cssSelector("input[data-qa='login-email']");
    private static final By LOGIN_PASSWORD = By.cssSelector("input[data-qa='login-password']");
    private static final By LOGIN_BUTTON   = By.cssSelector("button[data-qa='login-button']");
    private static final By LOGIN_ERROR    = By.xpath("//p[normalize-space()='Your email or password is incorrect!']");

    private static final By SIGNUP_NAME    = By.cssSelector("input[data-qa='signup-name']");
    private static final By SIGNUP_EMAIL   = By.cssSelector("input[data-qa='signup-email']");
    private static final By SIGNUP_BUTTON  = By.cssSelector("button[data-qa='signup-button']");
    private static final By SIGNUP_ERROR   = By.xpath("//p[normalize-space()='Email Address already exist!']");

    public LoginPage(WebDriver driver) { super(driver); }

    public boolean isLoginSectionVisible()  { return isVisible(LOGIN_SECTION); }
    public boolean isSignupSectionVisible() { return isVisible(SIGNUP_SECTION); }

    @Step("Login with email: {0}")
    public LoginPage login(String email, String password) {
        type(LOGIN_EMAIL, email);
        type(LOGIN_PASSWORD, password);
        click(LOGIN_BUTTON);
        return this;
    }

    // NEW for TC_I_07
    @Step("Click Login with empty email, password: {0}")
    public LoginPage loginWithEmptyEmail(String password) {
        try { driver.findElement(LOGIN_EMAIL).clear(); } catch (Exception ignored) {}
        type(LOGIN_PASSWORD, password);
        click(LOGIN_BUTTON);
        return this;
    }

    // NEW for TC_I_08
    @Step("Click Login with email: {0} and empty password")
    public LoginPage loginWithEmptyPassword(String email) {
        type(LOGIN_EMAIL, email);
        try { driver.findElement(LOGIN_PASSWORD).clear(); } catch (Exception ignored) {}
        click(LOGIN_BUTTON);
        return this;
    }

    public boolean isLoginErrorDisplayed() { return isVisible(LOGIN_ERROR); }
    public String  getLoginErrorMessage()  { return textOf(LOGIN_ERROR); }

    // NEW HTML5 validation getters
    @Step("Get HTML5 validation message for login email field")
    public String getLoginEmailValidation() { return html5Validation(LOGIN_EMAIL); }

    @Step("Get HTML5 validation message for login password field")
    public String getLoginPasswordValidation() { return html5Validation(LOGIN_PASSWORD); }

    // ---------- Signup ----------
    @Step("Signup with name: {0}, email: {1}")
    public LoginPage signup(String name, String email) {
        type(SIGNUP_NAME, name);
        type(SIGNUP_EMAIL, email);
        click(SIGNUP_BUTTON);
        return this;
    }

    // NEW for TC_I_11
    @Step("Signup with name: {0} and invalid email: {1}")
    public LoginPage signupWithInvalidEmail(String name, String invalidEmail) {
        type(SIGNUP_NAME, name);
        type(SIGNUP_EMAIL, invalidEmail);
        click(SIGNUP_BUTTON);
        return this;
    }

    // NEW for TC_I_12
    @Step("Click Signup with empty name, email: {0}")
    public LoginPage signupWithEmptyName(String email) {
        try { driver.findElement(SIGNUP_NAME).clear(); } catch (Exception ignored) {}
        type(SIGNUP_EMAIL, email);
        click(SIGNUP_BUTTON);
        return this;
    }

    public SignupPage goToSignupForm() { return new SignupPage(driver); }

    public boolean isSignupErrorDisplayed() { return isVisible(SIGNUP_ERROR); }
    public String  getSignupErrorMessage()  { return textOf(SIGNUP_ERROR); }

    // NEW HTML5 validation getters
    @Step("Get HTML5 validation message for signup name field")
    public String getSignupNameValidation() { return html5Validation(SIGNUP_NAME); }

    @Step("Get HTML5 validation message for signup email field")
    public String getSignupEmailValidation() { return html5Validation(SIGNUP_EMAIL); }
}