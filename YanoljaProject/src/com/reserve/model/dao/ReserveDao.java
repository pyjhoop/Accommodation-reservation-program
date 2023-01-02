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
	
	public Review hotelChoice(Connection conn,int num) {
		Review r = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("hotelChoice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
			r = new Review();
			r.setRoomNo(rset.getInt("ROOMNO"));
			r.setRoomName(rset.getString("ROOMNAME"));
			r.setCapacity(rset.getInt("CAPACITY"));
			r.setType(rset.getString("TYPE"));
			r.setLocation(rset.getString("LOCATION"));
			r.setPrice(rset.getInt("PRICE"));
			r.setReviewNo(rset.getInt("REVIEWNO"));
			r.setReview(rset.getString("REVIEW"));
			r.setRated(rset.getInt("RATED"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}
}
