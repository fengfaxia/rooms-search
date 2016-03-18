package com.ffx.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ffx.domain.Messsage;
import com.ffx.domain.Room;

public class SqlHelper {
	private static Connection ct = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	public Room executeQuery(String bookid) {
		//ArrayList<Room> room1 = new ArrayList<Room>();
		Room room = new Room();
		try {
			ct = DB.getConn();
			String sql = "select * from room where bookid=?";
			ps = ct.prepareStatement(sql);
			ps.setString(1, bookid);
			rs = ps.executeQuery();
			while (rs.next()) {
				room.setBookid(rs.getString(1));
				room.setStudentname(rs.getString(2));
				room.setMale(rs.getString(3));
				room.setDnumber(rs.getString(4));
				room.setRoomnumber(rs.getString(5));
				room.setPrice(rs.getString(6));
				room.setStudentid(rs.getString(7));
				room.setPlace(rs.getString(8));
				room.setClassname(rs.getString(9));
				room.setMajor(rs.getString(10));
				room.setCollage(rs.getString(11));
				room.setUniqueid(rs.getString(12));
				//room1.add(room);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RuntimeException(e.getMessage());
		} finally {
			DB.close(rs, ps, ct);
		}
		return room;
	}

	public void executeUpdate(Messsage messsage) {
		try {
			ct = DB.getConn();
			String sql = "insert into messsage values(?,?,?,?,?)";
			ps = ct.prepareStatement(sql);
			ps.setInt(1, messsage.getMesssageid());
			ps.setString(2, messsage.getDnumber());
			ps.setString(3, messsage.getRoomnumber());
			ps.setString(4, messsage.getStudentnumber());
			ps.setString(5, messsage.getContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, ps, ct);
		}
	}
	public int pageCount(String roomnumber) {
		int rowCount = 0;
		int pageCount = 0;
		int pageSize = 1;
		try {
			ct = DB.getConn();
			String sql = "select * from messsage where roomnumber = ?";
			ps = ct.prepareStatement(sql);
			ps.setString(1, roomnumber);
			rs = ps.executeQuery();
			rs.last();
			rowCount = rs.getRow();
			
			if(rowCount % pageSize == 0) {
				pageCount = rowCount/pageSize;
			}else{
				pageCount = rowCount/pageSize + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(rs, ps, ct);
		}
		return pageCount;
}
	public ArrayList<Messsage> eQueryMesssage(String roomnumber,int pageNow) {
		ArrayList<Messsage> al = new ArrayList<Messsage>();
		int pageCount = 0;
		int pageSize = 1;
		pageCount = pageCount(roomnumber);
		int startRow = (pageNow - 1) * pageSize;
		try {
			ct = DB.getConn();
			String sql = "select * from messsage where roomnumber = ? order by messsageid limit "+startRow+","+pageSize;
			ps = ct.prepareStatement(sql);
			ps.setString(1, roomnumber);	
			rs = ps.executeQuery();
			while(rs.next()) {
				Messsage message = new Messsage();
				message.setMesssageid(rs.getInt(1));
				message.setDnumber(rs.getString(2));
				message.setRoomnumber(rs.getString(3));
				message.setStudentnumber(rs.getString(4));
				message.setContent(rs.getString(5));
				al.add(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RuntimeException(e.getMessage());
		} finally {
			DB.close(rs, ps, ct);
		}
		return al;
	}
}
