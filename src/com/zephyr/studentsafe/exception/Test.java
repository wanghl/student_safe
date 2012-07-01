package com.zephyr.studentsafe.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Test {

	public static void sendMai(String[] argvs) throws IOException {
		Email email = new SimpleEmail();
		email.setHostName("www.zephyr.com.cn");
		// email.setHostName("smtp.163.com");
		email.setSmtpPort(25);
		
		email.setAuthentication("wanghongliang", "zhaohong");
		email.setTLS(true);
		try {
			email.setFrom("wanghongliang@zephyr.com.cn");
			email.setCharset("GBK");
			email.setMsg("≤‚ ‘≤‚ ‘");
			email.addTo("wanghongliang@zephyr.com.cn");
			email.setSubject("TestMailŒƒ±æ” º˛");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public void tt() throws IOException {
		InputStream input = new FileInputStream("D:\\111\\22.xls");
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cel = null;
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(2);
		list.add(5);
		list.add(7);
		list.add(24);
		int x = sheet.getPhysicalNumberOfRows();
		int y;
		String s = "";
		for (int j = 1; j < x; j++) {
			row = sheet.getRow(j);
			y = row.getLastCellNum();
			for (int z = 0; z < y; z++) {
				cel = row.getCell(z);
				if (list.indexOf(z) >= 0) {
					s += " " + r(cel);
				}

			}
			s += "\n";
		}
		System.out.println(s);
	}

	public String r(HSSFCell cell) {
		StringBuffer bf = new StringBuffer();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			bf.append(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_STRING:
			bf.append(cell.getStringCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			bf.append(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			bf.append(cell.getCellFormula());
			break;
		default:
			bf.append("unsuported sell type");
			break;
		}
		return bf.toString();
	}
}
