package pageObjects;

import org.testng.annotations.AfterClass;

import org.testng.annotations.Test;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.AfterClass;
public class Topic04_BankScreen {
	private static WebDriver driver = null;
	
	public Topic04_BankScreen(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebElement createUser(WebDriver driver) {
		WebElement element = driver.findElement(By.xpath("//a[text() ='here']"));
		return element;
	}
	
	public WebElement emailID(WebDriver driver) {
		WebElement element = driver.findElement(By.xpath("//input[@type='text' and @name='emailid']"));
		return element;
	}
	public WebElement btnSubmit(WebDriver driver) {
		WebElement element = driver.findElement(By.xpath("//input[@value='Submit']"));
		return element;
	}
	public WebElement getUserId(WebDriver driver) {
		WebElement element = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td"));
		return element;
	}
	
	public WebElement getPwd(WebDriver driver) {
		// TODO Auto-generated method stub
		WebElement element = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td"));
		return element;
	}
	
	public void lgnUser(WebDriver driver, String lgnUID, String lgnPwd) {
		WebElement UID = driver.findElement(By.xpath("//input[@name='uid']"));
		UID.sendKeys(lgnUID);
		WebElement Pwd = driver.findElement(By.xpath("//input[@name='password']"));
		Pwd.sendKeys(lgnPwd);
		Pwd.submit();
		
	}
	
	public void NwCustomer(WebDriver driver) {
		WebElement menuMng = driver.findElement(By.xpath("//li[@class='orange']/following-sibling::li"));
		menuMng.click();
		WebElement Name = driver.findElement(By.xpath("//input[@name ='name']"));
		Name.sendKeys("Loc Nguyen");
		WebElement Gender = driver.findElement(By.xpath("//input[@value='f']"));
		Gender.click();
		WebElement DoB = driver.findElement(By.xpath("//input[@id='dob']"));
		DoB.sendKeys("1992-08-31" );
		WebElement Address = driver.findElement(By.xpath("//textarea [@name='addr']"));
		Address.sendKeys("Testing 123");
		WebElement City = driver.findElement(By.xpath("//input[@name='city']"));
		City.sendKeys("Testing address ");
		WebElement State = driver.findElement(By.xpath("//input[@name='state']"));
		State.sendKeys("TPHCM");
		WebElement Pin = driver.findElement(By.xpath("//input[@name='pinno']"));
		Pin.sendKeys("700000");
		WebElement PhoneNo = driver.findElement(By.xpath("//input[@name='telephoneno']"));
		PhoneNo.sendKeys("1031910941");
		WebElement email = driver.findElement(By.xpath("//input[@name='emailid']"));
		email.sendKeys("testing1" + randomNumber() + "@yopmail.com");
		WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
		pwd.sendKeys("Auvjdaj1992@");
		WebElement sbmBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		sbmBtn.click();
	}	
	

	
	private int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

	public WebElement getCustomId(WebDriver driver) {
		// TODO Auto-generated method stub
		WebElement element = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td"));
		return element;		
	}

	public void editCity_Address(WebDriver driver, WebElement actual_address) {
		actual_address.clear();
		actual_address.sendKeys("new address");
		WebElement City = driver.findElement(By.xpath("//input[@name='city']"));
		City.clear();
		City.sendKeys("new city");
		WebElement sbmBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		sbmBtn.click();
	}
	
}