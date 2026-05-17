package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookieManager {
    private WebDriver driver;
    private WebDriverWait wait;

    public CookieManager(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void acceptCookiesIfPresent() {
        try {
            // Complex XPath targeting the standard TrustArc cookie consent button
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@id='trustarc-banner']//button[contains(text(), 'Accept')] | //button[@id='onetrust-accept-btn-handler']")
            ));
            acceptButton.click();
        } catch (Exception e) {
            System.out.println("No cookie banner found or already accepted.");
        }
    }
}
