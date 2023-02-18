import java.util.*;
import java.time.*;
import java.math.*;
import java.time.LocalDateTime.*;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.stream.*;
import java.time.format.*;
public class Hospital{
	//装患者
	static HashSet<Suffer> sufferSet = new HashSet<>();
	//药信息
	static ArrayList<Drug> drugList = new ArrayList<>();
	//医生
	static ArrayList<Doctor> docList = new ArrayList<>();
	//装科室和子科室名称
	static TreeMap<String,ArrayList<String>> map = new TreeMap<>();
	static Scanner sc = new Scanner(System.in);
	//登录人
	static Suffer suffer;
    static{
        Drug d1 = new Drug("复方金银花颗粒",new BigDecimal("18.12"),"咽痛,清热解毒",Type.清热解毒);
        Drug d2 = new Drug("维C银翘片",new BigDecimal("1.8"),"发烧,清热解毒,头痛目眩,咳嗽,咽痛",Type.清热解毒);
        Drug d3 = new Drug("小柴胡颗粒",new BigDecimal("19.6"),"头痛目眩,咳嗽,比赛流涕",Type.咽喉疼痛);
        Drug d4 = new Drug("蒙脱石散",new BigDecimal("21.3"),"腹痛腹泻",Type.肠胃不适);
        Drug d5 = new Drug("通便灵胶囊",new BigDecimal("16"),"便秘",Type.肠胃不适);
        Drug d6 = new Drug("痔疮胶囊",new BigDecimal("25.8"),"痔疮",Type.其他);
        Drug d7 = new Drug("喉舒宁片",new BigDecimal("3.4"),"咽痛",Type.咽喉疼痛);
        Drug d8 = new Drug("清火片",new BigDecimal("6"),"牙痛",Type.咽喉疼痛);
        Drug d9 = new Drug("桂林西瓜霜含片",new BigDecimal("9.4"),"口腔溃疡,咽痛",Type.咽喉疼痛);
        Drug d10 = new Drug("维A酸乳膏",new BigDecimal("14"),"痤疮粉刺",Type.其他);
        Collections.addAll(drugList,d1,d2,d3,d4,d5,d6,d7,d8,d9,d10);

        Doctor d11 = new Doctor("主任医师","妇科","子宫内膜异位,不孕不育,妇科恶性肿瘤等疾病",9,"佳林",new BigDecimal("18"));
        Doctor d22 = new Doctor("主治医师","妇科","各种妇科常见疾病治疗",4,"王杰",new BigDecimal("10"));
        Doctor d33 = new Doctor("主任医师","心血管内科","冠心病,心绞痛,先天性心脏病,心肌梗死的药物治疗",18,"李贵",new BigDecimal("22"));
        Doctor d44 = new Doctor("副主任医师","心血管内科","高血压,高血脂,动脉硬化,冠心病，心肌梗死",11,"安鹏",new BigDecimal("15"));
        Doctor d55 = new Doctor("主任医师","内分泌科","糖尿病,甲状腺疾病,肥胖症,青少年发育异常",10,"唐晓",new BigDecimal("10"));
        Doctor d66 = new Doctor("主任医师","产科","产期保健,高危妊娠,平产难产的处理,产科疑难杂症",8,"罗红",new BigDecimal("22"));
        Doctor d77 = new Doctor("主任医师","小儿外科","小儿泌尿外科疾病,尿道下裂",8,"庄子羊",new BigDecimal("11"));
        Doctor d88 = new Doctor("副主任医师","小儿内科","儿童血液病,恶性肿瘤,呼吸系统及消化系统疾病",20,"李利利",new BigDecimal("28"));
        Collections.addAll(docList,d11,d22,d33,d44,d55,d66,d77,d88);


        Suffer s1 = new Suffer("Dawei",15555555555L,"etoak2020",new BigDecimal("100.0"));
        Suffer s2 = new Suffer("LiNa",13333333333L,"12345",new BigDecimal("100.0"));
        Collections.addAll(sufferSet,s1,s2);

        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1,"心血管内科","内分泌科","风湿科","呼吸科");
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2,"妇科","产科","计划生育科");
        ArrayList<String> list3 = new ArrayList<>();
        Collections.addAll(list3,"小儿内科","小儿外科","儿童保健科");

        map.put("内科",list1);
        map.put("妇产科",list2);
        map.put("儿科",list3);
    }
    //
	public static void main(String[] args){
		new Hospital().firstPanel();
	}
	//首页面  登录 注册
	//提示 有两个选项
	//校验
	//如果用户选择A 调用regist()
	//如果用户选择B 调用login()
	public void firstPanel(){
		println("A:注册账号","B:登录账号","请选择...");
		String str = checkXX(sc.next(),"[A-Ba-b]");
		switch(str.toLowerCase()){
			case "a" : regist();break;
			case "b" : login();break;
		}
	}
	//注册
	public void regist(){
		println("请输入您的手机号码：");
		long phone = Long.parseLong(checkXX(sc.next(),"(13[0-9]|15[0-9]|18[0-9]|17[0|1|4|7])\\d{8}"));
		//校验手机号是否注册过 直接返回患者
		Suffer sf = checkSuffer(phone);
		if(sf !=null){//患者不为空
			println("注册失败 该手机号码已经被注册");
			firstPanel();
		}
		//该手机没有被注册
		println("请输入密码：");
		String passWord = checkXX(sc.next(),"\\w{5,10}");// \\w 是0-9且a-zA-Z
		Suffer sff = new Suffer(phone,passWord);
		sufferSet.add(sff);
		println("注册成功！");
		firstPanel();
	}
	//登录的方法
	public void login(){
		println("请输入您的手机号码：");
		long phone = Long.parseLong(checkXX(sc.next(),"(13[0-9]|15[0-9]|18[0-9]|17[0|1|4|7])\\d{8}"));
		//校验手机号是否存在
		Suffer suf = checkSuffer(phone);
		if(suf == null){//手机号不存在
			println("登录失败 当前的手机号码没有被注册过");
			firstPanel();
		}
		println("请输入密码：");
		//用户输入的密码与数据库里面的作比较
		String passWord = checkXX(sc.next(),"\\w{5,10}");
		checkThree(suf,passWord);
	}
	//第二个页面
	public void secondPanel(){
		println("a:完善个人信息","b:充值中心","c:网上购药","d:预约挂号","e:订单查询","f:返回首页面");
		String str = checkXX(sc.next(),"[a-fA-F]");
		switch(str){
			case "a" : chooseA();break;
			case "b" : chooseB();break;
			case "c" : chooseC();break;
			case "d" : chooseD();break;
			case "e" : chooseE();break;
			case "f" : firstPanel();
		}
	}
	//完善个人信息
	public void chooseA(){
		String name = Optional.ofNullable(suffer.getName()).orElse("");
		println("姓名："+name+"	是否修改Y|N？");
		if(checkXX(sc.next(),"Y|N").equals("Y")){
			println("请输入修改之后的姓名：");
			suffer.setName(checkXX(sc.next(),"[A-Za-z\u4e00-\u9fa5]{1,}"));
		}
		println("密码："+suffer.getPassWord()+"	是否修改Y|N");
		if(checkXX(sc.next(),"Y").equals("Y")){
			println("请输入修改之后的密码：");
			suffer.setPassWord(checkXX(sc.next(),"\\w{5,10}"));
		}
		println("手机号码：" + suffer.getPhone());
		println("账户余额：" + Optional.ofNullable(suffer.getMoney()).orElse(new BigDecimal("0.0")) + "元");
		secondPanel();
	}
	//充值中心
	public void chooseB(){
		BigDecimal money = Optional.ofNullable(suffer.getMoney()).orElse(new BigDecimal("0.0"));
		println("账户余额："+money+"元");
		println("请输入充值金额：10元 20元 50元 100元...");
		BigDecimal in = new BigDecimal(checkXX(sc.next(),"[1-9]\\d{1,2}"));
		suffer.setMoney(money.add(in));
		println("充值成功 账户余额" + suffer.getMoney()+"元");
		secondPanel();
	}
	//网上购药
	public void chooseC(){
		//先展示所有药的类型
		EnumSet<Type> set = EnumSet.allOf(Type.class);
		set.forEach(System.out :: println);
		println("请选择药物分类");
		String type = sc.next();
		boolean drugHaving = checkType(type);
		if(!drugHaving){//输入的药物类型不存在
			println("没有查找到该药物分类...");
			secondPanel();
		}
		//药物分类存在
		// ? Type.valueOf(type) 将字符串类型type转换成枚举对象
		List<Drug> list = getDrugByType(Type.valueOf(type));
		//展示所有的药
		list.forEach(System.out :: println);//Drug类的toString()
		println("请输入要购买的药物名称-个数 没有请回复N");
		LocalDateTime ldt1 = LocalDateTime.now();
		String str  = checkXX(sc.next(),"[A-Za-z0-9\u4e2d-\u9fa5]{1,}-\\d{1,}|N");
		for(int i = 5;i>=0;i--){
					try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
					System.out.print("\r订单剩余有效时间 "+i+" 请尽快支付！");
		}
		LocalDateTime ldt2 = LocalDateTime.now();
		if(ldt1.plusSeconds(10).isBefore(ldt2)){
			println("页面停留时间太长已经过时");
			secondPanel();
		}
		if(str.equals("N")){
			secondPanel();
		}

		//输入药 str = 药名 - 个数
		String[] data = str.split("-");
		Drug dd = getDrugByName(list,data[0]);
		if(dd == null){
			println("没有找到该药物信息");
			secondPanel();
		}
		//药存在
		isPay(dd.getName(),dd.getPrice(),Integer.parseInt(data[1]));
	}
	//预约挂号
	public void chooseD(){
		//展示科室
		List<String> list = getDepartment();
		list.forEach(System.out :: println);
		println("请选择挂号科室：");
		String dep =  sc.next();
		if(!list.contains(dep)){
			println("没有查找到该科室信息...");
			secondPanel();
		}
		//展示科室下面的项目
		List<String>temp = getSubName(dep);
		temp.forEach((x) -> System.out.println("	"+x));
		println("请输入挂号项目名称：");
		String subName = sc.next();
		if(!temp.contains(subName)){
			println("没有查到该项目信息...");
			secondPanel();
		}
		//用户输入的科目 在展示列里面出现
		//展示负责医生
		List<Doctor> ll = getDoctor(subName);
		if(ll.size() == 0){
			println("该科室下面没有负责医生....");
			secondPanel();
		}
		//展示医生信息
		ll.forEach(System.out :: println);
		println("请选择挂号医生：");
		LocalDateTime ld1 = LocalDateTime.now();
		String docName = sc.next();
		LocalDateTime ld2 = LocalDateTime.now();

		if(ld1.plusSeconds(10).isBefore(ld2)){
			println("预约失败 页面停留时间太长 请重新预约...");
			secondPanel();
		}
		//判断用户选择的医生是否在医生集合中
		Doctor dd = docIsHaving(ll,docName);
		if(dd == null){
			println("没有查到该医生信息...");
			secondPanel();
		}
		isPay(dd.getName(),dd.getDiscipline(),dd.getPrice());

	}
	public void chooseE(){
		List<Order> list1 = suffer.getList().stream()
				.filter((x) -> x.getState().equals(State.购药订单))
				.collect(Collectors.toList());
		List<Order> list2 = suffer.getList().stream()
			.filter((x) -> x.getState().equals(State.预约订单))
			.collect(Collectors.toList());
		if(suffer.getList().size() == 0){
			println("没有查找到任何订单信息...");
			secondPanel();
		}
		if(list1.size() != 0){
			println("购药订单:");
			show1(list1);
		}
		if(list2.size() != 0){
			println("预约订单:");
			show2(list2);
		}
		secondPanel();
	}
	//展示list集合里面的订单信息	预约订单
	public void show2(List<Order> list){
		list.forEach((x) -> {
			println("	预约医生："+x.getDoecName());
			println("	预约项目："+x.getSubName());
			LocalDateTime ld = x.getDate();
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(ld);
			println("	付款时间："+time+"\n");
		});
	}
	//展示list集合里面的订单信息	购买订单
	public void show1(List<Order> list){
		list.forEach((x) -> {
			println("	药品名称："+x.getDoecName()+"	*"+x.getCount());
			println("	付款金额："+x.getTotalPrice());
			LocalDateTime ld = x.getDate();
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(ld);
			println("	付款时间："+time+"\n");
		});
	}
	//账户余额是否能够支付
	public void isPay(String docName,String subName,BigDecimal price){
		for(int i = 5;i>=0;i--){
			try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
			System.out.print("\r支付中...."+i+"s");
		}
		println(" ");
		BigDecimal money = Optional.ofNullable(suffer.getMoney()).orElse(new BigDecimal("0.0"));
		if(money.compareTo(price) >= 0){
			println("付款成功");
			suffer.setMoney(money.subtract(price));
			//购药订单
			Order o1 = new Order(docName,subName,price,State.预约订单,LocalDateTime.now());
			suffer.getList().add(o1);
			secondPanel();
		}
		println("账户余额不足 是否充值Y|N");
		if(checkXX(sc.next(),"Y|N").equals("Y")){
			chooseB();//充值
		}else{
			secondPanel();
		}
	}
	//检查输入的医生是否在list集合里面存在
	public Doctor docIsHaving(List<Doctor>list,String docName){
		return list.stream()
					.filter((x) -> x.getName().equals(docName))
					.findFirst()
					.orElse(null);
	}
	//查询负责该项目的医生有哪些
	public List<Doctor> getDoctor(String subName){
		return docList.stream()
					.filter((x) -> x.getDiscipline().equals(subName))
					.collect(Collectors.toList());
	}
	//查询输入的科室下面的子项目有哪些
	public List<String> getSubName(String depName){
		return map.get(depName);//通过主键String得到值对象List
	}
	//得到所有的父项目名称 装进集合里面返回
	public List<String> getDepartment(){
		return map.keySet().stream().collect(Collectors.toList());
	}
	//查询余额是否足够支付订单
	public void isPay(String drugName,BigDecimal price,int count){
		//余额
		BigDecimal yue = Optional.ofNullable(suffer.getMoney()).orElse(new BigDecimal("0.0"));
		//支付的金额
		BigDecimal money = price.multiply(new BigDecimal(count+""));
		if(yue.compareTo(money) >= 0){
			println("支付成功！");
			suffer.setMoney(suffer.getMoney().subtract(money));
			Order o1 = new Order(drugName,money,count,State.购药订单,LocalDateTime.now());
			suffer.getList().add(o1);
			secondPanel();
		}
		println("账户余额不足 是否充值Y|N");
		if(checkXX(sc.next(),"Y|N").equals("Y")){
			chooseB();
		}
		secondPanel();
	}
	//通过药类型得到药信息
	public List<Drug> getDrugByType(Type t){
		return drugList.stream()
					.filter((x) -> x.getType().equals(t))
					.collect(Collectors.toList());
	}
	//通过药名获得要信息 如果没有返回null
	public Drug getDrugByName(List<Drug> list,String drugName){
		return list.stream()
				.filter((x) -> x.getName().equals(drugName))
				.findFirst()
				.orElse(null);
	}
	//检查输入的药物分类是否在Type枚举类中存在
	public boolean checkType(String str){
		return EnumSet.allOf(Type.class).stream()
									.anyMatch((x) -> x.name().equals(str));
	}
	//三次校验密码
	//sf表示输入的手机号码对应的患者
	//passWord:表示用户输入的密码
	public void checkThree(Suffer sf,String passWord){
		for(int i = 0;i<3;i++){
			if(passWord.equals(sf.getPassWord())){
				println("登录成功！");
				suffer = sf;//当前的登录账号交给suffer
				secondPanel();
			}
			if(i == 3){
				println("找回密码："+sf.getPassWord());
				firstPanel();
			}
			//密码输入错误
			println("请重新输入密码：");
			passWord = sc.next();
		}
	}
	//通过输入手机号码查找对应的患者信息
	//如果查到人 将患者返回
	//不能查到人 返回null
	public Suffer checkSuffer(long phone){
		return sufferSet.stream()
					.filter((x) -> x.getPhone() == phone)
					.findFirst()
					.orElse(null);
	}
	//用来校验所有的格式是否正确
	public String checkXX(String in,String out){
		if(!in.matches(out)){
			println("请输入合法的内容！");
			in = sc.next();
		}
		return in;
	}
	//批量打印
	public void println(String ... str){
		for(String x : str){
			System.out.println(x);
		}
	}


}

//医生
class Doctor{
	private String job;//职位
	private String discipline;//专业
	private String hobby;//特长
	private int workAge;//工龄
	private String name;//医生姓名
	private BigDecimal price;//工龄
	//构造方法
	public Doctor(String job,String discipline,String hobby,int workAge,String name,BigDecimal price){
		this.job = job;
		this.discipline = discipline;
		this.hobby = hobby;
		this.workAge = workAge;
		this.name = name;
		this.price = price;
	}
	public void setJob(String job){
		this.job = job;
	}
	public String getJob(){
		return job;
	}
	public void setDiscipline(String discipline){
		this.discipline = discipline;
	}
	public String getDiscipline(){
		return discipline;
	}
	public void setHobby(String hobby){
		this.hobby = hobby;
	}
	public String getHobby(){
		return hobby;
	}
	public void setWorkAge(int workAge){
		this.workAge = workAge;
	}
	public int getWorkAge(){
		return workAge;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	public BigDecimal getPrice(){
		return price;
	}
	@Override
	public String toString(){
		return "	医生姓名："+name+"\n"+
				"	从业时间："+workAge+"\n"+
				"	挂号费用："+price+"\n"+
				"	擅长领域："+hobby+"\n";
	}

}
//药类
class Drug{
	private String name;
	private BigDecimal price;
	private String desc;//药物描述
	private Type type;//药物分类enum
	//构造方法
	public Drug(String name,BigDecimal price,String desc,Type type){
		this.name = name;
		this.price = price;
		this.desc = desc;
		this.type = type;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	public BigDecimal getPrice(){
		return price;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
	public void setType(Type type){
		this.type = type;
	}
	public Type getType(){
		return type;
	}
	@Override
	public String toString(){
		return "	药名：" + name + "\n" +
				"	价格：" + price + "\n" +
				"	描述信息：" + desc + "\n" ;
	}

}
//订单
class Order{
	private String doecName;//医生姓名
	private String subName;//项目名
	private String drugName;//药物名
	private BigDecimal totalPrice;//总价格
	private int count;//药物个数
	private State state;//订单状态 Enum
	private LocalDateTime date;//预约时间
	//构造方法1 医生姓名 项目名 总价格 订单状态 预约时间
	public Order(String doecName,String subName,BigDecimal totalPrice,State state,LocalDateTime date){
		this.doecName = doecName;
		this.subName = subName;
		this.totalPrice = totalPrice;
		this.state = state;
		this.date = date;
	}
	//构造方法2 药物名 药价 药物个数 订单状态 预约时间
	public Order(String drugName,BigDecimal totalPrice,int count,State state,LocalDateTime date){
		this.drugName = drugName;
		this.totalPrice = totalPrice;
		this.count = count;
		this.state = state;
		this.date = date;
	}
	public void setDoecName(String doecName){
		this.doecName = doecName;
	}
	public String getDoecName(){
		return doecName;
	}
	public void setSubName(String subName){
		this.subName = subName;
	}
	public String getSubName(){
		return subName;
	}
	public void setDrugName(String drugName){
		this.drugName = drugName;
	}
	public String getDrugName(){
		return drugName;
	}
	public void setTotalPrice(BigDecimal totalPrice){
		this.totalPrice = totalPrice;
	}
	public BigDecimal getTotalPrice(){
		return totalPrice;
	}
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
	public void setState(State state){
		this.state = state;
	}
	public State getState(){
		return state;
	}
	public void setLocalDateTime(LocalDateTime date){
		this.date = date;
	}
	public LocalDateTime getDate(){
		return date;
	}

}
//患者
class Suffer{
	private String name;
	private long phone;
	private String passWord;
	private BigDecimal money;
	private ArrayList<Order> list = new ArrayList<>();//订单
	//构造方法1
	public Suffer(long phone,String passWord){
		this.phone = phone;
		this.passWord = passWord;
	}
	//构造方法2
	public Suffer(String name,long phone,String passWord,BigDecimal money){
		this.name = name;
		this.phone = phone;
		this.passWord = passWord;
		this.money = money;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPhone(long Phone){
		this.phone = phone;
	}
	public long getPhone(){
		return phone;
	}
	public void setPassWord(String passWord){
		this.passWord = passWord;
	}
	public String getPassWord(){
		return passWord;
	}
	public void setMoney(BigDecimal money){
		this.money = money;
	}
	public BigDecimal getMoney(){
		return money;
	}
	public void setList(ArrayList<Order> list){
		this.list = list;
	}
	public ArrayList<Order> getList(){
		return list;
	}


}
//药物分裂
enum Type{
	清热解毒,
	肠胃不适,
	咽喉疼痛,
	其他;
}
enum State{
	预约订单,
	购药订单;
}