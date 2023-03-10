package com.reserve.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.reserve.controller.ReserveController;
import com.reserve.model.vo.Member;
import com.reserve.model.vo.Reserve;
import com.reserve.model.vo.Room;


public class ReserveMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	private ReserveController rc = new ReserveController();
	int reservationNo= 0;
	
	
	/**
	 * 전반적인 로그인 메서드
	 */
	public void login() { 
		
		while(true) {
			System.out.println("로그인 화면입니다.");
			System.out.println();
			System.out.println("1) 로그인하기"); //인호
			System.out.println("2) 회원가입하기.");//연준
			System.out.println("3) 프로그램 종료");
			System.out.print("입력 : ");
			int num = exception();
			sc.nextLine();
			switch(num) {	
			case 1 : inputLogin(); break;
			case 2 : signUp();break;
			case 3 : System.out.println("프로그램을 종료합니다"); return;
			default : System.out.println("잘못된 숫자를 입력했습니다.");  break;
			}
		}
	}
	
	/**
	 * 메인메뉴 출력 메서드
	 * @param result
	 */
	public void mainMenu(int result) { // 메인메뉴 매개변수 result는 회원번호
		while(true) {
			System.out.println("=== 숙박 프로그램 ===");
			System.out.println();
			System.out.println("1) 예약하기");// 인호
			System.out.println("2) 예약취소");// 연준
			System.out.println("3) 내 찜하기보기"); // 연준
			System.out.println("4) 리뷰쓰기");//인호
			System.out.println("5) 뒤로가기");
			System.out.print("입력 : ");
			int num = exception();
			sc.nextLine();
			switch(num) {
			case 1 : reserve(result);  break;
			case 2 : cancel(result); break;
			case 3 : zzim(result); break;
			case 4 : listReserve(result);  break;
			case 5 :  login(); break;
			default : System.out.println("잘못입력하셨습니다."); break;
			}
		}
	}
	
	
	/**
	 * 로그인시 필요한 값 입력받는 메서드
	 */
	public void inputLogin() { 
		System.out.println("== 숙박 프로그램 ==");
		System.out.println();
		System.out.print("아이디를 입력하세요 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String userPwd = sc.nextLine();
		
		rc.login(userId, userPwd);
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
		
		ArrayList<String> userids = rc.getUserNames();
		while(true) {
			System.out.print("아이디 입력 : ");
			userId = sc.nextLine();
			for(String s: userids) {
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
	
	/**
	 * 예약하기 메뉴 눌렀을 때 호출되는 메서드
	 * @param result
	 */
	public void reserve(int result) { 
		
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
			int num = exception();
			sc.nextLine();
			switch(num) {
			case 1: hotelChoice(result);break;
			case 2: rc.orderList(1); break;
			case 3: rc.orderList(2); break;
			case 4: rc.orderList(3); break;
			case 5: return;
			default: System.out.println("잘못 입력했습니다."); break;
			}
		}
	}
	
	/**
	 * 숙소 목록중 호텔번호 선택
	 * @param result
	 */
	public void hotelChoice(int result) { 
		System.out.print("숙소 번호를 입력해주세요 : ");
		int num = exception();
		sc.nextLine();
		
		rc.hotelChoice(num ,result);
	}
	
	/**
	 * 내찜목록에서 예약시 실행될 메서드
	 * @param result
	 * @param list
	 */
	public void hotelChoice(int result, ArrayList<Integer> list) { 
		System.out.print("숙소 번호를 입력해주세요 : ");
		int num = exception();
		sc.nextLine();
		
		for(int i: list) {
			if(num == i) {
				int num1 = rc.hotelChoice1(num ,result);
				System.out.println(num1);
				if(num1 == 2) {
					rc.deleteZzim(result,num);
				}
				return;
			}
		}
		System.out.println("찜목록에 없는 숙소입니다.");
	}
	
	/**
	 * 입력한 숙소번호가 실제 데이터에 있을시 실행되는 메서드
	 * 
	 */
	public int reserveChoice(Room r, int result) {
		int num = 0;
		//선택한 호텔정보 출력
		while(true) {
	    System.out.println();
		System.out.println(r);
		System.out.println("1) 리뷰보기"); // 인호
		System.out.println("2) 예약하기"); // 인호
		System.out.println("3) 찜하기"); // 연준
		System.out.println("4) 뒤로가기");
		System.out.print("입력 : ");
		num = exception();
		sc.nextLine();
		switch(num) {
		case 1:rc.getReview(r.getRoomNo());  break;
		case 2: 
			if(rc.overlapReserve(r,result)>0) {
				System.out.println("해당 방에 이미 예약되어있습니다.");
				num=0;
				continue;
			}
			num = reserveDate(r ,result);break;
		case 3: doZzim(r,result); break;
		case 4:    return 0;
		default:System.out.println("잘못 입력했습니다.");   break;
		}
		return num;
		}
	}
	
	/**
	 * 예약하기 선택시 날짜 선택 메서드
	 * @param r
	 * @param result
	 * @return
	 */
	public int reserveDate(Room r , int result) { // 예약일 정하기
		int num = 0;
		while(true) {
		System.out.println("== 예약 화면 ==");
		System.out.println();
		System.out.println("몇일 후로 예약하겠습니까? 일주일까지 예약가능! (1~7)의 숫자 입력 ]");
		System.out.println("0) 뒤로가기 ");
		System.out.print("입력 : ");
		int date = exception();
		sc.nextLine();
		if(date>0&&date<8) {
			num = reservePayment(r,result,date);
		}else if(date==0){
			return num;
		}else {
			System.out.println("잘못 입력하셨습니다. 옳바른 숫자를 입력해주세요");
		}
		return num;
		}
	}
	
	/**
	 * 예약하기 선택, 날짜 선택 후 결제방식 정하기
	 * @param r
	 * @param result
	 * @param date
	 * @return
	 */
	public int reservePayment(Room r, int result,int date) { // 예약시 결제방식 정하기
		int num = 0;
		while(true) {
		System.out.println("== 결제 화면 ==");
		System.out.println();
		System.out.println("결제 방식을 선택해주세요");
		System.out.println("1) 카드결제");
		System.out.println("2) 카카오페이(카카오와 제휴 이벤트로 결제시 10%할인)");
		System.out.println("3) 네이버페이");
		System.out.println("4) 뒤로가기");
		System.out.print("입력 : ");
		int payment = exception();
		sc.nextLine();
		int sum = r.getPrice();
		switch(payment) {
		case 1 : break;
		case 2 : sum = (int) (sum*0.9); break;
		case 3 : break;
		case 4 : return num;
		default : System.out.println("잘못 입력했습니다."); continue;
		}
		System.out.println("결제 금액은 "+sum+"원 입니다. 결제하시겠습니까?");
		System.out.print("입력 (y/n) : ");
		char yn = sc.nextLine().toUpperCase().charAt(0);
		if(yn=='Y') {
			rc.reservePayment(r,result,date);
			num = 2;
		}else if(yn=='N') {
			System.out.println("결제가 취소되었습니다. 메인 화면으로 돌아갑니다.");
			mainMenu(result);
		}else {
			System.out.println("잘못 입력했습니다.");
		}
		return num;
		}
	}
	
	/**
	 * 내찜보기 선택시 출력되는 메서드
	 * @param result
	 */
	public void zzim(int result) {
		rc.zzim(result);
		System.out.println("1. 예약하기  2. 뒤로가기");
		System.out.print("입력 : ");
		int num = exception();
		ArrayList<Integer> list = rc.getRoomNo(result);
		if(num == 1) {
			hotelChoice(result, list);
		
			
		}
	}
	
	
	/**
	 * 호텔선택 창에서 찜하기 메서드
	 * @param r
	 * @param result
	 */
	public void doZzim(Room r, int result) {
		ArrayList<Integer> list = rc.getRoomNo(result);
		
		for(int i: list) {
			if(i == r.getRoomNo()) {
				System.out.println("이미 찜이 되어있습니다.");
				return;
			}
		}
		rc.doZzim(r, result);
	}
	
	
	
	/**
	 * 예약 취소 메서드
	 * @param result
	 */
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
		int reservNo = exception();
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
	
	/**
	 * 예약 목록을 반환하는 메서드
	 * @param reserveNo
	 */
	public void listReserve(int reserveNo) {
		System.out.println(reserveNo);
		System.out.println("== 리뷰메뉴 ==");
		rc.listReserve(reserveNo);
		
	}
	
	/**
	 * 리뷰쓰기 메서드1
	 * @param list
	 * @param reserveNo
	 */
	public void getReview(ArrayList<Reserve> list, int reserveNo) { 
		int count = 0;
		int sum = 0;
		for(Reserve r : list) {
			System.out.println(r);
		}
		while(true) {
		System.out.println("리뷰를 쓰실 예약번호를 입력해주세요");
		System.out.println("0) 뒤로가기");
		System.out.print("입력 : ");
		int num = exception();
		sc.nextLine();
		for(Reserve r : list) {
			if(r.getReserveNo()==num) {
		if(rc.overlapReview(num,reserveNo,r.getRoomNo())>0) {
			System.out.println("이미 해당 예약룸에 리뷰를 작성하셨습니다.");
			sum = 1;
		}
			}
		}
		if(sum == 1) {
			sum = 0;
			continue;
		}
		if(num == 0) {
			return;
		}
		
		for(Reserve r: list) {
			if(r.getReserveNo()==num && r.getState().equals("예약")) {
				reviewWrite(r);
			}
			count++;
			if(count == list.size()) {
				System.out.println("잘못 입력했습니다.");
				count = 0;
				return;
			}
		}
		
		}
	}
	
	/**
	 * 리뷰쓰기 메서드2
	 * @param r
	 */
	public void reviewWrite(Reserve r) { // 리뷰쓰기
		while(true) {
		System.out.print("리뷰를 입력해주세요(300자이내) : ");
		String review = sc.nextLine();
		System.out.print("별점을 매겨주세요(1~5 사이 입력) : ");
		int rated = exception();
		sc.nextLine();
	    rc.inputReview(r,review,rated);
		
		}
	}
	
	/**
	 * 정수타입 예외처리 메서드
	 * @return
	 */
	public int exception() { //숫자 입력하지 않을 시 예외처리 메소드
		int num = 0;
		while(true) {
			try {
				num = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.print("숫자만 입력해주세요 :");
				sc.nextLine();
				continue;
			}
			break;
		}
		return num;
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
	}
	
	public void ReserveFail(String message , int result) {
		System.out.println(message);
	}
	public void ReserveSuccess1(String message,int result) {
		System.out.println(message);
	 mainMenu(result);
	}
	
	public void ReserveFail1(String message , int result) {
		System.out.println(message);
		mainMenu(result);
	}
	
	

}
