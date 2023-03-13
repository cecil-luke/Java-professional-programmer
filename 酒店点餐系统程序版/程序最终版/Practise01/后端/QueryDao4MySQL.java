import java.sql.*;
import java.util.*;
public class QueryDao4MySQL implements QueryDao{
	@Override
	public List<Map<String,Object>> queryDetailByOrderId(String did){
		String sql = "select food.name ����,food.price �˼�,user.username �û���,dd.date �µ�ʱ�� from dd left join user on dd.uid = user.id left join ddfood on ddfood.did = dd.id left join food on ddfood.fid = food.id where dd.id = ?";
		List<Map<String,Object>> list = new ArrayList<>();
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1,did);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					Map<String,Object> oneLine = new LinkedHashMap<>();
					oneLine.put("����",rs.getString("����"));
					oneLine.put("�˼�",rs.getString("�˼�"));
					oneLine.put("�û���",rs.getString("�û���"));
					oneLine.put("�µ�ʱ��",rs.getString("�µ�ʱ��"));
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
	String sql = "select dd.id �������,count(ddfood.fid) ��Ʒ����,sum(food.price) �ܹ��۸�,dd.date �µ�ʱ�� from dd left join user on dd.uid = user.id left join ddfood on ddfood.did = dd.id left join food on ddfood.fid = food.id where user.username = ? group by dd.id";
		List<Map<String,Object>> list = new ArrayList<>();
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1,username);
			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					Map<String,Object> oneLine = new LinkedHashMap<>();
					oneLine.put("�������",rs.getString("�������"));
					oneLine.put("��Ʒ����",rs.getInt("��Ʒ����"));
					oneLine.put("�ܹ��۸�",rs.getString("�ܹ��۸�"));
					oneLine.put("�µ�ʱ��",rs.getString("�µ�ʱ��"));
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
		System.out.println("ʱ��Σ�"+start+"	"+end);
		String sql = "select dd.id �������,count(ddfood.fid) ��Ʒ����,sum(food.price) �ܹ��۸�,dd.date �µ�ʱ��,min(user.username) �û��� from dd left join user on dd.uid = user.id left join ddfood on ddfood.did = dd.id left join food on ddfood.fid = food.id where dd.date between ? and ? group by dd.id";
		List<Map<String,Object>> list = new ArrayList<>();
		try(Connection conn = CF.get();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1,start+" 00:00:00");
				pstmt.setString(2,end+" 23:59:59");

			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					Map<String,Object> oneLine = new LinkedHashMap<>();
					oneLine.put("�������",rs.getString("�������"));
					oneLine.put("��Ʒ����",rs.getInt("��Ʒ����"));
					oneLine.put("�ܹ��۸�",rs.getString("�ܹ��۸�"));
					oneLine.put("�û���",rs.getString("�û���"));
					oneLine.put("�µ�ʱ��",rs.getString("�µ�ʱ��"));
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