package com.zephyr.studentsafe.ui.dialog;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;

import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.ui.action.button.ButtonsAction;
import com.zephyr.studentsafe.ui.action.button.IButtonsAction;
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
public class MessageSenderFrame extends javax.swing.JFrame {
	private JPanel jPanel1;
	private JLabel jLabel3;
	private JLabel jLabel5;
	public static JButton relaseButton;
	public static JButton receiveButton;
	public static JButton sendButton;
	public  static JButton initButton;
	public static JTextField dbName;
	private JLabel jLabel9;
	public static JPasswordField password;
	private JButton exitButton;
	private JLabel jLabel8;
	public static  JTextField app;
	private JLabel jLabel7;
	public static JTextField userName;
	private JLabel jLabel6;
	public static JTextField serverIP;
	public static JTextArea content;
	private JLabel jLabel2;
	public static JTextField phoneNumber;
	private JLabel jLabel1;
	private JPanel jPanel5;
	private JPanel jPanel4;
	private JPanel jPanel3;
	private JPanel jPanel2;
	
	private IButtonsAction action = new ButtonsAction();

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
				MessageSenderFrame inst = new MessageSenderFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MessageSenderFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setTitle("\u77ed\u4fe1\u901a\u9053\u6d4b\u8bd5");
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/icons/log.gif")).getImage());
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setLayout(null);
				{
					jPanel2 = new JPanel();
					jPanel1.add(jPanel2);
					jPanel2.setLayout(null);
					jPanel2.setBounds(0, 0, 542, 238);
					{
						jPanel4 = new JPanel();
						jPanel2.add(jPanel4);
						jPanel4.setBounds(14, 13, 502, 224);
						jPanel4.setBorder(BorderFactory.createTitledBorder(""));
						jPanel4.setLayout(null);
						{
							jLabel1 = new JLabel();
							jPanel4.add(jLabel1);
							jLabel1.setText("\u624b\u673a\u53f7");
							jLabel1.setBounds(20, 30, 56, 18);
						}
						{
							phoneNumber = new JTextField();
							jPanel4.add(phoneNumber);
							phoneNumber.setBounds(91, 23, 354, 32);
						}
						{
							jLabel2 = new JLabel();
							jPanel4.add(jLabel2);
							jLabel2.setText("\u77ed\u4fe1\u5185\u5bb9");
							jLabel2.setBounds(20, 125, 65, 18);
						}
						{
							content = new JTextArea();
							jPanel4.add(content);
							content.setBounds(91, 85, 354, 119);
							content.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
						}
						{
							jLabel3 = new JLabel();
							jPanel4.add(jLabel3);
							jLabel3.setText("\u652f\u6301\u591a\u4e2a\u624b\u673a\u53f7\uff0c\u7528\u9017\u53f7\u9694\u5f00");
							jLabel3.setBounds(91, 60, 288, 18);
						}
					}
				}
				{
					jPanel3 = new JPanel();
					jPanel1.add(jPanel3);
					jPanel3.setLayout(null);
					jPanel3.setBounds(0, 254, 530, 254);
					{
						jPanel5 = new JPanel();
						jPanel3.add(jPanel5);
						jPanel5.setBounds(14, -1, 502, 206);
						jPanel5.setBorder(BorderFactory.createTitledBorder(""));
						jPanel5.setLayout(null);
						{
							serverIP = new JTextField();
							jPanel5.add(serverIP);
							serverIP.setBounds(90, 16, 138, 24);
							serverIP.setText(StudentSafeUtil.getStringValue("ip"));
						}
						{
							jLabel5 = new JLabel();
							jPanel5.add(jLabel5);
							jLabel5.setText("\u670d\u52a1\u5668IP");
							jLabel5.setBounds(20, 19, 64, 18);
						}
						{
							jLabel6 = new JLabel();
							jPanel5.add(jLabel6);
							jLabel6.setText("\u7528\u6237\u540d");
							jLabel6.setBounds(242, 22, 56, 18);
						}
						{
							userName = new JTextField();
							jPanel5.add(userName);
							userName.setBounds(312, 16, 138, 24);
							userName.setText(StudentSafeUtil.getStringValue("user"));
						}
						{
							jLabel7 = new JLabel();
							jPanel5.add(jLabel7);
							jLabel7.setText("APP");
							jLabel7.setBounds(20, 64, 56, 18);
						}
						{
							app = new JTextField();
							jPanel5.add(app);
							app.setBounds(90, 64, 138, 24);
							app.setText(StudentSafeUtil.getStringValue("appid"));
						}
						{
							jLabel8 = new JLabel();
							jPanel5.add(jLabel8);
							jLabel8.setText("\u5bc6\u7801");
							jLabel8.setBounds(242, 67, 56, 18);
						}
						{
							password = new JPasswordField();
							jPanel5.add(password);
							password.setBounds(312, 64, 138, 28);
							password.setText(StudentSafeUtil.getStringValue("pswd"));
						}
						{
							jLabel9 = new JLabel();
							jPanel5.add(jLabel9);
							jLabel9.setText("\u6570\u636e\u5e93");
							jLabel9.setBounds(20, 111, 56, 18);
						}
						{
							dbName = new JTextField();
							jPanel5.add(dbName);
							dbName.setBounds(90, 108, 138, 24);
							dbName.setText(StudentSafeUtil.getStringValue("dbname"));
						}
						{
							initButton = new JButton();
							jPanel5.add(initButton);
							initButton.setText("\u521d\u59cb\u5316");
							initButton.setBounds(20, 152, 101, 27);
							initButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										action.initButtonPerformeAction(evt, initButton);
									} catch (StudentSafeException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									//TODO add your code for initButton.actionPerformed
								}
							});
						}
						{
							sendButton = new JButton();
							jPanel5.add(sendButton);
							sendButton.setText("\u53d1\u9001");
							sendButton.setBounds(129, 152, 101, 27);
							sendButton.setEnabled(false);
							sendButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										action.sendButtonPerformeAction(evt, sendButton);
									} catch (StudentSafeException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									//TODO add your code for sendButton.actionPerformed
								}
							});
						}
						{
							receiveButton = new JButton();
							jPanel5.add(receiveButton);
							receiveButton.setText("\u6536\u56de\u6267");
							receiveButton.setBounds(240, 152, 101, 27);
							receiveButton.setEnabled(false);
							receiveButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										action.receiveRPTButtonPerformeAction(evt, receiveButton);
									} catch (StudentSafeException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									//TODO add your code for receiveButton.actionPerformed
								}
							});
						}
						{
							relaseButton = new JButton();
							jPanel5.add(relaseButton);
							relaseButton.setText("\u91ca\u653e\u8fde\u63a5");
							relaseButton.setBounds(353, 152, 101, 27);
							relaseButton.setEnabled(false);
							relaseButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									try {
										action.relaseButtonPerformeAction(evt, relaseButton);
									} catch (StudentSafeException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									//TODO add your code for relaseButton.actionPerformed
								}
							});
						}
					}
					{
						exitButton = new JButton();
						jPanel3.add(exitButton);
						exitButton.setText("\u5173\u95ed");
						exitButton.setBounds(421, 217, 92, 28);
						exitButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
							    closeSelf(); 
								//TODO add your code for exitButton.actionPerformed
							}
						});
					}
				}
			}
			pack();
			this.setSize(548, 554);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public void closeSelf(){
		this.dispose();
	}

}
