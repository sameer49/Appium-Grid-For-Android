package libs;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest implements Runnable{
	protected AndroidDriver driver;
	protected BaseTest[] deviceThreads;
	protected int numOfDevices;
	protected String deviceId;
	protected String deviceName;
	protected String osVersion;
	protected Thread t;
	protected int deviceCount;
	
	AppiumManager appiumMan = new AppiumManager();
	static Map<String, String> devices = new HashMap<String, String>();
	static DeviceConfiguration deviceConf = new DeviceConfiguration();

	public BaseTest(){
		try {
			devices = deviceConf.getDivces();
			deviceCount = devices.size()/3;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BaseTest(int i){
		int deviceNumber = (i+1);
		this.deviceId = devices.get("deviceID"+deviceNumber);
		this.deviceName = devices.get("deviceName"+deviceNumber);
		this.osVersion = devices.get("osVersion"+deviceNumber);
		setup();
	}
	
	public void setup(){
		try	{
			String port = appiumMan.startAppium(); 			// Start appium server			  
			  
			// create appium driver instance
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability(CapabilityType.VERSION, osVersion);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			//capabilities.setCapability("app", "D:\\work\\Appium-Grid-For-Android\\src\\AndroidCalculator.apk");
			capabilities.setCapability("udid", deviceId);
				
			driver = new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"),capabilities);
		}
		catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	public void start(){
	   if (t == null){
	      t = new Thread(this);
	      t.start ();
	   }
	}

	public void run(){
	}
}
