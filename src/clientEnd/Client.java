package clientEnd;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	private static String hostName = "127.0.0.1";
	private static int portNo = 8080;
	
	public static void main(String[] args) throws IOException
	{
		Socket clientSocket = null;
		DataInputStream dIn = null;
		DataOutputStream dOut = null;
		try 
		{
			clientSocket = new Socket(hostName, portNo);
			dIn = new DataInputStream(clientSocket.getInputStream());
			dOut = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("Data streams at client's end initialized!");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		String clientInput = "";
		String serverResponse = "";
		while(serverResponse.indexOf("disconnected") == -1)
		{	
			System.out.println("Enter the expression");
			clientInput = sc.nextLine();
			dOut.writeUTF(clientInput);
		//	dOut.flush();
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
		System.out.println("Client shutting down.");
		sc.close();
		dIn.close();
		dOut.close();
		clientSocket.close();
	}

}
