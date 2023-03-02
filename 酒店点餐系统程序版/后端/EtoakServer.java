import java.net.*;
import java.util.concurrent.*;
//�������̣߳���������
public class EtoakServer{
	public static void main(String[] args)throws Exception{
		ExecutorService es = Executors.newFixedThreadPool(5);
		ServerSocket server = new ServerSocket(12345);
		boolean isRunning = true;
		while(isRunning){
			Socket skt = server.accept();
			//���߳�
			EtoakThread et = new EtoakThread(skt);
			es.submit(et);
		}
		server.close();
		es.shutdown();
	}
}