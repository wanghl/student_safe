package com.zephyr.studentsafe.ui.action.button;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.zephyr.sudentsafe.exception.StudentSafeException;

/**
 * @author lenovo UI界面中所有按钮的action处理 从mainframe类中分离出来
 */
public interface IButtonsAction {

	// 开始按钮事件
	public void startButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

	// 设置按钮事件
	public void settingButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

	// help buuton
	public void helpButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

	// mas init button
	public void initButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

	// mas send message button

	public void sendButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

	// mas receive rpt button
	public void receiveRPTButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

	// mas relase button
	public void relaseButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

}
