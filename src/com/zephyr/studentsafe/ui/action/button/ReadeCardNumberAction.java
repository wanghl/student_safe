package com.zephyr.studentsafe.ui.action.button;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.serialport.RfidReader;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.ui.action.ImportDataToJTableAction;
import com.zephyr.studentsafe.ui.dialog.StudentImportMainFrame;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ReadeCardNumberAction implements ActionListener {
	private static final Logger log = Logger.getLogger(ReadeCardNumberAction.class);

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Thread rfidreader = new Thread(new RfidReader());
		rfidreader.start();
		StudentImportMainFrame.getTable().requestFocus() ;
		((JButton)e.getSource()).setEnabled(false);
		

	}

	public static void updateStudentCardNumber(String rfic) {
		ImportDataToJTableAction.insertCardNumber(rfic, null);
	}

}


