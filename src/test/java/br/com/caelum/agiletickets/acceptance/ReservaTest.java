package br.com.caelum.agiletickets.acceptance;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReservaTest {
	public static String BASE_URL = "http://localhost:8080";
	private WebDriver browser;
	
	@BeforeClass
	public void setUp() throws Exception{
		browser = new FirefoxDriver();
	}
	
	@AfterClass
	public void tearDown(){
		browser.close();
	}
	
}
