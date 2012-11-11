package com.zephyr.studentsafe.ui.dialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.dao.ClassInfoDAO;
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
public class ClassManage extends javax.swing.JDialog {
	private JPanel jPanel1;
	private JButton newClassButton;
	private static JTable classTable;
	private JScrollPane jScrollPane1;
	private JButton deleteClassButton;
	private JButton editClassButton;
	private JLabel jLabel1;
	private JPanel jPanel2;

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
				ClassManage inst = new ClassManage(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public ClassManage(JFrame frame) {
		super(frame);
		initGUI();
		initData();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS);
			getContentPane().setLayout(thisLayout);
			this.setTitle("\u73ed\u7ea7\u7ba1\u7406");
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/icons/log.gif")).getImage());

			{
				jPanel2 = new JPanel();
				GridLayout jPanel2Layout = new GridLayout(1, 1);
				jPanel2Layout.setHgap(5);
				jPanel2Layout.setVgap(5);
				jPanel2Layout.setColumns(1);
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2);
				jPanel2.setPreferredSize(new java.awt.Dimension(490, 545));
				{
					jScrollPane1 = new JScrollPane();
					jPanel2.add(jScrollPane1);
					{
						TableModel classTableModel = 
							new DefaultTableModel(
									null,
									new String[] { "序号", "班级名称","班主任" });
						classTable = new JTable(classTableModel){
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						classTable.setRowHeight(20);
						classTable.setDefaultRenderer(Object.class, new ClassRenderer());
						jScrollPane1.setViewportView(classTable);
						classTable.setModel(classTableModel);
					}
				}
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setPreferredSize(new java.awt.Dimension(162, 545));
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setPreferredSize(new java.awt.Dimension(104, 12));
				}
				{
					newClassButton = new JButton();
					jPanel1.add(newClassButton);
					newClassButton.setText("\u65b0\u589e");
					newClassButton.setPreferredSize(new java.awt.Dimension(119, 27));
					newClassButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							NewEditClassDialog inst = new NewEditClassDialog(null);
							inst.setLocationRelativeTo(null);
							inst.setVisible(true);
						}
					});
				}
				{
					editClassButton = new JButton();
					jPanel1.add(editClassButton);
					editClassButton.setText("\u7f16\u8f91");
					editClassButton.setPreferredSize(new java.awt.Dimension(119, 27));
					editClassButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							editClass();
						}
					});
				}
				{
					deleteClassButton = new JButton();
					jPanel1.add(deleteClassButton);
					deleteClassButton.setText("\u5220\u9664");
					deleteClassButton.setPreferredSize(new java.awt.Dimension(119, 27));
					deleteClassButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							int selectedRow = classTable.getSelectedRow();
							if(selectedRow <  0)
							{
								MessageWindow.show("请选择要删除的班级");
								return ;
							}
							int r = JOptionPane.showConfirmDialog(null,
							        "确定要删除该班级？", "提示...", JOptionPane.YES_NO_OPTION,
							        JOptionPane.QUESTION_MESSAGE );
							if(r == JOptionPane.YES_OPTION )
							{
								BaseDAO dao = new BaseDAO();
								ClassInfo classinfo = new ClassInfo();
								classinfo.setClassName(classTable.getValueAt(selectedRow, 1).toString());
								classinfo = (ClassInfo) dao.getByExample(ClassInfo.class, classinfo).get(0);
								dao.delete(classinfo);
								initData();
							}
						}
					});
				}
			}
			this.setSize(673, 590);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editClass(){
		ClassInfoDAO dao = new ClassInfoDAO();
		int selectedRow = classTable.getSelectedRow();
		ClassInfo classinfo = new ClassInfo();
		classinfo.setClassName(classTable.getValueAt(selectedRow, 1).toString());
		classinfo = (ClassInfo) dao.getByExample(ClassInfo.class, classinfo).get(0);
		NewEditClassDialog inst = new NewEditClassDialog(null,classinfo);
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
	}
	
	public static void initData(){
		DefaultTableModel model = (DefaultTableModel) classTable.getModel();
		model.getDataVector().clear();
		model.fireTableDataChanged();
		ClassInfoDAO dao = new ClassInfoDAO();
		List<Object[]>  classlist = dao.getClassInfo();
		for(Object[] obj : classlist){
			
			model.addRow(new Object[]{
					model.getRowCount() + 1,
					obj[0],
					obj[1]
			});
		}
	}

}

//内部类 ，各行改变JTable颜色
class ClassRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
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
