package com.reserve.model.service;

import static com.reserve.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;



import com.reserve.model.vo.Member;
import com.reserve.controller.ReserveController;
import com.reserve.model.dao.ReserveDao;
import com.reserve.model.vo.Review;
import com.reserve.model.vo.Room;
import com.reserve.view.ReserveMenu;

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
	
	public Room hotelChoice(int num) {
		Connection conn = getConnection();
		Room r = new ReserveDao().hotelChoice(conn, num);
		close(conn);
		
		return r;
		

	}
	
	public ArrayList<Review> getReview(int RoomNo){
		Connection conn = getConnection();
		ArrayList<Review> list = new ReserveDao().getReview(conn, RoomNo);
		close(conn);
		
		
		return list;
	}
	
	public int reservePayment(Room r,int result,int date) {
		Connection conn = getConnection();
		int result2 = new ReserveDao().reservePayment(conn,r,result,date);
		if(result2>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result2;
	}
}
