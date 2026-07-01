package com.automationexercise.tests;

import base.BaseTest;
import com.automationexercise.pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

@Epic("Automation Exercise")
@Feature("Negative Scenarios")
public class InvalidTests extends BaseTest {

    // ============ EXISTING 6 TESTS ============

    @Test(priority = 1, description = "TC_I_01 - Login with wrong email AND wrong password")
    @Story("Login error handling")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_I_01_loginWithWrongCredentials() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.login("wrong_user@test.com", "WrongPass123!");
        Assert.assertTrue(login.isLoginErrorDisplayed());
        Assert.assertEquals(login.getLoginErrorMessage(),
                "Your email or password is incorrect!");
    }

    @Test(priority = 2, description = "TC_I_02 - Signup with already registered email")
    @Story("Signup error handling")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_I_02_signupWithExistingEmail() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.signup("Nagwa", ConfigReader.get("existing.user.email"));
        Assert.assertTrue(login.isSignupErrorDisplayed());
        Assert.assertEquals(login.getSignupErrorMessage(),
                "Email Address already exist!");
    }

    @Test(priority = 3, description = "TC_I_03 - Search with non-existent term")
    @Story("Search edge case")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_03_searchWithNonExistentTerm() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.search(ConfigReader.get("search.invalid"));
        Assert.assertTrue(products.isSearchedHeaderVisible());
        Assert.assertEquals(products.getProductsCount(), 0);
    }

    @Test(priority = 4, description = "TC_I_04 - Submit Contact Us with empty required fields")
    @Story("Contact Us validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_04_contactUsEmptyFields() {
        ContactUsPage contact = new HomePage(driver).goToContactUs();
        contact.clickSubmit();
        Assert.assertFalse(contact.isSuccessDisplayed(),
                "Form must NOT show success when required fields are empty");
    }

    @Test(priority = 5, description = "TC_I_05 - Subscribe with invalid email format")
    @Story("Subscription validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_05_subscribeWithInvalidEmail() {
        HomePage home = new HomePage(driver);
        home.subscribe("invalid-email-no-at-sign");

        String msg = home.getSubscribeEmailValidation();
        Assert.assertFalse(msg.isEmpty(),
                "Browser should show HTML5 email-format validation");
        Assert.assertFalse(home.isSubscriptionSuccess());
    }

    @Test(priority = 6, description = "TC_I_06 - Proceed to checkout with empty cart")
    @Story("Cart empty state")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_06_checkoutWithEmptyCart() {
        new HomePage(driver).goToLogin().login(
                ConfigReader.get("existing.user.email"),
                ConfigReader.get("existing.user.password")
        );
        CartPage cart = new HomePage(driver).goToCart();
        Assert.assertTrue(cart.isEmptyCartVisible());
        Assert.assertFalse(cart.isProceedToCheckoutVisible());
    }

    // ============ NEW 10 TESTS ============

    @Test(priority = 7, description = "TC_I_07 - Login with empty email field")
    @Story("Login required-field validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_07_loginWithEmptyEmail() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.loginWithEmptyEmail("SomePass123!");

        String msg = login.getLoginEmailValidation();
        Assert.assertFalse(msg.isEmpty(),
                "Browser should show HTML5 'required' validation on email");
        Assert.assertFalse(login.isLoginErrorDisplayed(),
                "Server error should NOT appear — form must not submit");
    }

    @Test(priority = 8, description = "TC_I_08 - Login with empty password field")
    @Story("Login required-field validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_08_loginWithEmptyPassword() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.loginWithEmptyPassword(ConfigReader.get("existing.user.email"));

        String msg = login.getLoginPasswordValidation();
        Assert.assertFalse(msg.isEmpty(),
                "Browser should show HTML5 'required' validation on password");
        Assert.assertFalse(login.isLoginErrorDisplayed());
    }

    @Test(priority = 9, description = "TC_I_09 - Login with valid email format not registered")
    @Story("Login error handling")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_I_09_loginWithUnregisteredEmail() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.login("never_registered_9999@test.com", "AnyPass123!");
        Assert.assertTrue(login.isLoginErrorDisplayed());
        Assert.assertEquals(login.getLoginErrorMessage(),
                "Your email or password is incorrect!");
    }

    @Test(priority = 10, description = "TC_I_10 - Login with registered email + wrong password")
    @Story("Login error handling")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_I_10_loginWithWrongPassword() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.login(ConfigReader.get("existing.user.email"), "DefinitelyWrong!");
        Assert.assertTrue(login.isLoginErrorDisplayed());
        Assert.assertEquals(login.getLoginErrorMessage(),
                "Your email or password is incorrect!");
    }

    @Test(priority = 11, description = "TC_I_11 - Signup with invalid email format (no @)")
    @Story("Signup email-format validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_11_signupWithInvalidEmail() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.signupWithInvalidEmail("Nagwa", "nagwaEmailNoAtSign");

        String msg = login.getSignupEmailValidation();
        Assert.assertFalse(msg.isEmpty(),
                "Browser should show HTML5 email-format validation");
    }

    @Test(priority = 12, description = "TC_I_12 - Signup with empty name field")
    @Story("Signup required-field validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_12_signupWithEmptyName() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.signupWithEmptyName("newuser@test.com");

        String msg = login.getSignupNameValidation();
        Assert.assertFalse(msg.isEmpty(),
                "Browser should show HTML5 'required' validation on signup name");
    }

    @Test(priority = 13, description = "TC_I_13 - Contact Us with invalid email format")
    @Story("Contact Us email-format validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_13_contactUsInvalidEmail() {
        ContactUsPage contact = new HomePage(driver).goToContactUs();
        contact.fillForm("Nagwa", "invalidEmailNoAt", "Inquiry", "Test message");
        contact.clickSubmit();
        Assert.assertFalse(contact.isSuccessDisplayed(),
                "Contact Us success message must NOT appear with invalid email");
    }

    @Test(priority = 14, description = "TC_I_14 - Subscribe with empty email")
    @Story("Subscription required-field validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_14_subscribeWithEmptyEmail() {
        HomePage home = new HomePage(driver);
        home.subscribeEmpty();

        String msg = home.getSubscribeEmailValidation();
        Assert.assertFalse(msg.isEmpty(),
                "Browser should show HTML5 'required' validation on subscribe email");
        Assert.assertFalse(home.isSubscriptionSuccess());
    }

    @Test(priority = 15, description = "TC_I_15 - Proceed to checkout without login")
    @Story("Checkout - guest restriction")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_I_15_checkoutWithoutLogin() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.addFirstProductToCart();
        CartPage cart = products.viewCartFromModal();

        cart.clickProceedAsGuest();

        Assert.assertTrue(cart.isRegisterLoginModalVisible(),
                "'Register / Login account to proceed on checkout' modal should be displayed");
        Assert.assertTrue(driver.getCurrentUrl().contains("/view_cart"),
                "User should still be on /view_cart");
    }

    @Test(priority = 16, description = "TC_I_16 - Search with only special characters")
    @Story("Search edge case")
    @Severity(SeverityLevel.MINOR)
    public void TC_I_16_searchWithSpecialCharacters() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.search("!@#$%^&*()");

        Assert.assertTrue(products.isSearchedHeaderVisible(),
                "'Searched Products' header should appear");
        Assert.assertEquals(products.getProductsCount(), 0,
                "Special-character-only search should return zero products");
    }
}
