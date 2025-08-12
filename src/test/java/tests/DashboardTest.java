package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;
import pages.DashboardPage;

public class DashboardTest extends TestBase {

    @Test
    public void verifyDashboardScenarios() {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver);

        // Step 2: Verify dashboard is visible
        Assert.assertTrue(dashboardPage.isDashboardVisible(),
                "Dashboard is not visible after login.");

        // Step 3: Verify header text
        String headerText = dashboardPage.getDashboardHeaderText();
        Assert.assertEquals(headerText, "Dashboard",
                "Dashboard header text is incorrect.");

        // Step 4: Verify quick access widgets (if any)
        Assert.assertTrue(dashboardPage.areQuickAccessWidgetsVisible(),
                "Quick access widgets are not displayed.");

        // Step 5: Verify logged-in username
        String username = dashboardPage.getLoggedInUsername();
        Assert.assertEquals(username, "TestUser_450 Patil", // change as per your app
                "Logged in username is incorrect.");

        // Step 6: Verify dashboard loads within acceptable time
        Assert.assertTrue(dashboardPage.waitForDashboardLoad(),
                "Dashboard took too long to load.");

        // Step 7: Logout and verify redirected to login page
        dashboardPage.logout();
        Assert.assertTrue(dashboardPage.isLoginPageVisible(),
                "User was not redirected to login page after logout.");
    }
}
