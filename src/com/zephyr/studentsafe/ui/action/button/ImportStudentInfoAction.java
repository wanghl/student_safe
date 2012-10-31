package com.zephyr.studentsafe.ui.action.button;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.ui.action.ImportDataToJTableAction;
import com.zephyr.studentsafe.ui.dialog.StudentImportMainFrame;

/**
 * import student infomation from *.xls (excel ). ��excel ����ѧ����Ϣˢ�µ�jtable û��POI
 * ,������ҵ��aspose.cell
 * 
 * @author wanghongliang
 * 
 */
public class ImportStudentInfoAction implements ActionListener {
	// ���������Ƿ��Ѿ����
	public static boolean insertDB;
	// ��ǰ���������Ƿ��Ѿ�����XLS�ļ�
	public static boolean exportXls;
	// Ĭ��·��
	public static String default_open_path = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		// У����
		if (StudentImportMainFrame.getTable().getModel().getRowCount() > 0)
		{
			if (!insertDB)
			{
				MessageWindow.show("����δ�������ݿ��е�ѧ����Ϣ");
				return;
			}
			if (!exportXls)
			{
				MessageWindow.show("����������ǰ���Ƚ���ǰ��Ϣ����Excel");
				return;
			}
		}
		// TODO Auto-generated method stub
		String filePath = null;
		JFileChooser fileChoose = new JFileChooser();
		// excel file only .
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel ������", "xls",
				"xlsx");
		fileChoose.setFileFilter(filter);
		// set default open path
		if (default_open_path != null)
		{
			fileChoose.setCurrentDirectory(new File(default_open_path));
		}
		if (fileChoose.showOpenDialog(fileChoose) == 0)
		{
			filePath = fileChoose.getSelectedFile().getPath();
			default_open_path = filePath.substring(0, filePath.lastIndexOf("\\"));
		}

		if (filePath != null)
		{
			try
			{
				Workbook workBook = new Workbook(filePath);
				Worksheet sheet = workBook.getWorksheets().get(0);
				Cells cells = sheet.getCells();
				ImportDataToJTableAction.insertCellsToTable(cells);
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// readButton enable .
		StudentImportMainFrame.setButtonEnable(true);
	}

}
