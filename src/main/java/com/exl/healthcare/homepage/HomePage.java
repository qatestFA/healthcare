package com.exl.healthcare.homepage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.exl.healthcare.testBase.TestBase;

public class HomePage extends TestBase {

	@FindBy(id="searchboxinput")
	WebElement searchInputbox;

	@FindBy(id="searchbox-searchbutton")
	WebElement searchicon;

	@FindBy(id="searchbox-directions")
	WebElement searchboxdirection;

	@FindBy(xpath="//div[@class='directions-travel-mode-icon directions-drive-icon']")
	WebElement caricon;

	@FindBy(xpath="//div[@id='directions-searchbox-0']/div/div/input")
	WebElement src_location;

	@FindBy(xpath="//div[@id='sb_ifc52']/input")
	WebElement dest_location;

	@FindBy(xpath="(//button[@class='searchbox-searchbutton'])[3]")
	WebElement searchcarbutton;

	@FindBy(xpath="(//div[@class='section-directions-trip-description']/div/div[2]/h1/span)[1]")
	WebElement title_firstroute;

	@FindBy(xpath="(//div[@class='section-directions-trip-description']/div/div[2]/h1/span)[2]")
	WebElement title_secondroute;

	@FindBy(xpath="(//div[@class='section-directions-trip-numbers']/div/span)[1]")
	WebElement time_firstroute;

	@FindBy(xpath="(//div[@class='section-directions-trip-description'])[2]/div/div/div[1]/span")
	WebElement time_secondroute;

	@FindBy(xpath="(//div[@class='section-directions-trip-distance section-directions-trip-secondary-text']/div)[1]")
	WebElement distance_firstroute;

	@FindBy(xpath="(//div[@class='section-directions-trip-description'])[2]/div/div/div[2]/div")
	WebElement distance_secondroute;	

	public void searchIngooglemap() throws Exception {
		loadData();
		searchInputbox.sendKeys(OR.getProperty("txt_search"));
		log.info("enter text in searchinput box");
		searchicon.click();
		log.info("click on search button");
		Thread.sleep(4000);
	}

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void searchbycar_direction() throws Exception {
		loadData();
		searchboxdirection.click();
		log.info("click on searchdirection icon");
		caricon.click();
		log.info("click on drive direction icon");
		src_location.clear();
		src_location.sendKeys(OR.getProperty("txt_searchcar_srclocation"));
		log.info("enter source location");
		dest_location.clear();
		dest_location.sendKeys(OR.getProperty("txt_search"));
		log.info("enter deatination location");
		searchcarbutton.click();
		log.info("click on search icon for route");
	}

	public void createTextFile() throws IOException {
		
		BufferedWriter output = null;
		try {
			SimpleDateFormat formater = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
			Calendar calender = Calendar.getInstance();
			File file = new File("routes.txt");
			output = new BufferedWriter(new FileWriter(file));
			output.write(formater.format(calender.getTime())+"_"+"First Route Title: "+title_firstroute.getText()+ " Time: "+time_firstroute.getText()+ " miles: "+distance_firstroute.getText()+" \n");
			output.write(formater.format(calender.getTime())+"_"+"Second Route Title: "+title_secondroute.getText()+ " Time: "+time_secondroute.getText()+ " miles: "+distance_secondroute.getText()+"");
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			if ( output != null ) {
				output.close();
			}
		}
	}

}
