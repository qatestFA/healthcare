/**
 * 
 */
package com.exl.healthcare.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

import com.exl.healthcare.customListener.Listener;

/**
 * @author Fahim
 *
 */
public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public static WebDriver driver;
	public Properties OR;
	Listener lis;

	public void init() throws Exception {
		loadData();
		PropertyConfigurator.configure(OR.getProperty("log4jConfPath"));
		selectBrowser(OR.getProperty("browser"));
		getUrl(OR.getProperty("url"));
	}

	public void getUrl(String url) {
		try {
			driver.get(url);
			log.info("Navigate to " +url);
			driver.manage().window().maximize();
			setImplicitWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectBrowser(String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver(options);
			log.info("Creating Object of "+browser);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Creating Object of "+browser);
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("Creating Object of "+browser);
		}

	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
		try {
			driver.manage().timeouts().implicitlyWait(timeout, unit);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadData() throws Exception {
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\exl\\healthcare\\config\\OR.properties");
		FileInputStream fis = new FileInputStream(file);
		OR = new Properties();
		OR.load(fis);
	}

	public void close() {
		try {
			//driver.close();
			//log.info("Browser Closed!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getscreenshotCapture() {
		
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String reportdirectory = new File(System.getProperty("user.dir")).getAbsolutePath();
		File destFile = new File((String) reportdirectory + "/failure_screenshot/" + "_" + formater.format(calender.getTime())+ ".png");
		try {
			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"' height='100' width='100'> </a>");
	}

}
