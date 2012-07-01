package com.zephyr.studentsafe.ui.action.button;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.zephyr.studentsafe.exception.StudentSafeException;

/**
 * @author lenovo UI½çÃæÖÐËùÓÐ°´Å¥µÄaction´¦Àí ´ÓmainframeÀàÖÐ·ÖÀë³öÀ´
 */
public interface IButtonsAction {

	// ¿ªÊ¼°´Å¥ÊÂ¼þ
	public void startButtonPerformeAction(ActionEvent evt, JComponent jcp)
			throws StudentSafeException;

	// ÉèÖÃ°´Å¥ÊÂ¼þ
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
