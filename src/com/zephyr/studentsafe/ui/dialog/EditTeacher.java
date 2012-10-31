package com.zephyr.studentsafe.ui.dialog;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.zephyr.studentsafe.bo.Teacher;
import com.zephyr.studentsafe.dao.TeacherDAO;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.util.StudentSafeUtil;


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
public class EditTeacher extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel jPanel1;
	private JLabel jLabel5;
	private JCheckBox jCheckBox1;
	private JButton applyButton;
	private JButton cancelButton;
	private JLabel jLabel2;
	private JTextField phoneInput;
	private JTextField nameInput;
	private JLabel jLabel1;
	private Teacher teacher ;
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				EditTeacher inst = new EditTeacher(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public EditTeacher(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public EditTeacher(JFrame frame,Teacher teacher) {
		super(frame);
		initGUI();
		this.teacher = teacher ;
		initData(teacher);
	}
	
	private void initGUI() {
		try {
			{
				GridLayout thisLayout = new GridLayout(1, 1);
				thisLayout.setHgap(5);
				thisLayout.setVgap(5);
				thisLayout.setColumns(1);
				getContentPane().setLayout(thisLayout);
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setLayout(null);
				jPanel1.setPreferredSize(new java.awt.Dimension(434, 387));
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u59d3\u540d");
					jLabel1.setBounds(14, 30, 53, 18);
				}
				{
					nameInput = new JTextField();
					jPanel1.add(nameInput);
					nameInput.setBounds(66, 24, 178, 24);
				}
				{
					phoneInput = new JTextField();
					jPanel1.add(phoneInput);
					phoneInput.setBounds(66, 61, 178, 24);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u7535\u8bdd");
					jLabel2.setBounds(14, 67, 53, 18);
				}
				{
					applyButton = new JButton();
					jPanel1.add(applyButton);
					applyButton.setBounds(65, 132, 99, 27);
					applyButton.setText("\u786e\u5b9a");
					applyButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							saveTeacher();
						}
						
					});
				}
				{
					cancelButton = new JButton();
					jPanel1.add(cancelButton);
					cancelButton.setBounds(172, 132, 99, 27);
					cancelButton.setText("\u53d6\u6d88");
				}
				{
					jLabel5 = new JLabel();
					jPanel1.add(jLabel5);
					jLabel5.setBounds(3, 94, 62, 15);
				}
				{
					jCheckBox1 = new JCheckBox();
					jPanel1.add(jCheckBox1);
					jCheckBox1.setText("\u521d\u59cb\u5316\u5bc6\u7801");
					jCheckBox1.setBounds(67, 95, 105, 24);
					
					
				}
			}
			this.setSize(327, 225);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initData(Teacher teacher){
		nameInput.setText(teacher.getName());
		phoneInput.setText(teacher.getPhone_number());
	}
	
	private void saveTeacher(){
		TeacherDAO dao = new TeacherDAO();
		teacher.setName(nameInput.getText());
		teacher.setPhone_number(phoneInput.getText());
		if(jCheckBox1.isSelected())
		{
			teacher.setPassword(StudentSafeUtil.getMD5String("123123".getBytes()));
		}
		try
		{
			dao.saveORupdate(teacher);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TeacherManage.initData();
		this.dispose();
	}
	

}
