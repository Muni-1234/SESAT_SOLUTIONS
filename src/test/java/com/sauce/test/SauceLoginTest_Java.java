package com.sauce.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.sauced.common.Common_Utilities;

public class SauceLoginTest_Java {

	WebDriver driver=null;
	Common_Utilities Testdata = new Common_Utilities();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		SauceLoginTest_Java test = new SauceLoginTest_Java();
		test.LaunchUrl();
		test.DoLogin();
		test.Home();
		test.Lougout();
		test.TearDown();
		//new SauceLoginTest_Java().LaunchUrl();
	}
	
	public void LaunchUrl() throws IOException, InterruptedException {
		String URL = Testdata.getpropertyvalue("URL", "Test_config.properties");		
		driver = new ChromeDriver();
		driver.get(URL);
		Thread.sleep(2000);
		System.out.println("Browser Launched Successfully");
		driver.manage().window().maximize();		
		System.out.println(driver.getTitle());
	}
	
	public void DoLogin() throws IOException, InterruptedException {
		String UserName = Testdata.getpropertyvalue("UserName", "Test_config.properties");
		String Password = Testdata.getpropertyvalue("Password", "Test_config.properties");
		driver.findElement(By.id("user-name")).sendKeys(UserName);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.id("login-button")).click();
		Thread.sleep(3000);
	}
	
	public void Home() throws IOException {
		//checkInventoryItemTest:
		Assert.assertTrue(driver.findElements(By.xpath("//div[@class='inventory_item']")).size() == 6);
		
		//checkAddToCartButtonTest:
		Assert.assertTrue(driver.findElements(By.xpath("//button[text()='Add to cart']")).size() == 6);
		
		//appLogoTest:
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='app_logo']")).isDisplayed());
		
		//socialTwitterTest:
		Assert.assertTrue(driver.findElement(By.className("social_twitter")).isDisplayed());
		
		//socialFacebookTest:
		Assert.assertTrue(driver.findElement(By.className("social_facebook")).isDisplayed());
		
		//socialLinkedInTest:
		Assert.assertTrue(driver.findElement(By.className("social_linkedin")).isDisplayed());		
	}
	
	public void Lougout() throws IOException, InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
	}
	
	public void TearDown() throws InterruptedException {		
		Thread.sleep(2000);
		driver.quit();
		Thread.sleep(2000);
		System.out.println("Browser Closed Successfully");
	}
	
}
