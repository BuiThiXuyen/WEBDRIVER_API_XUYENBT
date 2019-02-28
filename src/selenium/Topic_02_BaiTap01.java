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
    String passIncorrect=driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
    Assert.assertEquals(passIncorrect, "Invalid login or password.");
  	}
  	

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  }



