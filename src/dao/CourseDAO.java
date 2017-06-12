package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

public class CourseDAO {
	
	private Connection con;
	
	public List<Map<String,String>> getCourses(int id) throws SQLException{
		List<Map<String,String>> list = new ArrayList<>();
		con = new DbConnection().getConnection();
		try{
		StringBuilder sb = new StringBuilder();
		sb.append("select course.id, course.course_id, course.coursename, course.semester ");
		sb.append(" from course where course.facultyid = ");
		sb.append(id);
		System.out.println(sb.toString());
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sb.toString());
		while(rs.next()){
			Map<String,String> map = new HashMap<String,String>();
			map.put("cid", rs.getInt(1)+"");
			map.put("ccid", rs.getString(2));
			map.put("cname", rs.getString(3));
			map.put("csemester", rs.getString(4));
			System.out.println(rs.getString(3));
			list.add(map);
		}
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException();
		}finally{
			con.close();
		}
		return list;
	}
	
    public Map<String,Object> courseDetail(int cid) throws SQLException{
    	con = new DbConnection().getConnection();
    	Map<String,Object> map = new HashMap<String,Object>();
		try{
		StringBuilder sb = new StringBuilder();
		String q1 = "select coursename, otherdetails, exam from course where id = "+cid;
		String q2 = "select * from lecture where courseid = "+cid;
		String q3 = "select * from syllabus where courseid = "+cid;
		
		
		Statement st = con.createStatement();
		Statement st1 = con.createStatement();
		Statement st2 = con.createStatement();
		ResultSet rs = st.executeQuery(q1);
		ResultSet rs1 = st1.executeQuery(q2);
		ResultSet rs2 = st2.executeQuery(q3);
		map.put("cid", cid);
		if(rs.next()){
			map.put("cname", rs.getString(1));
			map.put("cinfo", rs.getString(2));
			map.put("cexam", rs.getString(3));
		}
		List<Map<String, String>> l1 = new ArrayList<Map<String, String>>();
		List<Map<String, String>> l2 = new ArrayList<Map<String, String>>();
		while(rs1.next()){
          Map<String, String> m = new HashMap<String,String>();
          m.put("lid", rs1.getInt(1)+"");
          m.put("content", rs1.getString(2));
          m.put("name", rs1.getString(3));
          m.put("type", rs1.getString(4));
		  l1.add(m);
		}
		
		while(rs2.next()){
	          Map<String, String> m = new HashMap<String,String>();
	          m.put("sid", rs2.getInt(1)+"");
	          m.put("content", rs2.getString(2));
	          m.put("name", rs2.getString(3));
	          m.put("type", rs2.getString(4));
			  l2.add(m);
			}
		map.put("lecture", l1);
		map.put("syllabus", l2);
		
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException();
		}finally{
			con.close();
		}
		return map;
    }
    
    public void update(String category, String type, String content, String cid,InputStream inputStream, String name){
    	con = new DbConnection().getConnection();
    	System.out.println(content+"       dfgfh cat    "+category);
    	try{
    	if(category.equals("syllabus")){
    		String sql = "INSERT INTO syllabus (contents,name,type,data,courseid) values (?, ?, ?,?,?)";
    		PreparedStatement statement = con.prepareStatement(sql);
    		statement.setString(1, content);
    		statement.setString(2, name);
    		statement.setString(3, type);
    		System.out.println(type);
    		statement.setInt(5,Integer.parseInt(cid));
    		    		
    		 if (inputStream != null) {
                 // fetches input stream of the upload file for the blob column
                 statement.setBlob(4, inputStream);
             }
    		 statement.executeUpdate();
    	
    	}else if(category.equals("lecture")){
    	
    		String sql = "INSERT INTO lecture (contents,name,type,data,courseid) values (?, ?, ?,?,?)";
    		PreparedStatement statement = con.prepareStatement(sql);
    		statement.setString(1, content);
    		statement.setString(2, name);
    		statement.setString(3, type);
    		statement.setInt(5,Integer.parseInt(cid));
    		    		
    		 if (inputStream != null) {
                 // fetches input stream of the upload file for the blob column
                 statement.setBlob(4, inputStream);
             }
    		 statement.executeUpdate();
    		 
    		 
    	}else if(category.equals("exam")){
    		Statement st = con.createStatement();
    		st.executeUpdate("update course set exam = '"+content+"'where id="+cid);
    		System.out.println(content+"       dfgfh");
    	}else if(category.equals("info")){
    		Statement st = con.createStatement();
    		st.executeUpdate("update course set otherdetails = '"+content+"'where id="+cid);
    	}
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		
    		try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    public Map<String, Object> download(int fid, String type){
    	 Map<String, Object> m =  new HashMap<String, Object>(); 
    	con = new DbConnection().getConnection();
    	try{
    	Statement st = con.createStatement();
    	String query = null;
    	if(type.equalsIgnoreCase("syllabus")){
    		query = "select * from syllabus where syllabusid = "+fid;
    	}else{
    		query = "select * from lecture where lectureid = "+fid;
    	}
		ResultSet rs = st.executeQuery(query); 
		if(rs.next()){
			m.put("name", rs.getString("name"));
		    Blob b = rs.getBlob("data");
		    long s = b.length();
		    System.out.println("size     "+s);
		    byte requestBytes[] = b.getBytes(1L,(int)s);
		    ByteArrayInputStream bis = new ByteArrayInputStream(requestBytes);
		    m.put("data", bis);
		    m.put("type", rs.getString("type"));
		}
    	}catch(Exception e){
    	 e.printStackTrace();
    	}
    		
		return m;
    }

    public void delete(int fid, String type){
    	con = new DbConnection().getConnection();
    	try{
        	Statement st = con.createStatement();
        	String query = null;
        	if(type.equalsIgnoreCase("syllabus")){
        		query = "delete from syllabus where syllabusid = "+fid;
        	}else{
        		query = "delete from lecture where lectureid = "+fid;
        	}
    		st.executeUpdate(query); 
    		
        	}catch(Exception e){
        	 e.printStackTrace();
        	}
    }
}
