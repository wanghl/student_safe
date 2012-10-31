package com.zephyr.studentsafe.ui.action;

import java.awt.Rectangle;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.ui.action.button.ImportStudentInfoAction;
import com.zephyr.studentsafe.ui.dialog.StudentImportMainFrame;

public class ImportDataToJTableAction {

	public static int CURSOR_INDEX = 0;
	public static boolean MAX_INDEX;

	/**
	 * insert cell to jtable
	 * 
	 * @param cell
	 */
	public static void insertCellsToTable(final Cells cells) {

		final DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame.getTable()
				.getModel();
		// frist clear table
		model.getDataVector().clear();
		model.fireTableDataChanged();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// getMaxDataRow() 返回有效数据行数 getMaxDataColumn返回有效数据列数
				for (int i = 1; i < cells.getMaxDataRow() + 1; i++)
				{
					model.addRow(new Object[] { model.getRowCount() + 1,
							cells.get(i, 0).getStringValue(), cells.get(i, 1).getStringValue(), "",
							"",
							cells.get(i, 2).getStringValue(), cells.get(i, 3).getStringValue(),
							cells.get(i, 4).getStringValue(),
							cells.get(i, 5).getStringValue(), cells.get(i, 6).getStringValue(),
							cells.get(i, 7).getStringValue(),
							cells.get(i, 8).getStringValue(), cells.get(i, 9).getStringValue(), ""
						});
				}

				StudentImportMainFrame.getTable().repaint();
				StudentImportMainFrame.getTable().updateUI();
				// 统计导入学生信息条数
				StudentImportMainFrame.setImportCount(StudentImportMainFrame.getImportCount()
						+ model.getRowCount());

			}
		});
		CURSOR_INDEX = 0;
		MAX_INDEX = false;
		ImportStudentInfoAction.insertDB = false;
		ImportStudentInfoAction.exportXls = false;
	}

	/**
	 * insert cell to jtable
	 * 
	 * @param cell
	 */
	public static void insertDataToTable(final List list, final String className) {
		final BaseDAO dao = new BaseDAO();
		final DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame.getTable()
				.getModel();
		// frist clear table
		model.getDataVector().clear();
		model.fireTableDataChanged();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Studentrfid rfid = null;
				Studentfamily family = null;
				String teacherName = (String) dao.getTeacherById(
						((Studentrfid) list.get(0)).getTeacherUID()).get(0);
				// getMaxDataRow() 返回有效数据行数 getMaxDataColumn返回有效数据列数
				for (int i = 0; i < list.size(); i++)
				{
					rfid = (Studentrfid) list.get(i);
					family = rfid.getStudentFamily().iterator().next();
					model.addRow(new Object[] { model.getRowCount() + 1,
							className, rfid.getStudentName(), rfid.getRfidCardID(),
							rfid.getLowCardNumber(),
							rfid.getStudentSex(), rfid.getStudentBirthday(), teacherName, "",
							family.getFamilyName(),
							family.getFamilySex(), family.getRelationship(),
							family.getFamilyPhone(), ""
					});
				}

				StudentImportMainFrame.getTable().repaint();
				StudentImportMainFrame.getTable().updateUI();
				// 统计导入学生信息条数
				StudentImportMainFrame.setImportCount(StudentImportMainFrame.getImportCount()
						+ model.getRowCount());

			}
		});
		CURSOR_INDEX = 0;
		MAX_INDEX = true;
		ImportStudentInfoAction.insertDB = false;
		ImportStudentInfoAction.exportXls = false;
	}

	/**
	 * 设置一个游标CURSOR_INDEX 初始值为0 ； 读到卡号后，从第一行开始插入，到最后一行后游标归零
	 * 跳过家长手机号为空的记录，没有手机号不配发信息卡 读入的RFID和M1卡号 ，先在现有Table中查询，如果有，则替换卡号并选中该行
	 * ；没有则将卡分配给下一个学生。
	 * 
	 * @param rfid
	 * @param m1
	 */
	public static void insertCardNumber(final String rfid, final String m1) {

		final DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame.getTable()
				.getModel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//首先判断jtable中所有学生记录都已处理过
				if (MAX_INDEX)
				{
					// 先找看卡号是否存在
					for (int j = 0; j < model.getRowCount(); j++)
					{
						//如何高频卡号已经分配到某学生，则选中该行
						if (model.getValueAt(j, 3).equals(rfid))
						{
							selectRow(j);
							StudentImportMainFrame.getTable().changeSelection(j, 4, true, true);
							// model.setValueAt(readM1Card(), j, 4); // m1 card
							// number
							return;
						}
					}

					for (int i = 0; i < model.getRowCount(); i++)
					{
						if (model.getValueAt(i, 3).equals(""))
						{
							// 家长电话为空的，暂不发卡
							if (model.getValueAt(i, 12).equals(""))
							{
								model.setValueAt("", i, 4); // m1 card number

							} else
							{
								selectRow(i);
								model.setValueAt(rfid, i, 3); // rfid card
								StudentImportMainFrame.getTable().changeSelection(i, 4, true, true);
							}
						}
					}
				} else
				{
					while (true)
					{
						if (!model.getValueAt(CURSOR_INDEX, 12).equals(""))
						{
							// 查询卡号是否存在
							for (int j = 0; j < model.getRowCount(); j++)
							{
								if (model.getValueAt(j, 3).equals(rfid))
								{
									selectRow(j);
									StudentImportMainFrame.getTable().changeSelection(j, 4, true,
											true);
									return;
								}
							}
							selectRow(CURSOR_INDEX);
							model.setValueAt(rfid, CURSOR_INDEX, 3); // rfid
							StudentImportMainFrame.getTable().changeSelection(CURSOR_INDEX, 4,
									true, true);
							// number
							countRfid();
							CURSOR_INDEX++;
							break;
						} else
						{
							if (++CURSOR_INDEX == model.getRowCount())
							{
								break;
							}
							if (model.getValueAt(CURSOR_INDEX, 12).equals(""))
								continue;
							if ((CURSOR_INDEX) < model.getRowCount())
							{
								// 查询卡号是否存在
								for (int j = 0; j < model.getRowCount(); j++)
								{
									if (model.getValueAt(j, 3).equals(rfid))
									{
										selectRow(j);
										StudentImportMainFrame.getTable().changeSelection(j, 4,
												true, true);
										return;
									}
								}
								selectRow(CURSOR_INDEX);
								model.setValueAt(rfid, CURSOR_INDEX, 3); // rfid
								StudentImportMainFrame.getTable().changeSelection(CURSOR_INDEX, 4,
										true, true);
								// card
								countRfid();

								break;
							}
						}
					}
				}
				if (CURSOR_INDEX >= model.getRowCount())
				{
					CURSOR_INDEX = 0;
					MAX_INDEX = true;
				}
				StudentImportMainFrame.getTable().repaint();
				StudentImportMainFrame.getTable().updateUI();
			}
		});

	}

	// 统计发卡数量
	private static void countRfid() {
		StudentImportMainFrame.setCardCount(StudentImportMainFrame.getCardCount() + 1);
	}

	public static void updateTable(final int row, final int column, final String content) {

		DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame.getTable().getModel();
		// frist clear table

		model.setValueAt(content, row, column);

	}

	private static void selectRow(int index) {
		StudentImportMainFrame.getTable().getSelectionModel().setSelectionInterval(index, index);
		Rectangle reg = StudentImportMainFrame.getTable().getCellRect(index, 1, true);
		StudentImportMainFrame.getTable().scrollRectToVisible(reg);

	}

}
