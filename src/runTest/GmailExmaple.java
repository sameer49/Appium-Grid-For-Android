package runTest;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;

public class GmailExmaple {

	AndroidDriver driver;
	
	public GmailExmaple(AndroidDriver driver) {
		this.driver=driver;
	}

	public void login()
	{
		try
		{
			driver.get("http://gmail.com/");
			Thread.sleep(2000);
			driver.findElement(By.id("Email")).sendKeys("test");
			Thread.sleep(2000);
			driver.findElement(By.id("Passwd")).sendKeys("test");
			Thread.sleep(2000);
			driver.findElement(By.id("signIn")).click();
			Thread.sleep(2000);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	// close driver
	    	driver.quit();
	    }
	}
}
