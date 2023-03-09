### DateBase of practise about Oracle

============================================================================

#### one

1.查询20号部门的所有员工信息

select * from emp where deptno = 20;

2.查询所有工种为CLERK的员工的工号、员工名和部门名

select empno,ename,dept.dname dname  

from emp ;

left join dept on emp.deptno = dept.deptno where job like 'CLERK';

3.查询奖金（COMM）高于工资（SAL）的员工信息

select * from emp where comm > sal;

4.查询奖金高于工资的20%的员工信息

select * from emp where comm > (sal*0.2);

5.查询10号部门中工种为MANAGER和20号部门中工种为CLERK的员工信息

select * from emp where job = 'MANAGER' and deptno = 10 or job = 'CLERK' and deptno = 20;

6.查询所有工种不是MANAGER和CLERK，且工资大于或等于2000的员工信息

select * from emp where job not in('MANAGER','CLERK') and sal>2000;

7.查询有奖金的员工的不同工种

select distinct job frm emp where comm is not null;

8.查询所有员工 工资和奖金的和

select ename,(sal+nvl(comm,0))sal from emp;

9.查询没有奖金或奖金低于100的员工

select * from emp where comm is null or comm<100;

10.查询个月倒数第二天入职的员工信息

select * from emp where hiredate = (LAST_DAY(hiredate)-2);

select * from emp where hiredate in(select  (LAST_DAY(hiredate)-2) from emp);

11.查询员工工龄大于或等于10年的员工信息

select * from emp where months_between(sysdate,hiredate)>=(10*12);

12查询员工信息，要求以首字母大写的方式显示所有员工的姓名

select initcap(ename) name,e.* from emp;

13.查询员工名正好为6个字母的员工信息

select * from emp where length(ename) = 6;

14查询员工名中不包含字母“S”员工

select * from emp where enam not like '%S%';

select * from emp where instr(ename,'S',1,1)=0;

select * from emp where ename not in (select ename from emp where ename like '%S%');

15.查询员工姓名的第二个字母为“M”的员工信息

select * from emp where substr(ename,2,1) = 'M';

select * from emp where ename like '_M%';

select * from emp where instr(ename,'M',2,1) = 2;

16.查询所有员工姓名的前3个字符
select substr(ename,1,3) from emp;

17.查询所有员工的姓名，如果包含字母“S”，则用“s”替换

select replace(ename,'S','s') name from emp;

18.查询员工的姓名和入职日期，并按入职日期从先到后进行排序

select ename,hiredate form emp order by hiredate asc;

19.显示所有的姓名、工种、工资和奖金，按工种降序排序，若工种相同则按工资升序排序

select ename,job,sal,comm from emp order by job desc,sal asc;

20.显示所有员工的姓名、入职的年份和月份，按入职日期所在的月份排序

select ename,to_char(hiredate,'yyyy-mm') from emp order by to_char(hiredate,'mm')asc,to_char(hiredate,'yyyy')desc;

21.查询在2月份入职的所有员工信息

select * from emp where to_char(hiredate,'mm')  = '02';

22.查询所有员工入职以来的工作期限，用“XX年XX月XX日”的形式表示

select ename,floor((sysdate-hiredate)/365)||'年'||floor(mod((sysdate-hiredate),365)/30)||'月'||ceil(mod(mod((sysdate-hiredate),365),30))||'天' from emp;

23.查询至少有一个员工的部门信息

select * from dept where deptno in(select deptno from emp);

select * from dept where deptno in(select distinct deptno from emp where mgr is not null);

24.查询工资比SMITH员工工资高的所有员工信息

select * from from emp where sal > (select sal from emp where ename like 'SMITH');

25.查询所有员工的姓名及其直接上级的姓名

select a.ename,b.ename from emp a join emp b on a.mgr = b.empno;

select staname,ename supname from (select ename staname,mgr from emp)t join emp on t.mgr = emp.empno;

26.查询入职日期在于其直接上级领导的所有员工信息

select * from emp where empno in(select staemno from  (select empno staempno,hiredate stehiredate,mgr from emp)t join emp on t.mgr = emp.empno and stahiredate < hiredate)；

select a.ename,b.ename,a.hiredate,b.hiredate from emp a join emp b on a.mgr  = b.empno where a.hiredate< b.hiredate;

27查询所有部门及其员工信息，包括那些没有员工的部门

select * from dept left join emp on emp.deptno = dept.deptno order by dept.deptno;

28.查询所有工种为CLERK的员工的姓名及其部门名称

select ename,dname from emp join dept on emp.deptno = dept.deptno and job like 'CLERK';

29.查询最低工资大于2500的各种工作

select job from (select min(sal) min_sal,job from emp group by job )where min_sal>2500;

select job from emp group by job having min(sal)>2500;

select distinct job from emp where job not in(select job from emp where sal < 2500);

====================================================================================

#### two

31.查询最低工资低于2000的部门及其员工信息

select dept.dname,dept.loc,emp.* 

from dept 

join emp  on dept.deptno = emp.deptno 

where dept.deptno 

in(select deptno from emp group by deptno having min(sal)<2000);

select * freom dept 

left join emp on emp.deptno = dept.deptno

where dept.deptno not in(select dept.deptno from dept left join emp on emp.deptno = dept.deptno group by dept.deptno having min(sal)>=2000);

32.查询在SALES部门工作的员工的姓名信息

select ename,dname from emp,dept where emp .deptno = dept.deptno and dname = 'SALES';

select ename from  emp where deptno = (select deptno from dept where dname = 'SALES');

33.查询工资高于公司平均工资的所有员工信息

select * from emp where sal > (select avg(sal) frm emp);

34.查询与SMITH员工从事相同工作的所有员工信息

select * from emp where job = (select job from emp where ename = 'SMITH') and ename <> 'SMITH';

35.列出工资等于30号部门中某个员工工资的所有员工的姓名和工资

select ename,sal ,deptno from emp where sal in (select sal from emp where deptno = 30) and deptno  <> 30;

select e.ename,e.sal from (select sal s, deptno d from emp where deptno = 30)

join emp e on e.deptno <>d where e.sal = s;

36.查询工资高于30号部门中工作的所有员工的工资的员工姓名和工资

elect ename,sal from emp where sal > all(select sal from emp where deptno = 30);

37.查询每个部门中的员工数量、平均工资和平均工作年限 	floor()向下取整ceil()向上取整

select deptno,count(empno),floor(avg(sal)),floor(avg(sysdate-hiredate)/365) from emp group by deptno;

39.查询各个部门的详细信息以及部门人数、部门平均工资

select deptno,count(ename),avg(sal) from emp 

join dept on dept.deptno = emp.deptno

group by emp.deptno;

select dept.*,a.c,a.avg from dept left join (select deptno d,count(ename) c,round(avg(sal))avg from emp group by deptno) a on d= dept.deptno;

40.查询各种工作的最低工资

select job,min(sal) from emp group by job;

41.查询各个部门中的不同工种的最高工资

select dept.dname,emp.job,max(sal) from dept left join emp on emp.deptno = dept.deptno group by dept.dname,emp.job;

select deptno,job,max(sal) from emp group by deptno,job;

42.查询10号部门员工以及领导的信息

select e1.*,e2.* from emp e1 left join emp e2 on e1.mgr = e2.empno where e1.deptno = 10;

select * from emp where empno in (select mgr frm emp where deptno = 10) or deptno = 10;

43.查询各个部门的人数及品骏工资

select deptno,coutn(deptno) 部门人数,round(avg(sal)) 平均工资 from emp group by deptno;

44.查询工资为某个部门平均工资的员工信息

select * from emp where sal in(select avg(sal) from emp group by deptno);

45.查询工资高于本部门平均工资的员工的信息

select e.* from emp e join(select deptno,round(avg(sal))avgsal from emp roup by deptno) d on e.depnto = d.deptno and e.sal > d.avgsal;

select * from emp a where a.sal > (select avg(sal)from emp b where a.depno = b.deptno);

46.查询工资高于本部门平均工资的员工的信息及其部门的平均工资

select e.* ,avgsal from emp e join (select deptno,round(avg(sal))avgsal from emp group by deptno) d on e.deptno = d.depnto and e.sal > d.avgsal;

47.查询工资高于20号部门某个员工工资的员工信息

select emp.* from emp where sal > any(select sal form emp where depno = 20);

48.统计各个工种的人数与平均工资

select job,count (empno),avg(sal) from emp group by job;

49.统计每个部门中各个工种的人数与平局工资

select job,deptno,count (empno) ,avg(sal) from emp group by deptno,job;

50.查询工资、奖金与10号部门某个员工工资、奖金都相同的员工的信息

select e1.* from emp e1 join (select sal ,comm from emp e2 where depnto = 10)a on a.sal = e1.sal and nvl(e1.comm,0) = nvl(a.comm,0) and depno <> 10;

51.查询部门人数大于5的部门的员工信息

select * from emp where deptno in(select deptno from emp group by depno having count(*)>5);

52.查询所有员工工资都大于1000的部门的信息

select * from dept where 1000<(select min(sal) from emp where emp.deptno = dept.deptno);

select * from dept where deptno in(select deptno from emp group by depno having min(sal)>1000);

53.查询所有员工工资都大于1000的部门的信息及其员工信息

select * from emp join (select * from  dept where depno in(select deptno from emp group by deptno having min(sal)>1000))a on emp.deptno  = a.deptno;

select * from dept d join emp e on d.deptno= e.deptno where 1000<(select min (sal)from emp where deptno = d.deptno);

54.查询所有员工工资都在900~3000之间的部门的信息

select * from dept where deptno in (select deptno from emp group by deptno havign min(sal) from emp where deptno = d.depno);

select * from dept d join emp e on d.deptno = e.deptno where 1000<(select min (sal)from emp  where deptno = d.deptno);

55.查询所有员工工资都在900~3000之间的员工所在部门的员工信息

select  * from emp where deptno in (select deptno from emp group by deptno having min(sal)>900 and max(sal)<3000);



























































