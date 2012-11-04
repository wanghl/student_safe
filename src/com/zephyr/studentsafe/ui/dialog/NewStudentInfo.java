package com.zephyr.studentsafe.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.Document;

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.ui.MessageWindow;

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
public class NewStudentInfo extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	private JPanel jPanel1;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel removeFamilyButton;
	private JButton cancelButton;
	private JButton saveButton;
	private JLabel addFamilyButton;
	private JTable familyTable;
	private JScrollPane jscroolepane;
	private JPanel jPanel2;
	private JComboBox classList;
	private JComboBox studentSex;
	private JTextField lowCardNumber;
	private JLabel jLabel2;
	private JTextField cardNumber;
	private JTextField nameInput;
	private JLabel jLabel1;
	boolean update;
	Studentrfid student;
	List<Integer> newRows = new ArrayList<Integer>();
	boolean showStudent = false ;
	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				NewStudentInfo inst = new NewStudentInfo(frame);
				inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				inst.setVisible(true);
			}
		});
	}

	public NewStudentInfo(JFrame frame) {
		super(frame);
		initGUI();
	}
	//编辑信息用
	public NewStudentInfo(JFrame frame, Studentrfid student) {
		super(frame);
		initGUI();
		this.student = student;
		initData(student);
	}
	//双击进出校信息 显示学生资料用
	public NewStudentInfo(JFrame frame ,Studentrfid student ,boolean enable){
		super(frame);
		initGUI();
		this.student = student;
		this.showStudent = enable;
		initData(student);
	}

	private void initGUI() {
		try
		{
			{
				this.setTitle("\u65b0\u589e\u4fe1\u606f");
				this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/icons/log.gif")).getImage());

			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setPreferredSize(new java.awt.Dimension(500, 536));
				jPanel1.setLayout(null);
				jPanel1.setBorder(BorderFactory.createTitledBorder(""));
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("\u59d3\u540d");
					jLabel1.setBounds(27, 26, 43, 27);
				}
				{
					nameInput = new JTextField();
					jPanel1.add(nameInput);
					nameInput.setBounds(70, 21, 187, 27);
				}
				{
					cardNumber = new JTextField();
					jPanel1.add(cardNumber);
					cardNumber.setBounds(70, 60, 187, 27);

				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("\u5361\u53f7");
					jLabel2.setBounds(27, 60, 43, 27);
				}
				{
					lowCardNumber = new JTextField();
					jPanel1.add(lowCardNumber);
					lowCardNumber.setBounds(70, 99, 187, 27);
				}
				{
					jLabel3 = new JLabel();
					jPanel1.add(jLabel3);
					jLabel3.setText("\u8bed\u97f3\u5361");
					jLabel3.setBounds(18, 99, 51, 27);
				}
				{
					jLabel4 = new JLabel();
					jPanel1.add(jLabel4);
					jLabel4.setText("\u6027\u522b");
					jLabel4.setBounds(27, 138, 43, 18);
				}
				{
					ComboBoxModel jComboBox1Model =
							new DefaultComboBoxModel(
									new String[] { "男", "女" });
					studentSex = new JComboBox();
					jPanel1.add(studentSex);
					studentSex.setModel(jComboBox1Model);
					studentSex.setBounds(70, 138, 188, 27);
				}
				{
					jLabel5 = new JLabel();
					jPanel1.add(jLabel5);
					jLabel5.setText("\u73ed\u7ea7");
					jLabel5.setBounds(27, 178, 43, 18);
				}
				{
					classList = new JComboBox();
					BaseDAO dao = new BaseDAO();
					List l = dao.getObjList(ClassInfo.class);
					if (l != null)
					{
						for (int i = 0; i < l.size(); i++)
						{
							classList.addItem(l.get(i));

						}
					}
					classList.insertItemAt(null, 0);
					classList.setSelectedIndex(0);
					jPanel1.add(classList);

					classList.setBounds(70, 178, 188, 27);
				}
				{
					jPanel2 = new JPanel();
					jPanel2.setLayout(null);
					jPanel1.add(jPanel2);
					jPanel2.setBounds(6, 212, 472, 256);
					jPanel2.setBorder(BorderFactory.createTitledBorder("\u5bb6\u957f\u4fe1\u606f"));
					{
						addFamilyButton = new JLabel();
						jPanel2.add(addFamilyButton);
						addFamilyButton.setText("addFamilyButton");
						addFamilyButton.setBounds(6, 22, 33, 24);
						addFamilyButton.setIcon(new ImageIcon(getClass().getClassLoader()
								.getResource("com/zephyr/studentsafe/icons/add.gif")));
						addFamilyButton.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								// 单击加号 新增一行
								DefaultTableModel model = (DefaultTableModel) familyTable
										.getModel();
								model.addRow(new Object[] { model.getRowCount() + 1, null, null,
										null });

								if (update)
								{
									newRows.add(model.getRowCount() - 1);
								}
							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub

							}

						});
					}
					{
						removeFamilyButton = new JLabel();
						jPanel2.add(removeFamilyButton);
						removeFamilyButton.setText("removeFamilyButton");
						removeFamilyButton.setBounds(39, 29, 33, 10);
						removeFamilyButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
								"com/zephyr/studentsafe/icons/117.png")));
						removeFamilyButton.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								DefaultTableModel model = (DefaultTableModel) familyTable
										.getModel();
								int selectedRow = familyTable.getSelectedRow();
								if (selectedRow < 0)
								{
									MessageWindow.show("请选择要删除的家长信息");
									return;
								}
								model.removeRow(selectedRow);
							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub

							}

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub

							}

						});
					}
					{
						jscroolepane = new JScrollPane();
						jPanel2.add(jscroolepane);
						jscroolepane.setBounds(2, 52, 466, 198);
						{
							TableModel jTable1Model =
									new DefaultTableModel(
											null,
											new String[] { "序号", "姓名", "家庭关系", "联系电话" });
							familyTable = new JTable();
							jscroolepane.setViewportView(familyTable);
							familyTable.setModel(jTable1Model);
							familyTable.setRowHeight(20);
							familyTable.setDefaultRenderer(Object.class,
									new ColorRenderer());
						}
					}
				}
				{
					saveButton = new JButton();
					jPanel1.add(saveButton);
					saveButton.setText("\u4fdd\u5b58");
					saveButton.setBounds(268, 475, 99, 30);
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							saveStudent();
						}
					});
				}
				{
					cancelButton = new JButton();
					jPanel1.add(cancelButton);
					cancelButton.setText("\u53d6\u6d88");
					cancelButton.setBounds(371, 475, 99, 30);
					cancelButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dispose();

						}

					});
				}
			}
			this.setSize(502, 559);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private int verification() {
		if (nameInput.getText().equals(""))
		{
			MessageWindow.show("请填写学生姓名");
			return -1;
		}
		if (cardNumber.getText().equals(""))
		{
			MessageWindow.show("学生卡号不能为空");
			return -1;
		}

		if (classList.getSelectedItem() == null)
		{
			MessageWindow.show("请选择班级");
			return -1;
		}
		return 0;
	}

	private void saveStudent() {
		if (verification() < 0)
		{
			return;
		}
		StudentDAO dao = new StudentDAO();
		Studentrfid student = new Studentrfid();
		DefaultTableModel model = (DefaultTableModel) familyTable.getModel();
		if (!update)
		{
			if (dao.getStudentbyCardID(cardNumber.getText()) != null)
			{
				MessageWindow.show("卡号 " + cardNumber.getText() + " 已存在");
				return;
			}
			Studentfamily family = null;
			Set<Studentfamily> set = new HashSet<Studentfamily>();
			student.setStudentName(nameInput.getText());
			student.setStudentSex(studentSex.getSelectedItem().toString());
			student.setRfidCardID(cardNumber.getText());
			student.setLowCardNumber(lowCardNumber.getText());
			student.setClassInfo((ClassInfo)classList.getSelectedItem());
			for (int i = 0; i < model.getRowCount(); i++)
			{
				if (model.getValueAt(i, 1) == null || model.getValueAt(i, 3) == null)
				{
					MessageWindow.show("请填写完整家长信息");
					return;
				}

				family = new Studentfamily();
				family.setFamilyName(model.getValueAt(i, 1).toString());
				family.setFamilyPhone(model.getValueAt(i, 3).toString());
				set.add(family);

			}
			student.setStudentFamily(set);
		} else
		{
			student = this.student;
			Studentfamily family = null;
			Set<Studentfamily> set = student.getStudentFamily();
			for (int i : newRows)
			{
				// 先检查家长信息录入是否完整
				if (model.getValueAt(i, 3) == null)
				{
					MessageWindow.show("手机号不能为空");
					return;
				}
				family = new Studentfamily();
				family.setFamilyName(model.getValueAt(i, 1).toString());
				family.setRelationship(model.getValueAt(i, 2).toString());
				family.setFamilyPhone(model.getValueAt(i, 3).toString());
				set.add(family);

			}
			student.setStudentFamily(set);
		}
		try
		{
			dao.saveORupdate(student);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 StudentInfoManage.getQueryButton().doClick();
		this.dispose();
	}

	private void initData(Studentrfid student) {
		update = true;
		// 填学生信息
		BaseDAO dao = new BaseDAO();
		nameInput.setText(student.getStudentName());
		nameInput.setEditable(false);
		cardNumber.setText(student.getRfidCardID());
		cardNumber.setEditable(false);
		lowCardNumber.setText(student.getLowCardNumber());
		lowCardNumber.setEditable(false);
		studentSex.setSelectedItem(student.getStudentSex());
		studentSex.setEnabled(false);
		ClassInfo classInfo = new ClassInfo();
		classInfo.setClassUID(student.getClassInfo().getClassUID());

		classList.removeAllItems();
		classList.insertItemAt(dao.get(ClassInfo.class, student.getClassInfo().getClassUID()), 0);
		//classList.setSelectedItem(student.getClassInfo()) ;
		classList.setSelectedIndex(0);
		classList.setEnabled(false);
		// for (int i = 0; i < classList.getItemCount(); i++)
		// {
		// if (((ClassInfo)
		// classList.getItemAt(i)).getClassUID().equals(classInfo.getClassUID()))
		// {
		// classList.setSelectedIndex(i);
		// break;
		// }
		// }
		// 家长信息
		DefaultTableModel model = (DefaultTableModel) familyTable.getModel();
		Studentfamily family = null;
		for (Iterator<Studentfamily> it = student.getStudentFamily().iterator(); it.hasNext();)
		{
			family = it.next();
			model.addRow(new Object[] {
					model.getRowCount() + 1,
					family.getFamilyName(),
					family.getRelationship(),
					family.getFamilyPhone()
			});
		}
		//如果是在进出校信息表里双击某行 
		if(showStudent)
		{
			addFamilyButton.setEnabled(false);
			removeFamilyButton.setEnabled(false);
			saveButton.setEnabled(false);
		}
	}

}

// 内部类 ，各行改变JTable颜色
class Renderer extends DefaultTableCellRenderer implements TableCellRenderer {
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
