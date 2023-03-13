import java.util.*;
import java.io.*;
//
public class EtoakFAO{
	//�����û���Ϣ
	static File file = new File("user.data");
	public static synchronized void save(Map<String,String> map){
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(map);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//�����û���Ϣ
	public static synchronized Map<String,String> load(){
		if(!file.exists()){
			return new HashMap<String,String>();
		}
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			return (Map<String,String>)ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args){
		Map<String,String> map = EtoakFAO.load();
		map.put("Test","okle");
		EtoakFAO.save(map);
		EtoakFAO.load().forEach((k,v) -> System.out.println(k+" : "+v));
	}
	static{
		File dir = new File("orders");
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	public static void createOrder(String username,String[] foods){
		long time = System.currentTimeMillis();
		String filename = username+" ["+time+"].txt";
		File tar = new File("orders",filename);
		try(PrintWriter pw = new PrintWriter(tar)){//д�����ļ�
			pw.println(username+"�Ķ����������£�");
			for(String food : foods){
				pw.println("	"+food);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}