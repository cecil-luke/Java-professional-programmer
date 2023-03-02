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
		frame = new JFrame("��;ʢ����Ƶ� ֻ�ܵ��ϵͳ v0.8");
		top = new JPanel();
		center = new JPanel(new BorderLayout());
		bottom = new JPanel(new GridLayout(4,9));

		lab1 = new JLabel("�û���:");
		lab2 = new JLabel("����:");
		pic = new JLabel(new ImageIcon("img/enter.jpg"));
		username = new JTextField(12);
		password = new JTextField(12);
		password.setEditable(false);//��ֹ�ֶ��༭
		register = new JButton("ע��");
		register.addActionListener((ae) -> {
			//1.�ɼ��û�����
			String name = username.getText();
			String pwd = password.getText();
			//2.У�������Ƿ�Ϸ�
			if(name.trim().length() == 0 || pwd.trim().length() == 0){
				JOptionPane.showMessageDialog(frame,"����?");
				return;
			}
			Request req = new Request();
			req.setAskNo(1001);
			req.setParameter("username",name);
			req.setParameter("password",pwd);

			Response res = ClientNetTools.sendAndRead(req);
			int result = res.getResult();
			//6.���ݽ��������ʾ�û�
			if(result == 0){
				JOptionPane.showMessageDialog(frame,"ע��ɹ� �볢�Ե�¼");
			}else if(result == 1){
				JOptionPane.showMessageDialog(frame,"�û����Ѿ���ռ�� �����������");
			}

		});
		submit = new JButton("��¼");
		submit.addActionListener((ae) -> {
			//1.�ɼ��û�����
			String name = username.getText();
			String pwd = password.getText();
			//2.У�������Ƿ�Ϸ�
			if(name.trim().length() == 0 || pwd.trim().length() == 0){
				JOptionPane.showMessageDialog(frame,"���������û���������֮������");
				return;
			}
			//System.out.println("����׼������ �������ӷ�����");

			Request req = new Request();
			req.setAskNo(1002);//��¼
			req.setParameter("username",name);
			req.setParameter("password",pwd);
			//���ӷ�����
			Response res = ClientNetTools.sendAndRead(req);
			int result = res.getResult();
			//6.���ݽ����ʾ�û�
			if(result == 0){
				JOptionPane.showMessageDialog(frame,"��¼�ɹ�");
				frame.setVisible(false);//���ص�¼����
				try{
					new OrderFrame(name);//������˴���
				}catch(Exception e){
					e.printStackTrace();
				}
			}else if(result == 1){
				JOptionPane.showMessageDialog(frame,"�û������� �����µ�¼");
			}else if(result == 2){
				JOptionPane.showMessageDialog(frame,"������� �����µ�¼");
			}
		});
		reset = new JButton("����");
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
			String cmd = ae.getActionCommand();//����ָ��(Ĭ�Ͼ��ǰ�ť�ϵ���)
			String old = password.getText();//ԭ��������
			password.setText(old + cmd);
		}
	}
}