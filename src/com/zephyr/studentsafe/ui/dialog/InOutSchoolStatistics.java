package com.zephyr.studentsafe.ui.dialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.dao.StudentDAO;
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
public class InOutSchoolStatistics extends javax.swing.JDialog {
	private JScrollPane jScrollPane1;
	private JButton queryButton;
	private JComboBox eventInput;
	private JLabel jLabel2;
	private JTextField dataInput;
	private JLabel jLabel1;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private JTable classList;

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
				InOutSchoolStatistics inst = new InOutSchoolStatistics(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public InOutSchoolStatistics(JFrame frame) {
		super(frame);
		initGUI();
	}
	
	private void initGUI() {
		try {
			BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS);
			getContentPane().setLayout(thisLayout);
			this.setTitle("\u8fdb\u51fa\u6821\u68c0\u6d4b\u7edf\u8ba1");
			this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/icons/log.gif")).getImage());

			{
				jPanel2 = new JPanel();
				getContentPane().add(jPanel2);
				jPanel2.setPreferredSize(new java.awt.Dimension(643, 56));
				jPanel2.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanel2.add(jLabel1);
					jLabel1.setText("\u65e5\u671f");
					jLabel1.setBounds(14, 15, 30, 18);
				}
				{
					dataInput = new JTextField();
					jPanel2.add(dataInput);
					dataInput.setBounds(53, 9, 129, 24);
					dataInput.setText(StudentSafeUtil.getCurrentDate());
				}
				{
					jLabel2 = new JLabel();
					jPanel2.add(jLabel2);
					jLabel2.setText("\u65f6\u6bb5");
					jLabel2.setBounds(193, 12, 30, 21);
				}
				{
					ComboBoxModel eventInputModel = 
						new DefaultComboBoxModel(
								new String[] { "上午上学", "下午放学" });
					eventInput = new JComboBox();
					jPanel2.add(eventInput);
					eventInput.setModel(eventInputModel);
					eventInput.setBounds(231, 9, 132, 24);
				}
				{
					queryButton = new JButton();
					jPanel2.add(queryButton);
					queryButton.setText("\u67e5\u8be2");
					queryButton.setBounds(371, 9, 99, 24);
					queryButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							queryData();
						}
						
					});
				}
			}
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
						TableModel classListModel = 
							new DefaultTableModel(
									null ,
									new String[] { "序号", "班级","班级人数","发卡人数","实测人数","比例" });
						classList = new JTable(classListModel ){
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						classList.addMouseListener(new MouseListener(){

							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								if(e.getClickCount() == 2)
								{
									int selectedRow  = classList.getSelectedRow();
									String className = classList.getValueAt(selectedRow, 1).toString();
									String date = dataInput.getText();
									int flag = 0 ;
									if (eventInput.getSelectedIndex() == 0)
									{
										flag = 1;
									}else if (eventInput.getSelectedIndex() == 1)
									{
										flag = 3 ;
									}
									StudentDAO dao  =new StudentDAO();
									Map<String,List> map = dao.countUnCheckStudent(className, date, flag);
									UNCheckStudentList inst = new UNCheckStudentList(null,map);
									inst.setLocationRelativeTo(null);
									inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									inst.setVisible(true);
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
							
						}) ;
						//行高
						classList.setRowHeight(20);
						classList.setDefaultRenderer(Object.class, new ClassListRenderer());
						jScrollPane1.setViewportView(classList);
					}
				}
			}
			this.setSize(661, 479);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void queryData(){
		StudentDAO dao = new StudentDAO();
		int flag = eventInput.getSelectedIndex();
		if(flag == 0){
			flag = 1; //查询早上上学
		}else {
			flag = 3 ;
		}
		Map<ClassInfo,Object[]> map = dao.studentInOutSchoolStatistics(dataInput.getText(), flag);
		DefaultTableModel model = (DefaultTableModel) classList.getModel();
		ClassInfo classInfo ;
		// frist clear table
		model.getDataVector().clear();
		model.fireTableDataChanged();
		for(Iterator<ClassInfo> it = map.keySet().iterator(); it.hasNext();)
		{
			classInfo = it.next();
			model.addRow(new Object[]{
				model.getRowCount() + 1 ,classInfo.getClassName(),
				map.get(classInfo)[0],map.get(classInfo)[1],map.get(classInfo)[2],
				count(map.get(classInfo)[1].toString(),map.get(classInfo)[2].toString())
			});
		}
	}
	
	private String count(String allStudent, String checkStudent){
		double checkcount = Integer.parseInt(checkStudent) * 1.0 ;
		double allcount =  Integer.parseInt(allStudent) * 1.0 ;
		double result = checkcount / allcount ;
		DecimalFormat df1 = new DecimalFormat("##.00%"); 
		return df1.format(result);
	}

}
//内部类 ，各行改变JTable颜色
class ClassListRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
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