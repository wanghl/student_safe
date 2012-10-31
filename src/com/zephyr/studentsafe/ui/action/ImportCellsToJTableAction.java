package com.zephyr.studentsafe.ui.action;

import java.awt.Rectangle;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.zephyr.studentsafe.ui.action.button.ImportStudentInfoAction;
import com.zephyr.studentsafe.ui.dialog.StudentImportMainFrame;

public class ImportCellsToJTableAction {
	
	public static int CURSOR_INDEX = 0 ;
	public static boolean MAX_INDEX ;
	
	
	/**
	 * insert cell to jtable 
	 * @param cell
	 */
	public static void insertCellToTable(final Cells cells){
		
		final DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame.getTable().getModel();
		//frist clear table 
		model.getDataVector().clear();
		model.fireTableDataChanged();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// getMaxDataRow() 返回有效数据行数 getMaxDataColumn返回有效数据列数
				for ( int i = 1 ; i < cells.getMaxDataRow() + 1 ; i  ++  )
				{
						model.addRow(new Object[]{ model.getRowCount() + 1 ,
							cells.get(i,0).getStringValue(),cells.get(i,1).getStringValue(),"" ,"",
							cells.get(i,2).getStringValue(),cells.get(i,3).getStringValue(),cells.get(i,4).getStringValue(),
							cells.get(i,5).getStringValue(),cells.get(i,6).getStringValue(),cells.get(i,7).getStringValue(),
							cells.get(i,8).getStringValue(),cells.get(i,9).getStringValue(),""
						});
				}

				StudentImportMainFrame.getTable().repaint();
				StudentImportMainFrame.getTable().updateUI();
				//统计导入学生信息条数
				StudentImportMainFrame.setImportCount(StudentImportMainFrame.getImportCount() + model.getRowCount()) ;

			}
		});
		CURSOR_INDEX = 0 ;
		MAX_INDEX = false;
		ImportStudentInfoAction.insertDB = false ;
		ImportStudentInfoAction.exportXls = false ;
	}
	
	/**
	 * 设置一个游标CURSOR_INDEX 初始值为0 ；
	 * 读到卡号后，从第一行开始插入，到最后一行后游标归零
	 * @param rfid
	 * @param m1
	 */
	public static  void insertCardNumber(final String rfid ,final String m1){
		
		final DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame.getTable().getModel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if ( MAX_INDEX ){
					for ( int i = 0 ; i < model.getRowCount(); i ++ )
					{
						if ( model.getValueAt(i, 3).equals("") ||
							 model.getValueAt(i, 4).equals("") )
						{
							//家长电话为空的，暂不发卡
							if(model.getValueAt(i, 12).equals("")){
								
								model.setValueAt("", i, 3); //rfid card number
								model.setValueAt("", i, 4); // m1 card number
								
							}else {
								
								model.setValueAt(rfid, i, 3); //rfid card number
								model.setValueAt(m1, i, 4); // m1 card number
								selectRow(i);
							}
						}
					}
				}else {
					while (true){
					if(!model.getValueAt(CURSOR_INDEX, 12).equals("")){
						
						model.setValueAt(rfid, CURSOR_INDEX, 3); //rfid card number
						model.setValueAt(m1, CURSOR_INDEX, 4); // m1 card number
						selectRow(CURSOR_INDEX);	
						countRfid();
						CURSOR_INDEX ++ ;
						break;
					}else {
						if ( ++CURSOR_INDEX == model.getRowCount() ){
							break ;
						}
						if (model.getValueAt(CURSOR_INDEX,12).equals("")) continue ;
						if ((CURSOR_INDEX ) < model.getRowCount()){

							model.setValueAt(rfid, CURSOR_INDEX, 3); //rfid card number
							model.setValueAt(m1, CURSOR_INDEX, 4); // m1 card number
							selectRow(CURSOR_INDEX);	
							
							break ;
						}
					}
					}
				}
				if ( CURSOR_INDEX >= model.getRowCount() ){
					CURSOR_INDEX = 0 ;
					MAX_INDEX = true ;
				}
				StudentImportMainFrame.getTable().repaint();
				StudentImportMainFrame.getTable().updateUI();
			}
		});
		
	}
	
	//统计发卡数量
	private static void countRfid(){
		StudentImportMainFrame.setCardCount(StudentImportMainFrame.getCardCount() + 1);
	}
	
	public static void updateTable(final int row ,final int column,final String content ){
		
		DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame.getTable().getModel();
		//frist clear table 
		
				model.setValueAt(content, row , column) ;
		
	}
	
	private static void selectRow(int index){
		StudentImportMainFrame.getTable().getSelectionModel().setSelectionInterval(index, index);
		Rectangle reg = StudentImportMainFrame.getTable().getCellRect(index, 1, true);
		StudentImportMainFrame.getTable().scrollRectToVisible(reg);

	}

}
