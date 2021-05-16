package seleniumPackage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;

public class FirstClass {
	
	public WebDriver loginCriteria(WebDriver driver, String username, String password) {
		driver.findElement(By.id("usernamefield")).sendKeys(username);
		driver.findElement(By.id("passwordfield")).sendKeys(password);
		driver.findElement(By.id("loginsubmitbutton")).click();
		return driver;
	}
	
	public static void main(String args[]) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Files\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		driver.navigate().to("https://library.qc.cuny.edu/spaces/mediascape/");
		
		
		// need library credentials 
		driver.findElement(By.id("usernamefield")).sendKeys(/*username here*/);
		driver.findElement(By.id("passwordfield")).sendKeys(/*password here*/);
		driver.findElement(By.id("loginsubmitbutton")).click();
		
		TimeUnit.SECONDS.sleep(4);
		
		List<WebElement> calendar = driver.findElements(By.xpath("//table[@id='calendarTable']//tbody//tr//td[@class='calendarday']"));
		
		Calendar c = Calendar.getInstance();
		Date currentDate = new Date();
		c.setTime(currentDate);
		int currentHour = c.get(Calendar.HOUR_OF_DAY);
		c.add(Calendar.DAY_OF_MONTH, 14);
		System.out.println(currentDate);
		
		//calendar part
		int countdate = c.get(Calendar.DATE);
		for(WebElement webElement : calendar) {
			if(Integer.parseInt(webElement.getText()) == countdate) {
				driver.findElement(By.xpath("//table[@id='calendarTable']//tbody//tr//td[contains(text(),"+webElement.getText()+")]")).click();
			}
		}
		
		
		//time chart part
		
		//6-> 541
		//5-> 503
		//4-> 445
		//3-> 446
		//2-> 300x
		
		List<WebElement> dayTime = driver.findElements(By.xpath("//table[@id='dayviewTable']//tbody//tr//td[@class='dayviewTime']"));
		
		String currenthour="";
		if(currentHour > 12) {
			currenthour = (currentHour % 12)+":00 pm";
		}else if(currentHour == 12) {
			currenthour = currentHour+":00 pm";
		}else {
			currenthour = currentHour+":00 am";
		}
		
		Actions action = new Actions(driver);
		for(WebElement webElement : dayTime) {
			if(webElement.getText().compareTo(currenthour) == 0) {
				action.moveToElement(webElement);
				action.click().build().perform();
				driver.findElement(By.xpath("//tr[@class='mousedoverrow']//td[3]")).click();
				driver.findElement(By.xpath("//div[@id='popup']//form[@name='reserve']//center//a[contains(text(),'Yes')]")).click();
			}
		}
		
		
		
		driver.close();
	}
}
