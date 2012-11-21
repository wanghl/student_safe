package com.zephyr.studentsafe.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceSkin;
import org.jvnet.substance.skin.*;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.zephyr.studentsafe.bo.Studentrfid;
import com.zephyr.studentsafe.dao.HibernateUtil;
import com.zephyr.studentsafe.dao.StudentDAO;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.impl.ProcessStudentData;
import com.zephyr.studentsafe.impl.StudentReaderQueue;
import com.zephyr.studentsafe.ui.action.button.ButtonsAction;
import com.zephyr.studentsafe.ui.action.button.IButtonsAction;
import com.zephyr.studentsafe.ui.dialog.AboutFrame;
import com.zephyr.studentsafe.ui.dialog.ClassManage;
import com.zephyr.studentsafe.ui.dialog.InOutSchoolStatistics;
import com.zephyr.studentsafe.ui.dialog.NewStudentInfo;
import com.zephyr.studentsafe.ui.dialog.StudentInfoManage;
import com.zephyr.studentsafe.ui.dialog.TeacherManage;
import com.zephyr.studentsafe.util.FrameClear;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ZephyrPntMainFrame extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try
		{
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private static JTable rfidInfoTable;
	private JSeparator jSeparator10;
	private JSeparator jSeparator9;
	private JMenuItem jMenuItem5;
	private JMenu manage;
	private JMenuItem jMenuItem2;
	private JPopupMenu jPopupMenu1;
	public  static JTextArea logMessageBox;
	private JMenuItem jMenuItem4;
	private JSeparator jSeparator8;
	private JTabbedPane jTabbedPane;
	private JToolBar toolsBar;
	private JPanel logPanel;
	private JPanel rfidPanel;
	private JPanel toolsBarPanel;
	private JPanel mainPanel;
	private JMenuItem settingMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenu jMenu4;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem clearRfidTable;
	private JMenuItem saveMenuItem;
	private JMenuItem jMenuItem3;
	private JMenuItem jMenuItem1;
	private static JTable dataMonitTable;
	private JScrollPane jScrollPane3;
	private JTabbedPane jTabbedPane1;
	private JSeparator jSeparator7;
	private JButton countButton;
	private JSeparator jSeparator6;
	private JRadioButton debugRadio;
	private JRadioButton normalRadio;
	private JPanel jPanel3;
	private JSeparator jSeparator5;
	private JButton jButton3;
	private JButton jButton2;
	private JTextField cardFindInput;
	private JSeparator jSeparator4;
	private JButton settingButton;
	private JSeparator jSeparator3;
	public static JButton startButton;
	private JPanel jPanel2;
	private JPanel jPanel1;
	private static JMenuItem startMenu;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	private IButtonsAction action = new ButtonsAction();

	private int searchIndex = 0;

	public static void main(String[] args) {
		// JFrame.setDefaultLookAndFeelDecorated(true);
		// JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// SubstanceLookAndFeel.setSkin(new GraphiteAquaSkin()) ;
				ZephyrPntMainFrame inst = new ZephyrPntMainFrame();
				inst.setLocationRelativeTo(null);
				// start db
				HibernateUtil.alive();
				inst.setVisible(true);

				// 定时清空界面上的日志输出
				CronExpression ce;
				try
				{
					JobDetail j = new JobDetail("clear", "group2", FrameClear.class);
					CronTrigger c = new CronTrigger("t1", "t1");
					ce = new CronExpression("0 0 12,23 * * ?");
					c.setCronExpression(ce);
					SchedulerFactory s = new StdSchedulerFactory();
					Scheduler t = s.getScheduler();
					t.scheduleJob(j, c);
					t.start();
				} catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SchedulerException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	public ZephyrPntMainFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		try
		{
			{
				this
						.setTitle("\u7d2b\u67ab\u201c\u5e73\u5b89\u901a\u201d\u6821\u56ed\u5b89\u5168\u7ba1\u7406\u5e73\u53f0");
				this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource(
						"com/zephyr/studentsafe/icons/log.gif")).getImage());

			}

			{
				mainPanel = new JPanel();
				InputMap imk1 = mainPanel
						.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
				imk1.put(KeyStroke.getKeyStroke("F8"), "test input window");
				ActionMap am1 = mainPanel.getActionMap();
				am1.put("test input window", new AbstractAction() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String r = (String) JOptionPane.showInputDialog(
								null,
								"输入卡号和进出方向，格式：" +
										"卡号&方向\n",
								"提示...",
								JOptionPane.INFORMATION_MESSAGE);

						if (r != null)
						{
							List l = new ArrayList();
							l.add(r);
							StudentReaderQueue.put(l);
						}
					}

				});
				BoxLayout mainPanelLayout = new BoxLayout(mainPanel, javax.swing.BoxLayout.Y_AXIS);
				mainPanel.setLayout(mainPanelLayout);
				getContentPane().add(mainPanel, BorderLayout.CENTER);
				{
					toolsBarPanel = new JPanel();
					BorderLayout toolsBarPanelLayout = new BorderLayout();
					toolsBarPanel.setLayout(toolsBarPanelLayout);
					mainPanel.add(toolsBarPanel);
					toolsBarPanel.setPreferredSize(new java.awt.Dimension(821, 53));
					toolsBarPanel.setMaximumSize(new java.awt.Dimension(32767, 53));
					toolsBarPanel.setBorder(BorderFactory.createTitledBorder(""));
					{
						toolsBar = new JToolBar();
						toolsBarPanel.add(toolsBar, BorderLayout.CENTER);
						toolsBar.setBounds(6, 6, 809, 45);
						toolsBar.setPreferredSize(new java.awt.Dimension(809, 47));
						toolsBar.setEnabled(false);
						{
							jPanel1 = new JPanel();
							GridLayout jPanel1Layout = new GridLayout(1, 1);
							jPanel1Layout.setHgap(5);
							jPanel1Layout.setVgap(5);
							jPanel1Layout.setColumns(1);
							jPanel1.setLayout(jPanel1Layout);
							toolsBar.add(jPanel1);
							jPanel1.setPreferredSize(new java.awt.Dimension(125, 28));
							{
								jPanel2 = new JPanel();
								jPanel1.add(jPanel2);
								GroupLayout jPanel2Layout = new GroupLayout((JComponent) jPanel2);
								jPanel2.setLayout(jPanel2Layout);
								jPanel2.setPreferredSize(new java.awt.Dimension(796, 44));
								{
									startButton = new JButton();
									startButton
											.setIcon(new ImageIcon(
													getClass()
															.getClassLoader()
															.getResource(
																	"com/zephyr/studentsafe/icons/start.png")));
									startButton.setBorder(BorderFactory.createTitledBorder(""));
									startButton.setToolTipText("\u542f\u52a8");
									startButton.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent evt) {
											// startButton
											try
											{
												action.startButtonPerformeAction(evt, startMenu);
											} catch (StudentSafeException e)
											{
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
										}
									});
								}
								{
									jSeparator3 = new JSeparator();
									jSeparator3.setOrientation(SwingConstants.VERTICAL);
								}
								{
									settingButton = new JButton();
									settingButton.setBorder(BorderFactory.createTitledBorder(""));
									settingButton.setIcon(new ImageIcon(getClass().getClassLoader()
											.getResource("com/zephyr/studentsafe/icons/set.png")));
									settingButton.setToolTipText("\u8bbe\u7f6e");
									settingButton.addActionListener(new ActionListener() {
										// setting button action .
										public void actionPerformed(ActionEvent evt) {
											try
											{
												action.settingButtonPerformeAction(evt,
														settingButton);
											} catch (StudentSafeException e)
											{
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											// TODO add your code for
											// settingButton.actionPerformed
										}
									});
								}
								{
									jSeparator4 = new JSeparator();
									jSeparator4.setOrientation(SwingConstants.VERTICAL);
								}
								{
									cardFindInput = new JTextField();
									//搜索框输入完成后回车执行
									cardFindInput.addKeyListener(new KeyAdapter(){

										public void keyPressed(KeyEvent e) {
											if (e.getKeyCode() == e.VK_ENTER)
												{
												searchRfid() ;
												}
											}

										
									});
									
								}
								{
									jButton2 = new JButton();
									jButton2.setIcon(new ImageIcon(
											getClass().getClassLoader().getResource(
													"com/zephyr/studentsafe/icons/BWS_006.png")));
									jButton2.setBorder(BorderFactory.createTitledBorder(""));
									jButton2.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent evt) {
											
											searchRfid() ;
											
										}
									});
								}
								{
									countButton = new JButton();
									countButton.setBorder(BorderFactory.createTitledBorder(""));
									countButton.setIcon(new ImageIcon(getClass().getClassLoader()
											.getResource("com/zephyr/studentsafe/icons/cc.png")));
									countButton.setToolTipText("\u68c0\u6d4b\u7387\u7edf\u8ba1");
									countButton.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent evt) {
											countButtonActionPerformed(evt);
										}
									});
								}
								{
									jSeparator6 = new JSeparator();
									jSeparator6.setOrientation(SwingConstants.VERTICAL);
								}
								{
									jPanel3 = new JPanel();
									jPanel3.setLayout(null);
									jPanel3.setBorder(BorderFactory.createTitledBorder(""));
									jPanel3.setMaximumSize(new java.awt.Dimension(200, 38));
									{
										normalRadio = new JRadioButton();
										jPanel3.add(normalRadio);
										normalRadio.setText("\u6b63\u5e38\u6a21\u5f0f");
										normalRadio.setBounds(6, 4, 89, 27);
										normalRadio.setSelected(true);
										normalRadio.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent evt) {
												normalRadio.setSelected(true);
												debugRadio.setSelected(false);
												ProcessStudentData._DEBUG_MODEL = 0;
												// TODO add your code for
												// normalRadio.actionPerformed
											}
										});
									}
									{
										debugRadio = new JRadioButton();
										jPanel3.add(debugRadio);
										debugRadio.setText("\u8c03\u8bd5\u6a21\u5f0f");
										debugRadio.setBounds(101, 3, 93, 31);
										debugRadio.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent evt) {
												normalRadio.setSelected(false);
												debugRadio.setSelected(true);
												// 启动调试模式
												ProcessStudentData._DEBUG_MODEL = 1;

											}
										});
									}
								}
								{
									jSeparator5 = new JSeparator();
									jSeparator5.setOrientation(SwingConstants.VERTICAL);
								}
								{
									jButton3 = new JButton();
									jButton3.setIcon(new ImageIcon(
											getClass().getClassLoader().getResource(
													"com/zephyr/studentsafe/icons/BWS_039.png")));
									jButton3.setBorder(BorderFactory.createTitledBorder(""));
									jButton3.setToolTipText("\u5173\u4e8e...");
									jButton3.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent evt) {
											showAboutDialog();
											// TODO Auto-generated catch block
											// TODO add your code for
											// settingButton.actionPerformed
										}
									});
								}
								{
									jSeparator7 = new JSeparator();
									jSeparator7.setOrientation(SwingConstants.VERTICAL);
									jSeparator7.setBounds(-14, -6, 3, 42);
								}
								jPanel2Layout.setHorizontalGroup(jPanel2Layout
										.createSequentialGroup()
										.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 53,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, 1,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(settingButton, GroupLayout.PREFERRED_SIZE,
												52, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jSeparator4, GroupLayout.PREFERRED_SIZE, 3,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(countButton, GroupLayout.PREFERRED_SIZE, 51,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jSeparator5, GroupLayout.PREFERRED_SIZE, 3,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 53,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jSeparator7, GroupLayout.PREFERRED_SIZE, 3,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 201,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jSeparator6, GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(cardFindInput, GroupLayout.PREFERRED_SIZE,
												120, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 54,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(112, Short.MAX_VALUE));
								jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup()
										.addGroup(
												GroupLayout.Alignment.LEADING,
												jPanel2Layout.createParallelGroup(
														GroupLayout.Alignment.BASELINE)
														.addComponent(startButton,
																GroupLayout.Alignment.BASELINE, 0,
																38, Short.MAX_VALUE)
														.addComponent(jSeparator3,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE, 38,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(settingButton,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE, 38,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jSeparator4,
																GroupLayout.Alignment.BASELINE, 0,
																38, Short.MAX_VALUE)
														.addComponent(countButton,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE, 38,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jSeparator5,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE, 37,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton3,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE, 38,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jSeparator7,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE, 38,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jSeparator6,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE, 38,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton2,
																GroupLayout.Alignment.BASELINE,
																GroupLayout.PREFERRED_SIZE, 37,
																GroupLayout.PREFERRED_SIZE))
										.addComponent(jPanel3, GroupLayout.Alignment.LEADING, 0,
												38, Short.MAX_VALUE)
										.addGroup(
												GroupLayout.Alignment.LEADING,
												jPanel2Layout.createSequentialGroup()
														.addGap(7)
														.addComponent(cardFindInput,
																GroupLayout.PREFERRED_SIZE, 23,
																GroupLayout.PREFERRED_SIZE)
														.addGap(0, 8, Short.MAX_VALUE)));
								jPanel2Layout.linkSize(SwingConstants.VERTICAL, new Component[] {
										jSeparator3, settingButton, jSeparator4, countButton });
							}
						}
					}
				}
				{
					rfidPanel = new JPanel();
					GridLayout rfidPanelLayout = new GridLayout(1, 1);
					rfidPanelLayout.setHgap(5);
					rfidPanelLayout.setVgap(5);
					rfidPanelLayout.setColumns(1);
					rfidPanel.setLayout(rfidPanelLayout);
					mainPanel.add(rfidPanel);
					rfidPanel.setPreferredSize(new java.awt.Dimension(821, 428));
					{
						jTabbedPane = new JTabbedPane();
						rfidPanel.add(jTabbedPane);
						jTabbedPane.setPreferredSize(new java.awt.Dimension(821, 413));
						{
							jScrollPane1 = new JScrollPane();
							jTabbedPane.addTab("进出校信息", null, jScrollPane1, null);
							jScrollPane1.setFont(new java.awt.Font("方正舒体", 0, 15));
							jScrollPane1.setPreferredSize(new java.awt.Dimension(816, 338));
							{
								TableModel rfidInfoTableModel =
										new DefaultTableModel(
												null,
												new String[] { "序号", "卡号", "时间", "首次经过区域",
														"最终经过区域", "被扫描次数", "结果" });
								rfidInfoTable = new JTable(rfidInfoTableModel) {
									public boolean isCellEditable(int row, int column) {
										return false;
									}
								};
								jScrollPane1.setViewportView(rfidInfoTable);
								rfidInfoTable.setRowHeight(20);
								// 设置第列宽度
								rfidInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
								rfidInfoTable.getTableHeader().getColumnModel().getColumn(2)
										.setPreferredWidth(150);
								// 居中显示
								DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) rfidInfoTable
										.getTableHeader().getDefaultRenderer();
								renderer.setHorizontalAlignment(renderer.CENTER);
								rfidInfoTable.getTableHeader().setDefaultRenderer(renderer);
								rfidInfoTable.setModel(rfidInfoTableModel);
								// 自动排序
								TableRowSorter sorter = new TableRowSorter(rfidInfoTable.getModel());
								rfidInfoTable.setRowSorter(sorter);
								// 双击事件
								rfidInfoTable.addMouseListener(new MouseListener() {

									@Override
									public void mouseClicked(MouseEvent e) {
										// TODO Auto-generated method stub
										if (e.getClickCount() == 2)
										{
											StudentDAO dao = new StudentDAO();
											String rfidcardid = (String) rfidInfoTable.getValueAt(
													rfidInfoTable.getSelectedRow(), 1);
											Studentrfid student = dao
													.getStudentbyCardID(rfidcardid);
											NewStudentInfo inst = new NewStudentInfo(null, student,
													true);
											inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
											inst.setLocationRelativeTo(null);
											inst.setVisible(true);
										}
									}

									@Override
									public void mouseEntered(MouseEvent e) {
										// TODO Auto-generated method stub

									}

									@Override
									public void mouseExited(MouseEvent e) {
										// TODO Auto-generated method stub

									}

									@Override
									public void mousePressed(MouseEvent e) {
										// TODO Auto-generated method stub

									}

									@Override
									public void mouseReleased(MouseEvent e) {
										// TODO Auto-generated method stub

									}

								});

							}
						}
					}
				}
				{
					logPanel = new JPanel();
					BorderLayout logPanelLayout = new BorderLayout();
					logPanel.setLayout(logPanelLayout);
					mainPanel.add(logPanel);
					logPanel.setPreferredSize(new java.awt.Dimension(821, 178));
					logPanel.setBorder(BorderFactory.createTitledBorder(""));
					logPanel.setMaximumSize(new java.awt.Dimension(32767, 200));
					{
						jTabbedPane1 = new JTabbedPane();
						logPanel.add(jTabbedPane1, BorderLayout.NORTH);
						jTabbedPane1.setPreferredSize(new java.awt.Dimension(834, 171));
						{
							jScrollPane2 = new JScrollPane();
							jTabbedPane1.addTab("日 志", null, jScrollPane2, null);
							jScrollPane2.setFont(new java.awt.Font("黑体", 1, 14));
							jScrollPane2.setPreferredSize(new java.awt.Dimension(804, 125));
							{
								logMessageBox = new JTextArea();

								jScrollPane2.setViewportView(logMessageBox);
								logMessageBox.setFont(new java.awt.Font("楷体", 0, 16));
								{
									jPopupMenu1 = new JPopupMenu();
									setComponentPopupMenu(logMessageBox, jPopupMenu1);
									{
										jMenuItem2 = new JMenuItem();
										jMenuItem2.setMnemonic('r');
										jMenuItem2.addActionListener(new ActionListener() {

											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method
												// stub
												logMessageBox.setText("");
											}

										});
										jPopupMenu1.add(jMenuItem2);
										jMenuItem2.setText("\u6e05\u7a7a\u65e5\u5fd7");
									}
								}
							}
						}
						{
							jScrollPane3 = new JScrollPane();
							jTabbedPane1.addTab("数据监控", null, jScrollPane3, null);
							jScrollPane3.setFont(new java.awt.Font("楷体", 0, 15));
							{
								TableModel dataMonitTableModel =
										new DefaultTableModel(
												new String[][] { { "入校人数", "0" }, { "出校人数", "0" },
														{ "未离开区域人数", "0" }, { "共计", "0" },
														{ "待处理队列长度", "0" } },
												new String[] { "数据项", "值" });
								dataMonitTable = new JTable(dataMonitTableModel) {
									public boolean isCellEditable(int row, int column) {
										return false;
									}
								};
								jScrollPane3.setViewportView(dataMonitTable);
								// dataMonitTable.setModel(dataMonitTableModel);
								// 设置第列宽度
								dataMonitTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
								dataMonitTable.getTableHeader().getColumnModel().getColumn(0)
										.setPreferredWidth(150);
								dataMonitTable.getTableHeader().getColumnModel().getColumn(1)
										.setPreferredWidth(850);
								// 居中显示
								DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) dataMonitTable
										.getTableHeader().getDefaultRenderer();
								renderer.setHorizontalAlignment(renderer.CENTER);
								// 行高
								dataMonitTable.setRowHeight(20);
								dataMonitTable.setPreferredSize(new java.awt.Dimension(806, 108));
								// 好吧。。。。设置背景颜色，显的牛逼些。。。。
								dataMonitTable.setDefaultRenderer(Object.class,
										new RowColorRenderer());
								dataMonitTable.setFont(new java.awt.Font("仿宋", 0, 15));
							}
						}
					}
				}
			}
			this.setSize(864, 722);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				jMenuBar1.setFont(new java.awt.Font("微软雅黑", 0, 15));
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("\u6587\u4ef6");
					{
						startMenu = new JMenuItem();
						jMenu3.add(startMenu);
						startMenu.setText("\u542f\u52a8");
						startMenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try
								{
									action.startButtonPerformeAction(evt, startMenu);
								} catch (StudentSafeException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("\u4fdd\u5b58\u8fdb\u51fa\u6821\u4fe1\u606f");
						saveMenuItem.setEnabled(false);
					}
					{
						clearRfidTable = new JMenuItem();
						jMenu3.add(clearRfidTable);
						clearRfidTable.setText("\u6e05\u7a7a\u8868\u683c\u5185\u5bb9");
						clearRfidTable.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try
								{
									action.clearRfidTablePerformeAction(evt, clearRfidTable);
								} catch (StudentSafeException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// TODO add your code for
								// clearRfidTable.actionPerformed
							}
						});
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("\u9000\u51fa");
						exitMenuItem.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								System.exit(0);

							}

						});
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("\u5de5\u5177");
					{
						pasteMenuItem = new JMenuItem();
						jMenu4.add(pasteMenuItem);
						pasteMenuItem.setText("\u8bfb\u5934\u63a2\u6d4b");
						pasteMenuItem.setEnabled(false);
					}
					{
						copyMenuItem = new JMenuItem();
						jMenu4.add(copyMenuItem);
						copyMenuItem.setText("\u77ed\u4fe1\u901a\u9053\u6d4b\u8bd5");
						copyMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try
								{
									action.messageChanelPerformeAction(evt, null);
								} catch (StudentSafeException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// TODO add your code for
								// copyMenuItem.actionPerformed
							}
						});
					}
					{
						jSeparator8 = new JSeparator();
						jMenu4.add(jSeparator8);
					}
					{
						manage = new JMenu();
						jMenu4.add(manage);
						manage.setText("\u6570\u636e\u7ba1\u7406");
						{
							jMenuItem4 = new JMenuItem();
							manage.add(jMenuItem4);
							jMenuItem4.setText("\u6559\u5e08\u7ba1\u7406");
							jMenuItem4.setBounds(0, 26, 132, 26);
							jMenuItem4.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									TeacherManage inst = new TeacherManage(null);
									inst.setLocationRelativeTo(null);
									inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									inst.setVisible(true);
								}
							});
						}
						{
							jSeparator9 = new JSeparator();
							manage.add(jSeparator9);
						}
						{
							jMenuItem3 = new JMenuItem();
							manage.add(jMenuItem3);
							jMenuItem3.setText("\u5b66\u751f\u7ba1\u7406");
							jMenuItem3.setBounds(0, 26, 132, 26);
							jMenuItem3.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									StudentInfoManage inst = new StudentInfoManage(null);
									inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									inst.setLocationRelativeTo(null);

									inst.setVisible(true);

								}

							});
						}
						{
							jSeparator10 = new JSeparator();
							manage.add(jSeparator10);
						}
						{
							jMenuItem5 = new JMenuItem();
							manage.add(jMenuItem5);
							jMenuItem5.setText("\u73ed\u7ea7\u7ba1\u7406");
							jMenuItem5.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									ClassManage inst = new ClassManage(null);
									inst.setLocationRelativeTo(null);
									inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									inst.setVisible(true);
								}
							});
						}
					}
					{
						jMenuItem1 = new JMenuItem();
						jMenu4.add(jMenuItem1);
						jMenuItem1.setText("\u5b66\u751f\u4fe1\u606f\u6279\u91cf\u5bfc\u5165");
						jMenuItem1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try
								{
									action.studentDataBatchProcess(evt, null);
								} catch (StudentSafeException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}
					{
						jSeparator1 = new JSeparator();
						jMenu4.add(jSeparator1);
						jSeparator1.setPreferredSize(new java.awt.Dimension(0, 2));
					}
					{
						settingMenuItem = new JMenuItem();
						jMenu4.add(settingMenuItem);
						settingMenuItem.setText("\u9996\u9009\u9879");
						settingMenuItem.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								try
								{
									action.settingButtonPerformeAction(e, null);
								} catch (StudentSafeException e1)
								{
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}

						});
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("\u5e2e\u52a9");
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("\u5173\u4e8e...");
						helpMenuItem.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								showAboutDialog();

							}

						});
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void printLog(final String message) {
		logMessageBox.append(message);
		// 这句话让LOG实时刷新到jtextarea上。
		// 月月光啊月月光 ~~ 涨工资啊涨工资~ ! ~~~ 有一个美丽的传说
		logMessageBox.paintImmediately(logMessageBox.getBounds());
		// logMessageBox.paintImmediately(logMessageBox.getBounds()) ;

	}

	public void showAboutDialog() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AboutFrame inst = new AboutFrame();
				inst.setLocationRelativeTo(null);
				// inst.setUndecorated(true);
				inst.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
				inst.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				inst.setVisible(true);
			}
		});
	}

	// 窗口关闭事件处理。关闭时释放资源
	// 资源释放完成后必须调用父类的processWindowEvent方法来关闭
	public void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{

			super.processWindowEvent(e);
			// 释放数据连接
			// HibernateUtil.relaseConnection() ;
			// 释放全部资源
			System.exit(0);

		}
	}
	
	private void searchRfid(){
		int rowCount = rfidInfoTable.getRowCount();
		DefaultTableModel model = (DefaultTableModel) rfidInfoTable
				.getModel();
		String cid;

		Rectangle reg;
		for (; searchIndex < rowCount; searchIndex++)
		{

			if (searchIndex == rfidInfoTable
					.getSelectionModel()
					.getAnchorSelectionIndex())
			{
				continue;
			}

			cid = model.getValueAt(searchIndex, 1).toString();
			if (cid.equals(cardFindInput.getText().trim()))
			{
				rfidInfoTable.getSelectionModel()
						.setSelectionInterval(searchIndex,
								searchIndex);
				reg = rfidInfoTable.getCellRect(searchIndex, 1,
						true);
				rfidInfoTable.scrollRectToVisible(reg);
				break;

			}

		}
		// 遍历整个表格后，searchIndex=
		// RowCount().归零
		if (searchIndex == rowCount)
		{
			searchIndex = 0;

		}
	}

	public static JTextArea getMessageBox() {
		return logMessageBox;
	}

	public static JMenuItem getStartMenu() {
		return startMenu;
	}

	public static JButton getStartButton() {
		return startButton;
	}

	public static JTable getRfidInfoTable() {
		return rfidInfoTable;
	}

	public static JTable getMonitDataTable() {
		return dataMonitTable;
	}

	private void countButtonActionPerformed(ActionEvent evt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				InOutSchoolStatistics inst = new InOutSchoolStatistics(null);
				inst.setLocationRelativeTo(null);
				inst.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				inst.setVisible(true);
			}
		});
	}

	/**
	 * Auto-generated method for setting the popup menu for a component
	 */
	private void setComponentPopupMenu(final java.awt.Component parent,
			final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				if (e.isPopupTrigger())
					menu.show(parent, e.getX(), e.getY());
			}
		});
	}

}

// 内部类 ，各行改变JTable颜色
class RowColorRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable t, Object value,
							boolean isSelected, boolean hasFocus, int row, int column) {
		// 分支判断条件可根据需要进行修改
		if (row % 2 == 0)
		{
			setBackground(new Color(206, 231, 255));
		} else
		{

			setBackground(new Color(255, 255, 255));
		}
		this.setHorizontalAlignment(CENTER);
		return super.getTableCellRendererComponent(t, value, isSelected,
							hasFocus, row, column);
	}
}