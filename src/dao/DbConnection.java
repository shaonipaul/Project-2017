package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	int getConnection;
	public static Connection conn;
	
	public Connection getConnection(){
		String username= "root";
		String url= "jdbc:mysql://localhost:3306/final_project";
		String password="grant";
		String driver="com.mysql.jdbc.Driver";
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url,username,password);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
