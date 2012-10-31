package com.zephyr.studentsafe.ui.action.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;

import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.ui.dialog.StudentImportMainFrame;

/**
 * save jtable rows to excel file .
 * 
 * @author wanghongliang
 * 
 */
public class ExportRowsToExcel implements ActionListener {
	private static final Logger log = Logger.getLogger(ExportRowsToExcel.class);
	private static String default_save_path = null ;
	@Override
	public void actionPerformed(ActionEvent e) {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("平安通-学生信息");
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 4000);

		// 创建字体样式

		HSSFFont font = wb.createFont();

		font.setFontName("宋体");
		font.setBoldweight((short) 100);
		font.setFontHeight((short) 200);


		// 创建单元格样式

		HSSFCellStyle style = wb.createCellStyle();

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 设置边框

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setFont(font);// 设置字体
		
		HSSFRow row = sheet.createRow(0);
		
		HSSFCell  cell = row.createCell(0) ;
		cell.setCellStyle(style);
		cell.setCellValue("序号");
		
		cell = row.createCell(1) ;
		cell.setCellStyle(style);
		cell.setCellValue("班级");
		
		cell = row.createCell(2) ;
		cell.setCellStyle(style);
		cell.setCellValue("姓名");
		
		cell = row.createCell(3) ;
		cell.setCellStyle(style);
		cell.setCellValue("卡号");
		
		cell = row.createCell(4) ;
		cell.setCellStyle(style);
		cell.setCellValue("家长姓名");
		
		Cell cell0 = row.createCell(5) ;
		cell0.setCellStyle(style);
		cell0.setCellValue("联系电话");
		
		
		int count = 0 ;
		DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame.getTable().getModel();
		//set class info 
		boolean flag = false; ;
		for (int i = 0; i < model.getRowCount(); i++)
		{
			row = sheet.createRow(i + 1);
			
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue(model.getValueAt(i, 0).toString());
			
			if( !flag)
			{
				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(model.getValueAt(0, 1).toString());
				flag = true ;
			}else 
			{
				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue("");
			}
			// 没有数据用空填充
				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue(model.getValueAt(i, 2).toString()) ;
			//
			  if (!model.getValueAt(i, 3).toString().equals(""))
			  {
				  cell = row.createCell(3);
				  cell.setCellStyle(style);
				  cell.setCellValue(model.getValueAt(i, 3).toString()) ;
				  count ++ ;
			  }else {
				  cell = row.createCell(3);
				  cell.setCellStyle(style);
				  cell.setCellValue("") ;
			  }
			  	cell = row.createCell(4);
				cell.setCellStyle(style);
				cell.setCellValue(model.getValueAt(i, 9).toString()) ;
				
				cell = row.createCell(5);
				cell.setCellStyle(style);
				cell.setCellValue(model.getValueAt(i, 12).toString()) ;
			
				
				
		}
		int id = model.getRowCount() + 2;
		row = sheet.createRow(model.getRowCount()  + 2);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(id,id,id,id + 2));
		cell.setCellValue("共配发信息卡 " + count + " 张");
		//
		int index = model.getRowCount() + 3;
		row = sheet.createRow(model.getRowCount() + 3) ;
		cell = row.createCell(0);
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(index,index,index,index + 3));
		
		cell.setCellValue("备注：未提供家长手机号码的学生暂不配发信息卡。");
		String filePath = null;
		JFileChooser fileChoose = new JFileChooser();
		// excel file only .
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Microsoft Excel 工作表", "xls", "xlsx");
		fileChoose.setFileFilter(filter);
		// set default fileName .
		String fileName = model.getValueAt(0, 1).toString() + "_" + model.getRowCount() + "人" + "_" + count + "张卡";
		fileChoose.setSelectedFile( new File(fileName) );
		// set default path
		if (default_save_path != null ){
			fileChoose.setCurrentDirectory(new File(default_save_path));
		}
		if ( fileChoose.showSaveDialog(fileChoose) == 0) {
			filePath = fileChoose.getSelectedFile().getPath();
			default_save_path = filePath.substring(0,filePath.lastIndexOf("\\"));
			if (filePath.indexOf(".") < 0)
				filePath += ".xls";
			try {
				FileOutputStream o = new FileOutputStream(filePath);
				wb.write(o);
				o.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				log.error(e1);
				MessageWindow.show("导出文件失败:" + e1.getLocalizedMessage());
				return ;
			}
			MessageWindow.show("成功导出到Excel文件");
			ImportStudentInfoAction.exportXls = true ;
		}


	}

}
