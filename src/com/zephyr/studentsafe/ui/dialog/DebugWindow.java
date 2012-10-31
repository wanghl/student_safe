package com.zephyr.studentsafe.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.impl.ProcessStudentData;
import com.zephyr.studentsafe.ui.action.button.ButtonsAction;
import com.zephyr.studentsafe.ui.action.button.IButtonsAction;
import com.zephyr.studentsafe.util.StudentSafeUtil;

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
public class DebugWindow extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel jPanel1;
	private static JTable jTable1;
	private JScrollPane jScrollPane1;
	private JButton jButton3;
	private JSeparator jSeparator1;
	private JButton jButton1;
	private JTextField card_no;
	private JLabel jLabel4;
	private static JComboBox jComboBox2;
	private static JComboBox jComboBox1;
	private JLabel jLabel3;
	private static JButton startButton;
	private JPanel jPanel2;
	private JLabel jLabel1;
	private static JComboBox serialNumberList;
	private JPanel jPanel5;
	private JPanel jPanel3;

	private IButtonsAction buttonAction = new ButtonsAction();

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DebugWindow inst = new DebugWindow();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public DebugWindow() {
		super();
		setResizable(false);

		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setPreferredSize(new java.awt.Dimension(806, 590));
			{
				jPanel1 = new JPanel();
				BoxLayout jPanel1Layout = new BoxLayout(jPanel1,
						javax.swing.BoxLayout.Y_AXIS);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				{
					jPanel3 = new JPanel();
					GroupLayout gl_jPanel3 = new GroupLayout(
							(JComponent) jPanel3);
					jPanel3.setLayout(gl_jPanel3);
					jPanel1.add(jPanel3);
					jPanel1.add(getJPanel2());
					jPanel3.setPreferredSize(new java.awt.Dimension(788, 53));
					{
						jPanel5 = new JPanel();
						GroupLayout gl_jPanel5 = new GroupLayout(
								(JComponent) jPanel5);
						jPanel5.setLayout(gl_jPanel5);
						{
							ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
									StudentSafeUtil.getSerialPortList());
							serialNumberList = new JComboBox();
							serialNumberList.setModel(jComboBox1Model);
						}
						gl_jPanel5
								.setHorizontalGroup(gl_jPanel5
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(getJLabel1(),
												GroupLayout.PREFERRED_SIZE, 74,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(serialNumberList,
												GroupLayout.PREFERRED_SIZE,
												114, GroupLayout.PREFERRED_SIZE)
										.addGap(22)
										.addComponent(getJLabel4(),
												GroupLayout.PREFERRED_SIZE, 72,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(getJComboBox2(),
												GroupLayout.PREFERRED_SIZE,
												114, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(getJLabel3(),
												GroupLayout.PREFERRED_SIZE, 72,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(getJComboBox1(),
												GroupLayout.PREFERRED_SIZE,
												114, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(getJButton1(),
												GroupLayout.PREFERRED_SIZE, 97,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(27, Short.MAX_VALUE));
						gl_jPanel5
								.setVerticalGroup(gl_jPanel5
										.createSequentialGroup()
										.addGroup(
												gl_jPanel5
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																getJComboBox2(),
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																getJLabel4(),
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																serialNumberList,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																getJComboBox1(),
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																getJLabel1(),
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																getJLabel3(),
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																getJButton1(),
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE,
																24,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(41, 41));
					}
					gl_jPanel3.setHorizontalGroup(gl_jPanel3
							.createSequentialGroup().addContainerGap()
							.addComponent(jPanel5, 0, 761, Short.MAX_VALUE)
							.addContainerGap());
					gl_jPanel3.setVerticalGroup(gl_jPanel3
							.createSequentialGroup().addContainerGap(21, 21)
							.addComponent(jPanel5, 0, 35, Short.MAX_VALUE)
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED));
				}
			}
			pack();
			this.setSize(806, 590);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("\u9605\u8BFB\u56681\uFF1A");
		}
		return jLabel1;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			GroupLayout gl_jPanel2 = new GroupLayout((JComponent) jPanel2);
			gl_jPanel2
					.setHorizontalGroup(gl_jPanel2
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									gl_jPanel2
											.createSequentialGroup()
											.addContainerGap()
											.addGroup(
													gl_jPanel2
															.createParallelGroup(
																	Alignment.LEADING)
															.addComponent(
																	getJScrollPane1(),
																	GroupLayout.DEFAULT_SIZE,
																	772,
																	Short.MAX_VALUE)
															.addGroup(
																	gl_jPanel2
																			.createSequentialGroup()
																			.addComponent(
																					getCard_no(),
																					GroupLayout.PREFERRED_SIZE,
																					150,
																					GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(
																					ComponentPlacement.RELATED)
																			.addComponent(
																					getJButton1x(),
																					GroupLayout.PREFERRED_SIZE,
																					97,
																					GroupLayout.PREFERRED_SIZE)
																			.addGap(
																					22)
																			.addComponent(
																					getJSeparator1(),
																					GroupLayout.PREFERRED_SIZE,
																					14,
																					GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(
																					ComponentPlacement.RELATED)
																			.addComponent(
																					getJButton3(),
																					GroupLayout.PREFERRED_SIZE,
																					97,
																					GroupLayout.PREFERRED_SIZE)))
											.addContainerGap()));
			gl_jPanel2.setVerticalGroup(gl_jPanel2.createParallelGroup(
					Alignment.LEADING).addGroup(
					gl_jPanel2.createSequentialGroup().addGroup(
							gl_jPanel2.createParallelGroup(Alignment.BASELINE)
									.addComponent(getJButton1x(),
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(getCard_no(),
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(getJSeparator1(), 0, 29,
											Short.MAX_VALUE).addComponent(
											getJButton3(),
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(getJScrollPane1(),
									GroupLayout.PREFERRED_SIZE, 434,
									GroupLayout.PREFERRED_SIZE).addGap(23)));
			jPanel2.setLayout(gl_jPanel2);
			jPanel2.setPreferredSize(new java.awt.Dimension(788, 484));
		}
		return jPanel2;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
					null, null, null, null));
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	private static JTable getJTable1() {
		if (jTable1 == null) {
			TableModel jTable1Model = new DefaultTableModel(null, new String[] {
					"序号", "卡号", "首次经过区域", "最终经过区域", "收到信号次数", "结果" });
			// JTABLE所有行都设为不可编辑.
			jTable1 = new JTable(jTable1Model) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
		}
		
		return jTable1;
	}

	private JButton getJButton1() {
		if (startButton == null) {
			startButton = new JButton();
			startButton.setText("\u5f00\u59cb");
			startButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						buttonAction.startButtonPerformeAction(e, startButton);
					} catch (StudentSafeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}) ;
		}
		return startButton;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("\u9605\u8BFB\u56683\uFF1A");
		}
		return jLabel3;
	}

	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
					StudentSafeUtil.getSerialPortList());
			jComboBox1 = new JComboBox();
			jComboBox1.setModel(jComboBox1Model);
		}
		return jComboBox1;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("\u9605\u8BFB\u56682\uFF1A");
		}
		return jLabel4;
	}

	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
			ComboBoxModel jComboBox2Model = new DefaultComboBoxModel(
					StudentSafeUtil.getSerialPortList());
			jComboBox2 = new JComboBox();
			jComboBox2.setModel(jComboBox2Model);

		}
		return jComboBox2;
	}

	// 窗口关闭事件处理。关闭时释放资源
	// 资源释放完成后必须调用父类的processWindowEvent方法来关闭
	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			ProcessStudentData._DEBUG_MODEL = 0 ;
			super.processWindowEvent(e);
			
			
		}
	}

	public static JComboBox getReaderAjcombox() {
		return serialNumberList;
	}

	public static JComboBox getReaderBjcombox() {
		return jComboBox2;
	}

	public static JComboBox getReaderCjcombox() {
		return jComboBox1;
	}

	public static JTable getTable() {
		return  getJTable1();
	}

	public static JButton getButton() {
		return startButton;
	}

	private JTextField getCard_no() {
		if (card_no == null) {
			card_no = new JTextField();
		}
		return card_no;
	}

	private JButton getJButton1x() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("\u67e5\u627e");
			jButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					int rowCount = getTable().getRowCount();
					DefaultTableModel model = (DefaultTableModel) getTable()
							.getModel();
					String cid;
					Rectangle reg;
					for (int i = 0; i < rowCount; i++) {

						cid = model.getValueAt(i, 1).toString();
						if (cid.equals(card_no.getText().trim())) {
							getTable().getSelectionModel()
									.setSelectionInterval(i, i);
							reg = getTable().getCellRect(i, 1, true);
							getTable().scrollRectToVisible(reg);
							break;

						}

					}
				}
			});
		}
		return jButton1;
	}

	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
			jSeparator1.setOrientation(SwingConstants.VERTICAL);
		}
		return jSeparator1;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("\u6e05\u7a7a\u5185\u5bb9");
			jButton3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					
					try {
						buttonAction.clearRfidTablePerformeAction(evt, null);
					} catch (StudentSafeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return jButton3;
	}

}
