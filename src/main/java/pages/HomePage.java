package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    // Complex XPath for the search form
    @FindBy(xpath = "//form[@id='site_search']//input[@type='text']")
    private WebElement searchBar;

    @FindBy(xpath = "//form[@id='site_search']//select[@name='type']")
    private WebElement searchCategoryDropdown;

    @FindBy(xpath = "//form[@id='site_search']//button[@type='submit']")
    private WebElement searchSubmitButton;

    @FindBy(xpath = "//button[contains(@id, 'user-menu')]")
    private WebElement userMenuToggle;

    @FindBy(xpath = "//ul[contains(@class, 'user-menu')]//a[contains(@href, 'logout')]")
    private WebElement logoutLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String query, String categoryValue) {
        // Dropdown interaction (Select class requirement)
        Select categorySelect = new Select(searchCategoryDropdown);
        categorySelect.selectByValue(categoryValue); 
        
        searchBar.sendKeys(query);
        searchSubmitButton.click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userMenuToggle)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}