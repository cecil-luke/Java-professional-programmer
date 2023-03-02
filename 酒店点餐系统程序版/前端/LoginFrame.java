import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
public class LoginFrame{
	JFrame frame;
	JPanel top,center,bottom;

	JLabel lab1,lab2,pic;
	JTextField username,password;
	JButton register,submit,reset;

	JButton[] bts;
	public LoginFrame(){
		frame = new JFrame("易途盛世大酒店 只能点餐系统 v0.8");
		top = new JPanel();
		center = new JPanel(new BorderLayout());
		bottom = new JPanel(new GridLayout(4,9));

		lab1 = new JLabel("用户名:");
		lab2 = new JLabel("密码:");
		pic = new JLabel(new ImageIcon("img/enter.jpg"));
		username = new JTextField(12);
		password = new JTextField(12);
		password.setEditable(false);//禁止手动编辑
		register = new JButton("注册");
		register.addActionListener((ae) -> {
			//1.采集用户数据
			String name = username.getText();
			String pwd = password.getText();
			//2.校验数据是否合法
			if(name.trim().length() == 0 || pwd.trim().length() == 0){
				JOptionPane.showMessageDialog(frame,"手呢?");
				return;
			}
			Request req = new Request();
			req.setAskNo(1001);
			req.setParameter("username",name);
			req.setParameter("password",pwd);

			Response res = ClientNetTools.sendAndRead(req);
			int result = res.getResult();
			//6.根据结果弹窗提示用户
			if(result == 0){
				JOptionPane.showMessageDialog(frame,"注册成功 请尝试登录");
			}else if(result == 1){
				JOptionPane.showMessageDialog(frame,"用户名已经被占用 请更换后重试");
			}

		});
		submit = new JButton("登录");
		submit.addActionListener((ae) -> {
			//1.采集用户数据
			String name = username.getText();
			String pwd = password.getText();
			//2.校验数据是否合法
			if(name.trim().length() == 0 || pwd.trim().length() == 0){
				JOptionPane.showMessageDialog(frame,"请先输入用户名和密码之后重试");
				return;
			}
			//System.out.println("数据准备就绪 即将连接服务器");

			Request req = new Request();
			req.setAskNo(1002);//登录
			req.setParameter("username",name);
			req.setParameter("password",pwd);
			//连接服务器
			Response res = ClientNetTools.sendAndRead(req);
			int result = res.getResult();
			//6.根据结果提示用户
			if(result == 0){
				JOptionPane.showMessageDialog(frame,"登录成功");
				frame.setVisible(false);//隐藏登录窗体
				try{
					new OrderFrame(name);//弹出点菜窗体
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(result == 1){
				JOptionPane.showMessageDialog(frame,"用户名错误 请重新登录");
			}else if(result == 2){
				JOptionPane.showMessageDialog(frame,"密码错误 请重新登录");
			}
		});
		reset = new JButton("重置");
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				username.setText("");
				password.setText("");
			}
		});

		bts = new JButton[36];
		KeyboardListener kl = new KeyboardListener();
		for(int i = 0;i<bts.length;i++){
			bts[i] = new JButton(i<10 ? i+"" : (char)(i+87)+"");
			bts[i].addActionListener(kl);
			bottom.add(bts[i]);
		}

		center.add(pic);


		top.add(lab1);
		top.add(username);
		top.add(lab2);
		top.add(password);
		top.add(register);
		top.add(submit);
		top.add(reset);

		frame.add(top,"North");
		frame.add(center);
		frame.add(bottom,"South");

		frame.setSize(800,640);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);

	}
	public static void main(String[] args){
		new LoginFrame();
	}

	class KeyboardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String cmd = ae.getActionCommand();//动作指令(默认就是按钮上的字)
			String old = password.getText();//原本的内容
			password.setText(old + cmd);
		}
	}
}