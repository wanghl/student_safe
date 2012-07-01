package com.zephyr.studentsafe.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.zephyr.studentsafe.bo.StudentExt;

public class ReaderDebugTEST {
	@Test
	public void testgetReaderInfo(){
		ReaderDebugDAO dao = new ReaderDebugDAO();
		Map<String, List> map = dao.getReaderInfo("654462");
		assertTrue(!map.get("ReaderA").isEmpty());
		assertTrue(!map.get("ReaderB").isEmpty());
	}

}
