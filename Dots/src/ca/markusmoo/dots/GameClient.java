package ca.markusmoo.dots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class GameClient{
	
	private String ipAddress;
	private int port;
	
	private boolean isConnected;
	
	private Socket clientSocket;
	private BufferedReader clientInput;
	private PrintStream clientOutput;
	
	public GameClient(String ipAddress){
		isConnected = false;
		if(ipAddress.contains(":")){
			String[] ipAndPort = splitIpPort(ipAddress.trim());
			this.ipAddress = ipAndPort[0];
			this.port = Integer.parseInt(ipAndPort[1]);
		}else if(ipAddress.toLowerCase().equals("localhost")){
			this.ipAddress = "localhost";
			this.port = 26656;
		}else{
			this.ipAddress = ipAddress;
			this.port = 26656;
		}
	}
	
	public boolean connect(){
		isConnected = false;
		try{
			System.out.println("Client attmepting to connect to server at host="+ipAddress+" port="+port+".");
			
			clientSocket = new Socket(ipAddress, port);
			
			clientInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			clientOutput = new PrintStream(clientSocket.getOutputStream());
			
			System.out.println("Client: Ping");
			clientOutput.print("Ping\n");
			
			String buffer = clientInput.readLine();
			if(buffer != null)
				System.out.println("Server: "+buffer);
			
			System.out.println("Client successfully connected to server at host="+ipAddress+" port="+port+".");
			
			isConnected = true;
			return true;
		}catch(IOException e){
			System.out.println("Client failed to connect to server at host="+ipAddress+" port="+port+".");
			return false;
		}
	}
	
	public boolean connect(String ipAddressWithPort){
		if(ipAddress.contains(":")){
			String[] ipAndPort = splitIpPort(ipAddressWithPort.trim());
			this.ipAddress = ipAndPort[0];
			this.port = Integer.parseInt(ipAndPort[1]);
		}else if(ipAddress.toLowerCase().equals("localhost")){
			this.ipAddress = "localhost";
			this.port = 26656;
		}else{
			this.ipAddress = ipAddressWithPort;
			this.port = 26656;
		}
		return connect();
	}
	
	private boolean sendData(String string){
		return true;
	}
	
	public boolean isConnected(){
		return isConnected;
	}
	
	//Helper method
	private String[] splitIpPort(String ipAddressWithPort){
		String[] ipAddressAndPort;
		ipAddressAndPort = ipAddressWithPort.split(":");
		
		return ipAddressAndPort;
	}

}
