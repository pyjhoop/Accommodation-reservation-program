package com.reserve.model.vo;

public class Member {
	private int reservationNo;
	private String userName;
	private String userId;
	private String userPwd;
	private int guests;
	
	public Member() {}

	public Member(int reservationNo, String userName, String userId, String userPwd, int guests) {
		super();
		this.reservationNo = reservationNo;
		this.userName = userName;
		this.userId = userId;
		this.userPwd = userPwd;
		this.guests = guests;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getGuests() {
		return guests;
	}

	public void setGuests(int guests) {
		this.guests = guests;
	}

	@Override
	public String toString() {
		return "Member [reservationNo=" + reservationNo + ", userName=" + userName + ", userId=" + userId + ", userPwd="
				+ userPwd + ", guests=" + guests + "]";
	}
	
	
}
