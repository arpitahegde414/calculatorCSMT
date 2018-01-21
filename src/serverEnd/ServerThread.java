package serverEnd;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import calculatorLogic.BusinessLogic;


public class ServerThread extends Thread {
	Socket currentThreadSocket;
	String currentClientID;
	ArrayList <String> calculations = new ArrayList<String>();
	public ServerThread(Socket socket,int count)
	{
		currentThreadSocket = socket;
		currentClientID = "Client"+ count;
	}
	public void run()
	{
		try
		{
			//create streams for data flow between client-server
			DataInputStream dIn = new DataInputStream(currentThreadSocket.getInputStream());
			DataOutputStream dOut = new DataOutputStream(currentThreadSocket.getOutputStream());
			dOut.flush();
			String clientInput = "";
			ArrayList <String> clientHistory = new ArrayList<String>();
			BusinessLogic solver = new BusinessLogic();
			while(!clientInput.equals("exit")){
				if((clientInput = dIn.readUTF()) != null)
				{
					if(clientInput.equals("exit"))
						break;
					String result = solver.solve(clientInput);
					clientHistory.add(clientInput + " = " + result);
					dOut.writeUTF("Result = " + result);
				}
			}
			if(clientHistory.size() > 0)
			{
				dOut.writeUTF(currentClientID +", your calculation history :");
				
				for(int i = 0; i < clientHistory.size(); i++)
					dOut.writeUTF(clientHistory.get(i));
				
				dOut.writeUTF("fin");
			}
			dOut.writeUTF(currentClientID +", you have been disconnected!");
			dIn.close();
			dOut.close();
			currentThreadSocket.close();
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
