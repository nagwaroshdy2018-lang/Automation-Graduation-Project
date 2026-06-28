package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * /products — list, search, add-to-cart, view product.
 */
public class ProductsPage extends BasePage {

    // Case-insensitive XPath for headers (handles "All Products", "ALL PRODUCTS", etc.)
    private static final By ALL_PRODUCTS_HEADER  = By.xpath(
            "//h2[contains(translate(normalize-space()," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," +
                    "'all products')]"
    );

    private static final By SEARCHED_HEADER      = By.xpath(
            "//h2[contains(translate(normalize-space()," +
                    "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," +
                    "'searched products')]"
    );

    private static final By PRODUCT_ITEMS        = By.cssSelector(".features_items .product-image-wrapper");
    private static final By SEARCH_INPUT         = By.id("search_product");
    private static final By SEARCH_SUBMIT        = By.id("submit_search");

    // First product helpers
    private static final By FIRST_ADD_TO_CART    = By.cssSelector(".features_items .product-image-wrapper:nth-child(1) .productinfo a.add-to-cart");
    private static final By FIRST_VIEW_PRODUCT   = By.cssSelector(".features_items .product-image-wrapper:nth-child(1) .choose a");

    // Modal that appears after adding to cart
    private static final By MODAL_CONTINUE_SHOP  = By.cssSelector("button.close-modal");
    private static final By MODAL_VIEW_CART      = By.xpath("//u[normalize-space()='View Cart']");

    public ProductsPage(WebDriver driver) { super(driver); }

    public boolean isAllProductsHeaderVisible() { return isVisible(ALL_PRODUCTS_HEADER); }
    public boolean isSearchedHeaderVisible()    { return isVisible(SEARCHED_HEADER); }

    @Step("Get product cards count")
    public int getProductsCount() {
        return driver.findElements(PRODUCT_ITEMS).size();
    }

    @Step("Search for product: {0}")
    public ProductsPage search(String term) {
        scrollTo(SEARCH_INPUT);
        type(SEARCH_INPUT, term);
        click(SEARCH_SUBMIT);
        return this;
    }

    @Step("Hover first product and click Add to cart")
    public ProductsPage addFirstProductToCart() {
        List<WebElement> products = driver.findElements(PRODUCT_ITEMS);
        if (products.isEmpty()) throw new RuntimeException("No product cards found on page");
        scrollTo(PRODUCT_ITEMS);
        actions.moveToElement(products.get(0)).perform();
        jsClick(FIRST_ADD_TO_CART);
        return this;
    }

    @Step("Click Continue Shopping on the modal")
    public ProductsPage continueShopping() {
        click(MODAL_CONTINUE_SHOP);
        return this;
    }

    @Step("Click View Cart on the modal")
    public CartPage viewCartFromModal() {
        click(MODAL_VIEW_CART);
        return new CartPage(driver);
    }

    @Step("Click View Product on the first result")
    public ProductDetailsPage viewFirstProduct() {
        scrollTo(FIRST_VIEW_PRODUCT);
        click(FIRST_VIEW_PRODUCT);
        return new ProductDetailsPage(driver);
    }
}