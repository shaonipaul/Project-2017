package dao;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

import com.fasterxml.jackson.databind.ObjectMapper;


import model.ChatMessage;
import model.MessageList;

public class Message {
	private Connection con;
	
	public MessageList getMessage(String ccd){
		con = new DbConnection().getConnection();
		MessageList chatMessage = null;
    	try{
    	Statement st = con.createStatement();
    	String query = "select * from chat where chatid = '"+ccd+"'";
    	
		ResultSet rs = st.executeQuery(query); 
		if(rs.next()){
			 ObjectMapper mapper = new ObjectMapper();
			 chatMessage = mapper.readValue(rs.getString("message"), MessageList.class);
		}
    	}catch(Exception e){
    	 e.printStackTrace();
    	}
    	
    	return chatMessage;
	}
	
	public void updateMessage(String ccd, String msg){
		con = new DbConnection().getConnection();
		MessageList chatMessage = null;
    	try{
    	Statement st = con.createStatement();
    	String query = "update chat set message = '"+msg + "' where chatid = '"+ccd+"'";
    	
		st.executeUpdate(query); 
		
    	}catch(Exception e){
    	 e.printStackTrace();
    	}
	}
	
	public void insertMessage(String ccd, String msg){
		con = new DbConnection().getConnection();
		MessageList chatMessage = null;
    	try{
    	PreparedStatement ps = con.prepareStatement("insert into chat(chatid,message) values(?,?)"); 
    	ps.setString(1,ccd);
    	ps.setString(2,msg);
    	ps.executeUpdate();
		
    	}catch(Exception e){
    	 e.printStackTrace();
    	}
	}

}
