import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
public class OrderFrame{
	JFrame frame;
	JPanel center,east,south,cc,cs;
	JLabel pic,title,total;
	JButton enter,submit,reset;
	java.awt.List list;
	Map<String,Integer> nameAndPrice = new HashMap<>();
	String current = "ţ����";
	int sum;
	String username;
	public OrderFrame(String username)throws Exception{
		this.username = username;
		frame = new JFrame("��ͽ���");
		createMenu();
		center = new JPanel(new BorderLayout());
		east = new JPanel(new BorderLayout());
		south = new JPanel(new FlowLayout());
		cc = new JPanel(new BorderLayout());
		cs = new JPanel(new FlowLayout());

		pic = new JLabel(new ImageIcon("img/ţ����.jpg"));
		title = new JLabel("ţ���棺80 Ԫ");
		total = new JLabel("��ǰ�ܹ����ѣ�0 Ԫ");
		enter = new JButton("��Ҫ�������");
		enter.addActionListener(new EnterListener());
		submit = new JButton("ȷ���µ�");
		submit.addActionListener((ae) -> {
			String[] data = list.getItems();
			if(data.length == 0){
				JOptionPane.showMessageDialog(frame,"����?");
				return;
			}
			StringBuffer buff = new StringBuffer();
			for(String x : data){
				buff.append(x).append(",");
			}
			String foods = buff.toString();

			Request req = new Request();
			req.setAskNo(1003);
			req.setParameter("username",username);
			req.setParameter("foods",foods);

			Response res = ClientNetTools.sendAndRead(req);

			int result = res.getResult();
			if(result == 0){
				JOptionPane.showMessageDialog(frame,"�µ��ɹ�~");
				submit.setEnabled(false);
			}else if(result == 1){
				JOptionPane.showMessageDialog(frame,"�µ�ʧ��~ δ��5���� �����µ�");
			}else if(result == 2){
				JOptionPane.showMessageDialog(frame,"GUN");
			}
		});
		reset = new JButton("���ò˵�");
		reset.addActionListener((ae) -> {
			sum = 0;
			list.removeAll();
			total.setText("��ǰ�ܹ����ѣ�0 Ԫ");
			submit.setEnabled(true);
		});
		list = new java.awt.List();

		south.add(enter);
		south.add(submit);
		south.add(reset);
		south.add(total);

		east.add(list);

		cc.add(pic);
		cs.add(title);
		center.add(cc);
		center.add(cs,"South");

		frame.add(south,"South");
		frame.add(east,"East");
		frame.add(center);

		frame.setSize(800,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);


	}
	public void createMenu()throws Exception{
		FoodListener fl = new FoodListener();
		JMenuBar bar = new JMenuBar();

		Properties menuPros = new Properties();
		menuPros.load(new FileReader("cfg/config.properties"));
		Set<String> menuNames = menuPros.stringPropertyNames();
		for(String menuName : menuNames){
			JMenu menu = new JMenu(menuName);
			String fileName = menuPros.getProperty(menuName);

			Properties itemPros = new Properties();
			itemPros.load(new FileReader("cfg/"+fileName));
			Set<String> itemNames = itemPros.stringPropertyNames();
			for(String itemName : itemNames){
				String str = itemPros.getProperty(itemName);
				int price = Integer.parseInt(str);

				nameAndPrice.put(itemName,price);

				JMenuItem mi = new JMenuItem(itemName);
				mi.addActionListener(fl);

				menu.add(mi);
			}
			bar.add(menu);
		}

		frame.setJMenuBar(bar);
	}

	class FoodListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String cmd = ae.getActionCommand();
			//System.out.println(cmd);
			//�ı��ǩ�����ͼƬ
			pic.setIcon(new ImageIcon("img/"+cmd+".jpg"));

			//�õ�������Ǹ��˵ļ۸�
			Integer price = nameAndPrice.get(cmd);

			//�ı��ǩ������
			title.setText(cmd + " : " + price + " Ԫ");

			//��¼������ʾ�ĸ���
			current = cmd;
		}
	}

	class EnterListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			list.add(current);
			//�õ�����˵ļ۸�
			Integer price = nameAndPrice.get(current);
			sum += price;
			total.setText("��ǰ�ܹ����ѣ�"+sum+" Ԫ");
		}
	}

}