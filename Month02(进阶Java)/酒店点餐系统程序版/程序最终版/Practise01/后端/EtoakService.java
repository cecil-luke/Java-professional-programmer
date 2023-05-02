import java.util.*;
//核心业务
public class EtoakService{
	UserDao uDao = new UserDao4MySQL();
	//OrderDao oDao = new OrderDao4MySQL();
	OrderDao oDao = new OrderDao4MySQLPlus();
	QueryDao qd = new QueryDao4MySQL();

	//注册
	public int register(String username,String password){
		if(uDao.exists(username)){
			return 1;
		}
		boolean isOkey = uDao.addUser(username,password);
		return isOkey ?  0:2;
	}//登录
	public int login(String username,String password){
		if(!uDao.exists(username)){
			return 1;
		}
		boolean isOkey = uDao.checkUser(username,password);
		return isOkey ? 0:2;
	}
	//下单
	public int order(String username,String[] foods){
		//生成订单号
		String did = UUID.randomUUID().toString().replace("-","");
		//获得用户id
		int uid = oDao.queryUserIdByName(username);
		boolean isOkey = oDao.addOrder(did,uid);
		for(String food:foods){
			//获得食物id
			int fid = oDao.queryFoodIdByName(food);
			isOkey = isOkey && oDao.addOrderFood(did,fid);
		}
		//下单成功或否
		return isOkey ? 0:1;
	}
	//查询
	public List<Map<String,Object>> queryDetailByOrderId(String did){
		return qd.queryDetailByOrderId(did);
	}
	public List<Map<String,Object>> queryDetailByUsername(String username){
		return qd.queryDetailByUsername(username);
	}
	//时间段
	public List<Map<String,Object>> queryDetailByTime(String start,String end){
		return qd.queryDetailByTime(start,end);
	}
}