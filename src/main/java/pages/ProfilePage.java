package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    // Textarea requirement and form sending
    @FindBy(xpath = "//form[contains(@class, 'profile-settings')]//textarea[@name='profile']")
    private WebElement bioTextArea;

    @FindBy(xpath = "//form[contains(@class, 'profile-settings')]//button[@type='submit' and contains(text(), 'Save')]")
    private WebElement saveSettingsButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-success')]")
    private WebElement successMessage;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void updateBio(String newBio) {
        wait.until(ExpectedConditions.visibilityOf(bioTextArea));
        bioTextArea.clear();
        bioTextArea.sendKeys(newBio);
        saveSettingsButton.click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
    }
}