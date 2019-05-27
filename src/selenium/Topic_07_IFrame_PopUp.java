package selenium;

import org.openqa.selenium.Alert;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;
import utility_function.Js_function;

public class Topic_07_IFrame_PopUp {
  WebDriver driver;
  @BeforeClass
  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.manage().window().maximize();

  }
  
  @Test (enabled = false)
  public void TC_00(){
	 driver.get("https://kyna.vn/");
	 //Index
	 //driver.switchTo().frame(0);
	 WebElement facebookIframe = driver.findElement(By.xpath("//iframe[contains(@src, 'facebook.com/kyna.vn')]"));
	 driver.switchTo().frame(facebookIframe);
	 WebElement likes = driver.findElement(By.xpath("//div[a[@title='Kyna.vn']]/following-sibling::div"));
	 Assert.assertEquals("168K likes", likes.getText());
  }
  @Test (enabled = false)
  public void TC_01() {
	  driver.get("https://www.hdfcbank.com/");
	  //Element not displayed
	  List<WebElement> iframeClosePopup = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  System.out.println("======== the number of iframe:" +" " + iframeClosePopup.size());
	  if (iframeClosePopup.size() > 0) {
		  driver.switchTo().frame(iframeClosePopup.get(0));
		  WebElement closePopup = driver.findElement(By.xpath("//div[@id='div-close']"));
		  closePopup.click();
	  }
	 WebElement lookingForIframe = driver.findElement(By.xpath("//iframe[@id='viz_iframe36255b17e0d5922914e98208b87ONR]"));
	 driver.switchTo().frame(lookingForIframe);
	 
	 WebElement lookingForText = driver.findElement(By.xpath("//span[@id='messageText']"));
	 Assert.assertEquals("What are you looking for?", lookingForText.getText());
	 
	 //switch to parent page (Top Windows)
	 driver.switchTo().defaultContent();
	 
	 WebElement bannerImageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	 driver.switchTo().frame(bannerImageIframe);
	 
	 List <WebElement> images = driver.findElements(By.xpath("//div[@id='bannercontainer']//img"));
	 int imageNumber = images.size();
	 Assert.assertEquals(6,imageNumber);
	 
	//switch to parent page (Ton windows)
	 driver.switchTo().defaultContent();
	 
	 WebElement flipperBanner = driver.findElement(By.xpath("//div[@class='flipBanner']"));
	 Assert.assertTrue(flipperBanner.isDisplayed());
	 List <WebElement> flipperBannerImages = driver.findElements(By.xpath("//div[@class ='flipBanner']"));
	 int imageNumber1 = flipperBannerImages.size();
	 Assert.assertEquals(8, imageNumber1);
	 
  }
  
  @Test
  public void TC_02() throws Exception {
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  String parentWindowID = driver.getWindowHandle();
	  System.out.println(parentWindowID);
	  driver.findElement(By.xpath("//a[text()='Click Here']")).click();
	  switchToChildWindow(parentWindowID);
	  String googleTitle = driver.getTitle();
	  Assert.assertEquals("Google", googleTitle);
	  Thread.sleep(3000);
	  
  }
  //only 2 windows tab
  public void switchToChildWindow(String parent) {
	  //get all windows ID
	  Set<String> allWindows = driver.getWindowHandles();
	  //For each java 
	  for (String runWindow : allWindows) {
		  System.out.println("Window ID = " + runWindow);
		  if(!runWindow.equals(parent)) {
			  driver.switchTo().window(runWindow);
			  break;
		  }
	  }
  }
  //>= 2 windows tab
  public void switchToWindowByTitle(String title) {
	  //Get all windwos ID
	  Set<String> allWindows = driver.getWindowHandles();
	  //Duyet qua tung ID
	  for (String runWindows : allWindows) {
		  //Switch qa tung ID
		  driver.switchTo().window(runWindows);
		  
		  //get tutke cua page do ra
		  String currentTitle = driver.getTitle();
		  
		  //Title current windows = title truyen vao
		  if(currentTitle.equals(title)) {
			  break;
		  }
	  }
  }
  
  public boolean closeAllWithoutParentWindows(String parentWindow) {
	  Set<String> allWindows = driver.getWindowHandles();
	  
	  //Duyet qua tung ID
	  for (String runWindows : allWindows) {
		  if(!runWindows.equals(parentWindow)) {
			//Switch qua id do
			  driver.switchTo().window(parentWindow);
			  driver.close();
		  }
	  }
	  driver.switchTo().window(parentWindow);
	  if(driver.getWindowHandles().size() == 1) {
		  return true;
	  }else {
		  return false;
	  }
  }
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}