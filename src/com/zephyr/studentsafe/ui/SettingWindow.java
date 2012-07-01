package com.zephyr.studentsafe.ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.comm.CommPortIdentifier;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.sudentsafe.exception.StudentSafeException;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class SettingWindow extends javax.swing.JFrame {

	private static final Logger log = Logger.getLogger(SettingWindow.class);
	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public SettingWindow() throws StudentSafeException {
		super();
		initGUI();
	}

	private void initGUI() throws StudentSafeException {
		try {
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				GroupLayout jPanel1Layout = new GroupLayout(
						(JComponent) jPanel1);
				jPanel1.setLayout(jPanel1Layout);
				{
					jPanel2 = new JPanel();
					GroupLayout jPanel2Layout = new GroupLayout(
							(JComponent) jPanel2);
					jPanel2.setLayout(jPanel2Layout);
					jPanel2.setBorder(BorderFactory.createTitledBorder("串口设置"));
					{
						jLabel1 = new JLabel();
						jLabel1.setText("\u6ce2\u7279\u7387");
					}
					{
						// 自动获取当前机器的COM口列表
						ComboBoxModel seralPortListModel = new DefaultComboBoxModel(
								getSeralPortListA());
						seralPortListA = new JComboBox();
						seralPortListA.setModel(seralPortListModel);
						seralPortListA.setSelectedItem(StudentSafeUtil
								.getStringValue(Constants.SERIAL_PORT_A));

					}
					{
						jLabel2 = new JLabel();
						jLabel2.setText("\u9605\u8bfb\u5668A");
					}
					{
						jLabel13 = new JLabel();
						jLabel13.setText("\u9605\u8bfb\u5668B");
					}
					{
						ComboBoxModel jComboBox2Model = new DefaultComboBoxModel(
								getSeralPortListA());
						seralPortListB = new JComboBox();
						seralPortListB.setModel(jComboBox2Model);
						seralPortListB.setSelectedItem(StudentSafeUtil
								.getStringValue(Constants.SERIAL_PORT_B));
					}
					{
						ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
								new String[] { "256000", "128000", "115200",
										"57600", "56000", "43000", "38400",
										"28800", "19200", "9600", "4800",
										"2400", "1200", "600", "300", "110", });
						baudrateList = new JComboBox();
						baudrateList.setModel(jComboBox1Model);
						baudrateList.setSelectedItem(StudentSafeUtil
								.getStringValue(Constants.BAUDRATE));
					}
					jPanel2Layout
							.setHorizontalGroup(jPanel2Layout
									.createSequentialGroup()
									.addGap(8)
									.addGroup(
											jPanel2Layout
													.createParallelGroup()
													.addGroup(
															jPanel2Layout
																	.createSequentialGroup()
																	.addGap(
																			0,
																			0,
																			Short.MAX_VALUE)
																	.addComponent(
																			jLabel13,
																			GroupLayout.PREFERRED_SIZE,
																			70,
																			GroupLayout.PREFERRED_SIZE))
													.addGroup(
															jPanel2Layout
																	.createSequentialGroup()
																	.addPreferredGap(
																			LayoutStyle.ComponentPlacement.RELATED)
																	.addGroup(
																			jPanel2Layout
																					.createParallelGroup()
																					.addComponent(
																							jLabel1,
																							GroupLayout.Alignment.LEADING,
																							GroupLayout.PREFERRED_SIZE,
																							67,
																							GroupLayout.PREFERRED_SIZE)
																					.addComponent(
																							jLabel2,
																							GroupLayout.Alignment.LEADING,
																							GroupLayout.PREFERRED_SIZE,
																							67,
																							GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(
											LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(
											jPanel2Layout
													.createParallelGroup()
													.addComponent(
															baudrateList,
															GroupLayout.Alignment.LEADING,
															GroupLayout.PREFERRED_SIZE,
															265,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															seralPortListA,
															GroupLayout.Alignment.LEADING,
															GroupLayout.PREFERRED_SIZE,
															265,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															seralPortListB,
															GroupLayout.Alignment.LEADING,
															GroupLayout.PREFERRED_SIZE,
															265,
															GroupLayout.PREFERRED_SIZE))
									.addContainerGap(145, 145));
					jPanel2Layout.linkSize(SwingConstants.HORIZONTAL,
							new Component[] { baudrateList, seralPortListB,
									seralPortListA });
					jPanel2Layout
							.setVerticalGroup(jPanel2Layout
									.createSequentialGroup()
									.addGroup(
											jPanel2Layout
													.createParallelGroup(
															GroupLayout.Alignment.BASELINE)
													.addComponent(
															baudrateList,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															22,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel1,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															20,
															GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
											LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(
											jPanel2Layout
													.createParallelGroup(
															GroupLayout.Alignment.BASELINE)
													.addComponent(
															seralPortListA,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															22,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel2,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															20,
															GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
											LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(
											jPanel2Layout
													.createParallelGroup(
															GroupLayout.Alignment.BASELINE)
													.addComponent(
															seralPortListB,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															22,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel13,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															20,
															GroupLayout.PREFERRED_SIZE))
									.addGap(7));
					jPanel2Layout.linkSize(SwingConstants.VERTICAL,
							new Component[] { baudrateList, seralPortListB,
									seralPortListA });
				}
				{
					commitButton = new JButton();
					commitButton.setText("\u786e\u5b9a");
					commitButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							submitButtonPerformeAction(e);
						}

					});
				}
				{
					cancelButton = new JButton();
					cancelButton.setText("\u53d6\u6d88");
					cancelButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							cancelButtonPerformeAction(e);
							
						}
						
					});
				}
				{
					jPanel3 = new JPanel();
					GroupLayout jPanel3Layout = new GroupLayout(
							(JComponent) jPanel3);
					jPanel3.setLayout(jPanel3Layout);
					jPanel3.setBorder(BorderFactory.createTitledBorder("杂项"));
					{
						jLabel3 = new JLabel();
						jLabel3
								.setText("\u6536\u77ed\u4fe1\u56de\u6267\u95f4\u9694");
					}
					{
						receiveTimes = new JTextField();
						receiveTimes.setText(StudentSafeUtil
								.getStringValue(Constants.RECEIVE_RPT_TIME));
					}
					{
						jLabel4 = new JLabel();
						jLabel4
								.setText("(\u6574\u6570\u3002\u5224\u65ad\u662f\u5426\u79bb\u5f00\u9605\u8bfb\u5668\u533a\u57df)");
					}
					{
						jLabel5 = new JLabel();
						jLabel5.setText("\u672a\u68c0\u6d4b\u5230\u6b21\u6570");
					}
					{
						jLabel11 = new JLabel();
						jLabel11.setText("\u6570\u636e\u5e93\u540d");
					}
					{
						masDBName = new JTextField();
						masDBName.setText(StudentSafeUtil
								.getStringValue(Constants.MAS_DB_NAME));
					}
					{
						jSeparator1 = new JSeparator();
					}
					{
						noScanTimes = new JTextField();
						noScanTimes.setText(StudentSafeUtil
								.getStringValue(Constants.NO_SCAN_TIMES));
					}
					{
						jLabel6 = new JLabel();
						jLabel6.setText("(\u6beb\u79d2)");
					}
					{
						jLabel7 = new JLabel();
						jLabel7.setText("MAS\u670d\u52a1\u5668IP");
					}
					{
						masIP = new JTextField();
						masIP.setText(StudentSafeUtil
								.getStringValue(Constants.MAS_IP));
					}
					{
						jLabel8 = new JLabel();
						jLabel8.setText("\u7528\u6237\u540d");
					}
					{
						masUser = new JTextField();
						masUser.setText(StudentSafeUtil
								.getStringValue(Constants.MAS_USER_NAME));
					}
					{
						jLabel9 = new JLabel();
						jLabel9.setText("APP");
					}
					{
						masAPP = new JTextField();
						masAPP.setText(StudentSafeUtil
								.getStringValue(Constants.MAS_APP));
					}
					{
						jLabel10 = new JLabel();
						jLabel10.setText("\u5bc6 \u7801");
					}
					{
						masPswd = new JPasswordField();
						masPswd.setText(StudentSafeUtil
								.getStringValue(Constants.MAS_USER_PASSWORD));
					}
					jPanel3Layout
							.setHorizontalGroup(jPanel3Layout
									.createSequentialGroup()
									.addContainerGap()
									.addGroup(
											jPanel3Layout
													.createParallelGroup()
													.addGroup(
															GroupLayout.Alignment.LEADING,
															jPanel3Layout
																	.createSequentialGroup()
																	.addComponent(
																			jLabel3,
																			GroupLayout.PREFERRED_SIZE,
																			GroupLayout.PREFERRED_SIZE,
																			GroupLayout.PREFERRED_SIZE)
																	.addGap(17)
																	.addGroup(
																			jPanel3Layout
																					.createParallelGroup()
																					.addComponent(
																							noScanTimes,
																							GroupLayout.Alignment.LEADING,
																							GroupLayout.PREFERRED_SIZE,
																							91,
																							GroupLayout.PREFERRED_SIZE)
																					.addComponent(
																							receiveTimes,
																							GroupLayout.Alignment.LEADING,
																							GroupLayout.PREFERRED_SIZE,
																							91,
																							GroupLayout.PREFERRED_SIZE))
																	.addGroup(
																			jPanel3Layout
																					.createParallelGroup()
																					.addGroup(
																							jPanel3Layout
																									.createSequentialGroup()
																									.addComponent(
																											jLabel4,
																											GroupLayout.PREFERRED_SIZE,
																											233,
																											GroupLayout.PREFERRED_SIZE)
																									.addGap(
																											0,
																											0,
																											Short.MAX_VALUE))
																					.addGroup(
																							GroupLayout.Alignment.LEADING,
																							jPanel3Layout
																									.createSequentialGroup()
																									.addComponent(
																											jLabel6,
																											GroupLayout.PREFERRED_SIZE,
																											57,
																											GroupLayout.PREFERRED_SIZE)
																									.addGap(
																											0,
																											176,
																											Short.MAX_VALUE)))
																	.addGap(16))
													.addComponent(
															jSeparator1,
															GroupLayout.Alignment.LEADING,
															0, 462,
															Short.MAX_VALUE)
													.addGroup(
															jPanel3Layout
																	.createSequentialGroup()
																	.addGroup(
																			jPanel3Layout
																					.createParallelGroup()
																					.addGroup(
																							jPanel3Layout
																									.createSequentialGroup()
																									.addPreferredGap(
																											jLabel7,
																											jLabel11,
																											LayoutStyle.ComponentPlacement.INDENT)
																									.addGroup(
																											jPanel3Layout
																													.createParallelGroup()
																													.addComponent(
																															jLabel11,
																															GroupLayout.Alignment.LEADING,
																															GroupLayout.PREFERRED_SIZE,
																															87,
																															GroupLayout.PREFERRED_SIZE)
																													.addGroup(
																															GroupLayout.Alignment.LEADING,
																															jPanel3Layout
																																	.createSequentialGroup()
																																	.addGap(
																																			33)
																																	.addComponent(
																																			jLabel9,
																																			GroupLayout.PREFERRED_SIZE,
																																			36,
																																			GroupLayout.PREFERRED_SIZE)
																																	.addGap(
																																			18))))
																					.addGroup(
																							GroupLayout.Alignment.LEADING,
																							jPanel3Layout
																									.createSequentialGroup()
																									.addComponent(
																											jLabel7,
																											GroupLayout.PREFERRED_SIZE,
																											GroupLayout.PREFERRED_SIZE,
																											GroupLayout.PREFERRED_SIZE)
																									.addGap(
																											12))
																					.addGroup(
																							GroupLayout.Alignment.LEADING,
																							jPanel3Layout
																									.createSequentialGroup()
																									.addComponent(
																											jLabel5,
																											GroupLayout.PREFERRED_SIZE,
																											GroupLayout.PREFERRED_SIZE,
																											GroupLayout.PREFERRED_SIZE)
																									.addGap(
																											7)))
																	.addGroup(
																			jPanel3Layout
																					.createParallelGroup()
																					.addGroup(
																							jPanel3Layout
																									.createSequentialGroup()
																									.addComponent(
																											masDBName,
																											GroupLayout.PREFERRED_SIZE,
																											128,
																											GroupLayout.PREFERRED_SIZE)
																									.addGap(
																											0,
																											0,
																											Short.MAX_VALUE))
																					.addComponent(
																							masAPP,
																							GroupLayout.Alignment.LEADING,
																							0,
																							128,
																							Short.MAX_VALUE)
																					.addComponent(
																							masIP,
																							GroupLayout.Alignment.LEADING,
																							0,
																							128,
																							Short.MAX_VALUE))
																	.addPreferredGap(
																			LayoutStyle.ComponentPlacement.RELATED)
																	.addGroup(
																			jPanel3Layout
																					.createParallelGroup()
																					.addGroup(
																							GroupLayout.Alignment.LEADING,
																							jPanel3Layout
																									.createSequentialGroup()
																									.addComponent(
																											jLabel10,
																											GroupLayout.PREFERRED_SIZE,
																											39,
																											GroupLayout.PREFERRED_SIZE)
																									.addGap(
																											8))
																					.addComponent(
																							jLabel8,
																							GroupLayout.Alignment.LEADING,
																							GroupLayout.PREFERRED_SIZE,
																							47,
																							GroupLayout.PREFERRED_SIZE))
																	.addPreferredGap(
																			LayoutStyle.ComponentPlacement.RELATED)
																	.addGroup(
																			jPanel3Layout
																					.createParallelGroup()
																					.addComponent(
																							masPswd,
																							GroupLayout.Alignment.LEADING,
																							GroupLayout.PREFERRED_SIZE,
																							124,
																							GroupLayout.PREFERRED_SIZE)
																					.addComponent(
																							masUser,
																							GroupLayout.Alignment.LEADING,
																							GroupLayout.PREFERRED_SIZE,
																							124,
																							GroupLayout.PREFERRED_SIZE))
																	.addGap(54)))
									.addContainerGap(18, 18));
					jPanel3Layout
							.setVerticalGroup(jPanel3Layout
									.createSequentialGroup()
									.addGroup(
											jPanel3Layout
													.createParallelGroup(
															GroupLayout.Alignment.BASELINE)
													.addComponent(
															receiveTimes,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															25,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel6,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel3,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
											LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(
											jPanel3Layout
													.createParallelGroup(
															GroupLayout.Alignment.BASELINE)
													.addComponent(
															noScanTimes,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															25,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel4,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															21,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel5,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
											LayoutStyle.ComponentPlacement.UNRELATED)
									.addComponent(jSeparator1,
											GroupLayout.PREFERRED_SIZE, 3,
											GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(
											LayoutStyle.ComponentPlacement.UNRELATED)
									.addGroup(
											jPanel3Layout
													.createParallelGroup(
															GroupLayout.Alignment.BASELINE)
													.addComponent(
															masUser,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															25,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel8,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															masIP,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															25,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel7,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
											LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(
											jPanel3Layout
													.createParallelGroup(
															GroupLayout.Alignment.BASELINE)
													.addComponent(
															masPswd,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															23,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel10,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															masAPP,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															25,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel9,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
											LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(
											jPanel3Layout
													.createParallelGroup(
															GroupLayout.Alignment.BASELINE)
													.addComponent(
															masDBName,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															25,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(
															jLabel11,
															GroupLayout.Alignment.BASELINE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE,
															GroupLayout.PREFERRED_SIZE))
									.addContainerGap(25, 25));
				}
				{
					jLabel12 = new JLabel();
					jLabel12
							.setText("\u4fee\u6539\u540e\u9700\u91cd\u542f\u7a0b\u5e8f");
				}
				jPanel1Layout
						.setHorizontalGroup(jPanel1Layout
								.createSequentialGroup()
								.addGap(10)
								.addGroup(
										jPanel1Layout
												.createParallelGroup()
												.addGroup(
														GroupLayout.Alignment.LEADING,
														jPanel1Layout
																.createSequentialGroup()
																.addComponent(
																		jLabel12,
																		GroupLayout.PREFERRED_SIZE,
																		147,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(163)
																.addComponent(
																		commitButton,
																		GroupLayout.PREFERRED_SIZE,
																		87,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		cancelButton,
																		GroupLayout.PREFERRED_SIZE,
																		87,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(
																		0,
																		14,
																		Short.MAX_VALUE))
												.addComponent(
														jPanel2,
														GroupLayout.Alignment.LEADING,
														0, 506, Short.MAX_VALUE)
												.addComponent(
														jPanel3,
														GroupLayout.Alignment.LEADING,
														0, 506, Short.MAX_VALUE))
								.addContainerGap());
				jPanel1Layout.linkSize(SwingConstants.HORIZONTAL,
						new Component[] { cancelButton, commitButton });
				jPanel1Layout
						.setVerticalGroup(jPanel1Layout
								.createSequentialGroup()
								.addContainerGap(18, 18)
								.addComponent(jPanel2,
										GroupLayout.PREFERRED_SIZE, 128,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jPanel3, 0, 229, Short.MAX_VALUE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel1Layout
												.createParallelGroup(
														GroupLayout.Alignment.BASELINE)
												.addComponent(
														commitButton,
														GroupLayout.Alignment.BASELINE,
														GroupLayout.PREFERRED_SIZE,
														34,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jLabel12,
														GroupLayout.Alignment.BASELINE,
														GroupLayout.PREFERRED_SIZE,
														30,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														cancelButton,
														GroupLayout.Alignment.BASELINE,
														GroupLayout.PREFERRED_SIZE,
														34,
														GroupLayout.PREFERRED_SIZE))
								.addGap(8));
				jPanel1Layout.linkSize(SwingConstants.VERTICAL,
						new Component[] { cancelButton, commitButton });
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
					.addComponent(jPanel1, 0, 437, Short.MAX_VALUE));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
					.addComponent(jPanel1, 0, 530, Short.MAX_VALUE));
			pack();
			this.setSize(548, 482);
			this.requestFocus() ;
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public String[] getSeralPortListA() {
		String ports = "";
		CommPortIdentifier portId;
		Enumeration serList = CommPortIdentifier.getPortIdentifiers();
		while (serList.hasMoreElements()) {
			portId = (CommPortIdentifier) serList.nextElement();
			ports += portId.getName() + ",";
		}
		return ports.split(",");
	}

	// 取消按钮事件

	private void cancelButtonPerformeAction(ActionEvent evt) {
		this.dispose();
	}

	// 确定按钮事件
	private void submitButtonPerformeAction(ActionEvent evt) {
		// IP地址
		SettingProperites.put(Constants.MAS_IP, masIP.getText());
		// user name
		SettingProperites.put(Constants.MAS_USER_NAME, masUser.getText());
		// user password
		SettingProperites.put(Constants.MAS_USER_PASSWORD, masPswd.getText());
		// app id
		SettingProperites.put(Constants.MAS_APP, masAPP.getText());
		// mas db name
		SettingProperites.put(Constants.MAS_DB_NAME, masDBName.getText());
		// baudrate
		SettingProperites.put(Constants.BAUDRATE, baudrateList
				.getSelectedItem().toString());
		// seralPortListA
		SettingProperites.put(Constants.SERIAL_PORT_A, seralPortListA
				.getSelectedItem().toString());
		// seralPortListB
		SettingProperites.put(Constants.SERIAL_PORT_B, seralPortListB
				.getSelectedItem().toString());
		// no scan times
		SettingProperites.put(Constants.NO_SCAN_TIMES, noScanTimes.getText());
		// receive times
		SettingProperites.put(Constants.RECEIVE_RPT_TIME, receiveTimes
				.getText());

		try {
			SettingProperites.saveSetting();
			//关闭
			this.dispose();
		} catch (StudentSafeException e) {
			log.error("保存设置失败:" + e.getLocalizedMessage());
			MessageWindow.show("保存设置失败:" + e.getLocalizedMessage(), null);
			this.dispose();
		}
	}

	private JPanel jPanel1;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton commitButton;
	private JTextField masDBName;
	private JComboBox seralPortListB;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JButton cancelButton;
	private JLabel jLabel11;
	private JSeparator jSeparator1;
	private JPasswordField masPswd;
	private JLabel jLabel10;
	private JTextField masAPP;
	private JLabel jLabel9;
	private JTextField masUser;
	private JLabel jLabel8;
	private JTextField masIP;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JTextField noScanTimes;
	private JTextField receiveTimes;
	private JPanel jPanel3;
	private JComboBox baudrateList;
	private JLabel jLabel2;
	private JComboBox seralPortListA;
	private JLabel jLabel1;
	private JPanel jPanel2;

}
