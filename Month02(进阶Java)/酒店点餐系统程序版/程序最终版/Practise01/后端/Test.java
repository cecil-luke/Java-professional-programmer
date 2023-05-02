import java.net.*;
import java.io.*;
//
public class Test{
	public static void main(String[] args)throws Exception{
		Request req = new Request();
		req.setAskNo(1001);
		req.setParameter("username","JayZhou");
		req.setParameter("password","000000");
		//
		Socket skt = new Socket("192.168.26.1",12345);
		OutputStream os = skt.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(req);
		//
		InputStream is = skt.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		Response res = (Response)ois.readObject();
		ois.close();
		oos.close();
		skt.close();
		//
		int result = res.getResult();
		System.out.println(result);
	}
}