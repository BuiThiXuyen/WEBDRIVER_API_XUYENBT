package selenium;

import org.openqa.selenium.Alert;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;
import utility_function.Js_function;

public class Topic_08_JS_Executor {
  WebDriver driver;
  @BeforeClass
  public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver","\\driver\\chromedriver.exe");
		driver = new FirefoxDriver();
	  	driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://google.com");
  }
  @Test
  public void TC_01() {
	  driver.get("http://live.guru99.com/");
	  // them cai (string) de ep kieu tu Object -> String
	  String domain = (String) executeJSForBrowserElement("return document.domain;");
	  Assert.assertEquals("live.guru99.com", domain);
	  
	  String url = (String) executeJSForBrowserElement("return document.url;");
	  Assert.assertEquals("live.guru99.com", domain);
	  
	  WebElement mobilePage = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  clickBYJSForWebElement(mobilePage); 
	  
	  WebElement samsungProduct = driver.findElement(By.xpath("//h2[a[@title='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
	  clickBYJSForWebElement(samsungProduct); 
	  
	  String addToCart = (String) executeJSForBrowserElement("return document.documentElement.innerText");
	  Assert.assertTrue(addToCart.contains("Samsung Galaxy was added to your shopping cart."));
	  
	  WebElement privacyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	  clickBYJSForWebElement(privacyLink);
	  
	  String privacyTitle = (String) executeJSForBrowserElement("return document.title");
	  Assert.assertEquals("Privacy Policy", privacyTitle);
	  scrollToBottomPage();
	  WebElement wishlist = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
	  Assert.assertTrue(wishlist.isDisplayed());
	  
	  String demoGuruSite = "http://demo.guru99.com/v4/";
	  executeJSForBrowserElement("window.location ='" + demoGuruSite + "'");
	  
	  String domainDemoGuru = (String) executeJSForBrowserElement ("return document.domain;");
	  Assert.assertEquals("demo.guru99.com", domainDemoGuru);
  }
  
  @Test
  public void TC_02() {
	  driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
	  WebElement iframeResult = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
	  driver.switchTo().frame(iframeResult);
	  WebElement lastNameTextbox = driver.findElement(By.xpath("//input[@name = 'lname']"));
	  removeAttributeInDOM(lastNameTextbox, "disabled");
	  lastNameTextbox.sendKeys("Loc abc");
	  WebElement valueSubmit = driver.findElement(By.xpath("//input[@value = 'Submit']"));
	  valueSubmit.click();
	  WebElement success = driver.findElement(By.xpath("//div[contains(text(),'Loc abc')]"));
	  Assert.assertTrue(success.isDisplayed());

  }
  
  
  public Object executeJSForBrowserElement (String javascript) {
	  try {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  return js.executeScript(javascript);
	  } catch (Exception e) {
		  e.getMessage();
		  return null;
	  }
  }
  
  public Object clickBYJSForWebElement (WebElement element) {
	  try {
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  return js.executeScript("arguments[0].click();", element);
	  } catch (Exception e) {
		  e.getMessage();
		  return null;
	  }
  }
  
  public void highlightElement (WebElement element) {
	  JavascriptExecutor js = (JavascriptExecutor) driver; 
	  js.executeScript("arguments[0].style.border = '6px groove red'", element);
  }
  
  public Object scrollToBottomPage() {
                        try {
                                    JavascriptExecutor js = (JavascriptExecutor) driver;
                                    return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
                        } catch (Exception e) {
                                    e.getMessage();
                                    return null;
                        }
            }
  
  public Object removeAttributeInDOM(WebElement element, String attribute) {
      try {
                  JavascriptExecutor js = (JavascriptExecutor) driver;
                  return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
      } catch (Exception e) {
                  e.getMessage();
                  return null;
      }
}
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
