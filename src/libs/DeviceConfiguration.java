package libs;

import java.util.HashMap;
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
		if(lines.length<=1)
		{
			System.out.println("No device connected");
			System.exit(0);
		}
		
		for(int i=1;i<lines.length;i++)
		{
			lines[i]=lines[i].replaceAll("\\s+", "");
			
			if(lines[i].contains("device"))
			{
				System.out.println("Following device is connected");
				
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
			else if(lines[i].contains("unauthorized"))
			{
				System.out.println("Following device is unauthorized");
				
				lines[i]=lines[i].replaceAll("unauthorized", "");
				String deviceID = lines[i];
				
				System.out.println(deviceID);
				System.out.println();
			}
			else if(lines[i].contains("offline"))
			{
				System.out.println("Following device is offline");
				
				lines[i]=lines[i].replaceAll("offline", "");
				String deviceID = lines[i];
				
				System.out.println(deviceID);
				System.out.println();
			}
		}
		
		return devices;
	}
	
//	public static void main(String[] args) throws Exception {
//		DeviceConfiguration gd = new DeviceConfiguration();
//		gd.getDivces();
//	}

}
