package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.TestBase;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTestExcel extends TestBase {

    @DataProvider(name = "loginDataFromExcel")
    public Object[][] getLoginData() {
        String excelPath = "C:\\Users\\Neha Negi\\Desktop\\testdata.xlsx";  // path to your Excel file
        return ExcelUtils.getExcelData(excelPath, "LoginData");
    }

    @Test(dataProvider = "loginDataFromExcel")
    public void verifyLogin(String username, String password, String validFlag) {
        boolean isValid = Boolean.parseBoolean(validFlag);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (isValid) {
            DashboardPage dashboardPage = new DashboardPage(driver);
            Assert.assertTrue(dashboardPage.isDashboardVisible(), "Dashboard not visible after valid login.");
            dashboardPage.logout();
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message not shown for invalid login.");
        }
    }
}
