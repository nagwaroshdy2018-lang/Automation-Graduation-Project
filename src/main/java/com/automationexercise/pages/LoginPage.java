package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * /login page — has both "Login to your account" and "New User Signup!" sections.
 */
public class LoginPage extends BasePage {

    // Section headers
    private static final By LOGIN_SECTION  = By.xpath("//h2[normalize-space()='Login to your account']");
    private static final By SIGNUP_SECTION = By.xpath("//h2[normalize-space()='New User Signup!']");

    // Login form
    private static final By LOGIN_EMAIL    = By.cssSelector("input[data-qa='login-email']");
    private static final By LOGIN_PASSWORD = By.cssSelector("input[data-qa='login-password']");
    private static final By LOGIN_BUTTON   = By.cssSelector("button[data-qa='login-button']");
    private static final By LOGIN_ERROR    = By.xpath("//p[normalize-space()='Your email or password is incorrect!']");

    // Signup form
    private static final By SIGNUP_NAME    = By.cssSelector("input[data-qa='signup-name']");
    private static final By SIGNUP_EMAIL   = By.cssSelector("input[data-qa='signup-email']");
    private static final By SIGNUP_BUTTON  = By.cssSelector("button[data-qa='signup-button']");
    private static final By SIGNUP_ERROR   = By.xpath("//p[normalize-space()='Email Address already exist!']");

    public LoginPage(WebDriver driver) { super(driver); }

    public boolean isLoginSectionVisible()  { return isVisible(LOGIN_SECTION); }
    public boolean isSignupSectionVisible() { return isVisible(SIGNUP_SECTION); }

    // ---------- Login ----------
    @Step("Login with email: {0}")
    public LoginPage login(String email, String password) {
        type(LOGIN_EMAIL, email);
        type(LOGIN_PASSWORD, password);
        click(LOGIN_BUTTON);
        return this;
    }

    public boolean isLoginErrorDisplayed() { return isVisible(LOGIN_ERROR); }
    public String  getLoginErrorMessage()  { return textOf(LOGIN_ERROR); }

    // ---------- Signup (step 1) ----------
    @Step("Signup with name: {0}, email: {1}")
    public LoginPage signup(String name, String email) {
        type(SIGNUP_NAME, name);
        type(SIGNUP_EMAIL, email);
        click(SIGNUP_BUTTON);
        return this;
    }

    public SignupPage goToSignupForm() {
        return new SignupPage(driver);
    }

    public boolean isSignupErrorDisplayed() { return isVisible(SIGNUP_ERROR); }
    public String  getSignupErrorMessage()  { return textOf(SIGNUP_ERROR); }
}