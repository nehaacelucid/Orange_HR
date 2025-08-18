package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;

public class ForgotPasswordTest extends TestBase {

    @Test
    public void verifyForgotPasswordLink() {
        LoginPage loginPage = new LoginPage(driver);

        // Step 1: Verify link is visible
        Assert.assertTrue(loginPage.isForgotPasswordLinkVisible(),
                "Forgot Password link is not visible on the login page.");

        // Step 2: Click on the link
        loginPage.clickForgotPasswordLink();

        // Step 3: Verify navigation to Reset Password page
        Assert.assertTrue(loginPage.isResetPasswordPageVisible(),
                "Not navigated to Reset Password page after clicking Forgot Password link.");
    }
}