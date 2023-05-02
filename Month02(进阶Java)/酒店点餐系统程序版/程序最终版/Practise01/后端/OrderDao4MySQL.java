import java.sql.*;
public class  OrderDao4MySQL implements OrderDao{
	@Override
	public int queryFoodIdByName(String name){
		String sql = "select id from food where name = '"+name+"'";
		try(Connection conn = CF.get();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);){
			if(!rs.next())return 0;
			return rs.getInt("id");
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int queryUserIdByName(String username){
		String sql = "select id from user where username = '"+username+"'";
		try(Connection conn = CF.get();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);){
			if(rs.next()){
				return rs.getInt("id");
			}
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public boolean addOrder(String orderId,int uid){
		String sql = "insert into dd(id,uid,date) values('"+orderId+"','"+uid+"',now())";
		try(Connection conn = CF.get();
			Statement stmt = conn.createStatement();){
			int count = stmt.executeUpdate(sql);
			return count!=0;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean addOrderFood(String orderId,int fid){
		String sql = "insert into ddfood(did,fid) values('"+orderId+"','"+fid+"')";
		try(Connection conn = CF.get();
			Statement stmt = conn.createStatement();){
			int count = stmt.executeUpdate(sql);
			return count !=0;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}