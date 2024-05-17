package base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BasePage 
{
	 public static WebDriver driver;
	 
	 public static ExtentSparkReporter htmlreport;
	 public static ExtentReports report;
	 public static ExtentTest test;
	 public static Properties prop;
	 
	 
	 @BeforeSuite
	 public void initialize()
	 {
		 prop=new Properties();
		 try {
		 prop.load(new FileInputStream("./src/main/java/config/config.properties"));}catch(Exception e) {}
		 
		 htmlreport=new ExtentSparkReporter("./Reports/flipkartresults.html");
		 htmlreport.config().setReportName("Flipkart");
		 htmlreport.config().setDocumentTitle("Flipkart Functional Testing");
		 htmlreport.config().setTheme(Theme.STANDARD);
		 
		 report=new ExtentReports();
		 report.attachReporter(htmlreport);		
	 }
	 
	 @BeforeTest
	 @Parameters({"x"})
	 public void setup(String brname)
	 {
		if(brname.matches("firefox"))
		{
		    driver=new FirefoxDriver();
		}
		if(brname.matches("chrome"))
		{
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	 }
	 public void openUrl()
	 {
		driver.get(prop.getProperty("url"));
		
	 }
	 public void takeScreenshot(String image)
	 {
		try {
		File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f,new File(prop.getProperty("imagelocation")+image));
		test.addScreenCaptureFromPath(prop.getProperty("imagelocation")+image);
		}catch(Exception e) {}
		
	 }
	 @AfterTest
	 public void tearDown()
	 {
		 driver.quit();
	 }
	 @AfterSuite
	 public void saveReport()
	 {
		 report.flush();  //save the report
	 }
}
