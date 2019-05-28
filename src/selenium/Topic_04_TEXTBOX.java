package selenium;

import org.testng.annotations.AfterClass;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import pageObjects.Topic04_form_demo;
import pageObjects.Topic04_BankScreen;

public class Topic_04_TEXTBOX {
	// Khai bao 1 cai driver
	private static WebDriver driver = null;
	String username, pass, name, dob, address, city, state, pin, mobi, email, password, customerID, newAddress,newCity;
	
	
	@BeforeClass
	public void beforeClass() {
//		//Firefox <=47 + selenium version 2.x.x
		driver = new FirefoxDriver();
		
		
		// wait cho page dc load thanh cong
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		
		//Maximize browser
		driver.manage().window().maximize();
		
		//open website
		
	}
	/*@Test (enabled = false)
	
	public void TC_01_Handle_Dropdown_list() {
		driver.get("http://daominhdam.890m.com/");
		Topic04_form_demo jobRole = new Topic04_form_demo(driver);
		//Kiem tra thuoc tinh job role 01 co multiple ko ?
		Assert.assertFalse(jobRole.jobRole01(driver).isMultiple());
		Select autoVal = jobRole.jobRole01(driver);
		
		//Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		//Kiểm tra giá trị đã được chọn thành công
		jobRole.selectByVisibleText(autoVal);
		String actual_result = autoVal.getFirstSelectedOption().getText();
		Assert.assertEquals(actual_result,"Automation Tester");
		
		//Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
		//Kiểm tra giá trị đã được chọn thành công
		jobRole.selectByIndex(autoVal);
		actual_result = autoVal.getFirstSelectedOption().getText();
		Assert.assertEquals(actual_result,"Mobile Tester");
		
		// Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
		// Kiểm tra giá trị đã được chọn thành công
		jobRole.selectByValue(autoVal);
		actual_result = autoVal.getFirstSelectedOption().getText();
		Assert.assertEquals(actual_result,"Manual Tester");
		Assert.assertEquals(jobRole.jobRole01(driver).getOptions().size(), 5);
	}
	*/
	
	@Test
	public void TC_02_Handle_textBox_textArea() {
		driver.get("http://demo.guru99.com/v4");
		// Create temporatory username
		Topic04_BankScreen tempUser = new Topic04_BankScreen(driver);
		tempUser.createUser(driver).click();
		tempUser.emailID(driver).sendKeys("tester1@yopmail.com");
		tempUser.btnSubmit(driver).click();
		String tempUserID = tempUser.getUserId(driver).getText();
		String tempPwd = tempUser.getPwd(driver).getText();
		driver.navigate().to("http://demo.guru99.com/v4");
		//Login w temporatory username vua tao
		// mngr135218/gEdAqyt 
		tempUser.lgnUser(driver,tempUserID,tempPwd);
		tempUser.NwCustomer(driver);
		//Add new Customer
		String actual_result = tempUser.getCustomId(driver).getText();
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name ='cusid']")).sendKeys(actual_result);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		WebElement actual_cusname = driver.findElement(By.xpath("//input[@name ='name']"));
		WebElement actual_address = driver.findElement(By.xpath("//textarea[@name ='addr']"));
		Assert.assertEquals("Xuyen Bui", actual_cusname.getAttribute("Value"));
		Assert.assertEquals("Testing 123", actual_address.getText());
		tempUser.editCity_Address(driver,actual_address);
		WebElement newAddr = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td"));
		WebElement newCity = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td"));

		Assert.assertEquals("new city", newCity.getText());
		Assert.assertEquals("new address", newAddr.getText());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}	