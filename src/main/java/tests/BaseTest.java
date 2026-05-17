package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utils.ConfigReader;
import utils.CookieManager;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        // WebDriver Config & Headless Execution Requirement
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Remove this line if Discogs blocks you with a Captcha!
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled"); // Helps bypass some bot detection

        driver = new ChromeDriver(options);
        driver.get(ConfigReader.getProperty("baseUrl"));

        // Cookie Manipulation Requirement
        CookieManager cookieManager = new CookieManager(driver);
        cookieManager.acceptCookiesIfPresent();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}