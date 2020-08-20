package com.Stackoverflow.StackoverflowUI;


import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Properties;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Stackoverflow.CommonUtils.ObjectRepo;
import com.Stackoverflow.CommonUtils.StackoverflowTestBase;
import com.Stackoverflow.Reports.Log;


public class StackoverflowTest {
	
	HashMap<String, Integer> tagNameWithNumber = new LinkedHashMap<String, Integer>();
	HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>(); 
	String KeyWithMaxQuestions;

	WebDriver driver = null;
	Properties Obj = null;
	

	@BeforeClass
	public void setup() throws IOException, Exception {
		Obj = ObjectRepo.getInstance();
		System.setProperty("webdriver.chrome.driver", Obj.getProperty("chromeDriverPath"));		
		driver = new ChromeDriver();
	}

	@Test
	public void testStackoverflowUI() throws Exception {
			driver.get(Obj.getProperty("base_url"));
			driver.manage().window().maximize();
			WebElement searchBar=driver.findElement(By.xpath(Obj.getProperty("searchBar"))); // identify the searchbar on stackoverflow page
			searchBar.sendKeys(Obj.getProperty("dummyData"));                                                   // Dummy test data
			searchBar.sendKeys(Keys.RETURN);
			 
			WebElement tags=driver.findElement(By.xpath(Obj.getProperty("tagsTab")));            //clicking on Tag tab
			tags.click();
			
			WebElement name=driver.findElement(By.xpath(Obj.getProperty("nameTab")));            //clicking on Name tab
			name.click();
			
			List<WebElement> allGrids = driver.findElements(By.xpath(Obj.getProperty("listOfGrids"))); // will return all grid on first page
			
		 for(int i=1;i<=allGrids.size();i++) {
			 
			String tagName=driver.findElement(By.xpath(Obj.get("gridName") + "[" + i + "]")).getText(); // Getting text for every tag name
			Log.info("Every Tag name on grid of first page  " + tagName);

			String numberWithQuestions =driver.findElement(By.xpath(Obj.getProperty("numberWithQuestions") + "[" + i + "]")).getText(); // Getting questions value for every tag name
			Log.info("Nubers with questions present below tag names   " + numberWithQuestions);
			
			int index =	numberWithQuestions.indexOf(" ");
			String numberFormat=(numberWithQuestions.substring(0, index)); 					// Will get numeric value without word question
			int numberWithoutQuestions = Integer.parseInt(numberFormat);
			Log.info("number accociated with tag and question  " + numberWithoutQuestions);
			
			tagNameWithNumber.put(tagName, numberWithoutQuestions); 							// Inserted value and tagname in hashmap
		}
		 
			Log.info("Map before sort {} "+ tagNameWithNumber);
			sortedMap= StackoverflowTestBase.sortByValue(tagNameWithNumber);   					// Getting sorted map
			Log.info("Sorted map " +sortedMap);
			
			int MaxNumber=(Integer) sortedMap.values().toArray()[sortedMap.size()-1];  			// Identifying the last Value as max number
			Log.info("highest value in array  " + MaxNumber);    
			
			KeyWithMaxQuestions = StackoverflowTestBase.getKeyByValue(sortedMap,MaxNumber);    // Identify the key for the max value
			Log.info("Tag with max questions: "+ KeyWithMaxQuestions);
			System.out.println("Tag with max questions: "+ KeyWithMaxQuestions);	
		}
	
	@AfterMethod
	public void closer() {
		driver.quit();

	}
	
}
