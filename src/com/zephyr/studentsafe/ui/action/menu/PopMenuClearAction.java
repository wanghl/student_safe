package com.zephyr.studentsafe.ui.action.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.zephyr.studentsafe.ui.MainFrame;
import com.zephyr.studentsafe.ui.MessageWindow;

/**
 * @author lenovo
 * 日志--右键弹出菜单clear
 */
public class PopMenuClearAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//清空日志内容
		MainFrame.clear("") ;
		
	}

}
