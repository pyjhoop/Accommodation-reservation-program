package com.reserve.model.dao;

import static com.reserve.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReserveDao {

	private Properties prop = new  Properties();
	
	public ReserveDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
