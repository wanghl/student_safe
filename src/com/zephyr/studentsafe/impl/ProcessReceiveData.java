package com.zephyr.studentsafe.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.zephyr.studentsafe.bo.Student;

public class ProcessReceiveData {
	private static final Logger log = Logger.getLogger(ProcessReceiveData.class);
	@SuppressWarnings("unused")
	private List<String> list ;
	private String readerFlag ;
	public ProcessReceiveData(List<String> list ,String flag){
		this.list = list;
		readerFlag = flag ;
		//System.out.println(list.toString());
	}
	
	public void run() {
		if(!list.isEmpty()){
			log.info(readerFlag  + " ̽ͷ�յ���ѧ��ID�б�" + list.toString());
			
		}
		Student student = null;
		String key = null ;
		for (String cardId : list){
			student = null ;
			student = StudentMap.get(cardId);
			//û�м�¼�����¶���
			if ( student == null ){
				student = new Student();
				student.setCardID(cardId);
				//�����A̽ͷ��һ�ζ����ÿ��� 
				if ( readerFlag.equals("A")){
					log.info("����A̽����");
					student.setInflag(0);
					student.setReaderAstate(0);
					student.setInSchooleTime(Calendar.getInstance());
				}
				else {
					log.info("����B̽����");
					student.setInflag(1);
					student.setReaderBstate(0);
					student.setOutSchooleTime(Calendar.getInstance());
				}
				StudentMap.put(cardId, student);
			}
			else{
				
				if (readerFlag.equals("A")){
					student.setReaderAstate(0);
					student.setNoTimes_A(0);
					
				}
				else{
					student.setReaderBstate(0);
					student.setNoTimes_B(0);
					
				}
			}
			
		}
		
		for ( Iterator it = StudentMap.getMapIterator(); it.hasNext();){
			key = (String) it.next();
			//key����̽ͷ�������б���
			if ( list.indexOf( key ) < 0){
				student = StudentMap.get(key);
				if (readerFlag.equals("A")){
					//A̽ͷû���������ID�������Notiems������5
					student.setReaderAstate(1);
					if ( (student.getNoTimes_A() + 1) == 5 && student.getNoTimes_B() == 5) {
						student.setNoTimes_A(5);
						if ( student.getReaderAstate() == 1 && student.getReaderBstate() == 1){
							//������������
							student.setOutflag(0);
							student.setOutSchooleTime(Calendar.getInstance());
							StudentQueue.put(student);
							log.info("�뿪A̽����");
							StudentMap.pop(student.getCardID());
							System.out.println("ѧ�� " + student.getCardID() + " IN " + student.getInflag() +  " OUT " + student.getOutflag() + student.getInOutValue());
						}
						//ѧ������A̽��������δ����B̽����
						if ( student.getReaderAstate() == 1 && student.getReaderBstate() < 0){
							System.out.println("�ε���");
						}
					}
					if ( (student.getNoTimes_A() + 1) <= 5){
						student.setNoTimes_A(student.getNoTimes_A() + 1);
					}
				}
				else{
					student.setReaderBstate(1);
					if ( (student.getNoTimes_B() + 1) == 5 && student.getNoTimes_A() == 5) {
						student.setNoTimes_B(5);
						if (student.getReaderBstate() == 1 && student.getReaderAstate() == 1){
							student.setOutflag(1);
							student.setOutSchooleTime(Calendar.getInstance());
							StudentQueue.put(student);
							StudentMap.pop(student.getCardID());
							log.info("�뿪B̽����");
							System.out.println("ѧ�� " + student.getCardID() + " IN " + student.getInflag() +  " OUT " + student.getOutflag() + student.getInOutValue());
						}
					}
					if ((student.getNoTimes_B() + 1) <= 5 ){
						student.setNoTimes_B(student.getNoTimes_B() + 1);
					}
				}
			}
		}
		
	}
	
	public static void main(String[] argvs){
		

//		ProcessReceiveData p = null;
//		String f = "";
//		for ( int i = 1 ; i <15 ;i ++){
//			if (( i % 2) == 1){
//				f = "B";
//			}else {
//				f = "A";
//			}
//			p = new ProcessReceiveData(f);
//			p.run() ;
//		}
//		
	}

}
