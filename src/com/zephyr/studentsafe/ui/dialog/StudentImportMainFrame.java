package com.zephyr.studentsafe.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.zephyr.studentsafe.impl.ProcessStudentData;
import com.zephyr.studentsafe.serialport.RfidReader;
import com.zephyr.studentsafe.ui.action.TableEventHandle;
import com.zephyr.studentsafe.ui.action.button.ExportRowsToExcel;
import com.zephyr.studentsafe.ui.action.button.ImportFromDatabase;
import com.zephyr.studentsafe.ui.action.button.ImportStudentInfoAction;
import com.zephyr.studentsafe.ui.action.button.InsertRowsToDBAction;
import com.zephyr.studentsafe.ui.action.button.ReadCardNumber4Dev;
import com.zephyr.studentsafe.ui.action.button.ReadeCardNumberAction;

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
public class StudentImportMainFrame extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try
		{
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private JPanel jPanel3;
	private JPanel jPanel2;
	private JToolBar jToolBar1;
	private JPanel jPanel1;
	private static JLabel cardCount;
	private JLabel jLabel2;
	private static JLabel inputCount;
	private JLabel jLabel1;
	private static JTable jTable1;
	private JScrollPane jScrollPane1;
	private JButton exportDBButton;
	private JLabel textLabel;
	private static JButton exportButton;
	private static JButton insert2DBButton;
	private static JButton readRficButton;
	private static JButton importButton;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StudentImportMainFrame inst = new StudentImportMainFrame();
				inst.setLocationRelativeTo(null);
				inst.setDefaultCloseOperation(EXIT_ON_CLOSE);
				inst.setVisible(true);
			}
		});
	}

	public StudentImportMainFrame() {
		super();
		
		initGUI();
	}

	private void initGUI() {
		try
		{
			BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS);
			getContentPane().setLayout(thisLayout);
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/icons/log.gif")).getImage());
			this.setTitle("\u6279\u91cf\u5bfc\u5165\u5de5\u5177");
			{
				jPanel1 = new JPanel();
				BorderLayout jPanel1Layout = new BorderLayout();
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1);
				jPanel1.setPreferredSize(new java.awt.Dimension(771, 50));
				jPanel1.setMaximumSize(new java.awt.Dimension(32767, 50));
				jPanel1.setBorder(BorderFactory.createTitledBorder(""));
				{
					jToolBar1 = new JToolBar();
					jPanel1.add(jToolBar1, BorderLayout.CENTER);
					jToolBar1.setBounds(6, 6, 759, 41);
					{
						jPanel3 = new JPanel();
						jPanel3.setLayout(null);
						jToolBar1.add(jPanel3);
						jPanel3.setPreferredSize(new java.awt.Dimension(278, 38));
						{
							importButton = new JButton();
							jPanel3.add(importButton);
							importButton.setText("\u8bfb\u6587\u4ef6");
							importButton.setBounds(0, 5, 99, 27);
							importButton.addActionListener(new ImportStudentInfoAction());
							importButton.setActionMap(null);
							importButton.addKeyListener(new KeyAdapter(){
								public void keyPressed(KeyEvent  e){
									if(e.getKeyChar() == '\n'){
										e.setKeyCode(ABORT) ;
									}
								}
							});
							
						}
						{
							readRficButton = new JButton();
							jPanel3.add(readRficButton);
							readRficButton.setText("\u8bfb\u5361");
							readRficButton.setBounds(101, 5, 99, 27);
							readRficButton.setEnabled(false);
							readRficButton.addActionListener(new ReadeCardNumberAction());
							readRficButton.setActionMap(null);
							readRficButton.addKeyListener(new KeyAdapter(){
								public void keyPressed(KeyEvent  e){
									if(e.getKeyChar() == '\n'){
										e.setKeyCode(ABORT) ;
									}
								}
							});
						}
						{
							insert2DBButton = new JButton();
							jPanel3.add(insert2DBButton);
							insert2DBButton.setText("\u4fdd\u5b58\u5230\u6570\u636e\u5e93");
							insert2DBButton.setBounds(202, 5, 124, 27);
							insert2DBButton.addActionListener(new InsertRowsToDBAction());
							insert2DBButton.setEnabled(false);
							insert2DBButton.setActionMap(null);
							insert2DBButton.addKeyListener(new KeyAdapter(){
								public void keyPressed(KeyEvent  e){
									if(e.getKeyChar() == '\n'){
										e.setKeyCode(ABORT) ;
									}
								}
							});

						}
						{
							exportButton = new JButton();
							jPanel3.add(exportButton);
							exportButton.setText("\u5bfc\u51faExcel");
							exportButton.setBounds(455, 5, 109, 27);
							exportButton.addActionListener(new ExportRowsToExcel());
							exportButton.setEnabled(false);
							exportButton.setActionMap(null);
							exportButton.addKeyListener(new KeyAdapter(){
								public void keyPressed(KeyEvent  e){
									if(e.getKeyChar() == '\n'){
										e.setKeyCode(ABORT) ;
									}
								}
							});

						}
						{
							jLabel1 = new JLabel();
							jPanel3.add(jLabel1);
							jLabel1.setText("\u5bfc\u5165\u6761\u6570\uff1a");
							jLabel1.setBounds(571, 9, 77, 20);
						}
						{
							inputCount = new JLabel();
							jPanel3.add(inputCount);
							inputCount.setBounds(641, 9, 47, 20);
							inputCount.setText("0");
						}
						{
							jLabel2 = new JLabel();
							jPanel3.add(jLabel2);
							jLabel2.setBounds(696, 9, 76, 20);
							jLabel2.setText("\u53d1\u5361\u603b\u6570\uff1a");
						}
						{
							cardCount = new JLabel();
							jPanel3.add(cardCount);
							cardCount.setBounds(767, 9, 63, 20);
							cardCount.setText("0");
						}
						{
							exportDBButton = new JButton();
							jPanel3.add(exportDBButton);
							exportDBButton.setText("\u4ece\u6570\u636e\u5e93\u5bfc\u5165");
							exportDBButton.setBounds(328, 5, 125, 27);
							exportDBButton.addActionListener(new ImportFromDatabase());
							exportDBButton.setActionMap(null);
							exportDBButton.addKeyListener(new KeyAdapter(){
								public void keyPressed(KeyEvent  e){
									if(e.getKeyChar() == '\n'){
										e.setKeyCode(ABORT) ;
									}
								}
							});
						}
					}
				}
			}
			{
				jPanel2 = new JPanel();
				BorderLayout jPanel2Layout = new BorderLayout();
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2);
				{
					jScrollPane1 = new JScrollPane();
					jPanel2.add(jScrollPane1, BorderLayout.CENTER);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(866, 589));
					{
						textLabel = new JLabel();
						jScrollPane1.setViewportView(textLabel);
						textLabel.setText("jLabel3");
						textLabel.setPreferredSize(new java.awt.Dimension(767, 385));
					}
					{
						TableModel jTable1Model =
								new DefaultTableModel(
										null,
										new String[] { "序号", "班级", "姓名", "卡号", "低频卡号", "性别", "生日",
												"班主任", "电话", "家长姓名", "性别", "家庭关系", "联系电话", "导入结果" }
										);
						jTable1 = new JTable(jTable1Model);
//							public boolean isCellEditable(int row, int column) {
//								return false;
//							}
//						};
						jScrollPane1.setViewportView(jTable1);
						jTable1.setModel(jTable1Model);
						jTable1.setRowHeight(20);
						jTable1.setDefaultRenderer(Object.class, new RowColorRenderer());
						jTable1.getModel().addTableModelListener(new TableEventHandle());
						
					}
				}
			}
			this.setSize(884, 684);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// 窗口关闭事件处理。关闭时释放资源
	// 资源释放完成后必须调用父类的processWindowEvent方法来关闭
	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			this.dispose();
			RfidReader.close() ;

		}
	}


	public static JTable getTable() {
		return jTable1;
	}

	public static void setButtonEnable(boolean flag) {

		readRficButton.setEnabled(flag);
		exportButton.setEnabled(flag);
		insert2DBButton.setEnabled(flag);
		if (!RfidReader.isClosed())
		{
			readRficButton.setEnabled(false);
			
		}

	}

	public static void setImportCount(int i) {
		inputCount.setText(Integer.toString(i));
	}

	public static int getImportCount() {
		return Integer.parseInt(inputCount.getText());
	}

	public static void setCardCount(int i) {
		cardCount.setText(Integer.toString(i));

	}

	public static int getCardCount() {
		return Integer.parseInt(cardCount.getText());
	}

}

// 内部类 ，各行改变JTable颜色
class RowColorRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable t, Object value,
							boolean isSelected, boolean hasFocus, int row, int column) {
		DefaultTableModel model = (DefaultTableModel) t.getModel();
		if (value != null && value.toString().equals("失败"))
		{
			setBackground(Color.RED);
		} else
		{

			setBackground(new Color(255, 255, 255));
		}
		this.setHorizontalAlignment(CENTER);
		return super.getTableCellRendererComponent(t, value, isSelected,
							hasFocus, row, column);
	}
}
