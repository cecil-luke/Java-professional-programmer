import java.util.*;
import java.math.*;
public class StudentSystem{

	static Student[] data = new Student[5];
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args){
		System.out.println("==================");
		System.out.println("ѧ����Ϣ����ϵͳ");
		System.out.println("==================");

		data[0] = new Student("������",29,'Ů',90);
		data[1] = new Student("����",18,'��',77);
		data[2] = new Student("���",22,'��',50);
		data[3] = new Student("��ǿǿ",25,'��',32);
		data[4] = new Student("��ѩ",26,'Ů',97);

		a:while(true){
			System.out.println("O:չʾ��ǰϵͳ����ѧ����Ϣ");
			System.out.println("A:���շ�����������");
			System.out.println("B:���շ�����������");
			System.out.println("C:���ѧ����Ϣ");
			System.out.println("D:ɾ��ѧ����Ϣ");
			System.out.println("E:�޸�ѧ����Ϣ");
			System.out.println("F:�������в���");
			String Str = sc.next();
			if(Str.equals("A")||Str.equals("a")){
				chooseA();
			}else if(Str.equals("B")||Str.equals("b")){
				chooseB();
			}else if(Str.equals("C")||Str.equals("c")){
				chooseC();
			}else if(Str.equals("D")||Str.equals("d")){
				chooseD();
			}else if(Str.equals("E")||Str.equals("e")){
				chooseE();
			}else if(Str.equals("F")||Str.equals("f")){
				break a;
			}else if(Str.equals("O") || Str.equals("o")){
				showAll();
			}
		}
	}
	public static void chooseA(){
		for(int i = 0;i<data.length-1;i++){
			for(int j = 0;j<data.length-1-i;j++){
				if(data[j].getScore()>data[j+1].getScore()){
					Student z = data[j];
					data[j] = data[j+1];
					data[j+1] = z;
				}
			}
		}
		showAll();
	}
	public static void showAll(){
		System.out.println("����"+"	����"+"	����"+"	�Ա�"+"	����");
		for(int i = 0;i<data.length;i++){
			System.out.println(i+"	"+data[i].getName()+"	"+data[i].getAge()+
				"	"+data[i].getGender()+"	"+data[i].getScore());
		}
	}
	public static void chooseB(){
		for(int i = 0;i<data.length-1;i++){
			for(int j = 0;j<data.length-1-i;j++){
				if(data[j].getScore()<data[j+1].getScore()){
					Student z = data[j];
					data[j] = data[j+1];
					data[j+1] = z;
				}
			}
		}
		showAll();
	}
	public static void chooseC(){
		System.out.println("������ѧ����������");
		String name = sc.next();
		System.out.println("������ѧ�������䣺");
		int age = sc.nextInt();
		System.out.println("��ѡ��ѧ�����Ա�[A:��	B:Ů]");
		String gender = sc.next();
		char gen = gender.equals("A") || gender.equals("a") ? '��' : 'Ů';
		System.out.println("������ѧ���ķ�����");
		int score = sc.nextInt();

		Student stu = new Student(name,age,gen,score);

		if(isHaving(stu)){
			System.out.println("ѧ����Ϣ���ڣ����ʧ�ܣ�");
		}else{
			data = Arrays.copyOf(data,data.length+1);
			data[data.length-1] = stu;
			System.out.println("��ӳɹ���");
		}
	}
	public static boolean isHaving(Student stu){
		for(Student x : data){
			if(stu.getName().equals(x.getName()) && stu.getAge() == x.getAge() &&
				stu.getGender() == x.getGender() && stu.getScore() == x.getScore()){

				return true;
			}
		}
		return false;
	}
	public static void chooseD(){
		showAll();
		System.out.println("������Ҫɾ����ѧ�����룺");
		int i = sc.nextInt();
		if(i>=0 && i<=data.length-1){
			System.arraycopy(data,i+1,data,i,data.length-1-i);
			data = Arrays.copyOf(data,data.length-1);
			System.out.println("ɾ���ɹ���");
			showAll();
		}else{
			System.out.println("ɾ��ʧ�ܣ����벻����");
		}
	}
	public static void chooseE(){
		showAll();
		System.out.println("��ѡ����Ҫ�޸ĵ�ѧ�����룺");
		int i = sc.nextInt();
		if(i>=0 && i<=data.length-1){
			System.out.print("�޸�ǰ������"+data[i].getName()+"	�������޸ĺ��������");
			String name = sc.next();
			data[i].setName(name);
			System.out.print("�޸�ǰ���䣺"+data[i].getAge()+"	�������޸ĺ�����䣺");
			int age = sc.nextInt();
			data[i].setAge(age);
			System.out.print("�޸�ǰ�Ա�"+data[i].getGender()+"	��ѡ���޸ĺ���Ա�[A:�� B:Ů] :");
			String gender = sc.next();
			char gen = gender.equals("A") || gender.equals("a") ? '��' : 'Ů';
			data[i].setGender(gen);
			System.out.print("�޸�ǰ������"+data[i].getScore()+"	�������޸ĺ�ķ�����");
			int score = sc.nextInt();
			data[i].setScore(score);

			System.out.println("�޸ĳɹ���");
		}else{
			System.out.println("�޸�ʧ�ܣ����벻����");
		}
	}
}
class Student{
	private String name;
	private int age;
	private char gender;
	private int score;
	public Student(String name,int age,char gender,int score){
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.score = score;
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

	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return score;
	}
}