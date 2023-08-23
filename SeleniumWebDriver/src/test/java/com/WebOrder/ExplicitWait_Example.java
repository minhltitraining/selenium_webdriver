package com.WebOrder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExplicitWait_Example {
	ChromeDriver driver;

	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	@Test
	public void Explicit_Wait()

	{
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		// -----------------ImplicitWait Example-------------

		/*
		 * driver.findElement(By.xpath("//a[contains(text(),'Welcome')]")).click();
		 * driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 * driver.findElement(By.linkText("Logout")).click();
		 * driver.findElement(By.id("logInPanelHeading")).isDisplayed();
		 */

		// ----------------ExplicitWait Example--------------
		// @SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
		System.out.println(element.getText());
		element.click();

	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
}