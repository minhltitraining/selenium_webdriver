package com.spree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Spree_CreateAddress_VerifyAddress {

	WebDriver driver;

	@BeforeTest
	public void pre_condition() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.spreecommerce.org/");
	}

	@AfterClass
	public void post_condition() throws InterruptedException {
		driver.close();
	}

	@Test(priority = 1)
	public void login() throws InterruptedException {
		driver.findElement(By.id("account-button")).click();
		driver.findElement(By.linkText("LOGIN")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("spree_user[email]")).sendKeys("minh@spree.com");
		driver.findElement(By.name("spree_user[password]")).sendKeys("123456");
		driver.findElement(By.cssSelector("input[class='btn btn-primary btn-block spree-btn mt-2']")).click();
	}

	@Test(priority = 2)
	public void addAddress() {
		String actUrl = driver.getCurrentUrl();
		String expUrl = "https://demo.spreecommerce.org/account";
		Assert.assertEquals(actUrl, expUrl);
		String actTitle = driver.getTitle();
		String expTitle = "My Account - Spree Demo Site";
		Assert.assertEquals(actTitle, expTitle);
		driver.findElement(By.linkText("Add new address")).click();
		driver.findElement(By.name("address[label]")).sendKeys("Home");
		driver.findElement(By.name("address[firstname]")).sendKeys("Minh");
		driver.findElement(By.name("address[lastname]")).sendKeys("Nguyen");
		driver.findElement(By.name("address[address1]")).sendKeys("123 Main St");
		driver.findElement(By.name("address[city]")).sendKeys("Dallas");
		Select state = new Select(driver.findElement(By.name("address[state_id]")));
		state.selectByVisibleText("Texas");
		driver.findElement(By.name("address[zipcode]")).sendKeys("75000");
		driver.findElement(By.name("address[phone]")).sendKeys("1234567890");
		driver.findElement(By.cssSelector("input[class='btn btn-primary btn-block spree-btn mb-5']")).click();

		// verify successfully create address
		String actMessage = driver.findElement(By.xpath("//span[text()='New address has been successfully created']"))
				.getText();
		String expMessage = "New address has been successfully created";
		Assert.assertEquals(actMessage, expMessage);
	}

	@Test(priority=3)
	public void addSameAddress() {
		driver.findElement(By.linkText("Add new address")).click();
		driver.findElement(By.name("address[label]")).sendKeys("Home");
		driver.findElement(By.name("address[firstname]")).sendKeys("Minh");
		driver.findElement(By.name("address[lastname]")).sendKeys("Nguyen");
		driver.findElement(By.name("address[address1]")).sendKeys("123 Main St");
		driver.findElement(By.name("address[city]")).sendKeys("Dallas");
		Select state = new Select(driver.findElement(By.name("address[state_id]")));
		state.selectByVisibleText("Texas");
		driver.findElement(By.name("address[zipcode]")).sendKeys("75000");
		driver.findElement(By.name("address[phone]")).sendKeys("1234567890");
		driver.findElement(By.cssSelector("input[class='btn btn-primary btn-block spree-btn mb-5']")).click();
		
		//verify the add address page still display
		String actUrl = driver.getCurrentUrl();
		String expUrl = "https://demo.spreecommerce.org/addresses";
		Assert.assertEquals(actUrl, expUrl);
	}
	

}
