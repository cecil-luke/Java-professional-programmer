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

#### **去重 distinct**

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





# day03

#### review

Oracle

SQL

DDL
create  alter  drop  truncate 

DML
insert  delete  update

DQL
select

DCL
grant  revoke

TCL
commit  rollback  savepoint

Oracle的数据类型：
字符型：varchar2(20)   char(6)
数值型：number(5,1)
日期型：date  timestamp

##### 建表：

create table student(
id number(5),
name varchar2(20),
birthday date,
sal number(5,1)
);

drop table student;

##### 增删改查：

新增数据：insert
insert into student values(1,'zs',sysdate,5000);
insert into student(id,name,sal) values(2,'ls',6000);

删除数据：delete
delete from student;
delete from student where id = 1;

修改数据：update
update student set sal = sal + 500;
update student set sal = sal + 500,name = 'lss' where id = 2;

查询数据：select
select * from student;
select name,sal from student where id = 2;
select name,sal from student where name = 'lss' and sal = 5500;
select name,sal * 1.3 as salary from student;
select name,sal from student where name like '张%';
select name,sal from student where name like '张,%' escape ',';

##### 事务：

四个特性：原子性，一致性，隔离性，持久性  CIAD  ACID
隔离性问题：脏读，幻读，不可重复读
隔离级别：未提交读，提交读，可重复读，序列化

is null
is not null

##### 函数：

聚组函数：max()  min()  avg()  sum() count()
单行函数：ceil()  floor()  abs()  sign()  power()  sqrt()  round()  trunc()

========================================================

#### Day 03

##### 函数：(聚组、单行)

聚组函数：max()  min()  avg()  sum() count()
单行函数：ceil()  floor()  abs()  sign()  power()  sqrt()  round()  trunc()

##### 字符函数：

upper()：转换成大写
lower()：转换成小写
initcap()：首字母大写
length()：求长度
select name,upper(name) uname,lower(name) lname,initcap(name) iname,
length(name) lename from student;

##### substr(a1,a2,a3)：截取字符串

a1：原字符串
a2：从哪开始截取
a3：截取长度
select substr('woshizhizhuxia',3) str from dual;
select substr('woshizhizhuxia',3,9) str from dual;

##### replace(a1,a2,a3)：替换字符串

a1：原字符串
a2：要替换的字符
a3：替换成的字符
select replace('woshizhizhuxia','h') str from dual;
select replace('woshizhizhuxia','h','0000') str from dual;

##### instr(a1,a2,a3,a4)：索引字符串

a1：原字符串
a2：想要找到的字符
a3：从哪开始找
a4：第几次出现
select instr('woshizhizhuxia','h') str from dual;
select instr('woshizhizhuxia','h',5) str from dual;
select instr('woshizhizhuxia','h',5,2) str from dual;

##### concat(a1,a2)：拼接字符串

select concat(name,sal) str from student;
select concat(concat(name,birthday),sal) str from student;

练习：
新增字段phone，新增phone数据，15556785678，18888888888，13856987896
查询效果展示如：155****5678，188****8888，138****7896
--14查询员工名字中不包含字母“S”员工  -- 用三种做法
--14查询员工名字中包含字母“S”员工  -- 用三种做法
--15查询员工姓名的第2个字母为“M”的员工信息  -- 用三种做法

##### 转换函数：

###### to_number()：

将一个字符类型的数值转换成数值类型  
select name,phone from student where to_number(phone) = 15556785678;

###### to_char()：

1.将数值类型转换成字符类型
select name,sal from student where to_char(sal) = '5150';
2.将**日期类型**转换成**字符类型**
select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') str from dual;
select to_char(systimestamp,'yyyy-mm-dd hh24:mi:ss:ff3') str from dual;
3.格式化字符串，常用在货币单位   
select to_char('1000000','999,999,999.99') str from dual;

###### to_date()：

将一个字符类型的日期转换成日期类型
select to_date('20230306111313','yyyy-mm-dd hh24:mi:ss') time from dual;

练习：
有一个字符串'20230306111616'，取出年，取出月，取出日

展示效果：
year     month     day
2023        03         06

##### 日期函数：

两个日期可以相减，单位是天

sqlplus中修改日期展示格式：
<u>一次性修改方法：</u>
alter session set nls_date_format = 'yyyy-mm-dd hh24:mi:ss';
永久修改方法：详见环境变量

yyyy    年    year   
mm     月   month  带'月'的月份
ddd     日   年中的日
dd       日   月中的日
d         日   周中的日
hh24   24小时制
hh12   12小时制
mi       分
ss        秒
xff       毫秒
ff3       毫秒保留三位

###### add_months()：在某个日期上添加多少个月

select add_months(sysdate,3) time from dual;

###### months_between()：两个日期之间存在多少月

select months_between(sysdate,to_date('20230606','yyyy-mm-dd')) time from dual;

###### next_day()：下一个周几是哪天

select next_day(sysdate,'星期一') time from dual;

###### last_day()：给定日期所在月份的最后一天

select last_day(sysdate) time from dual;

练习：
--10查询各月倒数第3天入职的员工信息

##### 通用函数：

###### nvl(原字符串,是空展示什么)：空值处理

select name,nvl(birthday,sysdate) birth from student;

###### nvl2(原字符串,不是空展示什么,是空展示什么)：空值处理二代

select name,nvl2(birthday,birthday,sysdate) birth from student;

练习：
--8查询所有员工 工资和奖金的和  

###### decode(c1,c2,c3,c4,c5...Cx,Cx+1)：

c1是原字符串，从第二个参数开始，每两个参数看作是一组，拿每组的第一个参数和c1进行比较，如果相同则返回该组的第二个参数

相当于：
第一次比较：c2 == c1 ? c3 ：
第二次比较：c4 == c1 ? c5 ：
...

如果参数个数是奇数个，并且最终判断没有相同的值，则返回空
如果参数个数是偶数个，并且最终判断没有相同的值，则返回最后一个参数的值

*：如果部门编号是10，则工资涨五百，如果部门编号是20，则工资减五百，其他部门加二百
select ename,deptno,sal,decode(deptno,10,sal + 500,20,sal - 500,sal + 200) salary from emp;

*：如果部门编号是10，则工资涨五百，如果部门编号是20，则工资减五百
select ename,deptno,sal,decode(deptno,10,sal + 500,20,sal - 500) salary from emp;

练习：
如果工种为CLERK，则工资减三百，如果工种为SALESMAN，则工资加三百，其他工种减二百

###### 条件取值语句：

(case  -- 拿来做比较的值
when -- 如果..
then  -- 则..
else   -- 否则..
end)  -- 结束

*：如果部门编号是10，则工资涨五百，如果部门编号是20，则工资减五百，其他部门加二百
select ename,deptno,sal,(case deptno when 10 then sal + 500 when 20 then sal - 500 else sal + 200 end) salary from emp;

*：如果部门编号是10，则工资涨五百，如果部门编号是20，则工资减五百
select ename,deptno,sal,(case deptno when 10 then sal + 500 when 20 then sal - 500  end) salary from emp;

练习：
如果工种为CLERK，则工资减三百，如果工种为SALESMAN，则工资加三百，其他工种减二百

###### 分组：group by

在一张表中，将某个或者多个列上相同的值划分为一个组，那么这张表就被分为多个组
*：如果以字段A分组，那么只能查询字段A，其他字段需要以组函数的形式出现
select deptno from emp group by deptno;
select deptno,job from emp group by deptno,job;
select deptno,count(ename) num from emp group by deptno;

练习：
面试题第五题

###### 条件：having

select deptno,count(ename) num from emp group by deptno
having count(ename) >= 5;

练习：
--29查询最低工资大于2500的各种工作

分组：聚合统计

###### 去重：distinct

*：支持单列，多列的去重
select distinct deptno from emp;
select distinct deptno,ename from emp;

###### 排序：order by

升序：asc
降序：desc

select deptno from emp order by deptno;
select deptno from emp order by deptno asc;
select ename,deptno,sal from emp order by deptno asc,sal desc;

###### 查询关键字的优先级：

select       列名  -- 优先级高于order by
from        表名  -- 优先级最高

###### 连表查询

where      条件  -- 优先级次高
group by 分组  -- 优先级次于where
having     条件  -- 优先级一定在group by之后
order by  排序  -- 优先级最低


select deptno, count(ename)  num
from emp
where job = 'CLERK'
group by deptno 
having count(ename) >= 2
order by num desc;

##### 约束：constraint

###### 主键约束：primary key

​	主键：在一张表中能够**唯一**定位一条数据的列称为**主键列**
​	*：非空且唯一

	*：建表时添加主键
	create table test(
	id number(5) primary key,
	name varchar2(20));
	
	*：建表后添加主键
	create table test2(id number(5),name varchar2(20));
	alter table test2 add constraint zj primary key(id);

###### 外键约束：foreign key    *：references

​	外键：在**子表中**有一个列引用了父**表**中的主键列，那么这个列在子表中就被称为**外键**
​	*：<u>一张表可以有多个外键</u>

	表名		主键		外键
1)	emp		empno		deptno		子表
	   dept		deptno				父表
	emp.deptno = dept.deptno

2)	dept		deptno		salno		子表
	   salgrade		salno				父表
	dept.salno = salgrade.salno

3)	emp		empno		deptno  salno	子表
	   dept		deptno				父表
	  salgrade		salno				父表
	emp.deptno = dept.deptno
	emp.salno = salgrade.salno	

非空约束：not null
唯一约束：unique
检查约束：check

<u>查看当前用户有哪些约束：</u>
select constraint_name,constraint_type,table_name from user_constraints;

**：
先建父表，在建子表，先删子表，再删父表

create table school(
id number(5) primary key,
name varchar2(20) unique not null,
addr varchar2(20));

insert into school values(1,'北京大学','北京');
insert into school values(2,'清华大学','北京');
insert into school values(3,'厦门大学','厦门');
insert into school values(4,'山东师范','济南');

create table teacher(
id number(5) primary key,
name varchar2(20) not null,
hobby varchar2(20));

insert into teacher values(1,'琛哥','剑道');
insert into teacher values(2,'周哥','打篮球');
insert into teacher values(3,'乐哥','敲代码');
insert into teacher values(4,'老大','键盘');

create table class(
id number(5) primary key,
name varchar2(20) not null unique,
tid number(5) references teacher(id));

insert into class values(1,'ET2210',3);
insert into class values(2,'ET2211',2);
insert into class values(3,'ET2212',1);

create table student(
id number(5) primary key,
name varchar2(20) not null,
birthday date,
sal number(5,1) check(sal between 5000 and 10000),
email varchar2(30) unique,
sid number(5) references school(id),
cid number(5) references class(id));

insert into student values(1,'葫芦娃',sysdate,5000,'hlw@126.com',1,2);
insert into student values(2,'金刚',to_date('19981212101010','yyyy-mm-dd hh24:mi:ss'),6000,'jg@163.com',2,2);
insert into student values(3,'蜘蛛侠',to_date('19961212101010','yyyy-mm-dd hh24:mi:ss'),7000,'zzx@yahoo.com',1,1);
insert into student values(4,'白龙',to_date('19951212101010','yyyy-mm-dd hh24:mi:ss'),8000,'bl@etoak.com',3,3);


表名		主键			外键
school		id	
teacher		id
class		id		       tid(teacher.id)
student		id		cid(class.id)   sid(school.id)

student.cid = class.id
student.sid = school.id
class.tid = teacher.id

###### 嵌套查询 = 子查询 = 某些条件是通过查询得出来的

select 嵌套查询 from 嵌套查询 where 嵌套查询 group by 嵌套查询;

*：谁和葫芦娃一个学校的？
1）葫芦娃是哪个学校的？
select sid from student where name = '葫芦娃';
2）谁还是这个学校的？
select name from student where sid = 1;

select name from student where sid = (select sid from student where name = '葫芦娃') and name <> '葫芦娃';

select name from student where (sid,name) = (select sid,name from student where name = '葫芦娃');

练习：
--24查询工资比SMITH员工工资高的所有员工信息


*：学生都上那些学校？
select student.name,(select school.name from school where student.sid = school.id) schname from student;

练习：
--2查询所有工种为CLERK的员工的工号、员工名和部门名。

emp.deptno = dept.deptno



===========================================================























