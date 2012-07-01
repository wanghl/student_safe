package com.zephyr.studentsafe.ui;

import javax.swing.SwingUtilities;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * �Զ���log4j appender,����־ͬ�������������
 * @author lenovo
 *
 */
public class Log4jGUIAppender extends AppenderSkeleton{

	@Override
	protected void append( LoggingEvent loggingevent) {
		final String message = this.layout.format(loggingevent);
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				if ( MainFrame.getJTextArea() == null )
					return ;
				MainFrame.print(message);
				
			}
			
		});
		
	}

	@Override
	public void close() {
		if ( this.closed ){
			return ;
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
