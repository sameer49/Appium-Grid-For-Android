package libs;
import io.appium.java_client.android.AndroidDriver;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import example.GmailExmaple;

public class BaseTest implements Runnable{
	protected AndroidDriver driver;
	protected BaseTest[] deviceThreads;
	protected int numOfDevices;
	protected String deviceId;
	protected String deviceName;
	protected String osVersion;
	protected String port;
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
	}
	
	public void createDriver(){
		try	{
			port = appiumMan.startAppium(); 			// Start appium server			  
			  
			// create appium driver instance
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability(CapabilityType.VERSION, osVersion);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			//capabilities.setCapability("app", "D:\\work\\Appium-Grid-For-Android\\src\\AndroidCalculator.apk");
			capabilities.setCapability("udid", deviceId);
				
			this.driver = new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"),capabilities);
		}
		catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	public void destroyDriver()
	{
		driver.quit();
		try {
			deviceConf.stopADB();
		} catch (Exception e) {
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
	
	public  <c> void execute()
	{
		Class<?> c;
		try {
			int startMethod = 0;
			// Get extended class name
			c = Class.forName("example.GmailExmaple");
			System.out.println("class : "+c);
			
			// Get start method
			Method[] m = Class.forName("example.GmailExmaple").getMethods();
			System.out.println("methods: "+m.length);
			for(int i=0;i<m.length;i++)	{
				//System.out.println("methods: "+m[i]);
				if(m[i].toString().contains("start")){
					startMethod=i;
					break;
				}
			}
			System.out.println("methods: "+m[startMethod]);
			// get constructor
			Constructor<?> cons = c.getConstructor(Integer.TYPE);
			System.out.println("cons: "+cons);
			
			System.out.println("deviceCount: "+deviceCount);
			// Create array of objects
			Object obj =  Array.newInstance(c, deviceCount);
			for (int i = 0; i < deviceCount; i++) {
                Object val = cons.newInstance(i);
                Array.set(obj, i, val);
            }

			for (int i = 0; i < deviceCount; i++) {
                Object val = Array.get(obj, i);
                m[startMethod].invoke(val);
            }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
