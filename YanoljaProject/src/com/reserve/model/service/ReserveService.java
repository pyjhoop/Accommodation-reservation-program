package com.reserve.model.service;

import static com.reserve.common.JDBCTemplate.*;

import java.sql.Connection;

import com.reserve.model.dao.ReserveDao;

public class ReserveService {

	public int login(String userId,String userPwd) {
		Connection conn = getConnection();
		int result = new ReserveDao().login(conn,userId,userPwd);
		close(conn);
		
		return result;
	}
}
