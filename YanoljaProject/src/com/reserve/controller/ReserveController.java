package com.reserve.controller;

import java.util.ArrayList;

import com.reserve.model.service.ReserveService;
import com.reserve.model.vo.Review;
import com.reserve.model.vo.Room;
import com.reserve.view.ReserveMenu;

public class ReserveController {

	public void login(String userId, String userPwd) {
		int result = new ReserveService().login(userId,userPwd);
		
		if(result>0) {
			new ReserveMenu().mainMenu();
		}else {
			new ReserveMenu().loginFail("아이디가 없거나 비밀번호를 잘못 입력하셨습니다.");
		}
	}
	
	public void allSelect() {
		ArrayList<Room> list = new ReserveService().allSelect();
		
		new ReserveMenu().outputList(list);
	}
	
	public void hotelChoice(int num) {
		Review r = new ReserveService().hotelChoice(num);
		
		if(r==null) {
			new ReserveMenu().noDate("존재하지 않는 객실입니다.");
		}
	}
}
