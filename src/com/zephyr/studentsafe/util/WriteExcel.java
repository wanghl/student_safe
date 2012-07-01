package com.zephyr.studentsafe.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class WriteExcel {

	public static void main(String[] argvs) throws IOException {
		// 创建HSSFWorkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle headerStyle = wb.createCellStyle();
		// 设置居中
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		// 字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setFont(font);
		// 边框
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		// 创建表头
		
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 创建HSSFSheet对象
		HSSFSheet sheet = wb.createSheet("s1");

		sheet.setColumnWidth(0, 32 * 120);// 对A列设置宽度为120像素
		sheet.setColumnWidth(1, 32 * 120);
		sheet.setColumnWidth(2, 32 * 120);
		sheet.setColumnWidth(3, 32 * 120);
		sheet.setColumnWidth(4, 32 * 120);
		sheet.setColumnWidth(5, 32 * 120);
		sheet.setColumnWidth(6, 32 * 120);
		sheet.setColumnWidth(7, 32 * 120);
		
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.setHeightInPoints(25f);
		HSSFCell classHeader = headerRow.createCell(0);
		classHeader.setCellValue("班级");
		classHeader.setCellStyle(headerStyle);
		HSSFCell nameHeader = headerRow.createCell(1);
		nameHeader.setCellValue("姓名");
		nameHeader.setCellStyle(headerStyle);
		HSSFCell countHeader = headerRow.createCell(2);
		countHeader.setCellValue("未带卡人数");
		countHeader.setCellStyle(headerStyle);
		HSSFCell kfHeader = headerRow.createCell(3);
		kfHeader.setCellValue("扣分");
		kfHeader.setCellStyle(headerStyle);
		//
		List<List> l = makedate();
		if (l.size() > 17) {
		HSSFCell classHeader2 = headerRow.createCell(4);
		classHeader2.setCellValue("班级");
		classHeader2.setCellStyle(headerStyle);
		HSSFCell nameHeader2 = headerRow.createCell(5);
		nameHeader2.setCellValue("姓名");
		nameHeader2.setCellStyle(headerStyle);
		HSSFCell countHeader2 = headerRow.createCell(6);
		countHeader2.setCellValue("未带卡人数");
		countHeader2.setCellStyle(headerStyle);
		HSSFCell kfHeader2 = headerRow.createCell(7);
		kfHeader2.setCellValue("扣分");
		kfHeader2.setCellStyle(headerStyle);
		}
		int y = 0 ;
		for (int i = 0 ; i< l.size() ; i++ ){
			List<Student> t = l.get(i);
			for (Student s : t ){
				HSSFRow row = sheet.createRow(y + 1);
				
				row.setHeightInPoints(20f);
				HSSFCell classcell = row.createCell(0);
				classcell.setCellValue(s.getClassname());
				classcell.setCellStyle(cellstyle);
				HSSFCell namecell = row.createCell(1);
				namecell.setCellValue(s.getName());
				namecell.setCellStyle(cellstyle);
				HSSFCell coutcell = row.createCell(2);
				coutcell.setCellValue(t.size());
				coutcell.setCellStyle(cellstyle);
				HSSFCell kfcell = row.createCell(3);
				kfcell.setCellValue(t.size());
				kfcell.setCellStyle(cellstyle);
				y ++ ;
			}
			System.out.println(sheet.getLastRowNum());
			System.out.println(y);
		    sheet.addMergedRegion(new CellRangeAddress((sheet.getLastRowNum() +1)-t.size(), y, 2, 2));  
		    sheet.addMergedRegion(new CellRangeAddress((sheet.getLastRowNum() +1)-t.size(), y, 3, 3));  
		   // sheet.getRow(2).getCell(1).setCellStyle(cellstyle) ;

		}
		// 定义你需要的输出流
		OutputStream out = new FileOutputStream("viwo.xls");
		// 输出Excel
		wb.write(out);
		out.flush();
	}
	
	public static List<List> makedate(){
		List<List> mainlist = new ArrayList<List>();
		List sublist = new ArrayList() ;
		Student s = new Student();
		s.setClassname("一年级1班");
		s.setName("郭文佳");
		Student s1 = new Student();
		s1.setClassname("一年级1班");
		s1.setName("郭文佳");
		Student s2 = new Student();
		s2.setClassname("一年级1班");
		s2.setName("郭文佳");
		Student s3 = new Student();
		s3.setClassname("一年级1班");
		s3.setName("郭文佳");
		Student s4 = new Student();
		s4.setClassname("一年级1班");
		s4.setName("郭文佳");
		Student s5 = new Student();
		s5.setClassname("一年级1班");
		s5.setName("郭文佳");
		
		sublist.add(s);
		sublist.add(s1);
		sublist.add(s2);
		sublist.add(s3);
		sublist.add(s4);
		sublist.add(s5);
		
		Student ss = new Student();
		ss.setClassname("二年级1班");
		ss.setName("曹约");
		Student ss1 = new Student();
		ss1.setClassname("二年级1班");
		ss1.setName("曹约");
		Student ss2 = new Student();
		ss2.setClassname("二年级1班");
		ss2.setName("曹约");
		Student ss3 = new Student();
		ss3.setClassname("二年级1班");
		ss3.setName("曹约");
		Student ss4 = new Student();
		ss4.setClassname("二年级1班");
		ss4.setName("曹约");
		Student ss5 = new Student();
		ss5.setClassname("二年级1班");
		ss5.setName("曹约");
		
		List as = new ArrayList();
		as.add(ss);
		as.add(ss1);
		as.add(ss2);
		as.add(ss3);
		as.add(ss4);
		as.add(ss5);
		
		Student ta = new Student();
		ta.setClassname("三年级1班");
		ta.setName("高慧学");
		Student ta1 = new Student();
		ta1.setClassname("三年级1班");
		ta1.setName("高慧学");
		Student ta2 = new Student();
		ta2.setClassname("三年级1班");
		ta2.setName("高慧学");
		Student ta3 = new Student();
		ta3.setClassname("三年级1班");
		ta3.setName("高慧学");
		Student ta4 = new Student();
		ta4.setClassname("三年级1班");
		ta4.setName("高慧学");
		Student ta5 = new Student();
		ta5.setClassname("三年级1班");
		ta5.setName("高慧学");
		Student ta6 = new Student();
		ta6.setClassname("三年级1班");
		ta6.setName("高慧学");
		Student ta7 = new Student();
		ta7.setClassname("三年级1班");
		ta7.setName("高慧学");
		Student ta8 = new Student();
		ta8.setClassname("三年级1班");
		ta8.setName("高慧学");
		Student ta9 = new Student();
		ta9.setClassname("三年级1班");
		ta9.setName("高慧学");
		Student ta11 = new Student();
		ta11.setClassname("三年级1班");
		ta11.setName("高慧学");
		
		List l = new ArrayList();
		l.add(ta);
		l.add(ta1);
		l.add(ta2);
		l.add(ta3);
		l.add(ta4);
		l.add(ta5);
		l.add(ta6);
		l.add(ta7);
		l.add(ta8);
		l.add(ta9);
		l.add(ta11);
		mainlist.add(l);
		mainlist.add(sublist);
		mainlist.add(as);
		return mainlist ;
		
		
	}
	
	public static class Student {
		
		String classname;
		String name ;
		int count ;
		public String getClassname() {
			return classname;
		}
		public void setClassname(String classname) {
			this.classname = classname;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
		
		
	}
	

}


