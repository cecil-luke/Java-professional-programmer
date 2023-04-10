# day01

## 1. 时间分布？

day1~day2: JDBC

day2~day4: javaweb 打通前后台

day5~day6: 案例项目 学生选课

day7~day8: 反射 案例 maven 

day8:学生选课改造---》引入spring

day9~12:Spring IOC  MVC 案例改造

day12~day15:mybatis 后端框架+spring整合 +案例改造

day16~day19:综合项目 WEB版+模拟

## 2. JDBC(Java Database Connectivity)的简介？

1. JDBC是JAVA语言提供的一套访问和处理数据库中数据的API（应用程序编程接口 application programming interfaces）。**API:类库**

   

2. JDBC这套API在JDK中java.sql.包和javax.sql.包。

   

3. JDBC是一套工具。我们需要连接工具的各部分。

## 3. 三方的分工（责任）？

- 数据库厂商：

  - 实现JAVA语言连接各自的数据库，叫驱动。我们可以在数据库厂商的官方的获取驱动信息。

    

- JAVA官方：

  - 制定一套JAVA语言连接数据库的标准，各个厂商实现JAVA连接各自数据库的同时，实现标准。

  - JAVA官方制定的标准包：java.sql.包和javax.sql包

    

- 我们（开发者）：

  - 我们学习标准，使用真正的驱动程序完成CRUD操作。



## 4. JDBC的核心API？

1. `java.sql.Driver`【接口】【造桥的人】

   1. 驱动标准，所有的驱动jar包中都需要提供该接口的实现，该接口也是真正创建连接的。
   2. ![image-20230410103006108](./note.assets/image-20230410103006108.png)

2. `java.sql.DriverManager`:【类】【造桥指挥部】

   1. 驱动管理器，主要用来管理驱动【注册、注销、存放驱动对象】
   2. 对外获取连接
   3. ![image-20230410103453729](./note.assets/image-20230410103453729.png)
   4. ![image-20230410103523737](../../../../Month 4/day01/4.10/note.assets/image-20230410103523737.png)

3. `java.sql.Connection`:【接口】【桥】

   1. 构造SQL语句的执行器

      ![image-20230410103735583](./note.assets/image-20230410103735583.png)

      ![image-20230410103748827](./note.assets/image-20230410103748827.png)

      ![image-20230410103804860](./note.assets/image-20230410103804860.png)

   2. 管理事务

      ![image-20230410103843581](./note.assets/image-20230410103843581.png)

      ![image-20230410103858118](./note.assets/image-20230410103858118.png)

      ![image-20230410103920687](../../../../Month 4/day01/4.10/note.assets/image-20230410103920687.png)

   3. 对于连接的一些设置相关属性。

      ![image-20230410104007500](./note.assets/image-20230410104007500.png)

      

4. `java.sql.Statement`:【接口】【车】

   1. 负责向数据库中运行SQL，以及返回从数据库中查询的结果

      ![image-20230410104135138](./note.assets/image-20230410104135138.png)

      ![image-20230410104149069](./note.assets/image-20230410104149069.png)

   2. 一些执行器的设置

5. `ResultSet`:【接口】【存放从数据库中查询的结果的对象】

   1. 获取数据的方法

      ![image-20230410104321351](./note.assets/image-20230410104321351.png)

   2. 可滚动 、可更新特性

      ![image-20230410104343893](./note.assets/image-20230410104343893.png)

      

## 5. JDBC程序helloworld:对数据库的CRUD？

- 注册驱动：告诉驱动管理器有哪些驱动可以使用 

- 获取连接

- 创建运送SQL的Statement对象

- 执行SQL返回结果

- 处理结果

- 关闭资源

  ~~~java
  import java.sql.*;//Connection Driver DriverManager Statement ResultSet
  //测试JDBC程序
  public class Test{
  	public static void main(String args[])throws Exception{
  		//1.注册驱动
  		Class.forName("com.mysql.cj.jdbc.Driver");
  		//2.获取连接
  		String url="jdbc:mysql://localhost:3309/et2301?"+
  		"characterEncoding=utf8&useSSL=false&"+
  		"serverTimezone=Asia/Shanghai"+
  		"&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
  		Connection con = DriverManager.getConnection(url,"root","etoak");
  		//3.创建执行SQL的Statement
  		Statement sta= con.createStatement();
  		//4.执行SQL返回结果
  		ResultSet rs = sta.executeQuery("select * from student");
  		while(rs.next()){
  			int id = rs.getInt("id");
  			String name = rs.getString("name");
  			int age = rs.getInt("age");
  			Date birth = rs.getDate("birth");
  			String email = rs.getString("email");
  			System.out.println(id+"\t"+name+"\t"+age+"\t"+birth+"\t"+email);
  		}
  		//关闭资源
  		rs.close();
  		sta.close();
  		con.close();
  	}
  }
  ~~~

  

## 6. 插播:批量添加？

1. insert into (f1,f2,..fn) values (),(),()

   ~~~sql
   mysql> insert into student(name,age,birth,email) values
       -> ('lisi',234,current_Date(),'lisi@qq.com'),
       -> ('wangwu',345,now(),'WANGWU@qq.com');
   ~~~

   

2. insert into (f1,f2..fn) (select 子查询) union [all] (select 子查询)

   ~~~sql
   mysql> insert into student(name,age,birth,email)
    -> (select 'zhaoliu',age,birth,'zhaoliu@qq.com' from student where name='lisi')
       -> union all
       -> (select name,age,now(),'etoak@qq.com' from student where name='wangwu');
   Query OK, 2 rows affected, 1 warning (0.01 sec)
   Records: 2  Duplicates: 0  Warnings: 1
   ~~~

## 7. JDBC流程中的每一个步骤？

1. #### 加载驱动/注册驱动

- Class.forName():Class类静态方法forName作用是加载指定名字的类（不只是加载驱动），我们这里使用它来加载驱动。

  ![image-20230410113349883](./note.assets/image-20230410113349883.png)

- ![image-20230410113708504](./note.assets/image-20230410113708504.png)

- **真正获取连接时，挨个遍历注册的驱动程序，找到能过符合该URL，使用驱动对象的connect构造连接。**

  ![image-20230410114337399](./note.assets/image-20230410114337399.png)

2. #### 获取连接？

   1. 常见的两种获取连接的方法：

      1. DriverManager.getConnection(url,user,pwd);

      2. DriverManager.getConnection(url,Properties info);

         info:至少包括：user和password  键值对

   2. 关于Properties类？

      1. java.util.Properties继承自java.util.HashTable、也是以key:value形式存放数据的工具类。
      2. 主要用来读取：key=value形式的配置文件【一般我们称为javabean属性文件，通常后缀.properties】
      3. 这里可以使用Properties对象存放数据库的连接信息。

   3. ##### Class.getResourceAsStream()和ClassLoader.getResourceAsStream()的区别？


   ![image-20230410120415771](./note.assets/image-20230410120415771.png)

3. #### 关于SQL的执行器？Statement

   1. 执行器主要执行SQL语句，我们主要了解执行器中的执行的方法。

   2. ##### execute: 

      ~~~java
      	Statement中的execute方法：
      		执行DDL(create): 执行成功，返回false.
      		执行DML(insert)：执行成功，返回false.
      		执行DQL(select): 返回true
         execute方法总结：
         	1.万能方法：可以执行任何的SQL语句.
         	2.返回值是以是否有ResultSet返回为判断标准的【只要有resultset(可以为empty)返回，就是返回true】。
      	*/
      	public static void testExecute()throws Exception{
      		Connection con = getConnection();
      		String sql="create table test1(id int primary key auto_increment,name varchar(32))";
      		String insert="insert into test1(name) values('test')";
      		String select="select * from test1 where 1=2 ";
      		Statement sta = con.createStatement();
      		boolean flag = sta.execute(select);
      		System.out.println(flag);//true
      
      	}
      ~~~

      

   3. ##### executeUpdate

      - 主要执行DML，insert 、update、delete操作。返回<u>对数据库影响的行数</u>。
      - 执行DDL,返回0，
      - 不能执行返回结果集的语句
      - ![image-20230410150738454](./note.assets/image-20230410150738454.png)

   4. ##### executeQuery()

      	Connection con = getConnection();
      		String select="select id,age,name as stuname,birth,email from student";
      		Statement sta = con.createStatement(
      			ResultSet.TYPE_SCROLL_INSENSITIVE, //1004、1005
      			ResultSet.CONCUR_READ_ONLY);//1007
      		ResultSet rs = sta.executeQuery(select);
      		//Thread.sleep(20000);
      	
      		while(rs.next()){
      			//按查询列表的字段名字
      			int id = rs.getInt("id");
      			//按列索引查询
      			int id1 = rs.getInt(1);
      	
      			String name = rs.getString(3);
      			String name1 = rs.getString("stuname");
      			System.out.println(id+"\t"+id1+"\t"+name+"\t"+name1);
      		}

4. ### 关于ResultSet?

   - ResultSet一般执行查询数据库的语句生成，主要用来**存放从数据库中返回的结果**。默认resultset有一个指向**当前行的光标**,最初光标位于第一行之前，next方法将光标向下移动一行，因为该方法在没有下一行时返回false,所以可以在while中迭代使用。

   - 在使用getXX()获取列内容时，可以使用列名字也可以使用列的索引，如果使用列名字，`需要和查询列表中的名字对应（有别名按照别名查询）`

   - 结果集中定义了一些开关：

   - | 常量名字                  | 常量值 | 意思                     | 其他 |
     | ------------------------- | ------ | ------------------------ | ---- |
     | `TYPE_FORWARD_ONLY`       | 1003   | 不可滚动结果集           | 默认 |
     | `TYPE_SCROLL_INSENSITIVE` | 1004   | 可滚动结果集【不敏感的】 |      |
     | `TYPE_SCROLL_SENSITIVE`   | 1005   | 可滚动结果集【敏感的】   |      |
     | `CONCUR_READ_ONLY`        | 1007   | 只读的结果集             | 默认 |
     | `CONCUR_UPDATABLE`        | 1008   | 可更新的结果集           |      |

     

   - ##### 常见错误1：指针在第一行的前边【忘了加next】

     ![image-20230410151503173](./note.assets/image-20230410151503173.png)

   - ##### 常见错误2：指针已经在最后一行之后了，默认不能继续获取数据的。【在循环外获取数据】

     ![image-20230410151625705](./note.assets/image-20230410151625705.png)

   - `结果集默认是只能从第一行到最后一行遍历一次，不能来回遍历的,也就是默认结果集不可滚动，如果需要重复遍历或者使用可滚动的结果集，需要打开开关。`

     ![image-20230410151936091](./note.assets/image-20230410151936091.png)

   - 常见滚动方法

   - | 滚动方法    | 意思                     |
     | ----------- | ------------------------ |
     | absolute    | 把指针绝对定位的某一行   |
     | previous    | 和next相反               |
     | first       | 把指针移动的第一行       |
     | beforeFirst | 把指针移动到第一行之前   |
     | last        | 把指针移动到最后一行     |
     | afterLast   | 把指针移动到最后一行之后 |
     | isFirst     | 是否在第一行             |
     | isLast      | 是否在最后一行           |

   - 可滚动结果集的应用场景：`假分页`

     - **真分页**: 按需提取数据 【查询部分】  物理分页 分页关键字 **limit rownum**

     - **假分页**:按需展示数据【查询所有的】逻辑分页 

       ~~~java
       	//每页的记录数
       		int pageSize=3;
       		//当前页
       		int pageNumber=2;
       
       		//起始位置
       		int start =(pageNumber-1)*pageSize;
       		System.out.println("================");
       		//1003:代表不可滚动
       		if(rs.getType()!=1003){
       			//1004 1005 可滚动
       			rs.absolute(start);
       		}else{
       			//不可滚动调用next方法 不断调用next 移动指针。
       			for(int i=0;i<start;i++){
       				rs.next();
       			}
       		}
       		int k=0;
       		while(rs.next() && k<pageSize){
       			int id = rs.getInt("id");
       			//按列索引查询
       			int id1 = rs.getInt(1);
       
       			String name = rs.getString(3);
       			String name1 = rs.getString("stuname");
       			System.out.println(id+"\t"+id1+"\t"+name+"\t"+name1);
       			k++;
       		}
       
       ~~~

## 8.  PreparedStatement的使用？

1. 我们现在使用的执行器Statement,一般用来执行静态SQL语句。所谓“静态SQL”：就是没有参数，不变的如：select * from student ，如果有参数只能是拼接参数。

   

2. 实际开发中，更多情况时需要传递参数的时候，使用PreparedStatement**["预编译"的执行器**]，PreparedStatement在被创建时，就需要传入SQL语句，此时SQL就会被发送数据库服务器，编译存储。支持?占位符，传参比较方便。

   

3. PreparedStatement 在**效率**和**安全性**两个方面，都比Statement要好。

   

4. ##### 有些情况，不能使用?占位符：MyBatis 中#和$的区别 再回首！！

   1. ###### 表名字未知

      ![image-20230410172035149](./note.assets/image-20230410172035149.png)

   2. ###### 查询参数在引号中

      ![image-20230410172357347](./note.assets/image-20230410172357347.png)

   3. ###### order by 后的字段

      1. order by 后跟?，导致排序失效

         ![image-20230410172757587](./note.assets/image-20230410172757587.png)

## 9.  事务？

1. ##### 数据库中事务的基本概念？

   1. 概念：多条对数据库修改的SQL  同一个单元 不可再分。

   2. 特征：

      1. A: 原子性
      2. C: 一致性
      3. I: 隔离性：多个事务影响程度
      4. D: 持久性

   3. 事务影响产生问题

      1. 脏读
      2. 幻读
      3. 不可重复读

   4. 隔离级别

      1. 读未提交
      2. 读已提交：oracle默认
      3. 可重复读：mysql默认
      4. 串行化

   5. start transaction/begin

      sql1,sql2..sqln

   6. end./commit/rollback

2. ##### 事务的应用场景？

   1. 员工系统：

      1. emp:insert  delete
      2. emp1: insert 

   2. 添加学生携带图片

      

   3. 清空购物车

      1. 购买---》购物车中数据----》订单  1.insert 订单 2.insert 订单明细 3.扣减库存 4.通知卖家 5.生成日志。。。

   4. 转账：

3. ##### JDBC中如何处理事务？

   1. **JDBC默认是自动提交的**。当执行完一条SQL语句之后，立刻提交。

   2. 开启事务: Connection.setAutoCommit(false);禁用自动提交

      sql1....sql2...sqln

   3. 提交：Connection.commit()

   4. 回滚：Connection.rollback();

4. MyBatis中如何处理事务、Spring中如何使用事务？【全部是JDBC】

## 10. 元数据？

## 11. 数据源？

## 12. 获取数据库中新添加的记录的主键？

- 数字类型：自动增长

  - JDBC自带开关

    - Statement.RETURN_GENERATED_KEYS :值是1

    - Statement.NO_GENERATED_KEYS: 值是2

      ~~~java
      public static void testNumberKeys1()throws Exception{
      		String sql="insert into test1(id,name) values(null,?)";
      		try(Connection con = getConnection();
      				PreparedStatement pst = con.prepareStatement(sql,
      				Statement.RETURN_GENERATED_KEYS)){//返回主键的开关
      				pst.setString(1,"xx");
      				int count = pst.executeUpdate();
      				System.out.println("执行成功,影响:"+count+"行");
      				//获取自动生成的主键
      				ResultSet rs = pst.getGeneratedKeys();
      				while(rs.next()){
      					int key = rs.getInt(1);
      					System.out.println(key);
      				}
      			}catch(Exception e){
      				e.printStackTrace();
      			}
      	}
      ~~~

      

  - 同一个连接，查询 mysql: select last_insert_id() 

    ~~~java
    public static void testNumberKeys2()throws Exception{
    		String sql="insert into test1(id,name) values(null,?)";
    		try(Connection con = getConnection();
    				PreparedStatement pst = con.prepareStatement(sql)){
    				pst.setString(1,"xx");
    				int count = pst.executeUpdate();
    				System.out.println("执行成功,影响:"+count+"行");
    				//同一个连接 进行查询
    				PreparedStatement pst1 = con.
    				prepareStatement("select last_insert_id()");
    				ResultSet keys = pst1.executeQuery();
    				while(keys.next()){
    					int id = keys.getInt(1);
    					System.out.println(id);
    				}
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    	}
    
    ~~~

    

- 字符串类型：

  - 先生成 

    - select replace(uuid(),'-','') as id    
    - UUID.randomUUI().toString().replaceAll('-', '');

  - 再添加

    - insert into test3(id,name) values(?,?)

      ~~~java
      public static void testString()throws Exception{
      			String sql="insert into test3(id,name) values(?,?)";
      			try(Connection con = getConnection();
      					PreparedStatement pst = con.prepareStatement(sql)){
      					String id = UUID.randomUUID().toString().replaceAll("-","");
      					pst.setString(1,id);
      					pst.setString(2,"xxx");
      					int count = pst.executeUpdate();
      					System.out.println("执行成功,影响:"+count+"行");
      					//同一个连接 进行查询
      					System.out.println("主键:"+id);
      				}catch(Exception e){
      					e.printStackTrace();
      				}
      	}
      
      ~~~


## 13. 数据库中表和表之间的关系？

- ##### 一对一:通过主键关联/唯一外键

  ​	person: id name   主

  ​		         1

  idcard: id  address  从 pid【unique】

  ​             100                   1

  ​              120                 10

  ​            

- ##### 一对多：通过外键实现

  - scott.dept表  一 deptno dname loc

  - scott.emp表 多 empno ename job sal hiredate comm  mgr deptno[外键引用一方的主键]

    

  

- ##### 多对多：多对多的关系必须添加第三张表：桥表 关联关系表

  - student:学生
  - course课程
  - sc: sid[学生id] ，cid[课程id]

## 14. 数据库中能不能直接存放图片？

  - 在数据库中有专门存放图片、音频、视频、大文本文档 字段类型，这种类型叫：**lob**（Large Object）类型,mysql中 文本大对象 text、二进制大对象（图片、音频视频）blob

    

  - 一般的客户端都不支持直接操作lob类型的数据，lob类型的数据一般通过程序（JDBC）采用IO流的方式添加和查询的。

    

  - `一般在保存文件、音视频等资源的时候，都是先把资源放到服务器端的指定目录中，然后再把保存地址放到数据库中`

    

  - ![image-20230410212248159](./note.assets/image-20230410212248159.png)

---

## 代码总结的知识点

```java
/*
excuteUpdate:
	DDL: 成功，返回0.
	DML: 返回对数据库影响的行数。
	DQL:

*/

/*
SQL分类:
	DDL:CREATE DROP ALTER truncate
	DML:insert update delete
	DQL:select
Statement中的execute方法：
	执行DDL(create): 执行成功，返回false.
	执行DML(insert)：执行成功，返回false.
	执行DQL(select): 返回true
execute方法总结：
    1.万能方法：可以执行任何的SQL语句.
    2.返回值是以是否有ResultSet返回为判断标准的【只要有resultset(可以为empty)返回，就是返回true】。
*/
```

```java
/*
1.locations( id name ltype pid
2.school(id,name ,phone ,proid ,cityid,areaid,info)
3.course课程表（id,name（课程名字）,code(课程代号)）
4.sc(桥表)(id,sid(studentid),cid(courseid),score(分数))
5.student添加外键schid
*/
```

### CRUD对数据库增删改查操作

```java
import java.sql.*;
import java.util.*;
import java.io.*;
//测试PreparedStatement的基本用法
public class TestPst{
	public static void main(String[] args)throws Exception{
		testQueryOrderBy("age");
		System.out.println("------------");
		testQueryByLike("u");
		System.out.println("------------");
		testQueryByTableName("student");
		System.out.println("------------");
		testQuery();
		System.out.println("------------");
		//testAddForeignKey();
		testUpdateStudent();
		System.out.println("------------");
		//testCreateSchool();
		//testInsertSchool();
		//testUpdateSchool();
		//testCreate();
	}
	//排序
	public static void testQueryOrderBy(String name)throws Exception{
		String sql = "select * from student order by "+name;
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt("age"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//模糊查询
	public static void testQueryByLike(String name)throws Exception{
		String sql = "select * from student where name like ?";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			pst.setString(1,"%"+name+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//查询某个表的信息
	public static void testQueryByTableName(String tableName)throws Exception{
		String sql = "select * from "+tableName;
		//String sql = "select * from ?";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			//pst.setString(1,tableName);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//真分页
	public static void testQuery()throws Exception{
		String sql = "select * from student limit ?,?";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			//起始位置从0开始
			pst.setInt(1,1);
			//最大显示的记录数
			pst.setInt(2,3);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//更新数据
	public static void testUpdateStudent()throws Exception{
		String sql = "update student set schid=? where id>?";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			pst.setInt(1,1);
			pst.setInt(2,0);
			int count = pst.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//添加列
	public static void testAddForeignKey()throws Exception{
		String sql = "alter table student add(schid int)";
		//String sql = "alter table student add foreign key fk_schid (schid) references school(id)";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			int count = pst.executeUpdate();
			//PreparedStatement pst1 = con.prepareStatement(sql);
			//int count 1 = pst1.executeUpdate();
			System.out.println(count+"\t");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//更新学校数据
	public static void testUpdateSchool()throws Exception{
		String sql = "update school set name = ? where id = ?";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			pst.setString(1,"济宁学院");
			pst.setInt(2,1);
			int count = pst.executeUpdate();
			System.out.println(count);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//添加school表数据
	public static void testInsertSchool()throws Exception{
		String sql = "insert into school(name,phone,proid,cityid,areaid,info) values(?,?,?,?,?,?)";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			pst.setString(1,"test0410");
			pst.setString(2,"15553104487");
			pst.setInt(3,1);
			pst.setInt(4,2);
			pst.setInt(5,3);
			pst.setString(6,"趵突泉北路6号蓝石401");

			int count = pst .executeUpdate();
			System.out.println(count);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//创建school表
	//id ,name,phone,省id,市id,区县的id,详细地址 ，
	public static void testCreateSchool()throws Exception{
		String sql = "create table school (id int primary key auto_increment,"
		+"name varchar(32),phone varchar(11),proid int,cityid int,areaid int,"+
		"info varchar(200))";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			pst.execute();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	//创建地址表
	public static void testCreate()throws Exception{
		String sql = "create table locations(id int primary key auto_increment,name varchar(32),ltype varchar(10),pid int)";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement(sql)){

			pst.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//插入数据
	public static void testInsert()throws Exception{
		String sql = "insert into locations(id,name,ltype,pid) select * from et2212.locations";
		try(Connection con = getConnection();
			PreparedStatement pst = con.prepareStatement()){

			pst.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//提供连接的方法
	private static Connection getConnection()throws Exception{
			//1.读取配置文件
			Properties pro = new Properties();
			//getClassLoader()使用类加载器去加载资源从类的所在包的外部开始寻找。
			InputStream is  = TestPst.class.getClassLoader().getResourceAsStream("db.properties");
			pro.load(is);
			is.close();

			Class.forName(pro.getProperty("m.driver"));

			Connection con = DriverManager.getConnection(
							pro.getProperty("m.url"),
							pro);
			return con;

	}
}
/*
1.locations( id name ltype pid
2.school(id,name ,phone ,proid ,cityid,areaid,info)
3.course课程表（id,name（课程名字）,code(课程代号)）
4.sc(桥表)(id,sid(studentid),cid(courseid),score(分数))
5.student添加外键schid
*/
```





















