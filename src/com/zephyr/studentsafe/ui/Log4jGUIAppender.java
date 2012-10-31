package com.zephyr.studentsafe.ui;

import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.zephyr.studentsafe.ui.dialog.AboutFrame;

/**
 * �Զ���log4j appender,����־ͬ�������������
 * 
 * @author lenovo
 * 
 */
public class Log4jGUIAppender extends AppenderSkeleton {

	@Override
	protected void append(LoggingEvent loggingevent) {
		final String message = this.layout.format(loggingevent);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				if (ZephyrPntMainFrame.getMessageBox() == null)
					return;
				ZephyrPntMainFrame.printLog(message);
			}
		});

	}

	@Override
	public void close() {
		if (this.closed) {
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
