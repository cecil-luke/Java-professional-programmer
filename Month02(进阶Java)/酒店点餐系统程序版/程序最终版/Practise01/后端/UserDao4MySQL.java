import java.sql.*;
public class UserDao4MySQL implements UserDao{
	@Override
	public boolean exists(String username){
		String sql = "select id from user where username = '"+username+"'";
		try(Connection conn = CF.get();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){

			return rs.next();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean addUser(String username,String password){
		String sql = "insert into user(username,password) values('"+username+"',md5('"+password+"'))";
		try(Connection conn = CF.get();
			Statement stmt = conn.createStatement();){

			int count = stmt.executeUpdate(sql);
			return count !=0;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean checkUser(String username,String password){
		String sql = "select id from user where username = '"+username+"' and password = md5('"+password+"')";
		try(Connection conn = CF.get();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);){

			return rs.next();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}