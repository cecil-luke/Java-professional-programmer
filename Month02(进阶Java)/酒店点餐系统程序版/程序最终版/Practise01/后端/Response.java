import java.util.*;
//��ִ
public class Response implements java.io.Serializable{
	//��Ӧ��ֵ������ͬ�ĺ���
	private int result = -1;
	public void setResult(int result){
		this.result = result;
	}
	public int getResult(){
		return result;
	}
	//��ѯ
	private Map<String,Object> data = new LinkedHashMap<>();
	public void setData(String key,Object value){
		data.put(key,value);
	}
	public Object getData(String key){
		return data.get(key);
	}
}