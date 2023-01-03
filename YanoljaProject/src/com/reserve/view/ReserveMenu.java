package com.reserve.view;

import java.util.ArrayList;
import java.util.Scanner;
import com.reserve.controller.ReserveController;
import com.reserve.model.vo.Member;
import com.reserve.model.vo.Room;


public class ReserveMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	private ReserveController rc = new ReserveController();
	
	
	public void login() {
		
		while(true) {
			System.out.println("로그인 화면입니다.");
			System.out.println();
			System.out.println("1) 로그인하기"); //인호
			System.out.println("2) 회원가입하기.");//연준
			System.out.println("3) 프로그램 종료");
			System.out.print("입력 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {	
			case 1 : inputLogin();    break;
			case 2 : signUp();break;
			case 3 : System.out.println("프로그램을 종료합니다"); return;
			default : System.out.println("잘못된 숫자를 입력했습니다.");  break;
			}
		}
	}
	
	public void inputLogin() { //login 입력시키는 메소드
		System.out.println("== 숙박 프로그램 ==");
		System.out.println();
		System.out.print("아이디를 입력하세요 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String userPwd = sc.nextLine();
		
		rc.login(userId, userPwd);
	}
	
	
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("=== 숙박 프로그램 ===");
			System.out.println();
			System.out.println("1) 예약하기");// 인호
			System.out.println("2) 예약취소");// 연준
			System.out.println("3) 내 찜하기보기"); // 연준
			System.out.println("4) 뒤로가기");
			System.out.print("입력 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1 : reserve();  break;
			case 2 :   break;
			case 3 :   break;
			case 4 :  return;
			default : System.out.println("잘못입력하셨습니다."); break;
			}
			
		}
		
	}
	
	public void reserve() { // 예약하기 메뉴 눌렀을때 호출되는 메소드
		
		while(true) {
			System.out.println("== 예약하기 메뉴==");
			System.out.println();
			rc.allSelect();
			System.out.println("1) 선택하기"); // 인호
			System.out.println("2) 별점순으로 보기"); // 연준
			System.out.println("3) 이름순"); // 연준
			System.out.println("4) 가격순");// 연준
			System.out.println("5) 뒤로가기");
			System.out.print("입력 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1: hotelChoice();	break;
			case 2:     break;
			case 3:     break;
			case 4:     break;
			case 5:     return;
			default: System.out.println("잘못 입력했습니다."); break;
			}
		}
	}
	
	public void hotelChoice() {
		System.out.print("호텔 번호를 입력해주세요 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		rc.hotelChoice(num);
	}
	
	public void reserveChoice(Room r) {
		//선택한 호텔정보 출력
		while(true) {
		System.out.println(r);
		System.out.println(r.getRoomNo());
		System.out.println("1) 리뷰보기");
		System.out.println("2) 예약하기"); // 인호
		System.out.println("3) 찜하기"); // 연준
		System.out.println("4) 뒤로가기");
		System.out.print("입력 : ");
		int num = sc.nextInt();
		sc.nextLine();
		switch(num) {
		case 1:/*if(rc.getReview(r.getRoomNo()).isEmpty()) {
			System.out.println("선택하신 호텔에는 리뷰가 존재하지 않습니다.");
		}else{
			System.out.println(rc.getReview(r.getRoomNo()));
		};*/ System.out.println(rc.getReview(r.getRoomNo()));  break;
		case 2:    break;
		case 3:    break;
		case 4:    return;
		default:System.out.println("잘못 입력했습니다.");   break;
		}
		}
	}
	
	
	
	/**
	 * 회원가입시 이름, 아이디, 비번을 입력받고 아이디 중복시 중복있다고 알려주는 메서드
	 * @author 박연준
	 */
	public void signUp() {
		boolean flag = false;
		System.out.println("=== 회원 가입 ===");
		System.out.print("\n이름 입력 : ");
		String userName = sc.nextLine();
		String userId = "";
		
		ArrayList<String> userNames = rc.getUserNames();
		System.out.println(userNames);
		while(true) {
			System.out.print("아이디 입력 : ");
			userId = sc.nextLine();
			for(String s: userNames) {
				if(s.equals(userId)) {
					System.out.println("이미 존재하는 아이디입니다. 다시 입력해주세요\n");
					break;
				}else {
					System.out.println("사용가능한 아이디입니다\n");
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		System.out.print("비밀번호 입력 : ");
		String pwd = sc.nextLine();
		System.out.print("\n예약 인원수 입력 : ");
		String guest = sc.nextLine();
		
		Member m = new Member(userName, userId, pwd, Integer.parseInt(guest));
		rc.signUp(m);
	}
	
	
	//====================== 메세지 출력=========================
	public void signUpMessage(String message) {
		System.out.println(message);
		
	}
	
	//=============================== 응답화면 ============================================
	
	public void loginFail(String message) {
		System.out.println(message);
		System.out.println();
		login();
	}
	
	public void outputList(ArrayList<Room> list) {
		for(Room r : list) {
			System.out.println(r);
		}
	}
	
	public void noDate(String message) {
		System.out.println(message);
	}

}
