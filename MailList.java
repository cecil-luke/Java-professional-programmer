import java.util.*;
public class MailList{
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Person> list = new ArrayList<>();
	static{
		���� x1 = new ����('Z',"����",22,'��',15555555555L,"0531-6661760");
		���� x2 = new ����('X',"С��",18,'��',18756789000L);
		���� x3 = new ����('W',"������",28,'Ů',18322009876L);
		ͬ�� x4 = new ͬ��('A',"����",24,'��',15559501427L,"0564-6021117");
		���� x5 = new ����('Z',"�ܿ�",38,'��',18765432189L);
		���� x6 = new ����('Z',"������",99,'��',17499990427L);
		���� x7 = new ����('Z',"����",22,'��',15555445555L);
		Collections.addAll(list,x1,x2,x3,x4,x5,x6,x7);
	}
	public static void main(String[] args){

		a:while(true){
			println("A:�鿴������ϵ��","B:����ĳһλ��ϵ��","C:�����ϵ��","D:ɾ����ϵ��","E:�޸���ϵ��","F:�������в���");
			String str = sc.next();
			str = check(str,"[A-F]");
			switch(str){
				case "A" : chooseA(list);break;
				case "B" : chooseB(list);break;
				case "C" : chooseC(list);break;
				case "D" : chooseD(list);break;
				case "E" : chooseE(list);break;
				case "F" : break a;
			}
		}
	}
	public static void chooseA(ArrayList<Person> list){
		System.out.println("a:����������Ҫ��������ĸ������ʾ");
		System.out.println("b:����������Ҫ�������ͷ�����ʾ");
		String str = sc.next();
		str = check(str,"[a-b]");
		if(str.equals("a")){
			for(char i = 'A';i<='Z';i++){
				String y = i+"";
				int count = 0;
				for(Person p : list){
					String x = p.getSzm()+"";
					if(x.equals(y)){
						count++;
						if(count == 1){
							System.out.println(x);
						}
						System.out.println(p.getName()+"	"+p.getPhone());
					}
				}
			}
		}else if(str.equals("b")){
			String[] data = {"����","ͬ��","����","����"};
			for(String type : data){
				int count = 0;
				for(Person p : list){
					if(p.getClass().getName().equals(type)){
						count++;
						if(count == 1)System.out.println("	"+type);
						System.out.println(p.getName()+"	"+p.getPhone());
					}
				}
			}
		}
	}
	//���
	public static String check(String in,String out){
		//�ַ�������.matches(������ʽ)���ж�����ַ����Ƿ�����ұߵı��ʽ
		while(!in.matches(out)){
			System.out.println("������Ϸ����ݣ�");
			in = sc.next();
		}
		return in;
	}
	public static void println(String ... str){
		for(String x : str){
			System.out.println(x);
		}
	}
	public static void chooseB(ArrayList<Person> list){
		System.out.println("����������Ҫ�������־�ȷ����/�ֻ��������ģ����ѯ");
		String str = sc.next();
		if(str.replaceAll("[0-9]","").length() == str.length()){
			int count = 0;
			for(Person p : list){
				count++;
				if(p.getName().equals(str)){
					println("��ϵ��������"+p.getName());
					println("��ϵ���ֻ����룺"+p.getPhone());
					println("��ϵ�˵������ţ�"+p.getZuoJi());
					String qh = p.getZuoJi().split("-")[0];
					String cityName = getCityName(qh);
					println("�������ڳ��У�"+cityName);
				}else{
					if(count == list.size()){
						println("����ʧ�ܣ�");
					}
				}
			}
		}else if(str.replaceAll("[0-9]","").length() == 0){
			int count = 0;
			for(Person p : list){
				count++;
				String phone = p.getPhone()+"";
				if(phone.contains(str)){
					println("��ϵ��������"+p.getName());
					println("��ϵ���ֻ����룺"+p.getPhone());
					println("��ϵ�˵������ţ�"+p.getZuoJi());
					String qh = p.getZuoJi().split("-")[0];
					String cityName = getCityName(qh);
					println("�������ڳ��У�"+cityName);
				}else{
					if(count == list.size()){
						println("����ʧ�ܣ�");
					}
				}
			}
		}else{
			println("����ʧ�ܣ�");
		}

	}
	//ͨ����������õ���������
	public static String getCityName(String qh){
		String[] data = {"0564-����","020-���","0531-����","0532-�ൺ","0538-̩��"};
		for(String str : data){
			String[] temp = str.split("-");
			if(temp[0].equals(qh)){
				return temp[1];
			}
		}
		return "δ֪����";
	}
	public static void chooseC(ArrayList<Person> list){
		println("��ѡ����ϵ�˷���[A:���� B:ͬ�� C:���� D:����]");
		String sort = sc.next();
		sort = check(sort,"[A-D]");
		println("��������ϵ�˴�д����ĸ��");
		String szmz = sc.next();
		szmz = check(szmz,"[A-Z]");
		char szm = szmz.charAt(0);
		println("��������ϵ��������");
		String name = sc.next();
		name = checkName(name);

		println("��������ϵ�����䣺");
		int age = Integer.parseInt(check(sc.next(),"0|100|[1-9]\\d{0,1}"));

		println("��ѡ����ϵ���Ա�[A:�� B:Ů]");
		String gender = sc.next();
		gender = check(gender,"[A-B]");
		char gen = gender.equals("A") ? '��' : 'Ů';

		println("��������ϵ���ֻ����룺");
		long phone = Long.parseLong(check(sc.next(),"(13[0-9]|15[0-9]|18[0-9]|17[0|1|4|7])\\d{8}"));

		for(Person p : list){
			if(phone == p.getPhone()){
				println("���ʧ��,��ǰ��ϵ���Ѿ����ڣ�");
				return;
			}
		}
		println("��������������[û�лظ�N]");
		String zuoJi = check(sc.next(),"N|0\\d{2,3}-\\d{7}"); // \\d ��ʾ0-9�ķ�Χ

		String yzm = "";
		for(int i = 0;i<5;i++){
			int id = (int)(Math.random()*3);
			if(id == 0){
				yzm += (int)(Math.random()*10);
			}else if(id == 1){
				yzm += (char)(Math.random()*26+'a');
			}else{
				yzm += (char)(Math.random()*26+'A');
			}
		}
		println("�����֤���ǣ�"+yzm+"	��������֤�룺");

		String yz = sc.next();
		while(!(yz.equalsIgnoreCase(yzm))){
			println("������������ȷ����֤�룺");
			yz = sc.next();
		}

		if(sort.equals("A")){
			���� ren = new ����(szm,name,age,gen,phone,zuoJi);
			list.add(ren);
		}else if(sort.equals("B")){
			ͬ�� ren = new ͬ��(szm,name,age,gen,phone,zuoJi);
			list.add(ren);
		}else if(sort.equals("C")){
			���� ren = new ����(szm,name,age,gen,phone,zuoJi);
			list.add(ren);
		}else if(sort.equals("D")){
			�������� ren = new ��������(szm,name,age,gen,phone,zuoJi);
			list.add(ren);
		}
		println("��ӳɹ���");


	}
	//�������
	public static String checkName(String name){
		while(name.matches("ϰ��ƽ|�ܶ���|ë��|������|������")){
			println("����������Ϸ�������");
			name = sc.next();
		}
		return name;
	}
	public static void chooseD(ArrayList<Person> list){
		showAll(list);
		println("������Ҫ��ɾ����ѧ���ı����ǣ�");
		int id = Integer.parseInt(check(sc.next(),"[0-9]"));
		if(!(id >= 0 && id <list.size())){
			println("���벻����,ɾ��ʧ�ܣ�");
			return;
		}else{
			list.remove(id);
			println("ɾ���ɹ���");

		}


	}
	public static void showAll(ArrayList<Person> list){
		println("����	����	����	�ֻ�����");
		for(int i = 0;i<list.size();i++){
			println(i+"	"+list.get(i).getName()+"	"+list.get(i).getAge()+"	"+(list.get(i).getPhone()+"").substring(0,3)+"****"+(list.get(i).getPhone()+"").substring(7));

		}
	}

	public static void chooseE(ArrayList<Person> list){
		println("������Ҫ�޸���ϵ�˴�д����ĸ-����-�ֻ�����[�м���-����]");
		String str = sc.next();
		if(DataIsHaving(str,list)){
			println("�������޸�֮����ϵ����д��ĸ-����-�ֻ�����[�м���-������]");
			String data = sc.next();
			String[] Data = data.split("-");
			data = check(Data[0],"[A-Z]");
			//�����Ƿ�Ϸ�
			while(nameLegal(Data[1])){
				println("���ֲ��Ϸ�!�����������޸�֮����ϵ����д��ĸ-����-�ֻ�����[�м���-������]");
				data = sc.next();
				String[] Data1 = data.split("-");
				data = check(Data1[0],"[A-Z]");
				Data[1] = Data1[1];
				ArrayList<String> list01 = new ArrayList<>();
				Collections.addAll(list01,"138","133","150","158","159","180","188","187","155");
				if(!list01.contains(Data1[2].substring(0,3))){
					println("�ֻ����벻�Ϸ�!�����������޸�֮����ϵ����д��ĸ-����-�ֻ�����[�м���-������]");
					data = sc.next();
					String[] Data2 = data.split("-");
					data = check(Data2[0],"[A-Z]");
					Data1[2] = Data2[2];
					Data[1] = Data2[1];
					Data[2] = Data2[2];
				}
			}
			char szm = Data[0].charAt(0);
			long phone = Long.parseLong(Data[2]);
			int count = 0;
			for(Person p : list){
				if((phone+"").equals(str.substring(str.lastIndexOf("-"),str.length()))){
					break;
				}else if(phone == p.getPhone()){
					println("�޸�ʧ�� ��ǰ��ϵ���ֻ����Ѿ�����");
					return;
				}
			}

			String[] Str = str.split("-");
			for(int i = 0;i<list.size();i++){
				if((list.get(i).getSzm()+"").equals(Str[0]) && list.get(i).getName().equals(Str[1])
						&& (list.get(i).getPhone()+"").equals(Str[2])){
					list.get(i).setSzm(szm);
					list.get(i).setName(Data[1]);
					list.get(i).setPhone(phone);
					println("�޸ĳɹ���");
				}
			}

		}else{
			println("�޸�ʧ�� ��ǰ��ϵ�˲�����!");
		}

	}
	public static boolean nameLegal(String name){
		ArrayList<String> temp = new ArrayList<>();
		Collections.addAll(temp,"ϰ��ƽ","�ܶ���","ë��","������","������");
		if(temp.contains(name)){
			return true;
		}
		return false;
	}
	public static boolean DataIsHaving(String str,ArrayList<Person> list){
		String[] data = str.split("-");
		for(Person p : list){
			if((p.getSzm()+"").equals(data[0]) && p.getName().equals(data[1]) && (p.getPhone()+"").equals(data[2])){
				return true;
			}
		}
		return false;
	}

}
class Person{
	private char szm;//����ĸ
	private String name;
	private int age;
	private char gender;
	private long phone;
	private String zuoJi;
	public Person(char szm,String name,int age,char gender,long phone){
		this.szm = szm;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.zuoJi = "����������";
	}
	public Person(char szm,String name,int age,char gender,long phone,String zuoJi){
		this(szm,name,age,gender,phone);
		this.zuoJi = zuoJi;
	}

	public void setSzm(char szm){
		this.szm = szm;
	}
	public char getSzm(){
		return szm;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return age;
	}
	public void setGender(char gender){
		this.gender = gender;
	}
	public char getGender(){
		return gender;
	}
	public void setPhone(long phone){
		this.phone = phone;
	}
	public long getPhone(){
		return phone;
	}
	public void setZuoJi(String zuoJi){
		this.zuoJi = zuoJi;
	}
	public String getZuoJi(){
		return zuoJi;
	}
}
class ���� extends Person{
	public ����(char szm,String name,int age,char gender,long phone){
		super(szm,name,age,gender,phone);
	}
	public ����(char szm,String name,int age,char gender,long phone,String zuoJi){
		super(szm,name,age,gender,phone,zuoJi);
	}
}
class ���� extends Person{
	public ����(char szm,String name,int age,char gender,long phone){
		super(szm,name,age,gender,phone);
	}
	public ����(char szm,String name,int age,char gender,long phone,String zuoJi){
		super(szm,name,age,gender,phone,zuoJi);
	}
}
class ͬ�� extends Person{
	public ͬ��(char szm,String name,int age,char gender,long phone){
		super(szm,name,age,gender,phone);
	}
	public ͬ��(char szm,String name,int age,char gender,long phone,String zuoJi){
		super(szm,name,age,gender,phone,zuoJi);
	}
}
class �������� extends Person{
	public ��������(char szm,String name,int age,char gender,long phone){
		super(szm,name,age,gender,phone);
	}
	public ��������(char szm,String name,int age,char gender,long phone,String zuoJi){
		super(szm,name,age,gender,phone,zuoJi);
	}
}