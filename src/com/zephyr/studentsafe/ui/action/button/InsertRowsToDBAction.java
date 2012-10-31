package com.zephyr.studentsafe.ui.action.button;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;

import com.zephyr.studentsafe.bo.ClassInfo;
import com.zephyr.studentsafe.bo.Studentfamily;
import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.BaseDAO;
import com.zephyr.studentsafe.dao.ClassInfoDAO;
import com.zephyr.studentsafe.dao.HibernateUtil;
import com.zephyr.studentsafe.ui.MessageWindow;
import com.zephyr.studentsafe.ui.action.ImportDataToJTableAction;
import com.zephyr.studentsafe.ui.dialog.StudentImportMainFrame;

/**
 * 保存学生信息 保存之前，检查有没有对应的班级信息和班主任老师信息。
 * 
 * @author wanghongliang
 * 
 */
public class InsertRowsToDBAction implements ActionListener {

	public static final Logger log = Logger
			.getLogger(InsertRowsToDBAction.class);
	BaseDAO dao = new BaseDAO();

	@Override
	public void actionPerformed(ActionEvent e) {
		int r = JOptionPane.showConfirmDialog(new Frame(), "确定将当前内容导入数据库?",
				"数据导入确认", JOptionPane.YES_NO_CANCEL_OPTION);
		if (r == JOptionPane.YES_NO_OPTION) {
			Session s = null;
			List list = null;
			Set set = new HashSet();
			Studentrfid student = new Studentrfid();
			Studentfamily family = new Studentfamily();
			DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame
					.getTable().getModel();
			String className = model.getValueAt(0, 1).toString().trim();
			String teacherName = model.getValueAt(0, 7).toString().trim();
			String phone_number = model.getValueAt(0, 8).toString().trim();
			// find teacher.
			list = dao.getTeacherByName(teacherName);
			// if teacher not exists ,save it .
			if (list.isEmpty()) {
				dao.saveTeacherInfo(teacherName, phone_number);
			}
			list = dao.getTeacherByName(teacherName);
			String teacherKey = list.get(0).toString();
			// find class info.
			String classKey = null;
			ClassInfo classinfo = new ClassInfo();
			classinfo.setClassName(className);
			list = dao.getByExample(ClassInfo.class, classinfo);
			// if class not exists ,save it
			if (list.isEmpty()) {
				classinfo.setTeacher(teacherKey);
				try {
					dao.saveORupdate(classinfo);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					log.error(e1);
				}
				classKey = classinfo.getClassUID();
			} else {
				classKey = ((ClassInfo) list.get(0)).getClassUID();
			}
			String exception = null;
			int count = 0;
			int i = 0;
			StringBuffer errors = new StringBuffer();
			String sex = null;
			
			try {
				s = HibernateUtil.getSession();
				s.beginTransaction();
				for (; i < model.getRowCount(); i++) {
					if (model.getValueAt(i, 13).equals("")
							|| model.getValueAt(i, 13).equals("失败")) {
						try {
							// student name
							if (model.getValueAt(i, 2) != null) {
								student.setStudentName(model.getValueAt(i, 2)
										.toString());

							}
							// student rifd
							if (model.getValueAt(i, 3) != null) {
								student.setRfidCardID(model.getValueAt(i, 3)
										.toString());
								// get student from db
								// if student exist
								list = dao.getByExample(Studentrfid.class, student);
								if(!list.isEmpty())
								{
									student = (Studentrfid) list.get(0);
									
								}


							}
							// student m1
							if (model.getValueAt(i, 4) != null) {

								student.setLowCardNumber(model.getValueAt(i, 4)
										.toString());
							}
							// student sex
							if (model.getValueAt(i, 5) != null) {
								
								student.setStudentSex(model.getValueAt(i, 5).toString());

							}
							// student lastscanstate
							student.setLastScanState(0);
							// student bd
//							if (!model.getValueAt(i, 6).toString().equals("")) {
//								student.setStudentBirthday(new Date(model
//										.getValueAt(i, 6).toString()));
//							}
							// student class
							student.setClassUID(classKey);
							// student teacher
							student.setTeacherUID(teacherKey);
							
							
							if (model.getValueAt(i, 9) != null) {
								family.setFamilyName(model.getValueAt(i, 9)
										.toString());

							}
							if (model.getValueAt(i, 10) != null) {
								sex = model.getValueAt(i, 10).toString();
								if (sex.equals("男")) {
									sex = "先生";
								} else {
									sex = "女士";
								}
								family.setFamilySex(sex);

							}
							if (model.getValueAt(i, 11) != null) {

								family.setRelationship(model.getValueAt(i, 11)
										.toString());
							}
							if (model.getValueAt(i, 12) != null) {
								family.setFamilyPhone(model.getValueAt(i, 12)
										.toString());

							}
							family.setIsSendMessage(1);
							set.add(family);
							if (student.getStudentFamily() !=null && student.getStudentFamily().isEmpty()){
								
								student.setStudentFamily(set);
							}
							
							dao.saveORupdate(student);

							student = new Studentrfid();
							family = new Studentfamily();
							set = new HashSet();
							count++;
							ImportDataToJTableAction
									.updateTable(i, 13, "处理成功");
						} catch (Exception es) {
							es.printStackTrace();
							log.error(es);
							// 记录发生错误的数据序号
							ImportDataToJTableAction.updateTable(i, 13, "失败");
							errors.append(model.getValueAt(i, 0) + "、");
							continue;
						}
					}
				}
				s.getTransaction().commit();

			} catch (Exception ex) {
				ex.printStackTrace();
				// 记录发生错误的数据序号
				ImportDataToJTableAction.updateTable(i, 13, "失败");
				errors.append(model.getValueAt(i, 0) + "、");
				log.error(ex);

			} finally {
				s.close();
				setInsertFlag();
				MessageWindow.show(className + " 数据导入完成\n成功导入条数：" + count
						+ "\n导入失败数据序号：" + errors.toString());
			}
		}

	}

	// 如果所有数据均导入数据库，将ImportStudentInfoAction.insertDB 置为true.
	public static void setInsertFlag() {
		DefaultTableModel model = (DefaultTableModel) StudentImportMainFrame
				.getTable().getModel();
		boolean flag = false;
		for (int i = 0; i < model.getRowCount(); i++) {
			if (model.getValueAt(i, 13).equals("失败")) {
				flag = true;
				break;
			} 
		}
		if (!flag) {
			ImportStudentInfoAction.insertDB = true;
		}

	}

}
