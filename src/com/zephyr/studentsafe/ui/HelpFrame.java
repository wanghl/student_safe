package com.zephyr.studentsafe.ui;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;


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
public class HelpFrame extends javax.swing.JFrame implements HyperlinkListener {
	private JScrollPane jScrollPane1;
	private JEditorPane jEditorPane1;

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		
	}
	
	public HelpFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, BorderLayout.CENTER);
				{
					
					jEditorPane1 = new JEditorPane();
					
					jScrollPane1.setViewportView(jEditorPane1);
					jEditorPane1.setContentType("text/html");
					jEditorPane1.setEditable(false);
					jEditorPane1.addHyperlinkListener(this);
					
					
					
					InputStream isn = getClass().getResourceAsStream("lounge.html");
					byte[] b2 = new byte[isn.available()];
					isn.read(b2);
					
					jEditorPane1.setPage(getClass().getResource("lounge.html"));
					
				}
			}
			pack();
			this.setSize(688, 605);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		try {
			if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
			jEditorPane1.setPage(e.getURL());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
