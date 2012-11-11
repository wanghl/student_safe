package com.zephyr.studentsafe.ui.dialog;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Teacher;
import com.zephyr.studentsafe.dao.ClassInfoDAO;
import com.zephyr.studentsafe.dao.TeacherDAO;
import com.zephyr.studentsafe.ui.MessageWindow;


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
public class NewEditClassDialog extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JButton applyButton;
	private JButton cancelButton;
	private JComboBox teacherList;
	private JLabel jLabel2;
	private JTextField className;
	private JLabel jLabel1;
	private ClassInfo classinfo = null;

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
				NewEditClassDialog inst = new NewEditClassDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public NewEditClassDialog(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	public NewEditClassDialog(JFrame frame,ClassInfo classinfo) {
		super(frame);
		initGUI();
		this.classinfo  = classinfo ;
		initData();
		
	}
	
	private void initGUI() {
		try {
			{
				this.setTitle("\u65b0\u589e\u4fe1\u606f");
				this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/icons/log.gif")).getImage());

			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				//jPanel1.setLayout(jPanel1Layout);
				jPanel1.setPreferredSize(new java.awt.Dimension(364, 159));
				jPanel1.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u73ed\u7ea7\u540d");
					jLabel1.setBounds(14, 13, 141, 26);
				}
				{
					className = new JTextField();
					jPanel1.add(className);
					className.setBounds(76, 10, 175, 26);
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u73ed\u4e3b\u4efb");
					jLabel2.setBounds(14, 47, 141, 26);
				}
				{
					
					teacherList = new JComboBox();
					TeacherDAO dao = new TeacherDAO();
					List l = dao.getObjList(Teacher.class);
					for(Object o : l){
						teacherList.addItem(o);
					}
					teacherList.insertItemAt(null, 0);
					teacherList.setSelectedIndex(0);
					jPanel1.add(teacherList);
					teacherList.setBounds(76, 46, 175, 26);
				}
				{
					applyButton = new JButton();
					jPanel1.add(applyButton);
					applyButton.setText("\u786e\u5b9a");
					applyButton.setBounds(99, 110, 123, 27);
					applyButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							saveClass();
						}
					});
				}
				{
					cancelButton = new JButton();
					jPanel1.add(cancelButton);
					cancelButton.setText("\u53d6\u6d88");
					cancelButton.setBounds(227, 110, 123, 27);
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							close();
						}
					});
				}
			}
			this.setSize(382, 197);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void close(){
		this.dispose();
	}
	public void saveClass(){
		ClassInfoDAO dao = new ClassInfoDAO();
		if(className.getText().equals(""))
		{
			MessageWindow.show("请输入班级名称");
			return ;
		}
		if(teacherList.getSelectedItem() == null)
		{
			MessageWindow.show("请选择班主任");
			return ;
		}
		if(this.classinfo == null)
		{
			ClassInfo c = new ClassInfo();
			c.setClassName(className.getText());
			c.setTeacher(((Teacher)teacherList.getSelectedItem()).getObjUID());
			try
			{
				dao.saveORupdate(c);
				ClassManage.initData();
				this.dispose();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else 
		{
			this.classinfo.setClassName(className.getText());
			this.classinfo.setTeacher(((Teacher)teacherList.getSelectedItem()).getObjUID());
			try
			{
				dao.saveORupdate(classinfo);
				ClassManage.initData();
				this.dispose();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void initData(){
		 this.setTitle("编辑信息");
		ClassInfoDAO dao = new ClassInfoDAO();
		Teacher t = (Teacher) dao.load(Teacher.class, classinfo.getTeacher());
		className.setText(classinfo.getClassName());
		for(int i = 1 ; i < teacherList.getItemCount() ; i++ )
		{
			if(((Teacher)teacherList.getItemAt(i)).getObjUID().equals(classinfo.getTeacher()))
			{
				teacherList.setSelectedItem(((Teacher)teacherList.getItemAt(i)));
				break ;
			}
		}
		
	}

}
