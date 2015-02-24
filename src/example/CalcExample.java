package example;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class CalcExample {

	AndroidDriver driver;
	
	public CalcExample(AndroidDriver driver) {
		this.driver=driver;
	}
	
	public void performOperations() {
		
		try
		{
			driver.findElement(By.id("com.android2.calculator3:id/cling_dismiss")).click();
			driver.findElement(By.id("com.android2.calculator3:id/digit5")).click();
			driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
			driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
			driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
			String num = driver.findElement(By.xpath("//android.widget.EditText[@index=0]")).getText();
			System.out.println("Result : "+num);
			Thread.sleep(2000);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	// close driver
	    	driver.closeApp();
	    }
	}

}
