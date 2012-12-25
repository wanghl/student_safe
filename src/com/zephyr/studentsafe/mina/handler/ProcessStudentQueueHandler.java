package com.zephyr.studentsafe.mina.handler;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.mobilemessage.ISendMobileMessage;
import com.zephyr.studentsafe.mobilemessage.SendMobileMessage;
import com.zephyr.studentsafe.util.IPManUtil;
import com.zephyr.studentsafe.util.SystemProperty;

public class ProcessStudentQueueHandler extends IoHandlerAdapter{
		private final Logger log = Logger.getLogger(ProcessStudentQueueHandler.class);
	 @Override
	    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
	    {
	        log.error(cause);
	    }

	    @Override
	    public void messageReceived( IoSession session, Object message ) throws Exception
	    {
	        if(log.isDebugEnabled())
	        {
	        	log.debug("���뿨�ŷ����������У����ţ�" + message.toString());
	        }
	    	StudentReaderQueue.put((List<String>)message);
	    	
	    	if(log.isDebugEnabled())
	    	{
	    		log.debug("��������г��ȣ�" + StudentReaderQueue.getSize());
	    	}
	        
	    }

	    @Override
	    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
	    {
	        log.warn("�趨ʱ�����Ķ����������ϴ�");
	        if (SystemProperty.sendReaderErrorMessage())
	        {
	        	InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
	        	String message = "[����]�趨ʱ���ڣ��Ķ�����������Ϣ��IP:" 
	        			+ inetSocketAddress.getAddress().getHostAddress() 
	        			+ "����ѧУ��" + IPManUtil.getStringValue(inetSocketAddress.getAddress().getHostAddress());
	        	ISendMobileMessage sender = new SendMobileMessage();
	        	sender.sendMessage(message ,SystemProperty.getReceiveMOMobile()) ;
	        	
	        }
	    }

}
