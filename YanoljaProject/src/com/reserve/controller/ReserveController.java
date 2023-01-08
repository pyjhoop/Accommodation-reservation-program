package com.reserve.controller;

import java.util.ArrayList;

import com.reserve.model.service.ReserveService;
import com.reserve.model.vo.Member;
import com.reserve.model.vo.Reserve;
import com.reserve.model.vo.Review;
import com.reserve.model.vo.Room;
import com.reserve.view.ReserveMenu;

public class ReserveController {
	
	//=========================박연준=========================

	/**
	 * DB에 있는 아이디를 가져오는 메서드
	 * @author 박연준
	 * @return
	 */
	public ArrayList<String> getUserNames(){
		return new ReserveService().getUserNames();
	}
	
	public void signUp(Member m) {
		int result = new ReserveService().signUp(m);
		if(result >0) {
			new ReserveMenu().signUpMessage("== 회원가입에 성공했습니다 ==");
		}else {
			new ReserveMenu().signUpMessage("== 회원가입에 실패했습니다 ==");
		}
	}
	
	public ArrayList<Reserve> selectReserve(int reservationNo) {
		ArrayList<Reserve> list = new ReserveService().selectReserve(reservationNo);
		return list;
	}
	
	public void deleteReservation(int reservNo) {
		new ReserveService().deleteReservation(reservNo);
	}
	
	public void orderList(int num) {
		ArrayList<Room> list = new ReserveService().orderList(num);
		if(list.isEmpty()) {
			new ReserveMenu().selectMessage("조회된 정보가 없습니다");
		}else {
			new ReserveMenu().orderList(list);
		}
	}
	
	
	//====================강인호===================================
	public void login(String userId, String userPwd) {
		int result = new ReserveService().login(userId,userPwd);
		
		if(result>0) {
			new ReserveMenu().mainMenu(result);
		}else {
			new ReserveMenu().loginFail("아이디가 없거나 비밀번호를 잘못 입력하셨습니다.");
		}
	}
	
	public void allSelect() {
		ArrayList<Room> list = new ReserveService().allSelect();
		
		new ReserveMenu().outputList(list);
	}
	
	public void hotelChoice(int num, int result) {
		Room r = new ReserveService().hotelChoice(num);
		
		if(r==null) {
			new ReserveMenu().noDate("존재하지 않는 객실입니다.");
		}else{
			new ReserveMenu().reserveChoice(r , result);
		}
	}
	
	public ArrayList<Review> getReview(int RoomNo) {
		ArrayList<Review> list = new ReserveService().getReview(RoomNo);
		
		if(list.isEmpty()) {
			new ReserveMenu().noDate("선택하신 호텔에는 리뷰가 존재하지 않습니다.");
		}else {
			new ReserveMenu().outputList(list);
		}
		
		return list;
	}
	
	public void reservePayment(Room r,int result,int date) {
		int result2 = new ReserveService().reservePayment(r,result,date);
		
		if(result2>0) {
			new ReserveMenu().ReserveSuccess("성공적으로 예약했습니다.", result2);
		}else {
			new ReserveMenu().ReserveFail("예약에 실패했습니다.",result2);
		}
	}
	
	public void listReserve(int reserveNo) {
		ArrayList<Reserve> list = new ReserveService().listReserve(reserveNo);
		
		if(list.isEmpty()) {
			
		}else {
			new ReserveMenu().getReview(list);
		}
	}
}
