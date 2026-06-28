package com.automationexercise.tests;

import base.BaseTest;
import com.automationexercise.pages.*;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.TestDataGenerator;

@Epic("Automation Exercise")
@Feature("End-to-End Flows")
public class E2ETests extends BaseTest {

    @Test(priority = 1, description = "TC_E2E_01 - Register → Login → Verify logged in → Delete account")
    @Story("Full account lifecycle")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_E2E_01_accountLifecycle() {
        String name  = TestDataGenerator.uniqueName();
        String email = TestDataGenerator.uniqueEmail();
        String pwd   = "NAgwa98*#";

        // 1. Register
        HomePage home = new HomePage(driver);
        LoginPage login = home.goToLogin();
        login.signup(name, email);

        AccountCreatedPage created = new SignupPage(driver)
                .fillAccountInfo(pwd)
                .submit();
        Assert.assertTrue(created.isDisplayed(), "ACCOUNT CREATED! should be visible");

        // 2. Continue → already logged in
        home = created.clickContinue();
        Assert.assertTrue(home.isLoggedIn(), "User should be logged in after signup");
        Assert.assertTrue(home.getLoggedInAsText().contains(name),
                "Logged-in nav text should contain the registered name");

        // 3. Logout
        login = home.logout();
        Assert.assertTrue(login.isLoginSectionVisible(), "Should land back on /login");

        // 4. Login again
        login.login(email, pwd);
        home = new HomePage(driver);
        Assert.assertTrue(home.isLoggedIn(), "Should be logged in again");

        // 5. Delete account
        AccountDeletedPage deleted = home.deleteAccount();
        Assert.assertTrue(deleted.isDisplayed(),
                "'ACCOUNT DELETED!' confirmation should be visible");

        deleted.clickContinue();
    }

    @Test(priority = 2, description = "TC_E2E_02 - Login → Add to cart → Checkout → Place order")
    @Story("Checkout flow")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_E2E_02_checkoutFlow() {
        // 1. Login
        HomePage home = new HomePage(driver);
        home.goToLogin().login(
                ConfigReader.get("existing.user.email"),
                ConfigReader.get("existing.user.password")
        );
        Assert.assertTrue(home.isLoggedIn(), "Login must succeed before checkout flow");

        // 2. Products → Add first to cart
        ProductsPage products = home.goToProducts();
        Assert.assertTrue(products.isAllProductsHeaderVisible(),
                "All Products header must be visible");
        products.addFirstProductToCart();

        // 3. View Cart
        CartPage cart = products.viewCartFromModal();
        Assert.assertTrue(cart.getItemCount() > 0, "Cart should not be empty");

        // 4. Proceed to checkout
        CheckoutPage checkout = cart.proceedToCheckout();
        Assert.assertTrue(checkout.isAddressVisible(),
                "Delivery address should be shown on checkout");

        // 5. Place order
        PaymentPage payment = checkout
                .addComment("Test order comment")
                .placeOrder();

        // 6. Pay and confirm
        payment.payAndConfirm(
                ConfigReader.get("card.name"),
                ConfigReader.get("card.number"),
                ConfigReader.get("card.cvc"),
                ConfigReader.get("card.expiry.month"),
                ConfigReader.get("card.expiry.year")
        );

        Assert.assertTrue(payment.isOrderPlaced(),
                "Order placed / confirmation message should be displayed");
    }

    @Test(priority = 3, description = "TC_E2E_03 - Login → Search → View → Add to cart (qty=2) → Logout")
    @Story("Search + cart flow")
    @Severity(SeverityLevel.NORMAL)
    public void TC_E2E_03_searchAndCart() {
        // 1. Login
        HomePage home = new HomePage(driver);
        home.goToLogin().login(
                ConfigReader.get("existing.user.email"),
                ConfigReader.get("existing.user.password")
        );
        Assert.assertTrue(home.isLoggedIn());

        // 2. Search
        ProductsPage products = home.goToProducts();
        products.search(ConfigReader.get("search.valid"));
        Assert.assertTrue(products.isSearchedHeaderVisible(),
                "'Searched Products' header should be visible");
        Assert.assertTrue(products.getProductsCount() > 0,
                "Search results should not be empty for term 'dress'");

        // 3. View first product → set quantity 2 → add to cart
        ProductDetailsPage details = products.viewFirstProduct();
        details.setQuantity(2).addToCart();

        // 4. Open cart and verify quantity
        CartPage cart = details.viewCartFromModal();
        Assert.assertEquals(cart.getFirstItemQuantity(), "2",
                "Quantity in cart should be 2");

        // 5. Logout
        LoginPage login = home.logout();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),
                "Should redirect to /login after logout");
        Assert.assertTrue(login.isLoginSectionVisible(),
                "Login section should be visible after logout");
    }

    @Test(priority = 4, description = "TC_E2E_04 - Register → Subscribe → Submit Contact Us")
    @Story("Engagement flow")
    @Severity(SeverityLevel.NORMAL)
    public void TC_E2E_04_registerSubscribeContact() {
        String name  = TestDataGenerator.uniqueName();
        String email = TestDataGenerator.uniqueEmail();

        // 1. Register
        HomePage home = new HomePage(driver);
        home.goToLogin()
                .signup(name, email);
        new SignupPage(driver).fillAccountInfo("NAgwa98*#").submit().clickContinue();

        // 2. Subscribe from footer
        home.subscribe(email);
        Assert.assertTrue(home.isSubscriptionSuccess(),
                "Subscription success message should be visible");

        // 3. Contact Us
        ContactUsPage contact = home.goToContactUs();
        contact.fillForm(name, email, "Inquiry", "Test inquiry message")
                .clickSubmit()
                .acceptAlert();

        Assert.assertTrue(contact.isSuccessDisplayed(),
                "Contact form success banner should be displayed");
        Assert.assertTrue(contact.getSuccessText().toLowerCase().contains("success"),
                "Success message text should contain 'success'");
    }
}