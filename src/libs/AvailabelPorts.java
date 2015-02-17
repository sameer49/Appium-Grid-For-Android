package libs;

import java.net.ServerSocket;

public class AvailabelPorts {
	
	public String getPort() throws Exception
	{
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		String port = Integer.toString(socket.getLocalPort()); 
		socket.close();
		return port;
	}
	
//	public static void main(String[] args) throws Exception 
//	{
//		AvailabelPorts ap = new AvailabelPorts();
//		for(int i=0;i<100;i++)
//			System.out.println(ap.getPort());
//	}

}
