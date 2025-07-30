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
}