package isapsw.tim43.ISCC.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;

@Getter
public class AvailableClinicsDoctorsPage {
	
	private WebDriver webDriver;
	
	@FindBy(xpath = "//*[@id = \"search-clinic-doctor-date\"]")
	private WebElement dateInputField;
	
	@FindBy(xpath = "//*[@id = \"search-clinic-doctor-typeOfProcedure\"]")
	private WebElement procedureInputField;
	
	@FindBy(xpath = "//*[@id = \"search-clinic-doctor-btn-search\"]")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id = \"patient-profile-dropdown\"]")
	private WebElement dropdownProfile;

	@FindBy(xpath = "//*[@id = \"patient-profile-logout\"]")
	private WebElement logoutBtn;

	@FindBy(xpath = "//*[@id = \"patient-profile-available\"]")
	private WebElement availableTab;
	
	@FindBy(xpath = "//*[@id = \"clinics\"]")
	private WebElement clinicsCard;
	
	@FindBy(xpath = "//*[@id = \"dropdownMenuButton\"]")
	private WebElement dropdownTimes;
	
	@FindBy(xpath = "//*[@id = \"dropDownMenu\"]")
	private WebElement dropDownMenu;
	
	public AvailableClinicsDoctorsPage() {
		
	}
	
	public AvailableClinicsDoctorsPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void ensureIsDisplayedDateInput() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(dateInputField));
	}
	
	public void ensureIsDisplayedProcedureInput() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(procedureInputField));
	}
	
	public void ensureSearchBtnIsVisible() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("search-clinic-doctor-btn-search")));
	}
	
	public void ensureIsDisplayedDropdownBtn() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(dropdownProfile));
	}
	
	public void ensureDropdownBtnIsVisible() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("patient-profile-dropdown")));
	}
	
	public void ensureLogoutBtnIsVisible() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("patient-profile-logout")));
	}
	
	public void ensureAvailableBtnIsVisible() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("patient-profile-available")));
	}
	
	public void ensureClinicCardsIsVisible() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("clinics")));
	}
	
	public void ensureDropdownTimesIsVisible() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownMenuButton")));
	}
	
	public void ensureDropdownMenuIsVisible() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("dropDownMenu")));
	}
	
	public void ensureIsDisplayedDropDownMenu() {
		(new WebDriverWait(webDriver, 10)).until(ExpectedConditions.elementToBeClickable(dropDownMenu));
	}
}
