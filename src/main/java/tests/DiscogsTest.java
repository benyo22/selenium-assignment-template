package tests;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utils.ConfigReader;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class DiscogsTest extends BaseTest {

    @Test
    @DisplayName("Verify static page title loads correctly")
    public void verifyHomepageTitle() {
        String expectedTitle = "Discogs - Music Database and Marketplace";
        Assertions.assertTrue(driver.getTitle().contains("Discogs"), "Page title does not match!");
    }

    @Test
    @DisplayName("User can login, update profile with random data, and logout safely")
    public void completeUserJourney_LoginUpdateLogout() {
        // 1. Navigation & Login
        driver.get(ConfigReader.getProperty("baseUrl") + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // 2. Interact with Dropdown and Search
        HomePage homePage = new HomePage(driver);
        // Searching for "Daft Punk" in the "Artist" category (value 'artist' depends on their actual DOM)
        homePage.searchFor("Daft Punk", "artist"); 

        // 3. Form Sending with User (Profile Update) & Random Data requirement
        driver.get(ConfigReader.getProperty("baseUrl") + "/settings/user"); 
        ProfilePage profilePage = new ProfilePage(driver);
        
        // Random Data Requirement
        String randomBio = "Music enthusiast. Automated test run ID: " + UUID.randomUUID().toString();
        profilePage.updateBio(randomBio);
        
        // Ensure the update worked
        Assertions.assertTrue(profilePage.isSuccessMessageDisplayed(), "Profile update success message not found!");

        // 4. Logout Requirement
        homePage.logout();
        Assertions.assertTrue(driver.getCurrentUrl().contains("login") || driver.getCurrentUrl().equals(ConfigReader.getProperty("baseUrl") + "/"), "User was not logged out properly");
    }
}