package com.announcement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AnnouncementDBUtil {

	
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	
	
	public static boolean insertdetails(String title, String pname, String des, String phone, String email, String date) {
    	
    	boolean isSuccess = false;
    	
    	try {
    		con = DBConnect.getConnection();
    		stmt = con.createStatement();
    		String sql ="insert into annou values (0,'"+title+"','"+pname+"','"+des+"','"+phone+"','"+email+"','"+date+"')";
    		int rs = stmt.executeUpdate(sql);
    		
    		if(rs > 0) {
    			isSuccess = true;
    		} else {
    			isSuccess = false;
    		}
    		
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
 	
    	return isSuccess;
    }
	
public static boolean validate(String title) {
		
		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			String sql = "select * from annou where title='"+title+"'";
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	public static List<Announcement> getAnnouncement(String Title) {
		
		ArrayList<Announcement> anu = new ArrayList<>();
		
		try {
			
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			String sql = "select * from annou ";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int adid = rs.getInt(1);
				String title = rs.getString(2);
				String pname = rs.getString(3);
				String des = rs.getString(4);
				String phone = rs.getString(5);
				String email = rs.getString(6);
				String date = rs.getString(7);
				
				
				Announcement a = new Announcement (adid, title, pname, des, phone,email,date);
				anu.add(a);
			}
			
		} catch (Exception e) {
			
		}
		
		return anu;	
	}
			 
	public static boolean upadateannouncment(String id, String title, String pname, String des, String phone, String email, String date) {
		try {
		
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			String sql = "update annou set title='"+title+"', pname='"+pname+"', des='"+des+"', phone='"+phone+"', email='"+email+"', date='"+date+"'"
					+ "where adid ='"+id+"'";
			int rs = stmt.executeUpdate(sql);
			
				if(rs > 0) {
					isSuccess = true;
				}		
				else {
					isSuccess = false;
				}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;	
	}

	public static List<Announcement> getAnnouncementDetails(String Id){
		
		int convertedID = Integer.parseInt(Id);
		
		ArrayList<Announcement> anu = new ArrayList<>();
		
		try {
			con = DBConnect.getConnection();
			String sql ="select * from annou where adid ='"+convertedID+"'";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int adid = rs.getInt(1);
				String title = rs.getString(2);
				String pname = rs.getString(3);
				String des = rs.getString(4);
				String phone = rs.getString(5);
				String email = rs.getString(6);
				String date = rs.getString(7);
				
				
				Announcement a = new Announcement(adid, title, pname, des, phone, email, date);
				anu.add(a);
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace()
			
		}
		
		return anu;
		
	}
	
	public static boolean deleteannouncement(String Id) {
	
		int convID = Integer.parseInt(Id)
		
		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement()
			String sql ="delete from annou where adid ='"+convID+"'";
			int r = stmt.executeUpdate(sql);
			
			if(r > 0) {
				isSuccess = true;
			}
			else {
				isSuccess = false;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
		
		return isSuccess
	}
}
