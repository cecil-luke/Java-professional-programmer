import java.sql.*;
public class OrderDao4MySQLPlus implements OrderDao{
	@Override
	public int queryFoodIdByName(String name){
		String sql = "select id from food where name=?";
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1,name);
			try(ResultSet rs = pstmt.executeQuery()){
				if(!rs.next())return 0;
				return rs.getInt("id");
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int queryUserIdByName(String username){
		String sql = "select id from user where username = ?";
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1,username);
			try(ResultSet rs = pstmt.executeQuery()){
				if(!rs.next())return 0;
				return rs.getInt("id");
			}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public boolean addOrder(String orderId,int uid){
		String sql = "insert into dd(id,uid,date) values(?,?,now())";
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1,orderId);
			pstmt.setInt(2,uid);
			int count = pstmt.executeUpdate();
			return count != 0;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean addOrderFood(String orderId,int fid){
		String sql = "insert into ddfood(did,fid) values(?,?)";
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1,orderId);
			pstmt.setInt(2,fid);
			//如果修改成功，有返回值，即不为0
			//返回true
			int count = pstmt.executeUpdate();
			return count != 0;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}