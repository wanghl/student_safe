package com.zephyr.studentsafe.mina;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.firewall.BlacklistFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.zephyr.studentsafe.dao.HibernateUtil;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.impl.MonitorDataPoolClear;
import com.zephyr.studentsafe.impl.ProcessStudentData;
import com.zephyr.studentsafe.mina.filter.MyProtocolCodecFactory;
import com.zephyr.studentsafe.mina.filter.VisitorLogsFilter;
import com.zephyr.studentsafe.mina.handler.ProcessStudentQueueHandler;
import com.zephyr.studentsafe.mobilemessage.MobileMessageHandler;
import com.zephyr.studentsafe.mobilemessage.ReceiveMessageMO;
import com.zephyr.studentsafe.mobilemessage.ReceiveMessageRPT;
import com.zephyr.studentsafe.util.IPManUtil;
import com.zephyr.studentsafe.util.SystemProperty;
import com.zephyr.studentsafe.util.ThreadPoolManage;

public class MinaServer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(MinaServer.class);

	public void init() throws ServletException {

		// start db
		HibernateUtil.getSession();
		// init mas server
		try {
			MobileMessageHandler.alive();
		} catch (StudentSafeException e) {
			log.error(e);
		}
		// start in/out school process thread
		ProcessStudentData.alive();
		ThreadPoolManage.getThreadPool().execute(new ProcessStudentData());
		// clear in/out school
		try
		{
			JobDetail j = new JobDetail("clear", "group2", MonitorDataPoolClear.class);
			CronTrigger c = new CronTrigger("t1", "t1");
			CronExpression ce = new CronExpression("0 0 12,23 * * ?");
			c.setCronExpression(ce);
			SchedulerFactory s = new StdSchedulerFactory();
			Scheduler t = s.getScheduler();
			t.scheduleJob(j, c);
			t.start();
			
		}catch(Exception e)
		{
			log.error(e);
		}

		// 执行收回执任务
		Timer receiveRPOTask = new Timer();
		ReceiveMessageRPT task1 = new ReceiveMessageRPT();
		receiveRPOTask.schedule(task1, 30 * 1000,SystemProperty.getReceiveRPTTime());

		// 接收短信回复MO
		if (SystemProperty.isReceiveMOFromFamily()) {
			Timer receiveMOTask = new Timer();
			ReceiveMessageMO task2 = new ReceiveMessageMO();
			receiveMOTask.schedule(task2, 10 * 1000,SystemProperty.getReceiveMOTime());
		}

		// start mina server
		IoAcceptor acceptor = new NioSocketAcceptor();
		List blacklist = IPManUtil.getBlackList();
		try {
			if (!blacklist.isEmpty()) {
				BlacklistFilter blacklistFilter = new BlacklistFilter();
				InetAddress[] addressArray = new InetAddress[blacklist.size()];
				for (int i = 0; i < blacklist.size(); i++) {
					addressArray[i] = InetAddress.getByName(blacklist.get(i)
							.toString());
				}
				blacklistFilter.setBlacklist(addressArray);
				acceptor.getFilterChain()
						.addFirst("blacklsit", blacklistFilter);
			}
		} catch (UnknownHostException e) {
			log.error("构建IP地址黑名单过滤器时发生错误：" + e);
		}

		acceptor.getFilterChain().addLast("logger", new VisitorLogsFilter());
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new MyProtocolCodecFactory()));

		acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE,120);

		acceptor.setHandler(new ProcessStudentQueueHandler());

		try {
			acceptor.bind(new InetSocketAddress(8086));
			log.info("MINA服务启动，监听端口" + 8086);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("MINA 服务启动失败！" + e);
		}
	}

	public static void main(String[] argvs) throws ServletException {
		MinaServer server = new MinaServer();
		server.init();
	}
}
