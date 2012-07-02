/**
 *<p>Title: StudentReaderQueueWithTrigger.java</p>
 *<p>Copyright (c) 2012 �����Ϸ�Ƽ��������޹�˾.Co.Ltd. All rights reserved.</p>
 *@author wanghongliang
 *@data 2012-7-2
 */
package com.zephyr.studentsafe.impl.trigger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<p>ClassName:StudentReaderQueueWithTrigger</p>
 *<p>Description: �������ж�ͷ����Ŀ��źͽ���������Ϣ��
 *                ��ʽ�� 5956|AB ����5956��ʾ���ţ�AB��ʾ��������</p> 
 *@author wanghongliang xbwolf@sina.cn
 *@date 2012-7-2 ����07:14:45
 *
 */
public class StudentReaderQueueWithTrigger {
	
	//�������Ӧ�����̰߳�ȫ��
	 private static List<List<String>> _cardlist = Collections.synchronizedList(new ArrayList<List<String>>());
	 
	 //����
	 public static void put(List list) {
		 _cardlist .add(list);
	 }
	 
	 //ץȡ����ͬʱɾ��
	 public static List<String> get(int index){
		 List<String> l = _cardlist.get(index);
		 //ɾ��
		 remove (index);
		 return l ;
	 }
	 
	 //delete 
	 
	 public static void remove (int index ){
		 _cardlist.remove(index); 
	 }
	 
	 public static int getListSize(){
		 return _cardlist.size() ;
	 }

}
