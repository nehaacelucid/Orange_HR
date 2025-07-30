package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends TestBase {

    @Test
    public void verifyLoginAndLogout()  {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardVisible(), "Dashboard not visible after login.");

        dashboardPage.logout();

        // Verify returned to login page
        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"), "Not navigated back to login page after logout.");
    }
}