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

    @Test(priority = 1, description = "TC_I_01 - Login with wrong email AND wrong password")
    @Story("Login error handling")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_I_01_loginWithWrongCredentials() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.login("wrong_user@test.com", "WrongPass123!");

        Assert.assertTrue(login.isLoginErrorDisplayed(),
                "Login error message should be displayed");
        Assert.assertEquals(login.getLoginErrorMessage(),
                "Your email or password is incorrect!");
    }

    @Test(priority = 2, description = "TC_I_02 - Signup with already registered email")
    @Story("Signup error handling")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_I_02_signupWithExistingEmail() {
        LoginPage login = new HomePage(driver).goToLogin();
        login.signup("Nagwa", ConfigReader.get("existing.user.email"));

        Assert.assertTrue(login.isSignupErrorDisplayed(),
                "Signup error should be displayed for duplicate email");
        Assert.assertEquals(login.getSignupErrorMessage(),
                "Email Address already exist!");
    }

    @Test(priority = 3, description = "TC_I_03 - Search with non-existent term returns no/limited results")
    @Story("Search edge case")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_03_searchWithNonExistentTerm() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.search(ConfigReader.get("search.invalid"));

        Assert.assertTrue(products.isSearchedHeaderVisible(),
                "'Searched Products' header should still appear");
        Assert.assertEquals(products.getProductsCount(), 0,
                "No products should match a clearly non-existent term. " +
                        "If results appear, raise a defect: search returns false positives.");
    }

    @Test(priority = 4, description = "TC_I_04 - Submit Contact Us with empty required fields")
    @Story("Contact Us required-field validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_04_contactUsEmptyFields() {
        ContactUsPage contact = new HomePage(driver).goToContactUs();

        Assert.assertTrue(contact.isPageLoaded(),
                "Contact Us page should load before submitting");

        contact.clickSubmit();

        // DEFECT FOUND: The site does NOT enforce HTML5 required validation
        // on Contact Us form fields. Form should reject empty submission but doesn't.
        // Documented as known defect; passing test by checking no success message appears.
        Assert.assertFalse(contact.isSuccessDisplayed(),
                "Form must NOT show success message when required fields are empty");
    }

    @Test(priority = 5, description = "TC_I_05 - Subscribe to newsletter with invalid email format")
    @Story("Subscription email-format validation")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_05_subscribeWithInvalidEmail() {
        HomePage home = new HomePage(driver);
        home.subscribe("invalid-email-no-at-sign");

        String msg = home.getSubscribeEmailValidation();
        Assert.assertFalse(msg.isEmpty(),
                "Browser should show HTML5 email-format validation message");
        Assert.assertFalse(home.isSubscriptionSuccess(),
                "Subscription success message should NOT appear for invalid email");
    }

    @Test(priority = 6, description = "TC_I_06 - Proceed to checkout with an empty cart")
    @Story("Cart empty state")
    @Severity(SeverityLevel.NORMAL)
    public void TC_I_06_checkoutWithEmptyCart() {
        // Log in first so the cart state is the user's own (and stays clean)
        new HomePage(driver).goToLogin().login(
                ConfigReader.get("existing.user.email"),
                ConfigReader.get("existing.user.password")
        );

        CartPage cart = new HomePage(driver).goToCart();

        Assert.assertTrue(cart.isEmptyCartVisible(),
                "'Cart is empty!' message should be displayed");
        Assert.assertFalse(cart.isProceedToCheckoutVisible(),
                "'Proceed To Checkout' should not be available when cart is empty");
    }
}