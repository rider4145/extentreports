package reports.extentreports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class reports {
	
	ExtentReports reports;								// declare the object variable outside methods to access over other methods
	ExtentSparkReporter report;

	@BeforeTest
	public void config()
	{
		reports = new ExtentReports();
		String path = System.getProperty("user.dir")+"//reports//index.html";	// path where the reports has to be saved
		report = new ExtentSparkReporter(path);				// passing the path inside reporter object
		
		report.config().setReportName("Automation Reports - Web");
		report.config().setDocumentTitle("Reports");
		
		// attach the report to main extent report object
		reports.attachReporter(report);
		reports.setSystemInfo("Tester", "Rahul N");
		
	}
	
	@Test
	public void demo()
	{
		ExtentTest test =reports.createTest("demo");			// create test will handle the passed and failed method and generate report. Pass testname
		System.setProperty("WebDriver.chrome.driver", "C://Users//Sudha//Downloads//rahul//chromedriver//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		String title = driver.getTitle();
		System.out.println(title);
		
		test.fail("Result don't match");				// this method is used to fail the method
		test.addScreenCaptureFromBase64String(title); 	// by adding this line, automatically screenshot will be added to specific method in report 
		reports.flush();					// to notify the test is completed/done
		
		
	}
	
}
