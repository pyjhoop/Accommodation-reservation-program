package com.reserve.model.vo;

public class Review extends Room{
	private int reviewNo;
	private int roomNo;
	private int reservatioNo;
	private String review;
	private int rated;
	
	public Review() {}

	public Review(int roomNo, String roomName, int capacity, String type, String location, int price,int reviewNo,String review, int rated) {
		super(roomNo,roomName,capacity,type,location,price);
		this.reviewNo = reviewNo;
		this.review = review;
		this.rated = rated;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getReservatioNo() {
		return reservatioNo;
	}

	public void setReservatioNo(int reservatioNo) {
		this.reservatioNo = reservatioNo;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRated() {
		return rated;
	}

	public void setRated(int rated) {
		this.rated = rated;
	}

	@Override
	public String toString() {
		return super.toString()+"reviewNo=" + reviewNo + ", review="
				+ review + ", rated=" + rated + "]";
	}
	
	
}
