package com.crm.LoginTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

// testing 
public class Login {
	WebDriver driver;
	@BeforeTest
	public void launchSetUp() {
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		 driver=new ChromeDriver();
		driver.get("https://local.valuechain.com/Login");
		driver.manage().window().maximize();
	}
//
	
	@Test(priority=1)
	public void loginTest() throws Exception {
		driver.findElement(By.xpath("//input[@id='txtLoginName']")).sendKeys("demo");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("valuechain");
		driver.findElement(By.id("btnLogin")).click();
		Reporter.log("*********Log In Completed***********");
		System.out.println(driver.getTitle());
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='nnavbar']/div[1]/div[1]/div/div[1]/a/i")).click();
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//span[text()='Audits']")).click();
		System.out.println(driver.getTitle());
		Actions a=new Actions(driver);
		WebElement lbtn = driver.findElement(By.xpath("//span[contains(text(),'John Smith')]"));
		a.moveToElement(lbtn).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Log Out")).click();
		driver.switchTo().alert().accept();
		System.out.println(driver.getTitle());
		Reporter.log("=============LogOut Completed===========");
	}
	
	@Test(priority=2, dependsOnMethods="loginTest")
	public void verifyURL() {
		System.out.println(driver.getCurrentUrl());
		Reporter.log("=======URL Matched==========");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
}
