package com.zephyr.studentsafe.ui.dialog;
import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.ClassInfoDAO;
import com.zephyr.studentsafe.dao.StudentDAO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


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
public class StudentEditerDialog extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JComboBox classSelecter;
	private JTextField studentid;
	private JTextField lowCardNumberInput;
	private JTextField cardNumberInput;
	private JTextField stuentNameInput;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JButton cancelButton;
	private JButton applyButton;
	private JPanel jPanel3;
	private JPanel jPanel2;
	
	ClassInfoDAO dao = new ClassInfoDAO();

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
			public void run() {
				JFrame frame = new JFrame();
				StudentEditerDialog inst = new StudentEditerDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public StudentEditerDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public StudentEditerDialog(JFrame frame,Studentrfid student){
		super(frame);
		initGUI();
		loadStudent(student);
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("\u7f16\u8f91\u5b66\u751f\u4fe1\u606f");
			}
			{
				jPanel1 = new JPanel();
				BoxLayout jPanel1Layout = new BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setPreferredSize(new java.awt.Dimension(494, 496));
				{
					jPanel3 = new JPanel();
					jPanel3.setLayout(null);
					jPanel1.add(jPanel3);
					jPanel3.setPreferredSize(new java.awt.Dimension(354, 299));
					jPanel3.setBorder(BorderFactory.createTitledBorder("\u5b66\u751f\u4fe1\u606f"));
					{
						jLabel1 = new JLabel();
						jPanel3.add(jLabel1);
						jLabel1.setText("\u59d3\u540d");
						jLabel1.setBounds(30, 30, 40, 18);
					}
					{
						jLabel2 = new JLabel();
						jPanel3.add(jLabel2);
						jLabel2.setText("\u5361\u53f7");
						jLabel2.setBounds(30, 67, 40, 18);
					}
					{
						stuentNameInput = new JTextField();
						jPanel3.add(stuentNameInput);
						stuentNameInput.setBounds(82, 24, 176, 24);
					}
					{
						jLabel4 = new JLabel();
						jPanel3.add(jLabel4);
						jLabel4.setText("\u8bed\u97f3\u5361\u53f7");
						jLabel4.setBounds(20, 104, 65, 18);
					}
					{
						cardNumberInput = new JTextField();
						jPanel3.add(cardNumberInput);
						cardNumberInput.setBounds(82, 61, 176, 24);
					}
					{
						lowCardNumberInput = new JTextField();
						jPanel3.add(lowCardNumberInput);
						lowCardNumberInput.setBounds(82, 98, 176, 24);
					}
					{
						jLabel5 = new JLabel();
						jPanel3.add(jLabel5);
						jLabel5.setText("\u73ed\u7ea7");
						jLabel5.setBounds(30, 142, 40, 18);
					}
					{
						studentid = new JTextField();
						jPanel3.add(studentid);
						studentid.setBounds(82, 220, 74, 24);
						studentid.setVisible(false);
					}
					{
						
						classSelecter = new JComboBox();
						List l = dao.getObjList(ClassInfo.class);
						if(l != null)
						{
							for(int i = 0 ;i < l.size(); i++)
							{
								classSelecter.addItem(l.get(i));
								
							}
						}
						jPanel3.add(classSelecter);
						classSelecter.setBounds(82, 136, 176, 24);
					}
				}
				{
					jPanel2 = new JPanel();
					jPanel1.add(jPanel2);
					FlowLayout jPanel2Layout = new FlowLayout();
					jPanel2.setPreferredSize(new java.awt.Dimension(354, 41));
					jPanel2.setLayout(null);
					{
						applyButton = new JButton();
						jPanel2.add(applyButton);
						applyButton.setText("\u4fdd\u5b58");
						applyButton.setBounds(136, 2, 93, 28);
						applyButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								applyButtonAction();
							}
						});
					}
					{
						cancelButton = new JButton();
						jPanel2.add(cancelButton);
						cancelButton.setText("\u53d6\u6d88");
						cancelButton.setBounds(237, 2, 93, 28);
					}
				}
			}
			this.setSize(355, 332);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadStudent(Studentrfid student){
		
		stuentNameInput.setText(student.getStudentName());
		cardNumberInput.setText(student.getRfidCardID());
		lowCardNumberInput.setText(student.getLowCardNumber());
		for(int i = 0 ; i < classSelecter.getItemCount() ; i++ )
		{
			if(((ClassInfo)classSelecter.getItemAt(i)).getClassUID().equals(student.getClassUID()))
			{
				classSelecter.setSelectedItem(((ClassInfo)classSelecter.getItemAt(i)));
				break ;
			}
		}
		studentid.setText(student.getStudentUID());
		
	}
	
	public void applyButtonAction(){
		StudentDAO dao = new StudentDAO();
		Studentrfid studentrfid = (Studentrfid) dao.get(Studentrfid.class, studentid.getText());
		studentrfid.setStudentName(stuentNameInput.getText());
		studentrfid.setRfidCardID(cardNumberInput.getText());
		studentrfid.setLowCardNumber(lowCardNumberInput.getText());
		studentrfid.setClassUID(((ClassInfo)classSelecter.getSelectedItem()).getClassUID());
		studentrfid.setTeacherUID(((ClassInfo)classSelecter.getSelectedItem()).getTeacher());
		try
		{
			dao.saveORupdate(studentrfid);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StudentInfoManage.getQueryButton().doClick();
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
