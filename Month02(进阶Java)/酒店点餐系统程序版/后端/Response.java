
//回执
public class Response implements java.io.Serializable{
	//回应数值，代表不同的含义
	private int result = -1;
	public void setResult(int result){
		this.result = result;
	}
	public int getResult(){
		return result;
	}
}