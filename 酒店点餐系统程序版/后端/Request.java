import java.util.*;
public class Request implements java.io.Serializable{
	//1001ע�� 1002��¼ 1003���
	private int askNo;
	//����
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