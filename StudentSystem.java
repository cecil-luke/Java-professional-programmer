import java.util.*;
import java.math.*;
public class StudentSystem{

	static Student[] data = new Student[5];
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args){
		System.out.println("==================");
		System.out.println("学生信息管理系统");
		System.out.println("==================");

		data[0] = new Student("周晓丽",29,'女',90);
		data[1] = new Student("王大锤",18,'男',77);
		data[2] = new Student("李饼",22,'男',50);
		data[3] = new Student("赵强强",25,'男',32);
		data[4] = new Student("王雪",26,'女',97);

		a:while(true){
			System.out.println("O:展示当前系统所有学生信息");
			System.out.println("A:按照分数升序排序");
			System.out.println("B:按照分数降序排序");
			System.out.println("C:添加学生信息");
			System.out.println("D:删除学生信息");
			System.out.println("E:修改学生信息");
			System.out.println("F:结束所有操作");
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
		System.out.println("编码"+"	姓名"+"	年龄"+"	性别"+"	分数");
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
		System.out.println("请输入学生的姓名：");
		String name = sc.next();
		System.out.println("请输入学生的年龄：");
		int age = sc.nextInt();
		System.out.println("请选择学生的性别：[A:男	B:女]");
		String gender = sc.next();
		char gen = gender.equals("A") || gender.equals("a") ? '男' : '女';
		System.out.println("请输入学生的分数：");
		int score = sc.nextInt();

		Student stu = new Student(name,age,gen,score);

		if(isHaving(stu)){
			System.out.println("学生信息存在，添加失败！");
		}else{
			data = Arrays.copyOf(data,data.length+1);
			data[data.length-1] = stu;
			System.out.println("添加成功！");
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
		System.out.println("请输入要删除的学生编码：");
		int i = sc.nextInt();
		if(i>=0 && i<=data.length-1){
			System.arraycopy(data,i+1,data,i,data.length-1-i);
			data = Arrays.copyOf(data,data.length-1);
			System.out.println("删除成功！");
			showAll();
		}else{
			System.out.println("删除失败！编码不存在");
		}
	}
	public static void chooseE(){
		showAll();
		System.out.println("请选择您要修改的学生编码：");
		int i = sc.nextInt();
		if(i>=0 && i<=data.length-1){
			System.out.print("修改前姓名："+data[i].getName()+"	请输入修改后的姓名：");
			String name = sc.next();
			data[i].setName(name);
			System.out.print("修改前年龄："+data[i].getAge()+"	请输入修改后的年龄：");
			int age = sc.nextInt();
			data[i].setAge(age);
			System.out.print("修改前性别："+data[i].getGender()+"	请选择修改后的性别[A:男 B:女] :");
			String gender = sc.next();
			char gen = gender.equals("A") || gender.equals("a") ? '男' : '女';
			data[i].setGender(gen);
			System.out.print("修改前分数："+data[i].getScore()+"	请输入修改后的分数：");
			int score = sc.nextInt();
			data[i].setScore(score);

			System.out.println("修改成功！");
		}else{
			System.out.println("修改失败！编码不存在");
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