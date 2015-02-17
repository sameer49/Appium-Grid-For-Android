package libs;

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
}
