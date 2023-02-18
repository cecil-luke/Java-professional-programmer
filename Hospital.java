import java.util.*;
import java.time.*;
import java.math.*;
import java.time.LocalDateTime.*;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.stream.*;
import java.time.format.*;
public class Hospital{
	//װ����
	static HashSet<Suffer> sufferSet = new HashSet<>();
	//ҩ��Ϣ
	static ArrayList<Drug> drugList = new ArrayList<>();
	//ҽ��
	static ArrayList<Doctor> docList = new ArrayList<>();
	//װ���Һ��ӿ�������
	static TreeMap<String,ArrayList<String>> map = new TreeMap<>();
	static Scanner sc = new Scanner(System.in);
	//��¼��
	static Suffer suffer;
    static{
        Drug d1 = new Drug("��������������",new BigDecimal("18.12"),"��ʹ,���Ƚⶾ",Type.���Ƚⶾ);
        Drug d2 = new Drug("άC����Ƭ",new BigDecimal("1.8"),"����,���Ƚⶾ,ͷʹĿѣ,����,��ʹ",Type.���Ƚⶾ);
        Drug d3 = new Drug("С�������",new BigDecimal("19.6"),"ͷʹĿѣ,����,��������",Type.�ʺ���ʹ);
        Drug d4 = new Drug("����ʯɢ",new BigDecimal("21.3"),"��ʹ��к",Type.��θ����);
        Drug d5 = new Drug("ͨ���齺��",new BigDecimal("16"),"����",Type.��θ����);
        Drug d6 = new Drug("�̴�����",new BigDecimal("25.8"),"�̴�",Type.����);
        Drug d7 = new Drug("������Ƭ",new BigDecimal("3.4"),"��ʹ",Type.�ʺ���ʹ);
        Drug d8 = new Drug("���Ƭ",new BigDecimal("6"),"��ʹ",Type.�ʺ���ʹ);
        Drug d9 = new Drug("��������˪��Ƭ",new BigDecimal("9.4"),"��ǻ����,��ʹ",Type.�ʺ���ʹ);
        Drug d10 = new Drug("άA�����",new BigDecimal("14"),"��۴�",Type.����);
        Collections.addAll(drugList,d1,d2,d3,d4,d5,d6,d7,d8,d9,d10);

        Doctor d11 = new Doctor("����ҽʦ","����","�ӹ���Ĥ��λ,���в���,���ƶ��������ȼ���",9,"����",new BigDecimal("18"));
        Doctor d22 = new Doctor("����ҽʦ","����","���ָ��Ƴ�����������",4,"����",new BigDecimal("10"));
        Doctor d33 = new Doctor("����ҽʦ","��Ѫ���ڿ�","���Ĳ�,�Ľ�ʹ,���������ಡ,�ļ�������ҩ������",18,"���",new BigDecimal("22"));
        Doctor d44 = new Doctor("������ҽʦ","��Ѫ���ڿ�","��Ѫѹ,��Ѫ֬,����Ӳ��,���Ĳ����ļ�����",11,"����",new BigDecimal("15"));
        Doctor d55 = new Doctor("����ҽʦ","�ڷ��ڿ�","����,��״�ټ���,����֢,�����귢���쳣",10,"����",new BigDecimal("10"));
        Doctor d66 = new Doctor("����ҽʦ","����","���ڱ���,��Σ����,ƽ���Ѳ��Ĵ���,����������֢",8,"�޺�",new BigDecimal("22"));
        Doctor d77 = new Doctor("����ҽʦ","С�����","С��������Ƽ���,�������",8,"ׯ����",new BigDecimal("11"));
        Doctor d88 = new Doctor("������ҽʦ","С���ڿ�","��ͯѪҺ��,��������,����ϵͳ������ϵͳ����",20,"������",new BigDecimal("28"));
        Collections.addAll(docList,d11,d22,d33,d44,d55,d66,d77,d88);


        Suffer s1 = new Suffer("Dawei",15555555555L,"etoak2020",new BigDecimal("100.0"));
        Suffer s2 = new Suffer("LiNa",13333333333L,"12345",new BigDecimal("100.0"));
        Collections.addAll(sufferSet,s1,s2);

        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1,"��Ѫ���ڿ�","�ڷ��ڿ�","��ʪ��","������");
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2,"����","����","�ƻ�������");
        ArrayList<String> list3 = new ArrayList<>();
        Collections.addAll(list3,"С���ڿ�","С�����","��ͯ������");

        map.put("�ڿ�",list1);
        map.put("������",list2);
        map.put("����",list3);
    }
    //
	public static void main(String[] args){
		new Hospital().firstPanel();
	}
	//��ҳ��  ��¼ ע��
	//��ʾ ������ѡ��
	//У��
	//����û�ѡ��A ����regist()
	//����û�ѡ��B ����login()
	public void firstPanel(){
		println("A:ע���˺�","B:��¼�˺�","��ѡ��...");
		String str = checkXX(sc.next(),"[A-Ba-b]");
		switch(str.toLowerCase()){
			case "a" : regist();break;
			case "b" : login();break;
		}
	}
	//ע��
	public void regist(){
		println("�����������ֻ����룺");
		long phone = Long.parseLong(checkXX(sc.next(),"(13[0-9]|15[0-9]|18[0-9]|17[0|1|4|7])\\d{8}"));
		//У���ֻ����Ƿ�ע��� ֱ�ӷ��ػ���
		Suffer sf = checkSuffer(phone);
		if(sf !=null){//���߲�Ϊ��
			println("ע��ʧ�� ���ֻ������Ѿ���ע��");
			firstPanel();
		}
		//���ֻ�û�б�ע��
		println("���������룺");
		String passWord = checkXX(sc.next(),"\\w{5,10}");// \\w ��0-9��a-zA-Z
		Suffer sff = new Suffer(phone,passWord);
		sufferSet.add(sff);
		println("ע��ɹ���");
		firstPanel();
	}
	//��¼�ķ���
	public void login(){
		println("�����������ֻ����룺");
		long phone = Long.parseLong(checkXX(sc.next(),"(13[0-9]|15[0-9]|18[0-9]|17[0|1|4|7])\\d{8}"));
		//У���ֻ����Ƿ����
		Suffer suf = checkSuffer(phone);
		if(suf == null){//�ֻ��Ų�����
			println("��¼ʧ�� ��ǰ���ֻ�����û�б�ע���");
			firstPanel();
		}
		println("���������룺");
		//�û���������������ݿ���������Ƚ�
		String passWord = checkXX(sc.next(),"\\w{5,10}");
		checkThree(suf,passWord);
	}
	//�ڶ���ҳ��
	public void secondPanel(){
		println("a:���Ƹ�����Ϣ","b:��ֵ����","c:���Ϲ�ҩ","d:ԤԼ�Һ�","e:������ѯ","f:������ҳ��");
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
	//���Ƹ�����Ϣ
	public void chooseA(){
		String name = Optional.ofNullable(suffer.getName()).orElse("");
		println("������"+name+"	�Ƿ��޸�Y|N��");
		if(checkXX(sc.next(),"Y|N").equals("Y")){
			println("�������޸�֮���������");
			suffer.setName(checkXX(sc.next(),"[A-Za-z\u4e00-\u9fa5]{1,}"));
		}
		println("���룺"+suffer.getPassWord()+"	�Ƿ��޸�Y|N");
		if(checkXX(sc.next(),"Y").equals("Y")){
			println("�������޸�֮������룺");
			suffer.setPassWord(checkXX(sc.next(),"\\w{5,10}"));
		}
		println("�ֻ����룺" + suffer.getPhone());
		println("�˻���" + Optional.ofNullable(suffer.getMoney()).orElse(new BigDecimal("0.0")) + "Ԫ");
		secondPanel();
	}
	//��ֵ����
	public void chooseB(){
		BigDecimal money = Optional.ofNullable(suffer.getMoney()).orElse(new BigDecimal("0.0"));
		println("�˻���"+money+"Ԫ");
		println("�������ֵ��10Ԫ 20Ԫ 50Ԫ 100Ԫ...");
		BigDecimal in = new BigDecimal(checkXX(sc.next(),"[1-9]\\d{1,2}"));
		suffer.setMoney(money.add(in));
		println("��ֵ�ɹ� �˻����" + suffer.getMoney()+"Ԫ");
		secondPanel();
	}
	//���Ϲ�ҩ
	public void chooseC(){
		//��չʾ����ҩ������
		EnumSet<Type> set = EnumSet.allOf(Type.class);
		set.forEach(System.out :: println);
		println("��ѡ��ҩ�����");
		String type = sc.next();
		boolean drugHaving = checkType(type);
		if(!drugHaving){//�����ҩ�����Ͳ�����
			println("û�в��ҵ���ҩ�����...");
			secondPanel();
		}
		//ҩ��������
		// ? Type.valueOf(type) ���ַ�������typeת����ö�ٶ���
		List<Drug> list = getDrugByType(Type.valueOf(type));
		//չʾ���е�ҩ
		list.forEach(System.out :: println);//Drug���toString()
		println("������Ҫ�����ҩ������-���� û����ظ�N");
		LocalDateTime ldt1 = LocalDateTime.now();
		String str  = checkXX(sc.next(),"[A-Za-z0-9\u4e2d-\u9fa5]{1,}-\\d{1,}|N");
		for(int i = 5;i>=0;i--){
					try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
					System.out.print("\r����ʣ����Чʱ�� "+i+" �뾡��֧����");
		}
		LocalDateTime ldt2 = LocalDateTime.now();
		if(ldt1.plusSeconds(10).isBefore(ldt2)){
			println("ҳ��ͣ��ʱ��̫���Ѿ���ʱ");
			secondPanel();
		}
		if(str.equals("N")){
			secondPanel();
		}

		//����ҩ str = ҩ�� - ����
		String[] data = str.split("-");
		Drug dd = getDrugByName(list,data[0]);
		if(dd == null){
			println("û���ҵ���ҩ����Ϣ");
			secondPanel();
		}
		//ҩ����
		isPay(dd.getName(),dd.getPrice(),Integer.parseInt(data[1]));
	}
	//ԤԼ�Һ�
	public void chooseD(){
		//չʾ����
		List<String> list = getDepartment();
		list.forEach(System.out :: println);
		println("��ѡ��Һſ��ң�");
		String dep =  sc.next();
		if(!list.contains(dep)){
			println("û�в��ҵ��ÿ�����Ϣ...");
			secondPanel();
		}
		//չʾ�����������Ŀ
		List<String>temp = getSubName(dep);
		temp.forEach((x) -> System.out.println("	"+x));
		println("������Һ���Ŀ���ƣ�");
		String subName = sc.next();
		if(!temp.contains(subName)){
			println("û�в鵽����Ŀ��Ϣ...");
			secondPanel();
		}
		//�û�����Ŀ�Ŀ ��չʾ���������
		//չʾ����ҽ��
		List<Doctor> ll = getDoctor(subName);
		if(ll.size() == 0){
			println("�ÿ�������û�и���ҽ��....");
			secondPanel();
		}
		//չʾҽ����Ϣ
		ll.forEach(System.out :: println);
		println("��ѡ��Һ�ҽ����");
		LocalDateTime ld1 = LocalDateTime.now();
		String docName = sc.next();
		LocalDateTime ld2 = LocalDateTime.now();

		if(ld1.plusSeconds(10).isBefore(ld2)){
			println("ԤԼʧ�� ҳ��ͣ��ʱ��̫�� ������ԤԼ...");
			secondPanel();
		}
		//�ж��û�ѡ���ҽ���Ƿ���ҽ��������
		Doctor dd = docIsHaving(ll,docName);
		if(dd == null){
			println("û�в鵽��ҽ����Ϣ...");
			secondPanel();
		}
		isPay(dd.getName(),dd.getDiscipline(),dd.getPrice());

	}
	public void chooseE(){
		List<Order> list1 = suffer.getList().stream()
				.filter((x) -> x.getState().equals(State.��ҩ����))
				.collect(Collectors.toList());
		List<Order> list2 = suffer.getList().stream()
			.filter((x) -> x.getState().equals(State.ԤԼ����))
			.collect(Collectors.toList());
		if(suffer.getList().size() == 0){
			println("û�в��ҵ��κζ�����Ϣ...");
			secondPanel();
		}
		if(list1.size() != 0){
			println("��ҩ����:");
			show1(list1);
		}
		if(list2.size() != 0){
			println("ԤԼ����:");
			show2(list2);
		}
		secondPanel();
	}
	//չʾlist��������Ķ�����Ϣ	ԤԼ����
	public void show2(List<Order> list){
		list.forEach((x) -> {
			println("	ԤԼҽ����"+x.getDoecName());
			println("	ԤԼ��Ŀ��"+x.getSubName());
			LocalDateTime ld = x.getDate();
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(ld);
			println("	����ʱ�䣺"+time+"\n");
		});
	}
	//չʾlist��������Ķ�����Ϣ	���򶩵�
	public void show1(List<Order> list){
		list.forEach((x) -> {
			println("	ҩƷ���ƣ�"+x.getDoecName()+"	*"+x.getCount());
			println("	�����"+x.getTotalPrice());
			LocalDateTime ld = x.getDate();
			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(ld);
			println("	����ʱ�䣺"+time+"\n");
		});
	}
	//�˻�����Ƿ��ܹ�֧��
	public void isPay(String docName,String subName,BigDecimal price){
		for(int i = 5;i>=0;i--){
			try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
			System.out.print("\r֧����...."+i+"s");
		}
		println(" ");
		BigDecimal money = Optional.ofNullable(suffer.getMoney()).orElse(new BigDecimal("0.0"));
		if(money.compareTo(price) >= 0){
			println("����ɹ�");
			suffer.setMoney(money.subtract(price));
			//��ҩ����
			Order o1 = new Order(docName,subName,price,State.ԤԼ����,LocalDateTime.now());
			suffer.getList().add(o1);
			secondPanel();
		}
		println("�˻����� �Ƿ��ֵY|N");
		if(checkXX(sc.next(),"Y|N").equals("Y")){
			chooseB();//��ֵ
		}else{
			secondPanel();
		}
	}
	//��������ҽ���Ƿ���list�����������
	public Doctor docIsHaving(List<Doctor>list,String docName){
		return list.stream()
					.filter((x) -> x.getName().equals(docName))
					.findFirst()
					.orElse(null);
	}
	//��ѯ�������Ŀ��ҽ������Щ
	public List<Doctor> getDoctor(String subName){
		return docList.stream()
					.filter((x) -> x.getDiscipline().equals(subName))
					.collect(Collectors.toList());
	}
	//��ѯ����Ŀ������������Ŀ����Щ
	public List<String> getSubName(String depName){
		return map.get(depName);//ͨ������String�õ�ֵ����List
	}
	//�õ����еĸ���Ŀ���� װ���������淵��
	public List<String> getDepartment(){
		return map.keySet().stream().collect(Collectors.toList());
	}
	//��ѯ����Ƿ��㹻֧������
	public void isPay(String drugName,BigDecimal price,int count){
		//���
		BigDecimal yue = Optional.ofNullable(suffer.getMoney()).orElse(new BigDecimal("0.0"));
		//֧���Ľ��
		BigDecimal money = price.multiply(new BigDecimal(count+""));
		if(yue.compareTo(money) >= 0){
			println("֧���ɹ���");
			suffer.setMoney(suffer.getMoney().subtract(money));
			Order o1 = new Order(drugName,money,count,State.��ҩ����,LocalDateTime.now());
			suffer.getList().add(o1);
			secondPanel();
		}
		println("�˻����� �Ƿ��ֵY|N");
		if(checkXX(sc.next(),"Y|N").equals("Y")){
			chooseB();
		}
		secondPanel();
	}
	//ͨ��ҩ���͵õ�ҩ��Ϣ
	public List<Drug> getDrugByType(Type t){
		return drugList.stream()
					.filter((x) -> x.getType().equals(t))
					.collect(Collectors.toList());
	}
	//ͨ��ҩ�����Ҫ��Ϣ ���û�з���null
	public Drug getDrugByName(List<Drug> list,String drugName){
		return list.stream()
				.filter((x) -> x.getName().equals(drugName))
				.findFirst()
				.orElse(null);
	}
	//��������ҩ������Ƿ���Typeö�����д���
	public boolean checkType(String str){
		return EnumSet.allOf(Type.class).stream()
									.anyMatch((x) -> x.name().equals(str));
	}
	//����У������
	//sf��ʾ������ֻ������Ӧ�Ļ���
	//passWord:��ʾ�û����������
	public void checkThree(Suffer sf,String passWord){
		for(int i = 0;i<3;i++){
			if(passWord.equals(sf.getPassWord())){
				println("��¼�ɹ���");
				suffer = sf;//��ǰ�ĵ�¼�˺Ž���suffer
				secondPanel();
			}
			if(i == 3){
				println("�һ����룺"+sf.getPassWord());
				firstPanel();
			}
			//�����������
			println("�������������룺");
			passWord = sc.next();
		}
	}
	//ͨ�������ֻ�������Ҷ�Ӧ�Ļ�����Ϣ
	//����鵽�� �����߷���
	//���ܲ鵽�� ����null
	public Suffer checkSuffer(long phone){
		return sufferSet.stream()
					.filter((x) -> x.getPhone() == phone)
					.findFirst()
					.orElse(null);
	}
	//����У�����еĸ�ʽ�Ƿ���ȷ
	public String checkXX(String in,String out){
		if(!in.matches(out)){
			println("������Ϸ������ݣ�");
			in = sc.next();
		}
		return in;
	}
	//������ӡ
	public void println(String ... str){
		for(String x : str){
			System.out.println(x);
		}
	}


}

//ҽ��
class Doctor{
	private String job;//ְλ
	private String discipline;//רҵ
	private String hobby;//�س�
	private int workAge;//����
	private String name;//ҽ������
	private BigDecimal price;//����
	//���췽��
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
		return "	ҽ��������"+name+"\n"+
				"	��ҵʱ�䣺"+workAge+"\n"+
				"	�Һŷ��ã�"+price+"\n"+
				"	�ó�����"+hobby+"\n";
	}

}
//ҩ��
class Drug{
	private String name;
	private BigDecimal price;
	private String desc;//ҩ������
	private Type type;//ҩ�����enum
	//���췽��
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
		return "	ҩ����" + name + "\n" +
				"	�۸�" + price + "\n" +
				"	������Ϣ��" + desc + "\n" ;
	}

}
//����
class Order{
	private String doecName;//ҽ������
	private String subName;//��Ŀ��
	private String drugName;//ҩ����
	private BigDecimal totalPrice;//�ܼ۸�
	private int count;//ҩ�����
	private State state;//����״̬ Enum
	private LocalDateTime date;//ԤԼʱ��
	//���췽��1 ҽ������ ��Ŀ�� �ܼ۸� ����״̬ ԤԼʱ��
	public Order(String doecName,String subName,BigDecimal totalPrice,State state,LocalDateTime date){
		this.doecName = doecName;
		this.subName = subName;
		this.totalPrice = totalPrice;
		this.state = state;
		this.date = date;
	}
	//���췽��2 ҩ���� ҩ�� ҩ����� ����״̬ ԤԼʱ��
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
//����
class Suffer{
	private String name;
	private long phone;
	private String passWord;
	private BigDecimal money;
	private ArrayList<Order> list = new ArrayList<>();//����
	//���췽��1
	public Suffer(long phone,String passWord){
		this.phone = phone;
		this.passWord = passWord;
	}
	//���췽��2
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
//ҩ�����
enum Type{
	���Ƚⶾ,
	��θ����,
	�ʺ���ʹ,
	����;
}
enum State{
	ԤԼ����,
	��ҩ����;
}