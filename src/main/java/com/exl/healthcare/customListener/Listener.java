package com.exl.healthcare.customListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.exl.healthcare.testBase.TestBase;

public class Listener extends TestBase implements ITestListener{
	
	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String methodName = result.getName();
		
		if(!result.isSuccess()) {
			
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String reportdirectory = new File(System.getProperty("user.dir")).getAbsolutePath();
			File destFile = new File((String) reportdirectory + "/failure_screenshot/" + methodName + "_" + formater.format(calender.getTime())+ ".png");
			try {
				FileHandler.copy(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}	
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"' height='100' width='100'> </a>");
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		String methodName = result.getName();
		
		if(result.isSuccess()) {
			
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String reportdirectory = new File(System.getProperty("user.dir")).getAbsolutePath();
			File destFile = new File((String) reportdirectory + "/failure_screenshot/" + methodName + "_" + formater.format(calender.getTime())+ ".png");
			try {
				FileHandler.copy(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}	
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"'> <img src='"+destFile.getAbsolutePath()+"' height='100' width='100'> </a>");
		}
		
	}

}
