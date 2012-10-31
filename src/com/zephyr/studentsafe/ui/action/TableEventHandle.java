package com.zephyr.studentsafe.ui.action;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.ui.MessageWindow;

/**�������������ݸı��¼�
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
		//��¼��Ŀ��� ������ݿ�����ľ��
		if (model.getColumnName(column).equals("����")){
			student.setRfidCardID(model.getValueAt(row, column).toString());
			if (!student.getRfidCardID().equals("")){
				
				list = dao.getByExample(Studentrfid.class, student);
				if ( ! list.isEmpty() ){
					MessageWindow.show("���� " + student.getRfidCardID() + " �Ѵ���ร� ^&^ \n" 
							+ "Ԫ��������ô���� \n ���ˣ���Ѿ���鷳�ˣ��Ұ�...."); 
					
				}
			}
		}
		

		
	}


}
