package com.zephyr.studentsafe.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class ThreadPoolManage {
	
	private  static ThreadPoolExecutor threadPool = null;

	private  static ThreadPoolExecutor getPoolManage() {
		if (threadPool == null){
			//return threadPool = new ThreadPoolExecutor(MIX_POOL_SIZE, MAX_POOL_SIZE,keepAliveTime, unit,new ArrayBlockingQueue<Runnable>(QueueSize),new ThreadPoolExecutor.AbortPolicy());
		//使用固定大小线程池
			return threadPool =   (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		}else{
			return threadPool ;
		}
	}
	
	public static ThreadPoolExecutor getThreadPool(){
		
		return getPoolManage();
	}
	
	public static void relaseThreadPool(){
		if (threadPool != null){
			threadPool.shutdownNow() ;
			threadPool = null ;
			
		}
	}
	
	
	
	public static void main(String[] argvs){
		ThreadPoolManage.getPoolManage();
	}

}
