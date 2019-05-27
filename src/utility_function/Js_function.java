package utility_function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Js_function {
	private static WebDriver driver = null;
	public Js_function(WebDriver driver){
		this.driver = driver;
	}
	public void clickElementByJavascript(WebElement element) {
	    JavascriptExecutor je = (JavascriptExecutor) driver;
	    je.executeScript("arguments[0].click();", element);
	}
}