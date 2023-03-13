import java.sql.*;
import java.util.*;
public class QueryDao4MySQL implements QueryDao{
	@Override
	public List<Map<String,Object>> queryDetailByOrderId(String did){
		String sql = "select food.name 菜名,food.price 菜价,user.username 用户名,dd.date 下单时间 from dd left join user on dd.uid = user.id left join ddfood on ddfood.did = dd.id left join food on ddfood.fid = food.id where dd.id = ?";
		List<Map<String,Object>> list = new ArrayList<>();
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1,did);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					Map<String,Object> oneLine = new LinkedHashMap<>();
					oneLine.put("菜名",rs.getString("菜名"));
					oneLine.put("菜价",rs.getString("菜价"));
					oneLine.put("用户名",rs.getString("用户名"));
					oneLine.put("下单时间",rs.getString("下单时间"));
					list.add(oneLine);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return list;
	}
	@Override
	public List<Map<String,Object>> queryDetailByUsername(String username){
	String sql = "select dd.id 订单编号,count(ddfood.fid) 菜品数量,sum(food.price) 总共价格,dd.date 下单时间 from dd left join user on dd.uid = user.id left join ddfood on ddfood.did = dd.id left join food on ddfood.fid = food.id where user.username = ? group by dd.id";
		List<Map<String,Object>> list = new ArrayList<>();
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1,username);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					Map<String,Object> oneLine = new LinkedHashMap<>();
					oneLine.put("订单编号",rs.getString("订单编号"));
					oneLine.put("菜品数量",rs.getInt("菜品数量"));
					oneLine.put("总共价格",rs.getString("总共价格"));
					oneLine.put("下单时间",rs.getString("下单时间"));
					list.add(oneLine);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return list;
	}
	@Override
	public List<Map<String,Object>> queryDetailByTime(String start,String end){
		System.out.println("时间段："+start+"	"+end);
		String sql = "select dd.id 订单编号,count(ddfood.fid) 菜品数量,sum(food.price) 总共价格,dd.date 下单时间,min(user.username) 用户名 from dd left join user on dd.uid = user.id left join ddfood on ddfood.did = dd.id left join food on ddfood.fid = food.id where dd.date between ? and ? group by dd.id";
		List<Map<String,Object>> list = new ArrayList<>();
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1,start+" 00:00:00");
				pstmt.setString(2,end+" 23:59:59");

			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					Map<String,Object> oneLine = new LinkedHashMap<>();
					oneLine.put("订单编号",rs.getString("订单编号"));
					oneLine.put("菜品数量",rs.getInt("菜品数量"));
					oneLine.put("总共价格",rs.getString("总共价格"));
					oneLine.put("用户名",rs.getString("用户名"));
					oneLine.put("下单时间",rs.getString("下单时间"));
					list.add(oneLine);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return list;
	}
}