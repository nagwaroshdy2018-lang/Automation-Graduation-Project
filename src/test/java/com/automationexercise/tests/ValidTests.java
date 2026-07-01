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

    // ============ EXISTING 6 TESTS ============

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
                .fillAccountInfo("Nagwa98*#.")
                .submit();

        Assert.assertTrue(created.isDisplayed(),
                "'ACCOUNT CREATED!' should be visible after signup");
    }

    @Test(priority = 3, description = "TC_V_03 - Products page loads with product list")
    @Story("Products")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_03_productsPageLoads() {
        ProductsPage products = new HomePage(driver).goToProducts();
        Assert.assertTrue(driver.getCurrentUrl().contains("/products"));
        Assert.assertTrue(products.isAllProductsHeaderVisible());
        Assert.assertTrue(products.getProductsCount() > 0);
    }

    @Test(priority = 4, description = "TC_V_04 - Search for an existing product")
    @Story("Search")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_04_searchExistingProduct() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.search(ConfigReader.get("search.valid"));
        Assert.assertTrue(products.isSearchedHeaderVisible());
        Assert.assertTrue(products.getProductsCount() > 0);
    }

    @Test(priority = 5, description = "TC_V_05 - Add a product to the cart")
    @Story("Cart")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_V_05_addProductToCart() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.addFirstProductToCart();
        CartPage cart = products.viewCartFromModal();
        Assert.assertEquals(cart.getItemCount(), 1);
        Assert.assertEquals(cart.getFirstItemQuantity(), "1");
    }

    @Test(priority = 6, description = "TC_V_06 - Submit Contact Us form with valid fields")
    @Story("Contact Us")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_06_contactUsFormSuccess() {
        ContactUsPage contact = new HomePage(driver).goToContactUs();
        contact.fillForm("Nagwa", "nagwa@test.com", "Inquiry", "Test inquiry message")
                .clickSubmit()
                .acceptAlert();
        Assert.assertTrue(contact.isSuccessDisplayed());
    }

    // ============ NEW 5 TESTS ============

    @Test(priority = 7, description = "TC_V_07 - Filter products by Women's Dress category")
    @Story("Products - Category filter")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_07_filterByWomenDressCategory() {
        ProductsPage products = new HomePage(driver).goToProducts();
        CategoryProductsPage category = products.filterByWomenDress();

        Assert.assertTrue(driver.getCurrentUrl().contains("/category_products/1"),
                "URL should contain /category_products/1 (Women > Dress)");
        Assert.assertTrue(category.isHeaderVisible(),
                "Category header should be visible");
        Assert.assertTrue(category.getHeaderText().toLowerCase().contains("dress"),
                "Header should mention 'DRESS' — actual: " + category.getHeaderText());
        Assert.assertTrue(category.getProductsCount() > 0,
                "At least one product should appear");
    }

    @Test(priority = 8, description = "TC_V_08 - Filter products by brand (Polo)")
    @Story("Products - Brand filter")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_08_filterByPoloBrand() {
        ProductsPage products = new HomePage(driver).goToProducts();
        CategoryProductsPage brandPage = products.filterByBrandPolo();

        Assert.assertTrue(driver.getCurrentUrl().contains("/brand_products/Polo"),
                "URL should contain /brand_products/Polo");
        Assert.assertTrue(brandPage.isHeaderVisible(),
                "Brand header should be visible");
        Assert.assertTrue(brandPage.getHeaderText().toLowerCase().contains("polo"),
                "Header should mention 'POLO' — actual: " + brandPage.getHeaderText());
        Assert.assertTrue(brandPage.getProductsCount() > 0,
                "At least one product should appear for Polo");
    }

    @Test(priority = 9, description = "TC_V_09 - Add product with quantity 5 from Product Details")
    @Story("Cart - quantity")
    @Severity(SeverityLevel.NORMAL)
    public void TC_V_09_addProductWithQuantity5() {
        ProductsPage products = new HomePage(driver).goToProducts();
        ProductDetailsPage details = products.viewFirstProduct();
        details.setQuantity(5).addToCart();
        CartPage cart = details.viewCartFromModal();

        Assert.assertTrue(driver.getCurrentUrl().contains("/view_cart"));
        Assert.assertEquals(cart.getFirstItemQuantity(), "5",
                "Cart quantity for the first item should be 5");
    }

    @Test(priority = 10, description = "TC_V_10 - Remove a product from the cart")
    @Story("Cart - remove item")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_V_10_removeProductFromCart() {
        ProductsPage products = new HomePage(driver).goToProducts();
        products.addFirstProductToCart();
        CartPage cart = products.viewCartFromModal();

        Assert.assertEquals(cart.getItemCount(), 1,
                "Pre-condition: cart should have 1 item");

        cart.removeFirstProduct();

        Assert.assertTrue(cart.isEmptyCartVisible(),
                "Cart should show 'Cart is empty!' after removing the product");
    }

    @Test(priority = 11, description = "TC_V_11 - Home page loads with slider and category sidebar")
    @Story("Home Page")
    @Severity(SeverityLevel.MINOR)
    public void TC_V_11_homePageLoads() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isHomeSliderVisible(),
                "Main slider should be visible on the home page");
        Assert.assertTrue(home.isCategorySidebarVisible(),
                "Category sidebar should be visible");
        Assert.assertTrue(home.areAllCategoriesVisible(),
                "Women, Men, and Kids categories should all be visible");
    }
}