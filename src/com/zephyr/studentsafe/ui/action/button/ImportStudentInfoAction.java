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
 * import student infomation from *.xls (excel ). 读excel ，把学生信息刷新到jtable 没用POI
 * ,用了商业的aspose.cell
 * 
 * @author wanghongliang
 * 
 */
public class ImportStudentInfoAction implements ActionListener {
	// 所有数据是否已经入库
	public static boolean insertDB;
	// 当前数据入库后是否已经导出XLS文件
	public static boolean exportXls;
	// 默认路径
	public static String default_open_path = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		// 校验数
		if (StudentImportMainFrame.getTable().getModel().getRowCount() > 0)
		{
			if (!insertDB)
			{
				MessageWindow.show("尚有未导入数据库中的学生信息");
				return;
			}
			if (!exportXls)
			{
				MessageWindow.show("导入新数据前，先将当前信息导出Excel");
				return;
			}
		}
		// TODO Auto-generated method stub
		String filePath = null;
		JFileChooser fileChoose = new JFileChooser();
		// excel file only .
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel 工作表", "xls",
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
