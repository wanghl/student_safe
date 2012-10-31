package com.zephyr.studentsafe.ui.action;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.zephyr.studentsafe.bo.StudentExt;
import com.zephyr.studentsafe.impl.StudentQueue;
import com.zephyr.studentsafe.ui.ZephyrPntMainFrame;
import com.zephyr.studentsafe.util.StudentSafeUtil;

public class ProcessJTableEvent {

	/**
	 * 
	 * @param list
	 * @param model
	 */
	public static void updateTableUI(final StudentExt student) {
		// 刷新进出校检测table
		final DefaultTableModel model = ((DefaultTableModel) ZephyrPntMainFrame
				.getRfidInfoTable().getModel());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				model.addRow(new Object[] { model.getRowCount() + 1,
						student.getRfidCardID(),
						StudentSafeUtil.getCurrentDateAllformated(),
						student.getFirstArea(), student.getLastArea(),
						student.getReceiveTimes(), student.getEvent() });

				ZephyrPntMainFrame.getRfidInfoTable().repaint();
				ZephyrPntMainFrame.getRfidInfoTable().updateUI();

			}
		});
		// 刷新数据监控table
		final DefaultTableModel monitModel = (DefaultTableModel) ZephyrPntMainFrame
				.getMonitDataTable().getModel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (student.getEvent().equals("入校")) {
					// 跟新入校人数
					monitModel.setValueAt((Integer.parseInt(monitModel
							.getValueAt(0, 1).toString()) + 1), 0, 1);
				} else if (student.getEvent().equals("出校")) {
					// outschoole
					monitModel.setValueAt((Integer.parseInt(monitModel
							.getValueAt(1, 1).toString()) + 1), 1, 1);

				} else {
					// other
					// 妈逼。。。 月供、取暖费、物业费。。。。要逼死老子了。。。
					// 苦逼的码农。。。
					monitModel.setValueAt((Integer.parseInt(monitModel
							.getValueAt(2, 1).toString()) + 1), 2, 1);

				}

				ZephyrPntMainFrame.getMonitDataTable().repaint();
				ZephyrPntMainFrame.getMonitDataTable().updateUI();
			}
		});

	}

	// 刷新数据监控表格里数据
	public static void updateMonitTableData() {

		final DefaultTableModel monitModel = (DefaultTableModel) ZephyrPntMainFrame
				.getMonitDataTable().getModel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 显示剩余待处理的人数
				monitModel.setValueAt(StudentQueue.getSize(), 4, 1);
				
				if (StudentQueue.getSize() == 0) {
					countAll() ;
				}

				ZephyrPntMainFrame.getMonitDataTable().repaint();
				ZephyrPntMainFrame.getMonitDataTable().updateUI();
			}
		});

	}

	private static void countAll() {
		final DefaultTableModel monitModel = (DefaultTableModel) ZephyrPntMainFrame
				.getMonitDataTable().getModel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//计算总数之前，先暂停一下，UI界面刷新先
				try {
					Thread.sleep(3000) ;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int count = Integer.parseInt(monitModel.getValueAt(0, 1)
						.toString())
						+ Integer.parseInt(monitModel.getValueAt(1, 1)
								.toString())
						+ Integer.parseInt(monitModel.getValueAt(2, 1)
								.toString());
				monitModel.setValueAt(count, 3, 1);

				ZephyrPntMainFrame.getMonitDataTable().repaint();
				ZephyrPntMainFrame.getMonitDataTable().updateUI();
			}
		});

	}

}
