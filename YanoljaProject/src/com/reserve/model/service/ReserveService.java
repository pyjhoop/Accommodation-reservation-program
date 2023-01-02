package com.reserve.model.service;

import static com.reserve.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.reserve.model.dao.ReserveDao;
import com.reserve.model.vo.Member;

public class ReserveService {
	/**
	 * DB의 아이디를 가져오는 메서드
	 * @author 박연준
	 * @return
	 */
	public ArrayList<String> getUserNames(){
		Connection conn = getConnection();
		ArrayList<String> list = new ReserveDao().getUserNames(conn);
		close(conn);
		return list;
	}
	
	public int signUp(Member m) {
		Connection conn = getConnection();
		int result = new ReserveDao().signUp(conn, m);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
