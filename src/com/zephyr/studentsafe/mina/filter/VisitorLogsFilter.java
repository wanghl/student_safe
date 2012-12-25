package com.zephyr.studentsafe.mina.filter;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilter.NextFilter;
import org.apache.mina.core.session.IoSession;

import com.zephyr.studentsafe.bo.MonitorData;
import com.zephyr.studentsafe.impl.MonitorDataPool;
import com.zephyr.studentsafe.mobilemessage.ISendMobileMessage;
import com.zephyr.studentsafe.mobilemessage.SendMobileMessage;
import com.zephyr.studentsafe.util.IPManUtil;
import com.zephyr.studentsafe.util.SystemProperty;

public class VisitorLogsFilter extends IoFilterAdapter{
	
	private final Logger log = Logger.getLogger(VisitorLogsFilter.class);

	public void sessionOpened(NextFilter nextFilter, IoSession session)
			throws Exception {
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		log.info("�յ��µ���������IP��" + inetSocketAddress.getAddress().getHostAddress() + " �˿�:" + inetSocketAddress.getPort()) ;
		//
		MonitorData data = new MonitorData();
		MonitorDataPool.put(inetSocketAddress.getAddress().getHostAddress() , data);
		if(SystemProperty.sendSocketOpenedMessge())
		{
			sendSocketOpenedMessage( session) ;
		}
		nextFilter.sessionOpened(session);
	}

	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		MonitorDataPool.addReceivedDataLength(inetSocketAddress.getAddress().getHostAddress(), ((IoBuffer)message).limit()) ;
		log.info("RECEIVED �� " + message);
		nextFilter.messageReceived(session, message);
	}

	public void sessionClosed(NextFilter nextFilter, IoSession session)
			throws Exception {
		//TODO  send message
		log.info("�ͻ��˹ر�����");
		if(SystemProperty.sendNetworkErrorMessage())
		{
			sendNetworkErrorMessage( session);
		}
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String ip = inetSocketAddress.getAddress().getHostAddress()  ;
		MonitorDataPool.pop(ip) ;
	}

	public void exceptionCaught(NextFilter nextFilter, IoSession session,
			Throwable cause) throws Exception {
		
		log.info("�����쳣");
		if(SystemProperty.sendNetworkErrorMessage())
		{
			sendNetworkErrorMessage( session);
		}
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String ip = inetSocketAddress.getAddress().getHostAddress()  ;
		MonitorDataPool.pop(ip) ;
		log.error(cause);

	}
	
	public void sendNetworkErrorMessage(IoSession session)
	{
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String ip = inetSocketAddress.getAddress().getHostAddress()  ;
		String school = IPManUtil.getStringValue(inetSocketAddress.getAddress().getHostAddress()) ;
		String mobile = SystemProperty.getReceiveMOMobile() ;
		StringBuilder sb = new StringBuilder();
		sb.append("[�쳣����]IP��" + ip + " ����ѧУ��" + school + " ����������ӶϿ���") ;
		
		ISendMobileMessage sender  = new SendMobileMessage();
		sender.sendMessage(sb.toString(), mobile) ;
	}
	
	public void sendSocketOpenedMessage(IoSession session)
	{
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String ip = inetSocketAddress.getAddress().getHostAddress()  ;
		String school = IPManUtil.getStringValue(inetSocketAddress.getAddress().getHostAddress()) ;
		String mobile = SystemProperty.getReceiveMOMobile() ;
		StringBuilder sb = new StringBuilder();
		sb.append("[��������]�յ��µ���������IP��" + ip + " ����ѧУ��" + school + " ���������������") ;
		
		ISendMobileMessage sender  = new SendMobileMessage();
		sender.sendMessage(sb.toString(), mobile) ;
	}


}
