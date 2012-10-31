package com.zephyr.studentsafe.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.bo.Teacher;
import com.zephyr.studentsafe.dao.ClassInfoDAO;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.serialport.RfidReader;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.ui.ZephyrPntMainFrame;

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
public class StudentInfoManage extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JButton newFamilyButton;
	private JButton newStudentButton;
	private JButton editButton;
	public static JButton queryButton;
	private JPanel jPanel5;
	private JPanel jPanel4;
	private JLabel jLabel4;
	private JComboBox classList;
	private JScrollPane jScrollPane2;
	private JTable familyInfoTable;
	private JPanel jPanel6;
	private JTabbedPane jTabbedPane1;
	private JComboBox teacherList;
	private JLabel jLabel3;
	private JTextField studentName;
	private JLabel jLabel2;
	private JTextField studentNumber;
	private JLabel jLabel1;
	private JTable studentInfoTable;
	private JPanel jPanel3;
	private JPanel jPanel2;

	ClassInfoDAO dao = new ClassInfoDAO();

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

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				final StudentInfoManage inst = new StudentInfoManage(frame);
				inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				inst.addWindowListener(new WindowListener(){

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						inst.dispose() ;
					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				inst.setVisible(true);
			}
		});
	}

	public StudentInfoManage(JFrame frame) {
		super(frame);
		initGUI();
	}

	private void initGUI() {
		try
		{
			{
				this.setTitle("\u5b66\u751f\u8d44\u6599\u7ba1\u7406");
			}
			{
				jPanel1 = new JPanel();
				BoxLayout jPanel1Layout = new BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				{
					jTabbedPane1 = new JTabbedPane();
					jPanel1.add(jTabbedPane1);
					{
						jPanel2 = new JPanel();
						jTabbedPane1.addTab("学生信息", null, jPanel2, null);
						GridLayout jPanel2Layout = new GridLayout(1, 1);
						jPanel2Layout.setHgap(5);
						jPanel2Layout.setVgap(5);
						jPanel2Layout.setColumns(1);
						jPanel2.setLayout(jPanel2Layout);
						jPanel2.setPreferredSize(new java.awt.Dimension(631, 663));
						jPanel2.setEnabled(false);
						{
							jScrollPane1 = new JScrollPane();
							jPanel2.add(jScrollPane1);
							jScrollPane1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
									null, null, null));
							jScrollPane1.setPreferredSize(new java.awt.Dimension(623, 663));
							{
								TableModel studentInfoTableModel =
										new DefaultTableModel(
												null,
												new String[] { "序号", "学生姓名", "卡号", "语音卡号", "班级",
													});
								studentInfoTable = new JTable(studentInfoTableModel) {
									public boolean isCellEditable(int row, int column) {
										return false;
									}
								};
								studentInfoTable.setRowHeight(20);
								studentInfoTable.setDefaultRenderer(Object.class,
										new ColorRenderer());
								studentInfoTable.addFocusListener(new FocusListener(){

									@Override
									public void focusGained(FocusEvent e) {
										// TODO Auto-generated method stub
										familyInfoTable.clearSelection();
									}

									@Override
									public void focusLost(FocusEvent e) {
										// TODO Auto-generated method stub
										
									}
									
								});

								jScrollPane1.setViewportView(studentInfoTable);
							}
						}
					}
					{
						jPanel6 = new JPanel();
						jTabbedPane1.addTab("家长信息", null, jPanel6, null);
						GridLayout jPanel6Layout = new GridLayout(1, 1);
						jPanel6Layout.setHgap(5);
						jPanel6Layout.setVgap(5);
						jPanel6Layout.setColumns(1);
						jPanel6.setLayout(jPanel6Layout);
						{
							jScrollPane2 = new JScrollPane();
							jPanel6.add(jScrollPane2);
							{
								TableModel jTable1Model =
										new DefaultTableModel(
												null,
												new String[] { "序号", "学生姓名", "家长姓名", "家庭关系",
														"联系电话"});
								familyInfoTable = new JTable(jTable1Model) {
									public boolean isCellEditable(int row, int column) {
										return false;
									}
								};
								familyInfoTable.setRowHeight(20);
								familyInfoTable.setDefaultRenderer(Object.class,
										new ColorRenderer());
								familyInfoTable.addFocusListener(new FocusListener(){

									@Override
									public void focusGained(FocusEvent e) {
										// TODO Auto-generated method stub
										studentInfoTable.clearSelection();
									}

									@Override
									public void focusLost(FocusEvent e) {
										// TODO Auto-generated method stub
										
									}
								
								
								}) ;

								jScrollPane2.setViewportView(familyInfoTable);
							}
						}
					}
				}
				{
					jPanel3 = new JPanel();
					jPanel1.add(jPanel3);
					jPanel3.setLayout(null);
					jPanel3.setSize(200, 663);
					jPanel3.setPreferredSize(new java.awt.Dimension(200, 323));
					{
						jPanel4 = new JPanel();
						jPanel4.setLayout(null);
						jPanel3.add(jPanel4);
						jPanel4.setBounds(4, 9, 190, 192);
						jPanel4.setBorder(BorderFactory.createTitledBorder(""));
						jPanel4.setToolTipText("\u67e5\u8be2");
						jPanel4.setOpaque(false);
						{
							jLabel1 = new JLabel();
							jPanel4.add(jLabel1);
							jLabel1.setText("\u5361\u53f7");
							jLabel1.setBounds(6, 25, 36, 18);
						}
						{
							studentNumber = new JTextField();
							jPanel4.add(studentNumber);
							studentNumber.setBounds(44, 19, 139, 24);
							studentNumber.setText(null);
							studentNumber.addKeyListener(new KeyAdapter() {

								public void keyPressed(KeyEvent e) {
									if (e.getKeyCode() == e.VK_ENTER)
										{
											queryButtonEvent();
										}
									}

							});
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									studentNumber.requestFocus();
								}
							});

						}
						{
							jLabel4 = new JLabel();
							jPanel4.add(jLabel4);
							jLabel4.setText("\u6559\u5e08");
							jLabel4.setBounds(6, 122, 36, 18);
						}
						{
							studentName = new JTextField();
							jPanel4.add(studentName);
							studentName.setBounds(44, 49, 139, 26);
							studentName.setText(null);
						}
						{
							jLabel2 = new JLabel();
							jPanel4.add(jLabel2);
							jLabel2.setText("\u73ed\u7ea7");
							jLabel2.setBounds(6, 88, 36, 18);
						}
						{

							classList = new JComboBox();
							List l = dao.getObjList(ClassInfo.class);
							if (l != null)
							{
								for (int i = 0; i < l.size(); i++)
								{
									classList.addItem(l.get(i));

								}
							}
							jPanel4.add(classList);
							classList.insertItemAt(null, 0);
							classList.setSelectedIndex(0);
							classList.setBounds(44, 82, 139, 26);
						}
						{
							jLabel3 = new JLabel();
							jPanel4.add(jLabel3);
							jLabel3.setText("\u59d3\u540d");
							jLabel3.setBounds(6, 53, 36, 18);
						}
						{
							teacherList = new JComboBox();
							List l = dao.getObjList(Teacher.class);
							if (l != null)
							{
								for (int i = 0; i < l.size(); i++)
								{
									teacherList.addItem(l.get(i));

								}
							}
							teacherList = new JComboBox();
							jPanel4.add(teacherList);
							teacherList.insertItemAt(null, 0);
							teacherList.setSelectedIndex(0);
							teacherList.setBounds(44, 114, 139, 26);
						}
						{
							queryButton = new JButton();
							jPanel4.add(queryButton);
							queryButton.setText("\u67e5\u8be2");
							queryButton.setBounds(28, 153, 138, 27);
							queryButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									queryButtonEvent();
								}
							});
						}
					}
					{
						jPanel5 = new JPanel();
						jPanel3.add(jPanel5);
						jPanel5.setBounds(6, 206, 188, 455);
						jPanel5.setBorder(BorderFactory.createTitledBorder(""));
						{
							editButton = new JButton();
							jPanel5.add(editButton);
							editButton.setText("\u7f16\u8f91");
							editButton.setPreferredSize(new java.awt.Dimension(139, 27));
							editButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									if(studentInfoTable.getSelectedRow() > 0)
									{
										studentEdit();
									}else if (familyInfoTable.getSelectedRow() > 0){
										familyedit();
									}else {
										MessageWindow.show("请选择要编辑的学生或家长信息");
									}
								}
							});
						}
						{
							newStudentButton = new JButton();
							jPanel5.add(newStudentButton);
							newStudentButton.setText("\u65b0\u589e\u5b66\u751f\u4fe1\u606f");
							newStudentButton.setPreferredSize(new java.awt.Dimension(139, 27));
							newStudentButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									NewStudentInfo inst = new NewStudentInfo(new ZephyrPntMainFrame());
									inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									inst.setLocationRelativeTo(new ZephyrPntMainFrame());
									inst.setVisible(true);
								}
							});
						}
						{
							newFamilyButton = new JButton();
							jPanel5.add(newFamilyButton);
							newFamilyButton.setText("\u65b0\u589e\u5bb6\u957f\u4fe1\u606f");
							newFamilyButton.setPreferredSize(new java.awt.Dimension(139, 27));
							newFamilyButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									addFamilyInfo();
								}
							});
						}
					}
				}
			}
			this.setSize(850, 708);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			super.processWindowEvent(e);
			this.dispose();

		}
	}

	// 查询按钮事件
	private void queryButtonEvent() {
		Studentrfid student = new Studentrfid();
		List list = null;
		String cardNumber = studentNumber.getText().equals("") ? null : studentNumber.getText();

		if (cardNumber != null)
		{
			if (cardNumber.length() == 10)
			{
				student.setLowCardNumber(cardNumber);
			} else
			{
				student.setRfidCardID(cardNumber);
			}
		}

		student.setStudentName(studentName.getText().equals("") ? null : studentName.getText());
		if (classList.getSelectedItem() != null)
		{
			student.setClassUID(((ClassInfo) classList.getSelectedItem()).getClassUID());

		}
		if (teacherList.getSelectedItem() != null)
		{
			list = dao.getTeacherByName(teacherList.getSelectedItem().toString());
			student.setTeacherUID(list.get(0).toString());
		}

		list = dao.getByExample(Studentrfid.class, student);
		if (!list.isEmpty())
		{
			updateTable(list);
		} else
		{
			// 无数据 ，清除数据表
			((DefaultTableModel) studentInfoTable.getModel()).getDataVector().clear();
			((DefaultTableModel) studentInfoTable.getModel()).fireTableDataChanged();

			((DefaultTableModel) familyInfoTable.getModel()).getDataVector().clear();
			((DefaultTableModel) familyInfoTable.getModel()).fireTableDataChanged();
			MessageWindow.show("无数据");
		}
	}
	//按条件查询数据，结果刷新到jtable中
	private void updateTable(final List dataList) {
		final DefaultTableModel studentModel = (DefaultTableModel) studentInfoTable.getModel();
		final DefaultTableModel familyModel = (DefaultTableModel) familyInfoTable.getModel();
		//插入新数据前先清空表格
		studentModel.getDataVector().clear();
		studentModel.fireTableDataChanged();

		familyModel.getDataVector().clear();
		familyModel.fireTableDataChanged();
		//把list里的数据搞到set中，过滤重复数据
		final Set set = new HashSet();
		for(int i = 0 ; i < dataList.size() ; i++)
		{
			set.add(dataList.get(i));
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Studentrfid student = new Studentrfid();
				Studentfamily family = new Studentfamily();
				for (Iterator it = set.iterator(); it.hasNext();)
				{
					student = (Studentrfid) it.next();
					// 插入学生信息
					studentModel.addRow(new Object[] {
							studentModel.getRowCount() + 1,
								student.getStudentName(),
								student.getRfidCardID(),
								student.getLowCardNumber(),
								((ClassInfo) dao.get(ClassInfo.class, student.getClassUID()))
										.getClassName() });

					for (Iterator<Studentfamily> its = student.getStudentFamily().iterator(); its
							.hasNext();)
					{
						family = its.next();
						familyModel.addRow(new Object[] { familyModel.getRowCount() + 1,
								student.getStudentName(),
								family.getFamilyName(),
								family.getRelationship(),
								family.getFamilyPhone()

						});
					}
				}
			}
		});

		studentInfoTable.repaint();
		studentInfoTable.updateUI();

		familyInfoTable.repaint();
		familyInfoTable.updateUI();

	}
	
	
	//编辑学生信息
	private void studentEdit() {
		int selectRow = studentInfoTable.getSelectedRow();
		StudentDAO dao = new StudentDAO();
		String rfid = studentInfoTable.getValueAt(selectRow, 2).toString();
		final Studentrfid student = (Studentrfid) dao.getStudentbyCardID(rfid);

		StudentEditerDialog inst = new StudentEditerDialog(new ZephyrPntMainFrame(), student);
		inst.setLocationRelativeTo(new ZephyrPntMainFrame());
		inst.setVisible(true);

	}
	//编辑家长信息
	private void familyedit(){
		int selectRow = familyInfoTable.getSelectedRow();
		Studentfamily family = new Studentfamily();
		family.setFamilyName(familyInfoTable.getValueAt(selectRow, 2).toString());
		family.setFamilyPhone(familyInfoTable.getValueAt(selectRow, 4).toString());
		List l =  dao.getByExample(Studentfamily.class, family);
		if(! l.isEmpty())
		{
			family = (Studentfamily) l.get(0);
			ZephyrPntMainFrame frame = new ZephyrPntMainFrame();
			FamilyEditerDialog inst = new FamilyEditerDialog(frame,family);
			inst.setLocationRelativeTo(frame);
			inst.setVisible(true);
			
		}
	}
	//新增家长信息
	private void addFamilyInfo(){
		int selectedRow = studentInfoTable.getSelectedRow();
		if(selectedRow < 0)
		{
			MessageWindow.show("请选择关联的学生");
			return ;
		}
		StudentDAO dao = new StudentDAO();
		Studentrfid student = dao.getStudentbyCardID(studentInfoTable.getValueAt(selectedRow, 2).toString()) ;
		ZephyrPntMainFrame frame = new ZephyrPntMainFrame();
		NewStudentInfo inst = new NewStudentInfo(frame,student);
		inst.setLocationRelativeTo(frame);
		inst.setVisible(true);
	}
	public static JButton getQueryButton() {
		return queryButton;
	}

}

// 内部类 ，各行改变JTable颜色
class ColorRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable t, Object value,
							boolean isSelected, boolean hasFocus, int row, int column) {
		// 分支判断条件可根据需要进行修改
		if (row % 2 == 0)
		{
			setBackground(new Color(206, 231, 255));
		} else
		{

			setBackground(new Color(255, 255, 255));
		}
		this.setHorizontalAlignment(CENTER);
		return super.getTableCellRendererComponent(t, value, isSelected,
							hasFocus, row, column);
	}
}