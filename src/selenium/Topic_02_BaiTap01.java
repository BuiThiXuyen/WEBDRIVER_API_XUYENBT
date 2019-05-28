package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_BaiTap01 {
 
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://live.guru99.com/");
	  
  }

  	@Test
	public void TC_01_Login_Empty() {
  	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
  	  driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
  	  driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]")).click();
  	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
  	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
  	  driver.findElement(By.xpath(".//*[@id='send2']")).click();
  	  String emailRequired=driver.findElement(By.xpath("//*[@id='advice-required-entry-email']")).getText();
  	  Assert.assertEquals(emailRequired, "This is a required field.");
  	  String passRequired = driver.findElement(By.xpath("//*[@id='advice-required-entry-pass']")).getText();
  	  Assert.assertEquals(passRequired, "This is a required field.");
  	  //driver.quit();
  	}
 
  	@Test
  	public void TC_02_Login_with_Email_invalid() {
  	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
    driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]")).click();
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("23434234@12312.123123");
    //driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
    driver.findElement(By.xpath(".//*[@id='send2']")).click();
    String emailIvalid= driver.findElement(By.xpath("//input[@id='email']/following-sibling::div[@class='validation-advice']")).getText();
    Assert.assertEquals(emailIvalid, "Please enter a valid email address. For example johndoe@domain.com.");
  	}

  	@Test
  	public void TC_03_Invalid_Password() {
  	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
    driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]")).click();
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
    driver.findElement(By.xpath(".//*[@id='send2']")).click();
    String passInvalid=driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div[@class='validation-advice']")).getText();
    Assert.assertEquals(passInvalid, "Please enter 6 or more characters without leading or trailing spaces.");
  	}
  	
  	
  	@Test
  	public void TC_04_Incorrect_Password() {
  	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
    driver.findElement(By.xpath(".//*[@id='header-account']/div/ul/li[1]")).click();
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
    driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
    driver.findElement(By.xpath(".//*[@id='send2']")).click();    
    String passIncorrect=  driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div/ul/li/ul/li")).getText();
    Assert.assertEquals(passIncorrect,"Invalid login or password.");
  	}
  	
  	@Test
  	public void TC_05() {
  	    driver.findElement(By.xpath("//img[@class='large']")).click(); 
  	    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'][contains(text(),'My Account')]")).click();
  		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
  		driver.findElement(By.xpath("//input[@title='First Name']")).sendKeys("Xuyen");
  		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Thi");
  		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Bui");
  		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("btx@gmail.com");
  		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
  		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
  		driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
  		driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();
  		String LoginRequired = driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]")).getText();
  	    Assert.assertEquals(LoginRequired, "Thank you for registering with Main Website Store.");
  		driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
  		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
  	}

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  }



