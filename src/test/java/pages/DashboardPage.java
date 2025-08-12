package pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By dashboardHeader = By.xpath("//h6[normalize-space(.)='Dashboard']");
    private By userDropdown = By.xpath("//span[@class='oxd-userdropdown-tab']");
    private By logoutLink = By.xpath("//a[normalize-space()='Logout']");
    private By quickAccessWidgets = By.cssSelector(".oxd-grid-item");
    private By usernameText = By.cssSelector(".oxd-userdropdown-name");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isDashboardVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
    }

    public void logout() {
    	 WebElement dropdown = wait.until(ExpectedConditions
                 .elementToBeClickable(userDropdown));
         dropdown.click();

         WebElement logout = wait.until(ExpectedConditions
                 .visibilityOfElementLocated(logoutLink));
         logout.click();
     }
    
    public String getDashboardHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
    }

    public boolean areQuickAccessWidgetsVisible() {
        return !driver.findElements(quickAccessWidgets).isEmpty();
    }

    public String getLoggedInUsername() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameText)).getText();
    }

    public boolean waitForDashboardLoad() {
        long startTime = System.currentTimeMillis();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
        long loadTime = System.currentTimeMillis() - startTime;
        return loadTime <= 5000; // within 5 seconds
    }

    public boolean isLoginPageVisible() {
        return driver.getCurrentUrl().contains("/auth/login");
    }
}