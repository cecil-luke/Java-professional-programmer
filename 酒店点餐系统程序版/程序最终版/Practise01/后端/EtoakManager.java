import java.util.*;
public class EtoakManager{
	//另外一个类
	EtoakService service = new EtoakService();
	//提供信息
	public Response work(Request req){
		Response res = new Response();
		//获取用户来意
		int askNo = req.getAskNo();
		//用到query的三个方法
		EtoakService es = new EtoakService();
		if(askNo == 1001){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			//
			int result = service.register(username,password);
			res.setResult(result);
			System.out.println("1001"+username+" : "+password+" :"+result);
		}else if(askNo == 1002){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			//
			int result = service.login(username,password);
			res.setResult(result);
			System.out.println("1002"+username+" : "+password+" : "+result);
		}else if(askNo == 1003){
			String username = req.getParameter("username");
			String foods = req.getParameter("foods");
			//
			int result = service.order(username,foods.split(","));
			res.setResult(result);
		}else if(askNo == 2001){
			String username = req.getParameter("username");
			List<Map<String,Object>> data = es.queryDetailByUsername(username);
			System.out.println("名字查询：开始获取得到的数据->"+data);
			if(data != null && data.size() != 0){
				int result = 0;
				res.setData("data",data);
				res.setResult(result);
			}
		}else if(askNo == 2002){
			String did = req.getParameter("did");
			List<Map<String,Object>> data = es.queryDetailByOrderId(did);
			System.out.println("订单查询：开始获取得到的数据->"+data);
			if(data != null && data.size() != 0){
				int result = 0;
				res.setData("data",data);
				res.setResult(result);
			}
		}else if(askNo == 2003){
			String start = req.getParameter("start");
			String end = req.getParameter("end");
			List<Map<String,Object>> data = es.queryDetailByTime(start,end);
			System.out.println("时间段查询：开始获取得到的数据->"+data);
			if(data != null && data.size() != 0){
				int result = 0;
				res.setData("data",data);
				res.setResult(result);
			}
		}
		return res;
	}
}