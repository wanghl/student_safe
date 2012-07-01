package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.ReaderA;
import com.zephyr.studentsafe.bo.ReaderB;
import com.zephyr.studentsafe.bo.Student;
import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.dao.HibernateUtil;
import com.zephyr.studentsafe.dao.ReaderDebugDAO;
import com.zephyr.studentsafe.mobilemessage.MobileMessageHandler;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.ThreadPoolManage;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.mobilemessage.MobileMessageHandler;
import com.zephyr.studentsafe.util.StudentSafeUtil;
import com.zephyr.studentsafe.util.ThreadPoolManage;

public class ProcessReceiveDataExt implements Runnable {
	private static final Logger log = Logger
			.getLogger(ProcessReceiveDataExt.class);
	@SuppressWarnings("unused")
	private List<String> list = new ArrayList<String>();
	private String readerFlag = "";
	private static int NO_SCAN_TIMES;
	private BaseDAO dao = new BaseDAO();
	private ReaderA ra = null;
	private ReaderB rb = null;
	private boolean DEBUG = false;

	public void run() {
		try {
			NO_SCAN_TIMES = StudentSafeUtil.getIntValue("no_scan_times");
			int debug_mode = StudentSafeUtil.getIntValue("debug_mode");
			if (debug_mode == 1) {
				DEBUG = true;
			}
		} catch (StudentSafeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String rfidCardID = null;

		StudentExt student = null;
		while (true) {

			 try {
			 Thread.sleep(500);
			 } catch (InterruptedException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			for (int i = 0; i < StudentReaderQueue.getSize(); i++) {
				student = null;
				list = StudentReaderQueue.get(i);

				StudentReaderQueue.pop(i);
				readerFlag = list.get(list.size() - 1);
				list.remove(list.size() - 1);

				log.info(readerFlag + "阅读器收到的学生ID列表" + list.toString());
				for (Iterator<String> lst = list.iterator(); lst.hasNext();) {
					student = null;
					rfidCardID = lst.next();
					// 先从等待队列处理
					student = StudentWaitQueue.get(rfidCardID);
					if (student != null) {
						StudentWaitQueue.pop(student);
						StudentQueueExt.put(student);
					}
					student = StudentQueueExt.get(rfidCardID);

					if (student == null) {

						student = new StudentExt();
						student.setRfidCardID(rfidCardID);
						// 如果卡号在队里中还没创建，并且此卡号由A探测器第一次探测到
						if (readerFlag.equals("A")) {

							student.setFristScanArea("A");
							if (DEBUG) {
								ra = new ReaderA();
								ra.setRfid(student.getRfidCardID());
								dao.saveORupdate(ra);
							}

						} else if (readerFlag.equals("B")){

							student.setFristScanArea("B");
							if (DEBUG) {
								rb = new ReaderB();
								rb.setRfid(student.getRfidCardID());
								dao.saveORupdate(rb);
							}
						}else {
							student.setFristScanArea("C");
						}

						StudentQueueExt.put(student);
						// dao.saveORupdate(student);
					} else {

						if (readerFlag.equals("A")) {
							// 检测到卡片 将未扫描到次数清零 ；
							student.setReaderAnoScanTime(0);

							student.setLastScanArea("A");
							// if (!student.getFristScanArea().equals("A"))
							// {
							// }
							if (DEBUG) {
								ra = new ReaderA();
								ra.setRfid(student.getRfidCardID());
								dao.saveORupdate(ra);
							}

						} else if (readerFlag.equals("B"))  {
							student.setReaderBnoScanTime(0);

							student.setLastScanArea("B");
							if (DEBUG) {
								rb = new ReaderB();
								rb.setRfid(student.getRfidCardID());
								dao.saveORupdate(rb);
							}
							// if (!student.getFristScanArea().equals("B"))
							// {
							// }
						}else {
							student.setReaderCnoScanTime(0);
							student.setLastScanArea("C");
						}

					}

					countNoHeartBeatTimes();
					// list = new ArrayList<String>();
				}
			}
			// 从StudentReaderQueue中移除已经处理过的list
			if (StudentReaderQueue.getSize() == 0) {
				list = null;
				countNoHeartBeatTimes();
			}
			// StudentReaderQueue.pop();
		}
	}

	private void countNoHeartBeatTimes() {
		StudentExt stud = null;
		String stdrfid = null;
		List<String> l = StudentQueueExt.getRfidList();
		//log.info(l);
		// if ((l.indexOf(rfidCardID) < 0)) {
		for (Iterator<String> stde = l.iterator(); stde.hasNext();) {
			stud = null;
			stdrfid = stde.next();
			if (list != null && list.indexOf(stdrfid) < 0) {
				stud = StudentQueueExt.get(stdrfid);
				if (readerFlag.equals("A")) {
					stud.setReaderAnoScanTime(stud.getReaderAnoScanTime() + 1);
					if (stud.getReaderAnoScanTime() >= NO_SCAN_TIMES
							&& stud.getReaderBnoScanTime() >= NO_SCAN_TIMES && stud.getReaderAnoScanTime() >= NO_SCAN_TIMES) {
						StudentQueueExt.pop(stud);
						log.info("POP");
						ProcessQueueDataExt.run(stud);
					}

				} else if (readerFlag.equals("B")){
					stud.setReaderBnoScanTime(stud.getReaderBnoScanTime() + 1);
					if (stud.getReaderBnoScanTime() >= NO_SCAN_TIMES
							&& stud.getReaderAnoScanTime() >= NO_SCAN_TIMES && stud.getReaderAnoScanTime() >= NO_SCAN_TIMES) {
						StudentQueueExt.pop(stud);
						log.info("POP");
						ProcessQueueDataExt.run(stud);
					}
				}else {
					stud.setReaderCnoScanTime(stud.getReaderCnoScanTime() + 1);
					if (stud.getReaderBnoScanTime() >= NO_SCAN_TIMES
							&& stud.getReaderAnoScanTime() >= NO_SCAN_TIMES && stud.getReaderCnoScanTime() >= NO_SCAN_TIMES) {
						StudentQueueExt.pop(stud);
						log.info("POP");
						ProcessQueueDataExt.run(stud);
					}
				}
			}
		}
		// 如果阅读器没有读到任何数据，队列中所有数据未检测到次数加1
		if (list == null) {
			for (int i = 0; i < StudentQueueExt.getSize(); i++) {

				stud = StudentQueueExt.get(i);
				stud.setReaderAnoScanTime(stud.getReaderAnoScanTime() + 1);
				stud.setReaderBnoScanTime(stud.getReaderBnoScanTime() + 1);
				stud.setReaderCnoScanTime(stud.getReaderCnoScanTime() + 1);
				if (stud.getReaderAnoScanTime() >= NO_SCAN_TIMES
						&& stud.getReaderBnoScanTime() >= NO_SCAN_TIMES && stud.getReaderAnoScanTime() >= NO_SCAN_TIMES) {

					StudentQueueExt.pop(i);
					ProcessQueueDataExt.run(stud);
				}

			}
		}

	}

	public static void main(String[] argvs) throws InterruptedException,
			StudentSafeException {
		// Timer t = new Timer();
		// t.schedule(new ProcessQueueDataExt(), 3 * 1000, 5 * 1000);
		// 数据库连接初始化;
		HibernateUtil.getSession();
		MobileMessageHandler.getInstance();
		List<String> a1 = new ArrayList<String>();
		List<String> b1 = new ArrayList<String>();
		// a.add("207737");
		// a.add("207701");
		// a.add("209247");
		// a.add("209263");
		// a.add("A");
		// // // List<String> b1 = new ArrayList<String>();
		// b.add("207737");
		// b.add("209263");
		// b.add("209263");
		// b.add("209247");
		// b.add("209263");
		// b.add("B");
		// // Thread.sleep(5000);
		// StudentReaderQueue.put(b);
		// StudentReaderQueue.put(a);
		ProcessReceiveDataExt p = new ProcessReceiveDataExt();

		// Thread.sleep(5000);
		//		
		// a.add("207737");
		// a.add("A");
		// b.add("207701");
		// b.add("B");
		// StudentReaderQueue.put(a);
		// StudentReaderQueue.put(b);
		// p.run();
		//		
		// Thread.sleep(5000);
		// p.run();
		// Thread.sleep(5000);
		// p.run();
		// Thread.sleep(5000);
		// p.run();
		// Thread.sleep(5000);
		// p.run();
		// Thread.sleep(5000);
		// p.run();
		// System.out.println(StudentQueue2DB.getSize());
		// b1.add("207737");
		b1.add("210888");
		b1.add("207701");
		b1.add("209247");
		b1.add("209263");
		b1.add("209219");
		b1.add("207729");
		b1.add("209271");
		b1.add("209229");
		b1.add("207871");
		b1.add("362838");
		b1.add("362833");
		b1.add("362842");
		b1.add("2841	");
		b1.add("362856");
		b1.add("362868");
		b1.add("362852");
		b1.add("362855");
		b1.add("362848");
		b1.add("362835");
		b1.add("210884");
		b1.add("210880");
		b1.add("210765");
		b1.add("210876");
		b1.add("210757");
		b1.add("210756");
		b1.add("213874");
		b1.add("362852");
		b1.add("210952");
		b1.add("213879");
		b1.add("210798");
		b1.add("210800");
		b1.add("210526");
		b1.add("364508");
		b1.add("210581");
		b1.add("364204");
		b1.add("210760");
		b1.add("210810");
		b1.add("210753");
		b1.add("436411");
		b1.add("210626");
		b1.add("210881");
		b1.add("364199");
		b1.add("215822");
		b1.add("210947");
		b1.add("364176");
		b1.add("210949");
		b1.add("210913");
		b1.add("210749");
		b1.add("210517");
		b1.add("213885");
		b1.add("213851");
		b1.add("213880");
		b1.add("210796");
		b1.add("321076");
		b1.add("210888");
		b1.add("210911");
		b1.add("210948");
		b1.add("210752");
		b1.add("210744");

		b1.add("A");

		a1.add("210888");
		a1.add("207701");
		a1.add("209247");
		a1.add("209263");
		a1.add("209219");
		a1.add("207729");
		a1.add("207695");
		a1.add("209271");
		a1.add("209229");
		a1.add("207871");
		a1.add("362838");
		a1.add("362868");
		a1.add("362852");
		a1.add("362855");
		a1.add("362848");
		a1.add("362835");
		a1.add("210884");
		a1.add("210880");
		a1.add("210765");
		a1.add("210876");
		a1.add("210621");
		a1.add("210757");
		a1.add("210756");
		a1.add("213874");
		a1.add("362852");
		a1.add("210952");
		a1.add("213879");
		a1.add("210798");
		a1.add("210800");
		a1.add("210526");
		a1.add("210581");
		a1.add("364204");
		a1.add("210760");
		a1.add("210810");
		a1.add("210753");
		a1.add("436411");
		a1.add("210626");
		a1.add("210881");
		a1.add("364199");
		a1.add("215822");
		a1.add("210947");
		a1.add("364176");
		a1.add("210949");
		a1.add("210913");
		a1.add("210749");
		a1.add("210517");
		a1.add("213885");
		a1.add("213851");

		a1.add("B");
		List<String> d1 = new ArrayList<String>();
		d1.add("210888");
		d1.add("207701");
		d1.add("209247");
		d1.add("209263");
		d1.add("209219");
		d1.add("207729");
		d1.add("207695");
		d1.add("209271");
		d1.add("209229");
		d1.add("207871");
		d1.add("362838");
		d1.add("362868");
		d1.add("362852");
		d1.add("362855");
		d1.add("362848");
		d1.add("362835");
		d1.add("210884");
		d1.add("210880");
		d1.add("210765");
		d1.add("210876");
		d1.add("210621");
		d1.add("210757");
		d1.add("210756");
		d1.add("213874");
		d1.add("362852");
		d1.add("210952");
		d1.add("213879");
		d1.add("210798");
		d1.add("210800");
		d1.add("210526");
		d1.add("210581");
		d1.add("364204");
		d1.add("210760");
		d1.add("210810");
		d1.add("210753");
		d1.add("436411");
		d1.add("210626");
		d1.add("210881");
		d1.add("364199");
		d1.add("215822");
		d1.add("210947");
		d1.add("364176");
		d1.add("210949");
		d1.add("210913");
		d1.add("210749");
		d1.add("210517");
		d1.add("213885");
		d1.add("213851");
		d1.add("C");

		//StudentReaderQueue.put(a1);
		StudentReaderQueue.put(d1);
		StudentReaderQueue.put(b1);

		ThreadPoolManage.getThreadPool().execute(new ProcessReceiveDataExt());
		

	}

}
