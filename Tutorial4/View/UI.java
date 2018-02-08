package View;
import Controller.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

public class UI implements ActionListener{
	String expression;
	JFrame f;
    JTextField t=new JTextField();
    JButton bclear,bresult;
    double result;
    JPanel panel; 
   /* UI(){
    	f=new JFrame("Calculator");
    	//panel = new JPanel();  
        //panel.setLayout(new FlowLayout()); 
        //t;
        bclear=new JButton("clear");
        bresult=new JButton("result");
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(1000,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        t.setBounds(40,30,500,40);
        bclear.setBounds(40,100,100,40);
        bresult.setBounds(170,100,100,40);
        f.add(t);
        f.add(bclear);
        f.add(bresult);
        bclear.addActionListener(this);
        bresult.addActionListener(this);
        
    }*/
    public void setGUI(){
    	f=new JFrame("Calculator");
    	//panel = new JPanel();  
        //panel.setLayout(new FlowLayout()); 
        //t;
        bclear=new JButton("clear");
        bresult=new JButton("result");
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(1000,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
        t.setBounds(40,30,500,40);
        bclear.setBounds(40,100,100,40);
        bresult.setBounds(170,100,100,40);
        f.add(t);
        f.add(bclear);
        f.add(bresult);
        bclear.addActionListener(this);
        bresult.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bclear)
        	t.setText("");
        if(e.getSource()==bresult)
        {
        	expression=t.getText();
        	StringParserController c=new StringParserController();
        	double result= c.solve(expression);
        	t.setText(Double.toString(result));
        	System.out.println(expression);
        	System.out.println(result);
       
        }
        
    }
	public static void main(String[] args)
	{
		UI calc=new UI();
		calc.setGUI();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter expression");
		String s=sc.nextLine();
		StringParserController spc=new StringParserController();
		double result=spc.solve(s);
		calc.t.setText(Double.toString(result));
		System.out.println(result);
	}
}
