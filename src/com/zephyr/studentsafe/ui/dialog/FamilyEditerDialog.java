package com.zephyr.studentsafe.ui.dialog;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.dao.BaseDAO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class FamilyEditerDialog extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JTextField studentid;
	private JButton applyButton;
	private JButton cancelButton;
	private JTextField phoneNumber;
	private JLabel jLabel2;
	private JTextField familyName;
	private JLabel jLabel1;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				FamilyEditerDialog inst = new FamilyEditerDialog(frame);
				inst.setVisible(true);
				
			}
		});
	}
	
	public FamilyEditerDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public FamilyEditerDialog(JFrame frame,Studentfamily family) {
		super(frame);
		initGUI();
		loadFamily(family);
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("\u7f16\u8f91\u5bb6\u957f\u4fe1\u606f");
			}
			{
				jPanel1 = new JPanel();
				jPanel1.setLayout(null);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u59d3\u540d");
					jLabel1.setBounds(32, 28, 56, 18);
				}
				{
					familyName = new JTextField();
					jPanel1.add(familyName);
					familyName.setBounds(102, 23, 202, 29);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u624b\u673a\u53f7");
					jLabel2.setBounds(32, 72, 56, 18);
				}
				{
					phoneNumber = new JTextField();
					jPanel1.add(phoneNumber);
					phoneNumber.setBounds(102, 65, 202, 29);
				}
				{
					applyButton = new JButton();
					jPanel1.add(applyButton);
					applyButton.setText("\u4fdd\u5b58");
					applyButton.setBounds(102, 115, 97, 27);
					applyButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							saveFamilyInfo() ;
						}
					});
				}
				{
					cancelButton = new JButton();
					jPanel1.add(cancelButton);
					cancelButton.setText("\u53d6\u6d88");
					cancelButton.setBounds(207, 115, 97, 27);
					cancelButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							close();
						}
						
					});
				}
				{
					studentid = new JTextField();
					jPanel1.add(studentid);
					studentid.setBounds(11, 142, 94, 20);
					studentid.setVisible(false);
				}
			}
			this.setSize(403, 217);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadFamily(Studentfamily family){
		familyName.setText(family.getFamilyName());
		phoneNumber.setText(family.getFamilyPhone());
		studentid.setText(family.getFamilyUID()) ;
	}
	
	private void saveFamilyInfo(){
		BaseDAO dao = new BaseDAO();
		Studentfamily family = (Studentfamily) dao.get(Studentfamily.class, studentid.getText());
		family.setFamilyName(familyName.getText());
		family.setFamilyPhone(phoneNumber.getText());
		try
		{
			dao.saveORupdate(family);
		} catch (Exception e)
		{
			e.printStackTrace();
			
		}
		StudentInfoManage.getQueryButton().doClick();
		this.dispose();
	}
	
	private void close(){
		this.dispose();
	}
	
	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			super.processWindowEvent(e);
			this.dispose();

		}
	}

}
