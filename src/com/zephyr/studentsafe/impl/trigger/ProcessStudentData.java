/**
 *<p>Title: ProcessData.java</p>
 *<p>Copyright (c) 2012 �����Ϸ�Ƽ��������޹�˾.Co.Ltd. All rights reserved.</p>
 *@author wanghongliang
 *@data 2012-7-2
 */
package com.zephyr.studentsafe.impl.trigger;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.util.StudentSafeUtil;

/**
 *<p>
 * ClassName:ProcessData
 * </p>
 *<p>
 * Description: ������յ�������
 * </p>
 * 
 * @author wanghongliang xbwolf@sina.cn
 *@date 2012-7-2 ����08:45:09
 * 
 */
public class ProcessStudentData implements Runnable {

	private static final Logger log = Logger
			.getLogger(ProcessStudentData.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	private List<String> list = new ArrayList<String>();
    private static int _NOSCANTIMES ;
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				_NOSCANTIMES = StudentSafeUtil.getIntValue("no_scan_times") ;
			} catch (StudentSafeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int _currentSize = StudentReaderQueueWithTrigger.getListSize();
			StudentExt student;
			String _rfid;
			String[] array;
			for (int i = 0; i < _currentSize; i++) {
				list = StudentReaderQueueWithTrigger.get(i);
				log.info("�Ķ����յ���ѧ��ID�б�" + list.toString());
				// ������ǰlist
				for (int index = 0; i < list.size(); index++) {
					_rfid = list.get(index);
					array = _rfid.split("|");
					student = StudentQueue.get(array[0]);
					if (student == null) {
						StudentExt st = new StudentExt();
						st.setRfidCardID(array[0]);
						st.setDirection(array[1]);
						StudentQueue.put(st);
					} else {
						// δ��⵽�������㡣
						student.setNoscantimes(0);
					}
					
					countNoscanTimes() ;
				}
			}
			if (StudentReaderQueueWithTrigger.getListSize() == 0 ){
				list = null ;
				countNoscanTimes(); 
			}
		}

	}

	private void countNoscanTimes() {
		List<String> _studentlist = StudentQueue.getRfidList();
		StudentExt student;
		if (list != null){
			for (int i = 0; i < _studentlist.size(); i++) {
				if (list.indexOf(_studentlist.get(i)) < 0) {
					student = StudentQueue.get(_studentlist.get(i));
					student.setNoscantimes(student.getNoscantimes() + 1);
					//���δ��⵽�Ĵ��������趨����
					if (student.getNoscantimes() >= _NOSCANTIMES){
						StudentQueue.pop(student);
						//TODO: ��һ��
					}
				}
			}
			// ����Ķ���û�ж����κ����ݣ���������������δ��⵽������1
		}else {
		  
		  for(int index = 0 ;index < StudentQueue.getSize(); index ++) {
			  student = StudentQueue.get(index); 
			  student.setNoscantimes(student.getNoscantimes() + 1) ;
			  if (student.getNoscantimes() >= _NOSCANTIMES){
					StudentQueue.pop(student);
					//TODO: ��һ��
				}
		  }
		}
	}

}
