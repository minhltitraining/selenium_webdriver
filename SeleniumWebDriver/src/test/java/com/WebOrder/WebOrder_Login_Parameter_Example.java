package com.WebOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_Login_Parameter_Example {
	WebDriver driver;

	
	@Test
	@Parameters({"uname", "upass"})
	public void login_to_app(String uname, String upass) {
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys(upass);
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		driver.findElement(By.linkText("Logout")).isDisplayed();
		driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).isDisplayed();
		driver.findElement(By.linkText("Logout")).click();

	}

	@BeforeTest
	public void pre_condition() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@AfterTest
	public void post_condition() {
		driver.close();
	}
}
