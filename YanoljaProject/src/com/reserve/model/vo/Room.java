package com.reserve.model.vo;

public class Room {
	private int roomNo;
	private String roomName;
	private int capacity;
	private String type;
	private String location;
	private int price;
	private double rate;
	
	

	public Room(int roomNo, String roomName, int price, double rate) {
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.price = price;
		this.rate = rate;
	}

	public Room() {}

	public Room(int roomNo, String roomName, int capacity, String type, String location, int price) {
		super();
		this.roomNo = roomNo;
		this.roomName = roomName;
		this.capacity = capacity;
		this.type = type;
		this.location = location;
		this.price = price;
	}

	public double getRate() {
		return rate;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}

	
	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "호텔 번호 : " + roomNo + ", 숙소 이름 : " + roomName + ", 수용 인원 : " + capacity + ", 숙소 타입 :" + type
				+ ", 위치 : " + location + ", 가격 :" + price + "원";
	}
	
	
}
