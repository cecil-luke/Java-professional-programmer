import java.net.*;
import java.io.*;
import java.util.*;
public class ClientNetTools{
	static String ip;
	static int port;
	static{
		Properties pro = new Properties();
		try(FileReader fr = new FileReader("serverConfig.properties")){//服务器连接IP PORT
			pro.load(fr);
		}catch(Exception e){
			e.printStackTrace();
		}
		ip = pro.getProperty("IP");
		String str = pro.getProperty("PORT");
		port = Integer.parseInt(str);
	}

	public static Response sendAndRead(Request req){
		try{
			Socket skt = new Socket(ip,port);
			OutputStream os = skt.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(req);
			InputStream is = skt.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Response res = (Response)ois.readObject();
			return res;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}