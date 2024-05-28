package contactTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class toCreateContactsusingTestNg {
	public static void main(String[] args) {
		// Launching the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("browser is mazimised");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// Navigated to the application
		driver.get("http://localhost:8888/");
		System.out.println("Navigated to the web-application");

		// Entering valid credentails
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Performing action on contacts link
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// Fill the mandatory details and save the details
		driver.findElement(By.name("lastname")).sendKeys("RAO");
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		// verification
		String value = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (value.contains("RAO")) {
			System.out.println(value + "TS is passed");
		} else
			System.out.println(value + "TS is failed");

		// logout from the application
		WebElement mouseover = driver.findElement(By.xpath("(//img[@style=\"padding: 0px;padding-left:5px\"])[1]"));
		Actions a = new Actions(driver);
		a.moveToElement(mouseover).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Application is logged out succesfully");

		//close the browser
		driver.quit();
	}


}
