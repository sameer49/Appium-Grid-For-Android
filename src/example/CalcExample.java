package example;

import java.io.File;

import libs.BaseTest;

import org.openqa.selenium.By;

public class CalcExample extends BaseTest{

	public CalcExample(){
	}
	
	public CalcExample(int deviceNum) {
		super(deviceNum);
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
			driver.closeApp();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	public void run(){
		File app = new File("src/example/AndroidCalculator.apk");
		String appPath = app.getAbsolutePath();
		createDriver(appPath); // create devices
		performOperations(); // user function
	}

	public static void main(String[] args) {
		// Create object
		CalcExample calc = new CalcExample();
		calc.execute();
	}
}
