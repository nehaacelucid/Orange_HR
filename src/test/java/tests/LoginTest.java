package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends TestBase {

	  @Test(priority = 1)
	  public void verifyLoginAndLogout()  {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardVisible(), "Dashboard not visible after login.");

        dashboardPage.logout();

        // Verify returned to login page
        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"), "Not navigated back to login page after logout.");
    }
	  
	  @Test(priority = 2)
	    public void invalidPasswordLogin() {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("Admin", "wrongPass");

	        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid password.");
	    }
	  @Test(priority = 3)
	    public void invalidUsernameLogin() {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("WrongUser", "admin123");

	        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not displayed for invalid username.");
	    }

	    @Test(priority = 4)
	    public void emptyUsernameAndPassword() {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("", "");

	        Assert.assertTrue(loginPage.isRequiredMessageDisplayed(), "Required message not displayed for both fields.");
	    }

	    @Test(priority = 5)
	    public void emptyUsernameOnly() {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("", "admin123");

	        Assert.assertTrue(loginPage.isRequiredMessageDisplayed(), "Required message not displayed for username.");
	    }

	    @Test(priority = 6)
	    public void emptyPasswordOnly() {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.login("Admin", "");

	        Assert.assertTrue(loginPage.isRequiredMessageDisplayed(), "Required message not displayed for password.");
	    }
}