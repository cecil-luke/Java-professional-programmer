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
		Outer.Inner in = new Outer().new Inner();

#### **静态内部类**：	

外部类的静态成员[属性+方法]
	如何理解其与外部类的关系：
		寄居蟹和蛤蜊壳的关系 
		房客和房东的关系
	如何创建其对象：
		Outer.Inner in = new Outer.Inner();	--->并不影响外部类，所以不创建

​	**表达类与类的专属关系**

	*: 静态内部类能够共享的是外部类的静态成员
		但是静态成员本身就能直接访问
		还用内部类去共享吗？ 显然不用
		所以 内部类的存在 并不是为了共享数据
		而是为了表达类和类的专属关系

#### **局部内部类**：

​	如果定义在外部类的静态方法中 ： 
​		**只能**共享外部类静态成员
​	如果定义在外部类的非静态方法中 ： 
​		能够共享外部类所有成员
​	另外 还有其所在的方法体当中的局部变量也能共享
​		只是JDK8.0之前必须用**final修饰** 
​		8.0开始可以**不加final** 但**默认就是final**

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

Plus01: 
	当类体当中的静态变量是调用有异常声明的方法完成赋值的时候
	我们不能在类体当中直接try catch 更不能在类的签名上throws
	此时如果想要编译通过 必须借助**静态初始化块**
	在静态初始化块当中使用**try catch**进行处理

​	***：如果是个非静态变量 则可以使用** 
​	**非静态初始化块 或者 构造方法**	

Plus02:
	在完成方法覆盖的时候 如果父类当中的方法没有任何throws声明
	子类在覆盖该方法的时候 能不能声明抛出异常呢？
	可以 但是只能**向外抛出**运行时异常 - 这样的行为没有意义
	因为在**Java当中<u>每个方法</u>都默认抛出所有的运行时异常**
	相当于 每个方法最后都有我们看不见的throws RuntimeException

Plus03:
	当我们的代码当中出现连续的多行语句都有异常声明的时候
	我们需要无论前者执行是否顺利 后者都要尝试去执行
	则必须借助try catch finally的finally当中 嵌套使用try catch
	我们把这种语法戏称为 **连环try... [学IO流的时候要用~]**

Plus04:
	为了进行异常处理而添加的try catch语法结构是有**{}**的
	这对儿大括号同样能够控制变量的作用范围
	所以如果我们的某些变量 在下文程序当中还要继续使用
	就不能在try{}当中进行定义
	应当在try{}前面完成定义 并且以默认值赋值
	try{}当中只做重新赋值 不做变量定义
	
Plus05:
	在某些场景下 **学会使用<u>异常处理的机制</u>代替传统的分支判断**
		**会有奇效！！！** 【某些场景 发生在下周... 和100有关】






















​			



























