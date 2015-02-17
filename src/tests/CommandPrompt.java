package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandPrompt {
	
	Process p;
	ProcessBuilder builder;
	public String runCommand(String command) throws InterruptedException, IOException
	{
		// build cmd proccess
		builder = new ProcessBuilder("cmd.exe","/c", command);
		builder.redirectErrorStream(true);
		Thread.sleep(1000);
		p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line="";
		String allLine="";
		int i=1;
		while((line=r.readLine()) != null)
		{
	//		System.out.println(i+". "+line);
			allLine=allLine+""+line+"\n";
			//	System.out.println("allLine:"+allLine);
			
			if(line.contains("Console LogLevel: debug"))
					break;
			i++;
		}
		return allLine;
	}
	
	public void startAppium()throws Exception
	{
		runCommand("appium --session-override");
		Thread.sleep(5000);
	}
	
	public void startAppium(String port, String chromePort, String bootstrapPort)throws Exception
	{
		String command = "appium --session-override -p "+port+" --chromedriver-port "+chromePort+" -bp "+bootstrapPort;
		System.out.println(command);
		String output = runCommand(command);
		System.out.println(output);
	}
	
	public void stopAppium()
	{
		p.destroy();
	}
}
