package com.reserve.model.vo;

import java.sql.Date;

public class Reserve {
	private int reserveNo;
	private String state;
	private int roomNo;
	private Date startDate;
	private Date endDate;
	private int reservationNo;
	
	public Reserve() {}

	public Reserve(int reserveNo, String state, int roomNo, Date startDate, Date endDate, int reservationNo) {
		super();
		this.reserveNo = reserveNo;
		this.state = state;
		this.roomNo = roomNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reservationNo = reservationNo;
	}

	public int getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	@Override
	public String toString() {
		return "Reserve [reserveNo=" + reserveNo + ", state=" + state + ", roomNo=" + roomNo + ", startDate="
				+ startDate + ", endDate=" + endDate + ", reservationNo=" + reservationNo + "]";
	}
	
	
}
