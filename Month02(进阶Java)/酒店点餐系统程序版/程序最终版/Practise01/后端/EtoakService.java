import java.util.*;
//����ҵ��
public class EtoakService{
	UserDao uDao = new UserDao4MySQL();
	//OrderDao oDao = new OrderDao4MySQL();
	OrderDao oDao = new OrderDao4MySQLPlus();
	QueryDao qd = new QueryDao4MySQL();

	//ע��
	public int register(String username,String password){
		if(uDao.exists(username)){
			return 1;
		}
		boolean isOkey = uDao.addUser(username,password);
		return isOkey ?  0:2;
	}//��¼
	public int login(String username,String password){
		if(!uDao.exists(username)){
			return 1;
		}
		boolean isOkey = uDao.checkUser(username,password);
		return isOkey ? 0:2;
	}
	//�µ�
	public int order(String username,String[] foods){
		//���ɶ�����
		String did = UUID.randomUUID().toString().replace("-","");
		//����û�id
		int uid = oDao.queryUserIdByName(username);
		boolean isOkey = oDao.addOrder(did,uid);
		for(String food:foods){
			//���ʳ��id
			int fid = oDao.queryFoodIdByName(food);
			isOkey = isOkey && oDao.addOrderFood(did,fid);
		}
		//�µ��ɹ����
		return isOkey ? 0:1;
	}
	//��ѯ
	public List<Map<String,Object>> queryDetailByOrderId(String did){
		return qd.queryDetailByOrderId(did);
	}
	public List<Map<String,Object>> queryDetailByUsername(String username){
		return qd.queryDetailByUsername(username);
	}
	//ʱ���
	public List<Map<String,Object>> queryDetailByTime(String start,String end){
		return qd.queryDetailByTime(start,end);
	}
}