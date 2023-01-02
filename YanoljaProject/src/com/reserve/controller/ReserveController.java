package com.reserve.controller;

import java.util.ArrayList;

import com.reserve.model.service.ReserveService;
import com.reserve.model.vo.Member;
import com.reserve.view.ReserveMenu;

public class ReserveController {
	
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
}
