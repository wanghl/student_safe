package com.zephyr.studentsafe.ui.action.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.zephyr.studentsafe.ui.MainFrame;
import com.zephyr.studentsafe.ui.MessageWindow;

/**
 * @author lenovo
 * ��־--�Ҽ������˵�clear
 */
public class PopMenuClearAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//�����־����
		MainFrame.clear("") ;
		
	}

}
