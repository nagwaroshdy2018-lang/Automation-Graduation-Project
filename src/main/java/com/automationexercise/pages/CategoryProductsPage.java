package com.automationexercise.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Landing page after clicking a category (Women/Men/Kids) or brand (Polo/H&M/...).
 * URL patterns:
 *   /category_products/{id}   e.g. /category_products/1  (Women > Dress)
 *   /brand_products/{name}    e.g. /brand_products/Polo
 */
public class CategoryProductsPage extends BasePage {

    private static final By HEADER        = By.cssSelector("h2.title.text-center");
    private static final By PRODUCT_ITEMS = By.cssSelector(".features_items .product-image-wrapper");

    public CategoryProductsPage(WebDriver driver) { super(driver); }

    @Step("Check the category/brand header is visible")
    public boolean isHeaderVisible() { return isVisible(HEADER); }

    @Step("Get the header text")
    public String getHeaderText() { return textOf(HEADER); }

    @Step("Get number of product cards on this page")
    public int getProductsCount() { return driver.findElements(PRODUCT_ITEMS).size(); }
}
