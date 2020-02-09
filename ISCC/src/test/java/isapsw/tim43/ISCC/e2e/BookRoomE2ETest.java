package isapsw.tim43.ISCC.e2e;

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

public class BookRoomE2ETest {
	
private static final String baseUrl = "http://localhost:8081/";
	
	private WebDriver driver;
	private LoginPage loginPage;
	private AvailableClinicsDoctorsPage availablePage;
	private AdminHomePage adminHome;
	
	@Before
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		availablePage = PageFactory.initElements(driver, AvailableClinicsDoctorsPage.class);
		adminHome = PageFactory.initElements(driver, AdminHomePage.class);
	}
	
	@Test
	public void bookingAppointment() throws InterruptedException {
		driver.navigate().to(baseUrl);
		
		loginPage.ensureIsDisplayedEmailInput();
		
		loginPage.getEmailInputField().sendKeys("admin@gmail.com");
		
		loginPage.ensureIsDisplayedPasswordInput();
		
		loginPage.getPasswordInputField().sendKeys("nesto");
		
		loginPage.ensureLoginBtnIsVisible();
		
		loginPage.getLoginBtn().click();
		
		adminHome.ensureIsDisplayedDropDownProfile();
		
		driver.navigate().to(baseUrl + "searchRooms?proc=12&type=exam"); 
		
		adminHome.ensureIsDisplayedTable();
		
		List<WebElement> buttons = adminHome.getTable().findElements(By.tagName("button"));
		
		buttons.get(3).click();
		
		driver.navigate().to(baseUrl);
		
		adminHome.ensureDropDownProfileIsVisible();
		
		adminHome.getDropDownProfile().click();
		
		adminHome.ensurLogoutIsVisible();
		
		adminHome.getLogut().click();
	}
	
	@After
	public void teardown() {
		driver.close();
	}

}
