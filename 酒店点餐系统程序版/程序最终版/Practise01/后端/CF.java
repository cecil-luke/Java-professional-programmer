import java.sql.*;
import java.io.*;
import java.util.*;
public class CF{
	static String url;
	static String username;
	static String password;
	static{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties pro = new Properties();
			pro.load(new FileReader("db.properties"));
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Connection get(){
		try{
			Connection conn = DriverManager.getConnection(url,username,password);
			return conn;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}