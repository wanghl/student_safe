package com.zephyr.studentsafe.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageWindow {
	
	public static void show(String message){
		JFrame jframe = new JFrame();
		JOptionPane.showMessageDialog(jframe, message);
	}
	

}
