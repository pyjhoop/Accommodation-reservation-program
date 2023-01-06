package com.reserve.model.dao;

import static com.reserve.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import com.reserve.model.vo.Member;
import com.reserve.model.vo.Reserve;
import com.reserve.model.vo.Review;
import com.reserve.model.vo.Room;


public class ReserveDao {

	private Properties prop = new  Properties();
	
	public ReserveDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getUserNames(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<String> list = new ArrayList<String>();
		
		String sql = prop.getProperty("getUserNames");
		
		
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					list.add(rset.getString("USERID"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
	}
	public int login(Connection conn,String userId,String userPwd) {
		int result =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("login");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("RESERVATION_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Room> allSelect(Connection conn){
		ArrayList<Room> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("allSelect");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Room(rset.getInt("ROOMNO")
								 ,rset.getString("ROOMNAME")
								 ,rset.getInt("CAPACITY")
								 ,rset.getString("TYPE")
								 ,rset.getString("LOCATION")
								 ,rset.getInt("PRICE")
						
						));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int signUp(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("signUp");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getUserId());
			pstmt.setString(3, m.getUserPwd());
			pstmt.setInt(4, m.getGuests());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public Room hotelChoice(Connection conn,int num) {
		Room r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("hotelChoice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
			r = new Room();
			r.setRoomNo(rset.getInt("ROOMNO"));
			r.setRoomName(rset.getString("ROOMNAME"));
			r.setCapacity(rset.getInt("CAPACITY"));
			r.setType(rset.getString("TYPE"));
			r.setLocation(rset.getString("LOCATION"));
			r.setPrice(rset.getInt("PRICE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}
	
	public ArrayList<Review> getReview(Connection conn,int RoomNo){
		ArrayList<Review> list = new ArrayList<>();
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, RoomNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Review(rset.getInt("REVIEWNO")
								   ,rset.getString("REVIEW")
								   ,rset.getInt("RATED")
						
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int reservePayment(Connection conn,Room r,int result,int date) {
		int result2 = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("reservePayment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getRoomNo());
			pstmt.setInt(2, date);
			pstmt.setInt(3, result);
			result2 = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result2;
	}
	
	public ArrayList<Reserve> listReserve(Connection conn,int reserveNo){
		ArrayList<Reserve> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("listReserve");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reserveNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Reserve(rset.getInt("RESERVNO")
									,rset.getString("STATE")
									,rset.getInt("ROMMNO")
									,rset.getDate("STARTDATE")
									,rset.getDate("ENDDATE")
									,rset.getInt("RESERVATION_NO")
						
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}
