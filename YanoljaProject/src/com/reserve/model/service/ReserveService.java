package com.reserve.model.service;

import static com.reserve.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;



import com.reserve.model.vo.Member;
import com.reserve.model.vo.Reserve;
import com.reserve.controller.ReserveController;
import com.reserve.model.dao.ReserveDao;
import com.reserve.model.vo.Review;
import com.reserve.model.vo.Room;
import com.reserve.view.ReserveMenu;

public class ReserveService {
	
	
	public ArrayList<String> getUserNames(){
		Connection conn = getConnection();
		ArrayList<String> list = new ReserveDao().getUserNames(conn);
		close(conn);
		return list;
	}
	
	public ArrayList<Reserve> selectReserve(int reservationNo){
		Connection conn = getConnection();
		ArrayList<Reserve> list = new ReserveDao().selectReserve(conn, reservationNo);
		close(conn);
		return list;
	}
	
	public void deleteReservation(int reservNo) {
		Connection conn = getConnection();
		int result = new ReserveDao().deleteReservation(conn, reservNo);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	
	public ArrayList<Room> orderList(int num){
		Connection conn = getConnection();
		ArrayList<Room> list = new ReserveDao().orderList(conn, num);
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
	
	public ArrayList<Room> zzim(int result){
		Connection conn = getConnection();
		
		ArrayList<Room> list = new ReserveDao().zzim(conn, result);
		
		close(conn);
		return list;
	}
	
	public int doZzim(Room r, int result) {
		Connection conn = getConnection();
		int result1 = new ReserveDao().doZzim(conn, r, result);
		
		if(result1 >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result1;
	}
	
	public void deleteZzim(int result, int num) {
		Connection conn = getConnection();
		int result1 = new ReserveDao().deleteZzim(conn, result, num);
			
		if(result1 >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
	}
	
	public ArrayList<Integer> getRoomNo(int result){
		Connection conn = getConnection();
		ArrayList<Integer> list = new ReserveDao().getRoomNo(conn, result);
		
		close(conn);
		return list;
	}
	
	//===================강인호===========================

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
	
	public ArrayList<Reserve> listReserve(int reserveNo){
		Connection conn = getConnection();
		ArrayList<Reserve> list = new ReserveDao().listReserve(conn,reserveNo);
		close(conn);
		
		return list;
		
	}
	
	public int inputReview(Reserve r,String review, int rated) {
		Connection conn = getConnection();
		int result = new ReserveDao().inputReview(conn,r,review,rated);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int overlapReview(int num,int reserveNo,int roomNo) {
		Connection conn = getConnection();
		int result = new ReserveDao().overlapReview(conn,num,reserveNo,roomNo);
		close(conn);
		return result;
	}
	
	public int overlapReserve(Room r,int reservationNo) {
		Connection conn = getConnection();
		int result = new ReserveDao().overlapReserve(conn,r,reservationNo);
		close(conn);
		return result;
	}
}
