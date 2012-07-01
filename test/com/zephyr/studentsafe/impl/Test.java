/**
 * Test.java
 * com.zephyr.studentsafe.impl
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2010-9-10 		lenovo
 *
 * Copyright (c) 2010, TNT All Rights Reserved.
*/

package com.zephyr.studentsafe.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.zephyr.studentsafe.bo.Student;

/**
 * ClassName:Test
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 *
 * @author   lenovo
 * @version  
 * @since    Ver 1.1
 * @Date	 2010-9-10		下午03:23:54
 *
 * @see 	 
 */
public class Test {
	
	public static void main(String[] argvs) throws IOException{
		Socket s = new Socket("localhost",3030);
		OutputStream o = s.getOutputStream();
		InputStream in = s.getInputStream();
		
		o.write("sssss".getBytes());
		if(in.available() > 0){
		   byte[] b = new byte[in.available()];
		   System.out.println("收到服务端返回:" + new String(b,"GBK"));
		}
		in.close();
		o.close();
	}

}

