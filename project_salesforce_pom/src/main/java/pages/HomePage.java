package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class HomePage extends BaseClass {
	

	public HomePage(WebDriver browser) {
		
		this.driver = browser;
		
	}
	
	public HomePage clickAppLauncher() {
		
		driver.findElement((By.xpath(prop.getProperty("HomePage.appLauncher.xpath")))).click();
		return this;

	}
	
	public HomePage clickViewAll() {
		//wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(prop.getProperty("HomePage.viewAllButton.xpath")))))
		.click();
		return this;

	}
	
	public IndividualsPage searchApps() {
		
		driver.findElement(By.xpath(prop.getProperty("HomePage.searchBox.xpath")))
		.sendKeys("indivi");
		WebElement individual = driver.findElement(By.xpath("//a[@class='al-tab-item']/span"));
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", individual);
		return new IndividualsPage(driver);

	}
	
	public HomePage clickToRecentUsed() {
		
		driver.findElement(By.xpath("//a[text()='Community']")).click();
		return this;

	}
	
	public HomePage printAppDetails() {
		
		List<WebElement> appName = driver.findElements(By.xpath("//div[text()='Lightning']/ancestor::td/preceding-sibling::th/span/span"));
		List<WebElement> devName = driver.findElements(By.xpath("//div[text()='Lightning']/ancestor::td/preceding-sibling::td[2]"));
		
		System.out.println(appName.size() +"  "+devName.size());
		
		for (int i = 0; i < appName.size(); i++) {
			String text = appName.get(i).getText();
			String text2 = devName.get(i).getText();
			System.out.println("Application Name: "+text+" | Developer Name: "+text2);
		}
		return this;

	}
	
	public HomePage clickProfile() {
		
		driver.findElement(By.xpath(prop.getProperty("HomePage.profile.xpath"))).click();
		return this;

	}
	
	public ClassicModeHomePage navigateToClassicMode() {
		
		driver.findElement(By.xpath(prop.getProperty("HomePage.switchToClassic.xpath"))).click();
		return new ClassicModeHomePage(driver);
		
	}
}