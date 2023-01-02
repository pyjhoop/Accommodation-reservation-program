package com.reserve.model.service;

import static com.reserve.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.reserve.model.dao.ReserveDao;
import com.reserve.model.vo.Room;

public class ReserveService {

	public int login(String userId,String userPwd) {
		Connection conn = getConnection();
		int result = new ReserveDao().login(conn,userId,userPwd);
		close(conn);
		
		return result;
	}
	
	public ArrayList<Room> allSelect(){
		Connection conn = getConnection();
		ArrayList<Room> list = new ReserveDao().allSelect(conn);
		close(conn);
		
		return list;
	}
}
