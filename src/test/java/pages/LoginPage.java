package pages;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage {
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 private By usernameField = By.cssSelector("input[name='username']");
	    private By passwordField = By.name("password");
	    private By loginButton = By.cssSelector("button[type='submit']");
	    private By errorMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
	    private By requiredMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
	    private By forgotPasswordLink = By.xpath("//p[contains(@class,'orangehrm-login-forgot')]");
	    private By resetPasswordHeader = By.xpath("//h6[normalize-space()='Reset Password']");

	

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }

	    public void login(String username, String password) {
	        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
	        usernameInput.sendKeys(username);

	        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
	        passwordInput.sendKeys(password);

	        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	        loginBtn.click();
	        try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	       
	        public boolean isErrorMessageDisplayed() {
	            try {
	                return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
	            } catch (Exception e) {
	                return false;
	            }
	        }

	        public boolean isRequiredMessageDisplayed() {
	            try {
	                return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessage)).isDisplayed();
	            } catch (Exception e) {
	                return false;
	            }
	        }
	        
	        public boolean isForgotPasswordLinkVisible() {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordLink)).isDisplayed();
	        }

	        public void clickForgotPasswordLink() {
	            wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
	        }
	        public boolean isResetPasswordPageVisible() {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordHeader)).isDisplayed();
	        }
	}

