package com.reserve.view;

import java.util.ArrayList;
import java.util.Scanner;
import com.reserve.controller.ReserveController;
import com.reserve.model.vo.Member;
import com.reserve.model.vo.Reserve;
import com.reserve.model.vo.Room;


public class ReserveMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	private ReserveController rc = new ReserveController();
	int reservationNo= 0;
	
	
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
	
	
	
	public void mainMenu(int result) {
		System.out.println(result);
		while(true) {
			System.out.println("=== 숙박 프로그램 ===");
			System.out.println();
			System.out.println("1) 예약하기");// 인호
			System.out.println("2) 예약취소");// 연준
			System.out.println("3) 내 찜하기보기"); // 연준
			System.out.println("4) 리뷰쓰기");//인호
			System.out.println("5) 뒤로가기");
			System.out.print("입력 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1 : reserve(result);  break;
			case 2 : cancel(result); break;
			case 3 :   break;
			case 4 : listReserve(result);  break;
			case 5 :  return;
			default : System.out.println("잘못입력하셨습니다."); break;
			}
			
		}
		
	}
	
	public void reserve(int result) { // 예약하기 메뉴 눌렀을때 호출되는 메소드
		
		rc.allSelect();
		while(true) {
			System.out.println("== 예약하기 메뉴==");
			System.out.println();
			System.out.println("1) 선택하기"); // 인호
			System.out.println("2) 별점순으로 보기"); // 연준
			System.out.println("3) 이름순"); // 연준
			System.out.println("4) 가격순");// 연준
			System.out.println("5) 뒤로가기");
			System.out.print("입력 : ");
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1: hotelChoice(result);	break;
			case 2: rc.orderList(1); break;
			case 3: rc.orderList(2); break;
			case 4: rc.orderList(3); break;
			case 5:     return;
			default: System.out.println("잘못 입력했습니다."); break;
			}
		}
	}
	
	public void cancel(int result) {
		System.out.println("== 예약 취소 ==");
		System.out.println("== 현재 예약중인 방 ==");
		ArrayList<Reserve> list = rc.selectReserve(result);
		for(int i = 0; i<list.size();i++) {
			Reserve r = list.get(i);
			System.out.println("예약번호 : "+r.getReserveNo()+" 숙소번호 : "+
			r.getRoomNo()+" 숙소이름 : "+r.getRoomName()+" 예약시작일 : "+r.getStartDate()+" 예약종료일 : "+r.getEndDate());
		}
		
		System.out.print("예약번호 입력 : ");
		int reservNo = sc.nextInt();
		sc.nextLine();
		char ch = ' ';
		for(int i = 0; i<list.size(); i++) {
			if(reservNo == list.get(i).getReserveNo()) {
				System.out.println("예약 번호 : "+reservNo+"을 취소하시겠습니까? (y/n)");
				System.out.print("입력 : ");
				ch = sc.nextLine().toUpperCase().charAt(0);
				break;
			}
		}
		
		if(ch == 'Y') {
			rc.deleteReservation(reservNo);
			System.out.println("예약 취소가 완료됬습니다.");
		}else {
			System.out.println("이전으로 돌아갑니다");
		}
		
	}
	
	
	
	
	public void listReserve(int reserveNo) {
		System.out.println(reserveNo);
		System.out.println("== 리뷰메뉴 ==");
		rc.listReserve(reserveNo);
		
	}
	
	public void getReview(ArrayList<Reserve> list, int reserveNo) {
		int count = 0;
		for(Reserve r : list) {
			System.out.println(r);
		}
		System.out.println("리뷰를 쓰실 예약번호를 입력해주세요");
		System.out.print("입력 : ");
		int num = sc.nextInt();
		sc.nextLine();
		if(rc.overlapReview(num,reserveNo)>0) {
			System.out.println("이미 해당 예약룸에 리뷰를 작성하셨습니다.");
		}
		while(true) {
		for(Reserve r: list) {
			if(r.getReserveNo()==num && r.getState().equals("예약")) {
				reviewWrite(r);
			}
			count++;
			if(count == list.size()-1) {
				System.out.println("잘못 입력했습니다.");
				count = 0;
				return;
			}
		}
		}
	}
	
	public void reviewWrite(Reserve r) {
		while(true) {
		System.out.print("리뷰를 입력해주세요(300자이내) : ");
		String review = sc.nextLine();
		System.out.print("별점을 매겨주세요(1~5 사이 입력) : ");
		int rated = sc.nextInt();
		sc.nextLine();
	    rc.inputReview(r,review,rated);
		
		}
	}
	
	
	
	public void hotelChoice(int result) { // 호텔목록중 호텔 선택
		System.out.print("호텔 번호를 입력해주세요 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		rc.hotelChoice(num ,result);
	}
	
	public void reserveChoice(Room r, int result) {
		//선택한 호텔정보 출력
		while(true) {
	    System.out.println();
		System.out.println(r);
		System.out.println("1) 리뷰보기"); // 인호
		System.out.println("2) 예약하기"); // 인호
		System.out.println("3) 찜하기"); // 연준
		System.out.println("4) 뒤로가기");
		System.out.print("입력 : ");
		int num = sc.nextInt();
		sc.nextLine();
		switch(num) {
		case 1:rc.getReview(r.getRoomNo());  break;
		case 2: reserveDate(r ,result);    break;
		case 3:    break;
		case 4:    return;
		default:System.out.println("잘못 입력했습니다.");   break;
		}
		}
	}
	
	public void reserveDate(Room r , int result) {
		while(true) {
		System.out.println("== 예약 화면 ==");
		System.out.println();
		System.out.println("몇일 후로 예약하겠습니까? 일주일까지 예약가능! (1~7)의 숫자 입력 ]");
		System.out.println("0) 뒤로가기 ");
		System.out.print("입력 : ");
		int date = sc.nextInt();
		sc.nextLine();
		if(date>0&&date<8) {
			reservePayment(r,result,date);
		}else if(date==0){
			return;
		}else {
			System.out.println("잘못 입력하셨습니다. 옳바른 숫자를 입력해주세요");
		}
		}
	}
	
	public void reservePayment(Room r, int result,int date) {
		while(true) {
		System.out.println("== 결제 화면 ==");
		System.out.println();
		System.out.println("결제 방식을 선택해주세요");
		System.out.println("1) 카드결제");
		System.out.println("2) 카카오페이(카카오와 제휴 이벤트로 결제시 10%할인)");
		System.out.println("3) 네이버페이");
		System.out.println("4) 뒤로가기");
		System.out.print("입력 : ");
		int payment = sc.nextInt();
		sc.nextLine();
		int sum = r.getPrice();
		switch(payment) {
		case 1 : break;
		case 2 : sum = (int) (sum*0.9); break;
		case 3 : break;
		case 4 : return;
		default : System.out.println("잘못 입력했습니다."); continue;
		}
		System.out.println("결제 금액은 "+sum+"원 입니다. 결제하시겠습니까?");
		System.out.print("입력 (y/n) : ");
		char yn = sc.nextLine().toUpperCase().charAt(0);
		if(yn=='Y') {
			rc.reservePayment(r,result,date);
		}else if(yn=='N') {
			System.out.println("결제가 취소되었습니다. 메인 화면으로 돌아갑니다.");
			mainMenu(result);
		}else {
			System.out.println("잘못 입력했습니다.");
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
	public void orderList(ArrayList list) {
		for(int i =0; i<list.size(); i++) {
			Room r = (Room) list.get(i);
			System.out.println("숙소번호 : "+r.getRoomNo()+" 숙소이름 : "+r.getRoomName()
			+" 가격 : " + r.getPrice()+" 평점 : "+r.getRate());
		}
	}
	
	public void selectMessage(String message) {
		System.out.println(message);
	}
	
	//=============================== 응답화면 ============================================
	
	public void loginFail(String message) {
		System.out.println(message);
		System.out.println();
		login();
	}
	
	public void outputList(ArrayList list) {
		for(Object o : list) {
			System.out.println(o);
		}
	}
	
	public void noDate(String message) {
		System.out.println(message);
	}
	
	public void ReserveSuccess(String message,int result) {
		System.out.println(message);
		mainMenu(result);
	}
	
	public void ReserveFail(String message , int result) {
		System.out.println(message);
		mainMenu(result);
	}
	
	

}
