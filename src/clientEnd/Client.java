package clientEnd;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	private String hostName;
	private int portNo;
	private Socket clientSocket;
	
	public Client(String hostName, int portNo)
	{
		this.hostName = hostName;
		this.portNo = portNo;
		try{
			this.clientSocket = new Socket(hostName, portNo);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getClientInput()
	{
		Scanner sc = new Scanner(System.in);
		String clientInput = "";
		System.out.print("Enter the expression\n>>> ");
		clientInput = sc.nextLine();
//		System.out.println(clientInput);
		return clientInput;
	}
	
	public void handleIO() throws IOException
	{
		DataInputStream dIn = getDataInputStream();
		DataOutputStream dOut = getDataOutputStream();
		System.out.println("Data streams at client's end initialized!");
		String serverResponse = "";
		String clientInput = "";
		while(serverResponse.indexOf("disconnected") == -1)
		{
			clientInput = getClientInput();
			dOut.writeUTF(clientInput);
			serverResponse = dIn.readUTF();

			if(serverResponse.indexOf("history") != -1)
			{
				System.out.println("Server:\t" + serverResponse);
				int count = 1;
				while((serverResponse = dIn.readUTF()).indexOf("fin") == -1)
					System.out.println((count++) + ") " + serverResponse);
				break;
			}
			if(serverResponse != null)
				System.out.println("Server:\t" + serverResponse);	
		}
	}
	
	public void closeConnection() throws IOException
	{
		this.clientSocket.close();
	}
	
	public DataInputStream getDataInputStream() throws IOException
	{
		DataInputStream dIn = new DataInputStream(this.clientSocket.getInputStream());
		return dIn;
	}
	
	public DataOutputStream getDataOutputStream() throws IOException
	{
		DataOutputStream dOut = new DataOutputStream(this.clientSocket.getOutputStream());
		return dOut;
	}
	
	public static void main(String[] args) throws IOException
	{	
		Client cli = new Client("127.0.0.1", 8080);
		try 
		{
			cli.handleIO();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println("Client shutting down.");
		cli.closeConnection();
	}

}



//package clientEnd;
//import java.io.*;
//import java.net.*;
//import java.util.Scanner;
//
//public class Client {
//	private static String hostName = "127.0.0.1";
//	private static int portNo = 8080;
//	
//	public static void main(String[] args) throws IOException
//	{
//		Socket clientSocket = null;
//		DataInputStream dIn = null;
//		DataOutputStream dOut = null;
//		try 
//		{
//			clientSocket = new Socket(hostName, portNo);
//			dIn = new DataInputStream(clientSocket.getInputStream());
//			dOut = new DataOutputStream(clientSocket.getOutputStream());
//			System.out.println("Data streams at client's end initialized!");
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		
//		Scanner sc = new Scanner(System.in);
//		String clientInput = "";
//		String serverResponse = "";
//		while(serverResponse.indexOf("disconnected") == -1)
//		{	
//			System.out.print("Enter the expression\n>>> ");
//			clientInput = sc.nextLine();
//			dOut.writeUTF(clientInput);
//		//	dOut.flush();
//			serverResponse = dIn.readUTF();
//			
//			if(serverResponse.indexOf("history") != -1)
//			{
//				System.out.println("Server:\t" + serverResponse);
//				int count = 1;
//				while((serverResponse = dIn.readUTF()).indexOf("fin") == -1)
//					System.out.println((count++) + ") " + serverResponse);
//				break;
//			}
//			
//			if(serverResponse != null)
//				System.out.println("Server:\t" + serverResponse);
//			
//		}
//		System.out.println("Client shutting down.");
//		sc.close();
//		dIn.close();
//		dOut.close();
//		clientSocket.close();
//	}
//
//}
