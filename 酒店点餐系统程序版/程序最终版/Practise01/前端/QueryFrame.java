import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class QueryFrame{
	JFrame frame;
	JLabel lab1,lab2,lab3,lab4;
	JTextField jtf1,jtf2,jtf3,jtf4;
	JButton bt1,bt2,bt3;
	JTextArea jta;
	JScrollPane jsp;

	public QueryFrame(){
		frame = new JFrame("�����ϰ��ר�� v1.0");
		lab1 = new JLabel("��ʼʱ�䣺");
		lab2 = new JLabel("��ֹʱ�䣺");
		lab3 = new JLabel("�û�����");
		lab4 = new JLabel("�����ţ�");
		jtf1 = new JTextField(20);
		jtf2 = new JTextField(20);
		jtf3 = new JTextField(18);
		jtf4 = new JTextField(18);

		bt1 = new JButton("��ѯ");
		bt1.addActionListener(new QueryNo1());

		bt2 = new JButton("��ѯ");
		bt2.addActionListener(new QueryNo2());

		bt3 = new JButton("��ѯ");
		bt3.addActionListener(new QueryNo3());
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		JPanel top = new JPanel(new GridLayout(2,1));
		JPanel line1 = new JPanel();
		line1.add(lab1);
		line1.add(jtf1);
		line1.add(lab2);
		line1.add(jtf2);
		line1.add(bt1);
		JPanel line2 = new JPanel();
		line2.add(lab3);
		line2.add(jtf3);
		line2.add(bt2);
		line2.add(lab4);
		line2.add(jtf4);
		line2.add(bt3);
		top.add(line1);
		top.add(line2);
		frame.add(top,"North");
		frame.add(jsp);

		frame.setSize(800,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);
	}
	public static void main(String[] args){
		new QueryFrame();
	}

	class QueryNo1 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String start = jtf1.getText();
			String end = jtf2.getText();
			if(start.trim().length() == 0 || end.trim().length() == 0){
				JOptionPane.showMessageDialog(frame,"����?");
				return;
			}
			try{
				Request req = new Request();
				req.setAskNo(2003);
				req.setParameter("start",start);
				req.setParameter("end",end);
				//���Ӻ�̨
				Response res = ClientNetTools.sendAndRead(req);
				//
				System.out.println("��̨���ӳɹ���");
				int result = res.getResult();
				//
				System.out.println("��̨���ؽ���ɹ���");
				if(result == 0){
					JOptionPane.showMessageDialog(frame,"��ѯ�ɹ�~");
					Object obj = res.getData("data");
					java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)obj;
					jta.setText("");
					list.forEach((x) -> {
						x.forEach((k,v) -> {
							jta.append(v.toString());
							jta.append("\t");
						});
						jta.append("\n");
					});

				}else{
					JOptionPane.showMessageDialog(frame,"��ѯʧ��~");
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	class QueryNo2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String username = jtf3.getText();
			if(username.length() == 0){
				JOptionPane.showMessageDialog(frame,"����?");
				return;
			}
			try{
				Request req = new Request();
				req.setAskNo(2001);
				req.setParameter("username",username);
				//���Ӻ�̨
				Response res = ClientNetTools.sendAndRead(req);
				System.out.println("�������ֲ�ѯ���������Ӻ�̨�ɹ���");
				int result = res.getResult();
				System.out.println("�������ֲ�ѯ�������Ѿ�������ӽ����");
				if(result == 0){
					JOptionPane.showMessageDialog(frame,"��ѯ�ɹ�~");
					Object obj = res.getData("data");
					System.out.println("��ѯ�ɹ�������no2��"+obj);
					java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)obj;
					jta.setText("");
					list.forEach((x) -> {
						x.forEach((k,v) -> {
							jta.append(v.toString());
							jta.append("\t");
						});
						jta.append("\n");
					});

				}else{
					JOptionPane.showMessageDialog(frame,"��ѯʧ��~");
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	class QueryNo3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae){
			String did = jtf4.getText();
			if(did.length() == 0){
				JOptionPane.showMessageDialog(frame,"����?");
				return;
			}
			try{
				Request req = new Request();
				req.setAskNo(2002);
				req.setParameter("did",did);
				//���Ӻ�̨
				Response res = ClientNetTools.sendAndRead(req);
				System.out.println("���ݶ�����ѯ���������Ӻ�̨�ɹ���");
				int result = res.getResult();
				System.out.println("���ݶ�����ѯ�������Ѿ�������ӽ����");
				if(result == 0){
					JOptionPane.showMessageDialog(frame,"��ѯ�ɹ�~");
					Object obj = res.getData("data");
					System.out.println("��ѯ�ɹ����������ݣ�"+obj);
					java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)obj;
					jta.setText("");
					list.forEach((x) -> {
						x.forEach((k,v) -> {
							jta.append(v.toString());
							jta.append("\t");
						});
						jta.append("\n");
					});

				}else{
					JOptionPane.showMessageDialog(frame,"��ѯʧ��~");
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}