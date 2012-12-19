package com.zephyr.studentsafe.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainAPP {
	
	
	public static void main(String[] argvs) throws IOException{
		File file = new File("c:/xbwolf/.gitignore");
		FileOutputStream out = new FileOutputStream(file);
		out.write("s".getBytes());
	}

}
