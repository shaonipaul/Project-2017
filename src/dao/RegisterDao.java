package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.OnlineStatus;

public class RegisterDao {
	
	private Connection con;


	public void register(String uname,String password, String email, String utype) throws SQLException{
		try{
		con = new DbConnection().getConnection();
		PreparedStatement pstatement=con.prepareStatement("Insert into user(username,password,email,usertype) values(?,?,?,?)");
		pstatement.setString(1, uname);
		pstatement.setString(2, password);
		pstatement.setString(3, email);
		pstatement.setString(4, utype);
		pstatement.executeUpdate();
		}catch(Exception e){
			throw new SQLException();
		}
		finally{
			con.close();
		}
	}
	
	public Map<String,String> login(String uname, String password)throws SQLException{
		Map<String,String> m = new HashMap<String,String>();
		try{
		con = new DbConnection().getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from user where username='"+uname+"' and password='"+password+"'");
		if(rs.next()){
			
			m.put("id", rs.getInt("id")+"");
			m.put("username", rs.getString("username")+"");
			//m.put("password", rs.getString("password")+"");
			m.put("email", rs.getString("email")+"");
			m.put("usertype", rs.getString("usertype")+"");
			
		}
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException();
		}
		finally{
			con.close();
		}			
		
		return m;
	}
	
	public List<OnlineStatus> getName(Set<String> ids, int idd) throws SQLException{
		List<OnlineStatus> list = new ArrayList<OnlineStatus>();
		try{
			con = new DbConnection().getConnection(); 
			Statement st = con.createStatement();
			Iterator<String> it = ids.iterator();
			while(it.hasNext()){
				String id = it.next();
			ResultSet rs = st.executeQuery("select username from user where id= '"+id+"' and id <> '"+idd+"'");
			if(rs.next()){
				list.add(new OnlineStatus(id,rs.getString("username")));
			}
			}
		}finally{
			con.close();
		}
		return list;
	}
}
