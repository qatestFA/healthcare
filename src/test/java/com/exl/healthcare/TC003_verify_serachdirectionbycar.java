package com.exl.healthcare;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.exl.healthcare.homepage.HomePage;
import com.exl.healthcare.testBase.TestBase;

public class TC003_verify_serachdirectionbycar extends TestBase {
	
	HomePage homepage;
	
	@BeforeClass
	public void setUp() throws Exception {
		init();
	}
	
	@Test
	public void verify_serachdirectionbycar() throws Exception {
		homepage = new HomePage(driver);
		homepage.searchbycar_direction();
		homepage.createTextFile();
	}
	
	@AfterClass
	public void closeBrowser() {
		close();
	}

}
