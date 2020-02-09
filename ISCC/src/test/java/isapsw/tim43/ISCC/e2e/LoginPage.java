package isapsw.tim43.ISCC.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;

@Getter
public class LoginPage {
	
	private WebDriver webDriver;
	
	@FindBy(xpath = "//*[@id = \"login-emailAddress\"]")
	private WebElement emailInputField;
	
	@FindBy(xpath = "//*[@id=\"login-password\"]")
    private WebElement passwordInputField;
	
	@FindBy(xpath = "//*[@id=\"login-btn\"]")
    private WebElement loginBtn;
	
	
	public LoginPage() {
		
	}
	
	public LoginPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void ensureIsDisplayedEmailInput() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(emailInputField));
	}
	
	public void ensureIsDisplayedPasswordInput() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(passwordInputField));
	}
	
	public void ensureLoginBtnIsVisible() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));
	}

}
