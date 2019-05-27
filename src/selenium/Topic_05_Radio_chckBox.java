package selenium;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility_function.Js_function;

public class Topic_05_Radio_chckBox {
  WebDriver driver;
  @BeforeClass
  public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.manage().window().maximize();

  }
  
  @Test (enabled = false)
  public void TC_00() {
	//Step 01 - Truy cập vào trang: http://live.guru99.com/
	driver.get("http://live.guru99.com/");
	//Step 02 - Click vào link My Account dưới footer (dùng hàm click của Selenium)
	WebElement footer_myaccount = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	footer_myaccount.click();
	//Step 03 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/login/
	String current_url = driver.getCurrentUrl();
	System.out.println(current_url);
	AssertJUnit.assertEquals("http://live.guru99.com/index.php/customer/account/login/", current_url);
	//Step 04 - Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor code)
	WebElement create_account_btn = driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']"));
	Js_function clickJS = new Js_function(driver);
	clickJS.clickElementByJavascript(create_account_btn);
	String crt_account_url = driver.getCurrentUrl();
	System.out.println(crt_account_url);
	AssertJUnit.assertEquals("http://live.guru99.com/index.php/customer/account/create/", crt_account_url);
	
  }	
  @Test (enabled = false)
  public void TC_01() throws Exception {
	  //Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/checkboxes
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  Thread.sleep(2000);
	  //Step 02 - Cick vào checkbox: Dual-zone air conditioning (Thẻ input ko được sử dụng thuộc tính id)
	  WebElement dual_zone = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']"));
	  dual_zone.click();
	  //Step 03 - Kiểm tra checkbox đó đã chọn
	  WebElement checkbox_dualZone = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	  AssertJUnit.assertTrue(checkbox_dualZone.isSelected());
	  //Step 04 - Sau khi checkbox đã được chọn - deselect nó và kiểm tra nó chưa được chọn
	  dual_zone.click();
	  AssertJUnit.assertFalse(checkbox_dualZone.isSelected());

  }	
  @Test (enabled = false)
  public void TC_02() throws Exception {
	  //Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/radios
	  driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
	  Thread.sleep(2000);
	  //Step 02 - Cick vào radiobutton:  2.0 Petrol, 147kW (Thẻ input ko được sử dụng thuộc tính id)
	  WebElement btn_label = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']"));
	  btn_label.click();
	  WebElement btn_radio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
	  boolean result = btn_radio.isSelected();
	  if (result == true)
	  {
		  System.out.println("Radio button already selected");
	  } else {
		  btn_radio.click();
	  }
  }
  @Test (enabled = false)
  public void TC_03() throws Exception{
	  //Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
	  driver.get("http://daominhdam.890m.com/");
	  Thread.sleep(3000);
	  //Step 02 - Click vào button: Click for JS Alert
	  WebElement btn_Click4JsAlert = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
	  btn_Click4JsAlert.click();
      Alert alert = driver.switchTo().alert();
      //Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
      String txt_alert = alert.getText();
}
  
  @Test (enabled = false)
  public void TC_04() throws Exception{
	  //Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
	  driver.get("http://daominhdam.890m.com/");
	  Thread.sleep(3000);
	  //Step 02 - Click vào button: Click for JS Prompt
	  WebElement btn_Click4JsAlert = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
	  btn_Click4JsAlert.click();
      Alert alert = driver.switchTo().alert();
      //Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
      alert.dismiss();
      WebElement result = driver.findElement(By.xpath(".//*[@id='result']"));
      AssertJUnit.assertEquals("You clicked an alert successfully", result.getText());
      
  }
  @Test
  public void TC_05() throws Exception{
	//Step 01 - Truy cập vào trang: http://daominhdam.890m.com/
	  driver.get("http://daominhdam.890m.com/");
	  Thread.sleep(1000);
	  //Step 02 - Click vào button: Click for JS Prompt
	  WebElement btn_Click4JsAlert = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
	  btn_Click4JsAlert.click();
      Alert alert = driver.switchTo().alert();
      alert.sendKeys("daominhdam88");
      alert.accept();
      WebElement result = driver.findElement(By.xpath(".//*[@id='result']"));
      AssertJUnit.assertEquals("You entered: daominhdam88",result.getText());
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}