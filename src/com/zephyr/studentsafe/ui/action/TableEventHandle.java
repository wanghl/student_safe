package com.zephyr.studentsafe.ui.action;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.ui.MessageWindow;

/**监听处理表格数据改变事件
 * @author wanghongliang
 *
 */
public class TableEventHandle implements TableModelListener{
	BaseDAO dao = new BaseDAO();
	@Override
	public void tableChanged(TableModelEvent e) {
		
		// TODO Auto-generated method stub
		TableModel model = (TableModel) e.getSource();
		int row = e.getFirstRow();
		int column = e.getColumn();
		Studentrfid student = new Studentrfid();
		List list ;
		//新录入的卡号 检查数据库里有木有
		if (model.getColumnName(column).equals("卡号")){
			student.setRfidCardID(model.getValueAt(row, column).toString());
			if (!student.getRfidCardID().equals("")){
				
				list = dao.getByExample(Studentrfid.class, student);
				if ( ! list.isEmpty() ){
					MessageWindow.show("卡号 " + student.getRfidCardID() + " 已存在喔！ ^&^ \n" 
							+ "元芳，你怎么看？ \n 大人，你丫有麻烦了，找吧...."); 
					
				}
			}
		}
		

		
	}


}
