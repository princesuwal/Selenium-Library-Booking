package seleniumPackage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;

public class AutomationSelenium {
	
	public WebDriver createWebDriver(){
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Files\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://library.qc.cuny.edu/spaces/mediascape/");
		
		return driver;
	}
	
	public WebDriver loginCriteria(WebDriver driver, String username, String password) {
		driver.findElement(By.id("usernamefield")).sendKeys(username);
		driver.findElement(By.id("passwordfield")).sendKeys(password);
		driver.findElement(By.id("loginsubmitbutton")).click();
		return driver;
	}
	
	public static void main(String args[]) throws InterruptedException {
		//wangdu
		//twangdu100
		//Tw189220619
		
		//niraj
		//nlama100
		//Jaguar17.
		
		//rani
		//rshrestha103
		//Esaelana15
		AutomationSelenium as = new AutomationSelenium();
		
		Calendar ca = Calendar.getInstance();
		int myTime = ca.get(Calendar.HOUR_OF_DAY);
		
		String cUser, cPass;
		
		while(true){
			System.out.println(myTime);
			if(myTime == 10 || myTime == 11){
				cUser = "twangdu100";
				cPass = "Tw189220619";
			}else if(myTime == 12 || myTime == 13){
				cUser = "rshrestha103";
				cPass = "Esaelana15";
			}else if(myTime == 14 || myTime == 15){
				cUser = "nlama100";
				cPass = "Jaguar17.";
			}else{
				cUser = "psuwal100";
				cPass = "Anju78925";
			}
			
			WebDriver driver = as.createWebDriver();
			as.loginCriteria(driver, cUser, cPass);
			TimeUnit.SECONDS.sleep(2);	// wait time for page to load
			
			
			//calendar part of web-page
			Calendar c = Calendar.getInstance();
			Date currentDate = new Date();
			c.setTime(currentDate);
			int currentHour = c.get(Calendar.HOUR_OF_DAY);
			int maxDate = c.getActualMaximum(Calendar.DATE);
			int countdate = c.get(Calendar.DATE) + 14;
			if(countdate <= maxDate){
				List<WebElement> calendar = driver.findElements(By.xpath("//table[@id='calendarTable']//tbody//tr//td[@class='calendarday']"));
				for(WebElement webElement : calendar) {
					if(Integer.parseInt(webElement.getText()) == countdate) {
						driver.findElement(By.xpath("//table[@id='calendarTable']//tbody//tr//td[contains(text(),"+webElement.getText()+")]")).click();
					}
				}
			}
			else{
				countdate = countdate % 30;
				driver.findElement(By.xpath("//table[@id='calendarTableHeader']//tbody//tr//td[@id='nextmonth']")).click();
				List<WebElement> calendar = driver.findElements(By.xpath("//table[@id='calendarTable']//tbody//tr//td[@class='calendarday']"));
				for(WebElement webElement : calendar) {
					if(Integer.parseInt(webElement.getText()) == countdate) {
						driver.findElement(By.xpath("//table[@id='calendarTable']//tbody//tr//td[contains(text(),"+webElement.getText()+")]")).click();
					}
				}
			}
			
			//time chart part
			//6-> 541  //5-> 503  //4-> 445  //3-> 446  //2-> 300x
			String currenthour="";
			if(currentHour > 12) {
				currenthour = (currentHour % 12)+":00 pm";
			}else if(currentHour == 12) {
				currenthour = currentHour+":00 pm";
			}else {
				currenthour = currentHour +":00 am";
			}
			
			List<WebElement> dayTime = driver.findElements(By.xpath("//table[@id='dayviewTable']//tbody//tr//td[@class='dayviewTime']"));
			
			Actions action = new Actions(driver);
			for(WebElement webElement : dayTime) {
				if(webElement.getText().compareTo(currenthour) == 0) {
					action.moveToElement(webElement);
					action.click().build().perform();
					driver.findElement(By.xpath("//tr[@class='mousedoverrow']//td[3]")).click();
					driver.findElement(By.xpath("//div[@id='popup']//form[@name='reserve']//center//a[contains(text(),'Yes')]")).click();
				}
			}
			driver.findElement(By.xpath("//div[@id='heading']//a[contains(text(),'Logout')]")).click();
			
			driver.close();
			driver.quit();
			TimeUnit.MINUTES.sleep(60);
			myTime = ca.get(Calendar.HOUR);
		}//while ends
	}
}
