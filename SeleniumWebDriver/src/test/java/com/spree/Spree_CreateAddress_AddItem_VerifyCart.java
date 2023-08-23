package com.spree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Spree_CreateAddress_AddItem_VerifyCart {

	WebDriver driver;

	@BeforeTest
	public void pre_condition() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public void post_condition() {
		driver.close();
	}
	@Test
	public void f() throws InterruptedException {
		driver.get("https://demo.spreecommerce.org/");
		driver.findElement(By.id("account-button")).click();
		driver.findElement(By.linkText("LOGIN")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("spree_user_email")).sendKeys("minh@spree.com");
		driver.findElement(By.id("spree_user_password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input[class='btn btn-primary btn-block spree-btn mt-2']")).click();
		driver.findElement(By.cssSelector("a[href='/t/categories/sportswear']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("product_111")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("label[for='variant_option_value_id_3_25']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(2000);
		// verify successful message
		Boolean successful = driver.getPageSource().contains("Added to cart successfully!");
		Assert.assertTrue(successful);

		String actItemName = driver.findElement(By.cssSelector("div[class='product-added-modal-product-details-name']"))
				.getText();
		String expItemName = "Short Pants";
		Assert.assertEquals(actItemName, expItemName);
		String actItemPrice = driver
				.findElement(By.cssSelector("div[class='product-added-modal-product-details-price']")).getText();
		String expItemPrice = "$82.99";
		Assert.assertEquals(actItemPrice, expItemPrice);
		
		driver.findElement(By.cssSelector("a[href='/checkout']")).click();
		
		//verify checkout page loaded
		String actUrl = driver.getCurrentUrl();
		String expUrl = "https://demo.spreecommerce.org/checkout";
		Assert.assertEquals(actUrl, expUrl);
		String actTotal = driver.findElement(By.id("summary-order-total")).getText();
		String expTotal = "$82.99";
		Assert.assertEquals(actTotal, expTotal);
	}
}