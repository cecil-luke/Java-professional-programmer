import java.net.*;
import java.io.*;
//
public class EtoakThread extends Thread{//implements Runnable
	Socket skt;
	public EtoakThread(Socket skt){
		this.skt = skt;
	}
	@Override
	public void run(){
		try{
			//接受信息
			InputStream is = skt.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Object obj = ois.readObject();
			Request req = (Request)obj;
			//
			EtoakManager em = new EtoakManager();
			Response res = em.work(req);
			//
			OutputStream os = skt.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(res);
			//
			oos.close();
			ois.close();
			skt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}