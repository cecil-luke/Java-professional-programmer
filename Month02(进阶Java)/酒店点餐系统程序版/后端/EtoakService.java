import java.util.*;
//核心业务
public class EtoakService{
	public int register(String username,String password){
		//得到信息
		Map<String,String> map = EtoakFAO.load();
		if(map.containsKey(username)){
			return 1;//用户名已经注册
		}
		map.put(username,password);
		EtoakFAO.save(map);
		return 0;
	}
	public int login(String username,String password){
		Map<String,String> map = EtoakFAO.load();
		if(!map.containsKey(username)){
			return 1;//用户名不存在
		}
		String realPassword = map.get(username);
		return realPassword.equals(password) ? 0 : 2;
	}
	public int order(String username,String[] foods){
		if(foods.length < 5){
			return 1;
		}
		if(username.contains("Gay")){
			return 2;
		}
		EtoakFAO.createOrder(username,foods);
		return 0;
	}
}