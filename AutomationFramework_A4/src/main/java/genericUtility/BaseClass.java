package genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {

	PropertyFileUtility putil = new PropertyFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver;

	@BeforeSuite(groups = {"smoke","regression"})
	public void basicConfiguration() {
		Reporter.log("---DataBase connection Successfull---", true);
	}

	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(groups = {"smoke","regression"})
	public void beforeClassConfiguration(/*String BROWSER*/) throws Exception {
		String URL = putil.toReadDataFromProperty("url");
		String BROWSER = putil.toReadDataFromProperty("browser");
		// To launch browser
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		sDriver = driver;
		wutil.maximizeWindow(driver);
		wutil.waitForPage(driver);
		driver.get(URL);
	}

	@BeforeMethod(groups = {"smoke","regression"})
	public void beforeMethodConfiguration() throws Exception {
		String USERNAME = putil.toReadDataFromProperty("username");
		String PASSWORD = putil.toReadDataFromProperty("password");
		objectRepository.LoginPage lp = new objectRepository.LoginPage(driver);
		lp.getUsernameTextFieldEdt().sendKeys(USERNAME);
		lp.getPasswordTextfieldEdt().sendKeys(PASSWORD);
		lp.getLoginButton().click();
	}

	@AfterMethod(groups = {"smoke","regression"})
	public void afterMethodConfiguration() {
		objectRepository.HomePage wp = new objectRepository.HomePage(driver);
		wutil.toMouseHover(driver, wp.getLogoutButton());
		wp.getSignOutLink().click();
		System.out.println("Logged Out");
	}

	@AfterClass(groups = {"smoke","regression"})
	public void afterClassConfiguration() {
		driver.quit();
	}

}
