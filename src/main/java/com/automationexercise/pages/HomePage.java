package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Top navigation bar + footer subscription.
 * Methods here are reachable from any page on the site.
 */
public class HomePage extends BasePage {

    // ---------- Top nav ----------
    private static final By SIGNUP_LOGIN_LINK = By.cssSelector("a[href='/login']");
    private static final By PRODUCTS_LINK     = By.cssSelector("a[href='/products']");
    private static final By CART_LINK         = By.cssSelector("a[href='/view_cart']");
    private static final By CONTACT_US_LINK   = By.cssSelector("a[href='/contact_us']");
    private static final By LOGOUT_LINK       = By.cssSelector("a[href='/logout']");
    private static final By DELETE_ACCOUNT    = By.cssSelector("a[href='/delete_account']");
    private static final By LOGGED_IN_AS      = By.xpath("//a[contains(normalize-space(),'Logged in as')]");

    // ---------- Footer subscription ----------
    private static final By SUBSCRIBE_EMAIL  = By.id("susbscribe_email");   // sic - typo in site
    private static final By SUBSCRIBE_BTN    = By.id("subscribe");
    private static final By SUBSCRIBE_OK     = By.xpath("//div[@id='success-subscribe']//div[contains(@class,'alert-success')]");

    public HomePage(WebDriver driver) { super(driver); }

    // ---------- Nav actions ----------
    @Step("Go to Signup / Login page")
    public LoginPage goToLogin() {
        click(SIGNUP_LOGIN_LINK);
        return new LoginPage(driver);
    }

    @Step("Go to Products page")
    public ProductsPage goToProducts() {
        click(PRODUCTS_LINK);
        return new ProductsPage(driver);
    }

    @Step("Go to Cart page")
    public CartPage goToCart() {
        click(CART_LINK);
        return new CartPage(driver);
    }

    @Step("Go to Contact Us page")
    public ContactUsPage goToContactUs() {
        click(CONTACT_US_LINK);
        return new ContactUsPage(driver);
    }

    @Step("Logout")
    public LoginPage logout() {
        click(LOGOUT_LINK);
        return new LoginPage(driver);
    }

    @Step("Delete account")
    public AccountDeletedPage deleteAccount() {
        click(DELETE_ACCOUNT);
        return new AccountDeletedPage(driver);
    }

    // ---------- Logged-in checks ----------
    @Step("Check 'Logged in as <name>' is visible")
    public boolean isLoggedIn() {
        return isVisible(LOGGED_IN_AS);
    }

    @Step("Get 'Logged in as' text")
    public String getLoggedInAsText() {
        return textOf(LOGGED_IN_AS);
    }

    // ---------- Footer subscription ----------
    @Step("Subscribe to newsletter with email: {0}")
    public HomePage subscribe(String email) {
        scrollToBottom();
        type(SUBSCRIBE_EMAIL, email);
        click(SUBSCRIBE_BTN);
        return this;
    }

    @Step("Get HTML5 validation message for subscribe email field")
    public String getSubscribeEmailValidation() {
        return html5Validation(SUBSCRIBE_EMAIL);
    }

    @Step("Check subscription success message")
    public boolean isSubscriptionSuccess() {
        return isVisible(SUBSCRIBE_OK);
    }
}