/**
 *<p>Title: ProcessData.java</p>
 *<p>Copyright (c) 2012 �����Ϸ�Ƽ��������޹�˾.Co.Ltd. All rights reserved.</p>
 *@author wanghongliang
 *@data 2012-7-2
 */
package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.Constants;
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
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	private List<String> list = new ArrayList<String>();
    //private static int _NOSCANTIMES = 5;
    private static boolean alive = true ;
    //��Ҫ����
    // 0 ����ģʽ�� 1 ����ģʽ������ģʽֻ����������������
    public static int _DEBUG_MODEL = 0 ;
    private final static Logger log = Logger.getLogger(ProcessStudentData.class);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		log.info("�߼��ж��߳�����!");
		while (alive) {
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			StudentExt student;
			String _rfid;
			String[] array;
			for (int i = 0; i < StudentReaderQueue.getSize(); i++) {
				list = StudentReaderQueue.get(i);
				System.out.println("�������" + list);
				//�Ӷ�����ɾ����ǰ����Ŀ�����Ϣ
				StudentReaderQueue.pop(i);
				// ������ǰlist
				for (Iterator<String> it = list.iterator() ;it.hasNext();) {
					student = null ;
					_rfid = it.next();
					array = _rfid.split("&");
					student = StudentQueue.get(array[0]);
					if (student == null) {
						StudentExt st = new StudentExt();
						st.setRfidCardID(array[0]);
						st.setFirstArea(array[1]);
						st.setRemoteIp(array[2]);
						StudentQueue.put(st);
					} else {
						//�������һ�ξ�������
						student.setLastArea(array[1]);
						// δ��⵽�������㡣
						student.setNoscantimes(0);
						//��ͷ������ǩ���źŴ�����1
						student.setReceiveTimes(student.getReceiveTimes() + 1) ;
					}
					
				}
				//ProcessJTableEvent.updateMonitTableData() ;
				
				try {
					countNoscanTimes() ;
				} catch (StudentSafeException e) {
					// TODO Auto-generated catch block
					log.error(e);
				}
				
			}
			if (StudentReaderQueue.getSize() == 0 ){
				list = null ;
				try {
					countNoscanTimes();
				} catch (StudentSafeException e) {
					// TODO Auto-generated catch block
					log.error(e);
				} 
			}
		}

	}

	private void countNoscanTimes() throws StudentSafeException {
		List<String> _studentlist = StudentQueue.getRfidList();
		StudentExt student;
		String rfid  ;
		if (list != null){
			for (Iterator<String> id = _studentlist.iterator(); id.hasNext();) {
				rfid = id.next() ;
				if (list.indexOf(rfid) < 0) {
					student = StudentQueue.get(rfid);
					student.setNoscantimes(student.getNoscantimes() + 1);
					//���δ��⵽�Ĵ��������趨����
					if (student.getNoscantimes() >= StudentSafeUtil.getIntValue(Constants.NO_SCAN_TIMES)){
						StudentQueue.pop(student);
						MonitorDataPool.addMonitorData(student);
						log.info("����Ϊ " + student.getRfidCardID() + " ��ѧ��" + student.getEvent());
						ProcessQueueData.run(student);
						
					}
				}
			}
			// ����Ķ���û�ж����κ����ݣ���������������δ��⵽������1
		}else {
		  
		  for(int index = 0 ;index < StudentQueue.getSize(); index ++) {
			  student = StudentQueue.get(index); 
			  student.setNoscantimes(student.getNoscantimes() + 1) ;
			  if (student.getNoscantimes() >= StudentSafeUtil.getIntValue(Constants.NO_SCAN_TIMES)){
					StudentQueue.pop(student);
					MonitorDataPool.addMonitorData(student);
					log.info("����Ϊ " + student.getRfidCardID() + " ��ѧ��" + student.getEvent());
					ProcessQueueData.run(student);
					
					
					
				}
		  }
		}
	
	

}
	
	public static void shutDown(){
		alive = false ;
	}
	
	public static void alive(){
		alive = true ;
	}


}
