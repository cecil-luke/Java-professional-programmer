import java.util.*;
public class Request implements java.io.Serializable{
	//1001×¢²á 1002µÇÂ¼ 1003µã²Ë
	private int askNo;
	//²ÎÊý
	private Map<String,String> params  = new HashMap<>();
	public void setAskNo(int askNo){
		this.askNo = askNo;
	}
	public int getAskNo(){
		return askNo;
	}
	public void setParameter(String key,String value){
		params.put(key,value);
	}
	public String getParameter(String key){
		return params.get(key);
	}
}