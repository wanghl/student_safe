/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ContactEditorUI.java
 *
 * Created on 2010-11-2, 22:02:44
 */

package com.zephyr.studentsafe.ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JWindow;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.zephyr.studentsafe.bo.Constants;
import com.zephyr.studentsafe.exception.StudentSafeException;
import com.zephyr.studentsafe.serialport.ReaderCheck;
import com.zephyr.studentsafe.ui.action.button.ButtonsAction;
import com.zephyr.studentsafe.ui.action.button.IButtonsAction;
import com.zephyr.studentsafe.ui.action.menu.PopMenuClearAction;
import com.zephyr.studentsafe.ui.action.menu.PopMenuSaveasFileAction;
import com.zephyr.studentsafe.util.ReaderLogPaserFromFile;
import com.zephyr.studentsafe.util.StudentSafeUtil;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 *
 * @author lenovo
 */
public class MainFrame extends javax.swing.JFrame {
	
	private static final Logger log = Logger.getLogger(MainFrame.class);
	
	private IButtonsAction action = new ButtonsAction() ;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


    /** Creates new form ContactEditorUI 
     * @throws StudentSafeException */
    public MainFrame() throws StudentSafeException {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * @throws StudentSafeException 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws StudentSafeException {

     //   BoxLayout jToolBar1Layout = new BoxLayout(jToolBar1, javax.swing.BoxLayout.X_AXIS);
     GroupLayout layout = new GroupLayout((JComponent)getContentPane());
        getContentPane().setLayout(layout);
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setTitle("\u897f\u90fd\u5b66\u6821\u5bb6\u6821\u4e92\u52a8\u5e73\u53f0");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        {
        	jScrollPane1 = new javax.swing.JScrollPane();
        	jTabbedPane1.addTab("��־", null, jScrollPane1, null);
        	{
        		jTextArea1 = new javax.swing.JTextArea();
        		jScrollPane1.setViewportView(jTextArea1);
        		jTextArea1.setColumns(20);
        		jTextArea1.setRows(5);
        		jTextArea1.setOpaque(false);
        		jTextArea1.setFont(new java.awt.Font("����",0,14));
        		{
        			jPopupMenu1 = new JPopupMenu();
        			setComponentPopupMenu(jTextArea1, jPopupMenu1);
        			{
        				clear = new JMenuItem();
        				clear.setMnemonic('r') ;
        				clear.addActionListener(new PopMenuClearAction());
        				jPopupMenu1.add(clear);
        				clear.setText("��� (R)");
        				javax.swing.JSeparator sp = new javax.swing.JSeparator();
        				jPopupMenu1.add(sp);
        				JMenuItem saveas = new JMenuItem();
        				saveas.setText("����Ϊ�ļ� (S)");
        				saveas.setMnemonic('s');
        				saveas.addActionListener(new PopMenuSaveasFileAction());
        				jPopupMenu1.add(saveas);
        				
        			}
        		}
        	}
        }
        {
        	jPanel1 = new JPanel();
        	jTabbedPane1.addTab("���ŷ��Ͳ���", null, jPanel1, null);
        	jTabbedPane1.addTab("�豸���", null, getJScrollPane2(), null);
        	GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
        	jPanel1.setLayout(jPanel1Layout);
        	{
        		jPanel2 = new JPanel();
        		GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
        		jPanel2.setLayout(jPanel2Layout);
        		jPanel2.setBorder(BorderFactory.createTitledBorder("�ֻ��ż���������"));
        		{
        			jLabel1 = new JLabel();
        			jLabel1.setText("\u624b\u673a\u53f7");
        		}
        		{
        			jLabel8 = new JLabel();
        			jLabel8.setText("\u53ef\u8f93\u5165\u591a\u4e2a\u624b\u673a\u53f7\uff0c\u53f7\u7801\u4e4b\u95f4\u7528\u9017\u53f7\u9694\u5f00\r\n");
        		}
        		{
        			mobilesInput = new JTextField();
        		}
        		{
        			jLabel2 = new JLabel();
        			jLabel2.setText("\u77ed\u4fe1\u5185\u5bb9");
        		}
        		{
        			msgContentInput = new JTextArea();
        			msgContentInput.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        		}
        		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap(26, 26)
        			.addGroup(jPanel2Layout.createParallelGroup()
        			    .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        			.addGroup(jPanel2Layout.createParallelGroup()
        			    .addGroup(jPanel2Layout.createSequentialGroup()
        			        .addComponent(msgContentInput, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
        			    .addGroup(jPanel2Layout.createSequentialGroup()
        			        .addComponent(mobilesInput, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
        			    .addGroup(jPanel2Layout.createSequentialGroup()
        			        .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(132, Short.MAX_VALUE));
        		jPanel2Layout.setVerticalGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			    .addComponent(mobilesInput, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(jLabel1, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        			.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        			.addGroup(jPanel2Layout.createParallelGroup()
        			    .addComponent(msgContentInput, GroupLayout.Alignment.LEADING, 0, 75, Short.MAX_VALUE)
        			    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
        			        .addGap(24)
        			        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        			        .addGap(0, 30, Short.MAX_VALUE)))
        			.addContainerGap());
        	}
        	{
        		jPanel3 = new JPanel();
        		GroupLayout jPanel3Layout = new GroupLayout((JComponent)jPanel3);
        		jPanel3.setLayout(jPanel3Layout);
        		jPanel3.setBorder(BorderFactory.createTitledBorder("MAS����������Ϣ"));
        		{
        			jLabel3 = new JLabel();
        			jLabel3.setText("IP\u5730\u5740");
        		}
        		{
        			ipAddInput = new JTextField();
        			ipAddInput.setText(StudentSafeUtil.getStringValue(Constants.MAS_IP));
        		}
        		{
        			jLabel4 = new JLabel();
        			jLabel4.setText("\u7528\u6237\u540d");
        		}
        		{
        			userNameInput = new JTextField();
        			userNameInput.setText(StudentSafeUtil.getStringValue(Constants.MAS_USER_NAME));
        		}
        		{
        			jLabel5 = new JLabel();
        			jLabel5.setText("API");
        		}
        		{
        			jLabel6 = new JLabel();
        			jLabel6.setText("\u5bc6\u7801");
        		}
        		{
        			apiInput = new JTextField();
        			apiInput.setText(StudentSafeUtil.getStringValue(Constants.MAS_APP));
        		}
        		{
        			dbNameInput = new JTextField();
        			dbNameInput.setText(StudentSafeUtil.getStringValue(Constants.MAS_DB_NAME));
        		}
        		{
        			jLabel7 = new JLabel();
        			jLabel7.setText("\u6570\u636e\u5e93");
        		}
        		{
        			passwordInput = new JPasswordField();
        			passwordInput.setText(StudentSafeUtil.getStringValue(Constants.MAS_USER_PASSWORD));
        		}
        		{
        			initButton = new JButton();
        			initButton.setText("\u521d\u59cb\u5316");
        			initButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								action.initButtonPerformeAction(e, initButton) ;
							} catch (StudentSafeException e1) {
								try {
									MessageWindow.show(e1.getLocalizedMessage(), null);
									throw e1;
								} catch (StudentSafeException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
							}
						}
        				
        			});
        		}
        		{
        			sendMsgButton = new JButton();
        			sendMsgButton.setText("\u53d1\u9001");
        			sendMsgButton.setEnabled(false);
        			sendMsgButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								action.sendButtonPerformeAction(e, sendMsgButton) ;
							} catch (StudentSafeException e1) {
								try {
									MessageWindow.show(e1.getLocalizedMessage(), null);
									throw e1;
								} catch (StudentSafeException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
							}
						}
        				
        			});
        		}
        		{
        			receiveRPTButton = new JButton();
        			receiveRPTButton.setText("\u63a5\u6536\u56de\u6267");
        			receiveRPTButton.setEnabled(false);
        			receiveRPTButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								action.receiveRPTButtonPerformeAction(e, receiveRPTButton) ;
							} catch (StudentSafeException e1) {
								try {
									MessageWindow.show(e1.getLocalizedMessage(), null);
									throw e1;
								} catch (StudentSafeException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
							}
						}
        				
        			});
        		}
        		{
        			relaseButton = new JButton();
        			relaseButton.setText("\u91ca\u653e\u8fde\u63a5");
        			relaseButton.setEnabled(false);
        			relaseButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								action.relaseButtonPerformeAction(e, relaseButton) ;
							} catch (StudentSafeException e1) {
								try {
									MessageWindow.show(e1.getLocalizedMessage(), null);
									throw e1;
								} catch (StudentSafeException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
							}
						}
        				
        			});
        		}
        		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createSequentialGroup()
        			.addContainerGap(27, 27)
        			.addGroup(jPanel3Layout.createParallelGroup()
        			    .addComponent(jLabel7, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        			    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        			        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        			        .addGap(0, 17, GroupLayout.PREFERRED_SIZE))
        			    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        			        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
        			        .addGap(8)))
        			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        			.addGroup(jPanel3Layout.createParallelGroup()
        			    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        			        .addComponent(dbNameInput, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        			        .addGap(28))
        			    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        			        .addComponent(apiInput, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        			        .addGap(28))
        			    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        			        .addComponent(ipAddInput, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        			        .addGap(28))
        			    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        			        .addComponent(initButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
        			        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        			        .addComponent(sendMsgButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
        			.addGroup(jPanel3Layout.createParallelGroup()
        			    .addGroup(jPanel3Layout.createSequentialGroup()
        			        .addGroup(jPanel3Layout.createParallelGroup()
        			            .addComponent(jLabel4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        			            .addComponent(jLabel6, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
        			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        			        .addGroup(jPanel3Layout.createParallelGroup()
        			            .addComponent(userNameInput, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        			            .addComponent(passwordInput, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
        			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
        			    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        			        .addGap(0, 14, GroupLayout.PREFERRED_SIZE)
        			        .addComponent(receiveRPTButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
        			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        			        .addComponent(relaseButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(59, 59));
        		jPanel3Layout.setVerticalGroup(jPanel3Layout.createSequentialGroup()
        			.addContainerGap(22, 22)
        			.addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			    .addComponent(userNameInput, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(jLabel4, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(ipAddInput, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(jLabel3, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
        			.addGap(25)
        			.addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			    .addComponent(passwordInput, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(jLabel6, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(apiInput, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(jLabel5, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
        			.addGap(22)
        			.addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			    .addComponent(dbNameInput, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(jLabel7, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, Short.MAX_VALUE)
        			.addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        			    .addComponent(receiveRPTButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(relaseButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(initButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        			    .addComponent(sendMsgButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(6));
        	}
        	jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
        		.addContainerGap(19, 19)
        		.addGroup(jPanel1Layout.createParallelGroup()
        		    .addGroup(jPanel1Layout.createSequentialGroup()
        		        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE))
        		    .addGroup(jPanel1Layout.createSequentialGroup()
        		        .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE)))
        		.addContainerGap(32, Short.MAX_VALUE));
        	jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
        		.addContainerGap()
        		.addComponent(jPanel2, 0, 193, Short.MAX_VALUE)
        		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        		.addComponent(jPanel3, 0, 216, Short.MAX_VALUE)
        		.addContainerGap());
        }
        {
        	jPanel4 = new JPanel();
        	GridLayout jPanel4Layout = new GridLayout(1, 1);
        	jPanel4Layout.setHgap(5);
        	jPanel4Layout.setVgap(5);
        	jPanel4Layout.setColumns(1);
        	jPanel4.setLayout(jPanel4Layout);
        	{
        		jToolBar1 = new JToolBar();
        		jPanel4.add(jToolBar1);
        		jToolBar1.setPreferredSize(new java.awt.Dimension(624, 53));
        		{
        			jPanel5 = new JPanel();
        			GridLayout jPanel5Layout = new GridLayout(1, 1);
        			jPanel5Layout.setHgap(5);
        			jPanel5Layout.setVgap(5);
        			jPanel5Layout.setColumns(1);
        			jPanel5.setLayout(jPanel5Layout);
        			jToolBar1.add(jPanel5);
        			jPanel5.setPreferredSize(new java.awt.Dimension(619, 44));
        			{
        				jPanel6 = new JPanel();
        				GroupLayout jPanel6Layout = new GroupLayout((JComponent)jPanel6);
        				jPanel6.setLayout(jPanel6Layout);
        				jPanel5.add(jPanel6);
        				jPanel6.setPreferredSize(new java.awt.Dimension(611, 49));
        				jPanel6.setEnabled(false);
        				{
        					startButton = new JButton();
        					startButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/ui/BWS_010.png")));
        					startButton.setToolTipText("\u542f\u52a8\u7a0b\u5e8f");
        					startButton.addActionListener(new ActionListener() {
        						public void actionPerformed(ActionEvent evt) {
        							try {
										action.startButtonPerformeAction(evt, startButton);
									} catch (StudentSafeException e) {
										try {
											MessageWindow.show(e.getLocalizedMessage(), null);
											throw e;
										} catch (StudentSafeException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
        						}
        					});
        				}
        				{
        					jSeparator1 = new JSeparator();
        					jSeparator1.setOrientation(SwingConstants.VERTICAL);
        				}
        				{
        					setingButton = new JButton();
        					setingButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/ui/BWS_001.png")));
        					setingButton.setToolTipText("\u8bbe\u7f6e");
        					setingButton.addActionListener(new ActionListener(){
        						public void actionPerformed(ActionEvent evt){
        							try {
										action.settingButtonPerformeAction(evt, setingButton);
									} catch (StudentSafeException e) {
										try {
											MessageWindow.show(e.getLocalizedMessage(), null);

											throw e;
										} catch (StudentSafeException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
									}
        						}
        					});
        				}
        				{
        					helpButton = new JButton();
        					helpButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("com/zephyr/studentsafe/ui/BWS_068.png")));
        					helpButton.setToolTipText("\u5e2e\u52a9");
        					helpButton.addActionListener(new ActionListener(){
								@Override
								public void actionPerformed(ActionEvent e) {
									try {
										action.helpButtonPerformeAction(e, helpButton);
									} catch (StudentSafeException e1) {
										try {
											MessageWindow.show(e1.getLocalizedMessage(), null);

											throw e1;
										} catch (StudentSafeException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
									}
									
								}
        					
        					});
        				}
        				{
        					jSeparator2 = new JSeparator();
        					jSeparator2.setOrientation(SwingConstants.VERTICAL);
        				}
        				jPanel6Layout.setHorizontalGroup(jPanel6Layout.createSequentialGroup()
        					.addComponent(startButton, 0, 59, Short.MAX_VALUE)
        					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					.addComponent(setingButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					.addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
        					.addComponent(helpButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(402, 402));
        				jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        					.addComponent(startButton, GroupLayout.Alignment.BASELINE, 0, 44, Short.MAX_VALUE)
        					.addComponent(jSeparator1, GroupLayout.Alignment.BASELINE, 0, 44, Short.MAX_VALUE)
        					.addComponent(setingButton, GroupLayout.Alignment.BASELINE, 0, 44, Short.MAX_VALUE)
        					.addComponent(jSeparator2, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
        					.addComponent(helpButton, GroupLayout.Alignment.BASELINE, 0, 44, Short.MAX_VALUE));
        			}
        		}
        	}
        }
        layout.setVerticalGroup(layout.createSequentialGroup()
        	.addContainerGap()
        	.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        	.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        	.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE)
        	.addContainerGap(24, Short.MAX_VALUE));
        layout.setHorizontalGroup(layout.createSequentialGroup()
        	.addContainerGap()
        	.addGroup(layout.createParallelGroup()
        	    .addComponent(jTabbedPane1, GroupLayout.Alignment.LEADING, 0, 624, Short.MAX_VALUE)
        	    .addComponent(jPanel4, GroupLayout.Alignment.LEADING, 0, 624, Short.MAX_VALUE))
        	.addContainerGap());

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //�õ��豸�б� ��������
    
    public static DefaultMutableTreeNode  flashSeail() throws StudentSafeException{
    	DefaultMutableTreeNode root = new DefaultMutableTreeNode("�����豸");
		//ϵͳ��ǰ�����б�
		DefaultMutableTreeNode sealPorts = new DefaultMutableTreeNode("ϵͳ�����б�");
		for(String port : ReaderCheck.getSeralPortListA()){
			sealPorts.add(new DefaultMutableTreeNode(port));
		}
		root.add(sealPorts);
		//�Ķ����豸�б�
		DefaultMutableTreeNode readers = new DefaultMutableTreeNode("�Ķ����б�");
		for(String desc : ReaderCheck.check()){
			readers.add(new DefaultMutableTreeNode(desc));
		}
		root.add(readers);
		return root ;
    }
   
    public static JTextArea getJTextArea(){
    	return jTextArea1;
    }
    
    public static void print(final String message){
    	SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				jTextArea1.append(message);
				
			}
			
		});
    }
    public static void clear(final String message){
    	SwingUtilities.invokeLater(new Runnable(){
    		@Override
    		public void run(){
    			jTextArea1.setText(" ");
    		}
    	});
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	try{
                MainFrame main = new MainFrame();
                main.setLocationRelativeTo(null);
                main.setVisible(true);

              //ÿ�����TEXTAREA����
    			JobDetail j = new JobDetail("clear","group2",FrameClear.class);
    			CronTrigger c = new CronTrigger("t1","t1");
    			CronExpression ce = new CronExpression("0 0 12,16,23 * * ?");
    			c.setCronExpression(ce);
    			SchedulerFactory s = new StdSchedulerFactory();
    			Scheduler t = s.getScheduler();
    			t.scheduleJob(j, c);
    			t.start() ;
    			

    			//ÿ��8��14��19��ͳ��©�����
    			JobDetail logdetail = new JobDetail("logjob1","loggroup1",ReaderLogPaserFromFile.class);
    			
    			CronTrigger logcronTrigger = new CronTrigger("logtr1","logtg1");
    			CronExpression logcexp = new CronExpression("0 05 8,15,19 ? * MON-FRI");
    			logcronTrigger.setCronExpression(logcexp);
    			SchedulerFactory logsf = new StdSchedulerFactory();
    			Scheduler logtt = logsf.getScheduler();
    			logtt.scheduleJob(logdetail, logcronTrigger);
    			logtt.start() ;
    			
            	}catch (StudentSafeException e){
            		log.error(e.getLocalizedMessage());
            		MessageWindow.show(e.getLocalizedMessage(), null);
            		System.exit(1);
            	}catch (Exception e){
            		log.error(e.getLocalizedMessage());
            		MessageWindow.show(e.getLocalizedMessage(), null);
            		System.exit(1);
            	}
            }
        });
    }
    
    /**
    * Auto-generated method for setting the popup menu for a component
    */
    private void setComponentPopupMenu(final java.awt.Component parent, final javax.swing.JPopupMenu menu) {
    	parent.addMouseListener(new java.awt.event.MouseAdapter() {
    		public void mousePressed(java.awt.event.MouseEvent e) {
    			if(e.isPopupTrigger())
    				menu.show(parent, e.getX(), e.getY());
    		}
    		public void mouseReleased(java.awt.event.MouseEvent e) {
    			if(e.isPopupTrigger())
    				menu.show(parent, e.getX(), e.getY());
    		}
    	});
    }
    
    private JScrollPane getJScrollPane2() throws StudentSafeException {
    	if(jScrollPane2 == null) {
    		jScrollPane2 = new JScrollPane();
    		jScrollPane2.setViewportView(getJTree1());
    	}
    	return jScrollPane2;
    }
    
    private JTree getJTree1() {
    	if(jTree1 == null) {
    		try {
				jTree1 = new JTree(flashSeail());
				
				
			} catch (StudentSafeException e) {
				MessageWindow.show(e.getLocalizedMessage(), null) ;
			}
    	}
    	return jTree1;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    public  static JTextField userNameInput;
    public  static JTextField apiInput;
    public  static JTextField dbNameInput;
    public  static JPasswordField passwordInput;
    private JSeparator jSeparator2;
    public  static JButton helpButton;
    public static JButton setingButton;
    private JSeparator jSeparator1;
    public static JButton startButton;
    private JPanel jPanel6;
    private JPanel jPanel5;
    private JToolBar jToolBar1;
    private JPanel jPanel4;
    public static JButton relaseButton;
    public static JButton receiveRPTButton;
    public static JButton sendMsgButton;
    public static JButton initButton;
    private JTree jTree1;
    private JButton jButton1;
    private JScrollPane jScrollPane2;
    private JMenuItem clear;
    private JPopupMenu jPopupMenu1;
    private JLabel jLabel8;
    public static JTextArea msgContentInput;
    private JLabel jLabel7;
    private JLabel jLabel6;
    private JLabel jLabel5;
    private JLabel jLabel4;
    public  static JTextField ipAddInput;
    private JLabel jLabel3;
    private JPanel jPanel3;
    private JLabel jLabel2;
    public static JTextField mobilesInput;
    private JLabel jLabel1;
    private JPanel jPanel2;
    private JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static  javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}