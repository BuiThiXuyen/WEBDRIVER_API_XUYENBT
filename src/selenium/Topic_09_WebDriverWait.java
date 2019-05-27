package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_09_WebDriverWait {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,30);
	}
	 
	@Test (enabled = false)
	public void TC_01_ImplicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loanding/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); 
		WebElement buttonStart = driver.findElement(By.xpath("//*[@id='start']/button"));
		buttonStart.click();
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='hello World!]")).isDisplayed());
	}
	@Test (enabled = false)
	public void TC_02_Explicit() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement dateTimePicker = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_RadCalendar1Panel']"));
		Assert.assertTrue(dateTimePicker.isDisplayed());
		
		WebElement dateSelectedBefore = driver.findElement(By.xpath("//*[@id = 'ctl00_ContentPlaceholder1_Label1']"));
		String textBefore = dateSelectedBefore.getText().trim();
		Assert.assertEquals("No Selected Dates to display.", textBefore);
		
		WebElement today = driver.findElement(By.xpath("//a[text()='16']"));
		today.click();
		
		By ajaxIcon = By.xpath("//div[@class='raDiv']");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ajaxIcon));
		
		WebElement todaySelected = driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='16']"));
		wait.until(ExpectedConditions.visibilityOf(todaySelected));
		
		
		WebElement dateSelectedAfter = driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_Label1']"));
		String textAfter = dateSelectedAfter.getText().trim();
		Assert.assertEquals("Saturday, June 16, 2018", textAfter);
	}
	/*@Test
	public void TC_03_FluentWait() {
		driver.get("https://stuntcoders.com/snippets/javascript-countdown/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement countdown = driver.findElement(By.xpath("javascript_countdown_time"));
		
		Wait wait = new FluentWait(driver).withTimeout(45,SECONDS).pollingEvery(15,SECONDS)
				.ignoring(NoSuchElementException.class);
				WebElement element = wait.until(new Function<webdriver,webElement>(){
				  public WebElement apply(WebDriver driver){
				     return driver.findElement(By.id(element ));
				  }
				});
	}*/
	@AfterClass
	public void AfterTest() {
		driver.quit();
	}
}