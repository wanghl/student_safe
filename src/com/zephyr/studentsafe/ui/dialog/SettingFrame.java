package com.zephyr.studentsafe.ui.dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.util.SettingProperites;
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
public class SettingFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPane1;
	private JPanel jPanel1;
	private JComboBox reader2Box;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JPanel jPanel4;
	private JTextField dbName;
	private JLabel jLabel10;
	private JPasswordField password;
	private static JCheckBox sendTeacherKQ;
	private JTextField timeOutSchool;
	private JCheckBox receiveMO;
	private JTextField receiveMobile;
	private JLabel jLabel18;
	private JLabel jLabel17;
	private JLabel jLabel16;
	private JTextField receiveMOTime;
	private JLabel jLabel15;
	private JLabel jLabel14;
	private JTextField betweenTime;
	private JCheckBox checkSendTime;
	private JLabel jLabel12;
	private JTextField timeToSchool;
	private JLabel jLabel11;
	private JPanel jPanel5;
	private JButton cancelButton;
	private JButton saveButton;
	private JLabel jLabel9;
	private JTextField app;
	private JLabel jLabel8;
	private JTextField userName;
	private JLabel jLabel7;
	private JTextField serverIP;
	private JLabel jLabel6;
	private JPanel jPanel3;
	private JTextField noScanTimes;
	private JTextField receiveRPTtime;
	private JPanel jPanel2;
	private JComboBox reader3Box;
	private JLabel jLabel2;
	private JComboBox readr1Box;
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
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SettingFrame inst = new SettingFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public SettingFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS);
			getContentPane().setLayout(thisLayout);
			this.setResizable(false);
			this.setTitle("\u9996\u9009\u9879");
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/icons/log.gif")).getImage());
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setPreferredSize(new java.awt.Dimension(538, 517));
				{
					jPanel1 = new JPanel();
					jTabbedPane1.addTab("串口设置", null, jPanel1, null);
					jPanel1.setPreferredSize(new java.awt.Dimension(475, 497));
					jPanel1.setLayout(null);
					{
						// 自动获取当前机器的COM口列表
						ComboBoxModel seralPortListModel = new DefaultComboBoxModel(
								StudentSafeUtil.getSerialPortList());
						
						readr1Box = new JComboBox();
						
						jPanel1.add(readr1Box);
						readr1Box.setModel(seralPortListModel);
						readr1Box.setSelectedItem(StudentSafeUtil
								.getStringValue(Constants.SERIAL_PORT_A)) ;
						readr1Box.setBounds(76, 39, 304, 24);
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("\u8bfb\u59342");
						jLabel2.setBounds(14, 86, 56, 18);
					}
					{
						// 自动获取当前机器的COM口列表
						ComboBoxModel seralPortListModel = new DefaultComboBoxModel(
								StudentSafeUtil.getSerialPortList());
						reader2Box = new JComboBox();
						jPanel1.add(reader2Box);
						reader2Box.setModel(seralPortListModel);
						reader2Box.setSelectedItem(StudentSafeUtil
								.getStringValue(Constants.SERIAL_PORT_B)) ;
						reader2Box.setBounds(76, 86, 304, 24);
					}
					{
						jLabel3 = new JLabel();
						jPanel1.add(jLabel3);
						jLabel3.setText("\u8bfb\u59343");
						jLabel3.setBounds(14, 136, 56, 18);
					}
					{
						// 自动获取当前机器的COM口列表
						ComboBoxModel seralPortListModel = new DefaultComboBoxModel(
								StudentSafeUtil.getSerialPortList());
						reader3Box = new JComboBox();
						jPanel1.add(reader3Box);
						reader3Box.setModel(seralPortListModel);
						reader3Box.setSelectedItem(StudentSafeUtil
								.getStringValue(Constants.SERIAL_PORT_C)) ;
						reader3Box.setBounds(76, 133, 304, 24);
					}
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("\u8bfb\u59341");
						jLabel1.setBounds(14, 42, 56, 18);
					}
				}
				{
					jPanel2 = new JPanel();
					jTabbedPane1.addTab("软件参数", null, jPanel2, null);
					jPanel2.setPreferredSize(new java.awt.Dimension(529, 437));
					jPanel2.setLayout(null);
					{
						jLabel4 = new JLabel();
						jPanel2.add(jLabel4);
						jLabel4.setText("\u63a5\u6536\u56de\u6267\u95f4\u9694:");
						jLabel4.setBounds(14, 11, 131, 18);
					}
					{
						receiveRPTtime = new JTextField();
						receiveRPTtime.setText(StudentSafeUtil.getStringValue(Constants.RECEIVE_RPT_TIME));
						jPanel2.add(receiveRPTtime);
						receiveRPTtime.setBounds(118, 8, 178, 24);
					}
					{
						jLabel5 = new JLabel();
						jPanel2.add(jLabel5);
						jLabel5.setText("\u79bb\u5f00\u533a\u57df\u5224\u5b9a:");
						jLabel5.setBounds(14, 99, 131, 18);
					}
					{
						noScanTimes = new JTextField();
						noScanTimes.setText(StudentSafeUtil.getStringValue(Constants.NO_SCAN_TIMES));
						jPanel2.add(noScanTimes);
						noScanTimes.setBounds(118, 96, 178, 24);
					}
					{
						jPanel5 = new JPanel();
						jPanel2.add(jPanel5);
						jPanel5.setBounds(7, 127, 528, 293);
						jPanel5.setBorder(BorderFactory.createTitledBorder(""));
						jPanel5.setLayout(null);
						{
							sendTeacherKQ = new JCheckBox();
							jPanel5.add(sendTeacherKQ);
							sendTeacherKQ.setText("\u53d1\u9001\u73ed\u7ea7\u8003\u52e4");
							sendTeacherKQ.setBounds(16, 15, 149, 27);
								if (StudentSafeUtil.getIntValue(Constants.SEND_TEACHER_KQ) == 1){
									sendTeacherKQ.setSelected(true);
								}
							
						}
						{
							jLabel11 = new JLabel();
							jPanel5.add(jLabel11);
							jLabel11.setText("\u4e0a\u5348\u4e0a\u5b66\u65f6\u95f4");
							jLabel11.setBounds(20, 48, 92, 18);
						}
						{
							timeToSchool = new JTextField();
							jPanel5.add(timeToSchool);
							timeToSchool.setBounds(118, 45, 94, 24);
							timeToSchool.setText("8");
						}
						{
							jLabel12 = new JLabel();
							jPanel5.add(jLabel12);
							jLabel12.setText("\u4e0b\u5348\u653e\u5b66\u65f6\u95f4");
							jLabel12.setBounds(232, 48, 97, 18);
						}
						{
							jLabel14 = new JLabel();
							jPanel5.add(jLabel14);
							jLabel14.setText("\u8fc7\u6ee4\u95f4\u9694\u65f6\u95f4");
							jLabel14.setBounds(20, 115, 94, 18);
						}
						{
							betweenTime = new JTextField();
							jPanel5.add(betweenTime);
							betweenTime.setBounds(114, 112, 98, 21);
							betweenTime.setText(StudentSafeUtil.getStringValue(Constants.MESSAGE_BETWEEN_TIME));
						}
						{
							timeOutSchool = new JTextField();
							jPanel5.add(timeOutSchool);
							timeOutSchool.setBounds(335, 45, 94, 24);
							timeOutSchool.setText("16");
						}
						{
							checkSendTime = new JCheckBox();
							jPanel5.add(checkSendTime);
							checkSendTime.setText("\u8fc7\u6ee4\u91cd\u590d\u77ed\u4fe1");
							checkSendTime.setBounds(16, 78, 119, 28);
							checkSendTime.addChangeListener(new ChangeListener(){

								@Override
								public void stateChanged(ChangeEvent e) {
									// TODO Auto-generated method stub
									if ( checkSendTime.isSelected() )
									{
										betweenTime.setEditable(true);
										jLabel14.setEnabled(true);
									}else {
										betweenTime.setEditable(false);
										jLabel14.setEnabled(false);
									}
								}
								
							});
							if (StudentSafeUtil.getStringValue(Constants.CHECK_MESSAGE_SEND_TIME).equals("1"))
							{
								checkSendTime.setSelected(true);
							}else
							{
								jLabel14.setEnabled(false);
								betweenTime.setEditable(false);
							}
						}
						
						
					}
					{
						jLabel15 = new JLabel();
						jPanel2.add(jLabel15);
						jLabel15.setText("\u63a5\u6536\u56de\u590d\u95f4\u9694:");
						jLabel15.setBounds(14, 42, 131, 17);
					}
					{
						receiveMOTime = new JTextField();
						jPanel2.add(receiveMOTime);
						receiveMOTime.setBounds(118, 37, 178, 24);
						receiveMOTime.setText(StudentSafeUtil.getStringValue(Constants.RECEIVE_MO_TIME)) ;
					}
					{
						jLabel16 = new JLabel();
						jPanel2.add(jLabel16);
						jLabel16.setText("\uff08\u6beb\u79d2\uff09");
						jLabel16.setBounds(304, 11, 64, 19);
					}
					{
						jLabel17 = new JLabel();
						jPanel2.add(jLabel17);
						jLabel17.setText("\uff08\u79d2\uff09");
						jLabel17.setBounds(302, 102, 57, 17);
					}
					{
						jLabel18 = new JLabel();
						jPanel2.add(jLabel18);
						jLabel18.setText("\u63a5\u6536\u56de\u590d\u53f7\u7801:");
						jLabel18.setBounds(14, 70, 98, 19);
					}
					{
						receiveMobile = new JTextField();
						jPanel2.add(receiveMobile);
						receiveMobile.setBounds(118, 66, 178, 24);
						receiveMobile.setText(StudentSafeUtil.getStringValue(Constants.RECEIVE_MO_MOBILE));
					}
					{
						receiveMO = new JCheckBox();
						jPanel2.add(receiveMO);
						receiveMO.setText("\u63a5\u6536\u77ed\u4fe1\u56de\u590d");
						receiveMO.setBounds(313, 36, 129, 27);
						receiveMO.setSelected(true);
						receiveMO.addChangeListener(new ChangeListener(){

							@Override
							public void stateChanged(ChangeEvent e) {
								if (receiveMO.isSelected())
								{
									receiveMOTime.setEditable(true);
									receiveMobile.setEnabled(true);
								}else
								{
									receiveMOTime.setEditable(false);
									receiveMobile.setEnabled(false);
								}
								
							}
							
						}) ;
						
					}
				}
				{
					jPanel3 = new JPanel();
					jTabbedPane1.addTab("短信通道设置", null, jPanel3, null);
					jPanel3.setLayout(null);
					jPanel3.setPreferredSize(new java.awt.Dimension(543, 434));
					{
						jLabel6 = new JLabel();
						jPanel3.add(jLabel6);
						jLabel6.setText("\u670d\u52a1\u5668IP");
						jLabel6.setBounds(14, 39, 64, 18);
					}
					{
						serverIP = new JTextField();
						serverIP.setText(StudentSafeUtil.getStringValue(Constants.MAS_IP));
						jPanel3.add(serverIP);
						serverIP.setBounds(84, 36, 125, 24);
					}
					{
						jLabel7 = new JLabel();
						jPanel3.add(jLabel7);
						jLabel7.setText("\u7528\u6237\u540d");
						jLabel7.setBounds(223, 39, 56, 18);
					}
					{
						userName = new JTextField();
						userName.setText(StudentSafeUtil.getStringValue(Constants.MAS_USER_NAME));

						jPanel3.add(userName);
						userName.setBounds(293, 36, 138, 24);
					}
					{
						jLabel8 = new JLabel();
						jPanel3.add(jLabel8);
						jLabel8.setText("APP");
						jLabel8.setBounds(14, 87, 56, 18);
					}
					{
						app = new JTextField();
						app.setText(StudentSafeUtil.getStringValue(Constants.MAS_APP));

						jPanel3.add(app);
						app.setBounds(84, 84, 125, 24);
					}
					{
						jLabel9 = new JLabel();
						jPanel3.add(jLabel9);
						jLabel9.setText("\u5bc6\u7801");
						jLabel9.setBounds(223, 87, 56, 18);
					}
					{
						password = new JPasswordField();
						password.setText(StudentSafeUtil.getStringValue(Constants.MAS_USER_PASSWORD));

						jPanel3.add(password);
						password.setBounds(293, 84, 138, 28);
					}
					{
						jLabel10 = new JLabel();
						jPanel3.add(jLabel10);
						jLabel10.setText("\u6570\u636e\u5e93\u540d");
						jLabel10.setBounds(14, 137, 64, 18);
					}
					{
						dbName = new JTextField();
						dbName.setText(StudentSafeUtil.getStringValue(Constants.MAS_DB_NAME));
						jPanel3.add(dbName);
						dbName.setBounds(84, 133, 125, 24);
					}
				}
			}
			{
				jPanel4 = new JPanel();
				FlowLayout jPanel4Layout = new FlowLayout();
				jPanel4Layout.setAlignment(FlowLayout.RIGHT);
				jPanel4Layout.setHgap(10);
				jPanel4Layout.setVgap(15);
				jPanel4.setLayout(jPanel4Layout);
				getContentPane().add(jPanel4);
				jPanel4.setPreferredSize(new java.awt.Dimension(528, 63));
				{
					saveButton = new JButton();
					jPanel4.add(saveButton);
					saveButton.setText("\u5e94  \u7528");
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							submitButtonPerformeAction(evt) ;
							//TODO add your code for saveButton.actionPerformed
						}
					});
				}
				{
					cancelButton = new JButton();
					jPanel4.add(cancelButton);
					cancelButton.setText("\u53d6  \u6d88");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							cancelButtonPerformeAction(evt) ;
							//TODO add your code for cancelButton.actionPerformed
						}
					});
				}
			}
			pack();
			this.setSize(554, 562);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	// 取消按钮事件

	private void cancelButtonPerformeAction(ActionEvent evt) {
		this.dispose();
	}
	
	// 确定按钮事件
	private void submitButtonPerformeAction(ActionEvent evt) {
		// IP地址
		SettingProperites.put(Constants.MAS_IP, serverIP.getText());
		// user name
		SettingProperites.put(Constants.MAS_USER_NAME, userName.getText());
		// user password
		SettingProperites.put(Constants.MAS_USER_PASSWORD, password.getText());
		// app id
		SettingProperites.put(Constants.MAS_APP, app.getText());
		// mas db name
		SettingProperites.put(Constants.MAS_DB_NAME, dbName.getText());

		// seralPortListA
		SettingProperites.put(Constants.SERIAL_PORT_A, readr1Box
				.getSelectedItem().toString());
		// seralPortListB
		SettingProperites.put(Constants.SERIAL_PORT_B, reader2Box
				.getSelectedItem().toString());
		// seralPortListC
		SettingProperites.put(Constants.SERIAL_PORT_C, reader3Box
				.getSelectedItem().toString());
		// no scan times
		SettingProperites.put(Constants.NO_SCAN_TIMES, noScanTimes.getText());
		// receive times
		SettingProperites.put(Constants.RECEIVE_RPT_TIME, receiveRPTtime
				.getText());
		// send teacher kq
		String value = "0" ;
		if (sendTeacherKQ.isSelected())
		{
			value = "1" ;
		}
		SettingProperites.put(Constants.SEND_TEACHER_KQ, value );
		//time to school
		SettingProperites.put(Constants.TIME_TO_SCHOOL, timeToSchool.getText()) ;
		// time to school pm
		SettingProperites.put(Constants.TIME_OUT_SCHOOL, timeOutSchool.getText());
		value = "0" ;
		if (checkSendTime.isSelected())
		{
			value = "1" ;
		}
		//是否过滤重复短信
		SettingProperites.put(Constants.CHECK_MESSAGE_SEND_TIME, value) ;
		//间隔时间
		SettingProperites.put(Constants.MESSAGE_BETWEEN_TIME, betweenTime.getText());
		//receive mo time
		SettingProperites.put(Constants.RECEIVE_MO_TIME, receiveMOTime.getText());
		//receive mo mobile
		SettingProperites.put(Constants.RECEIVE_MO_MOBILE, receiveMobile.getText());
		//是否接收短信回复（家长回复的短信）
		value = "0" ;
		if (receiveMO.isSelected())
		{
			value = "1";
		}
		SettingProperites.put(Constants.RECEIVE_MO, value);
		//接收短信回复手机。如果为空，则只将回复短信保存到本地数据库，不转发
		SettingProperites.put(Constants.RECEIVE_MO_MOBILE, receiveMobile.getText());
		try {
			//将以上配置写入配置文件
			SettingProperites.saveSetting();
			//关闭
			this.dispose();
		} catch (StudentSafeException e) {
			MessageWindow.show("保存设置失败:" + e.getLocalizedMessage());
			this.dispose();
		}
	}
	

}
