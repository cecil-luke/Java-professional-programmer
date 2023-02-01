import java.util.*;
public class MailList{
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Person> list = new ArrayList<>();
	static{
		朋友 x1 = new 朋友('Z',"张三",22,'男',15555555555L,"0531-6661760");
		朋友 x2 = new 朋友('X',"小明",18,'男',18756789000L);
		亲人 x3 = new 亲人('W',"王刚美",28,'女',18322009876L);
		同事 x4 = new 同事('A',"阿磊",24,'男',15559501427L,"0564-6021117");
		亲人 x5 = new 亲人('Z',"周凯",38,'男',18765432189L);
		朋友 x6 = new 朋友('Z',"周树人",99,'男',17499990427L);
		朋友 x7 = new 朋友('Z',"张三",22,'男',15555445555L);
		Collections.addAll(list,x1,x2,x3,x4,x5,x6,x7);
	}
	public static void main(String[] args){

		a:while(true){
			println("A:查看所有联系人","B:查找某一位联系人","C:添加联系人","D:删除联系人","E:修改联系人","F:结束所有操作");
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
		System.out.println("a:请问您是需要按照首字母分裂显示");
		System.out.println("b:请问您是需要按照类型分类显示");
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
			String[] data = {"亲人","同事","朋友","其他"};
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
	//检查
	public static String check(String in,String out){
		//字符串对象.matches(正则表达式)：判断左边字符串是否符合右边的表达式
		while(!in.matches(out)){
			System.out.println("请输入合法内容！");
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
		System.out.println("请问您是需要按照名字精确查找/手机号码进行模糊查询");
		String str = sc.next();
		if(str.replaceAll("[0-9]","").length() == str.length()){
			int count = 0;
			for(Person p : list){
				count++;
				if(p.getName().equals(str)){
					println("联系人姓名："+p.getName());
					println("联系人手机号码："+p.getPhone());
					println("联系人的座机号："+p.getZuoJi());
					String qh = p.getZuoJi().split("-")[0];
					String cityName = getCityName(qh);
					println("座机所在城市："+cityName);
				}else{
					if(count == list.size()){
						println("查找失败！");
					}
				}
			}
		}else if(str.replaceAll("[0-9]","").length() == 0){
			int count = 0;
			for(Person p : list){
				count++;
				String phone = p.getPhone()+"";
				if(phone.contains(str)){
					println("联系人姓名："+p.getName());
					println("联系人手机号码："+p.getPhone());
					println("联系人的座机号："+p.getZuoJi());
					String qh = p.getZuoJi().split("-")[0];
					String cityName = getCityName(qh);
					println("座机所在城市："+cityName);
				}else{
					if(count == list.size()){
						println("查找失败！");
					}
				}
			}
		}else{
			println("查找失败！");
		}

	}
	//通过座机号码得到城市名字
	public static String getCityName(String qh){
		String[] data = {"0564-六安","020-天津","0531-济南","0532-青岛","0538-泰安"};
		for(String str : data){
			String[] temp = str.split("-");
			if(temp[0].equals(qh)){
				return temp[1];
			}
		}
		return "未知城市";
	}
	public static void chooseC(ArrayList<Person> list){
		println("请选择联系人分类[A:亲人 B:同事 C:朋友 D:其他]");
		String sort = sc.next();
		sort = check(sort,"[A-D]");
		println("请输入联系人大写首字母：");
		String szmz = sc.next();
		szmz = check(szmz,"[A-Z]");
		char szm = szmz.charAt(0);
		println("请输入联系人姓名：");
		String name = sc.next();
		name = checkName(name);

		println("请输入联系人年龄：");
		int age = Integer.parseInt(check(sc.next(),"0|100|[1-9]\\d{0,1}"));

		println("请选择联系人性别：[A:男 B:女]");
		String gender = sc.next();
		gender = check(gender,"[A-B]");
		char gen = gender.equals("A") ? '男' : '女';

		println("请输入联系人手机号码：");
		long phone = Long.parseLong(check(sc.next(),"(13[0-9]|15[0-9]|18[0-9]|17[0|1|4|7])\\d{8}"));

		for(Person p : list){
			if(phone == p.getPhone()){
				println("添加失败,当前联系人已经存在！");
				return;
			}
		}
		println("请输入座机号码[没有回复N]");
		String zuoJi = check(sc.next(),"N|0\\d{2,3}-\\d{7}"); // \\d 表示0-9的范围

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
		println("随机验证码是："+yzm+"	请输入验证码：");

		String yz = sc.next();
		while(!(yz.equalsIgnoreCase(yzm))){
			println("请重新输入正确的验证码：");
			yz = sc.next();
		}

		if(sort.equals("A")){
			亲人 ren = new 亲人(szm,name,age,gen,phone,zuoJi);
			list.add(ren);
		}else if(sort.equals("B")){
			同事 ren = new 同事(szm,name,age,gen,phone,zuoJi);
			list.add(ren);
		}else if(sort.equals("C")){
			朋友 ren = new 朋友(szm,name,age,gen,phone,zuoJi);
			list.add(ren);
		}else if(sort.equals("D")){
			其他类型 ren = new 其他类型(szm,name,age,gen,phone,zuoJi);
			list.add(ren);
		}
		println("添加成功！");


	}
	//检查名字
	public static String checkName(String name){
		while(name.matches("习近平|周恩来|毛泽东|江泽明|胡锦涛")){
			println("请重新输入合法的名字");
			name = sc.next();
		}
		return name;
	}
	public static void chooseD(ArrayList<Person> list){
		showAll(list);
		println("请输入要被删除的学生的编码是：");
		int id = Integer.parseInt(check(sc.next(),"[0-9]"));
		if(!(id >= 0 && id <list.size())){
			println("编码不存在,删除失败！");
			return;
		}else{
			list.remove(id);
			println("删除成功！");

		}


	}
	public static void showAll(ArrayList<Person> list){
		println("编码	姓名	年龄	手机号码");
		for(int i = 0;i<list.size();i++){
			println(i+"	"+list.get(i).getName()+"	"+list.get(i).getAge()+"	"+(list.get(i).getPhone()+"").substring(0,3)+"****"+(list.get(i).getPhone()+"").substring(7));

		}
	}

	public static void chooseE(ArrayList<Person> list){
		println("请输入要修改联系人大写首字母-姓名-手机号码[中间用-隔开]");
		String str = sc.next();
		if(DataIsHaving(str,list)){
			println("请输入修改之后联系人首写字母-姓名-手机号码[中间用-个隔开]");
			String data = sc.next();
			String[] Data = data.split("-");
			data = check(Data[0],"[A-Z]");
			//名字是否合法
			while(nameLegal(Data[1])){
				println("名字不合法!请重新输入修改之后联系人首写字母-姓名-手机号码[中间用-个隔开]");
				data = sc.next();
				String[] Data1 = data.split("-");
				data = check(Data1[0],"[A-Z]");
				Data[1] = Data1[1];
				ArrayList<String> list01 = new ArrayList<>();
				Collections.addAll(list01,"138","133","150","158","159","180","188","187","155");
				if(!list01.contains(Data1[2].substring(0,3))){
					println("手机号码不合法!请重新输入修改之后联系人首写字母-姓名-手机号码[中间用-个隔开]");
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
					println("修改失败 当前联系人手机号已经存在");
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
					println("修改成功！");
				}
			}

		}else{
			println("修改失败 当前联系人不存在!");
		}

	}
	public static boolean nameLegal(String name){
		ArrayList<String> temp = new ArrayList<>();
		Collections.addAll(temp,"习近平","周恩来","毛泽东","江泽民","胡锦涛");
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
	private char szm;//首字母
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
		this.zuoJi = "无座机号码";
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
class 朋友 extends Person{
	public 朋友(char szm,String name,int age,char gender,long phone){
		super(szm,name,age,gender,phone);
	}
	public 朋友(char szm,String name,int age,char gender,long phone,String zuoJi){
		super(szm,name,age,gender,phone,zuoJi);
	}
}
class 亲人 extends Person{
	public 亲人(char szm,String name,int age,char gender,long phone){
		super(szm,name,age,gender,phone);
	}
	public 亲人(char szm,String name,int age,char gender,long phone,String zuoJi){
		super(szm,name,age,gender,phone,zuoJi);
	}
}
class 同事 extends Person{
	public 同事(char szm,String name,int age,char gender,long phone){
		super(szm,name,age,gender,phone);
	}
	public 同事(char szm,String name,int age,char gender,long phone,String zuoJi){
		super(szm,name,age,gender,phone,zuoJi);
	}
}
class 其他类型 extends Person{
	public 其他类型(char szm,String name,int age,char gender,long phone){
		super(szm,name,age,gender,phone);
	}
	public 其他类型(char szm,String name,int age,char gender,long phone,String zuoJi){
		super(szm,name,age,gender,phone,zuoJi);
	}
}