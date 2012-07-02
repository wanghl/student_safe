/**
 *<p>Title: StudentReaderQueueWithTrigger.java</p>
 *<p>Copyright (c) 2012 北京紫枫科技开发有限公司.Co.Ltd. All rights reserved.</p>
 *@author wanghongliang
 *@data 2012-7-2
 */
package com.zephyr.studentsafe.impl.trigger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *<p>ClassName:StudentReaderQueueWithTrigger</p>
 *<p>Description: 保存所有堵头读入的卡号和进出方向信息。
 *                格式： 5956|AB 其中5956表示卡号，AB表示进出方向</p> 
 *@author wanghongliang xbwolf@sina.cn
 *@date 2012-7-2 下午07:14:45
 *
 */
public class StudentReaderQueueWithTrigger {
	
	//这个队列应该是线程安全的
	 private static List<List<String>> _cardlist = Collections.synchronizedList(new ArrayList<List<String>>());
	 
	 //新增
	 public static void put(List list) {
		 _cardlist .add(list);
	 }
	 
	 //抓取数据同时删除
	 public static List<String> get(int index){
		 List<String> l = _cardlist.get(index);
		 //删除
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
