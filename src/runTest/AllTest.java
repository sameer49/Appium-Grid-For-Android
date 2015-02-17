package runTest;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import libs.AppiumManager;
import libs.DeviceConfiguration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AllTest implements Runnable{
	AndroidDriver driver;
	String deviceId;
	String deviceName;
	String osVersion;
	private Thread t;
	
	AppiumManager appiumMan = new AppiumManager();
	static Map<String, String> devices = new HashMap<String, String>();
	static DeviceConfiguration deviceConf = new DeviceConfiguration();
	

	public AllTest() {
	
	}
	
	AllTest(String deviceId, String deviceName, String osVersion)
	{
		this.deviceId=deviceId;
		this.deviceName=deviceName;
		this.osVersion=osVersion;
	}
	
	public void setup()
	{
		try
		{
			System.out.println("Device ID: "+deviceId);
			System.out.println("Device name: "+deviceName);
			System.out.println("Device OS: "+osVersion);
	
			// Start appium server			  
			String port = appiumMan.startAppium();
			  
			// create appium driver instance
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability(CapabilityType.VERSION, osVersion);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			capabilities.setCapability("udid", deviceId);
				
			//driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
			driver = new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"),capabilities);
		}
		catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	
	
	public void run() {
		setup();
		GmailExmaple gmail = new GmailExmaple(driver);
		gmail.login();
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
