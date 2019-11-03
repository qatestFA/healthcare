package com.exl.healthcare;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.exl.healthcare.testBase.TestBase;

public class TC001_verify_browserlaunch extends TestBase {
	
	@Test
	public void setUp() throws Exception {
		init();
	}
	
	@AfterTest
	public void browserClose() {
		setImplicitWait(40, TimeUnit.SECONDS);
		close();
	}

}
