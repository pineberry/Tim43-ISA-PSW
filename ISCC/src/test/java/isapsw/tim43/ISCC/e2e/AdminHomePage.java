package isapsw.tim43.ISCC.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;

@Getter
public class AdminHomePage {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id=\"admin-profile-dropdown-profile\"]")
    private WebElement dropDownProfile;
	
	@FindBy(xpath = "//*[@id=\"room-search-table\"]")
    private WebElement table;
	
	@FindBy(xpath = "//*[@id=\"admin-profile-logut\"]")
    private WebElement logut;
	
	public AdminHomePage() {
		
	}
	
	public AdminHomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ensureDropDownProfileIsVisible() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-profile-dropdown-profile")));
	}
	
	public void ensureIsDisplayedDropDownProfile() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(dropDownProfile));
	}
	
	public void ensureIsDisplayedTable() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(table));
	}
	
	public void ensurLogoutIsVisible() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-profile-logut")));
	}
}
