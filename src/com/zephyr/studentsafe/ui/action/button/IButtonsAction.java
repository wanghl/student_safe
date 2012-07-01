package com.zephyr.studentsafe.ui.action.button;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.zephyr.sudentsafe.exception.StudentSafeException;

/**
 * @author lenovo UI���������а�ť��action���� ��mainframe���з������
 */
public interface IButtonsAction {

	// ��ʼ��ť�¼�
	public void startButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

	// ���ð�ť�¼�
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
