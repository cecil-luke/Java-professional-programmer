import java.util.*;
//����ҵ��
public class EtoakService{
	public int register(String username,String password){
		//�õ���Ϣ
		Map<String,String> map = EtoakFAO.load();
		if(map.containsKey(username)){
			return 1;//�û����Ѿ�ע��
		}
		map.put(username,password);
		EtoakFAO.save(map);
		return 0;
	}
	public int login(String username,String password){
		Map<String,String> map = EtoakFAO.load();
		if(!map.containsKey(username)){
			return 1;//�û���������
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