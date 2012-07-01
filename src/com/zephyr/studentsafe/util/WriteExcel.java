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
		// ����HSSFWorkbook����
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle headerStyle = wb.createCellStyle();
		// ���þ���
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = wb.createFont();
		// ����Ӵ�
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setFont(font);
		// �߿�
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿�
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿�
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
		// ������ͷ
		
		HSSFCellStyle cellstyle = wb.createCellStyle();
		cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // �±߿�
		cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// ��߿�
		cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// �ϱ߿�
		cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// �ұ߿�
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// ����HSSFSheet����
		HSSFSheet sheet = wb.createSheet("s1");

		sheet.setColumnWidth(0, 32 * 120);// ��A�����ÿ��Ϊ120����
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
		classHeader.setCellValue("�༶");
		classHeader.setCellStyle(headerStyle);
		HSSFCell nameHeader = headerRow.createCell(1);
		nameHeader.setCellValue("����");
		nameHeader.setCellStyle(headerStyle);
		HSSFCell countHeader = headerRow.createCell(2);
		countHeader.setCellValue("δ��������");
		countHeader.setCellStyle(headerStyle);
		HSSFCell kfHeader = headerRow.createCell(3);
		kfHeader.setCellValue("�۷�");
		kfHeader.setCellStyle(headerStyle);
		//
		List<List> l = makedate();
		if (l.size() > 17) {
		HSSFCell classHeader2 = headerRow.createCell(4);
		classHeader2.setCellValue("�༶");
		classHeader2.setCellStyle(headerStyle);
		HSSFCell nameHeader2 = headerRow.createCell(5);
		nameHeader2.setCellValue("����");
		nameHeader2.setCellStyle(headerStyle);
		HSSFCell countHeader2 = headerRow.createCell(6);
		countHeader2.setCellValue("δ��������");
		countHeader2.setCellStyle(headerStyle);
		HSSFCell kfHeader2 = headerRow.createCell(7);
		kfHeader2.setCellValue("�۷�");
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
		// ��������Ҫ�������
		OutputStream out = new FileOutputStream("viwo.xls");
		// ���Excel
		wb.write(out);
		out.flush();
	}
	
	public static List<List> makedate(){
		List<List> mainlist = new ArrayList<List>();
		List sublist = new ArrayList() ;
		Student s = new Student();
		s.setClassname("һ�꼶1��");
		s.setName("���ļ�");
		Student s1 = new Student();
		s1.setClassname("һ�꼶1��");
		s1.setName("���ļ�");
		Student s2 = new Student();
		s2.setClassname("һ�꼶1��");
		s2.setName("���ļ�");
		Student s3 = new Student();
		s3.setClassname("һ�꼶1��");
		s3.setName("���ļ�");
		Student s4 = new Student();
		s4.setClassname("һ�꼶1��");
		s4.setName("���ļ�");
		Student s5 = new Student();
		s5.setClassname("һ�꼶1��");
		s5.setName("���ļ�");
		
		sublist.add(s);
		sublist.add(s1);
		sublist.add(s2);
		sublist.add(s3);
		sublist.add(s4);
		sublist.add(s5);
		
		Student ss = new Student();
		ss.setClassname("���꼶1��");
		ss.setName("��Լ");
		Student ss1 = new Student();
		ss1.setClassname("���꼶1��");
		ss1.setName("��Լ");
		Student ss2 = new Student();
		ss2.setClassname("���꼶1��");
		ss2.setName("��Լ");
		Student ss3 = new Student();
		ss3.setClassname("���꼶1��");
		ss3.setName("��Լ");
		Student ss4 = new Student();
		ss4.setClassname("���꼶1��");
		ss4.setName("��Լ");
		Student ss5 = new Student();
		ss5.setClassname("���꼶1��");
		ss5.setName("��Լ");
		
		List as = new ArrayList();
		as.add(ss);
		as.add(ss1);
		as.add(ss2);
		as.add(ss3);
		as.add(ss4);
		as.add(ss5);
		
		Student ta = new Student();
		ta.setClassname("���꼶1��");
		ta.setName("�߻�ѧ");
		Student ta1 = new Student();
		ta1.setClassname("���꼶1��");
		ta1.setName("�߻�ѧ");
		Student ta2 = new Student();
		ta2.setClassname("���꼶1��");
		ta2.setName("�߻�ѧ");
		Student ta3 = new Student();
		ta3.setClassname("���꼶1��");
		ta3.setName("�߻�ѧ");
		Student ta4 = new Student();
		ta4.setClassname("���꼶1��");
		ta4.setName("�߻�ѧ");
		Student ta5 = new Student();
		ta5.setClassname("���꼶1��");
		ta5.setName("�߻�ѧ");
		Student ta6 = new Student();
		ta6.setClassname("���꼶1��");
		ta6.setName("�߻�ѧ");
		Student ta7 = new Student();
		ta7.setClassname("���꼶1��");
		ta7.setName("�߻�ѧ");
		Student ta8 = new Student();
		ta8.setClassname("���꼶1��");
		ta8.setName("�߻�ѧ");
		Student ta9 = new Student();
		ta9.setClassname("���꼶1��");
		ta9.setName("�߻�ѧ");
		Student ta11 = new Student();
		ta11.setClassname("���꼶1��");
		ta11.setName("�߻�ѧ");
		
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


