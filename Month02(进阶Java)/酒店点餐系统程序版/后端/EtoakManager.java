public class EtoakManager{
	//另外一个类
	EtoakService service = new EtoakService();
	//提供信息
	public Response work(Request req){
		Response res = new Response();
		//获取用户来意
		int askNo = req.getAskNo();
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
		}
		return res;
	}
}