package sub;

//This program will create a JPanel a fill it with what is created in the Sub.java class


//File Name: DiveGame


import javax.swing.*;

public class DiveGame
{
public static void main(String[] args)  // main method for the Jframe
{
  JFrame frame=new JFrame("Submarine");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  Sub submarine = new Sub();  
  frame.getContentPane().add(submarine);    
  frame.pack();
  frame.setVisible(true);// Makes the JFrame visible.
}
}  