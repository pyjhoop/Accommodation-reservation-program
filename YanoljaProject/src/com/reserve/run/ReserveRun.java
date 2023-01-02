package com.reserve.run;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.reserve.view.ReserveMenu;

public class ReserveRun {

	public static void main(String[] args) {
		ReserveMenu rm = new ReserveMenu();
		rm.rogin();
		
		
		
		/*
		Properties prop = new Properties();
		
		try {
			prop.store(new FileOutputStream("resources/driver.properties"), "");
			prop.storeToXML(new FileOutputStream("resources/query.xml"), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

}
