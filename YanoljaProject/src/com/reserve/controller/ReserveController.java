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
	 * 유저 아이디를 가져오는 메서드
	 * 이름을 잘못지음
	 * @return
	 */
	public ArrayList<String> getUserNames(){
		return new ReserveService().getUserNames();
	}
	
	
	/**
	 * 회원가입 메서드
	 * @param m
	 */
	public void signUp(Member m) {
		int result = new ReserveService().signUp(m);
		if(result >0) {
			new ReserveMenu().signUpMessage("== 회원가입에 성공했습니다 ==");
		}else {
			new ReserveMenu().signUpMessage("== 회원가입에 실패했습니다 ==");
		}
	}
	
	/**
	 * 현재 예약중인 방 반환
	 * @param reservationNo
	 * @return
	 */
	public ArrayList<Reserve> selectReserve(int reservationNo) {
		ArrayList<Reserve> list = new ReserveService().selectReserve(reservationNo);
		return list;
	}
	
	/**
	 * 예약중인 방 취소 
	 * @param reservNo
	 */
	public void deleteReservation(int reservNo) {
		new ReserveService().deleteReservation(reservNo);
	}
	
	/**
	 * 숙소 정렬하는 기능
	 * @param num
	 */
	public void orderList(int num) {
		ArrayList<Room> list = new ReserveService().orderList(num);
		if(list.isEmpty()) {
			new ReserveMenu().selectMessage("조회된 정보가 없습니다");
		}else {
			new ReserveMenu().orderList(list);
		}
	}
	
	/**
	 * 내찜 보기
	 * @param result
	 */
	public void zzim(int result) {
		ArrayList<Room> list = new ReserveService().zzim(result);
		if(list.isEmpty()) {
			new ReserveMenu().selectMessage("찜한 숙소가 없습니다.");
		}else {
			new ReserveMenu().outputList(list);
		}
	}
	/**
	 * 내가 찜한 방번호 반환
	 * @param result
	 * @return
	 */
	public ArrayList<Integer> getRoomNo(int result){
		ArrayList<Integer> list = new ReserveService().getRoomNo(result);
		return list;
	}
	
	/**
	 * 찜목록 삭제
	 * @param result
	 * @param num
	 */
	public void deleteZzim(int result,int num) {
		new ReserveService().deleteZzim(result, num);
	}
	
	/**
	 * 찜하기 기능 담당
	 * @param r
	 * @param result
	 */
	public void doZzim(Room r, int result) {
		int result1 = new ReserveService().doZzim(r,result);
		if(result1>0) {
			new ReserveMenu().selectMessage("찜 성공");
		}else {
			new ReserveMenu().selectMessage("찜 실패");
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
	
	public int hotelChoice(int num, int result) {
		Room r = new ReserveService().hotelChoice(num);
		int num1 = 0;
		if(r==null) {
			new ReserveMenu().noDate("존재하지 않는 객실입니다.");
		}else{
			num1 = new ReserveMenu().reserveChoice(r , result);
		}
		return num1;
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
			new ReserveMenu().ReserveSuccess("성공적으로 예약했습니다.", result);
		}else {
			new ReserveMenu().ReserveFail("예약에 실패했습니다.",result);
		}
	}
	
	public void listReserve(int reserveNo) {
		ArrayList<Reserve> list = new ReserveService().listReserve(reserveNo);
		
		if(list.isEmpty()) {
			
		}else {
			new ReserveMenu().getReview(list,reserveNo);
		}
	}
	
	public void inputReview(Reserve r,String review, int rated) {
		int result = new ReserveService().inputReview(r,review,rated);
		
		if(result > 0) {
			new ReserveMenu().ReserveSuccess("리뷰등록이 성공했습니다.", r.getReservationNo());
		}else {
			new ReserveMenu().ReserveFail("리뷰등록이 실패했습니다.", r.getReservationNo());
		}
		
	}
	
	public int overlapReview(int num,int reserveNo,int roomNo) {
		int result = new ReserveService().overlapReview(num,reserveNo,roomNo);
		
		return result;
	}
	
	public int overlapReserve(Room r,int reservationNo){
		int result = new ReserveService().overlapReserve(r, reservationNo);
		
		return result;
	}
}
