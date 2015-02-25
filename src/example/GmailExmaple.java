package example;

import libs.BaseTest;

import org.openqa.selenium.By;


public class GmailExmaple extends BaseTest{

	public GmailExmaple(){
	}
	
	public GmailExmaple(int deviceNum){
		super(deviceNum);
	}
	
	public void login(){
		try{
			driver.get("http://gmail.com/");
			Thread.sleep(2000);
			driver.findElement(By.id("Email")).sendKeys("test");
			Thread.sleep(2000);
			driver.findElement(By.id("Passwd")).sendKeys("test");
			Thread.sleep(2000);
			driver.findElement(By.id("signIn")).click();
			Thread.sleep(2000);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	driver.quit(); 	    	// close driver
	    }
	}
	
	public void run(){
		createDriver();
		login();
	}
	
	public static void main(String[] args) {
		// Create object
		GmailExmaple gmail = new GmailExmaple();
		
		// Get connected device count
		int totalDevices=gmail.deviceCount;
		
		// Initialize threads for each connected devices
		GmailExmaple[] threads = new GmailExmaple[totalDevices];
		
		// Create threads for each connected devices
		for(int i=0;i<totalDevices;i++)
			threads[i] = new GmailExmaple(i);
		
		
		// Start running execution on each device
		for(int i=0;i<totalDevices;i++)
			threads[i].start();
	}
}
