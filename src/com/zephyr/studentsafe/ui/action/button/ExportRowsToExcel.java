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
		HSSFSheet sheet = wb.createSheet("ƽ��ͨ-ѧ����Ϣ");
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 4000);

		// ����������ʽ

		HSSFFont font = wb.createFont();

		font.setFontName("����");
		font.setBoldweight((short) 100);
		font.setFontHeight((short) 200);


		// ������Ԫ����ʽ

		HSSFCellStyle style = wb.createCellStyle();

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// ���ñ߿�

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setFont(font);// ��������
		
		HSSFRow row = sheet.createRow(0);
		
		HSSFCell  cell = row.createCell(0) ;
		cell.setCellStyle(style);
		cell.setCellValue("���");
		
		cell = row.createCell(1) ;
		cell.setCellStyle(style);
		cell.setCellValue("�༶");
		
		cell = row.createCell(2) ;
		cell.setCellStyle(style);
		cell.setCellValue("����");
		
		cell = row.createCell(3) ;
		cell.setCellStyle(style);
		cell.setCellValue("����");
		
		cell = row.createCell(4) ;
		cell.setCellStyle(style);
		cell.setCellValue("�ҳ�����");
		
		Cell cell0 = row.createCell(5) ;
		cell0.setCellStyle(style);
		cell0.setCellValue("��ϵ�绰");
		
		
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
			// û�������ÿ����
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
		cell.setCellValue("���䷢��Ϣ�� " + count + " ��");
		//
		int index = model.getRowCount() + 3;
		row = sheet.createRow(model.getRowCount() + 3) ;
		cell = row.createCell(0);
		//�ϲ���Ԫ��
		sheet.addMergedRegion(new CellRangeAddress(index,index,index,index + 3));
		
		cell.setCellValue("��ע��δ�ṩ�ҳ��ֻ������ѧ���ݲ��䷢��Ϣ����");
		String filePath = null;
		JFileChooser fileChoose = new JFileChooser();
		// excel file only .
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Microsoft Excel ������", "xls", "xlsx");
		fileChoose.setFileFilter(filter);
		// set default fileName .
		String fileName = model.getValueAt(0, 1).toString() + "_" + model.getRowCount() + "��" + "_" + count + "�ſ�";
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
				MessageWindow.show("�����ļ�ʧ��:" + e1.getLocalizedMessage());
				return ;
			}
			MessageWindow.show("�ɹ�������Excel�ļ�");
			ImportStudentInfoAction.exportXls = true ;
		}


	}

}
