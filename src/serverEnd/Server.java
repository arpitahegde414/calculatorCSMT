package serverEnd;

import java.io.*;
import java.net.*;
//import java.util.Scanner;
public class Server {
//	private static String hostName = "127.0.0.1";
	private static int portNo = 8080;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
	{
		ServerSocket service = null;
		Socket serverSocket = null;
//		Scanner sc = new Scanner(System.in);
		int clientCounter = 0;
		service = new ServerSocket(portNo);
		System.out.println("Server up and running on port:\t"+portNo);
		String serverShutdown = "";
		while(serverShutdown.equals(""))//be ever ready to handle client requests
			try 
			{
				serverSocket = service.accept();
				clientCounter ++;
				ServerThread serverThread = new ServerThread(serverSocket, clientCounter); //instantiate a new Server Thread to serve the newly connected client
				serverThread.start();
//				if(sc.hasNext())
//					serverShutdown = sc.next();
			}catch(Exception e) 
			{
				e.printStackTrace();
			}
		System.out.println("Server Shutdown");
//		-test cases
//		-execute using jUnit FW
		}
		
	}
