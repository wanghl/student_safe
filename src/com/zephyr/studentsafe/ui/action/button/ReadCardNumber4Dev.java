package com.zephyr.studentsafe.ui.action.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import com.zephyr.studentsafe.ui.action.ImportDataToJTableAction;

public class ReadCardNumber4Dev implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {

		Random random = new Random();
		String rfic = Integer.toString(random.nextInt(999999));
		String m1 = Integer.toString(random.nextInt(999999));
		ImportDataToJTableAction.insertCardNumber(rfic, m1);
	}

}
