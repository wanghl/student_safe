/**
 *<p>Title: ProcessData.java</p>
 *<p>Copyright (c) 2012 北京紫枫科技开发有限公司.Co.Ltd. All rights reserved.</p>
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
 * Description: 处理接收到的数据
 * </p>
 * 
 * @author wanghongliang xbwolf@sina.cn
 *@date 2012-7-2 下午08:45:09
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
				log.info("阅读器收到的学生ID列表" + list.toString());
				// 遍历当前list
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
						// 未检测到次数归零。
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
					//如果未检测到的次数大于设定次数
					if (student.getNoscantimes() >= _NOSCANTIMES){
						StudentQueue.pop(student);
						//TODO: 下一步
					}
				}
			}
			// 如果阅读器没有读到任何数据，队列中所有数据未检测到次数加1
		}else {
		  
		  for(int index = 0 ;index < StudentQueue.getSize(); index ++) {
			  student = StudentQueue.get(index); 
			  student.setNoscantimes(student.getNoscantimes() + 1) ;
			  if (student.getNoscantimes() >= _NOSCANTIMES){
					StudentQueue.pop(student);
					//TODO: 下一步
				}
		  }
		}
	}

}
