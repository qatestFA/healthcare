package com.exl.healthcare;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.exl.healthcare.homepage.HomePage;
import com.exl.healthcare.testBase.TestBase;

public class TC004_verifycoordinates  extends TestBase{

	HomePage homepage;

	@BeforeClass
	public void setUp() throws Exception {
		init();
	}

	@Test
	public void verify_searchIngooglemap() throws Exception {
		homepage = new HomePage(driver);
		homepage.searchIngooglemap();
		Thread.sleep(4000);
		String url = driver.getCurrentUrl();
		String[] spliturl = url.split("@");
		String spliturl1 = spliturl[1].toString();
		String[] spliturl2 = spliturl1.split(",");

		if(spliturl2[0]==OR.getProperty("expected_lat") && spliturl2[1]==OR.getProperty("expected_long") ) {
			Reporter.log("Expected lat: "+OR.getProperty("expected_lat") +" Actual lat: "+spliturl2[0]);
			Reporter.log("Expected long: "+OR.getProperty("expected_long") +" Actual long: "+spliturl2[1]);
			Assert.assertTrue(true);
		} else {
			Reporter.log("Expected lat: "+OR.getProperty("expected_lat") +" Actual lat: "+spliturl2[0]);
			Reporter.log("Expected long: "+OR.getProperty("expected_long") +" Actual long: "+spliturl2[1]);
			Assert.assertTrue(false);
		}
	}

	@AfterClass
	public void closeBrowser() {
		close();
	}

}
