package com.zephyr.studentsafe.util;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zephyr.studentsafe.ui.ZephyrPntMainFrame;

public class FrameClear implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// 清空进出校数据
				((DefaultTableModel) ZephyrPntMainFrame.getRfidInfoTable()
						.getModel()).getDataVector().clear();
				((DefaultTableModel) ZephyrPntMainFrame.getRfidInfoTable()
						.getModel()).fireTableDataChanged();
				ZephyrPntMainFrame.getRfidInfoTable().updateUI();
				// 情况监控数据
				DefaultTableModel monitModel = (DefaultTableModel) ZephyrPntMainFrame
						.getMonitDataTable().getModel();
				for (int i = 0; i < monitModel.getRowCount(); i++) {
					monitModel.setValueAt("0", i, 1);
				}
				ZephyrPntMainFrame.getMonitDataTable().updateUI();
				
				ZephyrPntMainFrame.logMessageBox.setText("");
			}
		});
		
	}

}
