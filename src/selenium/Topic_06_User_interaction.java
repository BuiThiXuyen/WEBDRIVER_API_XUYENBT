package selenium;

import org.openqa.selenium.Alert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import junit.framework.Assert;
import utility_function.Js_function;

public class Topic_06_User_interaction {
  WebDriver driver = new FirefoxDriver();
  @BeforeClass
  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		

  }
  
  @Test (enabled = false)
  public void TC_00() {
	  driver.get("http://daominhdam.890m.com/");
	  Actions action = new Actions (driver);

	  //Step 02 - Hover chuột vào title: 'Hover over me'
	  WebElement hoverLink = driver.findElement(By.xpath("//a[text()='Hover over me']"));
	  action.moveToElement(hoverLink).perform();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='tooltip-inner' and text()='Hooray!']")).isDisplayed());
  }
  @Test (enabled = false)
  public void TC_00_1() {
	  driver.get("http://www.myntra.com/");
	  Actions action = new Actions (driver);
	  WebElement hoverIcon = driver.findElement(By.xpath("//div[@data-reactid='512']"));
	  action.moveToElement(hoverIcon).perform();
	  WebElement loginIcon = driver.findElement(By.xpath("//a[text()='login']"));
	  loginIcon.click();
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='login-title']")).isDisplayed());
  }
  
  @Test (enabled = false) 
  public void TC_01() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  List <WebElement> listNumbers = driver.findElements(By.xpath("//ol[@class ='ui-selectable']/li"));
	  Actions moveItem = new Actions(driver);
	  moveItem.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(3)).release().perform();
	  System.out.println("=-=--=0-=-=-=-=-=-=-=-=-");
	  Thread.sleep(1000);
	  List <WebElement> numberSelected = driver.findElements(By.xpath("//ol[@class ='ui-selectable']/li[contains(@class,'ui-selected')]"));
	  
	  Assert.assertEquals(4, numberSelected.size());
  }
  @Test (enabled = false)
  public void TC_02() {
	  driver.get("http://www.seleniumlearn.com/double-click");
	  WebElement ButtonDbClick = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
	  Actions Dbclick = new Actions(driver);
	  Dbclick.doubleClick(ButtonDbClick);
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals("The Button was double-clicked", alert.getText());
  }
  @Test (enabled = false)
  public void TC_03() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  WebElement rightClickMe = driver.findElement(By.xpath("//span[text()='right click me']"));
	  Actions action = new Actions(driver);
	  action.contextClick(rightClickMe);
	  WebElement quitbeforeHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]/span[text()='quit']"));
	  action.moveToElement(quitbeforeHover).perform();
	  WebElement quitAfterHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']"));
	  action.click(quitAfterHover).perform();
	  Alert alert = driver.switchTo().alert();
	  Assert.assertEquals("clicked: quit", alert.getText());
	  alert.accept();
  }
  @Test
  public void TC_04() {
	  driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	  WebElement beforeDrag = driver.findElement(By.xpath("//div[text()='Drag the small circle here.']"));
	  Assert.assertTrue(beforeDrag.isDisplayed());
	  
	  WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement targetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  
	  Actions action = new Actions(driver);
	  action.dragAndDrop(sourceElement, targetElement).build().perform();
	  action.release();
	  
	  WebElement afterDrag = driver.findElement(By.xpath("//div[text()='You did great!']"));
	  Assert.assertTrue(afterDrag.isDisplayed());
	  
	  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}