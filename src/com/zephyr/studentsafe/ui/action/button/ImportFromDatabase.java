package com.zephyr.studentsafe.ui.action.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.zephyr.studentsafe.dao.ClassInfoDAO;
import com.zephyr.studentsafe.ui.action.ImportDataToJTableAction;
import com.zephyr.studentsafe.ui.dialog.StudentImportMainFrame;

public class ImportFromDatabase implements ActionListener {
	ClassInfoDAO dao = new ClassInfoDAO();

	@Override
	public void actionPerformed(ActionEvent e) {

		Object[] clasArray = dao.getClassName();
		List l = null;
		String className = (String) JOptionPane.showInputDialog(
				null,
				"请选择导入班级\n",
				"选择班级",
				JOptionPane.INFORMATION_MESSAGE,
				null,
				clasArray,
				null);
		if (className != null)
		{
			l = dao.getStudentByClassName(className);
			ImportDataToJTableAction.insertDataToTable(l, className);
		}

		// readButton enable .
		StudentImportMainFrame.setButtonEnable(true);
		StudentImportMainFrame.getTable().requestFocus() ;

	}

}
