package com.zephyr.studentsafe.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.zephyr.studentsafe.bo.Teacher;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.dao.TeacherDAO;
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
public class TeacherManage extends javax.swing.JDialog {

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
	private JScrollPane jScrollPane1;
	private JButton batchInput;
	private JButton editTeacher;
	private JButton flashTeacher;
	private JButton newTeacher;
	private JButton deleteTeacher;
	private static JTable teacherTable;
	private JPanel jPanel3;
	private JPanel jPanel2;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				TeacherManage inst = new TeacherManage(frame);
				inst.setVisible(true);
			}
		});
	}

	public TeacherManage(JFrame frame) {
		super(frame);
		initGUI();
		initData();
	}

	private void initGUI() {
		try
		{
			{
				this.setTitle("\u6559\u5e08\u7ba1\u7406");
			}
			{
				jPanel1 = new JPanel();
				BoxLayout jPanel1Layout = new BoxLayout(jPanel1, javax.swing.BoxLayout.X_AXIS);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				{
					jPanel2 = new JPanel();
					GridLayout jPanel2Layout = new GridLayout(1, 1);
					jPanel2Layout.setHgap(5);
					jPanel2Layout.setVgap(5);
					jPanel2Layout.setColumns(1);
					jPanel2.setLayout(jPanel2Layout);
					jPanel1.add(jPanel2);
					jPanel2.setPreferredSize(new java.awt.Dimension(576, 599));
					{
						jScrollPane1 = new JScrollPane();
						jPanel2.add(jScrollPane1);
						jScrollPane1.setPreferredSize(new java.awt.Dimension(492, 530));
						{
							TableModel teacherTableModel =
									new DefaultTableModel(
											null,
											new String[] { "序号", "姓名", "联系电话" });
							teacherTable = new JTable(teacherTableModel){
								public boolean isCellEditable(int row, int column) {
									return false;
								}
							};
							teacherTable.setRowHeight(20);
							teacherTable.setDefaultRenderer(Object.class, new TeacherRenderer()) ;

							jScrollPane1.setViewportView(teacherTable);
						
						}
					}
				}
				{
					jPanel3 = new JPanel();
					jPanel1.add(jPanel3);
					jPanel3.setBorder(BorderFactory.createTitledBorder(""));
					jPanel3.setPreferredSize(new java.awt.Dimension(165, 530));
					jPanel3.setLayout(null);
					{
						batchInput = new JButton();
						jPanel3.add(batchInput);
						batchInput.setText("\u6279\u91cf\u5bfc\u5165");
						batchInput.setBounds(24, 11, 99, 29);
						batchInput.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								batchInput();
							}
						});
					}
					{
						deleteTeacher = new JButton();
						jPanel3.add(deleteTeacher);
						deleteTeacher.setText("\u5220\u9664");
						deleteTeacher.setBounds(24, 156, 99, 29);
						deleteTeacher.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								int r = JOptionPane.showConfirmDialog(new ZephyrPntMainFrame(), "确定删除教师信息?",
										"删除确认", JOptionPane.YES_NO_CANCEL_OPTION);
								if (r == JOptionPane.YES_NO_OPTION) 
								{
									int selectedRow = teacherTable.getSelectedRow();
									TeacherDAO dao = new TeacherDAO();
									if(selectedRow < 0)
									{
										MessageWindow.show("请选择教师信息");
										return ;
									}
									Teacher teacher = new Teacher();
									teacher.setName(teacherTable.getValueAt(selectedRow, 1).toString());
									teacher.setPhone_number(teacherTable.getValueAt(selectedRow, 2).toString());
									List l   = dao.getByExample(Teacher.class, teacher);
									dao.delete((Teacher)l.get(0));
									initData();
								}
							}
							
						});
					}
					{
						newTeacher = new JButton();
						jPanel3.add(newTeacher);
						newTeacher.setText("\u65b0\u589e");
						newTeacher.setBounds(24, 83, 99, 29);
						newTeacher.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								ZephyrPntMainFrame frame = new ZephyrPntMainFrame();
								NewTeacher inst = new NewTeacher(frame);
								inst.setLocationRelativeTo(frame);
								inst.setVisible(true);
								
							}
							
						});
					}
					{
						editTeacher = new JButton();
						jPanel3.add(editTeacher);
						editTeacher.setText("\u7f16\u8f91");
						editTeacher.setBounds(24, 119, 99, 29);
						editTeacher.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								int selectedRow = teacherTable.getSelectedRow();
								TeacherDAO dao = new TeacherDAO();
								if(selectedRow < 0)
								{
									MessageWindow.show("请选择教师信息");
									return ;
								}
								Teacher teacher = new Teacher();
								teacher.setName(teacherTable.getValueAt(selectedRow, 1).toString());
								List l   = dao.getByExample(Teacher.class, teacher);
								ZephyrPntMainFrame frame = new ZephyrPntMainFrame();
								EditTeacher inst = new EditTeacher(frame,(Teacher)l.get(0));
								inst.setLocationRelativeTo(frame);
								inst.setVisible(true);
							}
							
						});
					}
					{
						flashTeacher = new JButton();
						jPanel3.add(flashTeacher);
						flashTeacher.setText("\u5237\u65b0");
						flashTeacher.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								initData();
							}
							
						});
						flashTeacher.setBounds(24, 47, 99, 29);
					}
				}
			}
			this.setSize(656, 575);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void batchInput(){
		String filePath = null;
		JFileChooser fileChoose = new JFileChooser();
		// excel file only .
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel 工作表", "xls",
				"xlsx");
		fileChoose.setFileFilter(filter);
		if(fileChoose.showOpenDialog(fileChoose) == 0)
		{
			filePath = fileChoose.getSelectedFile().getPath();
			Workbook workBook;
			try
			{
				workBook = new Workbook(filePath);
				Worksheet sheet = workBook.getWorksheets().get(0);
				Cells cells = sheet.getCells();
				Teacher teacher ;
				TeacherDAO dao = new TeacherDAO();
				for(int i = 1 ; i < cells.getMaxDataRow() + 1; i ++)
				{
					teacher = new Teacher();
					teacher.setName(cells.get(i, 0).getStringValue());
					teacher.setPhone_number(cells.get(i,1).getStringValue());
					teacher.setPassword("4297f44b13955235245b2497399d7a93");
					dao.saveORupdate(teacher);
				}
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			initData();
		}
	}

	static void initData() {
		BaseDAO dao = new BaseDAO();
		Teacher teacher = new Teacher();
		List l = dao.getObjList(Teacher.class);
		DefaultTableModel model = (DefaultTableModel) teacherTable.getModel();
		// frist clear table
		model.getDataVector().clear();
		model.fireTableDataChanged();
		if (!l.isEmpty())
		{
			for (int i = 0; i < l.size(); i++)
			{
				teacher = (Teacher) l.get(i);
				model.addRow(new Object[] {
						model.getRowCount() + 1,
						teacher.getName(),
						teacher.getPhone_number()
				});
			}
		}
	}

}

//内部类 ，各行改变JTable颜色
class TeacherRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
	          /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable t, Object value,
	                      boolean isSelected, boolean hasFocus, int row, int column) {
	             //分支判断条件可根据需要进行修改
	             if (row % 2 ==0){
	            	 setBackground(new Color(206,231,255)) ;
	             }else {
	            	 
	            	 setBackground(new Color(255,255,255)) ;
	             }
	             this.setHorizontalAlignment(CENTER);
	             return super.getTableCellRendererComponent(t, value, isSelected,
	                     hasFocus, row, column);
	         }
}