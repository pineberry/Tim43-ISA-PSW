package isapsw.tim43.ISCC.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class AcceptingAppointmentE2ETest {
	
private static final String baseUrl = "http://localhost:8081/";
	
	private WebDriver driver;
	private LoginPage loginPage;
	private AvailableClinicsDoctorsPage availablePage;
	private AppointmentsPage appointmentPage;
	
	@Before
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		availablePage = PageFactory.initElements(driver, AvailableClinicsDoctorsPage.class);
		appointmentPage = PageFactory.initElements(driver, AppointmentsPage.class);
	}
	
	@Test
	public void acceptAppointmentTest() throws InterruptedException {
		driver.navigate().to(baseUrl);
		
		loginPage.ensureIsDisplayedEmailInput();
		
		loginPage.getEmailInputField().sendKeys("teodora.95mandic@gmail.com");
		
		loginPage.ensureIsDisplayedPasswordInput();
		
		loginPage.getPasswordInputField().sendKeys("pass");
		
		loginPage.ensureLoginBtnIsVisible();
		
		loginPage.getLoginBtn().click();
		
		appointmentPage.ensureAppointmentsBtnIsVisible();
		
		appointmentPage.getBtnAppointments().click();
		
		appointmentPage.ensureAppointmentsRevBtnIsVisible();
		
		appointmentPage.getBtnAppRev().click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		appointmentPage.ensureIsDisplayedTabReview();
		
		List<WebElement> btns = appointmentPage.getTabReview().findElements(By.className("btn-outline-primary"));
		
		assertThat(btns)
			.isNotNull()
			.isNotEmpty();
		
		btns.get(1).click();
		
		availablePage.ensureDropdownBtnIsVisible();
		
		availablePage.getDropdownProfile().click();
		
		availablePage.ensureLogoutBtnIsVisible();
		
		availablePage.getLogoutBtn().click();
	}
	
	
	@After
	public void teardown() {
		driver.close();
	}

}
