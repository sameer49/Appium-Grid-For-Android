package tests;

import java.io.IOException;

public class process {

	public static void main(String[] args) {
		try {
		    Process[] proc = new Process[1];
		    Process proc1;
//		    proc[0] = new ProcessBuilder("cmd.exe", "/c", "appium --session-override -p 4723 --chromedriver-port 9515 -bp 4724").start();
		    proc1 = new ProcessBuilder("cmd.exe", "/c", "appium --session-override -p 4723 --chromedriver-port 9515 -bp 4724").start();		    
		    //proc1 = new ProcessBuilder("notepad.exe").start();
		    try {
		        Thread.sleep(3000);
		    } catch (InterruptedException ex) {
		    }
		    
		    proc1.destroy();
		    
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		}

	}

}
