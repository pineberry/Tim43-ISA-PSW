package isapsw.tim43.ISCC.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.Getter;
import sun.security.krb5.internal.APOptions;

@Getter
public class AppointmentsPage {

	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id = \"patient-profile-appointments\"]")
	private WebElement btnAppointments;
	
	@FindBy(xpath = "//*[@id = \"patient-appointments-review\"]")
	private WebElement btnAppRev;
	
	@FindBy(xpath = "//*[@id = \"review\"]")
	private WebElement tabReview;
	
	public AppointmentsPage() {
		
	}
	
	public AppointmentsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ensureAppointmentsBtnIsVisible() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("patient-profile-appointments")));
	}
	
	public void ensureAppointmentsRevBtnIsVisible() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("patient-appointments-review")));
	}
	
	public void ensureIsDisplayedTabReview() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(tabReview));
	}
}
