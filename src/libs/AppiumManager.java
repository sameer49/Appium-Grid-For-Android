package libs;

public class AppiumManager {

	CommandPrompt cp = new CommandPrompt();
	AvailabelPorts ap = new AvailabelPorts();
	
	public void startDefaultAppium()throws Exception
	{
		cp.runCommand("appium --session-override");
		Thread.sleep(5000);
	}
	
	public String startAppium()throws Exception
	{
		// start appium server
		String port = ap.getPort();
		String chromePort = ap.getPort();
		String bootstrapPort = ap.getPort();
					
		String command = "appium --session-override -p "+port+" --chromedriver-port "+chromePort+" -bp "+bootstrapPort;
		System.out.println(command);
		String output = cp.runCommand(command);
		System.out.println(output);
		return port;
	}
	
	public void startAppium(String port, String chromePort, String bootstrapPort)throws Exception
	{
		String command = "appium --session-override -p "+port+" --chromedriver-port "+chromePort+" -bp "+bootstrapPort;
		System.out.println(command);
		String output = cp.runCommand(command);
		System.out.println(output);
	}
	
//	public static void main(String[] args) {
//
//	}

}
