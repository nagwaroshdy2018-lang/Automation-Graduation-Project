package com.automationexercise.tests;

import base.BaseTest;
import com.automationexercise.pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.TestDataGenerator;

@Epic("Automation Exercise")
@Feature("Positive Scenarios")
public class ValidTests extends BaseTest {

    @Test(priority = 1, description = "TC_V_01 - Successful login with valid registered credentials")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_V_01_loginWithValidCredentials() {
        HomePage home = new HomePage(driver);
        home.goToLogin().login(
                ConfigReader.get("existing.user.email"),
                ConfigReader.get("existing.user.password")
        );

        Assert.assertTrue(home.isLoggedIn(),
                "'Logged in as <name>' should be visible after valid login");
        Assert.assertTrue(home.getLoggedInAsText()
                        .toLowerCase()
                        .contains(ConfigReader.get("existing.user.name").toLowerCase()),
                "Nav text should contain the configured user name");
    }

    @Test(priority = 2, description = "TC_V_02 - Successful signup with valid new email")
    @Story("Signup")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_V_02_signupNewUser() {
        String name  = TestDataGenerator.uniqueName();
        String email = TestDataGenerator.uniqueEmail();

        HomePage home = new HomePage(driver);
        home.goToLogin().signup(name, email);

        AccountCreatedPage created = new SignupPage(driver)
                .fillAccountInfo("NAgwa98*#")
                .submit();

        Assert.assertTrue(created.isDisplayed(),
                "'ACCOUNT CREATED!' should be visible after signup");
        Assert.assertEquals(created.getMessage().toUpperCase(), "ACCOUNT CREATED!",
                "Confirmation text should be 'ACCOUNT CREATED!'");
    }

    @Test(priority = 3, description = "TC_V_03 - Products page loads with product list")
    @Story("Products")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_03_productsPageLoads() {
        ProductsPage products = new HomePage(driver).goToProducts();

        Assert.assertTrue(driver.getCurrentUrl().contains("/products"),
                "URL should contain /products");
        Assert.assertTrue(products.isAllProductsHeaderVisible(),
                "'All Products' header should be visible");
        Assert.assertTrue(products.getProductsCount() > 0,
                "At least one product card should be rendered");
    }

    @Test(priority = 4, description = "TC_V_04 - Search for an existing product returns matching results")
    @Story("Search")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_04_searchExistingProduct() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.search(ConfigReader.get("search.valid"));

        Assert.assertTrue(products.isSearchedHeaderVisible(),
                "'Searched Products' header should be visible");
        Assert.assertTrue(products.getProductsCount() > 0,
                "Search should return at least one product for term 'dress'");
    }

    @Test(priority = 5, description = "TC_V_05 - Add a product to the cart and verify it appears in cart")
    @Story("Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_V_05_addProductToCart() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.addFirstProductToCart();
        CartPage cart = products.viewCartFromModal();

        Assert.assertTrue(driver.getCurrentUrl().contains("/view_cart"),
                "Should be on /view_cart");
        Assert.assertEquals(cart.getItemCount(), 1,
                "Cart should contain exactly 1 item after adding one product");
        Assert.assertEquals(cart.getFirstItemQuantity(), "1",
                "Default quantity should be 1");
    }

    @Test(priority = 6, description = "TC_V_06 - Submit Contact Us form with all valid fields")
    @Story("Contact Us")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_06_contactUsFormSuccess() {
        ContactUsPage contact = new HomePage(driver).goToContactUs();
        contact.fillForm("Nagwa", "nagwa@test.com", "Inquiry", "Test inquiry message")
                .clickSubmit()
                .acceptAlert();

        Assert.assertTrue(contact.isSuccessDisplayed(),
                "Success banner should be displayed");
        Assert.assertTrue(contact.getSuccessText().toLowerCase().contains("success"),
                "Success message should contain 'success'");
    }
}