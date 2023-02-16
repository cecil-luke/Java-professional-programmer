# Day23

#### Review month1

**JCF** => Java Collections Framework => Java集合框架

			JCF
	
	   Collection			Map
	
	List        Set			SortedMap
	
		SortedSet

List :  有序 不唯一
Set : "唯一"
SortedSet : 有序 "唯一"

	ArrayList  LinkedList  Vector  Stack 
	HashSet 
	TreeSet
	HashMap   Hashtable  ConcurrentHashMap

**所有单值类型集合**统一的操作有哪些？
	add(obj)   remove(obj)  contains(obj)  size()
	clear()    iterator()   forEach()     stream()
	**addAll()**   **removeAll()**  **retainAll()**  ---> 返回差集

	小明：  手机  键盘  篮球
	小红：	 手机  键盘  口红
		
	进货：  小明.addAll(小红)


List集合有序吗？ 如何按照程序员需要的顺序进行排序呢？
	有序 默认添加顺序..

	1> Collections.sort(list,比较器);

Map集合 如何按照其值对象进行排序？
	Map集合**没法按照值对象**进行排序
	哪怕有序的Map集合 也是**只能按照主键对象**排序

	如果需求决定 一定要按照值对象排序
	可以创建一个新的Map集合 让键做值 值做键

​	

-----------------



#### 内部类：成员内部类 局部内部类 匿名内部类

 定义在一个类**类体当中**的类 被称作内部类	

​													  What

#### 为什么要使用内部类：				

​													  Why

​	**内部类**是Java当中<u>共享数据</u>最最简单的方式之一
​	还能体现类和类之间的<u>专属关系</u>~

	*: 内部类同样是类 编译之后会形成.class
		其命名规则为 外部类名字$内部类名字
		注意 同一篇代码 不能出现等价定义


内部类的分类：能够共享到外部类的哪些成员

#### **成员内部类**：	

外部类的所有[静态+非静态]成员[属性+方法]
	如何理解其与外部类的关系：
		蛔虫和牛的关系
	如何创建其对象：
		Outer.Inner in = **new Outer().new Inner();**

#### **静态内部类**：	

外部类的静态成员[属性+方法]
	如何理解其与外部类的关系：
		寄居蟹和蛤蜊壳的关系 
		房客和房东的关系
	如何创建其对象：
		Outer.Inner in = **new Outer.Inner()**;	--->并不影响外部类，所以不创建

​	**表达类与类的专属关系**

	*: 静态内部类能够共享的是外部类的静态成员
		但是静态成员本身就能直接访问
		还用内部类去共享吗？ 
		显然不用，所以，内部类的存在 并不是为了共享数据
		而是为了表达类和类的专属关系

#### **局部内部类**：

​	如果定义在外部类的静态方法中 ： 
​		**只能**共享外部类静态成员
​	如果定义在外部类的非静态方法中 ： 
​		能够共享外部类所有成员
​	另外 还有其所在的方法体当中的局部变量也能共享
​		只是JDK8.0之前必须用**final修饰** 
​		8.0开始可以**不加final** 但**默认就是final**			传参，并放到构造方法中

	如何理解其与外部类的关系：
		老师和学生的关系（特定范围内的关系）
	如何创建其对象：
		Inner in = new Inner();
		*: 有位置限定： 定义结束之后 所在方法结束之前

#### **匿名内部类：**

​	#: 如果生个孩子就是为了拿去卖钱的
​			就不用费尽心思给他起名字
​	#: 某些场景下 我们自己的名字根本不重要
​			反而父母或者长辈的名字才重要

		new 接口(){
			完成抽象方法的具体实现
		};
	
		或者
	
		new 父类(给父类构造方法传参){
			完成抽象方法的具体实现
		};
	
	*：匿名内部类能够共享外部类的哪些成员
		取决于定义它的位置 
		可能等价于上述三种的一种

*： 匿名内部类能够共享外部类的哪些成员
	**取决于定义它的位置** 
	可能等价于上述三种的一种

-----------

		//匿名内部类的语法创建一个医生对象
		Person bq = new Person("扁鹊"){
			@Override
			public void eat(){
				System.out.println("医生吃火腿");
			}
		};
	
		bq.eat();
---------------------



Java当中如何完成数据共享
	1.使用静态变量完成共享		Okay
	2.使用参数传递完成共享		Okay
	3.使用内部类完成共享		Okay


一个凄美的爱情故事
	一个小男孩 和 一个小女孩
	他们约会去看电影 买完电影票之后 只够买一杯大可乐
	于是找服务员要了两根吸管 共享同一杯可乐...
	
	广寒宫	->	电影院	Cinema
	貂蝉	->	可乐    cola
	吕布	->	男孩	Boy
	董卓	->	女孩	Girl



# Day24

#### review

**内部类**：定义在一个类 **类体**当中的类 被称作内部类

内部类的**作用**：
	1.用来共享数据
	2.用来表达类和类的专属关系

内部类的分类：
	成员内部类：共享外部类<u>所有</u>[静态+非静态]<u>成员</u>[属性+方法]
	静态内部类：共享外部类所有<u>静态成员</u>
	局部内部类：
		定义在静态方法中：只能共享静态成员
		定义在非静态方法中：能够共享<u>所有成员</u>
		由于局部内部类定义在**方法体**当中
		所以还能共享其所在方法体当中的局部变量
		只是**JDK8.0之前**必须加**final修饰**
		8.0开始可以不加final但**默认**就是**最终变量**

	匿名内部类：取决于定义它的位置 可能等价于上述三种的某一种

如何创建内部类对象：
	1成员内部类： Outer.Inner in = new Outer().new Inner();
	2静态内部类： Outer.Inner in = new Outer.Inner();
	3局部内部类： Inner in = new Inner();
			*:有位置限定 定义完成之后 所在方法结束之前
	4匿名内部类： new 父类/接口(){
									完成抽象方法的具体实现;
		     				 }

共享数据的三种方式：
	1.使用静态变量
	2.使用参数传递(不要只会构造方法 还得会setter)
	3.使用内部类



#### Exception（异常）

异常 Exception => 例外
	Exception : 程序运行过程当中出现的**例外情况**而已


Java当中所有"问题"的体系结构
			
			Throwable
			[可以向外抛出的]


		Error		Exception
		[错误]		[异常]
	
				RuntimeException
				[运行时异常]

#### ***:** **Error 和 Exception 的区别?**

​	**Error** 是错误 通常是指由于硬件环境或者系统原因
​			导致的相对较严重的问题
​			OutOfMemoryError..

​	**Exception** 是异常 程序运行过程当中出现的例外情况

#### ***:** **运行时异常** 和 **非运行时异常**的区别?

​	1、非运行时异常 在编译的时候 就要求程序员必须给出处理方案
​		否则编译无法通过 它们都直接**继承Exception**
​	2、运行时异常 在编译的时候不要求给出处理方案 编译直接通过
​		问题会在运行时直接体现出来 它们**继承RuntimeException**

<!--// SCJP => OCJP => Oracle Certified Java Programmer-->

***: 常见的运行时异常 11种**
	**1**运算符：
		ArithmeticException = **算术异常**						<!--System.out.println(7 / 0.0);  //Infinity 无穷-->

​																			<!--System.out.println(0.0 / 0.0);//NaN = Not a Number 不是一个数-->
​		***System.out.println(5 / 0);***



​	**2**数组：
​		NegativeArraySizeException = **负数的数组大小异常**

​		***int[] data1 = new int[-5];***

​		ArrayIndexOutOfBoundsException = **索引值超出边界异常**

​        ***int[] data2 = new int[]{11,22,33};***
​        ***System.out.println(data2[3]);***

​	**3**字符串：
​		NullPointerException = **空指针异常**

​		***String s1 = null;***
​		***System.out.println(s1.length());***

​		StringIndexOutOfBoundsException = **字符串索引值超出边界异常**

​		***String s2 = "ETOAK";***
​		***System.out.println(s2.charAt(5));***

​		NumberFormatException = **数字格式异常**

​		***String s3 = "123a";***
​		***int price = Integer.parseInt(s3);***
​		***System.out.println(price + 5);***

​	**1**强制类型转换：
​		ClassCastException   =  **类型造型异常**

​		***Object stu = new Student();***
​		***Cacti cc = (Cacti)stu;***

<!--Java当中的哪些语法涉及到 继承树的关系校验-->	

		// 强制类型转换    ()
		// instanceof
		// ==
​	**4**集合：
​		IllegalArgumentException = **非法参数异常**

​		***List<Integer> list1 = new ArrayList<>(-7);***

​		IndexOutOfBoundsException = **索引值超出边界异常**

​		***List<Integer> list2 = new ArrayList<>();***
​		***Collections.addAll(list2,11,22,33);***
​		***System.out.println(list2.get(3));***

​		IllegalStateException = **非法状态异常**

		List<Integer> list3 = new ArrayList<>();
		Collections.addAll(list3,11,22,33,44,55);
		Iterator<Integer> car = list3.iterator();
		car.remove();
​		ConcurrentModificationException = **并发修改异常**
​		

#### **为什么要处理异常?：**

​	A.如果是非运行时异常 不做处理 编译都无法通过
​	B.一旦程序(线程)运行过程当中出现**未作处理**的异常
​		虚拟机将直接中断程序(该线程)的执行

#### **如何处理异常?：**

​	1.抛还上级	throws
​		throws 出现在方法签名的最后 
​		用于表达 本方法当中出现指定种类的异常
​		本方法当中不做处理 抛还给调用的上级进行处理

		足以解决A 但无力解决B
	
	2.自行处理	try catch finally
		try{
			可能出现异常的语句;
			通常只写一句可能导致异常的语句;
			除非需求决定前者出现异常 后者跳过
		}catch(要捕获的异常类型 异常代号){
			对捕获到的异常进行处理
			0.隐瞒不报
			1.简要的审: 
				异常代号.getMessage()
			2.详细的审: 
				异常代号.printStackTrace()
		}finally{
			无论是否出现异常 最终都要执行的操作
			通常是释放和关闭资源的操作
		}
	
		*: 一个try 后面可以跟上多个不同的catch
			但是要求 类型必须前小后大 后者并列
			总之不能前大后小 否则后者没有意义
	
		*: 在JDK7.0之前 如果捕获到两种不同异常
			要做的处理是相同的 也必须写多个catch分支
			但是从JDK7.0开始 支持多重catch
			一个catch捕获多种类型 中间用 | 
	
		*: 我们不要在finally当中写return语句
			否则try 和 catch当中的所有return
			都失去意义了


如何在程序本没有异常出现的时候 主动制造异常出现的场景：

	throw 出现在方法体当中 用于在没有异常的时候
		主动制造异常出现的场景

#### ***：throw和throws的区别**

**throws** 出现在<u>方法签名的最后</u> 
	用于表达 本方法当中出现指定种类的异常
	本方法当中不做处理 抛还给调用的上级进行处理
**throw** 出现在方法体当中 用于在没有异常的时候
	主动制造异常出现的场景

#### **如何自定义异常：**

​	自己开发一个类 选择**继承Exception / RuntimeException**
​			    非运行时异常 / 运行时异常
​	然后 在其构造方法的首行使用**super()** 指定异常的描述信息

#### 五个常见异常：

**Plus01:** 
	当类体当中的静态变量是调用有异常声明的方法完成赋值的时候
	我们不能在类体当中直接try catch 更不能在类的签名上throws
	此时如果想要编译通过 <u>必须借助</u>**静态初始化块**	**static{ }**
	在静态初始化块当中使用**try catch**进行处理

​	***：如果是个非静态变量 则可以使用** **非静态初始化块 或者 构造方法**	**{ }**

~~~ java
public class TestExceptionPlus1{
	public static void main(String[] args){

	}
}
class A{

	static int i;
	static{
		try{
			i = get();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static int get()throws Exception{
		int x = (int)(Math.random()*5);//0-4
		if(x == 2 || x == 4){
			throw new Exception("生成的数字不吉利");
		}
		return x;
	}
}
~~~



**Plus02:**
	在完成方法覆盖的时候 如果父类当中的方法没有任何throws声明
	子类在覆盖该方法的时候 能不能声明抛出异常呢？
	可以 但是只能**向外抛出**运行时异常 - 这样的行为没有意义
	因为在**Java当中<u>每个方法</u>都默认抛出所有的运行时异常**
	相当于 每个方法最后都有我们看不见的throws RuntimeException

~~~ java
public class TestExceptionPlus2{
	public static void main(String[] args)throws RuntimeException{
		C cc = new C();
		cc.test();
	}
}
class A{
	public void test(){
		System.out.println("这是父类的test()方法");
	}
}
class C extends A{
	@Override
	public void test()throws RuntimeException{
		System.out.println("这是子类的test()方法");
	}
}
~~~



**Plus03:**
	当我们的代码当中出现连续的多行语句都有异常声明的时候
	我们需要无论前者执行是否顺利 后者都要尝试去执行
	则必须借助try catch finally的finally当中 嵌套使用try catch
	我们把这种语法戏称为 **连环try... [学IO流的时候要用~]**

~~~ java
public class TestExceptionPlus3{
	public static void main(String[] args){
		SLT no1 = new SLT();
		SLT no2 = new SLT();
		SLT no3 = new SLT();

		try{
			no1.close();
			return;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				no2.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					no3.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
class SLT{
	public void close()throws Exception{
		int x = (int)(Math.random()*2);//0 or 1
		if(x == 1){
			throw new Exception("拧坏了关不上的异常");
		}
		System.out.println("正常的关闭了水龙头");
	}
}
~~~



**Plus04:**
	为了进行异常处理而添加的try catch语法结构是有**{}**的
	这对儿大括号同样能够控制变量的作用范围
	所以如果我们的某些变量 在下文程序当中还要继续使用
	就不能在try{}当中进行定义
	应当在try{}前面完成定义 并且以默认值赋值
	try{}当中只做重新赋值 不做变量定义

~~~java
public class TestExceptionPlus4{
	public static void main(String[] args){
		int num = 0;
		try{
			num = get();

		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(num);

	}

	public static int get()throws Exception{
		int x = (int)(Math.random()*5);//0-4
		if(x == 2 || x == 4){
			throw new Exception("生成的数字不吉利");
		}
		return x;
	}
}
~~~


**Plus05:**
	在某些场景下 **学会使用<u>异常处理的机制</u>代替传统的分支判断**
		**会有奇效！！！** 【某些场景 发生在下周... 和100有关】

~~~java
public class TestExceptionPlus5{
	public static void main(String[] args){

	}
	public static boolean check(String str){
		//如果str当中全是数字 则返回true 反之false
		try{
			Integer.parseInt(str);
			return true;
		}catch(Exception e){
			return false;
		}

		/*
		for(int i = 0;i<str.length();i++){
			char c = str.charAt(i);
			if(c < '0' || c> '9'){
				return false;
			}
		}
		return true;
		*/
	}
}
~~~



# day25

#### Review

异常：程序运行过程当中出现的例外情况而已

Java当中所有问题的体系结构
			Throwable
			[可以向外抛出的]

		Error		Exception
	
				RuntimeException

#: **Error和Exception的区别?**
	Error通常是指由于硬件环境或者系统原因导致的
				相对较严重的问题 
	Exception就是运行过程当中出现的例外情况而已

#: **非运行时异常和运行时异常的区别?**
	<u>非运行时异常</u>在编译的时候就必须要求给出处理方案
				否则编译无法通过
	<u>运行时异常</u>在编译的时候不要求给出处理方案
		编译可以直接通过 问题在运行时直接体现出来

#: 常见的运行时异常：
	1 运算符
		ArithmeticException = 算术异常
	2 数组
		NegativeArraySizeException = 负数数组大小异常
		ArrayIndexOutOfBoundsException = 数组索引值超出边界异常
	3 字符串
		NullPointerException = 空指针异常
		StringIndexOutOfBoundsException = 字符串索引值超出边界异常
		NumberFormatException = 数字格式异常
	1 类型转换
		ClassCastException = 类型造型异常
	4 集合
		IllegalArgumentException = 非法参数异常  **illegalArgumentException**
		IllegalStateException = 非法状态异常
		IndexOutOfBoundsException = 索引值超出边界异常
		ConcurrentModificationException = 并发修改异常


#: 为什么要处理异常
	A. 非运行时异常不做处理编译无法通过
	B. 一旦线程执行过程当中出现未做处理的异常
		虚拟机将直接中断该线程的执行

#: 如何处理异常
	1. throws 抛还上级 足以解决A 但无力解决B
	2. try catch finally 自行处理

		try{
			可能出现异常的语句;
		}catch(要捕获的异常类型 给异常起个名字){
			0.隐瞒不报
			1.简要的审   e.getMessage()
			2.详细的审   e.printStackTrace();
		}finally{
			无论是否出现异常 最终都要执行的操作
			通常是释放和关闭资源的操作
		}
	
		*: 一个try 后面可以有多个catch 但是
			决不允许前者包含后者
	
		*: JDK7.0开始支持多重catch
			如果捕获到不同类型的异常想要做相同处理
			则catch(类型1 | 类型2 | 类型3 e)
	
		*: finally当中不该出现return语句

如何在本没有异常的情况下 主动制造异常出现
	throw 
		用在方法体当中 用于主动制造异常出现的场景
					[没事找事型]

	throws
		用在方法签名的最后 用于表达本方法中出现指定种类的异常
		本方法当中不做处理 抛还给调用的上级进行处理
					[有事甩锅型]

如何自定义异常
	自己开发一个类 选择**继承Exception / RuntimeException**
		在其构造方法的首行使用**super()传参**指定异常的描述信息



#### 线程  Thread

**程序**：保存在物理介质中的代码片段	

**进程**：一旦程序运行起来 就会编程操作系统当中的一个进程
	进行当中的程序

**线程**：进程当中更加微观的概念
	程序当中一条独立的执行线索

为什么要使用多线程：
	我们不否认在某些场景下使用多线程确实可以提高效率
	但是使用多线程的**根本目的不是提高效率**
	**而是让程序同一时间能够做多件事情**
	<u>从而可以服务多个用户 处理多个请求</u>


线程的一生：
	
	新生        就绪        运行        消亡
	NewBorn     Runnable    Running     Dead
	
	                   阻塞
			  		Blocking



#### 如何实现线程：

​	1.extends Thread
​	2.implements Runnable
​	3.金针菇..


	请用两种不同方式实现两个线程
		要求第一个线程打印1-26	extends Thread
		要求第二个线程打印a-z	implements Runnable



#### 如何控制线程：

​	0**.setPriority(int)** : 设置线程的优先级别
​		可选范围**1-10** 默认优先级为**5**
​		优先级高 <u>代表抢到时间片的概率高而已</u>

1.**static sleep(long)** : 让当前线程休眠指定的毫秒数			/*在主方法里调用xx.sleep(),主线程进入睡眠*/

​		**\r** 独立回车字符 	 <!--System.out.print("\r倒计时："+i+"%");-->

2.**static yield()** : 让<u>当前线程</u>**放弃时间片** 直接返回就绪

3.**join()** : 让**当前线程**邀请**调用方法的那个线程**<u>优先执行</u>
			在被邀请的线程执行结束之前
			当前线程一直阻塞 不再继续执行



*: **线程类当中所有的静态方法** 不要关注谁调用方法
		而要关注 调用出现在谁的线程体当中
		出现在谁的线程体当中 就是操作对应的那个线程
				别看谁点 看写在哪
*: **线程类**当中**所有涉及到主动进入阻塞状态的方法**
		都必须进行异常处理
		因为它们都有异常声明
		它们都有**throws InterruptedException**
		InterruptedException是个**非运行时异常**

BigOne
	一个向往美好的和尚 与 诸多动物一起旅游的故事

					- 《西游记》

故事当中涉及到如下多个线程：
	有一个师傅线程 打印999次 "Only You"
	有一个猴哥线程 打印888次 "俺老孙来也"
	有一个八戒线程 打印777次 "分行李吧 回高老庄"
	有一个悟净线程 打印666次 "大师兄 不好了 师傅被妖怪抓走了"

					setPriority()
	*：师傅天天逼逼叨叨特别烦人 请让他优先级最低 
	*：猴哥想给师弟们更多的表现机会 前三次拿到时间片
		猴哥决定主动放弃		yield()
	*：八戒很懒 干活之前需要先休眠300毫秒   sleep()
	*：沙僧很依赖猴哥 猴子不死 他就不上     join()







#### 线程类其它常用方法：static activeCount() / setDaemeon(true) / interrupt() / static currentThread

**setName() + getName()** : 设置和得到线程的名字~

**static activeCount()** : 得到程序当中所有活跃线程的总数
		活跃线程：就绪 + 运行 + 阻塞
	
**setDaemon(true)** : 设置线程成为守护线程
		守护线程：守护线程是为其它线程提供服务的
			  当程序当中只剩下守护线程的时候
			  守护线程会自行消亡 
		*: **守护线程应当无限循环 以防止其过早消亡**
		*: 设置成为守护线程 **必须早于线程的start**
			否则会触发IllegalThreadStateException
		*: 守护线程**应该具有较低的优先级别**

​		<!----xx.setDaemon(true);//必须早于自身的start()-->
​		<!--xx.setPriority(1);//守护线程应该具有较低的优先级-->

**interrupt()** : 中断 打断线程的阻塞状态
	
**static currentThread()** : <u>得到正在运行状态的**线程对象**</u>			<!--可以用来取得调用线程名字-->
		1.它可以出现在主方法当中 	**☆用于获得主线程对象** 		  <!--main方法一定先进入执行状态-->
		2.它还可以出现在run()调用的其它方法中
			用于得到当**前线程是谁**~
		X.它绝对不该直接出现在run()当中
			它得到的将完全等价于this



-----------------------------

	1.线程的基础 如何实现线程 如何启动线程
	2.如何给线程设置名字 如何得到线程的线程
	3.单例模式 - 醉汉式
	4.run()调用的其它方法中 如何得到当前线程
	5.多线程高并发的场景下 该如何选择键值对集合 ConcurrentHashMap
	6.Map集合的基础操作 put() get() containsKey()
	7.分析数据的线程如何邀请生产数据的线程优先执行    join()
	8.连环try
	9.CountDownLatch




import java.util.concurrent.*;
/**
	**CountDownLatch** 是什么 ？核心方法有什么？
		直译叫做**倒计时门闩** 是JUC包(**并发包**)当中的常用工具之一
		其作者是Doug Lea  用于<u>主动制造阻塞</u>

		核心方法：
			构造方法： new CountDownLatch(门闩个数);
	
			await() : 阻塞直到门闩都被打开	[分析数据的线程]
			countDown() : 打开一个门闩		[每个生产数据的线程]
*/

class X{
	static CountDownLatch latch = new CountDownLatch(3);
}

**X.latch.countDown();//拔掉一个门闩**

try{
			**X.latch.await();//等待开门**
		}catch(Exception e){
			e.printStackTrace();
		}


​			

# day26

#### review

**线程**：程序当中一条独立的执行线索...
	而多线程编程 就是让程序当中拥有多条独立的执行线索
	从而可以同一时间做多件事 同一时间接待多个连接
	处理多个请求...

为什么要使用多线程：
	我们不否认某些场景使用多线程确实可以提高效率..
	能够让程序同一时间做多件事情...

线程的五大状态：
	新生	就绪	运行	消亡

		  阻塞(sleep() / join() / await())

如何创建线程：
	1. extends Thread
	2. implements Runnable
	3. 明天见

如何控制线程
	0.**setPriority(int)** : 设置线程的优先级别
	1.**static sleep(long)** : 让当前线程休眠指定的毫秒数
	2.**static yield()** : 让当前线程放弃时间片**直接返回就绪**
	3.**join()** : 让当前线程邀请调用方法的线程优先执行

线程类其它常用方法：
	setName() + getName() : 设置和得到线程的名字
	static activeCount() : 得到程序当中所有活跃线程总数
	**setDaemon(true)** : 设置线程成为守护线程
		1.守护线程必须无限循环 防止其过早消亡
		2.设置成为守护线程必须早于自身的start()
		3.守护线程应该具有较低的优先级
	interrupt() : 中断线程的阻塞状态
	static currentThread() : 得到当前正在运行的线程对象
		1.可以用于在主方法当中得到主线程
		2.可以在run()调用的其它方法中得到当前线程是谁
		X.不该直接出现在run() 得到的必然就是this

====================================================



#### synchronized

已知：Vector类的**add()** 和 **remove()** 都是synchronized修饰的
	我们有一个Vector对象 名叫v
	有两个线程对象 名叫t1 t2

	当t1线程调用v对象的add() 方法已经开始执行了
	但是还没执行结束呢 此时时间片耗尽
	而t2线程抢到了时间片 
问：
	t2能不能调用v对象的add()?	false
	t2能不能调用v对象的remove()?	false



已知：Hashtable类的put() 和 remove()都是**synchronized**修饰的
	我们有两个Hashtable对象 名叫h1 和 h2
	有两个线程对象 名叫t1 和 t2

	当t1线程调用h1对象的put() 方法已经开始执行了
	但是还没执行结束呢 此时时间片耗尽
	而t2线程抢到了时间片

问：
	t2能不能调用h1对象的add()?	false
	t2能不能调用h1对象的remove()?	false
	t2能不能调用h2对象的add()?	true
	t2能不能调用h2对象的remove()?	true

综上所述 即便synchronized加在方法上 也是对对象进行加锁
	Java世界当中 **只有每个对象才有互斥锁标记**
	才能对其进行加锁 Java当中根本没有对方法加锁

------------------------------

#### 锁池 等待池

**锁池**和**等待池**都是Java当中每个对象都有一份的空间
	它们都是用于存放**线程任务**的

**锁池** 存放的是那些想要拿到对象的**锁标记**
		但是还没能顺利拿到的线程
	
**等待池** 存放的是那些原本已经拿到了对象锁标记
		怕与其它线程永久阻塞而**主动**释放锁标记的线程

**它们的区别主要在三个方法**
	1.进入的时候<u>是否需要释放资源</u>
		锁池不需要
		等待池必须先要释放资源
	2.离开的时候<u>是否需要调用方法</u>
		锁池不需要
		等待池必须要notify() / notifyAll()
	3.离开之后<u>去往什么状态</u> 
		离开锁池返回就绪
		离开等待池进入锁池



#### 多线程共享数据的时候 可能导致并发错误!

**根本原因：**多个线程共享操作同一份数据...
**直接原因：**线程体当中连续的多个操作 未必能够连续执行
		很可能操作只完成了一部分
		然后<u>时间片</u>突然耗尽 
		此时另一个线程抢到了时间片
		直接访问<u>操作了</u>操作不完整的数据
**导火线：**【时间片突然耗尽】

多个线程共享操作同一份数据的时候 线程体当中连续的多行操作
	未必能够连续执行 很可能操作只完成了一部分
	时间片突然耗尽 此时另一个线程抢到时间片
	直接访问了操作并不完整的数据
	在语法上没有任何错误 但数据却全是错的(**逻辑错误**)

	编译不报错 运行没异常 它就是数据都不对

#### #: 并发修改异常 和 并发错误 是什么关系？

**并发修改异常** 是为了**减少**和**避免**并发错误出现
	由官方程序员主动校验 主动抛出的运行时异常
	它是为了避免程序进一步执行就该出现**并发错误**了
	并发错误 编译不报错 运行没异常 杀程序员于无形



#### 如何解决并发错误：

​	加锁

*: 多个线程共享操作的那个对象 被称作 **临界资源**
	

#### 1st.互斥锁:

​    synchronized 修饰符 同步的...
​	修饰代码块：
​		synchronized(临界资源){
​				需要连续执行的操作1;
​				需要连续执行的操作2;
​				....;
​		}
​	
修饰方法：
​			public synchronized void add(Object obj){
​				需要连续执行的操作1;
​				需要连续执行的操作2;
​				....;
​			}
​	
​	*: Vector Hashtable StringBuffer 之所以线程安全
​				是因为它们**底层大量的方法都使用了synchronized**
​	
​	*: 单例模式懒汉式 需要synchronized修饰那个getter~
​	
​	*: synchronized隔代丢失...

​	

#### 	2nd.可重入锁   JDK5.0新特性 Doug Lea

​		java.util.concurrent.locks.ReentrantLock
​		Java包的工具包的并发包的锁包 可重入锁
​	
​			lock()		unlock()
​			加锁		释放锁
​	

		ReentrantLock构造方法可以传参指定
			公平锁 / 非公平锁
	
			new ReentrantLock(true)

#### 死锁 what how

什么是死锁：
	多个线程 相互持有对方想要申请的资源 不释放的情况下
	又去申请对方已经持有到的资源 
	从而双双进入对方已经持有的资源的锁池当中
	产生了永久的阻塞
					- 死锁现象 DeadLock

	1.中美科学家联合国饿死事件
	2.泉城路奔宝事件
	3.AABB事件

如何解决死锁：
	一块空间：等待池
	三个方法：**wait() / notify() / notifyAll()**

	  wait() : 让当前线程放弃对象的锁标记
	 	   并且进入调用方法的那个对象的等待池当中
	
	  notify() : 让当前线程从调用方法的那个对象的
		     等待池中随机的唤醒一个线程
	
	  notifyAll() : 让当前线程从调用方法的那个对象的
			唤醒所有等待的线程

***: 注意 这三个方法不是线程类的方法 而是Object类的方法**
		因为Java当中每个对象都有**等待池** 都可能要操作等待池
		所以这三个方法被定义在Object当中了
	
***: 必须拿到对象的锁标记 才能操作对象的等待池**
	    这三个方法都必须在已经持有**锁标记**的前提下才能使用
		**xxx.wait()** 必然出现在synchronized(xxx){}
		否则不但操作失败 还会触发<u>运行时异常</u>
		**IllegalMonitorStateException**
		Monitor = 监视器 = 锁标记 = 互斥锁 = 锁旗标 = 互斥锁标记


两个线程交替执行~


顺丰陆运 不可能一个大卡车只安排一个司机师傅..

	王师傅			黎师傅
	1.开4个小时
	2.王师傅主动休息
				3.开4个小时
				4.唤醒王师傅
				5.主动休息
	6.让黎师傅做好准备















