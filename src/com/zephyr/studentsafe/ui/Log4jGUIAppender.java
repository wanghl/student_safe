package com.zephyr.studentsafe.ui;

import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.zephyr.studentsafe.ui.dialog.AboutFrame;

/**
 * 自定义log4j appender,把日志同步输出到界面上
 * 
 * @author lenovo
 * 
 */
public class Log4jGUIAppender extends AppenderSkeleton {

	protected void append(LoggingEvent loggingevent) {
		String message = this.layout.format(loggingevent);

		if (ZephyrPntMainFrame.getMessageBox() == null)
			return;
		ZephyrPntMainFrame.printLog(message);

	}

	public void close() {
		if (this.closed)
		{
			return;
		}
		this.closed = true;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return true;
	}

}
