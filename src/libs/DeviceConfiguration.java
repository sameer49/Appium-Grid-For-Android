package libs;

import java.util.HashMap;
import java.util.Map;


public class DeviceConfiguration {


	Map<String, String> devices = new HashMap<String, String>();
	
	public Map<String, String> getDivces() throws Exception	{
		
		CommandPrompt cmd = new CommandPrompt();
		cmd.runCommand("adb devices"); // run command only to start adb tool
		String output = cmd.runCommand("adb devices");
		String[] lines = output.split("\n");

		if(lines.length<=1){
			System.out.println("No Device Connected");
			System.exit(0);	// exit if no connected devices found
		}
		
		for(int i=1;i<lines.length;i++){
			lines[i]=lines[i].replaceAll("\\s+", "");
			
			if(lines[i].contains("device")){
				lines[i]=lines[i].replaceAll("device", "");
				String deviceID = lines[i];
				String model = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.product.model").replaceAll("\\s+", "");
				String brand = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.product.brand").replaceAll("\\s+", "");
				String osVersion = cmd.runCommand("adb -s "+deviceID+" shell getprop ro.build.version.release").replaceAll("\\s+", "");
				String deviceName = brand+" "+model;
				
				devices.put("deviceID"+i, deviceID);
				devices.put("deviceName"+i, deviceName);
				devices.put("osVersion"+i, osVersion);
				
				System.out.println("Following device is connected");
				System.out.println(deviceID+" "+deviceName+" "+osVersion+"\n");
			}else if(lines[i].contains("unauthorized")){
				lines[i]=lines[i].replaceAll("unauthorized", "");
				String deviceID = lines[i];
				
				System.out.println("Following device is unauthorized");
				System.out.println(deviceID+"\n");
			}else if(lines[i].contains("offline")){
				lines[i]=lines[i].replaceAll("offline", "");
				String deviceID = lines[i];
				
				System.out.println("Following device is offline");
				System.out.println(deviceID+"\n");
			}
		}
		return devices;
	}
	
//	public static void main(String[] args) throws Exception {
//		DeviceConfiguration gd = new DeviceConfiguration();
//		gd.getDivces();
//	}

}
