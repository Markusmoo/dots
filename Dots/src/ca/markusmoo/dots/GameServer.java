package ca.markusmoo.dots;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer{

	private int port;
	
	private ServerSocket serverServerSocket;
	private Socket serverSocket;
	private BufferedReader serverInput;
	private PrintStream serverOutput;
	
	public GameServer(int port) {
		this.port = port;
	}
	
	public boolean startServer(){
		try{
			System.out.println("Server is attempting to wait for incoming connection on port="+port+".");
			
			serverServerSocket = new ServerSocket(port);
			
			while(serverSocket != null)
			serverSocket = serverServerSocket.accept();
			
			serverInput = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			serverOutput = new PrintStream(serverSocket.getOutputStream());
			
			String buffer = serverInput.readLine();
			if(buffer != null){
				System.out.println("Client: "+buffer);
				System.out.println("Server: Pong");
				serverOutput.print("Pong");
			}
			
			System.out.println("Server successfully connected to a client at host="+serverSocket.getInetAddress().getHostName()+" port="+serverSocket.getLocalPort()+".");
			
			startRunningServer();
			return true;
		}catch(IOException e){
			System.out.println("Server failed to connect to a client at host on port="+port+".");
			e.printStackTrace();
			return false;
		}
	}
	
	private void startRunningServer(){
		while(true){
			try{
				
			}catch(EOFException eofEx){
				
			}finally{
				stopServer();
			}
		}
	}
	
	public boolean startServer(int port){
		this.port = port;
		return startServer();
	}
	
	public boolean stopServer(){
		try{
			if(serverSocket != null)
				serverSocket.close();
				serverSocket = null;
			if(serverServerSocket != null)
				serverServerSocket.close();
				serverServerSocket = null;
			System.out.println("Server successfully shutdown.");
			return true;
		}catch (IOException e){
			System.out.println("Server failed to shutdown.");
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean sendData(String string){
		return true;
	}
}
*/

public class GameServer{

	private int port;
	
	private ServerSocket serverServerSocket;
	private Socket serverSocket;
	private ObjectInputStream serverInput;
	private ObjectOutputStream serverOutput;
	
	//Ping
	long initialTime;
	
	public GameServer(int port) {
		this.port = port;
	}
	
	public void startServer(){
		try{
			serverServerSocket = new ServerSocket(port);
			while(true){
				try{
					waitForConnection();
					setupStreams();
					checkConnection();
				}catch(EOFException ex){
					System.out.println("Connection Closed");
				}finally{
					stopServer();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void waitForConnection() throws IOException{
		System.out.println("Server is attempting to wait for incoming connection on port="+port+".");
		serverSocket = serverServerSocket.accept();
		System.out.println("Server successfully connected to a client at host="+serverSocket.getInetAddress().getHostName()+" port="+serverSocket.getLocalPort()+".");
	}
	
	private void setupStreams() throws IOException{
		serverOutput = new ObjectOutputStream(serverSocket.getOutputStream());
		serverOutput.flush();
		serverInput = new ObjectInputStream(serverSocket.getInputStream());
		System.out.println("Server successfully created input/output streams.");
	}
	
	private void checkConnection(){
		initialTime = System.currentTimeMillis();
		sendData("ping");
	}
	
	public void stopServer(){
		System.out.println("Server is attempting to shutdown.");
		try{
			serverOutput.close();
			serverInput.close();
			serverSocket.close();
			serverServerSocket.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void sendData(String data){
		try{
			serverOutput.writeObject("SERVER: "+data);
			serverOutput.flush();
		}catch(IOException ex){
			System.out.println("Server failed to send data to client at host="+serverSocket.getInetAddress().getHostName()+" port="+serverSocket.getLocalPort()+".");
		}
	}
}
