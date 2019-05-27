package pageObjects;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

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
public class Topic04_form_demo {
	// reduce the memory consumption
	private static WebDriver driver = null;
	public Topic04_form_demo( WebDriver driver)
	{
		this.driver = driver;
	}
	public  Select jobRole01(WebDriver driver) {
		
		Select a = new Select (driver.findElement(By.xpath("//select[@id='job1']")));
		return a;
	}	

	public void selectByValue(Select element) {
		element.selectByValue("manual");
	}
	public void selectByIndex(Select element){
		element.selectByIndex(3);
	}
	public void selectByVisibleText(Select element) {
		// TODO Auto-generated method stub
		element.selectByVisibleText("Automation Tester");

	}
}
