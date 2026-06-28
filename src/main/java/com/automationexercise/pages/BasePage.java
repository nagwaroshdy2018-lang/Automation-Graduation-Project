package com.automationexercise.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

/**
 * Parent of every Page Object.
 * Robust against slow loads, ads, and overlays.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final WebDriverWait longWait;
    protected final Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        int timeout = ConfigReader.getInt("explicit.wait");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(timeout * 2L));
        this.actions = new Actions(driver);
    }

    // ---------- waits ----------
    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ---------- actions ----------
    protected void type(By locator, String text) {
        scrollTo(locator);
        WebElement el = waitVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected void click(By locator) {
        scrollTo(locator);
        try {
            waitClickable(locator).click();
        } catch (ElementClickInterceptedException | TimeoutException e) {
            jsClick(locator);
        }
    }

    protected void jsClick(By locator) {
        WebElement el = waitPresent(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void hover(By locator) {
        actions.moveToElement(waitVisible(locator)).perform();
    }

    protected void scrollTo(By locator) {
        try {
            List<WebElement> els = driver.findElements(locator);
            if (!els.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", els.get(0)
                );
            }
        } catch (Exception ignored) {}
    }

    protected void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight);"
        );
    }

    protected void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    /** Sleeps in milliseconds — use sparingly! Only when waits don't help. */
    protected void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }

    // ---------- reads ----------
    protected String textOf(By locator) {
        return waitVisible(locator).getText().trim();
    }

    protected boolean isVisible(By locator) {
        try {
            scrollTo(locator);
            return waitVisible(locator).isDisplayed();
        } catch (Exception e) {
            try {
                List<WebElement> els = driver.findElements(locator);
                return !els.isEmpty() && els.get(0).isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    /** Returns HTML5 validationMessage. */
    protected String html5Validation(By locator) {
        WebElement el = waitPresent(locator);
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", el);
    }

    public String currentUrl() { return driver.getCurrentUrl(); }
    public String pageTitle()  { return driver.getTitle(); }
}