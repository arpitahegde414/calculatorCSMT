package serverEnd;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import calculatorLogic.BusinessLogic;
import operands.Operand;

public class ServerThread extends Thread {
	private Socket currentThreadSocket;
	private String currentClientID;
	private ArrayList <String> clientHistory = new ArrayList<String>();
	private DataInputStream dIn;
	private DataOutputStream dOut;
	
	public ServerThread(Socket socket,int count) throws IOException
	{
		this.currentThreadSocket = socket;
		this.currentClientID = "Client"+ count;
		this.dOut = new DataOutputStream(this.currentThreadSocket.getOutputStream());
		this.dIn = new DataInputStream(this.currentThreadSocket.getInputStream());
	}
	
	public void printClientHistory() throws IOException
	{
		if(clientHistory.size() > 0)
		{
			dOut.writeUTF(currentClientID +", your calculation history :");
			
			for(int i = 0; i < clientHistory.size(); i++)
				dOut.writeUTF(clientHistory.get(i));
			
			dOut.writeUTF("fin");
			
			dOut.writeUTF(currentClientID +", you have been disconnected!");
		}
	}
	
	public void handleClientIO() throws IOException
	{
		dOut.flush();
		String clientInput = "";
		BusinessLogic solver = new BusinessLogic();
		while(!clientInput.equals("exit")){
			if((clientInput = dIn.readUTF()) != null)
			{
				if(clientInput.equals("exit"))
					break;
				try {
					Operand result = solver.solve(clientInput);
					clientHistory.add(clientInput + " = " + result.show());
					dOut.writeUTF("Result = " + result.show());
				} catch (Exception e) {
					dOut.writeUTF(e.getMessage());
				}
			}
		}
	}
	
	public void releaseResources() throws IOException
	{
		dIn.close();
		dOut.close();
		currentThreadSocket.close();
	}
	
	public void run()
	{
		try
		{
			//create streams for data flow between client-server
			handleClientIO();
			printClientHistory();
			releaseResources();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


//package serverEnd;
//import java.io.*;
//import java.net.*;
//import java.util.ArrayList;
//
//import calculatorLogic.BusinessLogic;
//import operands.Operand;
//
//
//public class ServerThread extends Thread {
//	Socket currentThreadSocket;
//	String currentClientID;
//	ArrayList <String> calculations = new ArrayList<String>();
//	public ServerThread(Socket socket,int count)
//	{
//		currentThreadSocket = socket;
//		currentClientID = "Client"+ count;
//	}
//	public void run()
//	{
//		try
//		{
//			//create streams for data flow between client-server
//			DataInputStream dIn = new DataInputStream(currentThreadSocket.getInputStream());
//			DataOutputStream dOut = new DataOutputStream(currentThreadSocket.getOutputStream());
//			dOut.flush();
//			String clientInput = "";
//			ArrayList <String> clientHistory = new ArrayList<String>();
//			BusinessLogic solver = new BusinessLogic();
//			while(!clientInput.equals("exit")){
//				if((clientInput = dIn.readUTF()) != null)
//				{
//					if(clientInput.equals("exit"))
//						break;
//					System.out.println(currentClientID + "\t" + clientInput);
////					Thread.sleep(5000);
//					try {
//						Operand result = solver.solve(clientInput);
//						clientHistory.add(clientInput + " = " + result.show());
//						dOut.writeUTF("Result = " + result.show());
//					} catch (Exception e) {
//						dOut.writeUTF(e.getMessage());
//					}
//				}
//			}
//			if(clientHistory.size() > 0)
//			{
//				dOut.writeUTF(currentClientID +", your calculation history :");
//				
//				for(int i = 0; i < clientHistory.size(); i++)
//					dOut.writeUTF(clientHistory.get(i));
//				
//				dOut.writeUTF("fin");
//			}
//			dOut.writeUTF(currentClientID +", you have been disconnected!");
//			dIn.close();
//			dOut.close();
//			currentThreadSocket.close();
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//}
