package tests;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AllTest implements Runnable{
	AndroidDriver driver;
	String deviceId;
	String deviceName;
	String osVersion;
	private Thread t;
	CommandPrompt dc = new CommandPrompt();
	AvailabelPorts ap = new AvailabelPorts();
	static Map<String, String> devices = new HashMap<String, String>();
	static DeviceConfiguration deviceConf = new DeviceConfiguration();
	Thread[] threads = new Thread[10];
	
	AllTest(String deviceId, String deviceName, String osVersion)
	{
		this.deviceId=deviceId;
		this.deviceName=deviceName;
		this.osVersion=osVersion;
	}
	
	public void Gmail()
	{
	  try
		{
		  System.out.println("Device ID: "+deviceId);
		  System.out.println("Device name: "+deviceName);
		  System.out.println("Device OS: "+osVersion);

		  // start appium server
		  String port = ap.getPort();
		  String chromePort = ap.getPort();
		  String bootstrapPort = ap.getPort();
		  
		  dc.startAppium(port, chromePort, bootstrapPort);
		  
		    // create appium driver instance
		    DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability(CapabilityType.VERSION, osVersion);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			capabilities.setCapability("udid", deviceId);
			
			//driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
			driver = new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"),capabilities);
			
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
	    	System.err.println(e.getMessage());
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	// close driver
	    	driver.quit();
	    	dc.stopAppium();
	    }
	}
	
	public void run() {
		Gmail();
	}
	
	public void start()
	{
	   if (t == null)
	   {
	      t = new Thread(this);
	      t.start ();
	   }
	}
	public static void main(String[] args) throws Exception 
	{
		
//		AllTest nexus1 = new AllTest("098976250ddf5049");
//		AllTest nexus2 = new AllTest("04d75df90de3cdc7");
//		AllTest samsung = new AllTest("225fcdbb");
//	 
//		nexus1.start();
//		//nexus2.start();
//		//samsung.start();
//		
		devices = deviceConf.getDivces();
		int numOfDevices = devices.size()/3;
		System.out.println("Number Of Connected Devices : "+numOfDevices);
		AllTest[] deviceThreads = new AllTest[numOfDevices];
		
		for (int i = 0; i < numOfDevices; i++) 
		{
			int deviceCount = (i+1);
		    deviceThreads[i]=new AllTest(devices.get("deviceID"+deviceCount), devices.get("deviceName"+deviceCount), devices.get("osVersion"+deviceCount));		    
		}
		
		for(int i=0;i< numOfDevices;i++)
			deviceThreads[i].start();
	}
	
	
}
