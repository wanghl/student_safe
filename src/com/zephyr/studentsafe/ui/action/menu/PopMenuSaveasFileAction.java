package com.zephyr.studentsafe.ui.action.menu;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.ui.MainFrame;
import com.zephyr.studentsafe.ui.MessageWindow;

/**
 * @author lenovo 日志--右键弹出菜单clear
 */
public class PopMenuSaveasFileAction implements ActionListener {
	private static final Logger log = Logger.getLogger(PopMenuSaveasFileAction.class);
	@Override
	public void actionPerformed(ActionEvent evt) {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showSaveDialog(MainFrame.jTextArea1);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
				try {
					FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile().getPath()));
					out.write(MainFrame.jTextArea1.getText().getBytes());
					out.close();
				} catch (FileNotFoundException e1) {
					log.error(e1.getLocalizedMessage());
					MessageWindow.show(e1.getLocalizedMessage());
				} catch (IOException e) {
					log.error(e.getLocalizedMessage());
					MessageWindow.show(e.getLocalizedMessage());
				}
		}
	}

}
