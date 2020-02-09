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

public class SearchAvailableClinicE2ETest {

	private static final String baseUrl = "http://localhost:8081/";
	
	private WebDriver driver;
	private LoginPage loginPage;
	private AvailableClinicsDoctorsPage availablePage;
	
	@Before
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		availablePage = PageFactory.initElements(driver, AvailableClinicsDoctorsPage.class);
		
	}
	
	
	@Test
	public void TestSearchForAvailableClinic() throws InterruptedException {
		driver.navigate().to(baseUrl);
		
		loginPage.ensureIsDisplayedEmailInput();
		
		loginPage.getEmailInputField().sendKeys("teodora.95mandic@gmail.com");
		
		loginPage.ensureIsDisplayedPasswordInput();
		
		loginPage.getPasswordInputField().sendKeys("pass");
		
		loginPage.ensureLoginBtnIsVisible();
		
		loginPage.getLoginBtn().click();
		
		Thread.sleep(1000);
		
		availablePage.ensureAvailableBtnIsVisible();
		
		availablePage.getAvailableTab().click();
		
		availablePage.ensureIsDisplayedDateInput();
		
		availablePage.getDateInputField().sendKeys("02/15/2020");
		
		availablePage.ensureIsDisplayedProcedureInput();
		
		availablePage.getProcedureInputField().click();
		
		List<WebElement> options = availablePage.getProcedureInputField().findElements(By.tagName("option"));
		
		options.get(1).click();
		
		availablePage.ensureSearchBtnIsVisible();
		
		availablePage.getSearchBtn().click();
		
		availablePage.ensureClinicCardsIsVisible();
	
		List<WebElement> title = availablePage.getClinicsCard().findElements(By.tagName("h3"));
		
		assertThat(title)
			.isNotNull()
			.isNotEmpty();
		
		List<WebElement> anchor = title.get(0).findElements(By.tagName("a"));
		
		anchor.get(0).click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		List<WebElement> divs = availablePage.getClinicsCard().findElements(By.tagName("div"));
		
		List<WebElement> link = divs.get(4).findElements(By.tagName("a"));
		
		link.get(0).click();
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		availablePage.ensureDropdownTimesIsVisible();
		
		availablePage.getDropdownTimes().click();
		
		availablePage.ensureDropdownMenuIsVisible();
		
		availablePage.ensureIsDisplayedDropDownMenu();
		
		List<WebElement> times = availablePage.getDropDownMenu().findElements(By.tagName("a"));
		
		assertThat(times)
			.isNotNull()
			.isNotEmpty();
		
		times.get(0).click();
		
		availablePage.ensureDropdownBtnIsVisible();
		
		availablePage.getDropdownProfile().click();
		
		availablePage.ensureLogoutBtnIsVisible();
		
		availablePage.getLogoutBtn().click();
		
		Thread.sleep(1000);
		
	}
	
	@After
	public void teardown() {
		driver.close();
	}
	
}
