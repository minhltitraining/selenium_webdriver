package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_Cross_Browser_Example {
	WebDriver driver;

	@Test(dataProvider = "WebOrder_Login_All_TCs", dataProviderClass = WebOrder_TestData.class)
	public void login_to_app(String uname, String pass, String expResult) {
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).clear();
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys(pass);
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		if (expResult.equalsIgnoreCase("Logout")) {
			String actMes = driver.findElement(By.linkText("Logout")).getText();
			Assert.assertEquals(actMes, expResult);
			driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).isDisplayed();
			driver.findElement(By.linkText("Logout")).click();
		} else {
			String actErr = driver.findElement(By.id("ctl00_MainContent_status")).getText();
			Assert.assertEquals(actErr, expResult);
		}
	}

	@BeforeTest
	@Parameters({"browser"})
	public void pre_condition(String browser) throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else {
			throw new Exception("Browser is not correct");
		}
		
		
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
	}

	@AfterTest
	public void post_condition() {
		driver.close();
	}
}
