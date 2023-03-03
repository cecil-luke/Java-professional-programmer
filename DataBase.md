# **数据库**

# day01

Oracle 
11g  

Mysql
5.6  5.7  8.0

#### sql语句分类

1.SQL语句分类  **授权  用户   建表   增删改查  事务  锁**
2.**函数  分组  排序  去重**
3.**约束 子查询（嵌套查询）  连表查询**
4.**伪列  分页  各种关键字  plsql编程  存储过程  函数  触发器**

Mysql  讲一下区别


sqlplus = 让我们和数据库进行沟通的工具
regedit  = 注册表

用户：
system
scott

退出：
exit



# day02

Oracle = 数据库

sqlplus = 让我们和数据库进行沟通的工具
**regedit** = 注册表
services.msc = 服务

用户：
**system**
**scott**

退出：
**exit**

#### SQL结构化查询语言 

​	Structured Query Language = 结构化查询语言

#### DDL数据定义语言

​	Data Definition Language = 数据定义语言
​				create 创建  alter 修改  drop 删除  truncate 截断

#### DML数据操纵语言 

​	Data Manipulation Language = 数据操纵语言
​				insert 增加  delete 删除  update 修改

#### DQL数据查询语言

​	Data Query Language = 数据查询语言
​				select 查询

#### DCL数据控制语言

​	Data Control Language = 数据控制语言
​				**grant** 授权  **revoke** 取消授权

#### TCL事务控制语言

​	Transaction Control Language = 事务控制语言
​				**commit** 提交  **rollback** 回滚  **savepoint** 保存点

#### 给用户解锁：

alter user scott account unlock;

#### 给用户加锁：

alter user scott account lock;

#### 给用户授权：

grant dba to scott;

#### 给用户取消授权：

revoke dba from scott;

#### 创建新用户：

create user ET2301 identified by etoak;

#### 给用户修改密码：

alter user scott identified by etoak;

#### 删除用户：

drop user ET2301;

#### 查看当前用户：

show user;

#### 切换用户：

conn 用户名/密码;
conn scott/etoak;

练习：
在scott用户下新建用户ET，在ET用户下新建用户ET2301，连接ET2301，删除ET2301

#### Oracle的数据类型：

**字符型**：
varchar2(20)   可变类型   0-4000字节          
char(2)            固定类型   0-2000字节

**数值型：**
number(5)
number(5,1)     

**日期型：**
date
timestamp  包含毫秒数

#### 创建表：

create table student(
id number(5),
name varchar2(20),
birthday date,
sal number(5,1)
);

练习：
创建teacher表，字段有老师工号，老师姓名，老师入职时间，老师年薪（五位数以上，两位小数）

#### 建表以后，修改表名：

alter table stu rename to student;

#### 建表以后，新增字段：

alter table student **add** email varchar2(30);

#### 建表以后，修改列名：

alter table student rename **column** email to youxiang;

#### 建表以后，修改数据类型：

alter table student modify youxiang varchar2(50);

#### 建表以后，删除字段：

alter table student drop column youxiang;

#### 建表以后，删除表：

drop table student2;      12

#### 建表以后，截断表：

**truncate** table student;   23

练习：
给teacher表重命名tea，新增字段aihao，将aihao修改为hobby，删除hobby，截断表，删除表

1.表结构
2.表数据
3.表空间

#### 查看表结构：

desc student;

#### 查看当前用户下有哪些表：

select table_name from user_tables;


增删改查：

#### 新增数据：insert

insert into student values(1,'zs',sysdate,5000);
insert into student (id,name,sal) values(2,'ls',6000);

#### 删除数据：delete

delete from student;
delete from student where id = 1;

#### 修改数据：update

update student **set** sal = sal + 500;
update student set sal = sal + 500 **where** id = 2;
update student set sal = sal + 500**,**name = 'lss' where id = 2; 	用**，**隔开表示并且条件

#### 查询数据：

select * from student;
select name,sal from student;
select name,sal from student where name = 'zs';

练习：
1）新增数据：张三，李四，王五
2）给张三和李四工资涨百分之三十
3）用查询的方式展示出题二的效果

#### 别名：

select name,sal * 1.3 as salary from student where name = 'zs' or name = 'lss';
select name,sal * 1.3 salary 
from student 
where name = 'zs' or name = 'lss';

select s.name,s.sal * 1.3 salary 
from student s
where s.name = 'zs' or s.name = 'lss';

#### like：模糊查询

**%：**代表**任意位**的**任意字符**
**_：**代表**一位**上的**任意字符**
select name from student where name like 'ET%';
select name from student where name like 'ET_';
select name from student where name like 'ET_%';
select name from student where name like '%s%';

not like：
select name from student where name not like '%s%';

练习：
姓张的和姓李的工资涨百分之三十   

#### escape：逃离符

通过指定一个字符位进行逃离，来保证like之后的特殊字符看作是普通字符
select name from student where name like 'ET,_' **escape ','**;
select name from student where name like 'ET._.%' escape '.';

#### 条件：

**and**：并且   两个条件必须都成立
**or**：或者      有一个条件成立即可
**between and**：闭合区间
select name,sal from student where sal between 5000 and 5500;
select name,sal from student where sal >= 5000 and sal <= 5500;

#### 比较：

>  <  >=  <=  !=
>  <> 不等于

#### 计算：

 +-*/

is null：是空
is not null：非空
select * from student where birthday is not null;

#### 函数：

*****：经过函数修饰的**列**起**别名**
**dual**：**万能表，测试表**

**聚组函数**：聚簇函数：组函数：多行函数     和分组group by一起使用
max() 最大值  min() 最小值  sum() 求和  avg() 求平均值  count() 求记录数
select max(sal) maxsal,min(sal) minsal,sum(sal) sumsal,avg(sal) avgsal
from student;

select count(*) num from student;   经过函数修饰的**列**起**别名** 并计算该列的数量
select count(1) num from student;
select count(birthday) num from student;

**去重 distinct**
select count(distinct sal) num from student;

**单行函数：**
ceil()：向上取整
floor()：向下取整
select ceil(12.3) num from dual;	dual	//万能表，测试表

abs()：求绝对值
select abs(-666) num from dual;

power(a,b)：求a的b次方
select power(2,3) num from dual;

sqrt()：求正平方根
select sqrt(16) num from dual;

sign()：求符号位   正数返回1，负数返回-1，零返回0
select sign(-666) num from dual;

round()：求四舍五入
select round(3.141592654) num from dual;
select round(3.141592654,3) num from dual;

trunc()：求直接截断
select trunc(3.141592654) num from dual;
select trunc(3.141592654,3) num from dual;

字符函数：
日期函数：
转换函数：
通用函数：



========================================================



#### TCL：事务控制语句

*：事务只会影响DML操作

事务：在一些列操作中有多个步骤，只有所有的步骤成功执行那么整个操作才算完成，如果有其中一个环节失败，那么整个操作都算失败。

##### Oracle中在sqlplus中开启事务：

**set autocommit off;**   -- 关闭自动提交
**set autocommit on;**   -- 开启自动提交
提交操作：将受影响的数据持久化到数据库中

##### 事务特性：

**原子性**：事务不可再分
**一致性**：数据类型保持一致
**持久性**：事务能够将数据持久化到数据库中
**隔离性**：多个事务之间可能会产生一些隔离性问题

##### 隔离性问题：

**脏读**：事务B读取到事务A中未提交的数据，然后事务A对刚才的操作进行了回滚操作，那么事务B读取到的数据就无效了，也就是脏数据
-- 读到了其他事务未提交的数据

**不可重复读**：事务A对表内数据修改提交后，事务B再次读取数据，发现和之前读取到的数据不一样
-- 一次读取到的数据其他事务对数据进行了修改，再次读取数据不一致

**幻读**：事务A对表新增数据并提交以后，事务B再次读取这张表，就会发现数据多了，就像发生了幻觉一样
-- 相同的查询条件，在别的事务添加或者删除数据后，再次查询不一致

##### **隔离级别：**

数据库事务的隔离级别一共有4个，由低到高依次为Read uncommitted
,Read committed,Repeatable read,Serializable,这四个隔离级别可以逐个解决脏读，不可查重复读，幻读这几个问题。
                                                    脏读      不可重复读       幻读
**未提交读**  Read uncommitted       会             会                 会
**提交读**     Read committed            不会         会                 会
**可重复读**  Repeatable read            不会         不会              会
**序列化**     Serializable                    不会         不会             不会

1.ISOLATION_READ_UNCOMMITTED:【**很少应用，效果很差**，效率也没高哪去】
这个是**事务最低的隔离级别**，它允许另外一个事务可以看到这个事务未提交的数据。
2.ISOLATION_READ_COMMITTED:【**Oracle默认的隔离级别** 大多数数据库默认】
保证一个事务修改的数据只有在提交以后别的事务才能读取。
3.ISOLATION_REPEATABLE_READ：【**Mysql默认的隔离级别**】
4.ISOLATION_SERIALIZABLE：【根治所有问题 但是牺牲效率】
**花费高代价但是最可靠的事务隔离级别**，事务被处理为**顺序执行**

##### Oracle中的提交读级别：（默认）

set transaction isolation level read committed;

*：更改数据库的隔离级别必须在开启事务的第一句话来更改

**commit**：提交  将数据持久化到数据库中
**rollback**：回滚  将事务中的操作回滚到第一步操作之前，就当什么都没发生
**savepoint**：还原点  可以回退到指定的位置

**显示提交**：在事务中<u>手动commit</u>
**隐式提交**：在事务中，如果正在执行DML操作，突然做了一个DDL操作，<u>数据库会自动在DDL操作之前隐式的做一个commit操作</u>

##### 当发生以下操作时，事务将结束

1.利用commit/rollback进行事务提交和回滚
2.执行DDL语句时，事务将自动提交
3.如果使用sqlplus时，正常退出事务会自动提交，**非正常退出**事务**回滚TCL**：事务控制语句
*：事务只会影响DML操作

事务：在一些列操作中有多个步骤，只有所有的步骤成功执行那么整个操作才算完成，如果有其中一个环节失败，那么整个操作都算失败。



============================================================================



#### 锁    

##### -- 锁的介绍

锁可以防止事务之间的破坏性交互，约束了最大程度的并发性，数据的完整性

##### -- 锁的分类

1.**排他锁（X锁）**防止资源共享，也就是当一个事务正在操作数据时，其他事务不可以操作这个事务的数据。
2.**共享锁（S锁）**被锁住的数据只能被读取，但是不能修改。

##### -- 锁的类型

**DML锁**：也就**数据锁**，用于**保护数据**，事务在最开始时添加，通过commit或者rollback释放。
**DDL锁**：可以**保护数据对象的结构**。Oracle自动的施加的释放。
**内部闩锁**：保护数据库的**内部结构**，完全自动调用。

**行级锁**：也叫**事务锁**，防止数据被同时<u>多个事务进行修改</u>，直到commit或者rollback。
**表级锁**：防止在修改数据的时候，表结构发生变化。

select name,salary from student for update;
会对student表进行加锁，此时只允许当前session对已经存在的数据进行更新，其他session仍可以进行insert操作。

##### -- 锁等待和死锁

**锁等待**也叫**锁冲突**，锁等待会严重影响数据库的性能和日常工作。
**死锁**，也就锁等待的一种，但是死锁会让事务一直处于锁等待的状态。

##### -- 查看是否有死锁

select sid,serial#,username from v$session where sid in (select blocking_session from v$session);

##### -- 查看死锁的语句

select sql_text from v$sql where hash_value in (select sql_hash_value from v$session where sid in(select session_id from v$locked_object));

##### 解决死锁：

https://localhost:1158/em
性能-->其他监视链接-->实例锁































