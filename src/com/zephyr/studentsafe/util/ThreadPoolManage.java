package com.zephyr.studentsafe.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class ThreadPoolManage {
	
	private  static final int MIX_POOL_SIZE = 3; 
	private  static final int MAX_POOL_SIZE = 15;
	private  static final int keepAliveTime = 10;
	private  static final TimeUnit unit = TimeUnit.SECONDS;
	private  static final int QueueSize = 5;
	@SuppressWarnings("unused")
	private  static ThreadPoolExecutor threadPool = null;

	private  static ThreadPoolExecutor getPoolManage() {
		if (threadPool == null){
			return threadPool = new ThreadPoolExecutor(MIX_POOL_SIZE, MAX_POOL_SIZE,keepAliveTime, unit,new ArrayBlockingQueue<Runnable>(QueueSize),new ThreadPoolExecutor.AbortPolicy());
		}else{
			return threadPool ;
		}
	}
	
	@SuppressWarnings("static-access")
	public static ThreadPoolExecutor getThreadPool(){
		
		return getPoolManage();
	}
	
	
	
	public static void main(String[] argvs){
		ThreadPoolManage.getPoolManage();
	}

}
