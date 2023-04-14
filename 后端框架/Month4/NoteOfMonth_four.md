# day01（JDBC）

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

## 10. 元数据？元信息[metadata]

1. 元数据：关于数据的数据。

2. JDBC中主要由两个元数据接口：`DatabaseMetaData`和·`ResultSetMetaData` 

3. `DatabaseMetaData`：主要用来获得数据信息,如：数据的版本、名字、驱动版本、是否支持..特性。

4. `ResultSetMetaData`:结果集的元数据，获取结果集中列的信息。

   1. getColumnCount:获得列数
   2. getColumnLabel:获得列别名【有则返回别名，没有则返回原名】
   3. getColumnName:获得列名字

5. 案例:

   ~~~java
   import java.sql.*;
   import java.util.*;
   import java.io.*;
   //数据库的元数据。
   public class TestMetaData{
   	public static void main(String args[])throws Exception{
   		Connection con = getConnection();
   		//获取数据库元数据
   		DatabaseMetaData dbmd = con.getMetaData();
   		//								8                         0
   		System.out.println(dbmd.getDatabaseMajorVersion()+"."+dbmd.getDatabaseMinorVersion() );
   		System.out.println(dbmd.getDatabaseProductName());
   		System.out.println(dbmd.getDatabaseProductVersion());
   		System.out.println(dbmd.getDriverName());
   		System.out.println(dbmd.getDriverVersion());
   		String sql="select id as sid,name as stuname,age,birth,email from et2212.student";
   		PreparedStatement pst = con.prepareStatement(sql);
   		ResultSet rs = pst.executeQuery();
   		//获取元数据
   		ResultSetMetaData rsmd = rs.getMetaData();
   		//列数
   		int count = rsmd.getColumnCount();
   		while(rs.next()){
   			for(int i=1;i<=count;i++){//获取列名字
   				String cName = rsmd.getColumnName(i);
   				//列别名 起别名返回别名 没有别名使用列名字
   				String cLabel = rsmd.getColumnLabel(i);
   				String value = rs.getString(cLabel);
   				System.out.println(cName+"\t"+cLabel+"\t"+value);
   
   			}
   		}
   
   
   	}
   	//提供连接的方法
   		private static Connection getConnection()throws Exception{
   			//1.读取配置文件
   			Properties pro = new Properties();
   			//getClassLoader()使用类加载器去加载资源从类的所在包的外部开始寻找。
   			InputStream is  = TestKeys.class.getClassLoader().getResourceAsStream("db.properties");
   			pro.load(is);
   			is.close();
   
   			Class.forName(pro.getProperty("m.driver"));
   
   			Connection con = DriverManager.getConnection(
   							pro.getProperty("m.url"),
   							pro);
   			return con;
   
   	}
   }
   ~~~

## 11. 数据源（Datasource）---获取连接的首选？

1. ##### 数据源标准？

   1. `javax.sql.DataSource`接口是JAVA官方在1.4中提出一个新的获取连接的标准。在此之前获取连接一般使用DriverManager，在此之后，推荐使用DataSource的方式获取连接。

   2. 提供的最主要的方法：`getConnection`

      

2. ##### 数据源实现方案？

   1. 基础实现：使用DriverManager提供一个连接

   2. 连接池实现：采用数据库连接池的方式实现数据源

      

3. ##### 数据库连接池？[必考必问]

   1. 数据库连接池是在服务器启动时初始化一些连接放到连接池（容器、集合）中，当需要获取连接时，首先查看连接池中是否有空闲的连接，如果有则返回，如果没有则判断当前连接数是否超过了最大可用的连接数，如果没超过，则创建新的连接，返回，如果超过了最大可用连接数，等待或者抛出无可用连接的异常。

      

   2. 当连接使用完毕之后，再把连接放回到连接池中，从而实现连接的`复用`

4. ##### 数据源产品？

   1. ASF:commons-DBCP
   2. 阿里：druid
   3. c3p0 ..mybatis实现数据源。。

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



## 15. 实现数据源?

## 16.DAO:Data Access Object 数据访问对象

- DAO层一般是由 接口+实现+实体类构成的，一般存放所有的系统中和数据库交互的方法。
- IStudentDao:接口 
  - getAllSchs():查询所有学校
  - querySomeStu():分页查询学生
  - addStu(Student stu):添加学生
  - addPic(Pic)：添加图片
- StudentDaoImpl:实现类

## 17. 作业：把et2301这个database中的所有的表和数据：

​	1. 打印出来

 2. 写出到 txt文件中

    表名字：xxx

    id   name  age

    1   xx      xxx

     2   xx      xxx

### TestWork1.java

```java
import java.sql.*;
import java.util.*;
import java.io.*;

public class TestWork1{
	public static void main(String[] args)throws Exception{

        String[] q = new String[100];
        String[] w = new String[100];
        int t=0;

        Connection aa = getConnection();

        Statement ff = aa.createStatement();//三个分别用来查询表名，列名，数据
        Statement qq = aa.createStatement();
        Statement www = aa.createStatement();

        ResultSet g = ff.executeQuery("Show tables");// 查询此数据库有多少个表格
		PrintWriter pw = new PrintWriter("./data.txt");
        while(g.next()){
            System.out.println(g.getString(1));
            pw.println(g.getString(1));
            q[t]=g.getString(1);//把表名给赋值给数组
            ResultSet u=qq.executeQuery("DESC "+q[t]+"");//以数组来查一下此表格的列
            int l=0;
            while(u.next()){
                w[l++]=u.getString(1);//获取此表的列数

            }
            ResultSet y=www.executeQuery("select * from "+q[t++]+"");//查询此表的数据
            while(y.next()){//获取此表数据
                for (int i = 1; i < l+1; i++) {//以列的数值为终止行，来打印！
					String str = y.getString(i)+"\t";
                    System.out.print(y.getString(i)+"\t");
                    pw.print(y.getString(i)+"\t");
                    /*if(i==1){
						str = "";
						str = g.getString(1)+"\n"+y.getString(i)+"\t";
					}
					try {
							File file = new File("./data.txt");
							if(!file.exists()) {
								file.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
							}
							FileOutputStream fos = new FileOutputStream(file,true);
							OutputStreamWriter osw = new OutputStreamWriter(fos);
							BufferedWriter bw = new BufferedWriter(osw);
							bw.write(str);
							bw.newLine();
							bw.flush();
							bw.close();
							osw.close();
							fos.close();
					}catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}*/
                }
                System.out.println();
                pw.println();
            }
        }
        pw.close();
        g.close();
        ff.close();
        aa.close();

        //读文件
        System.out.println("================================================================================");
        FileReader fr = new FileReader("./data.txt");
        BufferedReader br = new BufferedReader(fr);
        String str;
        while((str = br.readLine())!=null){
			System.out.println(str);
		}
	}
	//提供连接的方法
	private static Connection getConnection()throws Exception{
			//1.读取配置文件
			Properties pro = new Properties();
			//getClassLoader()使用类加载器去加载资源从类的所在包的外部开始寻找。
			InputStream is  = TestWork1.class.getClassLoader().getResourceAsStream("db.properties");
			pro.load(is);
			is.close();

			Class.forName(pro.getProperty("m.driver"));

			Connection con = DriverManager.getConnection(
							pro.getProperty("m.url"),
							pro);
			return con;

	}
}
```



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



# day02(线程池)

## 1.错误1：端口占用

![image-20230411192650177](./note.assets/image-20230411192650177.png)

错误原因：`端口被占用`，常见场景：

1. ​	开服务器的同时，再开服务器。
2. 先开前端项目（前端项目默认8080），再开后端项目（后端项目默认端口8080）

## 2. 我们模拟实现一个web 服务器？

1. 我们想后台发送请求时，后台接收到请求，但是没有返回内容时，前端`转圈`，就是没有任何响应，正在等待后台给的响应。

2. 当我们从服务器向客户端输出内容如下时:

   ![image-20230411193210977](./note.assets/image-20230411193210977.png)

3. 客户端浏览器显示，`无效的响应`

   ![image-20230411193237063](./note.assets/image-20230411193237063.png)

4. 通过我写的Tomcat.java的模拟实现WEB服务器过程，我们发送Tomcat（WEB服务器）可以接收到请求报文，可以返回响应报文，但是需要开发人员自己去 `处理请求,生成响应`

   1. web服务器: 接收请求

   2. 我们: 处理请求 生成响应

   3. web服务器: 返回响应

      

5. JAVA官方制定的JAVAEE的标准：其中定义了servlet的技术规范（接口），在这套规范中定义了核心的几个接口:

   1. javax.servlet.**Servlet**：处理请求 生成响应

   2. javax.servlet.**ServletConfig**: 为Servlet服务，存放一些Servlet的启动信息的

   3. javax.servlet.**ServletContext**:为Servlet服务，提供Servlet的上下文环境的。

   4. javax.servlet.**ServletRequest**: 打包请求

   5. javax.servlet.**ServletResponse**：打包响应。

      

6. 以上标准中，Servlet接口由`开发人员实现`,其他接口由WEB服务器（Tomcat）实现。我们在Servlet类中处理请求，生成响应，如果需要其他资源（如：请求、响应、配置、Context）等 Tomcat全部送给我们了。等我们实现完毕Servlet之后，我们再把Servlet类注册到**web服务器（Tomcat）**上，在服务器接收到请求之后，就可以使用我们写的Servlet类处理请求了。

   

7. Tomcat只会寻找（<u>Servlet接口</u>：init\service\destroy）方法，我们只需要在init\service\destroy中处理请求。

   

8. **静态资源**：.html/.css/.js/.png/.jpg 千人一面 动态效果

   

9. **动态资源**：servlet/jsp 根据不同的请求、参数，作出不同的响应。 千人千面

## 3. Tomcat的安装和使用？

1. Tomcat是ASF提供的一款免费、开源、纯JAVA实现的WEB服务器。

2. Tomcat是一款绿色软件。解压即安装。【解压到没有中文的目录下】

3. 配置环境变量：

   1. 因为Tomcat是JAVA实现的，所以需要`JAVA_HOME`环境变量

4. 修改Tomcat的控制台编码：

   1. tomcat/conf/logging.properties==>51行===》 GBK

5. tomcat目录结构：

   ![image-20230411202030871](./note.assets/image-20230411202030871.png)

## 	4.  第一个web应用

- 开发源代码的地方:随便

  

- 运行的地方：Tomcat

  

- 从开发的目录赋值代码到运行期tomcat中的过程：`部署`。

  

- 基础WEB应用程序目录机构:

  - 项目名字 et2301
    - WEB-INF 
      - lib:存放jar包
      - classes: 存放class文件
      - web.xml: **部署描述文件**【存放我们开发了哪些Servlet\Filter\Listener. 向tomcat注册组件】
    - 页面、页面所在目录
    - index.html
    - pages/index.html

  

---

## 连接池实现

### AbstractDataSource.java

```java
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
//抽象的DataSource 模板方法【（抽象方法、基本操作）  + 具体方法】
public abstract class AbstractDataSource implements DataSource {
	//抽象方法 基本操作
	public abstract Connection doGetConnection() throws SQLException ;

	//具体方法 模板方法 ：模板方法中调用抽象方法。
    @Override
    public Connection getConnection() throws SQLException {
        return this.doGetConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}

```

### PooledConnectionFactory.java

```java
import java.sql.*;
import java.lang.reflect.*;

public class PooledConnectionFactory implements InvocationHandler{

	//真正的连接
	private Connection realCon;
	//proxy:代理  代理连接对象
	private Connection proxyCon;
	//dataSource
	private PooledDataSource ds;

	public Connection getProxyCon(){return this.proxyCon;}

	public PooledConnectionFactory(Connection realCon,PooledDataSource ds){
		this.realCon = realCon;
		this.ds = ds;

		//代理.静态方法newProxyInstance():获得代理对象。
		proxyCon =  (Connection)Proxy.newProxyInstance(
					Connection.class.getClassLoader(),//类加载器
					new Class[]{Connection.class},//代理的接口
					this);//处理器InvocationHandler类型的

	/*
	Proxy.newPrxoyInstance()作用？
		1.在内存中动态生成一个代理类【接口的实现类】的字节码序列数组。

		2.把字节码序列的数组加载到内存--》构造对象。
	*/
	}
	@Override
	public Object 	invoke(Object proxy,Method method,	Object[] args)throws Throwable{
		System.out.println("方法执行:"+method);
		String methodName = method.getName();
		if(methodName.equals("close")){
			//放回到连接池中
			this.ds.pool.addLast(this.proxyCon);
		}else{
			//其他非close方法全部交给realCon执行
			//调用realCon对象中的当前方法【我们调用的方法】,并传入参数
			return method.invoke(this.realCon,args);
		}
		return null;
	}
}
```

### PooledDataSource.java

```java
import java.sql.*;
import java.util.*;

public class PooledDataSource extends AbstractDataSource{
	/*继承过来的：getConnection()====>doGetConnection()*/
	protected LinkedList<Connection> pool =new LinkedList<>();
	//DriverManagerDataSource
	//初始化个数
	private int initSize=5;

	//最大可用连接数10.
	private int maxActive=10;
	//当前连接数
	private int current=0;
	//最大空闲连接数
	private int maxIdle=8;

	//构造方法中模拟向连接池中添加连接。
	public PooledDataSource(){
		try{
			for(int i=0;i<initSize;i++){
				//真正连接
				Connection con = realCreate();
				PooledConnectionFactory f = new PooledConnectionFactory(con,this);
				//连接池中存放是代理连接对象，这样才能实现每一个代理对象中的方法执行，都会调用inovokee
				this.pool.addLast(f.getProxyCon());
				this.current++;
			}
	}catch(Exception e){}
	}

	//真正获取连接的方法
	public  Connection doGetConnection() throws SQLException{
		//如果连接池中有连接，从连接池中获取连接。
		if(this.pool.size()>0){
			return this.pool.removeFirst();
		}
		//如果连接池中没有连接，判断当前连接数是否超过了最大可用连接数。
		if(current<maxActive){
			Connection con = this.realCreate();
			PooledConnectionFactory f = new PooledConnectionFactory(con,this);
			//连接池中存放是代理连接对象，这样才能实现每一个代理对象中的方法执行，都会调用inovokee

			this.current++;
			return f.getProxyCon();
		}
		throw new SQLException("真正的没有连接了。。别拿了！！");

	}
	//当使用完连接时候，再将连接放回到连接池中。
	public void close(Connection con){
		try{
		if(con!=null){
			if(this.pool.size()<this.maxIdle){
				this.pool.addLast(con);
			}else{
				con.close();
			}
			this.current--;
		}}catch(Exception e){e.printStackTrace();}
	}

	//对内使用的真正创建连接的方法。
	private  Connection realCreate() throws SQLException{
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException e){}
			String url="jdbc:mysql://localhost:3306/et2301?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
			return DriverManager.getConnection(url,"root","etoak");
	}

}
```

### Test.java

```java
import java.sql.*;
import javax.sql.*;
public class Test{
	public static void main(String args[])throws Exception{
		DataSource ds = new PooledDataSource();
		for(int i=0;i<15;i++){
			Connection con = ds.getConnection();
			System.out.println(con);
			//ds.close(con);
			//我们现在需要想办法拦截处理con对象中的close方法：
			//不能直接关闭，而是放到连接池中。
			con.close();
		}
	}
}
```



---



# day03(Maven)

## 0. Maven

**<<Maven配置文件>>在/Month4的Maven文件里**



```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
            http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <!--定义坐标信息：当别人使用我们的jar包时，需要提供坐标信息-->
    <!--域名（倒过来）【+项目名字】-->
    <groupId>com.et2301.student</groupId>
    <!--artifactId:项目名【+模块名】-->
    <artifactId>student-entity</artifactId>
    <!--版本号！-->
    <version>0.0.1</version>
    <!--packaging:打包方式：
        普通工程：jar 包
        web工程： war 包  JAVAEE定制的压缩格式，会被服务器自动解压
        父子工程： pom-->
    <packaging>jar</packaging>

    <dependencies>
    <!--我们用到了第三方的组件：单元测试框架，所以需要导入依赖（maven会自动下载包）
    依赖的坐标信息来自MAVEN中央仓库： mvnrepository.com -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>
</project>
```



---

## 1. Servlet的生命周期？

1. Servlet类是我们（开发人员）写的，但是并不归我们管理【虽然我们实现类中的方法，但是我们不构造对象，不调用方法】，Servlet类作为组件（零件）交给Tomcat（WEB服务器管理的）。

   

2. Tomcat负责构造Servlet的对象，负责调用Servlet类中方法（尤其是**生命周期方法**）。

   

3. 生命周期方法：`Tomcat一定会调用到的方法，我们只需要在有需求时实现，不同我们调用。` 

   1. **init**(ServletConfig config):初始化

   2. **service**(ServletRequest req,ServletResponse resp):处理请求

   3. **destroy**(): 销毁之前执行。

   4. 除了生命周期方法，还有两个服务的方法：getServletConfig()+getServletInfo()

      

4. 生命周期方法的调用顺序：

   0. 服务器启动，请求发送，Tomcat接收，根据url判断静态资源，如果servlet动态资源，找**web.xml**，根据url-pattern-->servlet-name-->servlet-class--》判断内存中是否已经有该类型对象。

   1. Servlet被Tomcat构造.
   2. Tomcat调用init()进行初始化。
   3. 所有请求交给Service处理。

   Tomcat根据我们在service中生成的**response对象**，组装标准的**HTTP响应报文**，会送到浏览器。

   

5. `Tomcat在管理Servlet类对象的时候，采用单例模式，即：不管对某个Servlet的请求有多少次,Tomcat只会创建一个该类型的对象。`【构造方法和初始化方法只会执行一次】

   

6. 实现Servlet类的方式：

   1. 我们直接实现Servlet接口 可以但是不推荐；
   2. 继承GenericServlet,实现service() 可以但是不推荐
   3. **继承HttpServlet,实现doXXX方法。**

## 2. Servlet的初始化？

1. `初始化`：就是在servlet的service方法执行之前**预先执行init()方法**，我们可以在init方法加载配置、读取资源等操作。【并不是每次请求都需要初始化】

   

2. 在标准中（javax.servlet.Servlet接口）中，init方法是需要传参（ServletConfig）的。GenericServlet实现init()方法，在方法体中，接收config，调用空参init()，如果有初始化的需要，我们只需要重写init()即可。

   

3. 在init方法中有个参数ServletConfig:

   1. **ServletConfig**是为Servlet服务的，每一个Servlet都有一个与之关联的ServletConfig对象，ServletConfig中存放着Servlet的启动信息。【就相当于我们每个人都有一个身份证】

   2. ServletConfig中的方法列表：

      1. **getInitParameter**(String name):根据名字获取初始化参数的值

      2. **getInitParameterNames**():获取初始化参数名字

      3. **getServletName**():获得Servlet的名字  <servlet-name>

      4. **getServletContext**():获取Servlet所在的上下文环境对象。

         

   3. ServletContext对象：Servlet上下文环境，其中包含了很多Servlet和所在的容器通信的方法，如：获取服务器上工程所在的绝对路径、从服务器中加载资源、动态注册Servlet\Filter\Listener等。

      

   4. ServletContext和Servlet是**一对多**的关系，当服务器启动时，就会创建一个ServletContext,所有的servlet都可以获得也只能获得一个ServletContext;

      

   5. **默认Servlet对象在请求到达服务器时构造并初始化。我们可以通过<load-on-startup>修改特性，通常是一个数字 1，2  告诉tomcat在启动时读取构造Servlet对象并初始化**

      

   6. ![](./note.assets/image-20230412165058350.png)

## 3. 在servlet中处理请求：请求报文？

前台页面：

~~~jsp
<h2><a href="hello?username=xxx&checkbox=a&checkbox=b&checkbox=c">hello</a></h2>

<form action="hello" method="POST">
    用户名:<input type="text" name="username"><br>
    目标城市：
        <input type="checkbox" name="checkbox" value="jn">济南
    <input type="checkbox" name="checkbox" value="bj">北京
    <input type="checkbox" name="checkbox" value="sh">上海
    <input type="submit" value="提交">
</form>
~~~



~~~java
  //~~~~~~~~请求行~~~~~~~~~~~~~~~~~~
        //本次请求提交的方法GET POST
        String method = req.getMethod();
        //请求的资源 /项目名/请求路径  /stu/hello.html /stu/login
        String requestURI = req.getRequestURI();
        //请求的完整路径 http://......./stu/hello.html
        String requestURL = req.getRequestURL().toString();
        //协议版本
        String protocol = req.getProtocol();
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~请求行结束，请求头开始~~~~~~~~~~~~
        Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            //请求头 名字
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println(name+"\t"+value+";");
        }
        //~~~~~~~~~~~~~~~~~~~~~~~请求头获取完毕~~~~~~~~~~~~~~~~~~~~~~
        //根据参数名字获得参数值
        String parameter = req.getParameter("username");
        String[] checkboxes = req.getParameterValues("checkbox");
        //参数Map
        Map<String, String[]> parameterMap = req.getParameterMap();
        //获取文件：servlet3.0之后提出的获取上传的文件方式之一 不推荐。
        //Collection<Part> parts = req.getParts();
        //Part part = req.getPart("");
        //会话 对象
        HttpSession session = req.getSession();
       // session.setAttribute("stus",stus);
        Cookie[] cookies = req.getCookies();
        //获取ServletContext 上下文环境对象的另外一种方式
        ServletContext servletContext = req.getServletContext();
        req.setAttribute("hello","binbin");

        Object value = req.getAttribute("hello");
        //req提供给我们的手动解析参数的方法：1.可以通过getParameter获得参数
        //2.也可以自己通过getInputStream():解析参数【有些情况必须这么干】
        ServletInputStream inputStream = req.getInputStream();
        /*int serverPort = req.getServerPort();
        String serverName = req.getServerName();
        String remoteAddr = req.getRemoteAddr();
        int remotePort = req.getRemotePort();
        */
        //ContentType:获取Content-Type请求头 代表从客户端发往服务的请求种内容类型
        //响应头也有Content-Type:与之相反
        String contentType = req.getContentType();
        //消息体中的参数长度
        int contentLength = req.getContentLength();
        //GET方法提交请求时的?后的参数字符串 POST无效
        String queryString = req.getQueryString();
        //协议 HTTP/1.1
        String scheme = req.getScheme();
        // /工程名字
        String contextPath = req.getContextPath();

~~~

`我们可以通过getInputStream方法手动的读取消息体中的参数，一旦我们自己读取参数之后，tomcat不再给我们setParameter，所以req.getInputStream()之后时无法调用getParameter().`

## 4. 关于请求头中的Content-Type对于 <u>浏览器处理参数</u> 的影响? 

1. ##### application/x-www-form-urlencoded （默认方式）

   1. 代表浏览器把参数组装成key=value&key1=value1的格式。

      

2. ##### multipart/form-data （文件上传时、图片）

   ![image-20230412174548672](./note.assets/image-20230412174548672.png)

   1. 代表浏览器把`参数通过分隔符`分割传递到服务器。

   2. 分隔符在每次请求的请求头：Content-Type中

      ![image-20230412174653061](./note.assets/image-20230412174653061.png)

3. ##### application/json

   1. 浏览器把参数以**JSON格式**传递到后台服务器。
   2. 服务器无法解析参数，不会自动setParameter,所以我们也不能直接request.getParameter(),一般使用request.getInputStream()读取。


## 4.1 在servlet中处理请求：返回响应？

从服务器（servlet）返回响应给客户端浏览器，主要使用response.

1. ##### 使用response向客户端返回字符数据

   ~~~java
   resp.setContentType("text/html;charset=UTF-8"); <font color="red">中国</font>
       				 text/plain:原样输出  <font color="red">中国</font>
           //修改编码一定要在获取输出流之前
          PrintWriter writer = resp.getWriter();
           writer.print("<html><body><h1>大家好</h1></body></html>");
           writer.close();
   ~~~

   

2. ##### 使用response向客户端返回字节数据

   文件下载原理：服务器端的文件，写出客户端浏览器，并且以文件形式保存

   ~~~java
   resp.setContentType("image/jpeg");
          //2.使用response.getOutputStream 向客户端输出字节数据
           //File file = new File("058.jpg");
   
           //输入流 文件来自于服务器
           //InputStream is = new FileInputStream(file);
           InputStream is = req.getServletContext().getResourceAsStream("/WEB-INF/image/059.jpg");
           InputStream is1 = Hello2Servlet.class.getClassLoader().getResourceAsStream("../image/060.jpg");
           //内容的处理方式：以文件的形式保存图片
           resp.setHeader("Content-Disposition",
                   "attachment;filename="+
                           URLEncoder.encode("吴老师","UTF-8") +"abc.jpg");
           //输出流
           OutputStream os = resp.getOutputStream();
   
           int len;
           byte[] data = new byte[1024<<3];
           while((len =is.read(data))!=-1){
               os.write(data,0,len);
           }
           is.close();
           os.close();
   ~~~

   

3. ##### 使用跳转的方式跳转其他页面返回【同步方式使用】

   请求转发：hello--->helloservlet-->main.html 转发器：RequestDispatcher

   ​	request.getRequestDispatcher("转发的页面").forward(req,resp)

   重定向：

   ​	response.sendRedirect("目的地")

4. ##### 返回错误

   ​	response.sendError(状态码，状态码对应的字符串)

## 5. 从打开浏览器输入地址到看到内容的过程？





# day04



# day05

## 复习  

#### 1 . JDBC如何返回添加到数据库中的数据的主键？

​	数字

​		Statement.RETURN_GENERATED_KEYS

​		同一连接前提下查询: select last_insert_id()

​	字符串

​		1.先查询后生成主键

​		select replace(uuid(),"-",",")

​		2.添加进数据库

#### 2. AVAWEB中文件上传的原理?

​		客户端： 

​		带有文件上传的表单的enctype = multipart/form-data，method为post，el-upload底层采用JS的方式动态组装我们的选择的文件成formData，通过原始ajax发送到服务器

​		服务器:

​		使用第三方组件解析文件 - **commons - fileupload**

​		第三方组件中获得文件，保存到指定的位置

#### 3. Statement 和PreparedStatement的区别?

**PreparedStatement & Statement**     

- Statement一般用来执行静态SQL，如果有参数，参数是通过**拼接的方式**添加到SQL中
- PreparedStatement可以执行有动态参数的SQL语句。支持 ？ 占位符。当创建PreparedStatement对象时，立即传入SQL语句，**会被发送到数据库服务器**，编译成可以执行的形式保存，真正执行时只需要传入参数。
- PreparedStatement可以避免**SQL注入的问题**
- 不能使用 ？ 占位符

- - ' ' → 中有参数 不支持?
  - 表名字不支持 ? 
  - order by / group by 不支持 ？ 

#### 4. 代码题：JDBC的流程?

​	1. 加载驱动

​		class.forName("com.mysql.cj.jdbc.Driver");

​	2.获得与数据库的连接

​		String url = "地址..."

​		Connection con = DriverManager.getConnection(url,user,pwd)

​	3.创建SQL执行器

​		String sql = select * from student

​		Statement sta = con.createStatement();

​	4.执行SQL执行器

​			ResultSet rs = sta.executeQuery(sql);

​	5.处理结果
​		while(rs.next()){
​    		int id = rs.getInt("id");
​    		String name = rs.getString("name");
​    		Date birth = rs.getDate("birth");
​    		System.out.println(id+"\t"+name+"\t"+birth);
​		}

​	6.关闭资源

​		rs.close();
​		sta.close();
​		con.close();

#### 5. JAVAWEB中请求和响应分别有哪几部分组成并举例说明?

#### 		请求:

- 请求行：请求方法-get/post/put/delete + 请求资源-/index.html + 协议版本-HTTP/1.1	

- 请求头  Host Accept ...

- 消息体：参数，key=value&key1=value1 {"key":"value"}

  #### 响应:

  - 状态行：协议版本 + 状态码 + 字符串 `HTTP/1.1 200 OK`

  - - 响应头：content-type :text/html;charset=utf-8  

  - 响应正文：<html> </html>

#### 6. 从请求处理的角度简述Servlet的生命周期（就是一次请求的处理过程）



服务器启动： 

​		1.读取自身配置文件 

​		2.读取管理的其他工程配置文件 成功启动 

客户端请求到达 

​		\1. tomcat接收请求，创建request对象和response对象。

​		\2. 根据请求判断静态资源还是动态资源，静态资源交给DefaultServlet. 

​		\3. 动态资源 根据url-pattern,找到servlet-name,根据servlet-name找到 servlet-class. 

​		\4. 判断内存中是否有对应Servlet类型的对象，如果有则返回，没有则使 用反射创建Servlet的对象。【实例化】

​		\5. tomcat调用init()初始化，并传入ServletConfig 

​		\6. tomcat调用service:执行请求 

​		\7. Tomcat根据service中的response，打包响应返回给客户端。 

​		\8. 服务器关闭时，tomcat调用destroy()..完成

#### 7. 文件下载客户端发送的是同步请求还是异步请求？【2分】文件下载的原理【5分】和关键【3分】代码？

​	\1. 文件下载是同步请求 

​	\2. 文件下载就是 把服务器端的文件以IO的形式写出到客户端。并且以文件的形式接收服务器发送过来 的数据。 

​	\3. 关键代码: 

​		1.response.setHeader("Content-Disposition","attachment;filename=文件名") 

​		2.IO复制文件

#### 8. 请求中Content-Type有哪几种类型，分别代表的含义是什么？

- `application/x-www-form-urlencoded`，tomcat可自动解析这个格式的参数，key=value
- `multipart/form-data;boundary=----adsfadfad;`

- - 从请求头中获得boundary分隔符
  - 使用getInputStream获得消息体，再将数据分割。
  - 以上工作交给第三方组件**commons-fileupload，获得（普通参数+文件参数）**

- `application/json`直接读取消息体，通过第三方组件将JSON格式的字符串转换成JAVA对象{"username":"xx","pwd":"xx"}==>User{uname,pwd}

#### 9. 加载资源 `Student.class.getClassLoader().getResourceAsStream()`和`Student.class.getResourceAsStream()`以及`ServleContext.getResourceAsStream()`的区别？

​	getClassLoader().getResourcesAsStream():包外开始寻找资源 

​	getResourceAsStream():包内寻找资源

​	ServleContext.getResourceAsStream():根目录下寻找资源

#### 10. 代码题：需求如下：客户端通过http://localhost:8080/stu/addStu的URL以`POST`请求的方式将请求传递到服务器，其中参数有两个username和password，值分别是"etoak"和"abc",另外请求头中携带一个权限认证的字段:`authoriation:加密的字符串`，现在需要在Servlet中打印输出请求参数中的值和请求中的认证字段的值，请写出Servlet类及配置?

```java

@WebServlet("/addStu") 
public class XXServlet extends HttpServlet{ 
    public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{ 
        String usenrame = req.getParameter("username"); 
        String pwd = req.getParameter("password"); 
        String author = req.getHeader("authoriation"); 
    } 
}
```



---

## day05(项目：前后端交互数据）

### 1. 数据源？

- 作用：提供连接
- 实现方案：数据库连接池
- 产品：commons-dbcp

### 2. JDBC/DAO?

![image-20230414091535062](../../../../Month 4/day05/note.assets/image-20230414091535062.png)

### 3. JAVAWEB中文件上传？

1. 浏览器

   1. 请求头：Content-Type: multipart/form-data  

      1. 

         <form  enctype="multipart/form-data">
         </form>

      2. el-upload:js的方式动态组装formData,通过ajax XmlHttpRequest 发送异步请求传递到服务器的。 

   2. 浏览器把参数按照 分隔符 分割开。

      

2. 服务器端

   1. 不会解析参数，依然会给request对象赋值（请求头 协议 方法 url ..不会解析消息体中参数）

   2. 我们可以自己手动获取分隔符，再读取消息体，按照分隔符，分割参数。

      

   3. 我们不想自己干，交给第三方组件接管请求。

      List<FileItem> items = ServletFileUpload.parseRequest(req);

### 4. 关于Content-Type[内容-类型]?

请求头的Content-Type: 

- multipart/form-data:分割符分割参数	
- application/x-www-form-urlencoded: 参数key-value
- application/json:JSON格式 {“key”:"value","key1":{}}

响应头的Content-Type:

- ​	response.setContentType("text/html;charset=UTF-8") html
- text/plain   原样显示 json
- image/jpeg
- video/mp4

### 5. JDBC事务处理？

1. 在开发中，一般事务处理是放在service层的。
2. 为什么不放在DAO层？
   1. 因为实际开发中，很多事务是跨DAO的。
   2. orderDao.insertOrder    GoodDao.updateGoods()  XX.
3. 

### 6. 实体关联？

数据库中表和表的关系:

1 vs  1:主键 、唯一外键

1  vs n： 外键

m vs n ：关联表

面向对象：

一对一： Student{School sch }

一对多: School { List<Studnet> stus}

多对多: Sc{Student s,Course c}



---

# day06(项目归纳总结)

## sql语句：

```sql
update pic set flag=1 where id in(
    select a.* from(
        select id from pic where stuid=6 order by uploadtime desc limit 1
    )a
);

select
s.id as stuid, s.name as stuname, age, birth, email, schid,
sch.id as schoolid, sch.name as schname, phone,
        concat_ws('-',pro.name,city.name,area.name,info) info,
       p.id, savepath, realname, uploadtime, flag, stuid
from student s left join school sch on s.schid = sch.id left join locations pro
on pro.id = sch.proid left join locations city
on city.id = sch.cityid left join locations area
on area.id = sch.areaid left join pic p on p.stuid = s.id and p.flag=1;

select
    s.id as stuid, s.name as stuname, age, birth, email, schid,
    sch.id as schoolid, sch.name as schname, phone,
    concat_ws('-',pro.name,city.name,area.name,info) info,
    p.id, savepath, realname, uploadtime, flag, stuid
from student s left join school sch on s.schid = sch.id left join locations pro
on pro.id = sch.proid left join locations city
on city.id = sch.cityid left join locations area
on area.id = sch.areaid left join (select * from pic where flag=1) p on p.stuid = s.id;
```







