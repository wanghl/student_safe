package com.zephyr.studentsafe.ui.dialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

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
public class UNCheckStudentList extends javax.swing.JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JTable studentTable;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				UNCheckStudentList inst = new UNCheckStudentList(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public UNCheckStudentList(JFrame frame,Map<String ,List> map) {
		super(frame);
		initGUI();
		initData(map);
	}
	
	public UNCheckStudentList(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(1, 1);
			thisLayout.setHgap(5);
			thisLayout.setVgap(5);
			thisLayout.setColumns(1);
			getContentPane().setLayout(thisLayout);
			this.setTitle("\u672a\u68c0\u6d4b\u5230\u5b66\u751f\u7edf\u8ba1");
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/icons/log.gif")).getImage());

			{
				jPanel1 = new JPanel();
				GridLayout jPanel1Layout = new GridLayout(1, 1);
				jPanel1Layout.setHgap(5);
				jPanel1Layout.setVgap(5);
				jPanel1Layout.setColumns(1);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1);
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1);
					{
						TableModel studentTableModel = 
							new DefaultTableModel(
									null ,
									new String[] { "序号", "学生姓名","卡号","班级","最后检测时间","事件" });
						studentTable = new JTable();
						jScrollPane1.setViewportView(studentTable);
						studentTable.setModel(studentTableModel);
						studentTable.setRowHeight(20);
						studentTable.setDefaultRenderer(Object.class, new UNCheckRenderer());

					}
				}
			}
			this.setSize(635, 431);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initData(final Map<String,List> map){
		
		final DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				List<Object[]> list = map.get("uncheck");
				for(Object[] obj : list)
				{
					model.addRow(new Object[]{
						model.getRowCount() + 1, 
						obj[0],obj[1],obj[2],obj[3],obj[4]
					});
				}
				
				list = map.get("less");
				for(Object[] obj : list)
				{
					model.addRow(new Object[]{
							model.getRowCount() + 1, 
							obj[0],obj[1],obj[2],obj[3],obj[4]
					});
				}
				

			}
		});
		
	}

}

//内部类 ，各行改变JTable颜色
class UNCheckRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
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
