package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DeviceConfiguration {


	Map<String, String> devices = new HashMap<String, String>();
	
	public Map<String, String> getDivces() throws Exception
	{
		
		CommandPrompt cmd = new CommandPrompt();
		String output = cmd.runCommand("adb devices");
		//System.out.println(output);
		String[] lines = output.split("\n");
		//System.out.println(lines.length);
		for(int i=1;i<lines.length;i++)
		{
			lines[i]=lines[i].replaceAll("\\s+", "");
			lines[i]=lines[i].replaceAll("device", "");
			String deviceID = lines[i];
			String model = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.product.model").replaceAll("\\s+", "");
			String brand = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.product.brand").replaceAll("\\s+", "");
			String osVersion = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.build.version.release").replaceAll("\\s+", "");
			String deviceName = brand+" "+model;
			
			System.out.println(deviceID);
			devices.put("deviceID"+i, deviceID);
			
			System.out.println(deviceName);
			devices.put("deviceName"+i, deviceName);
			
			System.out.println(osVersion);
			devices.put("osVersion"+i, osVersion);
			
			System.out.println();
			
			
		}
		
		return devices;
	}
	
	public static void main(String[] args) throws Exception {
		DeviceConfiguration gd = new DeviceConfiguration();
		gd.getDivces();
	}

}
