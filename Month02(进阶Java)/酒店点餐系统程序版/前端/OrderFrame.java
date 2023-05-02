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
	String current = "牛肉面";
	int sum;
	String username;
	public OrderFrame(String username)throws Exception{
		this.username = username;
		frame = new JFrame("点餐界面");
		createMenu();
		center = new JPanel(new BorderLayout());
		east = new JPanel(new BorderLayout());
		south = new JPanel(new FlowLayout());
		cc = new JPanel(new BorderLayout());
		cs = new JPanel(new FlowLayout());

		pic = new JLabel(new ImageIcon("img/牛肉面.jpg"));
		title = new JLabel("牛肉面：80 元");
		total = new JLabel("当前总共消费：0 元");
		enter = new JButton("我要点这道菜");
		enter.addActionListener(new EnterListener());
		submit = new JButton("确定下单");
		submit.addActionListener((ae) -> {
			String[] data = list.getItems();
			if(data.length == 0){
				JOptionPane.showMessageDialog(frame,"玩呢?");
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
				JOptionPane.showMessageDialog(frame,"下单成功~");
				submit.setEnabled(false);
			}else if(result == 1){
				JOptionPane.showMessageDialog(frame,"下单失败~ 未达5个菜 不给下单");
			}else if(result == 2){
				JOptionPane.showMessageDialog(frame,"GUN");
			}
		});
		reset = new JButton("重置菜单");
		reset.addActionListener((ae) -> {
			sum = 0;
			list.removeAll();
			total.setText("当前总共消费：0 元");
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
			//改变标签里面的图片
			pic.setIcon(new ImageIcon("img/"+cmd+".jpg"));

			//得到点击的那个菜的价格
			Integer price = nameAndPrice.get(cmd);

			//改变标签的文字
			title.setText(cmd + " : " + price + " 元");

			//记录正在显示哪个菜
			current = cmd;
		}
	}

	class EnterListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			list.add(current);
			//得到这个菜的价格
			Integer price = nameAndPrice.get(current);
			sum += price;
			total.setText("当前总共消费："+sum+" 元");
		}
	}

}