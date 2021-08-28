package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ReadExcel;

public class BaseClass {

	public WebDriver driver;
	public String excelFilename;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Properties prop;
	public static Actions builder;
	public static String parentWindow;
	
	
	@BeforeMethod
	public void preCondition() throws IOException {
		
		FileInputStream fis = new FileInputStream("./src/main/resources/files.properties");
		prop = new Properties();
		prop.load(fis);
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		js = (JavascriptExecutor) driver;
		builder = new Actions(driver);
		parentWindow = driver.getWindowHandle();
		

	}
	
	@DataProvider
	public String[][] sendData() throws IOException {
		
		ReadExcel re = new ReadExcel();
		return re.excelRead(excelFilename);

	}
	
	
	  public void switchToLatestWindow() {
	  
	  Set<String> windowHandles = driver.getWindowHandles(); List<String>
	  listwindowHandles = new ArrayList<String>(windowHandles);
	  
	  driver.switchTo().window(listwindowHandles.get(listwindowHandles.size()-1));
	  
	  }
	 
	
	public void switchToParentWindow() {
		
		driver.switchTo().window(parentWindow);

	}
	
	
	
	@AfterMethod
	public void postCondition() {
		
		driver.close();

	}
	

}