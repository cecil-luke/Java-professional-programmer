Day01(git预习)

## 1. Git介绍

- [官方网站](https://git-scm.com)

- [Pro Git书籍地址](https://www.git-scm.com/book/zh/v2)

- [博客：Git十周年，Linus专访](https://blog.csdn.net/AlvinPanda/article/details/77411157)

### 1.1 Git是什么

​		Git是一个免费开源的分布式版本控制系统，可用于高效的处理任何大大小小的项目；

### 1.2 Git出现的背景

​		1991年Linus开发了Linux系统，随后将源代码开放，此后Linux系统不断发展；

​		Linus虽然创建了Linux，但Linux的壮大是靠全世界热心的开发者，这么多人在世界各地为Linux编写代码，那Linux的代码是如何管理的呢？

​		在2002年以前，Linux代码由Linus本人通过手工方式合并代码！

​		Linus坚定地反对CVS和SVN，这些集中式的版本控制系统不但速度慢，而且必须联网才能使用。有一些商用的版本控制系统，虽然比CVS、SVN好用，但那是付费的，与Linux的开源精神不符。

​		到了2002年，Linux系统已经发展了十年了，代码库之大让Linus很难继续通过手工方式管理了，开源社区的精英们也对这种方式表达了强烈不满，于是Linus选择了一个商业的**分布式版本控制系统BitKeeper**，BitKeeper的东家BitMover公司出于人道主义精神，授权Linux开源社区免费使用这个版本控制系统。

​		在2005年，Samba文件服务器开发人Andrew Tridgell试图破解BitKeeper的协议，被BitMover公司发现了，于是BitMover公司想让Linus公开发邮件道歉，保证以后严格约束社区开发者，否则，收回Linux开源社区的免费使用权。Linus迫不得已，自己开发了一个分布式版本控制工具代替了BitKeeper。

​		Git迅速成为最流行的分布式版本控制系统，尤其是2008年，GitHub网站上线了，它为开源项目免费提供Git存储，无数开源项目开始迁移至GitHub。

**Linus在Git十周年专访中的对Git的说明**

​	`我大概花一天让Git能达到自己管理自己的程度（self-hosting），之后我就开始用Git提交程序代码了。我的大部分工作是在白天完成的，不过也有几天工作到深夜。我觉得最有趣的地方在看到Git如何快速的成形。在Git的第一次提交并没有写很多程序，但是已经实现作出提交程序代码的基本功能。写Git并不会很难，比较难的是思考如何Git组织档案的方式。`

​	`我想强调，Git从无到有大概花了我十天（包含我第一次用Git提交核心程序代码），而且我也不是焚膏继晷的完成Git。这都取决于对Git的基本概念是否很清楚，早在着手写Git前，我已经看到其他源代码控制系统的缺陷。我只是不想重蹈覆辙。`

## 2. 版本控制系统的演化 

### 2.1 本地版本控制

​		许多人习惯用复制整个项目目录的方式来保存不同的版本，或许还会改名加上备份时间以示区别。这么做唯一的好处就是简单，但是特别容易犯错，有时候会混淆所在的工作目录，一不小心会写错文件或者覆盖意想外的文件。

​		为了解决这个问题，人们很久以前就开发了许多种本地版本控制系统，大多都是采用某种简单的数据库来记录文件的历次更新差异。

​		其中最流行的一种叫做 RCS，现今许多计算机系统上都还看得到它的踪影。 [RCS](https://www.gnu.org/software/rcs/) 的工作原理是在硬盘上保存补丁集（补丁是指文件修订前后的变化）；通过应用所有的补丁，可以重新计算出各个版本的文件内容。

<img src="imgs/2.本地版本控制.png" style="zoom:40%; margin-left:60px" /> 

### 2.2 集中式版本控制

​		接下来人们又遇到一个问题，如何让在不同系统上的开发者协同工作？ 于是，集中化的版本控制系统（Centralized Version Control Systems，简称 CVCS）应运而生。 这类系统，诸如CVS、**Subversion**以及 Perforce 等，都有一个单一的集中管理的服务器，保存所有文件的修订版本，而协同工作的人们都通过客户端连到这台服务器，取出最新的文件或者提交更新。 多年以来，这已成为版本控制系统的标准做法。

​		这种做法带来了许多好处，特别是相较于老式的本地 VCS 来说。 现在，每个人都可以在一定程度上看到项目中的其他人正在做些什么。 而管理员也可以轻松掌控每个开发者的权限，并且管理一个 CVCS 要远比在各个客户端上维护本地数据库来得轻松容易。

​		这么做最明显的缺点是**中央服务器的单点故障**， 如果宕机一小时，那么在这一小时内，谁都无法提交更新，也就无法协同工作。 如果中心数据库所在的磁盘发生损坏，又没有做恰当备份，毫无疑问你将丢失所有数据——包括项目的整个变更历史，只剩下人们在各自机器上保留的单独快照。 本地版本控制系统也存在类似问题，只要整个项目的历史记录被保存在单一位置，就有丢失所有历史更新记录的风险。

<img src="imgs/3.集中式版本控制.png"  style="zoom:40%;  margin-left:60px" /> 

### 2.3 分布式版本控制

​		于是**分布式版本控制系统**（Distributed Version Control System，简称 DVCS）面世了。 在这类系统中，像 Git、Mercurial、Bazaar 以及 Darcs 等，客户端并不只提取最新版本的文件快照， 而是把代码仓库完整地镜像下来，包括完整的历史记录。 这么一来，任何一处协同工作用的服务器发生故障，事后都可以用任何一个镜像出来的本地仓库恢复。 因为每一次的克隆操作，实际上都是一次对代码仓库的完整备份。

​		许多这类系统都可以指定和若干不同的远端代码仓库进行交互。这样就可以在同一个项目中，分别和不同工作小组的人相互协作。 你可以根据需要设定不同的协作流程，比如层次模型式的工作流，而这在以前的集中式系统中是无法实现的。

<img src="imgs/4.分布式版本控制.png" style="zoom: 50%;  margin-left:60px" /> 

## 3. Git安装 - Windows版

- 下载地址：[地址](https://git-scm.com/download/win)

<img src="imgs/1.Git下载.png" style="zoom:50%; margin-left: 60px" /> 

## 4. Git的三大区域

1. 工作区

2. 暂存区（index、stage）

3. 版本库

   本地版本库

   远程版本库

## 5. 掌握几个Linux命令

1、**cd** - 切换目录change directory

- **`绝对路径`**

  Linux、Mac OS：以根目录 / 开始的文件名或目录名称，如/opt、/home

  Windows：C:/windows、D:/java

- **`相对路径`**

  相对于当前路径的文件名写法。

  Linux、Mac OS：如./lib/sudo/ 或 ../lib/sudo/等，可以认为开头不是/就是相对路径；

  Windows：./windows

- **`4个特殊目录`**

  .（或./）: 当前目录

  ..（或../）: 上一级目录

  **~**：用户主目录(用户根目录), C:/users/用户名/

  -：上一次所在目录

2、**pwd** - 显示当前所在目录

3、**ls** - 显示某个目录下的内容

- ls -l：显示详细信息

- ls -a：显示所有文件（包括隐藏文件）

4、**cat** - 查看文件内容

5、**touch** - 创建文件

6、**echo + `>> 或 >`**

​		echo "文件内容" >> 文件

​		echo "文件内容" > 文件

​		**`> 和 >>区别`  **：

​		**`>`**：覆盖原有内容

​		**`>>`**：在原有内容上追加内容

7、**mkdir** - 创建目录

​		参数-p：递归创建子目录

​		`mkdir -p test/test2/test3`

8、**rm** - 删除文件

​		`rm -rf 文件\目录`

9、**mv** - 重命名\移动文件、目录

​		mv   原文件名    新文件名

​		mv   原文件(目录)    指定的目录





---



# Day02(gitee)

## 1. 练习 - 学生管理

### 1.1 创建版本库，在版本库中提交一个文件：Student.java

1. 创建student目录，切换到student目录

   <img src="imgs/image-20230508092837735.png" alt="image-20230508092837735" style="zoom:40%;" /> 

2. 在student目录中初始化本地版本库

   <img src="imgs/image-20230508092941705.png" alt="image-20230508092941705" style="zoom:50%;" /> 

3. 配置用户名和邮箱

   <img src="imgs/image-20230508093933833.png" alt="image-20230508093933833" style="zoom:50%;" /> 

4. 创建Student.java，提交到本地版本库

   <img src="imgs/image-20230508094237020.png" alt="image-20230508094237020" style="zoom:50%;" /> 

### 1.2 开发添加学生功能

<img src="imgs/image-20230508094558859.png" style="zoom:50%; margin-left:60px" /> 

### 1.3 开发查询功能 - （向Student.java中写一行注释“//查询学生”），提交到本地版本库

<img src="imgs/image-20230508094805778.png" style="zoom:50%; margin-left:60px" />

### 1.4 开发聊天功能 - （向Student.java中写一行注释“// 聊天”），提交到本地版本库

<img src="imgs/image-20230508094929265.png" style="zoom:50%; margin-left:60px" />

## 2. 重置版本

- **前提：要重置的版本不应该推送到远程版本库**

1. **重置版本库、暂存区和工作区**：`git reset --hard 版本记录`

   <img src="imgs/image-20230508100105946.png" alt="image-20230508100105946" style="zoom:50%;" /> 

2. **重置版本库**，不重置暂存区和工作区：`git reset --soft 版本记录`

3. **重置版本库和暂存区**，不重置工作区：`git reset --mixed 版本记录`

## 3. 撤销暂存区修改

- 实际就是撤销刚执行的`git add`步骤

1. `git reset HEAD 文件...`
2. `git restore --staged 文件... `(git 2.20 +)

## 4. 撤销工作区修改

- 前提：要撤销的文件一定已经生成版本了

1. `git checkout -- 文件...`
2. `git restore 文件...`

## 5. 分支

### 5.1 创建dev分支，在dev分支上开发成绩查询(Score.java)，合并回master分支

1. 创建dev分支，切换到dev分支

   <img src="imgs/image-20230508105756665.png" style="zoom:40%;" /> 

2. **在dev分支上开发成绩查询功能，提交到本地版本库**

   <img src="imgs/image-20230508110121175.png" style="zoom:40%;" /> 

3. **切换到master分支，合并dev分支的版本**

   <img src="imgs/image-20230508110602381.png" style="zoom:40%;" /> 

4. 合并结果展示

   ![image-20230508110805537](imgs/image-20230508110805537.png)

5. **删除dev分支**

   <img src="imgs/image-20230508111014183.png" style="zoom:40%;" /> 

### 5.2 创建dev分支并切换到dev分支，在dev和master上分别提交一个版本，将dev分支的版本合并到master上

1. 创建dev分支，并直接切换到dev分支，在dev上开发课程查询（Course.java），提交到本地版本库

   <img src="imgs/image-20230508112157272.png" style="zoom:40%;" /> 

2. 切换到mster分支分支，在master上开发作业功能（Homework.java），提交到本地版本库

   <img src="imgs/image-20230508112410150.png" style="zoom:40%;" /> 

3. 在master上合并dev分支的版本

   `git merge dev`

4. 合并结果

   ![image-20230508112950874](imgs/image-20230508112950874.png)

### 5.3 分支命令

1. 创建分支：`git branch 分支名称`
2. 切换分支：`git checkout 分支名称`
3. 创建并直接切换分支：`git checkout -b 分支名称`
4. 查看本地分支列表：`git branch -l`
5. 查看所有分支列表：`git branch -va`
6. 合并分支：`git merge 分支名称`
7. 删除分支：`git branch -d 分支名称`

## 6. 在gitee.com上注册账号，并配置SSH KEY

### 6.1 配置SSH Key

1. 执行命令

   `ssh-keygen ed25519 xxx@xxx.com`

2. 拷贝用户目录下的`id_算法名.pub`文件的公钥到Gitee


### 6.2 创建远程版本库

<img src="imgs/image-20230508114714507.png" style="zoom:40%; margin-left:60px" />

### 6.3. 为本地版本库关联刚创建远程版本库，并将本地版本库的版本推送到远程版本库

1. 关联远程版本库

   <img src="imgs/image-20230508115421174.png" alt="image-20230508115421174" style="zoom:33%;" /> 

2. 推送版本到远程版本库

   `git push origin master`

## 7. 模拟两个用户协同开发

### 7.1 克隆一个远程版本库，并配置用户名为lisi和邮箱lisi@etoak.com

<img src="imgs/image-20230508143515614.png" style="zoom:40%;" /> 

<img src="imgs/image-20230508143740865.png" style="zoom:40%;" /> 

### 7.2 lisi和zs分别开发一个新的功能，分别向远程版本库库推送

1. lisi先在stu仓库中开发签到功能（Sign.java），推送到远程版本库

   <img src="imgs/image-20230508144424371.png" style="zoom:40%;" /> 

2. zs在student仓库中开发签退功能（Out.java），推送到远程版本库，但是被远程版本库拒绝了

   **拒绝原因：本地没有远程版本库的所有版本**

   <img src="imgs/image-20230508145758827.png" style="zoom:40%;" /> 

3. 所以，zs应该先拉取远程版本库的版本，与本地的版本合并之后，再向远程版本库推送

   <img src="imgs/image-20230508150940827.png" style="zoom:40%;" /> 

   <img src="imgs/image-20230508151014290.png"  style="zoom:40%;" /> 

## 8. 版本冲突说明

- 多个用户同时修改了相同的文件

1. 修改相同文件的不同行：一般情况下，版本控制系统都可以自动合并
2. 修改相同文件的相同行：合并时出现冲突，需要手动解决冲突
3. 既修改了不同行，也修改了相同行：一般情况下不同行自动合并，相同行手动解决冲突

### 8.1 lisi和zs同时修改签到功能

1. lisi先更新最新版本，修改签到功能，推送到远程版本库

   <img src="imgs/image-20230508152225001.png"  style="zoom:40%;" /> 

2. zs在没有更新版本的情况下，也修改签到功能，向远程版本库推送时被拒绝

   <img src="imgs/image-20230508152656270.png" style="zoom:40%;" /> 

3. 所以，zs先拉取最新的版本，此时，出现版本冲突了

   <img src="imgs/image-20230508152817384.png" style="zoom:40%;" /> 

4. zs要先解决冲突

5. zs使用`git add 命令`标记解决了冲突（告诉Git冲突解决了）

   <img src="imgs/image-20230508153108834.png"  style="zoom:40%;" /> 

6. zs使用`git commit -m ""命令`完成本次版本的合并

   <img src="imgs/image-20230508153153101.png" style="zoom:40%;" /> 

7. zs使用`git push`将合并结果推送到远程版本库

   

## 9. IDEA配置Git

<img src="imgs/image-20230508153857913.png"  style="zoom:40%; margin-left: 60px" /> 

## 10. 删除文件

- 删除版本库的文件也是对文件的修改，也需要现在本地版本库操作，然后推送到远程版本库

1. 手动删除：右击文件点击删除或者执行`rm -rf 文件`

   先执行`git add .`

   再执行`git commit -m "xx"`

2. 使用`git rm 文件...`

   直接执行`git commit -m "xx"`

## 11. 重命名文件

- 重命名版本库的文件也是对文件的修改，也需要现在本地版本库操作，然后推送到远程版本库

1. 手动重命名：右击文件点击重命名或者执行`mv 旧文件名 新文件名`

   先执行`git add .`

   再执行`git commit -m "xx"`

2. 使用`git mv 旧文件名 新文件名`

   直接执行`git commit -m "xx"`

## 今天练习

<img src="imgs/image-20230508174926631.png" style="zoom:50%; margin-left: 60px" /> 



---



# Day03(SpringMVC处理器适配器)



# 上午内容(git rebase)

1. git rebase命令
2. git diff命令
3. 文件在三大区域穿梭

## 1. git rebase命令

1. 修改提交记录的消息（commit msg）
2. 合并历史记录
3. 合并分支，可以将历史记录合并为一条直线

### 1.1. 修改提交记录的消息（commit msg）

- **<font color="red">前提：要修改的历史记录没有推送到远程版本库</font>**

1. 分三次修改签到功能，向本地版本库提交三个版本

   <img src="imgs\image-20230509092606106.png" style="zoom:40%;" /> 

2. 使用`git rebase`修改第二次历史记录的消息

   <img src="imgs\image-20230509092825017.png" style="zoom:40%;" /> 

### 1.2 合并历史记录

- **<font color="red">前提：要修改的历史记录没有推送到远程版本库</font>**

1. 使用`git rebase`合并1.1节中提交的三个版本

   <img src="imgs\image-20230509093734938.png" style="zoom:40%;" /> 

   <img src="imgs\image-20230509093838369.png" style="zoom:40%;" /> 

### 1.3 合并本地分支的版本

- **<font color="red">前提：要修改的历史记录没有推送到远程版本库</font>**

1. 创建**test分支**

   <img src="imgs\image-20230509095224549.png" style="zoom:40%;" /> 

2. 在master分支上修改课程功能（修改Course.java），提交到本地版本库

   <img src="imgs\image-20230509095449294.png" style="zoom:40%;" /> 

3. 切换**test分支**上修改课程功能（修改Course.java），提交到本地版本库

   <img src="imgs\image-20230509095608053.png" style="zoom:40%;" /> 

4. 在**test分支，将test分支**的版本rebase到master，此时出现合并冲突

   <img src="imgs\image-20230509100007953.png" style="zoom:40%;" /> 

5. 手动解决test分支上的冲突

6. 使用`git add`标记冲突解决了（告诉Git冲突解决了）

   <img src="imgs\image-20230509100336144.png" style="zoom:40%;" /> 

7. 使用`git rebase --continue`完成本次版本的合并（test上现在有master版本，但是master上没有test分支的版本）

   <img src="imgs\image-20230509100539889.png" style="zoom:40%;" /> 

8. 切换到master分支，对test分支做一次`merge`操作（快进）

   <img src="imgs\image-20230509100635738.png" style="zoom:40%;" /> 

9. 合并结果展示

   ![image-20230509100758130](imgs\image-20230509100758130.png) 

### 1.4 合并远程分支的版本

1. lisi修改作业功能，推送到远程版本库

   <img src="imgs\image-20230509101708596.png" style="zoom:50%;" /> 

2. zs在没有更新最新版本的情况下，也修改了作业功能，向远程版本库推送时被拒绝了

   <img src="imgs\image-20230509101855521.png" alt="image-20230509101855521" style="zoom:50%;" /> 

3. zs先拉取远程版本库的版本（这里使用**`git pull --rebase origin master`**），此时出现版本冲突

   <img src="imgs\image-20230509101958829.png" alt="image-20230509101958829" style="zoom:50%;" /> 

4. zs手动解决冲突

5. zs使用`git add`标记解决了冲突

   <img src="imgs\image-20230509102126992.png" alt="image-20230509102126992" style="zoom:50%;" /> 

6. zs使用`git rebase --continue`完成本次版本合并

   

7. zs最后将合并结果推送到远程版本库

   `git push origin master`

## 2. git diff命令 - 对比文件内容差异

1. 对比工作区和暂存区文件差异：`git diff [文件...]`

2. 对比暂存区和版本库文件差异：`git diff --cached [文件...]`

3. 对比工作区和版本库文件差异

   与最新版本对比：`git diff HEAD [文件...]`

   与其它版本对比：`git diff commit [文件...]`

4. 对比版本之间的文件差异

   `git diff commit1 commit2 [文件...]`

## 3. 文件在三大区域穿梭

<img src="imgs\Git文件穿梭.png" style="margin-left: 20px; zoom: 40%" />



---



#  下午(Spring  MVC内容)

1. 什么是Web MVC
2. 什么是Spring MVC
3. Spring MVC的核心组件
4. Spring MVC的执行流程
5. Spring MVC的参数传递方式
6. Spring MVC对静态资源的处理
7. Spring MVC如何处理REST（RestFul）风格的请求
8. Spring MVC容器和Spring容器的关系
9. Spring MVC使用MyBatis
10. Spring AOP
11. Spring的事务
    - Spring事务隔离级别
    - **Spring事务传播机制**
    - Spring事务回滚机制
12. Spring MVC使用commons-fileupload实现文件上传
13. Spring MVC使用hibernate-validator实现后端Java Bean校验
14. Spring MVC的全局异常处理
15. Spring MVC的拦截器
16. 使用**Java Config**方式搭建Spring MVC环境
17. 其它知识点
    - 使用**SLF4J + Logback**做**日志记录**
    - Apache Commons的工具类、HuTool（国内的工具类）

## 1. 什么是Web MVC

- **MVC**是一种设计模式，它将应用程序分为**模型、视图、控制器**三部分

1. 模型：Java Bean

   保存数据状态的Java Bean：实体类等

   处理业务逻辑的Java Bean：Service、Dao

2. 视图：与用户交互界面

3. 控制器：实现了模型和视图的解耦

   接收视图发送请求

   委托模型层处理业务

   使用模型数据渲染页面，响应用户请求

## 2. 什么是Spring MVC

1. Spring MVC是建立在**Servlet API**之上的Web框架，从一开始就包含在Spring框架中；
1. 它的正式名称：**Spring Web MVC**（来自于它在Spring框架中的模块名称），通常被称为**Spring MVC**。

## 3. Spring MVC的核心组件

1. **前端控制器DispatcherServlet**：拦截用户请求，将请求转发给其它组件，并负责页面渲染等，是Spring MVC的核心控制器；
2. **处理器映射器HandlerMapping**：映射请求的URI到**处理器**（根据请求的URI查找处理器）
3. **处理器适配器HandlerAdapter**：执行处理器（调用处理器的方法）
4. **视图解析器ViewResolver**：解析**逻辑视图ModelAndView**，创建**View对象**
5. **处理器**：开发者自己编写业务逻辑
6. **拦截器**、MultipartResolver、国际化组件....

## 4. Spring MVC执行流程

<img src="imgs\Spring MVC执行流程图.png" style="zoom:50%; margin-left: 50px" />

1. **<font color="red">客户端发送请求到DispatcherServlet</font>**

2. **<font color="red">DispatcherServlet请求处理器映射器查找处理器</font>**

3. **<font color="red">处理器映射器找到处理器之后，返回一个处理器执行链对象给DispatcherServlet</font>**

4. **<font color="orange">DispatcherServlet请求处理器适配器执行处理器</font>**

5. **<font color="orange">处理器适配器执行处理器</font>**

6. **<font color="orange">处理器执行完成之后返回一个逻辑视图对象给处理器适配器</font>**

7. **<font color="orange">处理器适配器将逻辑视图返回给DispatcherServlet</font>**

8. **<font color="blue">DispatcherServlet请求视图解析器解析逻辑视图</font>**

9. **<font color="blue">视图解析器解析逻辑视图，返回一个View对象给DispatcherServlet</font>**

10. **<font color="blue">DispatcherServlet使用视图对象和模型数据渲染视图，响应客户端请求</font>**

    <img src="imgs\doDispatch.png" style="zoom:50%" /> 

## 5. 第一个Spring MVC工程

1. 创建Maven项目，导入Maven依赖

2. 配置DispatcherServlet

   有几种配置Servlet的方式：web.xml、@WebServlet、SPI（Java代码）

3. 配置处理器映射器

4. 配置处理器适配器

5. 配置视图解析器

6. 开发处理器，并配置为Spring容器的对象

7. 开发视图

8. 部署工程到Tomcat，测试请求

## 6. 第二个Spring MVC工程

1. 替换处理器映射器：`SimpleUrlHandlerMapping`

2. 替换处理器适配器：`HttpRequestHandlerAdapter`

   <img src="imgs\image-20230509155344660.png" style="zoom:40%;" /> 

## 7. 第三个Spring MVC工程

1. 替换处理器映射器：`ReqeustMappingHandlerMapping`
2. 替换处理器适配器：`ReqeustMappingHandlerAdapter`

- 特点：

  1、必须一起使用

  2、不要求处理器实现任何接口（处理器可以自定义）

  3、使用`@ReqeustMapping`注解的path或value属性值映射URI到处理器的方法

## 8. 第四个Spring MVC工程

## 9. `<mvc:annotation-driven>`

​	由`org.springframework.web.servlet.config.AnnotationDrivenBeanDefinitionParser`解析，向Spring容器中注册如下Bean

1. 处理器**映射器**

   `RequestMappingHandlerMapping`、`BeanNameUrlHandlerMapping`

2. 处理器**适配器**

   `RequestMappingHandlerAdapter`、`HttpRequestHandlerAdapter`、`SimpleControllerHandlerAdapter`

3. 异常**解析器**：`ExceptionHandlerExceptionResolver`

4. Java Bean校验器

5. 一组`HttpMessageConverter`

## 10. 今天练习

1. `BeanNameUrlHandlerMapping` + **`SimpleServletHandlerAdapter`**
2. `SimpleUrlHandlerMapping` + `SimpleControllerHanlerAdapter`



---



# Day04(SpringMVC整合Mybatis)

## 今天内容

1. Spring MVC参数传递方式
2. `@RequestParam`注解
3. `@RequestMapping`注解
4. 解决POST请求中文乱码
5. Spring父容器（Spring Root容器）和Spring MVC容器之间的关系
6. Spring整合MyBatis
7. SLF4J + Logback记录日志

## 1. Spring MVC参数传递 - 接收参数

### 1.1 常用的请求参数类型：HTTP请求头的Content-Type表示

1. form表单、key-value键值对：`x-www-form-urlencoded`
2. JSON：`application/json`
3. 文件：`multipart/form-data`

### 1.2 常用的响应结果类型：HTTP响应头的Content-Type表示

1. html：`text/html`
2. 纯文本、字符串：`text/plain`
3. JSON：`application/json`
4. 图片：`image/jpeg`、`image/gif`....
5. 二进制
6. ....

### 1.3 Spring MVC接收参数的方式

1. Servlet API：HttpServletRequest对象

2. String、基本数据类型及其包装类

3. 对象

4. 数组

5. List

   <img src="imgs\image-20230510112902852.png"  style="zoom:43%;" /> 

   <img src="imgs\image-20230510113000720.png" style="zoom:43%;" /> 

6. Map - 了解

7. JSON

   Spring MVC可以使用**Jackson、GSON、Jsonb**三个框架作为Json的消息转换器

   这里使用Jackson框架作为JSON转换器，需要引入Jackson的jar包

   - jackson-core.jar、jackson-annotations.jar、jackson-databind.jar）

   在Maven项目中，仅需要导入一个**jackson-databind**依赖即可

## 2. @RequestParam注解

- 只能在参数上使用

1. `value属性`：请求参数名称
2. `name属性`：请求参数名称，与value作用一致
3. `required属性`：参数是否为必填项，默认是必填（参数值不能null）
4. `defaultValue属性`：当参数为非必填项时，使用它设置默认值

## 3. @RequestMapping注解

- 可以在类和方法上使用

1. `String[] value属性`：映射URI地址

2. `String[] path属性`：映射URI地址

3. `RequestMethod[] method属性`：设置请求方法

4. `String[] produces属性`：设置响应头的`Content-Type`

5. `String[] consumes属性`：限制请求参数的类型

   ```java
   @PostMapping(value = "/test", consumes = "application/json;charset=utf-8")
   @ResponseBody
   public String test(@RequestBody String str) {
     System.out.println(str);
     return "success";
   }
   ```

## 4. 解决POST请求中文乱码问题

- **Spring Web模块**中提供了`CharacterEncodingFilter`可以解决post请求的中文乱码问题

  ```xml
  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>
      org.springframework.web.filter.CharacterEncodingFilter
    </filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  ```

## 5. Spring容器和Spring MVC容器之间的关系

<img src="imgs\两个容器的关系.png" style="zoom:50%; margin-left: 60px" />

## 6. Spring整合MyBatis

- [MyBatis官网](https://mybatis.org/mybatis-3/zh/)
- [MyBatis-Spring 官网](https://mybatis.org/spring/zh/)

### 6.1 Mybatis、MyBatis-Spring、Spring、JDK版本对应关系

| MyBatis-Spring | MyBatis | Spring Framework | Spring Batch | Java     |
| :------------- | :------ | :--------------- | :----------- | :------- |
| **3.0**        | 3.5+    | 6.0+             | 5.0+         | Java 17+ |
| **2.1**        | 3.5+    | 5.x              | 4.x          | Java 8+  |
| **2.0**        | 3.5+    | 5.x              | 4.x          | Java 8+  |
| **1.3**        | 3.4+    | 3.2.2+           | 2.1+         | Java 6+  |

### 6.2 SqlSessionFactoryBean

​		需要注意的是 `SqlSessionFactoryBean` 实现了 Spring 的 `FactoryBean` 接口（参见 Spring 官方文档：通过工厂 bean 自定义实例化逻辑](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-extension-factorybean) ）。 这意味着由 Spring 最终创建的 bean **并不是** `SqlSessionFactoryBean` 本身，而是工厂类（`SqlSessionFactoryBean`）的 getObject() 方法的返回结果。这种情况下，Spring 将会在应用启动时为你创建 `SqlSessionFactory`，并使用 `sqlSessionFactory` 这个名字存储起来。

### 6.3 Maven依赖

​		**spring-jdbc、mysql、druid、mybatis、mybatis-spring、pagehelper**

## 7. 使用SLF4J + Logback记录日志

1. 为什么要记录日志？

   方便查看线上代码的问题

   记录用户的操作行为，方便审计用户行为

2. 日志可以记录在哪里？

   开发阶段一般输出到控制台

   生产阶段一般输出文件、数据库（不一定是关系型数据库，例如：ELK）

3. 常用的日志框架有哪些？

   门面日志框架（接口规范）：Apache Commons Logging、SLF4J

   具体输出日志的框架：Log4J、JUL(Java Util Logging)、Logback、Log4J2...

4. 配置Slf4J + Logback

   `slf4j-api.jar`、`logback-core.jar`、`logback-classic.jar`

5. 日志级别

   `DEBUG  <  INFO   <   WARN   <   ERROR`



## 今天练习

<img src="imgs\image-20230510181452121.png" alt="image-20230510181452121" style="zoom:50%;" /> 



---



## 代码补充：

### springMVC参数接收类型

### Fromcontroller.java

```java
package com.etoak.controller;

import com.etoak.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 测试接收form表单参数
 */
@Controller
@RequestMapping("/form")
@CrossOrigin
public class FormController {

  /**
   * 使用String类型和基本数据类型接收参数
   */
  @RequestMapping("/simple")
  public String simple(@RequestParam String name,
                       @RequestParam(required = false, defaultValue = "123") int id) {
    System.out.println("name=" + name);
    System.out.println("id=" + id);

    return "hello";
  }

  /**
   * 使用Java Bean接收表单参数
   * 将form参数映射到Java Bean的属性
   */
  @PostMapping("/bean")
  public String bean(Student student) {
    System.out.println(student);
    return "hello";
  }

  @RequestMapping(value = "/array", method = RequestMethod.POST)
  public void array(String[] hobbies, HttpServletResponse response) throws IOException {
    for (String hobby: hobbies) {
      System.out.println(hobby);
    }

    response.setContentType("text/plain;charset=utf-8");
    PrintWriter writer = response.getWriter();
    writer.print("执行成功！");
    writer.flush();
    writer.close();
  }

  /**
   * List接收表单参数
   */
  @RequestMapping(path = "/list", method = RequestMethod.POST,
    produces = "text/plain;charset=utf-8")
  @ResponseBody
  public String testList(@RequestParam List<String> hobbies) {
    hobbies.forEach(x -> System.out.println(x));
    return "执行成功！";
  }

  /**
   * 使用Map接收表单参数
   */
  @RequestMapping(value = "/map", method = {RequestMethod.GET,RequestMethod.POST},
  produces = "text/plain;charset=utf-8")
  @ResponseBody
  public String testMap(@RequestParam Map<String, Object> map) {
    System.out.println(map);
    return "执行成功！";
  }

}
```



### db.properties

```properties
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql:///et2301?serverTimezone=UTC
db.username=root
db.password=etoak
```



### spring-root.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

  <context:component-scan base-package="com.etoak.**.service" />

  <!-- 导入db.properties -->
  <context:property-placeholder location="classpath:db.properties" />

  <!-- 数据源 -->
  <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${db.driver}" />
    <property name="url" value="${db.url}" />
    <property name="username" value="${db.username}" />
    <property name="password" value="${db.password}" />
  </bean>

  <bean id="pageInterceptor" class="com.github.pagehelper.PageInterceptor">
    <property name="properties">
      <props>
        <prop key="helperDialect">mysql</prop>
      </props>
    </property>
  </bean>

  <!-- SqlSessionFactoryBean -->
  <bean class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="typeAliasesPackage" value="com.etoak" />
    <!-- Mapper映射文件位置 -->
    <property name="mapperLocations" value="classpath:mapper/**/*.xml" />
    <!-- plugins -->
    <property name="plugins">
      <array>
        <ref bean="pageInterceptor" />
      </array>
    </property>
  </bean>

  <!-- MapperScannerConfigurer
       扫描指定包中的接口, 为接口创建代理对象
   -->
  <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.etoak.**.mapper" />
  </bean>

</beans>
```



### spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
  <!--
     *: 精确一层包结构
     **: 任意层级的包(0、1、2...)
     com.etoak.controller
     com.etoak.system.user.controller
   -->
  <context:component-scan base-package="com.etoak.**.controller" />

  <!-- 处理器映射器、处理器适配器 -->
  <mvc:annotation-driven></mvc:annotation-driven>

  <!-- 视图解析器 -->
  <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/pages/" />
    <property name="suffix" value=".jsp" />
  </bean>

</beans>
```



### webapp/WEB-INF/web.xml

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>

  <!-- 如果不指定, 父容器配置文件/WEB-INF/applicationContext.xml -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring-root.xml</param-value>
  </context-param>

  <!-- 加载父容器（Root容器） -->
  <listener>
    <listener-class>
      org.springframework.web.context.ContextLoaderListener
    </listener-class>
  </listener>

  <!-- CharacterEncodingFilter -->
  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>
      org.springframework.web.filter.CharacterEncodingFilter
    </filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!-- DispatcherServlet -->
  <servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>
      org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>

```





---



# day05(动态代理、初识AOP)

## REST风格的请求

​	REST这个词是**Roy Thomas Fielding**在2000年的博士论文中提出的。

​	**REST**全称**Representational State Transfer**  **表述性状态转移**；

​	**`资源`**在网络中以某种**`表现形式`**进行**`状态转移`**

1、**资源**

​		**所谓"资源"，就是网络上的一个实体，或者说是网络上的一个具体信息。**它可以是一段文本、一张图片、一首歌曲、一种服务，总之就是一个具体的实在。可以用一个**URI（统一资源定位符）**指向它，每种资源对应一个特定的URI。要获取这个资源，访问它的URI就可以，因此URI就成了每一个资源的地址或独一无二的识别符。

2、**表现层**

​		"资源"是一种信息实体，它可以有多种外在表现形式。**我们把"资源"具体呈现出来的形式，叫做它的"表现层"（Representation）。**

​		比如，文本可以用txt格式表现，也可以用HTML格式、XML格式、JSON格式表现，甚至可以采用二进制格式；图片可以用JPG格式表现，也可以用PNG格式表现。

​	  **URI只代表资源的实体**，不代表它的形式。严格地说，有些网址最后的".html"后缀名是不必要的，因为这个后缀名表示格式，属于"表现层"范畴，而URI应该只代表"资源"的位置。**它的具体表现形式，应该在HTTP请求的头信息中用Accept和响应头的Content-Type字段指定，这两个字段才是对"表现层"的描述。**

3、**状态转移**

​		访问一个网站，就代表了客户端和服务器的一个互动过程。在这个过程中，肯定会涉及到数据和状态的变化。

​		互联网通信协议HTTP协议，是一个无状态协议。这意味着，所有的状态都保存在服务器端。因此，**如果客户端想要操作服务器，必须通过某种手段，让服务器端发生"状态转化"（State Transfer）。而这种转化是建立在表现层之上的，所以就是"表现层状态转化"。**

​		客户端用到的手段，只能是HTTP协议。具体来说，就是HTTP协议里面，四个表示操作方式的动词：**GET、POST、PUT、DELETE**。它们分别对应四种基本操作：**GET用来获取资源，POST用来新建资源（也可以用于更新资源），PUT用来更新资源，DELETE用来删除资源。**

4、总结

1. 使用URI表示资源

2. 使用**响应头的Content-Type**和**请求头Accept**表示资源的表现层（表现形式：html、json、xml、text、jpg）

3. 使用Http请求方法表示动作：

   **GET用来获取资源，**

   **POST用来新建资源（也可以用于更新资源），**

   **PUT用来更新资源，**

   **DELETE用来删除资源。**

5、对比URI

|          | 传统的URI                         | REST风格的URI       |
| -------- | --------------------------------- | ------------------- |
| 查询学生 | `get /student/getStudent?id=1`    | `get /student/1`    |
| 添加学生 | `post /student/add `              | `post /student`     |
| 更新学生 | `put /student/update`             | `put /student/1`    |
| 删除学生 | `delete /student/deleteById?id=1` | `delete /student/1` |



---



## 今天内容

1. REST风格的（RestFul）请求
2. commons-fileupload上传文件
3. Spring MVC处理静态资源
4. Spring MVC后端校验
5. 静态代理
6. 动态代理
7. Spring AOP


## 1. REST风格的（RestFul）请求

- 详细内容参考今天的`REST风格.pdf`

## 2. commons-fileupload上传文件

1. 引入Maven依赖

   ```xml
   <!-- commons-fileupload -->
   <dependency>
     <groupId>commons-fileupload</groupId>
     <artifactId>commons-fileupload</artifactId>
     <version>1.3.3</version>
   </dependency>
   ```

   

2. 配置`CommonsMultipartResolver`（id或name必须是`multipartResolver`）

   

3. 开发上传接口

### 2.1 图片上传接口

1. 请求地址：`http://localhost:8080/upload/image`

2. **请求方法：`post`**

3. 请求参数：`file`

4. **请求参数类型：`multipart/form-data`**

5. 响应结果：

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": "/pics/aaaaaaaaa.jpeg"
   }
   ```

## 3. Spring MVC中访问静态资源 

1. 访问**CLASSPATH下**的静态资源、

   ```xml
   <mvc:resources mapping="/res/**" location="classpath:public/" />
   ```

2. 访问 **<u>本地文件系统</u> 的静态资源**

   ```xml
   <mvc:resources mapping="/pics/**" location="file:d:/upload/et2301/" />
   ```

3. 访问项目**根目录下**的静态资源

   ```xml
   <!-- 
   	 不让DispatcherServlet处理静态资源
        将静态资源交给Servlet容器处理
    -->
   <mvc:default-servlet-handler />
   ```

## 4. Spring MVC后端校验

​		**JSR-303(Java Specification Requests - 303)**是JAVA EE 6中的一项子规范，叫做Bean Validation，**Hibernate Validator**是Bean Validation 的参考实现 。Hibernate Validator 提供了 JSR 303 规范中所有内置约束的实现。

1. 配置maven依赖

   ```xml
   <!-- JSR-303参考实现 hibernate-validator -->
   <dependency>
     <groupId>org.hibernate.validator</groupId>
     <artifactId>hibernate-validator</artifactId>
     <version>6.2.0.Final</version>
   </dependency>
   ```

2. 校验规则

   | 约束                        |                                                              |
   | --------------------------- | ------------------------------------------------------------ |
   | @AssertTrue                 | 用于boolean字段，该字段只能为true                            |
   | @AssertFalse                | 用于boolean字段，该字段的值只能为false                       |
   | @CreditCardNumber           | 对信用卡号进行一个大致的验证                                 |
   | `@DecimalMax`               | `只能小于或等于该值（可以用在String上）`                     |
   | @DecimalMin                 | 只能大于或等于该值（可以用在String上）                       |
   | `@Max`                      | `该字段的值只能小于或等于该值`                               |
   | @Min                        | 该字段的值只能大于或等于该值                                 |
   | @Digits(integer=,fraction=) | 检查是否是一种数字的整数、分数,小数位数的数字                |
   | @Email                      | 检查是否是一个有效的email地址                                |
   | @Future                     | 检查该字段的日期是否是属于将来的日期                         |
   | @Past                       | 检查该字段的日期是**在过去**                                 |
   | `@Length(min=,max=)`        | 只能用于**字符串**，检查字段的长度是否在min和max之间         |
   | @NotNull                    | 不能为null，可以用在除了基本数据类型之外的所有引用类型       |
   | @NotBlank                   | 不能为空，检查时会将空格忽略，只能用在String类型上           |
   | @NotEmpty                   | 不能为空，可用于**String、Collection、Map、数组**            |
   | @Null                       | 检查该字段为空                                               |
   | @Pattern(regex=, flag=)     | **被注释的元素**必须符合指定的**正则表达式**，只能用在**String类型**上 |
   | @Range(min=,max=,message=)  | 被注释的元素必须在合适的范围内                               |
   | @Size(min=, max=)           | 检查该字段的size是否在min和max之间，可以是字符串、数组、集合、Map等 |
   | @URL(protocol=,host,port)   | 检查是否是一个有效的URL，如果提供了**protocol，host**等，则该URL还需满足提供的条件 |

## 5. 静态代理

1. 继承

   问题：需要为每个目标对象创建一个**代理对象**

2. 接口

   1、定义一个接口

   2、目标对象要实现这个接口

   3、代理对象也实现相同的接口，并且需要持有一个接口类型的目标对象（动态传入的）



## 6. 动态代理

1. **JDK动态代理**

   要求被代理的对象<u>必须</u>有**接口实现**

   **缺点：不能代理没有接口实现的普通对象**

   Proxy：创建代理对象

   InvocationHandler接口：代理对象要执行的业务逻辑

2. **CGLIB动态代理**

   既可以代理**有接口**实现的类型，也可以代理**没有接口**实现的类型

   代理方式：直接采用**继承**目标对象的方法创建代理对象

   

## 7. Spring AOP - Aspect Oriented Programming

- **核心概念**

  JointPoint：连接点，方法（ In Spring AOP, a join point always represents a method execution.）

  Ponitcut：切入点，连接点的集合（约束一组连接点）

  Advice：通知、增强，在连接点上执行的动作

  Aspect：一组模块化的操作（在切入点约束的连接点上执行Advice）

- **通知类型**

  before advice：前置通知，在连接点执行前执行

  after returning advice：后置通知，在连接点执行成功（执行完成）之后执行，例如提交事务

  after throwing advice：后置通知，在连接点执行异常之后执行，例如回滚事务

  after（finally）advice：后置通知，不管连接点执行是否成功，都会执行。例如关闭连接等

  around advice：环绕通知，通知中功能最强大的通知，**它可以控制连接点的执行**

  ​         在权限检查时，如果没有权限，那就不执行连接点

## 今天的练习

- 昨天的基础上增加后端校验





---

## 代码补充：



## REST代码风格

### UserController.java(以添加用户为例)

```java
package com.etoak.controller;

import com.etoak.entity.User;
import com.etoak.service.UserService;
import com.etoak.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注解@RestController = @Controller + @ResponseBody
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserService userService;

  @GetMapping("/getUser")
  public User getUser(long id) {
    log.info("id is {}", id);
    return userService.getUser(id);
  }

  /**
   * GET /user/{id}
   * http://localhost:8080/user/1
   */
  @GetMapping("/{id}")
  public User get(@PathVariable long id) {
    return userService.getUser(id);
  }

  /**
   * PUT /user/1
   * 参数类型: json
   */
  @PutMapping("/{id}")
  public String update(@PathVariable long id, @RequestBody User user) {
    log.info("user id is {}, user param {}", id, user);
    return "success";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable long id) {
    log.info("delete user id is {}", id);
    return "success";
  }

  /**
   * post /user
   */
  @PostMapping(produces = "text/plain;charset=utf-8")
  public String add(@RequestBody User user) {
    log.info("user param {}", user);

    try {
      ValidatorUtil.validate(user);
    } catch (RuntimeException e) {
      log.warn(e.getMessage(), e);
      return e.getMessage();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return "系统异常";
    }

    userService.addUser(user);
    return "success";
  }
}
```





---





## 动态代理过程：

### proxy-dynamic

### 父模块pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.etoak.et2301.proxy</groupId>
  <artifactId>proxy-dynamic</artifactId>
  <packaging>pom</packaging>
  <version>1.0-SNAPSHOT</version>

  <modules>
    <module>jdk-proxy</module>
    <module>cglib-proxy</module>
  </modules>

  <!-- 依赖管理器
       管理依赖、声明依赖, 但是不导入依赖
       需要由子模块自己决定要导入哪些依赖
   -->
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>cglib</groupId>
        <artifactId>cglib</artifactId>
        <version>3.3.0</version>
      </dependency>
    </dependencies>
  </dependencyManagement>
</project>
```



### 子模块pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <artifactId>proxy-dynamic</artifactId>
    <groupId>com.etoak.et2301.proxy</groupId>
    <version>1.0-SNAPSHOT</version>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <artifactId>cglib-proxy</artifactId>

  <dependencies>
    <dependency>
      <groupId>cglib</groupId>
      <artifactId>cglib</artifactId>
    </dependency>
  </dependencies>
</project>
```



### Teacher.java

```java
package com.etoak;

public class Teacher {

  public void teach() {
    System.out.println("老师讲课！");
  }
}
```



### MyInterceptor.java

```java
package com.etoak;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyInterceptor implements MethodInterceptor {

  /**
   * 代理对象的执行逻辑
   *
   * @param obj     代理对象
   * @param method  目标方法
   * @param args    方法参数
   * @param proxy   执行父类方法的对象
   */
  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

    System.out.println("上课前检查手机！");

    // super.teach() |  super.method // 执行代理对象父类的方法
    Object result = proxy.invokeSuper(obj, args);

    System.out.println("放学后帮忙解决问题！");

    return result;
  }

}

```



### Test.java

```java
package com.etoak;

import net.sf.cglib.proxy.Enhancer;

public class Test2 {

  public static void main(String[] args) {
    Enhancer enhancer = new Enhancer();

    // 设置代理对象的父类
    enhancer.setSuperclass(Teacher.class);

    // 代理对象的执行逻辑
    enhancer.setCallback(new MyInterceptor());

    // 创建代理对象
    Teacher proxy = (Teacher) enhancer.create();
    proxy.teach();
  }
}
```



---



## SpringMVC+Mybatis 文件上传(图片)

### pom.xml(工具包)

```xml
    <!-- commons-fileupload -->
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.3</version>
    </dependency>

    <!-- 工具包 -->
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.12.0</version>
    </dependency>

    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-collections4</artifactId>
      <version>4.4</version>
    </dependency>

    <dependency>
      <groupId>cn.hutool</groupId>
      <artifactId>hutool-all</artifactId>
      <version>5.8.0</version>
    </dependency>
```

### spring-root.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

  <context:component-scan base-package="com.etoak.**.service" />

  <!-- 导入db.properties -->
  <context:property-placeholder location="classpath:db.properties" />

  <!-- 数据源 -->
  <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${db.driver}" />
    <property name="url" value="${db.url}" />
    <property name="username" value="${db.username}" />
    <property name="password" value="${db.password}" />
  </bean>

  <bean id="pageInterceptor" class="com.github.pagehelper.PageInterceptor">
    <property name="properties">
      <props>
        <prop key="helperDialect">mysql</prop>
      </props>
    </property>
  </bean>

  <!-- SqlSessionFactoryBean -->
  <bean class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="typeAliasesPackage" value="com.etoak" />
    <!-- Mapper映射文件位置 -->
    <property name="mapperLocations" value="classpath:mapper/**/*.xml" />
    <!-- plugins -->
    <property name="plugins">
      <array>
        <ref bean="pageInterceptor" />
      </array>
    </property>
  </bean>

  <!-- MapperScannerConfigurer
       扫描指定包中的接口, 为接口创建代理对象
   -->
  <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.etoak.**.mapper" />
  </bean>

</beans>
```



### spring-mvc.xml(配置请求地址和文件大小)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
  <!--
     *: 精确一层包结构
     **: 任意层级的包(0、1、2...)
     com.etoak.controller
     com.etoak.system.user.controller
   -->
  <context:component-scan base-package="com.etoak.**.controller" />

  <!-- 处理器映射器、处理器适配器 -->
  <mvc:annotation-driven></mvc:annotation-driven>

  <!--
     不让DispatcherServlet处理项目根目录下静态资源
     将静态资源交给Servlet容器处理
  -->
  <mvc:default-servlet-handler default-servlet-name="default"/>

  <!-- @RequestMapping("/res/**")
       localhost:8080/res/css/test.css => src/main/resources/public/css/test.css
   -->
  <mvc:resources mapping="/res/**" location="classpath:public/" />

  <!--
     浏览器访问 http://localhost:8080/pics/6455518c209840dbab5c4a9a429a0233.jpg
     就是访问 d:/upload/et2301/6455518c209840dbab5c4a9a429a0233.jpg
  -->
  <mvc:resources mapping="/pics/**" location="file:d:/upload/et2301/" />

  <!-- 视图解析器 -->
  <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/pages/" />
    <property name="suffix" value=".jsp" />
  </bean>

  <!-- CommonsMultipartResolver. id的值必须是multipartResolver -->
  <bean id="multipartResolver"
    class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!-- 最大上传限制1MB -->
    <property name="maxUploadSize" value="1048576" />
  </bean>

</beans>
```



### UploadController.java文件上传

```java
package com.etoak.controller;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController {

  /** 上传目录 */
  public static final String UPLOAD_DIR = "d:/upload/et2301";

  /** 图片类型 */
  public static final List<String> TYPE_LIST = Arrays.asList("image/jpeg", "image/png");

  /**
   * 图片上传接口
   */
  @PostMapping("/image")
  public Object uploadImage(MultipartFile file) throws IOException {
    // 返回结果
    Map<String, Object> resultMap = new HashMap<>();

    /* 1、验证文件是否为空 */
    if (ObjectUtils.isEmpty(file) || file.isEmpty()) {
      resultMap.put("code", 500);
      resultMap.put("msg", "文件不能为空！");
      return resultMap;
    }

    /* 2、验证图片类型 */
    String contentType = file.getContentType();
    if (!TYPE_LIST.contains(contentType)) {
      resultMap.put("code", 500);
      resultMap.put("msg", "仅支持jpg和png格式");
      return resultMap;
    }

    /* 3、修改图片名称 */
    String originalFilename = file.getOriginalFilename();
    String extension = FilenameUtils.getExtension(originalFilename);//图片的扩展名
    String filename = IdUtil.simpleUUID() + "." + extension;

    /* 4、创建上传目录 */
    File uploadDir = new File(UPLOAD_DIR);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }

    // 目标文件
    File destFile = new File(uploadDir, filename);

    // 5、上传图片
    file.transferTo(destFile);

    // 6、返回结果
    resultMap.put("code", 200);
    resultMap.put("msg", "success");
    resultMap.put("data", "/pics/" + filename);
    return resultMap;
  }
}
```





---



# day06(Spring事务)



## 今天内容

1. Spring AOP
1. Spring AOP例子
1. Spring事务
1. Spring MVC异常处理
1. Spring MVC拦截器

## 1. Spring AOP（Aspect Oriented Programming）

- AOP将应用程序分为核心业务和非核心的公共服务，AOP的关注点是系统中的非核心的公共服务；
- AOP可以通过**预编译**或者**运行期动态代理**的方式，为横跨多个对象的业务逻辑添加统一的功能（`横切关注点` - Cross Cutting Concern：事务、日志记录、权限检查、异常处理等）
- 使用AOP可以实现在不修改核心业务源代码的情况下为核心业务添加统一的功能，实现了核心业务和非核心的公共服务之间的<u>解耦</u>，提高了<u>代码的重用性</u>；

### 1.1 AOP的概念

1. Join point：连接点，一个方法的执行（调用某个方法就是执行某个连接点）

   In Spring AOP, a join point always represents a method execution.

2. Pointcut：切入点，连接点集合（用于约束连接点）

3. Advice：在特定的连接点上执行的动作

4. Aspect：切面，在切入点约束的连接上执行的一系列的动作（一个模块化了的操作）


### 1.2 Advice类型

1. Before Advice：在连接点执行前执行
2. After returning advice：连接点执行成功之后执行
3. After Throwing advice：连接点抛出异常之后执行
4. After advice(finally)：不论连接点是否执行成功，都会执行
5. Around advice：可以控制连接点的执行（例如权限控制，当没有权限时，可以控制不执行连接点）

## 2. AOP例子 - 使用AOP实现增加用户访问量

1. 引入Maven依赖：spring-aspects

2. 在spring-root.xml配置：`<aop:aspectj-autoproxy proxy-target-class="true" />`

3. UserMapper接口定义更新方法

4. 编写VisitAspect

   Pointcut => getUser

   before：调用userMapper中的更新访问量的方法

5. 启动tomcat、调用/user/1

## 3. Spring 事务

## 4. Spring MVC全局异常处理

1. 在当前Controller使用**@ExcepotionHandler**注解来标记一个方法，这个方法就能处理当前Controller的方法抛出的异常

   缺点：只能当前Controller的异常

2. 实现HandlerExceptionResolver

3. `@ControllerAdvice`、`@RestConttrollerAdvice`

## 5. 拦截器

- 拦截器跟Servlet中学习过滤器类似，都能实现权限检查、日志记录等，是AOP的体现

- 反射 + 动态代理

- 创建拦截器

- 配置拦截器

- 执行顺序

  ```
  com.etoak.interceptor.FirstInterceptor- preHandle
   Controller执行了！
  com.etoak.interceptor.FirstInterceptor- postHandle
  =========View========
  com.etoak.interceptor.FirstInterceptor- afterCompletion
  ```

## AOP练习 - 日志记录

1. 创建表：t_log

   ```sql
   CREATE TABLE `t_log` (
     `id` int NOT NULL AUTO_INCREMENT,
     `content` varchar(128)  NOT NULL,
     `create_time` datetime NOT NULL,
     PRIMARY KEY (`id`)
   );
   ```

2. 创建实体类：`Log`

3. 创建LogMapper接口：向t_log表插入数据

4. 引入Maven依赖：`spring-aspects`

5. 在spring-root.xml中增加：`<aop:aspectj-autoproxy proxy-target-class="true" />`

6. 编写通知：LogAspect

   切入点：`"execution(* com.etoak.service..*.*(..))"`

   Before advice：

   1、获取方法名

   2、创建Log对象

   3、使用LogMapper插入Log对象

7. 调用rest接口，测试结果



---





---

# Spring事务

## 1. 什么是事务

​		事务由一条或多条操作数据库的SQL语句组成的一个不可分割的工作单元，这些操作要么全部执行成功，要么全部失败；

### 1.1. 事务的特性

1. **原子性（Atomicity）**

   事务中不可分割的一个单元，事务中的全部操作要么全部成功，要么全部失败；

2. **一致性（Consistency）**

   表示事务完成时，所有的数据必须保持一致； 

3. **隔离性（Isolation）**

   一个事务的执行不受其他事务的影响，也就是一个事务的内部操作和使用的数据对并发的其它事务是隔离的，并发执行的各个事务之间不能相互影响；

4. **持久性（Durability）**

   也称为永久性，指一个事务一旦提交，对数据库中数据的修改应该是永久性的；

### 1.2. 事务的隔离级别

1. **为什么要有事务的隔离？**

   ​	数据库中的数据有可能同时<u>被多个用户访问</u>，当多个用户操作相同数据的时候，可能会出现**并发问题**，下面的几个并发问题是事务的隔离出现的原因：

   1. **脏读**

   2. **幻读**

   3. **不可重复读**

   

2. **隔离级别有哪些**

   1. **未提交读（Read Uncommitted）**

      ```
      该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。
      该级别不能防止脏读、不可重复读和幻读，因此很少使用该隔离级别。
      ```

   2. **已提交读（Read Commited）**

      ```
      该隔离级别表示一个事务只能读取另一个事务已经提交的数据。
      该级别可以防止脏读，这也是大多数情况下的推荐值。
      ```

   3. **可重复读**

      ```
      该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。
      该级别可以防止不可重复读。
      ```

   4. **串行化（Serializable ）**

      ```
      所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰；
      也就是说，该级别可以防止脏读、不可重复读以及幻读。
      但是这将严重影响程序的性能。通常情况下也不会用到该级别。
      ```

      **总结**

      | 隔离级别         | 解决脏读 | 解决不可重复读 | 解决幻读 |
      | ---------------- | -------- | -------------- | -------- |
      | Read Uncommitted | 不会     | 不会           | 不会     |
      | Read Commited    | 会       | 不会           | 不会     |
      | Repeatable read  | 会       | 会             | 不会     |
      | Serializable     | 会       | 会             | 会       |


## 2. Spring事务

​		Spring提供了编程式事务和AOP声明式事务；

​		Spring的编程式事务在实际开发中很少使用；

​		这里主要讲声明式事务的两种使用方式；

### 2.1 Spring事务的核心API

![4.spring事务抽象接口](imgs\4.spring事务抽象接口.jpg)

### 2.1.1 PlatformTransactionManager - 事务管理器

​		Spring将事务管理交给了事务管理器`PlatformTransactionManager`，Spring为各个平台提供了相应的事务管理器，比如为JDBC提供的`DataSourceTransactionManager`。

```java
public interface PlatformTransactionManager extends TransactionManager {

	/** 由TransactionDefinition得到事务状态对象(TransactionStatus) */
	TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;

	/** 提交事务 */
	void commit(TransactionStatus status) throws TransactionException;

	/** 回滚事务 */
	void rollback(TransactionStatus status) throws TransactionException;
}
```

### 2.1.2 TransactionDefinition - 事务的定义

​	TransactionDefinition描述事务以下几个方面内容

1. 事务的隔离级别

2. 事务的传播行为、传播机制

3. 事务的超时时间

4. 事务是否是只读状态

   ```java
   public interface TransactionDefinition {
       /** 传播行为 */
       default int getPropagationBehavior() {
           return PROPAGATION_REQUIRED;
       }
     
       /** 隔离级别 */
       default int getIsolationLevel() {
           return ISOLATION_DEFAULT; // 根据数据自动选择隔离级别
       }
     
       /** 超时时间 */
       default int getTimeout() {
           return TIMEOUT_DEFAULT;
       }
     
       /** 事务的只读状态 */
       default boolean isReadOnly() {
           return false;
       }
   
       @Nullable
       default String getName() {
           return null;
       }
   
       static TransactionDefinition withDefaults() {
           return StaticTransactionDefinition.INSTANCE;
       }
   }
   ```

### 2.2 Spring的声明式事务配置 - xml配置

1. Maven依赖：

   Spring事务需要两个**spring-jdbc、spring-tx**两个模块，另外还有**spring-aspects**

   在Spring MVC项目中增加如下依赖

   ```xml
   <!-- spring-jdbc传递依赖了spring-tx -->
   <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-jdbc</artifactId>
     <version>5.3.19</version>
   </dependency>
   <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-aspects</artifactId>
     <version>5.3.19</version>
   </dependency>
   ```

2. Spring事务配置

   ```xml
   <!-- 配置数据源 -->
   <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
   	<property name="driverClassName" value="${jdbc.driver}" />
   	<property name="url" value="${jdbc.url}" />
   	<property name="username" value="${jdbc.username}" />
   	<property name="password" value="${jdbc.password}" />
   </bean>
   
   <!-- 配置事务管理器 -->
   <bean id="txManager" 
     class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     <property name="dataSource" ref="dataSource" />
   </bean>
   
   <!-- 事务通知（增强） -->
   <tx:advice id="txAdvice" transaction-manager="txManager">
   	<tx:attributes>
      <!-- 对哪些方法执行事务，配置事务定义信息（隔离级别、传播行为、是否只读、超时时间、回滚机制）-->
   	<!-- 1. name属性: 为哪些方法添加事务 -->
   	<!-- 2. isolation属性: 使用什么隔离级别，这里有5个隔离级别 -->
   	<!-- 3. timeout属性：-1：表示永不超时；3：3秒钟超时 -->
   	<!-- 4. read-only属性 -->
   	<!-- 5. rollback-for属性：指定对哪些异常起作用 -->
   	<!-- 6. no-rollback-for属性：指定对哪些异常不起作用 -->
   	<!-- 7. propagation属性:配置事务的传播行为 -->
   		<tx:method name="add*" 
                  isolation="DEFAULT" 
                  propagation="REQUIRED" />
   		<tx:method name="insert*" 
                  isolation="DEFAULT" 
                  propagation="REQUIRED"
                  rollback-for="Throwable" 
                  timeout="5"/>
   		<tx:method name="delete*" isolation="DEFAULT" 
                  propagation="REQUIRED" />
   		<tx:method name="query*" isolation="READ_UNCOMMITTED" 
                  read-only="true" />
   	</tx:attributes>
   </tx:advice>
   
   <!-- aop配置 -->
   <aop:config>
   	<!-- 第一个*: 任意方法返回类型 -->
   	<!-- 第二个*: 任意类名 -->
   	<!-- 第三个*: 任意方法名称 -->
   	<!-- 第一个..: 表示com.etoak.service包及其任意层级子包 -->
   	<!-- 第二个..: 任意方法参数 -->
   	<aop:pointcut id="txPoint" 
             expression="execution(* com.etoak.service..*.*(..))" />
   	<aop:advisor advice-ref="txAdvice" pointcut-ref="txPoint" />
   </aop:config>
   ```

### 2.3 Spring的声明式事务配置 - 注解配置

#### 2.3.1 Spring注解事务配置

```xml
<!-- 配置数据源 -->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
	<property name="driverClassName" value="${jdbc.driver}" />
	<property name="url" value="${jdbc.url}" />
	<property name="username" value="${jdbc.username}" />
	<property name="password" value="${jdbc.password}" />
</bean>

<!-- 配置事务管理器 -->
<bean id="txManager" 
  class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource" />
</bean>

<!-- 开启注解事务 -->
<tx:annotation-driven transaction-manager="txManager" />
```

#### 2.3.2 @Transactional注解属性说明

1. isolation => Isolation(5种) - 隔离级别 
2. timeout => int: 秒为单位，-1表示不超时；5:表示5秒超时
3. readOnly => boolean 用于加速查询效率
4. rollbackFor => Class[] 表示哪些异常需要回滚
5. rollbackForClassName => String[] 填写要回滚的异常的全限定类名称
6. noRollbackFor => Class[] 表示哪些异常不需要回滚
7. noRollbackForClassName => String[] 填写不需要回滚的异常的全限定类名称
8. transactionManager => String 指定事务管理器bean的id
9. propagation => Propagation 事务传播行为

### 2.4 Spring事务的隔离级别

TransactionDefinition接口中定义了五个表示隔离级别的常量：

1. **`DEFAULT`**：默认使用数据库的隔离级别

2. **READ_UNCOMMITTED**

3. **READ_COMMITTED**

4. **REPEATABLE_READ ** 

5. **SERIALIZABLE**

**注意**：这里主要说一下DEFAULT配置，这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是ISOLATION_READ_COMMITTED。

### 2.5 Spring事务的传播行为、传播特性、传播机制

​		Spring事务的传播行为是指`多个事务`同时存在时，Spring如何处理事务，它们是否需要在同一个事务中运行；

一个有事务的方法被另外有事务的方法调用时，这个事务应该如何运行；

```
methodA() { // 有事务
  update;
  ServiceB.methodB(); // 又调用了另一个有事务的方法
  异常;
}

methodB() { // 有事务
  update;
}
```

#### 2.5.1 Spring的支持七种 _传播特性_

1. REQUIRED：表示如果当前存在一个事务，则加入该事务，否则将新建一个事务；**这个是Spring事务默认传播行为**

2. REQUIRES_NEW：表示不管是否存在事务，都创建一个新的事务，把原来的事务挂起，新的事务执行完毕，继续执行老的事务；

3. SUPPORTS：表示如果当前存在事务，就加入该事务；如果当前没有事务，那就不使用事务；

4. NOT_SUPPORTED：表示不使用事务；如果当前存在事务，就把当前事务暂停，以非事务方式执行；

5. MANDATORY：表示必须在一个已有的事务中执行，如果当前没有事务，则抛出异常；

6. NEVER：表示以**非事务方式**执行，如果当前存在事务，则抛出异常；

7. NESTED：这个是**嵌套事务**；
   如果当前存在事务，则在嵌套事务内执行；
   如果当前不存在事务，则创建一个新的事务；
   嵌套事务使用数据库中的保存点来实现，**即嵌套事务回滚不影响外部事务**，但外部事务回滚将导致嵌套事务回滚；

### 2.6 Spring事务的回滚规则

1. Spring事务默认只对`运行时异常[非检查型]`起作用（也就是出现运行时异常时，回滚数据）；
2. Spring事务默认对`非运行时异常[检查型异常、编译期异常]`不起作用；
3. 可以使用`rollbackFor`指定Spring事务对哪些异常起作用；
4. 可以使用`noRollbackFor`指定Spring事务对哪些异常不起作用；



---



## 代码补充：

## 面向切面编程AOP

### pom.xml

```xml
    <!-- JSR-303实现: hibernate-validator -->
    <dependency>
      <groupId>org.hibernate.validator</groupId>
      <artifactId>hibernate-validator</artifactId>
      <version>6.2.0.Final</version>
    </dependency>

    <!-- spring-aspects -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.3.21</version>
    </dependency>
```

### spring-root.xml(第二版注解版)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
  
  <!--把aop连接点所在的类包加入扫描-->
  <context:component-scan base-package="com.etoak.**.service,com.etoak.**.aspect" />

  <!-- 导入db.properties -->
  <context:property-placeholder location="classpath:db.properties" />

  <!-- 数据源 -->
  <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${db.driver}" />
    <property name="url" value="${db.url}" />
    <property name="username" value="${db.username}" />
    <property name="password" value="${db.password}" />
  </bean>

  <bean id="pageInterceptor" class="com.github.pagehelper.PageInterceptor">
    <property name="properties">
      <props>
        <prop key="helperDialect">mysql</prop>
      </props>
    </property>
  </bean>

  <!-- SqlSessionFactoryBean -->
  <bean class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="typeAliasesPackage" value="com.etoak" />
    <!-- Mapper映射文件位置 -->
    <property name="mapperLocations" value="classpath:mapper/**/*.xml" />
    <!-- plugins -->
    <property name="plugins">
      <array>
        <ref bean="pageInterceptor" />
      </array>
    </property>
  </bean>

  <!-- MapperScannerConfigurer
       扫描指定包中的接口, 为接口创建代理对象
   -->
  <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.etoak.**.mapper" />
  </bean>

  <aop:aspectj-autoproxy proxy-target-class="true" />

  <!-- 事务管理器 -->
  <bean id="transactionManager"
    class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <constructor-arg name="dataSource" ref="dataSource" />
  </bean>

  <!-- 注解事务 -->
  <tx:annotation-driven transaction-manager="transactionManager" />

</beans>
```



### spring-root-xml.xml(第一版xml版)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">

  <context:component-scan base-package="com.etoak.**.service,com.etoak.**.aspect" />

  <!-- 导入db.properties -->
  <context:property-placeholder location="classpath:db.properties" />

  <!-- 数据源 -->
  <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${db.driver}" />
    <property name="url" value="${db.url}" />
    <property name="username" value="${db.username}" />
    <property name="password" value="${db.password}" />
  </bean>

  <bean id="pageInterceptor" class="com.github.pagehelper.PageInterceptor">
    <property name="properties">
      <props>
        <prop key="helperDialect">mysql</prop>
      </props>
    </property>
  </bean>

  <!-- SqlSessionFactoryBean -->
  <bean class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="typeAliasesPackage" value="com.etoak" />
    <!-- Mapper映射文件位置 -->
    <property name="mapperLocations" value="classpath:mapper/**/*.xml" />
    <!-- plugins -->
    <property name="plugins">
      <array>
        <ref bean="pageInterceptor" />
      </array>
    </property>
  </bean>

  <!-- MapperScannerConfigurer
       扫描指定包中的接口, 为接口创建代理对象
   -->
  <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.etoak.**.mapper" />
  </bean>

  <aop:aspectj-autoproxy proxy-target-class="true" />

  <!-- 事务管理器 -->
  <bean id="transactionManager"
    class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <constructor-arg name="dataSource" ref="dataSource" />
  </bean>

  <tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
      <tx:method name="add*" />
      <tx:method name="save*" />
      <tx:method name="update*" />
      <tx:method name="modify*" />
      <tx:method name="del*" />
      <tx:method name="transfer" propagation="REQUIRED" isolation="REPEATABLE_READ" rollback-for="Throwable" />
      <tx:method name="increase" propagation="REQUIRED" isolation="REPEATABLE_READ" rollback-for="SQLException" />
      <tx:method name="decrease" propagation="REQUIRED" isolation="REPEATABLE_READ" rollback-for="Exception" />
    </tx:attributes>
  </tx:advice>

  <aop:config>
    <aop:pointcut id="txPointcut" expression="execution(* com.etoak.service..*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut" />
  </aop:config>

</beans>
```



### spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
  <!--
     *: 精确一层包结构
     **: 任意层级的包(0、1、2...)
     com.etoak.controller
     com.etoak.system.user.controller
   -->
  <context:component-scan base-package="com.etoak.**.controller" />

  <!-- 处理器映射器、处理器适配器 -->
  <mvc:annotation-driven></mvc:annotation-driven>

  <!--
     不让DispatcherServlet处理项目根目录下静态资源
     将静态资源交给Servlet容器处理
  -->
  <mvc:default-servlet-handler default-servlet-name="default"/>

  <!-- @RequestMapping("/res/**")
       localhost:8080/res/css/test.css => src/main/resources/public/css/test.css
   -->
  <mvc:resources mapping="/res/**" location="classpath:public/" />

  <!--
     浏览器访问 http://localhost:8080/pics/6455518c209840dbab5c4a9a429a0233.jpg
     就是访问 d:/upload/et2301/6455518c209840dbab5c4a9a429a0233.jpg
  -->
  <mvc:resources mapping="/pics/**" location="file:d:/upload/et2301/" />

  <!-- 视图解析器 -->
  <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/pages/" />
    <property name="suffix" value=".jsp" />
  </bean>

  <!-- CommonsMultipartResolver. id的值必须是multipartResolver -->
  <bean id="multipartResolver"
    class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!-- 最大上传限制1MB -->
    <property name="maxUploadSize" value="1048576" />
  </bean>

  <mvc:interceptors>
    <mvc:interceptor>
      <mvc:mapping path="/**"/>
      <mvc:exclude-mapping path="/pics/**" />
      <mvc:exclude-mapping path="/login/**" />
      <bean class="com.etoak.interceptor.FirstInterceptor" />
    </mvc:interceptor>
  </mvc:interceptors>

</beans>
```



### AOP切入点VisitAspect.java

```java
package com.etoak.aspect;

import com.etoak.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Aspect
@Slf4j // private static final Logger log = LoggerFactory.getLogger(VisitAspect.class);
public class VisitAspect {

  @Autowired
  UserMapper userMapper;

  @Pointcut("execution(* com.etoak.service.impl.UserServiceImpl.getUser(long))")
  public void visit() {

  }

  @Before("visit()")
  public void updateVisit(JoinPoint joinPoint) {
    // 获取方法参数列表
    Object[] args = joinPoint.getArgs();

    // 获取id参数的值
    long id = (long) args[0];

    log.info("update visit, user id is {}", id);

    userMapper.updateVisit(id);
  }
}
```

### 全局异常处理器GlobalExceptionHandler.java

```java
package com.etoak.controller.advice;

import cn.hutool.core.map.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(RuntimeException.class)
  public Object handle(RuntimeException e) {
    log.error(e.getMessage(), e);
    return MapUtil.builder()
      .put("code", 500)
      .put("msg", e.getMessage())
      .build();
  }

}
```

### MyExceptionResolver.java

```java
package com.etoak.controller.resolver;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 可以处理所有Controller方法抛出的异常
 * 1、方法返回值是ModelAndView, 需要手动自定义返回数据
 * 2、手动判断异常类型
 */
// @Component
@Slf4j
public class MyExceptionResolver implements HandlerExceptionResolver {

  @Override
  public ModelAndView resolveException(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Object handler,
                                       Exception e) {
    log.error(e.getMessage(), e);

    response.setContentType("application/json;charset=utf-8");
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("code", 600);

    // 判断异常类型
    if (e instanceof RuntimeException) {
      resultMap.put("msg", e.getMessage());
    } else if (e instanceof SQLException) {
      resultMap.put("msg", "sql异常！");
    } else {
      resultMap.put("msg", "系统异常！");
    }

    String json = JSONUtil.toJsonStr(resultMap);
    try {
      response.getWriter().print(json);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return null;
  }
}
```

### UserController.java

```java
package com.etoak.controller;

import cn.hutool.core.map.MapUtil;
import com.etoak.entity.User;
import com.etoak.service.UserService;
import com.etoak.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注解@RestController = @Controller + @ResponseBody
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserService userService;

  @GetMapping("/getUser")
  public User getUser(long id) {
    log.info("id is {}", id);
    return userService.getUser(id);
  }

  /**
   * GET /user/{id}
   * http://localhost:8080/user/1
   */
  @GetMapping("/{id}")
  public User get(@PathVariable long id) {
    return userService.getUser(id);
  }

  /**
   * PUT /user/1
   * 参数类型: json
   */
  @PutMapping("/{id}")
  public String update(@PathVariable long id, @RequestBody User user) {
    log.info("user id is {}, user param {}", id, user);
    return "success";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable long id) {
    log.info("delete user id is {}", id);
    return "success";
  }

  /**
   * post /user
   */
  @PostMapping(produces = "text/plain;charset=utf-8")
  public String add(@RequestBody User user) {
    log.info("user param {}", user);

    ValidatorUtil.validate(user);

    userService.addUser(user);
    return "success";
  }


//
//  @ExceptionHandler(Exception.class)
//  public Object handle(Exception e) {
//    log.error(e.getMessage(), e);
//    return MapUtil.builder()
//      .put("code", 500)
//      .put("msg", "系统异常！")
//      .build();
//  }
}
```



---



## 转账_事务

### UserService.java

```java
package com.etoak.service;

import com.etoak.entity.User;

public interface UserService {

  /**
   * 查询用户详情
   */
  User getUser(long id);

  /**
   * 添加用户
   *
   * @param user
   * @return
   */
  boolean addUser(User user);

  /**
   * 模拟转入
   */
  void increase(long id, int money);

  /**
   * 模拟转出
   */
  void decrease(long id, int money);

}
```



### UserServiceImpl.java

```java
package com.etoak.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.etoak.entity.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public User getUser(long id) {
    return userMapper.selectById(id);
  }

  @Override
  public boolean addUser(User user) {
    user.setPassword(MD5.create().digestHex("123456"));
    return userMapper.insert(user) > 0;
  }

  @Transactional(propagation = Propagation.REQUIRED,
    isolation = Isolation.DEFAULT,
    rollbackFor = Exception.class)
  @Override
  public void increase(long id, int money) {
    userMapper.updateMoney(id, money);
  }

  @Transactional(propagation = Propagation.REQUIRED,
    isolation = Isolation.DEFAULT,
    rollbackFor = Exception.class)
  @Override
  public void decrease(long id, int money) {
    userMapper.updateMoney(id, -money);
    // int i = 1 / 0;
  }
}
```



---

---

---



# day07(Spring Boot)

## 上午内容springMVC

### 使用拦截器验证登录

​	前后端不分离的项目，可以使用HttpSession保存登录状态

1. 登录页面、跳转到登录页面的请求
2. 登录接口，验证用户名和密码
3. 拦截器验证登录

---

#### 配置拦截器interceptor

#### LoginInterceptor.java

```java
package com.etoak.interceptor;

import com.etoak.entity.User;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();

    // 从当前的Session中获取User对象
    User user = (User) session.getAttribute("user");
    if (ObjectUtils.isEmpty(user)) {
      String contextPath = request.getContextPath();
      response.sendRedirect(contextPath + "/toLogin");
      return false;
    }

    return true;
  }
}
```

#### UserService.java

```java
  /**
   * 登录业务
   *
   * @param user
   * @return
   */
  User login(User user);
```

#### UserServiceImpl.java

```java
  @Override
  public User login(User user) {
    user.setPassword(MD5.create().digestHex(user.getPassword()));

    return userMapper.getUser(user);
  }
```

#### login.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
  <title>登录</title>
</head>
<body>

  <p>${error}</p>

  <!-- http://localhost:8080/contextPath -->
  <form action="${pageContext.request.contextPath}/login" method="post">
    用户名：<input type="text" name="username"> <br/>
    密 码：<input type="password" name="password"> <br/>
          <input type="submit" value="登录">
  </form>
</body>
</html>
```



---

## 下午内容

## Spring Boot主要内容

1. Spring Boot介绍
2. 开发Spring Boot入门程序
3. Spring Boot**替换Servlet容器**
4. Spring Boot整合默认数据源
5. Spring Boot整合阿里巴巴Druid数据源
6. Spring Boot整合MyBatis
7. **编写Spring Boot Starter整合MyBatis**
8. Spring Boot整合MyBatis-Plus
9. Spring Boot配置Servlet和Filter
10. Spring Boot开发配置拦截器
11. Spring Boot处理静态资源
12. Spring Boot整合Quartz、Spring Task（`Quartz课程`）
13. Spring Boot整合`RabbitMQ`
14. Spring Boot整合Redis（`Redis课程`）
15. Spring Boot整合Dubbo（`Dubbo课程`）

## 1. Spring Boot介绍（理解）

### 1. Spring Boot是什么

​		Spring Boot是Spring团队在2014年推出的全新框架，它出现的**目的**就是**简化Spring项目的开发过程**，可以让开发者使用少量代码快速创建Spring应用程序；

### 2. Spring Boot的特点

1. 可以创建独立的Spring应用程序
2. 内置了Tomcat、Jetty和Undertow等<u>Web容器</u>（**不需要部署war包**）
3. 提供了Starter依赖来简化Spring配置
4. 尽可能自动配置Spring和第三方框架
5. 提供了生产级别的功能：监控、健康检查和外部化配置
6. 没有代码的生成、不需要XML配置

## 2. 开发Spring Boot入门程序

### 2.1 三种创建Spring Boot程序的方式

1. **可以在`https://start.spring.io/`网站上创建**

   <img src="imgs\image-20230320144246637.png" style="zoom:30%;" />  

2. **使用IDEA提供的`Spring Initializr`工具创建**

   <img src="imgs\image-20220725165037495.png" alt="image-20220725165037495" style="zoom: 43%;" /> 

   <img src="imgs\image-20220725165124471.png" alt="image-20220725165124471" style="zoom:43%;" /> 

   <img src="imgs\image-20220725165214372.png" alt="image-20220725165214372" style="zoom:43%;" /> 

   

3. **手动创建**

   JDK 8+

   IDE（IDEA、Eclipse）

## 3. 修改Web容器端口号、contextPath和多环境配置

1. 在src/main/resources下创建`application.properties`文件

   ```properties
   # http://localhost:9090/etoak/hello?name=zs
   server.port=9090
   server.servlet.context-path=/etoak
   ```

## 4. Spring Boot替换Servlet容器

- 在pom.xml中排除`Tomcat`，新增`Jetty`依赖

  ```xml
  <dependencies>
    <!-- 整合Spring MVC、tomcat、logback、jackson -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
  
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
  </dependencies>
  ```

##  5. Spring Boot自动配置默认数据源

1. 自动配置数据源的starter：`spring-boot-starter-jdbc`
2. Spring Boot 1.x 默认数据源：tomcat-jdbc
3. Spring Boot 2.x 默认数据源：HikariCP => `HikariDataSource`

## 6. Spring Boot整合MyBatis

​		Spring官方没有提供与MyBatis整合的Starter，但是MyBatis官方自己实现了与Spring Boot整合（实现了MyBatis自动配置）。

1. Starter：**mybatis-spring-boot-starter**(自动配置SqlSessionFactory)

   前提：数据源必须配置了，并且有且只有一个

2. 其它配置项：mapperLocations、typeAaliasesPackage

3. 增加注解：`@MapperScan`



---



# 代码补充：

## resources配置文件

### application.properties

```proper
spring.profiles.active=prod

```



### application-dev.properties

```proper
# http://localhost:9090/etoak/hello?name=zs
server.port=9090
server.servlet.context-path=/etoak
```



### application-prod.properties

```proper
# http://localhost:8080/etoak/hello?name=zs
server.port=8080
server.servlet.context-path=/etoak

```



### 启动类Application.java

```java
package com.etoak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 注解@EnableAutoConfiguration: 启动Spring Boot自动配置
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.etoak")
public class HelloApplication {

  public static void main(String[] args) {
    SpringApplication.run(HelloApplication.class, args);
  }
}
```



---



## Spring Boot 整合 Mybatis

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.etoak.et2301.boot</groupId>
  <artifactId>boot-02-mybatis</artifactId>
  <version>1.0-SNAPSHOT</version>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.5</version>
  </parent>

  <dependencies>
    <!-- spring-boot-starter-web -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- spring-boot-starter-jdbc 自动配置数据源 -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

    <!-- mysql -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <!-- mybatis-spring-boot-starter -->
    <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>2.2.2</version>
    </dependency>

    <!-- lombok -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- spring-boot-starter-test 测试框架 -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>

</project>
```



### application.yml

```yaml
# server.port=8080
# server.servlet.context-path=/etoak
# yaml => Yet Another Markup Language
server:
  port: 8080
  servlet:
    context-path: /etoak

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql:///et2301?serverTimezone=UTC
    username: root
    password: etoak

mybatis:
  mapper-locations: classpath:mapper/**/*.xml
  type-aliases-package: com.etoak

```

### UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.etoak.mapper.UserMapper">

  <sql id="base_sql">
    select id,
           username,
           email,
           age,
           create_time as createTime
      from sys_user
  </sql>

  <select id="selectById" parameterType="long" resultType="User">
    <include refid="base_sql" />
    where id = #{id}
  </select>

  <select id="selectList" parameterType="string" resultType="User">
    <include refid="base_sql" />
    <where>
      <if test="username != null and username != ''">
        AND username like concat('%', #{username}, '%')
      </if>
    </where>
  </select>

</mapper>
```

### UserMapper.interface

```java
package com.etoak.mapper;

import com.etoak.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

  User selectById(long id);

  List<User> selectList(@Param("username") String username);

}
```

### User.java

```java
package com.etoak.entity;

import lombok.Data;

@Data
public class User {

  private Long id;

  private String username;

  private String password;

  private String email;

  private Integer age;

  private Integer state;

  private Long visitCount;

  private Integer money;

  private String createTime;

}
```

### UserController.java

```java
package com.etoak.controller;

import com.etoak.entity.User;
import com.etoak.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  /**
   * 正式开发中最好不要直接注入持久层的内容
   */
  @Autowired
  UserMapper userMapper;

  /**
   * get /user/2
   */
  @GetMapping("/{id}")
  public User getUser(@PathVariable long id) {
    return userMapper.selectById(id);
  }

  /**
   * get /user/list?username=z
   */
  @GetMapping("/list")
  public List<User> getList(String username) {
    return userMapper.selectList(username);
  }

}
```

### 启动类Boot02Application.java

```java
package com.etoak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication
 * 1、SpringBootConfiguration (@Configuration)
 * 2、EnableAutoConfiguration
 * 3、@ComponentScan(basePackages="启动类所在包")
 */
@SpringBootApplication
@MapperScan(basePackages = "com.etoak.**.mapper")
public class Boot02Application {

  public static void main(String[] args) {
    SpringApplication.run(Boot02Application.class, args);
  }
}
```



---



# day08(Spring Boot整合Mybatis+项目练习)



## Spring工厂加载机制

### 1. JDK SPI

​		SPI全称Service Provider Interface，是Java提供的一套用来被第三方实现或者扩展的接口，它可以用来启用框架扩展组件和替换组件，**SPI的作用**就是为这些被扩展的API寻找服务实现，简单来说就是<u>可以使用SPI来解耦，实现插件的自由插拔</u>。

### 2. Spring SPI

​		**Spring框架从3.2版本开始提供了`SpringFactoriesLoader`工厂加载机制，类似于Java提供的SPI机制，Spring利用这种机制实现了Spring Bean的自动配置。**

### 3. `SpringFactoriesLoader`工厂的加载机制的默认约定

1. SpringFactoriesLoader是Spring框架内部的通用工厂加载机制（Spring3.2 之后提供的）

2. 它负责加载classpath下的`META-INF/spring.factories`文件，并实例化文件中的类型

3. **文件内容格式必须是Properties格式（k=v）**，

   key必须是某个类型的全限定名称（包名+类名）

   value可以是单个值，可以是使用逗号分隔的多个值

   例如：`example.MyService=example.MyService1,example.MyService2`

   key和value之间可以没有继承关系

### 4. Spring Boot的自动配置

1. Spring Boot自动配置主要是通过Spring工厂加载机制实现的；

2. 启动类上的**@SpringBootApplication**注解包含了三个注解：**@SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan**，其中<u>最重要</u>的注解就是@EnableAutoConfiguration；

3. @EnableAutoConfiguration注解导入一个`AutoConfigurationImportSelector`类型，这个类型实现了`ImportSelector接口`的`selectImports()方法`，在这个方法中使用Spring工厂加载机制加载classpath下`META-INF`文件夹下的`spring.factories`文件中以EnableAutoConfiguration为key的所有Value值（自动配置类），对**满足条件的类型**利用Java Config方式进行自动配置；

   <img src="imgs\Spring Boot自动配置.png" style="zoom:90%; margin-left:0px" >

### 5. Spring Boot 2.7.0 之后的自动配置实现方式

1. Spring Boot 2.7.0之后增加了加载Classpath下`META-INF/spring`文件夹下的`org.springframework.boot.autoconfigure.AutoConfiguration.imports`文件中的类型 

   <img src="imgs\Spring Boot 2.7.0 自动配置.png" style="zoom:40%; margin-left:0px" >



---



## 今天内容

1. Spring Boot自动配置
2. 编写MyBatis Starter
3. 整合MyBatis-Plus
4. 创建前端工程
5. 创建后端工程
6. 开发字典接口
7. 开发省市区列表接口

### 1. Spring Boot自动配置

### 2. 编写MyBatis Starter

1. 创建`etoak-mybatis-spring-boot-starter`，导入maven依赖

   ```xml
   <parent>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-parent</artifactId>
     <version>2.7.5</version>
   </parent>
   
   <dependencies>
     <!-- spring-boot-starter-jdbc -->
     <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-jdbc</artifactId>
     </dependency>
   
     <!-- mybatis -->
     <dependency>
       <groupId>org.mybatis</groupId>
       <artifactId>mybatis</artifactId>
       <version>3.5.10</version>
     </dependency>
   
     <!-- mybatis-spring -->
     <dependency>
       <groupId>org.mybatis</groupId>
       <artifactId>mybatis-spring</artifactId>
       <version>2.1.0</version>
     </dependency>
   
     <!-- configuration-processor -->
     <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-configuration-processor</artifactId>
       <optional>true</optional>
     </dependency>
   
     <!-- autoconfigure-processor -->
     <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-autoconfigure-processor</artifactId>
       <optional>true</optional>
     </dependency>
   </dependencies>
   ```

2. 创建配置类：`com.etoak.configuration.MyBatisAutoConfiguration`

3. 创建Properties配置类：`com.etoak.configuration.MyBatisProperties`

4. 在src/main/resources下创建文件：

   `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`

   并在文件中添加自动配置类类名：`com.etoak.configuration.MyBatisAutoConfiguration`

5. 修改`MyBatisProperties`（配置mapperLocations、typeAliasesPackage）

6. 修改`MyBatisAutoConfiguration`实现自动配置

7. 将项目安装到本地Maven仓库    **clean + install**

8. 在`boot-02-mybatis`中替换原来的**MyBatis**官方的Starter依赖

### 3. 整合MyBatis-Plus

1. 官网：https://www.baomidou.com/

2. Spring Boot配置

   mapperLocations默认配置：

   `private String[] mapperLocations = new String[]{"classpath*:/mapper/**/*.xml"};`

3. 使用MyBatis Plus编写SQL

   1、单表的自动生成

   2、条件构造器

   3、手动编写SQL语句（Mapper映射文件中）

4. 用到的注解

   **@TableName**：表名称和实体类名称不一致时使用

   **@TableId**：标记主键生成策略

5. 接口、类：**BaseMapper、IService、ServiceImpl**

   IService接口：通用 Service **CRUD** 封装IService接口

   BaseMapper接口：通用 **CRUD** 封装BaseMapper接口

### 创建前端工程：

1. vue create car-app

   选择Babel、VueRouter

2. 安装axios、qs、element-ui          npm i axios -S...

   <img src="imgs\image-20230515152224955.png" style="zoom:33%;" /> 

### 创建后端工程



### 字典接口

1. 接口地址：http://localhost:8000/dict/list?type=level

2. 请求方法：`GET`

3. 请求参数

   | 参数名 | 参数类型 | 是否必填 | 参数说明                                                     |
   | :----: | :------: | :------: | ------------------------------------------------------------ |
   |  type  |  String  |    是    | type：字典类型<br />type=level：获取级别列表<br />type=gearbox：获取变速箱列表<br />type=disp：获取变速箱列表 |

4. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": [
       {
         "name": "自动",
         "value": "1"
       },
       {
         "name": "手动",
         "value": "2"
       }
     ]
   }
   ```

### 省市区级联列表接口

1. 接口地址：http://localhost:8000/area/list

2. 请求方法：`get`

3. 请求参数：`无`

4. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": [
       {
         "name": "山东省",
         "id": "370000",
         "children": [
           {
             "name": "济南市",
             "id": "370100",
             "children": [
               {
                 "name": "历下区",
                 "id": "370102",
                 "children": null
               },
               {
                 "name": "天桥区",
                 "id": "370105",
                 "children": null
               }
             ]
           },
           {
             "name": "青岛市",
             "id": "370200",
             "children": [
               {
                 "name": "市南区",
                 "id": "370202",
                 "children": null
               },
               {
                 "name": "市北区",
                 "id": "370203",
                 "children": null
               }
             ]
           }
         ]
       }
     ]
   }
   ```




---



## 代码补充：

### Spring Boot 整合 Mybatis （clean+install打包成Mybatis包）

## etaok-mybatis-spring-boot-starter

### pom.xml

```xml
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.5</version>
  </parent>

  <dependencies>
    <!-- spring-boot-starter-jdbc -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

    <!-- mybatis -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.10</version>
    </dependency>

    <!-- mybatis-spring -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>2.1.0</version>
    </dependency>

    <!-- configuration-processor -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-configuration-processor</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- autoconfigure-processor -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-autoconfigure-processor</artifactId>
      <optional>true</optional>
    </dependency>
  </dependencies>
```

### resources

### /META-INF/spring/

### org.springframework.boot.autoconfigure.AutoConfiguration.imports

```pro
com.etoak.configuration.MyBatisAutoConfiguration
```

### MybatisProperties.java

```java
package com.etoak.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "etoak")
public class MyBatisProperties {
  //
  private String mapperLocations;
  //
  private String typeAliasesPackage;

  public String getMapperLocations() {
    return mapperLocations;
  }

  public void setMapperLocations(String mapperLocations) {
    this.mapperLocations = mapperLocations;
  }

  public String getTypeAliasesPackage() {
    return typeAliasesPackage;
  }

  public void setTypeAliasesPackage(String typeAliasesPackage) {
    this.typeAliasesPackage = typeAliasesPackage;
  }
}
```



### com.etoak.configuration.MybatisAutoConfiguration.java

```java
package com.etoak.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
// Classpath下必须有SqlSessionFactory.class, SqlSessionFactoryBean.class
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
// Spring容器必须只有一个DataSource
@ConditionalOnSingleCandidate(DataSource.class)
// 必须在数据源自动配置完成之后配置MyBatis(原因: SqlSessionFactory需要一个数据源)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
// 配置类
@EnableConfigurationProperties(MyBatisProperties.class)
public class MyBatisAutoConfiguration {

  @Autowired
  DataSource dataSource;

  @Autowired
  MyBatisProperties properties;

  @Bean
  public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
    System.out.println("自动配置MyBatis.");
      
    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

    // 数据源
    factory.setDataSource(this.dataSource);

    // Java Bean别名
    factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());

    // mapperLocations
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource[] resources = resolver.getResources(this.properties.getMapperLocations());
    factory.setMapperLocations(resources);
    return factory;
  }

}
```



---



### boot-03-mybatis-plus

### pom.xml

```xml
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.5</version>
  </parent>

  <dependencies>
    <!-- spring-boot-starter-web -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- mybatis-plus-boot-starter -->
    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-boot-starter</artifactId>
      <version>3.4.3.1</version>
    </dependency>

    <!-- mysql -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <!-- hutool-all -->
    <dependency>
      <groupId>cn.hutool</groupId>
      <artifactId>hutool-all</artifactId>
      <version>5.8.0</version>
    </dependency>

    <!-- lombok -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- spring-boot-starter-test -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>
```

### application.yml

```yaml
server:
  port: 8080

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql:///et2301?serverTimezone=UTC
    username: root
    password: etoak

mybatis-plus:
  type-aliases-package: com.etoak
  # mapperLocations默认配置
  # mapper-locations: classpath*:/mapper/**/*.xml
  configuration:
    # 控制台输出sql(与MyBatis配置一致); 上线时应该删除这个配置
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

### resources/mapper.UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.etoak.mapper.UserMapper">

</mapper>
```

### entity/User.java

```java
package com.etoak.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * sys_user => SysUser
 * 当表名称和实体类名称不一致时, 需要使用@TableName注解
 */
@Data
@TableName("sys_user")
public class User {

  /** 自增id */
  @TableId(type = IdType.AUTO)
  private Long id;

  private String username;

  private String password;

  private String email;

  private Integer age;

  private Integer state;

  private Long visitCount;

  private Integer money;

  private String createTime;


}
```



### mapper/UserMapper.interface

```interface
package com.etoak.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etoak.entity.User;

public interface UserMapper extends BaseMapper<User> {

}
```



### com.etoak.controller.UserController.java

```java
package com.etoak.controller;

import com.etoak.entity.User;
import com.etoak.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserMapper userMapper;

  @GetMapping("/{id}")
  public User getUser(@PathVariable long id) {
    return userMapper.selectById(id);
  }

  @PostMapping
  public String addUser(User user) {
    userMapper.insert(user);
    return "success";
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable long id) {
    userMapper.deleteById(id);
    return "success";
  }

}
```

### Boot03Application.java

```java
package com.etoak;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.etoak.**.mapper")
@EnableTransactionManagement // 事务
public class Boot03Application {

  public static void main(String[] args) {
    SpringApplication.run(Boot03Application.class, args);
  }
}
```



---

---

## Mybatis-plus代码生成器

### pom.xml

```xml
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.5</version>
  </parent>

  <dependencies>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-boot-starter</artifactId>
      <version>3.4.3.1</version>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid-spring-boot-starter</artifactId>
      <version>1.2.11</version>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <dependency>
      <groupId>cn.hutool</groupId>
      <artifactId>hutool-all</artifactId>
      <version>5.8.0</version>
    </dependency>

    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- spring-boot-starter-test -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>

    <!-- mybatis plus代码生成器 -->
    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-generator</artifactId>
      <version>3.4.1</version>
    </dependency>

    <!-- Velocity模板引擎 -->
    <dependency>
      <groupId>org.apache.velocity</groupId>
      <artifactId>velocity-engine-core</artifactId>
      <version>2.3</version>
    </dependency>

  </dependencies>
```

### Bybatis plus代码生成器：MPGenerator.java

```java
package com.etoak;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

/**
 * @Author Lushisan
 * @Date 2023/5/15 16:32
 * @Title: MPGenerator
 * @Description:
 * @Package com.etoak
 */

public class MPGenerator {
    //@Test
    public void generate() {
        AutoGenerator generator = new AutoGenerator();

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setAuthor("et2301");
        globalConfig.setOpen(false);
        // 重新生成时文件是否覆盖
        globalConfig.setFileOverride(true);
        // 去掉Service接口的首字母I
        globalConfig.setServiceName("%sService");
        // 主键策略
        globalConfig.setIdType(IdType.AUTO);
        // 定义生成的实体类中日期类型
        globalConfig.setDateType(DateType.ONLY_DATE);
        // mapper映射文件中生成 ResultMap
        globalConfig.setBaseResultMap(true);
        // mapper映射文件中生成 基本字段
        globalConfig.setBaseColumnList(true);
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/et2301?serverTimezone=UTC");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("etoak");
        dataSourceConfig.setDbType(DbType.MYSQL);
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.etoak.system");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setService("service");
        packageConfig.setMapper("mapper");
        // Mapper映射文件
        packageConfig.setXml("mapper");
        generator.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 设置表前缀不生成
        strategy.setTablePrefix("t_");
        // 数据库表字段映射
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // restful api风格控制器
        strategy.setRestControllerStyle(true);
        // url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(false);
        // "t_%"
        strategy.setLikeTable(new LikeTable("t_", SqlLike.RIGHT));
        //
        strategy.setInclude("t_area");

        strategy.setEntityLombokModel(true);
        generator.setStrategy(strategy);
        generator.execute();
    }
}
```



### REST接口统一返回值：ResultVO.java

```java
package com.etoak.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Lushisan
 * @Date 2023/5/15 16:56
 * @Title: ResultVO
 * @Description:
 * @Package com.etoak.common.vo
 */

/**
 *  REST接口统一返回值
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    public static final int SUCCESS_CODE = 200;

    public static final String SUCCESS_MSG = "success";

    public static final int FAILED_CODE = 500;

    public static final String FAILED_MSG = "系统异常";

    // 响应码
    private Integer code;
    // 响应消息
    private String msg;
    // 响应结果数据
    private T data;

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> ResultVO failed() {
        return failed(FAILED_MSG);
    }

    public static <T> ResultVO failed(String msg) {
        return failed(FAILED_CODE, msg);
    }

    public static <T> ResultVO failed(int code, String msg) {
        return new ResultVO<>(code, msg, null);
    }
}
```



---



# day09(项目：图片上传、车辆添加)

## 今天内容

1. 图片上传接口
2. Spring Boot处理静态资源
3. 车辆添加接口
4. 全局异常处理
5. 后端校验
6. 前后端联调
7. Spring Boot解决跨域
8. 搭建前端页面框架
9. 开发添加页面

## 1. 图片上传接口

Spring Boot默认使用`StandardServletMultipartResolver`进行文件上传

默认文件的大小限制1MB

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 2MB
```

1. 接口地址：http://localhost:8000/upload/image

2. 请求方法：`post`

3. 请求参数类型：`multipart/form-data`

4. 请求参数：`file`

5. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": "/pics/xxxxxxxx.jpg"
   }
   ```

## 2. Spring Boot处理静态资源

Spring Boot默认处理的静态资源在Classpath下

默认的自动配置是**WebMvcAutoConfiguration**

1. `mapping="/webjar/**"`，`location="classpath:/META-INF/resources/webjars/"`

2. `mapping="/**"`，location如下

   ```
   location="classpath:/META-INF/resources/"
   location="classpath:/resources/"
   location="classpath:/static/"
   location="classpath:/public/"
   ```

3. 如何自定义**静态资源的请求路径**和**静态文件位置**

   自己编写配置类，实现`WebMvcConfigurer`接口的`addResourceHandlers()方法`

## 3. 车辆添加接口

1. 接口地址：http://localhost:8000/car

2. 请求方法：`post`

3. 请求参数类型：`application/json`

4. 请求参数：

   

5. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": ""
   }
   ```

## 4. 全局异常处理



## 5. 后端Java Bean校验

1. 导入Maven依赖

   ```
   spring-boot-starter-validation
   ```

2. 创建ValidatorUtil

3. 为Java Bean添加注解

## 6. 前后端联调

1. 创建开发环境用的配置环境变量的文件：`.env.development`

   执行`vue-cli-service serve `命令时(开发者实际执行的命令：npm run serve)，使用`.env.development`文件

2. 创建`request.js`

3. 创建调用远程接口的api

## 7. Spring Boot处理跨域

- 跨域的原因：浏览器同源策略
- 什么才是同源：当两个域的**协议**、**域名**和**端口号**一致时才是同源



---



# day10(项目:分页查询_逻辑删除车辆及更新)

## 今天内容

1. 完成添加功能
2. 开发查询接口、开发列表页面
3. 开发逻辑删除接口、页面的删除功能
4. 开发更新接口

## 1. 完成添加功能

​		图片上传成功和删除后的二次校验

​		ElementUI日期选择器：禁用今天之后的日期

## 2. 开发查询接口、开发列表页面

1. 接口地址：http://localhost:8000/car

2. 请求方法：`get`

3. 请求参数

   brand：品牌

   series：车系

   pageNumber：页码

   pageSize：每页显示记录数

4. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": {
       "pageNumber": 1,
       "pageSize": 10,
       "rows": [
         {
           "id": 1,
           "brand": "1",
           "series": "xx",
           "level": "1",
           "levelName": "小型车",
           "gearbox": "1",
           "gearboxName": "自动",
           "disp": "1",
           "dispName": "1.5L"
         }
       ],
       "total": 100
     }
   }
   ```



## 3. 开发逻辑删除接口

​	逻辑删除：假删除，使用字段标记这条数据为删除状态

​	物理删除：从表中删除了数据（从磁盘上的文件中删除了数据）

1. 接口地址：http://localhost:8000/car

2. 请求方法：`DELELTE`

3. 请求参数

   ```json
   [1, 2, 3]
   ```

4. 请求参数类型：`application/json`

5. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": ""
   }
   ```

## 4. 开发更新接口

1. 接口地址：http://localhost:8000/car/{id}

2. 请求方法：`put`

3. 请求参数：

   ```json
   {
     "brand": "11",
     "series": "123213"
     ...
   }
   ```

4. 请求参数类型：`application/json`

5. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": ""
   }
   ```

## 5. 今天练习：完成更新

<img src="imgs\image-20230517175944788.png" alt="image-20230517175944788" style="zoom:50%;" /> 



---



# day11(项目_车辆展示-品牌列表-车系列表)

## 今天内容

1. 完成更新功能

2. 开发车辆展示页面

   开发品牌列表接口

   开发车系列表接口

   修改车辆列表接口：添加其它的参数

   开发前端页面

## 2. 开发车辆展示页面

### 2.1 开发品牌列表接口

1. 接口地址：http://localhost:8000/car/brand

2. 请求方法：`get`

3. 请求参数：`无`

4. 响应结果：

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": [
       "品牌1", "品牌2"
     ]
   }
   ```

### 2.2 开发车系列表接口

1. 接口地址：http://localhost:8000/car/series?brand=xx

2. 请求方法：`get`

3. 请求参数：

   brand：品牌，非必填项，不传默认返回10条车系

4. 响应结果：

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": [
       "车系1", "车系2"
     ]
   }
   ```

### 2.3 修改车辆列表接口：添加其它的参数

1. 先增加：level、gearbox、disp三个参数
2. 再增加：province、city、area三个参数

<img src="imgs/qs解决数组参数问题.png">

```js
/* 查询接口 */
/* get / car */
export function getCarList(pageNubmer, pageSize, queryForm) {
    return request({
        url: `/car?pageNumber=${pageNubmer}&pageSize=${pageSize}`,
        method: 'get',
        params: queryForm,
        paramsSerializer: {
            // 序列化数据
            // https://www.npmjs.com/package/qs
            
            //qs.stringify({ a: ['b', 'c'] }, { arrayFormat: 'indices' })
            // 'a[0]=b&a[1]=c'
            //qs.stringify({ a: ['b', 'c'] }, { arrayFormat: 'brackets' })
            // 'a[]=b&a[]=c'
            //qs.stringify({ a: ['b', 'c'] }, { arrayFormat: 'repeat' })
            // 'a=b&a=c'
            //qs.stringify({ a: ['b', 'c'] }, { arrayFormat: 'comma' })
            // 'a=b,c'
            
            serialize: function (params) {
                return qs.stringify(params, { arrayFormat: 'comma'})
            }
        }
    })
}
```



## 3. 验证码接口

1. 接口地址：http://localhost:8000/getCode

2. 请求方法：`get`

3. 请求参数：`无`

4. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": {
       "uuid": "xxxxxxxxxxxxxx",
       "code": "验证码图片的Base64编码"
     } 
   }
   ```

5. 导入Kaptcha

6. 配置Kaptcha的Producer对象

7. 编写Controller

## 4. 登录接口

1. 接口地址：http://localhost:8000/user/login

2. 请求方法：`post`

3. 请求参数类型：`x-www-form-urlencoded`

4. 请求参数：

   username

   password

   uuid：

   code：

5. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": "json web token"
   }
   ```

6. 实现步骤

   1、验证验证码

   2、验证用户名

   3、验证密码

   4、创建token





---





# day12(登录接口_JWT 路由守卫 拦截器)

## 今天内容

1. 完成登录接口 - 返回JWT
2. 开发登录页面
3. 全局前置路由守卫
4. AXIOS请求拦截器
5. Spring MVC拦截器实现接口验证
6. AXIOS响应拦截器

## 1. 完成登录接口 - 返回JWT

1. 引入Maven依赖

   ```xml
   <!-- Java Json Web Token   JDK1.8版本 -->
   <dependency>
     <groupId>io.jsonwebtoken</groupId>
     <artifactId>jjwt</artifactId>
     <version>0.9.1</version>
   </dependency>
   ```

2. 创建JWT

   ```java
   public class JwtUtil {
   
     public static final String KEY = "et230111111111";
   
     /**
      * 10分钟毫秒数
      */
     public static final long expire = 1000 * 60 * 10;
   
     /**
      * 创建JWT
      */
     public static String createJwt(String username) {
       Date issueDate = new Date();
       return Jwts.builder()
         .signWith(SignatureAlgorithm.HS256, KEY)
         .setIssuedAt(issueDate) // 签发时间
         .setExpiration(new Date(issueDate.getTime() + expire))
         .setSubject(username)
         .compact();
     }
   }
   ```

## 2. 开发登录页面

1. 在views创建Login.vue
2. 添加`/login`路由

## 3. 开发全局前置路由守卫

```js
/**
 * 全局前置路由守卫
 * to: 将要进入的路由
 * from: 从哪个路由来的
 * next: 函数, 必须确保这个函数被调用
 *   next()  进入to的路由
 *   next(false) 中断当前的导航
 *   next("/login") || next({path: '/login'}) || next({ name: 'login' })
 */
router.beforeEach((to, from, next) => {
  const token = sessionStorage.getItem('token')

  // 访问的是/login路由
  if (to.name === 'login') {
    if (token) {
      Message.warning('您已登录！')
      next('/')
    } else {
      next()
    }
  } else { // 访问的不是/login路由
    if (token) { // 如果登录了, 则允许进入当前路由
      next()
    } else {
      Message.error('您还未登录，请登录后操作！')
      setTimeout(() => {
        next('/login')
      }, 500)
    }
  }
})
```

## 4. axios请求拦截器

```js
/* axios请求拦截器 */
request.interceptors.request.use(config => {
  const { url } = config
  if (white_list.indexOf(url) < 0) {
    // 设置请求头
    config.headers['Authorization'] = sessionStorage.getItem('token')
  }

  // 必须return config. 没有这句就发不出请求了
  return config
}, e => {
  return Promise.reject(e)
})
```



## 5. Spring MVC拦截器实现接口验证



---

# Quartz课程

1. Quartz是什么
2. Quartz能做什么
3. Quartz的核心API
4. 测试Quartz API
5. Quartz的CRON表达式
6. 整合Spring （MVC）单机版、集群版
7. 整合Spring Boot单机版、集群版
8. 在Spring Boot中使用`Spring Task`

## 1. Quartz是什么

1. Quartz是一个功能丰富的**任务调度框架**，几乎可以集成任何Java应用中（小到单体应用，大到大型电子商务系统）；
2. Quartz可以用来创建成千上万的简单或复杂的调度任务；
3. Quartz的任务被定义为标准的Java组件，几乎可以执行任何编程任务；
4. Quartz包含许多企业级的功能，例如支持JTA事务和集群；

## 2. Quartz可以做什么

1. 定时发送邮件、短信
2. 定时同步数据（同步其它系统的数据到自己的系统中）
3. .....

## 3. Quartz的核心API

1. Job接口：创建一个任务，必须要实现Job接口

2. JobDetail：描述Job任务，定义Job的ID（name、group）

3. Trigger：任务执行规则（触发任务执行的规则 - 时间规则）

   SimpleTrigger：定义任务何时执行、执行频次、何时停止等

   CronTrigger：使用CRON表达式定义任务执行的时间规则

4. Scheduler：调度器，使用Trigger定义的规则执行任务

5. JobBuilder、TriggerBuilder、JobKey、TriggerKey....

6. JobExecutionContext

## 4. 测试Quartz API - SimpleTrigger

- 每隔5秒钟打印一个Hello

## 5. Cron表达式配置Quartz任务

## 6. 整合Spring

- Spring自己实现了与Quartz的整合（自己实现了FactoryBean），整合代码在spring-context-support模块

  JobDetailFactoryBean、CronTriggerSchedulerFactoryBean、SchedulerFactoryBean

- 单机版本

  MethodInvokingJobDetailFactoryBean

  特点：不需要任务对象实现任务接口

- 定义邮件任务

- 定义JobDetail、Trigger、Schedler，实现邮件下发



# Quartz代码补充：

# quartz-springboot

## pom.xml 依赖

```xml
    <dependency>
      <groupId>org.quartz-scheduler</groupId>
      <artifactId>quartz</artifactId>
      <version>2.3.2</version>
    </dependency>
```

## HelloJob.java 任务

```java
package com.etoak.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Quartz任务
 */
public class HelloJob implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String date = formatter.format(now);

    System.out.println("Hello=>" + date);
  }
}
```

## SimpleTest.java 

```java
package com.etoak;

import com.etoak.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 测试SimpleTrigger
 */
public class SimpleTest {

  public static void main(String[] args) throws SchedulerException {
    // 创建JobDetail
    JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
      .withIdentity("hello", "hello")
      .build();

    // 创建Trigger
    SimpleTrigger trigger = TriggerBuilder.newTrigger()
      .withIdentity("hello", "hello")
      .startNow() // 立即执行
      .withSchedule(SimpleScheduleBuilder.simpleSchedule()
        .withIntervalInSeconds(5) // 每隔5s执行一次
        .repeatForever()
      ).build();

    // 创建Scheduler 开启调度
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
  }
}
```

## CronTest.java

```java
package com.etoak;

import com.etoak.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronTest {

  public static void main(String[] args) throws SchedulerException {
    JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
      .withIdentity(JobKey.jobKey("hello", "hello"))
      .build();

    CronTrigger trigger = TriggerBuilder.newTrigger()
      .withIdentity(TriggerKey.triggerKey("hello", "hello"))
      .withSchedule(CronScheduleBuilder.cronSchedule("2/5 * * 19 5 ?"))
      .build();

    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
  }
}
```



# quartz-springmvc

## pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.etoak.et2301.quartz</groupId>
  <artifactId>quartz-springmvc</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>quartz-springmvc</name>

  <dependencies>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <optional>true</optional>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.3.21</version>
    </dependency>

    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.14.1</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>5.3.21</version>
    </dependency>

    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.3.21</version>
    </dependency>

    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.24</version>
      <optional>true</optional>
    </dependency>

    <dependency>
      <groupId>org.quartz-scheduler</groupId>
      <artifactId>quartz</artifactId>
      <version>2.3.2</version>
    </dependency>
  </dependencies>

  <build>
    <finalName>quartz-springmvc</finalName>
  </build>
</project>
```

## web.xml

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring-root.xml</param-value>
  </context-param>

  <filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
</web-app>
```

## spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.etoak.**.controller" />

    <mvc:default-servlet-handler />
    <mvc:annotation-driven> </mvc:annotation-driven>
</beans>
```

## spring-root.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.etoak.**.service" />

    <!-- 导入其他容器配置 拥有其他容器配置里的属性 -->
    <import resource="classpath:standalone.xml" />

</beans>
```

## standalone.xml 配置Quartz 任务类

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="emailJob" class="com.etoak.job.EmailJob" />

    <!-- emailJobDetail -->
    <bean id="emailJobDetail" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
        <property name="name" value="emailJob" />
        <property name="group" value="emailJob" />
        <!-- 任务对象 -->
        <property name="targetObject" ref="emailJob" />
        <!-- 任务对象的方法 -->
        <property name="targetMethod" value="send" />
    </bean>

    <!-- Trigger -->
    <bean id="emailTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <property name="name" value="emailTrigger" />
        <property name="group" value="emailTrigger" />
        <!-- 关联一个JobDetail -->
        <property name="jobDetail" ref="emailJobDetail" />
        <!-- 执行规则 -->
        <property name="cronExpression" value="0/5 * * * * ?" />

    </bean>

    <!-- Scheduler -->
    <bean class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
        <property name="triggers">
            <array>
                <ref bean="emailTrigger" />
            </array>
        </property>
    </bean>
</beans>
```

## EmailJob.java 模拟任务类

```java
package com.etoak.job;

/**
 * @Author Lushisan
 * @Date 2023/5/19 19:45
 * @Title: EmailJob
 * @Description:
 * @Package com.etoak.job
 */
public class EmailJob {

    public void send() {
        System.out.println("send Email ....");
    }
}
```

## HelloController.java 前端请求

```java
package com.etoak.job;

/**
 * @Author Lushisan
 * @Date 2023/5/19 19:45
 * @Title: EmailJob
 * @Description:
 * @Package com.etoak.job
 */
public class EmailJob {

    public void send() {
        System.out.println("send Email ....");
    }
}
```



---



# 周一至周五车辆管理系统项目总结(重点和要点)

前后端项目源码放至 **../day12(登录接口JWT_Quartz)(车辆项目总结)**

项目结构

<img src="imgs/xmjt.png">

<img src="imgs/xmjt2.png">



---



# day13d(Quartz集群)

## 今天内容

1. Spring Boot配置单机版Quartz
2. Quartz集群
3. Spring MVC整合Quartz集群
4. Spring Boot整合Quartz集群
5. 解决集群任务不能使用Spring容器中的对象的问题
6. 使用Spring Task创建定时任务

## 1. Spring Boot配置单机版Quartz

### job.EmailJob.java

```java
package com.etoak.job;

import org.springframework.stereotype.Component;

@Component
public class EmailJob {

  public void send() {
    System.out.println("发送邮件!!");
  }

}
```

### config.QuartzConfig.java

```java
package com.etoak.config;

import com.etoak.job.EmailJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * standalone.xml
 * 任务对象
 * JobDetail对象  -> MethodInvokingJobDetailFactoryBean
 * CronTrigger对象 -> CronTriggerFactoryBean
 * Scheduler对象   -> SchedulerFactoryBean
 */
@Configuration
public class QuartzConfig {

  @Autowired
  EmailJob emailJob;

  /** JobDetail */
  @Bean
  public MethodInvokingJobDetailFactoryBean emailJobDetail() {
    MethodInvokingJobDetailFactoryBean factory = new MethodInvokingJobDetailFactoryBean();

    // 属性: name、group、targetObject、targetMethod
    factory.setName("emailJob");
    factory.setGroup("emailJob");
    factory.setTargetObject(emailJob);
    factory.setTargetMethod("send");
    return factory;
  }

  /** CronTrigger */
  @Bean
  public CronTriggerFactoryBean emailTrigger() {
    CronTriggerFactoryBean factory = new CronTriggerFactoryBean();

    // 属性: name、group、jobDetail、cronExpression
    factory.setName("emailTrigger");
    factory.setGroup("emailTrigger");
    factory.setJobDetail(emailJobDetail().getObject());
    factory.setCronExpression("*/5 * * * * ?");
    return factory;
  }

  @Bean
  public SchedulerFactoryBean scheduler() {
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setTriggers(emailTrigger().getObject());
    return factory;
  }

}
```

### 启动类 QuartzApplication.java

### 配置 application.proprotes

---



<img src="imgs/quartz表.png">



## 2. Quartz集群

- 集群可以解决**单点故障问题**，提高**服务的可用性**（保障服务的高可用）

- Quartz集群之后，**任务**和**调度器**都会集群

  当一个调度调度了集群中某个任务，那么其它的调度器就不会再调度这个任务。

  如果某个调度器执行出现故障，其它的调度就可以接管它的任务。 

- Quartz通过**JDBC方式**将**任务**和**调度器**等<u>存储到数据库中实现了集群</u>

### 2.1 创建数据库和表

- 数据库：et2301
- 表：Quartz提供的

### 2.2 配置集群配置信息

- 配置集群名称、调度实例名称、线程池大小、表...

### 2.3 创建任务对象

- 必须实现Job接口或者**继承QuartzJobBean（Spring提供的，它实现了Job接口）**

### 2.4 配置Spring Bean

1. 数据源：将任务写入哪个数据库

2. 事务管理器

3. **JobDetail对象 => `JobDetailFactoryBean`**

4. CronTrigger => `CronTriggerFactoryBean`

5. Scheduler => `SchedulerFactoryBean`

   数据源、事务管理器、集群配置信息、triggers

## 3. 解决集群任务不能使用Spring容器中的对象的问题

1. 将Spring容器对象设置到SchedulerContext

   

2. 自定义创建任务对象的JobFactory，将任务工厂对象创建任务重新注册到Spring容器中；

   任务对象JobFactory创建的，Spring整合Quartz之后，默认的JobFactory是`AdaptableJobFactory`

   1、创建工厂对象：继承`AdaptableJobFactory`

   2、将工厂对象注入给`Scheduler`

   3、在集群任务中直接注入Spring容器的对象即可

## 使用Spring Task创建定时任务

​		Spring Task是Spring框架自带的定时任务，代码在`spring-context`模块

- 如何开启Spring Task

  `<task:annotation-driven />`

  `@EnableScheduling`

- 创建Spring Task任务

  在任何一个Spring对象中，使用`@Scheduled`注解注释一个公共的无参方法即可

- Spring Task默认单线程执行，修改线程数

  ```properties
  spring.task.scheduling.pool.size=20
  ```

  ```yaml
  spring:
    task:
      scheduling:
        pool:
          size: 50
  ```

  

  ---



# 代码补充：

## quartz-api

### com.etoak.job.HelloJob.java

```java
package com.etoak.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Quartz任务
 */
public class HelloJob implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String date = formatter.format(now);

    System.out.println("Hello=>" + date);
  }
}
```

### CronTest.java

```java
package com.etoak;

import com.etoak.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronTest {

  public static void main(String[] args) throws SchedulerException {
    JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
      .withIdentity(JobKey.jobKey("hello", "hello"))
      .build();

    CronTrigger trigger = TriggerBuilder.newTrigger()
      .withIdentity(TriggerKey.triggerKey("hello", "hello"))
      .withSchedule(CronScheduleBuilder.cronSchedule("2/5 * * 19 5 ?"))
      .build();

    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
  }
}
```

### SimpleTest.java

```java
package com.etoak;

import com.etoak.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 测试SimpleTrigger
 */
public class SimpleTest {

  public static void main(String[] args) throws SchedulerException {
    // 创建JobDetail
    JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
      .withIdentity("hello", "hello")
      .build();

    // 创建Trigger
    SimpleTrigger trigger = TriggerBuilder.newTrigger()
      .withIdentity("hello", "hello")
      .startNow() // 立即执行
      .withSchedule(SimpleScheduleBuilder.simpleSchedule()
        .withIntervalInSeconds(5) // 每隔5s执行一次
        .repeatForever()
      ).build();

    // 创建Scheduler 开启调度
    Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
  }
}
```

---



## quartz-boot-cluster

### application.properties

```properties
server.port=8000

# 数据源
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql:///et2301?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=etoak
spring.datasource.hikari.maximum-pool-size=60
```

### quartz.properties

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.etoak.et2301.quartz</groupId>
  <artifactId>quartz-boot-cluster</artifactId>
  <version>1.0-SNAPSHOT</version>

  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.5</version>
  </parent>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-quartz</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
    </dependency>
  </dependencies>


</project>
```



```properties
#可以设置为任意，用在 JDBC JobStore中来唯一标识实例，但是所有集群节点中必须相同。
org.quartz.scheduler.instanceName = et2301
# 属性为 AUTO即可，基于主机名和时间戳来产生实例 ID 
org.quartz.scheduler.instanceId = AUTO

## 线程
org.quartz.threadPool.class = org.quartz.simpl.SimpleThreadPool
org.quartz.threadPool.threadCount = 30
org.quartz.threadPool.threadPriority = 5
org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread = true

## 存储
org.quartz.jobStore.misfireThreshold = 60000
#JobStoreTX，将任务持久化到数据库中。因为集群中节点依赖于数据库来传播 Scheduler实例的状态，你只能在使用 JDBC JobStore 时应用 Quartz 集群。这意味着你必须使用 JobStoreTX 或是 JobStoreCMT 作为 Job 存储,不能在集群中使用 RAMJobStore。
org.quartz.jobStore.class = org.springframework.scheduling.quartz.LocalDataSourceJobStore
org.quartz.jobStore.driverDelegateClass=org.quartz.impl.jdbcjobstore.StdJDBCDelegate
org.quartz.jobStore.tablePrefix = QRTZ_
org.quartz.jobStore.maxMisfiresToHandleAtATime=10
#值 true，表示 Scheduler实例要参与到一个集群当中。这一属性会贯穿于调度框架的始终，用于修改集群环境中操作的默认行为。
org.quartz.jobStore.isClustered = true
#定义了Scheduler 实例检入到数据库中的频率(单位：毫秒)。Scheduler 检查是否其他的实例到了它们应当检入的时候未检入；这能指出一个失败的 Scheduler 实例，且当前 Scheduler 会以此来接管任何执行失败并可恢复的 Job。通过检入操作，Scheduler 也会更新自身的状态记录。clusterChedkinInterval 越小，Scheduler 节点检查失败的 Scheduler 实例就越频繁。默认值是 15000 (即15 秒)。
org.quartz.jobStore.clusterCheckinInterval = 5000
```



### config.QuartzConfig.java

```java
package com.etoak.config;

import com.etoak.factory.ClusterJobFactory;
import com.etoak.job.OrderJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class QuartzConfig {

  @Autowired
  DataSource dataSource;

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public JobDetailFactoryBean orderJobDetail() {
    JobDetailFactoryBean factory = new JobDetailFactoryBean();
    factory.setName("orderJob");
    factory.setGroup("orderJob");

    // Class对象   反射创建之后没有注册到Spring容器中, 所以默认情况下不能使用Spring容器中的对象
    factory.setJobClass(OrderJob.class);
    return factory;
  }

  @Bean
  public CronTriggerFactoryBean orderTrigger() {
    CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
    factory.setName("orderTrigger");
    factory.setGroup("orderTrigger");
    factory.setJobDetail(orderJobDetail().getObject());
    factory.setCronExpression("0/5 * * * * ?");
    return factory;
  }

  @Autowired
  ClusterJobFactory clusterJobFactory;

  @Bean
  public SchedulerFactoryBean scheduler() {
    SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
    scheduler.setDataSource(dataSource);
    scheduler.setTransactionManager(this.transactionManager());

    // configLocation
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource resource = resolver.getResource("classpath:quartz.properties");
    scheduler.setConfigLocation(resource);

    scheduler.setTriggers(this.orderTrigger().getObject());

    // 将ApplicationContext以spring为key 设置到SchedulerContext中
    scheduler.setApplicationContextSchedulerContextKey("spring");

    // 设置创建集群任务的工厂
    scheduler.setJobFactory(clusterJobFactory);
    return scheduler;
  }

}
```



### controller.JobController.java

```java
package com.etoak.controller;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/job", produces = "text/plain;charset=utf-8")
public class JobController {

  @Autowired
  Scheduler scheduler;

  @RequestMapping("/pause")
  public String pause(@RequestParam String name,
                      @RequestParam String group) throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(name, group);
    if (!scheduler.checkExists(jobKey)) {
      return "任务不存在！";
    }

    scheduler.pauseJob(jobKey);
    return "任务暂停了！";
  }

  @RequestMapping("/resume")
  public String resume(@RequestParam String name,
                       @RequestParam String group) throws SchedulerException {
    TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
    if (!scheduler.checkExists(triggerKey)) {
      return "任务不存在！";
    }

    // 获取状态
    Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);

    // 如果不是暂停状态
    if (!triggerState.equals(Trigger.TriggerState.PAUSED)) {
      return "任务不是暂停状态, 不需要恢复！";
    }

    scheduler.resumeTrigger(triggerKey);
    return "任务恢复了！";
  }

}
```

### factory.ClusterJobFactory.java

```java
package com.etoak.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component
public class ClusterJobFactory extends AdaptableJobFactory {

  /** 可以将程序运行期创建对象重新注册到Spring容器中 */
  @Autowired
  AutowireCapableBeanFactory ioc;

  @Override
  protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
    // 集群任务对象
    Object jobInstance = super.createJobInstance(bundle);

    // 注册到Spring容器中
    ioc.autowireBean(jobInstance);

    return jobInstance;
  }
}
```

### job.OrderJob.java

```java
package com.etoak.job;

import com.etoak.service.OrderService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class OrderJob extends QuartzJobBean {

  @Autowired
  OrderService orderService;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    orderService.cancel();
  }

  //@Override
  //protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
  //  Scheduler scheduler = context.getScheduler();
  //  try {
  //    SchedulerContext schedulerContext = scheduler.getContext();
  //
  //    // get("spring")
  //    ApplicationContext ioc = (ApplicationContext) schedulerContext.get("spring");
  //
  //    // 获取Spring容器中的对象
  //    OrderService orderService = ioc.getBean(OrderService.class);
  //    orderService.cancel();
  //  } catch (SchedulerException e) {
  //    e.printStackTrace();
  //  }
  //}
}
```



### service.OrderService.java

```java
package com.etoak.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

  public void cancel() {
    System.out.println("到数据中更新(取消)订单状态");
  }

}
```

### 启动类 QuartzApplication.java

```java
package com.etoak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuartzApplication.class, args);
  }

}
```



---

## spring-task-boot

### application.yml

```yaml
server:
  port: 8080
spring:
  task:
    scheduling:
      pool:
        size: 50
      thread-name-prefix: et2301-

```

### service.TaskService.java

```java
package com.etoak.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Scheduled(cron = "0/5 * * * * ?")
  public void sendEmail() {
    System.out.println(Thread.currentThread().getName() + "发送邮件");
  }

  @Scheduled(cron = "0/5 * * * * ?")
  public void sendSms() {
    System.out.println(Thread.currentThread().getName() + "-----发送短信");
  }
}
```

### TaskApplication.java

```java
package com.etoak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // spring task
public class TaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskApplication.class, args);
  }
}
```



---

# day14( Linux基础 )

## 1. Linux简单介绍

​		Linux是1991年由芬兰人**林纳斯·托瓦兹**（Linus Torvalds）在赫尔辛基大学上学时出于个人爱好而编写的；

​		Linux是一个自由免费的，开放源代码的操作系统；

​		Linux是一个**多用户**、**多任务**、**支持多线程**和**多cpu**的操作系统；

## 2. 安装XShell

​		SSH客户端：Putty、SecureCRT(收费)、XShell等

​		XShell 是一个强大的安全终端模拟软件，它支持SSH1，SSH2，以及微软平台的Telnet协议。

​		XShell 是一个非常好用的SSH客户端，可以作为 Telnet、Rlogin、SSH、SFTP 等协议的安全终端模拟软件，可以让你轻松管理远程主机。

​		XShell特色功能包括**标签化管理远程会话、动态端口转发、自定义键盘映射、VB脚本支持、完全的 Unicode 支持**等。

## 3. Linux的目录

### 3.1 绝对路径和相对路径

​		Linux中没有C盘、D盘等盘符；Linux的根目录是：**/**

1. **绝对路径**

   从根目录**`/`**开始写起的文件名或者目录名称，比如：`/opt/jdk1.8`

2. **相对路径**

   相对于当前路径的文件名或目录名的写法

#### 3.1.1 特殊目录

1. <font color="red" size="5">.</font>：表示当前目录，也可以用./表示
2. <font color="red"  size="5">..</font>：表示上一级目录，也可以用../表示
3. <font color="red"  size="5">~</font>：表示**用户主目录**，相当于与Windows的C:/users/lenovo
4. <font color="red"  size="5">-</font>：**上一次所在目录**

### 3.2 Centos的目录

1. **/bin：binary二进制，存放二进制可执行的文件（比如cd）**

2. **/boot：存放开机时用到的各种文件；**

3. /dev：用于存放**设备文件**

4. **/etc：存放<u>系统配置文件</u>，如用户密码等（在/etc/passwd文件中）**

5. **/home：普通用户的用户主目录，相当于Windows的C:/Users目录**

6. /lib：存放系统中的<u>运行程序所需要的函式库和其它模块</u>；

7. /mnt：系统管理员安装临时文件系统的安装点；

8. **/opt：可选应用程序包所放置的位置；**

9. /proc：虚拟文件系统，它存的数据都在内存中，如系统核心、进程信息等；

10. **/root：<u>超级管理员用户root</u>的用户目录；**

11. /run：存放系统开机时所产生的各项信息；

12. **/sbin：super binary，存放超级管理员root用户可以执行的二进制文件**

13. /srv：service的缩写，网络服务所需要的的数据目录

14. /sys：虚拟文件系统，存放与硬件相关的信息，与/proc类似

15. /tmp：存放<u>临时文件</u>

16. **/usr：用于<u>存放系统应用程序</u>，其中**

    /usr/bin就是/bin

    /usr/lib就是/lib

    /usr/sbin就是/sbin

    /usr/local：一般用于安装软件

17. /var：主要针对常态性变动的文件，包括缓存以及某些软件运作所产生的文件


## 4. 用户管理和用户组管理

​		登录Linux系统需要账号和密码

### 4.1 用户管理

​		任何一个需要使用Linux系统资源的用户，都需要用于一个账号，每个账号都有唯一的用户名和密码，管理用户的文件：`/etc/passwd`

#### 4.1.1 添加用户：`useradd`

​		添加用户就是在系统中创建一个新用户，然后为新用户分配<u>用户名、用户组、主目录和登录Shell等资源</u>。**刚添加的账号是被锁定的，无法使用。**

1. 添加用户

   ```
   [root@192 ~]# useradd etoak
   ```

   - 默认创建名称为etoak的用户和用户组

   - 默认在/home下创建一个etoak的**用户目录**

   - 在用户文件**/etc/passwd**中添加etoak用户这一行

     

2. 几个参数说明

   ```
   -d:指定用户的家目录
   -u:指定用户的id
   -g:指定用户组（可以是名称，也可以是用户组id）
   ```

<img src="imgs\image-20211026152109378.png" alt="image-20211026152109378" style="zoom:50%; margin-left:60px" />  



#### 4.1.2 设置密码：`passwd`

- 用法：passwd 用户名

  <img src="imgs\image-20211026152307754.png" alt="image-20211026152307754" style="zoom:50%;" /> 

#### 4.1.3 切换用户：`su`

- **switch user**

- su 用户名

  - su - 用户名

  - `su`和`su -`区别：

    ```
    su：只是切换了用户身份，但Shell环境仍然是切换之前的用户的Shell；
    "su -"：同时切换了用户身份和Shell环境；
    只有切换了Shell环境才不会出现PATH环境变量错误；
    ```


#### 4.1.4 其它相关命令

- whoami：查看我是谁

- id命令：查看用户的基本信息，包括用户id、组id等

#### 4.1.5 修改用户：`usermod`

​		修改用户账号就是根据实际情况更改用户的有关属性，如**用户id、主目录、用户组、登录Shell**等。

- 几个参数说明

  ```
  -u:修改用户的id
  -g:修改用户组（可以是名称，也可以是用户组id）
  -l:修改用户名
    usermod -l 新用户名 旧用户名
    usermod -l et etoak
  ```

 

#### 4.1.6 删除用户：userdel

​		如果一个用户的账号不再使用，可以从系统中删除。删除用户账号就是要将**/etc/passwd等系统文件中**的该用户记录删除，必要时还删除用户的主目录。

- 参数说明

  `-r：表示删除用户的同时删除用户的家目录`

### 4.2 用户组管理

​		每个用户都有一个用户组，系统可以对一个用户组中的所有用户进行集中管理。不同Linux 系统对用户组的规定有所不同，Linux下的用户一般属于**与它同名的用户组**，这个用户组在创建用户时同时创建。

#### 4.2.1 添加用户组：`groupadd`

- 参数说明

  ```
  -g:指定用户组id，与useradd的-u参数类似
  ```

#### 4.2.2 修改用户组：`groupmod`

- 参数说明

  ```
  -g:修改用户组id
  -n:修该用户组名；与usermod的-l参数类似
  ```

#### 4.2.3 删除用户组：`groupdel`

- 删除刚刚创建的用户组
  `[root@192 home]# groupdel et1`

  

- 删除已包含用户的用户组
  `[root@192 home]# groupdel etoak`
  `groupdel: cannot remove the primary group of user 'et'`

  

- 解决办法：先删除用户组下的用户，然后删除用户组
  `[root@192 home]# userdel -r et`
  `[root@192 home]# groupdel etoak`
  `[root@192 home]# `

## 5. 文件类型与权限问题

![image-20210929085653533](imgs\image-20210929085653533.png)

## 6. 修改文件权限、所属用户和组

- 详见：`文件权限与修改文件拥有者.pdf文档`

## 7. Linux常用命令

### 7.1 pwd

- `print working directory`：显示当前所在目录

- 举例

  ```bash
  [root@localhost etoak]# pwd
  /home/etoak
  ```

### 7.2. cd

- change directory

- cd 目录名

- 举例

  1、绝对路径方式进入/opt目录
  <img src="G:\ET2301\Month 5\day14\5.Linux\imgs\image-20211026155544082.png" alt="image-20211026155544082" style="zoom:50%;" /> 

  2、在/opt目录下以相对路径的方式进入/home

  <img src="G:\ET2301\Month 5\day14\5.Linux\imgs\image-20211026155642230.png" alt="image-20211026155642230" style="zoom:50%;" /> 

- 特殊用法举例

  ```
  1. 进入上一级目录
  [root@192 home]# cd ..
  [root@192 /]#
  ```

  ```
  2. 返回上一次所在目录
  [root@192 /]# cd -
  /home
  ```

  ```
  3. 直接进入用户根目录，切换到root用户的根目录
  [root@192 home]# cd ~
  [root@192 ~]# pwd
  /root
  ```



### 7.3 ls

- list：列出某个目录下的文件和目录

- `ls [参数] [目录]`

- 举例

  1. 参数-l：详细列出某个目录的文件，使用**ls -l** 或者 **ll**.

  2. 列出指定目录下的文件和目录，列出/home目录的下的文件和目录

  3. 参数**-a**：列出目录下所有文件和目录(包括**隐藏文件**)

     

### 7.4 mkdir

- make directory：创建目录

- **创建单个目录**

  <img src="imgs\image-20211026160301786.png" alt="image-20211026160301786" style="zoom:50%;" /> 

- **创建多个目录**

  <img src="imgs\image-20211026160340352.png" alt="image-20211026160340352" style="zoom:50%;" /> 

- **递归创建子目录**：需要在创建目录的前边加一个 `-p参数`

  <img src="imgs\image-20211026160445920.png" alt="image-20211026160445920" style="zoom:50%;" /> 

### 7.5 rm

- 用法：rm [OPTION]... 文件或目录

  **-r**：recursion，递归删除

  **-f**：force，强制删除

  

- 示例1：不使用任何参数，直接删除文件

  ```
  [root@192 etoak]# rm 1.txt 
  rm: remove regular empty file ‘1.txt’? y
  [root@192 etoak]# ls -l
  total 4
  lrwxrwxrwx. 1 root root  16 Apr  7 16:33 dat -> /home/etoak/data
  drwxr-xr-x. 2 root root   6 Dec 28 16:14 data
  -rw-r--r--. 1 root root 109 Dec 28 16:14 Hello.java
  ```

  

- 示例2：**递归强制删除目录**

  `[root@192 etoak]# rm -rf data2`

  

- 示例3：强制删除文件

  `rm -f 文件名`

### 7.6 mv

1. **重命名**

   把/home/et目录下的data2目录修改为data3

   <img src="imgs\image-20211026160748918.png" alt="image-20211026160748918" style="zoom:50%;" /> 

   把/home/et目录下的1.txt文件修改为2.txt

   <img src="imgs\image-20211026160837602.png" alt="image-20211026160837602" style="zoom:50%;" /> 

2. **移动文件或目录**

   `mv 文件/目录 已存在的目录`

### 7.7 文件相关命令

#### 7.7.1 touch：创建文件

1. 创建单个文件

   `[root@192 etoak]# touch a.html`

2. 创建多个文件

   ```
   [root@192 etoak]# touch a.txt b.txt
   [root@192 etoak]# ls -l
   total 4
   -rw-r--r--. 1 root root   0 Apr  8 09:39 a.html
   -rw-r--r--. 1 root root   0 Apr  8 09:39 a.txt
   -rw-r--r--. 1 root root   0 Apr  8 09:39 b.txt
   ```

#### 7.7.2 输出重定向

1. **>**

   这个符号会**覆盖**之前的内容

   覆盖输出

2. **>>**

   这个符号会将新内容**追加**到原内容之后

   追加输出

   

#### 7.7.3 cat

1. 查看文件内容

   ```
   [root@192 etoak]# cat a.txt 
   1.txt
   a
   a.html
   ```

2. **合并**文件内容

   ```
   [root@192 etoak]# cat 1.txt a.txt > b.txt
   [root@192 etoak]# ls -l
   -rw-r--r--. 1 root root 498 Apr  8 09:44 1.txt
   -rw-r--r--. 1 root root  60 Apr  8 09:45 a.txt
   -rw-r--r--. 1 root root 558 Apr  8 09:46 b.txt
   ```

#### 7.7.4 head

- 显示文件开头某个数量的文件内容，显示前多少行内容

- 默认显示前10行

- <u>显示b.txt的前3行内容</u>

  ```
  [root@192 etoak]# head -3 b.txt 
  123123
  123123qwd
  1.txt
  ```



#### 7.7.5 tail

1. 查看文件末尾某个数量的文件内容

   - 直接使用`tail 文件名`，默认显示最后10行内容

   - 指定显示最后多少行内容，使用`-n参数`

     `tail -n 5 b.txt`

     `tail -5 b.txt`

   

2. **动态查看(循环读取)文件内容，常用于查看线上日志**

   使用`-f参数`，非常非常非常重要

   **<font color="red">循环显示，但是显示最后5行</font>**

   `tail -f -n 5 b.txt `

   `tail -5f b.txt`



#### 7.7.6 more

- 查看文件内容

  如果文件内容过大，会按百分比显示，与cat区分（cat会显示所有文件内容）

  **按d：**向下查看文件内容

  **按b：**向上查看文件内容

  **按q**：退出



#### 7.7.7 <font color="red">less</font>

- 查看文件内容

  less 文件名
  **按d：**向下查看文件内容
  **按b：**向上查看文件内容
  **按q：**退出less命令

  

- 搜索文件内容

  `/字符`内容：向下搜索"字符内容"
  `?字符`内容：向上搜索"字符内容"
  一般使用?的时候，<u>都需要到达文件末尾</u>，**按G**可以到达文件末尾

  n：重复上一个搜锁操作，向前搜索
  N：重复上一个搜索操作，向后搜索

#### 7.7.8 wc

1. 统计行数：`wc -l b.txt`

   ```bash
   [root@192 etoak]# wc -l b.txt 
   34 b.txt
   ```

2. 统计单词数 - 了解：`wc -w b.txt`

   ```bash
   [root@192 etoak]# wc -w b.txt 
   126 b.txt
   ```

3. 统计字节数 - 了解：`wc -c b.txt`


### 7.8 free

- 查看内存使用情况

- `free -m`

- `free -h`

  <img src="imgs\image-20211026165818635.png" alt="image-20211026165818635" style="zoom:50%;" /> 

- `free -g`：以GB的方式显示

- 以1000来计算内存，而不是以1024计算，增加参数：`--si`

  <img src="imgs\image-20211026165741391.png" alt="image-20211026165741391" style="zoom:50%;" /> 



### 7.9 df

- 显示文件系统磁盘的使用情况

- `df -h`

  <img src="imgs\image-20211026170123418.png" alt="image-20211026170123418" style="zoom:50%;" /> 



### 7.10 du

- 查看指定目录或文件大小

- 参数-s：汇总大小

- 参数-h：以可读性较高的方式显示

- `du -h`：分别显示所有的文件大小，**最后显示汇总大小**

  <img src="imgs\image-20211026170409033.png" alt="image-20211026170409033" style="zoom:50%;" /> 

- `du -sh`：以较高可读性方式，仅显示**汇总大小**

  <img src="imgs\image-20211026170323536.png" alt="image-20211026170323536" style="zoom:50%;" /> 

### 7.11 top

- 查看**进程资源使用率**，相当于windows中的任务管理器

- 执行top

  <img src="imgs\image-20211026170633758.png" alt="image-20211026170633758" style="zoom:50%;" /> 

- 常用操作

  1. 按**内存使用率降序排序**：输入top命令之后，按下**M**即可

     <img src="imgs\image-20211026170712812.png" alt="image-20211026170712812" style="zoom:50%;" /> 

  2. 按**CPU使用率降序排序**：输入top命令之后，按下**P**即可

     <img src="imgs\image-20211026170749031.png" alt="image-20211026170749031" style="zoom:50%;" /> 

  3. 查看**每个CPU使用情况**：输入top命令之后，按下`1`即可

     <img src="imgs\image-20211026170903284.png" alt="image-20211026170903284" style="zoom:50%;" /> 



### 7.12 uptime

- 打印**系统总共运行了多长时间**和**系统的平均负载**

  <img src="imgs\image-20211026171014825.png" alt="image-20211026171014825" style="zoom:50%;" /> 

- 参数

  -p：显示系统运行时长

  -s：显示系统何时运行的

   ```
  [root@192 home]# uptime -p
  up 1 hour, 50 minutes
  [root@192 home]# uptime --pretty
  up 1 hour, 51 minutes
   ```

### 7.13 find（查找文件、目录）

- 用来在指定目录下查找文件、目录...

- 语法：**find   查找目录 (选项)    查找内容**

- 选项

  **-name**：搜索文件名称

  **-type**：搜索的文件类型

  ```
  -type f: 搜索文件
  -type d: 搜索目录
  -type l: 搜索软连接
  ```

  

- 示例1：从根目录查找Hello.java文件

  `find / -name "Hello.java"`

- 示例2：从当前目录下查找以`.java`结尾的文件

  `find ./ -name "*.java"`

- 示例3：从/home目录下查找所有的**带java的文件**

  `[root@192 ~]# find /home -type f -name "*java"`

  <img src="imgs\image-20211026171515508.png" alt="image-20211026171515508" style="zoom:50%;" /> 

- 示例4：从/home目录下下查找**所有带java的目录**

  `[root@192 et1910]# find /home -type d -name "*java"`

### 7.14 netstat

- 打印Linux中网络系统的状态信息

- 参数

  ```
  -t: 显示使用TCP协议端口的连接状况；
  -p: 显示进程号和程序名称；
  -l: 显示处于监听状态的连接；
  -n: 使用IP和端口号显示，不使用域名与服务名；
  
  -u:显示udp连接
  ```


### 7.15 ps

- 查看进程状态

- 参数

  ```
  -e: 显示所有进程
  -f: 显示UID, PPIP等字段
  
  -----------------------
  UID     ：程序执行UID
  PID     ：进程的ID 
  PPID    ：父进程ID
  C       ：CPU使用的资源百分比
  STIME   ：启动时间
  TTY     ：终端机位置
  TIME    ：使用掉的CPU时间
  CMD     ：执行指令
  ```

  <img src="imgs\image-20211026172147136.png" alt="image-20211026172147136" style="zoom:50%;" /> 

  ```
  a: 显示一个终端所有的进程。
  u: 显示进程归属用户等
  x: 显示没有关联控制终端的进程
  ```

  <img src="imgs\image-20211026172106905.png" alt="image-20211026172106905" style="zoom:50%;" /> 

### 7.16 kill

- 终止进程
- `kill 进程号`
- `kill -9 进程号`：**强制终止进程**

### 7.17 cp：文件拷贝

- cp 【参数】 源文件  目标文件（目录）

- 拷贝文件

- 拷贝目录

  `-R\-r`：拷贝目录必须要使用的参数，同时可以递归拷贝

### 7.18 scp：远程拷贝（走ssh协议）

1. **拷贝文件到远程机器**

   <img src="imgs\image-20211026172817724.png" alt="image-20211026172817724" style="zoom:50%;" /> 

2. **递归拷贝目录远程机器**

   `-r:以递归的方式拷贝`

   <img src="imgs\image-20211026173048767.png" alt="image-20211026173048767" style="zoom:50%;" /> 

3. **拷贝远程文件到本地**

   `scp root@192.168.85.133:/home/et/hello.py ./` 

4. **拷贝远程目录到本地**

   <img src="imgs\image-20211026173231416.png" alt="image-20211026173231416" style="zoom:50%;" /> 

### 7.19 tar

- **压缩和解压**tar.gz文件

- 解压

  ```
  -z:通过gzip指令处理备份文件
  -x:提取文件
  -v:显示解压过程
  -f:指定解压文件
  -C:指定解压目录
  ```

  `tar -zxvf 文件名 [-C /opt]`

### 7.20 ln

- 创建**软连接**（相当于windows的快捷方式）

- -s：对源文件建立软连接

  <img src="imgs\image-20211026173650114.png" alt="image-20211026173650114" style="zoom:50%;" /> 

### 7.21 date

- 显示时间

  <img src="imgs\image-20211026173927857.png" alt="image-20211026173927857" style="zoom:50%;" /> 

- 以"年-月-日 时:分:秒"显示时间

  <img src="imgs\image-20211026173909017.png" alt="image-20211026173909017" style="zoom:50%;" /> 

- 使用`date "+%F"`显示年月日

  <img src="imgs\image-20211026173958070.png" alt="image-20211026173958070" style="zoom:50%;" /> 

- 显示昨天的日期

  <img src="imgs\image-20211026174131370.png" alt="image-20211026174131370" style="zoom:50%;" /> 


- 显示上一个月的今天今天

  <img src="imgs\image-20211026174224641.png" alt="image-20211026174224641" style="zoom:50%;" /> 


### 7.22 clear

- 清除屏幕内容：相当于windows中cls命令


### 7.23  shutdown

- 系统关机命令

- shutdown -h now：立即关机

- shutdown -h 17:00 "系统将在17:00关机"

  ```
  [root@192 ~]# shutdown -h 17:00 "系统将要关机"
  Shutdown scheduled for Wed 2020-04-08 17:00:00 CST, use 'shutdown -c' to cancel.
  
  [root@192 ~]# shutdown -c
  Broadcast message from root@192.168.189.130 (Wed 2020-04-08 16:32:36 CST):
  The system shutdown has been cancelled at Wed 2020-04-08 16:33:36 CST!
  ```



### 5.24 reboot

- 系统重启命令

## 8. 安装vim编辑器

`yum install -y vim`

## 9. 安装JDK 配置JDK

1. **解压jdk**

   `tar -zxvf jdk-8u201-linux-x64.tar.gz` 

2. 创建了一个软连接

   `ln -s jdk1.8.0_102 java`

3. 修改`/etc/profile`文件 在文件末尾添加这两行

   ```bash
   export JAVA_HOME=/usr/local/java
   export PATH=$PATH:$JAVA_HOME/bin
   ```

4. source /etc/profile

5. java -version



---



# 文件权限与修改问价拥有者()

## 1. 文件权限

- Linux系统对文件的权限有着严格的控制，如果要对某个文件执行某种操作（读、写、执行），必须具有对应的权限。
- 修改文件权限 - **chmod命令**
- 修改文件所属用户 - **chown命令**

## 2. 修改文件权限 - chmod命令

### 2.1 格式

- `chmod [-cfvR] [--help] [--version] mode file...`

### 2.2 常用参数说明

- **-R**：以**递归的方式**对当前目录下的所有档案与子目录进行相同的权限变更

- mode：`[ugoa...][[+-=][rwxX]...][,...]`

  1. **[ugoa...]**

     **u**：表示该文件的拥有者

     **g**： 表示与该文件的拥有者属于同一个组(group)的用户

     **o**：other，表示其他以外的人（不属于该用户和用户组）

     **a**：表示所有（包含上面三者）。

  2. **[+-=]**

     +：表示增加权限

     -：表示取消权限

     =：表示唯一设定权限

  3. **[rwxX]**

     r：表示可以读

     w：表示可以写入

     x：表示可以执行

     X：只有**当文件为目录**，或者其他类型的用户有可执行权限时，才将文件权限设置可执行

  4. **[file...]**

     文件列表（单个或者多个文件、目录）

### 2.3 八进制语法

- chmod命令可以使用八进制数来指定权限。

- 文件或目录的权限位是由9个权限位来控制，每三位为一组，它们分别是

  文件所属**用户**的读、写、执行，

  **用户组**的读、写、执行

  其**它用户**的读、写、执行。

  | 数字 | 权限           | rwx  | 二进制  |
  | :--- | :------------- | :--- | :------ |
  | 7    | 读 + 写 + 执行 | rwx  | **111** |
  | 6    | 读 + 写        | rw-  | 110     |
  | 5    | 读 + 执行      | r-x  | 101     |
  | 4    | 只读           | r--  | 100     |
  | 3    | 写 + 执行      | -wx  | 011     |
  | 2    | 只写           | -w-  | 010     |
  | 1    | 只执行         | --x  | 001     |
  | 0    | 无             | ---  | **000** |

  

### 2.4 举例

1. 设置a.sh文件的**所属用户**对该文件有执行权限

   ```bash
   chmod u+x a.sh
   ```

2. 增加**其它用户**对a.sh文件的写入和执行权限

   ```bash
   chmod o+w,o+x a.sh
   ```

3. 设置**所有用户**对a.sh文件拥有执行权限

   ```bash
   chmod ugo+x a.sh
   ```

   ```bash
   chmod a+x a.sh
   ```

4. 使用数字方式

   ```bash
   chmod 766 a.sh
   ```

  

## 3. 更改文件拥有者 - chown命令

​		Linux是多用户系统，每个的文件都有**拥有者**（所有者），如果我们想变更文件的拥有者（利用 chown 将文件拥有者加以改变），一般只有系统管理员(root)拥有此操作权限；

### 3.1 格式

- `chown [-cfhvR] [--help] [--version] user[:group] file...`

### 3.2 常用参数说明

- -R : 处理指定目录以及其子目录下的所有文件
- user : 指定要修改的所属用户
- group : 指定要修改的所属用户组
- file...：文件或目录



### 3.3 举例

1. 将文件a.sh**所属用户和用户组**修改为etoak

   ```bash
   chown etoak:etoak a.sh
   ```

   

2. 递归修改a目录所有文件或子目录的**所属用户和组**修改etoak

   ```bash
   chown -R etoak:etoak a
   ```





---



# day15(RabbitMQ消息队列day1)



## CentOS 7 安装单机版 RabbitMQ

```properties
1、上传3个rpm包
socat-1.7.3.2-2.el7.x86_64.rpm
esl-erlang_23.2.3-1_centos_7_amd64.rpm
rabbitmq-server-3.8.22-1.el7.noarch.rpm

2、安装socat
yum localinstall -y socat-1.7.3.2-2.el7.x86_64.rpm

3、安装erlang
yum localinstall -y esl-erlang_23.2.3-1_centos_7_amd64.rpm 

4、安装RabbitMQ
yum localinstall -y rabbitmq-server-3.8.22-1.el7.noarch.rpm

5、启动RabbitMQ
systemctl start rabbitmq-server


6、启动web控制台
rabbitmq-plugins enable rabbitmq_management


访问浏览器：192.168.244.128:15672


7、添加用户和密码（用户名et, 密码也是et）
rabbitmqctl add_user et et

8、设置角色(administrator)
rabbitmqctl set_user_tags et administrator

9、设置权限
rabbitmqctl set_permissions et -p / ".*" ".*" ".*"
```

---



## RabbitMQ课程内容

1. 消息中间件

2. 消息中间件使用场景

3. 消息中间件的发展历史

4. AMQP协议

5. RabbitMQ

   RabbitMQ概述

   RabbitMQ支持的模式

   RabbitMQ工作原理

6. RabbitMQ简单队列模式

7. RabbitMQ工作队列模式

8. RabbitMQ消息确认机制

9. RabbitMQ交换机

10. 死信交换机

11. 延迟交换机

12. 案例

    1、用户注册+邮件下发

    2、自动取消超时订单

## 1. 消息中间件

1. **中间件概念：**

   **中间件**是<u>一种独立的系统软件服务程序</u>，位于客户机服务器的操作系统之上，管理着计算资源和网络通信。

   分布式应用系统可以借助这种软件<u>在不同的技术之间共享资源</u>。

2. **消息中间件：**

   支持在分布式系统之间**发送**和**接收**消息的软件。 

## 2. 消息中间件使用场景

1. 应用解耦
2. 异步消息通信
3. 流量削峰
4. 日志处理（Kafka）

## 3. 消息中间件的发展

 <img src="imgs\1.消息中间件发展史-168494618412612.png" style="zoom:50%;margin-left:60px" /> 

## 4. AMQP协议（Advanced Message Queue Protocol）

- AMQP协议概念

  |       概念       | 描述                                                         |
  | :--------------: | ------------------------------------------------------------ |
  |    **Broker**    | **接收**和**分发**消息的应用，RabbitMQ Server就是Message Broker。 |
  | **Virtual Host** | 为了在一个单独的代理上实现多个隔离的环境（用户、用户组、交换机、队列 等），AMQP 提供了一个虚拟主机（virtual hosts）的概念，当多个不同的用户使用同一个RabbitMQ时，可以划分出多个vhost，每个用户在自己的vhost创建exchange、queue等。 |
  |  **Connection**  | **生产者**、**消费者**和**Broker**之间的**TCP连接**。        |
  |   **Channel**    | 是在Connection内部建立的**逻辑连接**，它作为轻量级的Connection，极大减少了操作系统建立TCP连接的开销。 |
  |   **Exchange**   | 交换机，用于**接收消息**，根据分发规则，匹配Routing Key，分发消息到队列中去。 |
  |    **Queue**     | 队列，消息最终被送到这里<u>等待消费者取走</u>。              |
  |   **Binding**    | 用于描述**消息队列**与**交换机**之间的关系。一个绑定就是基于路由键将交换机和消息队列连接起来的路由规则。因此可以将交换器看成一个由绑定构成的路由表 |
  | **Routing Key**  | 路由规则，可用来确定如何路由一个特定消息。                   |
  |   **Message**    | 消息                                                         |
  |  **Publisher**   | 消息发布者                                                   |
  |   **Consumer**   | 消费者                                                       |


## 5. RabbitMQ

1. [官网](https://www.rabbitmq.com)

   <img src="imgs\3.logo-168494618412613.jpg" style="zoom:30%; margin-left:20px" />

### 5.1 RabbitMQ概述

​		RabbitMQ拥有成千上万的用户，是最受欢迎的开源消息代理服务器之一；

​		RabbitMQ是一个开源的AMQP（**高级消息队列协议**）实现，服务器端用Erlang语言编写，支持多种客户端，如：Python、Ruby、.NET、Java、JMS、C、PHP、ActionScript、XMPP、STOMP等。**用于在分布式系统中存储、转发消息**。

### 5.2 RabbitMQ支持的模式

<img src="imgs\RabbitMQ6种模式-168494618412714.png" style="zoom:50%; margin-left:60px">

### 5.3 RabbitMQ工作原理图

<img src="imgs\RabbitMQ整体架构图-168494618412715.png" style="zoom:50%; margin-left:60px" /> 



<img src="imgs\RabbitMQ整体架构图2-168494618412716.png" style="zoom:50%; margin-left:60px" />

## 6. RabbitMQ简单模式

<img src="imgs\4.simple-16546460498501-168494618412717.png" style="zoom:50%; float:left; margin-left:50px" />

- P是**生产者**。
- C是消费者。
- 中间的红色区域是一个**队列**，表示消费者保留的**消息缓冲区**。

## 7. RabbitMQ工作队列模式

<img src="imgs\5.work-16461798076264-168494618412718.png" style="zoom:50%; margin-left:60px"/>

​		在使用消息系统时，一般情况下生产者往队列里插入数据的速度是比较快的，但是消费者消费数据往往涉及到一些业务逻辑处理导致速度跟不上生产者生产数据。因此，如果一个队列只有一个消费者的话，很容易导致**大量的消息堆积在队列里**，这时，就可以使用**工作队列**，这样一个队列可以有多个消费者同时消费数据。

​		当队列有多个消费者时，消息会被哪个消费者消费呢？这里主要有两种模式：

1. **轮询分发**：一个消费者消费一条，按均分配；
2. **公平分发**：根据消费者的消费能力进行公平分发，处理快的处理的多，处理慢的处理的少；按劳分配；

### 7.1 轮询分发

​		一个消费者消费一条，平均分配；

### 7.2 公平分发

​		根据消费者的**消费能力**进行公平分发，处理快的处理的多，处理慢的处理的少；

1. 消费者需要手动确认消息
2. 设置消费者每次取一条消息，**channel.basicQos(1);**

## 8. 消费者确认模式（ACK 机制）

1. 什么是消费者消息确认？

   为了确保消息不会丢失，RabbitMQ 支持 [消息确认](https://www.rabbitmq.com/confirms.html)，消费者发回确认消息，告诉 RabbitMQ 特定消息已被接收、处理，并且 RabbitMQ可以自由删除它。

2. RabbitMQ提供了两种确认模式

   - **自动确认**

   - **手动确认**

### 8.1 自动确认

​		自动确认表示消息**发送给消费者后立即确认**，但存在丢失消息的可能，<u>如果消费端消费逻辑抛出异常</u>，也就是消费端没有成功处理这条消息，那么就相当于<u>丢失了消息</u>；

### 8.2 手动确认

​		如果一个消费者在处理消息**出现了网络不稳定、服务器异常等现象**，那么就不会有ACK反馈，RabbitMQ会认为这个消息没有正常消费，此时，<u>RabbitMQ会将消息重新放入队列中</u>。

​		开启手动确认后，如果队列有多个消费者，当出现异常情况后，RabbitMQ会立即将这个消息推送给另一个在线的消费者，这种机制保证了在消费方不会丢失消息。

### 8.3 忘记确认消息

​		**忘记进行消息确认**是一个简单的错误，但后果很严重，当客户端（消费者）退出时，<u>消息将被重新传送</u>（这可能看起来像随机重新传送）到RabbitMQ队列，RabbitMQ会消耗越来越多的内存，因为它无法释放任何未确认的消息，此时就会造成**内存泄漏**。

## 9. RabbitMQ交换机

​		RabbitMQ消息传递模型的**核心思想**是：**生产者从不直接向队列发送任何消息**。实际上，生产者经常甚至根本不知道消息是否会被传送到队列，相反，生产者只能将消息发送到交换机。交换机一方面接收来自生产者的消息，另一方面将它们推送到队列中。

​		交换机必须确切地知道如何处理它收到的消息，它应该发送到特定队列中？还是应该发送到许多队列中？或者它应该被丢弃？**这由交换类型决定。**

### 9.1 交换机类型

​		交换机类型：`fanout`、`direct`、`topic`、`headers` 

### 9.2 fanout交换机（发布-订阅模式）

​		[教程地址](https://www.rabbitmq.com/tutorials/tutorial-three-java.html)

<img src="imgs\6.Pub-Sub-16461798076263-168494618412719.png" style="zoom: 67%; margin-left:40px" />

​		fanout交换非常简单，<u>它仅将收到的所有消息**广播**到所有队列</u>。

​		fanout交换机**不设置路由键**，我们只需要将队列绑定到交换机上，生产者发送到fanout交换机的消息都会被转发到与该交换机绑定的所有队列上，很像子网广播，每台子网内的主机都能获得了一份消息。 

#### 9.2.1 fanout交换机实例

 <img src="imgs\image-20230301151644011-168494618412720.png" alt="image-20230301151644011" style="zoom:50%; margin-left:60px" /> 


### 9.3 direct交换机（路由模式）

<img src="imgs\direct-168494618412722.png" style="zoom:50%; margin-left:60px" />

​		**交换机**和**队列**之间产生关联需要使用**binding**，可理解为交换机只向与其绑定的队列中发送消息，这里的绑定关系需要使用**Routing Key**；

​		相对比fanout交换机，<u>direct交换机</u>在其基础上，多加了一层秘钥（Routing Key）。

## 10. Topic交换机

​		topic交换机是在direct交换机的基础上，支持了对routing key的通配符匹配（`*`号和`#`号），以满足更加复杂的消息分发场景。

​		**`*`**：精确匹配一个词（一个字母也行、一个单词也行）

​		**`#`**：匹配零个或多个词（多个字母也行、多个单词也行，都以逗号分割）

**注：**

- 如果队列绑定的路由键是`#`，那么这个队列可以接收所有数据，就类似于<u>fanout交换机</u>了
- 如果队列绑定的路由键键当中没有`#`和`*`，那么是<u>direct交换机</u>了

### 10.1 topic交换机实例

<img src="imgs\topic-168494618412721.png" style="zoom:70%; margin-left: 60px" />

## 11. headers交换机

​		headers交换机与`fanout、direct、topic交换机`不同，它时通过匹配**AMQP消息的header**，而**不是路由键**。

​		在实际开发中这种方式很少使用。 



## 今天练习

1. 创建模块：email-service

   ```xml
   <!-- common工程 -->
   <!-- spring-boot-starter-web -->
   <!-- spring-boot-starter-amqp -->
   <!-- hutool -->
   <!-- lombok -->
   ```

2. 创建`application.yml`

   配置Tomcat端口号

   配置RabbitMQ

3. 编写启动类：com.etoak.EmailServiceApplication

4. 编写`com.etoak.service.EmailService`

   ```java
   @RabbitListener(queues = "email.queue")
   // 方法体 打印队列的消息
   ```


---

### pom.xml

```xml
    <!-- common -->
    <dependency>
      <groupId>com.etoak.et2301.mq</groupId>
      <artifactId>common</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>

    <!-- spring-boot-starter-web -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- spring-boot-starter-amqp -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>

    <!-- hutool-all -->
    <dependency>
      <groupId>cn.hutool</groupId>
      <artifactId>hutool-all</artifactId>
    </dependency>

    <!-- lombok -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>

    <!-- Spring Boot自动配置Java Mail -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
```

### application.yml

配置Tomcat端口号

配置RabbitMQ

```yaml
server:
  port: 8081

spring:
  rabbitmq:
    host: 192.168.244.128
    port: 5672
    virtual-host: /email
    username: et
    password: et

  mail:
    host: smtp.qq.com
    username: 2548316766@qq.com
    # 授权码
    password: jqfcuqfgzgplecih
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true
```

### 启动类com.etoak.EmailServiceApplication

```java
package com.etoak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmailServiceApplication.class, args);
  }
}
```

### com.etoak.service.EmailService

```java
package com.etoak.service;

import cn.hutool.json.JSONUtil;
import com.etoak.common.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  /**
   * 用于发送邮件的对象
   */
  @Autowired
  JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String senderMail;

  @RabbitListener(queues = "email.queue")
  public void send(String msg) {
    Email email = JSONUtil.toBean(msg, Email.class);

    // 创建邮件消息
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    // 发件人
    mailMessage.setFrom(senderMail);
    // 收件人
    mailMessage.setTo(email.getReceiver());
    // 主题
    mailMessage.setSubject(email.getSubject());
    // 正文
    mailMessage.setText(email.getContent());

    System.out.println("开始下发邮件");
    mailSender.send(mailMessage);
    System.out.println("邮件发送结束");
  }
}
```





---



# day16(RabbitMQ消息队列day2)



## 今天内容

1. 实现邮件下发
2. Topic交换机
3. 死信队列
4. 延迟队列
5. 使用延迟队列实现订单的自动取消

## 1. 实现邮件下发

1. 开通QQ邮箱邮件发送功能（第三方发送邮件的能力）

   <img src="imgs\image-20230524092537297-168494914897335.png" alt="image-20230524092537297" style="zoom:33%;" /> 

2. 在pom.xml中引入：`spring-boot-starter-mail`依赖

3. 在application.yml中配置邮箱主机、授权码等

   ```yaml
   server:
     port: 8081
   
   spring:
     rabbitmq:
       host: 192.168.244.128
       port: 5672
       virtual-host: /email
       username: et
       password: et
   
     mail:
       host: smtp.qq.com
       username: 2548316766@qq.com
       # 授权码
       password: jqfcuqfgzgplecih
       properties:
         mail:
           smtp:
             auth: true
             starttls:
               enable: true
               required: true
   ```

## 2. Topic交换机

​		topic交换机是在direct交换机的基础上，支持了对routing key的通配符匹配（`*`号和`#`号），以满足更加复杂的消息分发场景。

​		**`*`**：精确匹配一个词（一个字母也行、一个单词也行）

​		**`#`**：匹配零个或多个词（多个字母也行、多个单词也行，都以`.`分割）

**注：**

- 如果队列绑定的路由键是`#`，那么这个队列可以接收所有数据，就类似于fanout交换机了
- 如果队列绑定的路由键键当中没有`#`和`*`，那么是direct交换机了

### 2.1 topic交换机实例

<img src="imgs\topic-168494914893934.png" style="zoom:70%;" > 

## 3. 死信队列

1. [死信队列官网](https://www.rabbitmq.com/dlx.html)

2. 按照前边讲的内容：生产者（Producer）会将消息投递到交换机（Exchange），消费者（Consumer）从队列（Queue）中取出消息进行消费；

   但有些时候由于特定的原因导致队列中的消息<u>无法被消费</u>，这样的消息如果没有后续的处理，就变成了"**死信**"。RabbitMQ可以配置**死信队列接收这些死信消息**，如果不配置死信队列，那么这条消息将会被丢弃。

3. 死信出现的原因：

   1. **消息被消费者拒绝（basicReject或basicNack，并且没有重新入队）**

   2. **消息过期（The message expires due to [per-message TTL](https://www.rabbitmq.com/ttl.html)）**

   3. **由于队列达到最大长度，队首的消息被丢弃**

      **注意：队列满了之后，如果继续向队列中发送消息，那么队首的消息就会成为死信**

### 3.1 消息的TTL（Time To Live） 和 队列的TTL

1. [消息的TTL 和 队列的TTL官网](https://www.rabbitmq.com/ttl.html)

2. 队列中消息的**TTL**：创建队列时统一设置队列上所有消息的过期时间

   ```java
   // 原生API设置队列上消息的过期时间
   Map<String, Object> args = new HashMap<String, Object>();
   args.put("x-message-ttl", 60000);
   channel.queueDeclare("myqueue", false, false, false, args);
   ```

   ```java
   // Spring Boot中设置队列上消息的过期时间
   @Bean
   public Queue normalQueue() {
     // 设置死信交换机和死信routing key
     Map<String, Object> args = new HashMap<>();
     // 队列上的消息的过期时间
     args.put("x-message-ttl", 1000 * 10);
     return new Queue(NORMAL_QUEUE, true, false, false, args);
   }
   ```

   

3. 队列的过期时间：**创建队列时设置**

   ```java
   Map<String, Object> args = new HashMap<String, Object>();
   // 80秒后队列直接被Broker删除, 队列中的消息也会被删除
   args.put("x-expires", 1000 * 80);
   channel.queueDeclare("myqueue", false, false, false, args);
   ```

   

4. 消息的TTL：**发送消息时设置消息过期时间**

   ```java
   byte[] messageBody = "Hello, world!".getBytes();
   AMQP.BasicProperties properties = new AMQP.BasicProperties（）
     											.builder()
                                     .expiration("60000")
                                     .build();
   channel.basicPublish("my-exchange", "routing-key", properties, messageBody);
   ```

   ```java
   rabbitTemplate.convertAndSend(DeadLetterConfig.NORMAL_EXCHANGE,
           DeadLetterConfig.NORMAL_KEY,
           msg,
           message -> {
             // 设置消息的过期时间  单位是ms
             String expire = String.valueOf(1000 * second);
             message.getMessageProperties().setExpiration(expire);
             return message;
           }
         );
   ```


### 3.2 死信队列 - 测试消息过期（消息的TTL到期）

<img src="imgs\image-20220609170103834-168494914897336.png" style="zoom:50%; margin-left:30px" />

## 4. 延迟队列

​		延迟队列存储的消息一般都是`延时消息`，所谓`延时消息`是指当消息被发送以后，并不想让消费者立即消费消息，而是**等待指定时间后**，才允许消费者来消费这条消息；

### 4.1 应用场景

1. 用户下单后30分钟未支付，使用延迟队列功能**取消**超时的订单
2. 注册一个网站，24小时（几天）内未登录，发送邮件或短信通知

## 5. 使用死信队列实现延迟队列功能

- 消息的过期时间确定：可以使用死信队列完成延迟队列功能
- 缺点：消息的过期时间不固定时，<u>过期时间早的不一定先被处理掉</u>


## 6. 安装延迟队列插件

1. 插件安装目录：`/usr/lib/rabbitmq/lib/rabbitmq_server-3.8.22/plugins`

2. 将`rabbitmq_delayed_message_exchange-3.8.0.ez`插件上传到插件安装目录

3. 安装命令，在plugins目录下执行：

   ```bash
   [root@192 plugins]# rabbitmq-plugins enable rabbitmq_delayed_message_exchange
   ```

4. 安装完成之后，RabbitMQ的Web控制台**Exchanges选项卡**下会出现一个交换机 ，如下图所示

   <img src="imgs\image-20220708165238654-168494914897337.png" style="zoom:50%;" /> 

## 7. 配置延迟队列



## 8. 使用延迟队列取消超时未支付的订单

1. 创建表

   ```sql
   CREATE TABLE `t_order` (
     `id` bigint NOT NULL AUTO_INCREMENT,
     `order_no` varchar(128) NOT NULL COMMENT '订单编号',
     `user_id` varchar(64)  NOT NULL COMMENT '用户id',
     `shop_id` varchar(64) NOT NULL COMMENT '商铺id',
     `order_status` int NOT NULL DEFAULT '0' COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
     `pay_status` int NOT NULL DEFAULT '0' COMMENT '支付状态: 0:未支付，1:已支付，2:取消支付',
     `cancel_reason` varchar(255) DEFAULT NULL COMMENT '取消原因',
     `create_time` datetime NOT NULL COMMENT '创建时间',
     `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `idx_unique_order_no` (`order_no`) USING BTREE
   ) ;
   ```

2. 创建工程

   rabbitmq-order         pom

   common                     ResultVO

   entity                          实体类工程

   mapper                       Mapper工程

   order-service              订单服务（Spring Boot）

   cancel-service             取消超时订单服务（Spring Boot）

   

---



---

# day17(Redis)

## Redis（Remote Dictionary Server）

1. NoSQL概述
2. Redis概述、使用场景
3. Redis安装（Windows、CentOS7）
4. Redis多数据库特点
5. Redis常用全局命令
6. Redis常用数据类型
7. 使用Jedis、spring-data-redis、Spring Boot连接单机版Redis
8. 简单的点赞接口开发
9. Redis持久化方式
10. Redis事务
11. Redis集群模式
    - 主从模式
    - 哨兵模式
    - 分片集群模式（在CentOS7上安装）
12. 使用Jedis、spring-data-redis、Spring Boot连接集群Redis
13. 简单排行榜接口开发

- Redis官网：https://redis.io
- Redis中文网：http://www.redis.cn
- Redis所属公司：Pivotal（Spring所在的公司）
- Redis源码地址：https://github.com/antirez/redis

## 1. NoSQL - Not Only SQL

​	**MySQL**、**Oracle**、**DB2**：**关系型数据库**

​		`结构化数据`：使用关系型数据库表示和存储，表现形式就是**二维行列结构**；

​	**NoSQL**：**存储非结构化和半结构化数据**

​		`非结构化数据`：没有固定存储结构，文件、图片、音视频等

​		`半结构化数据`：可以认为是结构化数据的一种，只不过不是以二维行列形式存储，但是数据包含**相关标记**，比如JSON、XML；

### 1.1 NoSQL数据库类型

1. **键值对数据库**（Key-Value数据库）

   代表数据库：Redis、Memcached

2. **列式数据库**

   代表数据库：谷歌的BigTable、Apache的HBase

3. **文档数据库**

   这种数据库存储半结构化数据：一般存储为**json格式**，这种数据库更接近关系型数据库；

   代表数据库：`MongoDB`、`ElasticSearch（ES）`

4. **图数据库**

   并不是存储图片，而是存储数据间关系；

   适合做社交网络、关系图谱；

   代表数据库：`Neo4J`、`OrientDB`

## 2. Redis概述

1. Redis是一个`键值对类型`的**内存数据库**；
2. Redis支持**字符串、哈希、列表、集合、有序集合**等数据结构；
3. Redis具有内置的**数据复制、回收、事务和不同级别的磁盘上的持久性**；
4. Redis是**纯内存操作**，具备很高的读写性能；读的速度能达到110000次/s，写的速度能达到81000次/s；
5. Redis支持**主从模式的数据备份**，数据可扩展性高；
6. Redis支持**集群**（Redis 3.0+）；

### 2.1 Redis的使用场景

1. **缓存**：缓存一些常用数据、缓存Session会话等
2. **排行榜**
3. **简单的消息队列**
4. **好友关系**：点赞、好友推荐等
5. ......

## 3. 安装Redis

​		Redis官方只提供了Linux的安装版本，不提供Windows的安装版本，但是微软使用Redis源码编译了一个Windows版的Redis安装包；

​		Window64位下载地址：https://github.com/MicrosoftArchive/redis/releases

​		其他开发者编译的Windows版本：https://github.com/tporadowski/redis/releases/

### 3.1 Windows安装Redis

1. 解压redis安装文件

2. **启动redis服务**：双击`redis-server.exe`即可

3. **或命令行方式启动redis服务**

   <img src="imgs\Windows启动Redis.png" alt="Windows启动Redis" style="zoom:50%; margin-left:0px;" /> 

   

4. 启动redis客户端

   `双击redis-cli.exe`

### 3.2 Centos7 安装Redis 5.0.4

1. 安装gcc-c++

   `yum install -y gcc-c++`

2. 上传安装包`redis-5.0.4.tar.gz`到`/opt`目录

3. 解压`redis-5.0.4.tar.gz`到`/opt`目录

   `tar -zxf redis-5.0.4.tar.gz [-C /opt]`

4. 编译安装Redis

   ```bash
   # 软连接
   [root@localhost opt]# ln -s redis-5.0.4 redis
   [root@localhost opt]# cd redis
   # 运行代码
   [root@localhost redis]# make
   [root@localhost redis]# cd src
   # 安装
   [root@localhost src]# make install
   ```

   > `CC Makefile.dep`
   >
   > `Hint: It's a good idea to run 'make test' ;)`
   >
   > `INSTALL install` 
   >
   > `INSTALL install` 
   >
   > `INSTALL install`
   >
   > `INSTALL install`
   >
   > `INSTALL install`

5. 修改配置文件(`/opt/redis/redis.conf`)

   1. bind 127.0.0.1               注释这一行（69行）

   2. protected-mode  no     (将保护模式修改为no)（88行）

   3. daemonize yes              (设置为守护进程，把no改为yes)（136行）

      ```bash
      sed -i '/^bind 127.0.0.1/s/bind 127.0.0.1/#bind 127.0.0.1/g' redis.conf
      sed -i '/protected-mode/s/yes/no/g' redis.conf
      sed -i '/daemonize/s/no/yes/g' redis.conf
      ```

6. 启动redis server

   `cd /opt/redis`

   `redis-server redis.conf`

   

7. 启动redis-cli客户端

   **redis-cli**

   `redis-cli [-h 127.0.0.1 -p 6379 -a 密码]`

   > -h：host，连接主机，默认连接127.0.0.1
   >
   > -p：port，redis服务器端口号，默认连接端口是6379
   >
   > -a：auth，redis登录密码

   

8. 停止redis服务端

   `redis-cli shutdown`

## 4. Redis的多数据库特点

- Redis默认支持`16个数据库`，数据库名称使用数字表示，下标从0开始（0-15）
- 可以通过redis.conf中的`databases`配置

### 4.1 Redis多数据库缺点

1. 不支持自定义**数据库名称**。
2. 不支持为每个数据库设置**访问密码**。
3. 多个数据库之间**不是完全隔离的**，`flushall`命令会清空所有数据库的数据。 
4. 不适用于存储**不同应用的数据**。
5. **Redis集群**不支持多数据库。

## 5. Redis常用的全局命令

1. `keys *`：**查看所有的键**，支持通配符查询，如：`keys name*`，生产环境最好不要使用（它会<u>阻塞服务器</u>）。
2. `exists key`：测试**是否存在**指定的key，存在返回1，不存在返回0
3. **`expire key seconds`：设置key的过期时间，单位是秒**
4. `ttl key`：查看**key的剩余有效时间**，`-1`代表**永不过期**，`-2`表示key**已经过期**
5. `persist key`：取消key的过期时间
6. `del key`：删除某个key
7. `rename key newkey`：修改key的名称
8. `flushall`：清空所有数据（<u>会跨库清空数据</u>）
9. `info [section]`：查看数据信息，info Server，info CPU、info Replication

## 6. Redis常用的五个数据类型

### 6.1 string类型

​		**string类型**是redis中最基本的类型，并且是**二进制的安全的**，可以存储序列化的对象、二进制图片、一个简单的字符串、数值等。string类型的键允许存储的数据**最大容量是512MB**。

### 6.2 hash类型

​        hash是一个string类型的**field和value的映射表**，适合存储**对象**，将一个对象存储在hash类型中会占用**更少的内存**，而且可以方便的操作对象。

### 6.3 list类型

​        list是一个**链表结构**，类似JDK的LinkedList、Queue，list类型的每个子元素都是<u>string类型</u>的**双向链表**；

### 6.4 set类型

​       set是string类型的**无序且不重复的集合**。Set是通过hash table实现的，添加、删除和查找的复杂度都是**O(1)**，可以对set取`并集、交集、差集`，通过set的这些操作我们可以实现好友推荐功能。

### 6.5 zset类型

​        zset**有序集合**，类似SortedSet。

## 7. Jedis连接单机版Redis

## 8. spring data redis连接单机版Redis

## 9. Spring Boot连接单机版Redis

​	Spring Boot完成了**Lettuce的自动配置**，自动配置类：`RedisAutoConfiguration`

<img src="imgs\image-20230525154536130.png" style="zoom:50%; margin-left:40px" /> 

### 开发点赞接口

1. 接口地址：http://localhost:8080/like/news?id=id&username=zs

2. 请求方法：`get`、`post`

3. 请求参数

   id：新闻id

   username：点赞用户名称

4. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": {
       "like": true, 
       "count": 200
     }
   }
   ```

5. 怎么实现？用什么数据类型？

   点赞、取消、获取点赞量

   什么情况下能点赞？当前用户没有点过赞时

   什么情况下能取消点赞？当前用户点过赞了

   所以，在点赞和取消之前应该有一个判断 - 判断用户有没有点过赞

   **hash类型：**

   key：`news:newId`

   value：`<username, 1>`

   `hexists news:100 zs`

   `hset news:100 zs 1`

   `hdel news:100 zs`

   `hlen news:100`

   **set类型：**

   key：`news:newId`

   value：`username`

   `sismember news:100 zs`

   `sadd news:100 zs`

   `srem news:100 zs`

   `scard news:100`

## 10. Redis的<u>持久化策略</u>

​	Redis提供了两种持久化策略：`RDB` 和 `AOF`

### 10.1 RDB持久化 - 默认持久化方式

1. **快照文件名称设置**

   通过`redis.conf`中的`dbfilename`设置

   默认值：`dbfilename "dump.rdb"`

2. **快照文件存放位置**

   通过`redis.conf`中的`dir`设置

   默认值：`dir ./` 表示在执行redis-server命令的目录下生成文件，当然可以修改

3. **快照（RDB持久化）策略配置**

   ```
   save 900 1    900秒内有一个修改就执行持久化
   save 300 10   300秒内有10个修改就执行持久化
   save 60 10000 60秒内有一万个修改就执行持久化
   ```

   增加4秒内有一个可以被修改就触发RDB持久化



#### 10.1.1 RDB持久化过程

​		**RDB持久化**是Redis定期将内存中的`数据集`写入磁盘，操作过程：主进程创建一个子进程，先将数据集写入临时文件，写入成功后，再替换之前的文件，使用二进制压缩存储，整个过程`主进程`不进行任何IO操作，保证Redis的高效性。

#### 10.1.2 RDB持久化的触发方式

​	`自动触发`和`手动触发`

1、**自动触发**（默认配置）：在redis.conf中配置

​	1271:M 25 May 2023 16:55:45.410 * 1 changes in 5 seconds. Saving...
​	1271:M 25 May 2023 16:55:45.418 * Background saving started by pid 1297
​	**1297:C 25 May 2023 16:55:45.419 * DB saved on disk**
​	1297:C 25 May 2023 16:55:45.419 * RDB: 4 MB of memory used by copy-on-write
​	1271:M 25 May 2023 16:55:45.521 * Background saving terminated with success

2、**手动触发**

- **save**

  执行此命令会阻塞Redis服务器，执行命令期间，Redis不能处理其它命令，直到RDB过程完成为止。

  **执行日志如下**

  1271:M 25 May 2023 16:59:40.310 * DB saved on disk

- **bgsave**

  ​	执行该命令时，Redis会在后台**异步**进行快照操作，做快照的同时还可以响应客户端请求；此时Redis主进程执行**fork**操作创建子进程，RDB持久化过程由子进程负责，完成后自动结束。阻塞只发生在fork阶段，一般时间很短。


### 10.2 AOF持久化：默认没有开启这个持久化策略

​		AOF持久化以`日志的形式`记录服务器所处理的每一个**写操作和删除操作**（以文本的方式记录，可以打开文件看到详细的操作记录）。

1. **如何开启aof持久化**

   使用`redis.conf`中的`appendonly yes`设置

   默认设置：`appendonly no`表示不开启aof持久化

   

2. **aof文件名称设置**

   使用`redis.conf`中的`appendfilename`设置

   默认设置：`appendfilename "appendonly.aof"`

   

3. **aof文件存放位置**

   通过`redis.conf`中的`dir`设置

   默认值：`dir ./` 表示在执行redis-server命令的目录下**生成文件**

#### 10.2.1 Redis支持三种同步AOF文件的策略

​	通过redis.conf中的`appendfsync设置`

1. `no`: 不进行同步，依靠Redis来进行同步. Faster. 

2. `always`: always表示每次有写操作都进行同步. Slow, Safest. 

3. `everysec`: 表示对写操作进行累积，每秒同步一次. Compromise.

   默认是"everysec"，按照速度和安全折中这是最好的。

## 11. Redis的事务

​		Redis对事务的支持比较简单，它是一组命令的集合，命令被**顺序的执行**，Redis也可以放弃事务的执行，此时事务中所有的命令都不会执行。

​		Redis的事务`没有隔离级别`的概念，因为事务提交前任何指令都不会被实际执行，也就不存在“事务内的查询要看到其它事务里的更新，在事务外查询不能看到”这种问题了。

​		Redis的事务`不保证原子性`，也就是不保证所有指令同时成功或同时失败，只有决定是否开始执行全部指令的能力，**没有执行到一半进行回滚的能力**。

​		`Redis集群不支持事务操作`；

### 11.1 Redis事务的相关命令

1. `multi`：**开启事务**
2. `exec`：**执行事务**
3. `discard`：**取消事务**
4. `watch`：**监控键值**
5. `unwatch`：**取消监控**

### 11.2 Redis事务的基本过程   multi

1. 发送一个事务的命令`multi`给redis； 
2. 依次把要执行的命令发送给Redis，Redis接到这些命令，**并不会立即执行**，而是放到**等待执行的事务队列**里；
3. 发送执行事务的命令`exec`给Redis；
4. Redis会保证**一个事务内的命令依次执行**，而**不会被其它命令插入**； 

### 11.3 Redis事务过程中的错误处理

​	Redis的事务非常简单，当然会存在一些问题。

​		Redis只能保证事务的每个命令连续执行，但是如果事务中的**一个命令失败了**，**并不回滚其他命令**，下面举例看错误处理；

1. **如果是某个命令执行错误（使用方式错了），那么其它的命令仍然会正常执行，然后在执行后返回错误信息；**

   <img src="imgs\16464405071501.png" style="zoom:40%;" />  

   

2. **如果任何一个命令的语法有错，redis会直接返回错误，所有的命令都不会执行**

   <img src="imgs\16464405071512.png" style="zoom:40%;" /> 

`注意`: **Redis不提供事务回滚的功能，开发者必须在事务执行出错后，<u>自行恢复数据库状态</u>；**

### 11.4 Redis事务扩展 - 乐观锁

​		Redis使用`watch`来提供乐观锁定；

​		当`exec`被**调用后**，**所有的之前被监视的键**会被**取消监视**，不管事务是否被取消或执行。并且当客户端**连接丢失**的时候，所有键都会被取消监视。

#### 11.4.1 乐观锁介绍

​		乐观锁大多是基于**数据版本（version）**的**记录机制**实现的。

​		什么是数据版本？

​		**数据版本**就是为数据增加一个<u>版本标识version</u>，更新数据时，<u>对此版本号加 1</u>，当线程A要更新数据值时，在读取数据的同时也会读取version值，在提交更新时，若刚才读取到的version值**大于**当前数据库中的version值时才更新，否则更新失败。

#### 11.4.2 乐观锁举例

​	小明的账户有余额1000；

1. 操作员A将小明的信息读出（此时 version=1），并准备从其帐户余额中扣除100（1000-100）； 剩余900
2. 在操作员A操作的过程中，操作员B也读入小明的信息（此时 version=1），并准备从其帐户余额中扣除500（1000-500）； 剩余500
3. A完成了修改工作后，将数据版本号加 1（此时 version=2），帐户扣除后余额为900，提交至数据库更新，此时由于提交数据版本大于数据库记录当前版本（2 > 1），数据被更新，数据库记录 version 更新为 2;
4. 在A完成更新操作之后，B也完成了操作，她也将版本号加 1（version=2）并试图向数据库提交数据（此时小明的余额为500），但比对数据库记录版本时发现，B提交的数据版本号为 2，数据库记录当前版本也为 2，不满足**提交版本必须大于记录当前版本才能执行更新**的<u>乐观锁策略</u>，因此，B的提交被驳回；

#### 11.4.3 Redis的乐观锁测试

1. 设置balance为1000，并使用watch监控balance

   <img src="imgs\16464405071516.png" style="zoom: 50%;" /> 

   

2. 第一个客户端开启事务，并将balance修改为900，但是没有执行事务

   <img src="imgs\16464405071514.png" style="zoom: 50%;" /> 

   

3. 第二个客户端直接把balance修改为500

   <img src="imgs\16464405071513.png" style="zoom: 50%;" /> 

   

4. 第一个客户端再次执行exec，结果没有修改成功

   <img src="imgs\16464405071515.png" style="zoom: 50%;" /> 



--------



# day18(上午Redis、下午Dubbo)

## 上午内容：

## 1. Redis集群模式

​	Redis集群模式：`主从模式`、`哨兵模式`、`分片集群模式`

### 1.1 Redis的主从模式

​		Redis的**主从模式**，使用**异步复制**，<u>从节点</u>异步从<u>主节点</u>复制数据，**主节点**提供<u>读写服务</u>，**从节点**只提供<u>读服务</u>（这个是默认配置，可以通过修改配置文件`replica-read-only`控制）。

   主节点可以有多个从节点。

​	**配置**一个从节点只需要在<u>redis.conf文件</u>中指定：

​	`replicaof master-ip master-port`

#### 1.1.1 主从复制配置

​	一主一从、一主多从、一主一从一从...

#### 1.1.2 主从复制基本过程

1. **Slave**启动时，向Master发送**SYNC命令**，以实现<u>全量复制</u>。 
2. **Mater(主库)**接到SYN请求，会在后台**保存快照**（也就是进行**RDB持久化**），`并将快照期间接收到命令缓存起来`。
3. 快照完成后，Master(主库)会将<u>快照文件和所有的缓存的命令</u>发送给Slave(从库)。 
4. Slave(从库)接收后，会<u>载入</u>快照文件并执行缓存的命令，从而完成<u>复制的初始化</u>。 
5. <u>在数据库使用阶段</u>，Master(主库)会**自动**把每次收到的**写命令**<u>同步</u>到从服务器。

#### 1.1.3 主从模式配置

1. **主节点配置**

   ```
   bind 127.0.0.1      # 注释掉
   protected-mode no   # 关闭保护模式(将yes改为no)
   daemonize yes       # 守护进程模式开启（将no改为yes）
   logfile "/opt/redis/redis.log"
   ```

2. **从节点配置**

   ```
   bind 127.0.0.1       # 注释掉
   protected-mode no    # 关闭保护模式
   daemonize yes        # 守护进程模式开启
   replicaof 主节点ip 主节点端口号 # replicaof 192.168.1.128 6379
   ```

### 1.2 Redis哨兵模式

1. 主从方式有个问题，就是`Master(主库)`挂了之后，**无法重新选举**新的节点作为主节点进行写操作，导致服务不可用。

2. Redis提供了`哨兵工具`来实现监控Redis系统的运行情况，能够实现如下功能：

   > 1、监控主从数据库运行是否正常。
   >
   > 2、当主数据库出现故障时，自动将从数据库转换为主数据库。

#### 1.2.1 哨兵配置

- 配置`/opt/redis/sentinel.conf文件`

  ```
  daemonize yes     # 改为yes
  logfile  /opt/redis/sentinel.log
  sentinel monitor mymaster 192.168.85.128 6379 1
  sentinel down-after-milliseconds mymaster 5000   # 改为5s，默认30s
  ```

- **启动哨兵**：`redis-sentinel /opt/redis/sentinel.conf`

- **进入哨兵**：`redis-cli -h 192.168.1.128 -p 26379`

- **查看哨兵信息**：`redis-cli -h 192.168.1.128 -p 26379 info Sentinel`

### 1.3. Redis分片集群

目前比较流行的**Redis集群方案**

|       系统        |    贡献者     | 编程语言 |
| :---------------: | :-----------: | :------: |
|     Twemproxy     |    Twitter    |    C     |
| **Redis Cluster** | **Redis官方** |  **C**   |
|       Codis       |    豌豆荚     |  Go、C   |

- **Redis集群**是一个提供在<u>多个Redis节点间</u>共享数据的<u>程序集</u>。
- Redis集群并`不支持处理多个key的命令(比如mset、mget、sdiff、sinter、sunion等)`，因为这需要在不同的节点间<u>移动数据</u>，从而达不到像Redis那样的性能，在高负载的情况下可能会导致不可预料的错误。
- Redis集群通过**分区**来提供一定程度的可用性，在实际环境中当某个节点<u>宕机</u>或者<u>不可达</u>的情况下继续处理命令。

#### 1.3.1 Redis 集群的优势 

- 自动分割数据到不同的节点上。
- 整个集群的<u>部分节点失败</u>或者<u>不可达</u>的情况下能够继续处理命令。

### 1.3.2 Redis 集群的数据分片

​	Redis集群引入了**哈希槽**的概念。 

​	Redis 集群有`16384个哈希槽` (2^14)，每个key通过`CRC16校验`后对16384**取模**来决定放置哪个槽。集群的每个节点负责一部分hash槽。

>  举个例子：比如当前集群有3个主节点，那么：
>
>  节点 A 包含 0 到 5500号哈希槽。
>
>  节点 B 包含5501 到 11000 号哈希槽。
>
>  节点 C 包含11001 到 16383号哈希槽。 

​	**这种结构很容易添加或者删除节点**：比如我们想新添加个节点D, 我们需要将节点 A、B、 C中的部分槽移动到D上。如果我想移除节点A，需要将A中的槽移到B和C节点上。然后将<u>没有任何槽的A节点从集群中移除</u>即可。由于从一个节点将哈希槽**移动**到另一个节点并不会停止服务，所以无论**添加删除**或者**改变**某个节点的哈希槽的数量都不会造成集群**不可用**的状态。

#### 1.3.3 Redis集群安装

​	**安装节点数：**必须有3个或3个以上的主节点，所以，采用3主3从方式安装；

​	**安装方式：**在一台机器上安装6个节点（3主3从）

​	**安装步骤：**

1. 在/opt目录下创建`cluster`目录

2. 在/opt/cluster下创建6个目录，分别是6001....6006

3. 拷贝一个新的redis.conf文件到/opt/cluster/6001目录下

4. 修改redis.conf

   ```
   1. bind 本机的ip地址    # 绑定ip（69行）
   2. protected-mode no  # 关闭保护模式（88行）
   3. port 600*          # 端口号为6001 - 6006（92行）
   4. daemoinze yes      # 开启守护进程模式（136行）
   5. pidfile "/var/run/redis_600*.pid"  (158行)
   6. logfile /opt/cluster/600*/redis.log  (171行)
   7. dir /opt/cluster/600*  # 持久化文件的默认保存位置、集群配置文件(263行)
   8. cluster-enabled yes   # 启动集群模式（去掉注释 838行）
   9. cluster-config-file nodes-600*.conf # 集群自动生成的文件，自动写到dir目录
   ```

5. 按顺序启动每一个节点

   ```
   redis-server /opt/cluster/6001/redis.conf
   redis-server /opt/cluster/6002/redis.conf
   redis-server /opt/cluster/6003/redis.conf
   redis-server /opt/cluster/6004/redis.conf
   redis-server /opt/cluster/6005/redis.conf
   redis-server /opt/cluster/6006/redis.conf
   ```

   

6. 使用`redis-cli`命令创**建集群**（只需要执行一次）

   `redis-cli --cluster create 192.168.136.129:6001 192.168.136.129:6002 192.168.136.129:6003 192.168.136.129:6004 192.168.136.129:6005 192.168.136.129:6006 --cluster-replicas 1`

   **注意**：这个创建集群的命令只需要执行一次，下次启动时，只需要执行`第五步`的命令即可

7. 登录集群

   `redis-cli -c -h 192.168.85.128 -p 6001` 

   > `-c`: 以集群的方式连接redis 
   >
   > `-h`: 连接的主机（host） 
   >
   > `-p`: 端口号（port）

    计算key的hash槽的槽号：`cluster keyslot key的名称`

## 2. 使用spring-data-redis连接Redis集群

1. 单机版配置

   RedisStandaloneConfiguration => ip和port

   JedisConnectionFactory

   StringRedisTemplate

2. 集群版本配置

   `Set<RedisNode>`

   RedisClusterConfiguration

   JedisPoolConfig

   JedisConnectionFactory

   StringRedisTemplate

## 3. 使用ZSET开发排行榜接口

### 3.1 添加英雄接口

1. 接口地址：http://localhost:8080/hero/{name}

2. 请求方法：`POST、GET`

3. 请求参数：name

4. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": ""
   }
   ```

### 3.2 查询所有英雄的排行、或者是TOP3

1. 接口地址：http://localhost:8080/hero/index?start=0&end=2

2. 请求方法：`get`

3. 请求参数

   start：起始下标，非必填，默认0

   end：结束下标，非必填，默认-1

4. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": [
       {
         "name": "吕布",
         "power": 900
       },
        {
         "name": "赵云",
         "power": 800
       }
     ]
   }
   ```

### 3.3 查询武力值在500 - 1000之间的英雄排行

1. 接口地址：http://localhost:8080/hero/power?min=500&max=1000

2. 请求方法：`get`

3. 请求参数

   min：最小武力值，非必填，默认500

   max：最大武力值，非必填，默认1000

4. 响应结果

   ```json
   {
     "code": 200,
     "msg": "success",
     "data": [
       {
         "name": "吕布",
         "power": 900
       },
        {
         "name": "赵云",
         "power": 800
       }
     ]
   }
   ```

   

---

## 下午内容：

# Dubbo课程

1. Dubbo发展背景

2. Dubbo概述
3. RPC
4. Zookeeper、Dubbo控制台的安装
5. Dubbo入门例子
6. Dubbo详细讲解
7. Dubbo整合Spring
8. Dubbo整合Spring Boot

****

​	Dubbo发音：`|ˈdʌbəʊ|`

​	Dubbo官方网站：`http://dubbo.apache.org`

​	Dubbo是阿里巴巴开发的，已经贡献给Apache，并且成为Apache顶级开源项目

<img src="imgs\0.dubbo称为Apache顶级开源项目-16850993555171.png" alt="0.dubbo称为Apache顶级开源项目" style="zoom:30%; margin-left: 60px" />   

## 1. Dubbo

### 1.1 Dubbo是什么

**第一版网站首页介绍：**

​        Apache Dubbo™ 是一款高性能**`Java RPC框架`**。

​        Apache Dubbo |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC框架，它提供了三大核心能力：`面向接口的远程方法调用`，`智能容错和负载均衡`，以及`服务自动注册和发现`。

**第二版网站首页介绍：**

​		Apache Dubbo 是一款高性能、轻量级的开源**`服务框架`**。

​		Apache Dubbo |ˈdʌbəʊ| 提供了六大核心能力：**面向接口代理的高性能RPC调用，智能容错和负载均衡，服务自动注册和发现，高度可扩展能力，运行期流量调度，可视化的服务治理与运维**。

**第三版网站首页介绍：**

​		Apache Dubbo 是**`一款微服务框架`**，为大规模微服务实践提供高性能 **RPC 通信、流量治理、可观测性**等解决方案，涵盖 Java、Golang 等多种语言 SDK 实现。

**第四版网站首页介绍：**

​		Apache Dubbo 是一款**易用、高性能的 WEB 和 RPC 框架**，同时为构建企业级微服务提供**服务发现、流量治理、可观测、认证鉴权等能力、工具与最佳实践**。

### 1.2 Dubbo作者

<img src="imgs\4.Dubbo的作者-16850993555192.png" alt="4.Dubbo的作者" style="zoom:100%; margin-left: 200px" /> 

<center>从左至右：刘超，梁飞，闾刚，陈雷，刘昊旻，李鼎</center>

### 1.3 Dubbo的发展历程

   2008年阿里内部开始使用；
​   2009年初，发布1.0版本；
​   2010年初，发布2.0版本；
​   2011年10月27日，阿里将Dubbo开源，版本号为2.0.7；
​   2012年3月，发布2.1.0版本；
​   **2014年10月，发布2.3.11版本，之后版本停滞；**
​   2017年9月，阿里重启维护，重点升级了依赖的JDK版本，发布2.5.4/2.5.5版本；
​   2017年10月，发布2.5.6版本；
​   2017年11月，发布2.5.7版本，后期集成了Spring Boot；
​   **2014年10月的时候，当当网Fork了Dubbo源代码，在此基础上增加了REST协议，发布版本号2.8.0，命名为`DubboX`;  ​**    

​	网易考拉在Dubbo基础上开发了DubboK；

## 2. Dubbo的发展背景

​        随着互联网的发展，网站应用的规模不断扩大，常规的垂直应用架构已无法应对，分布式服务架构以及流动计算架构势在必行，亟需一个治理系统确保架构有条不紊的演进。

![1.系统发展图](imgs\1.系统发展图-16850993555193.jpg)

### 2.1 单一应用架构

​         当网站流量很小时，只需一个应用，将所有功能都部署在一起，以减少部署节点和成本。此时，用于简化增删改查工作量的**数据访问框架(ORM)**是关键。

1. 优点
   - 架构简单，易于开发、部署、测试。
   - 对于小型项目来说，所有模块一起部署，维护方便。
2. 缺点
   - 对于大型项目来说，所有模块耦合在一起，不容易开发和维护。
   - 无法针对某个具体模块来提升性能。

### 2.2 垂直应用架构

​        当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，提升效率的方法之一是将应用**拆成**`互不相干的几个应用`，以提升效率。

​		电商交易系统、用户系统、权限系统、商品系统、订单系统、物流系统、支付系统....

​		**特点**：<u>系统独立部署</u>，可以针对不同模块进行优化，方便水平扩展；

​		**问题**：系统之间无法做到完全独立，公共模块无法复用，系统之间通信比较麻烦；

### 2.3 分布式服务架构

​        当垂直应用越来越多，应用之间交互不可避免，这时将核心业务抽取出来，`作为独立的服务`，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。

​       此时，用于提高业务复用及整合的**分布式服务框架（RPC）**是关键。

  **分布式架构的难点**：

1. 各个系统如何进行远程调用
2. 如何进行业务拆分

### 2.4 流动计算架构

​        当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个**调度中心**基于<u>访问压力实时管理集群容量</u>，提高集群利用率。此时，用于提高机器利用率的**资源调度和治理中心**（**SOA** - Service Oriented Architecture）是关键。

### 2.5 微服务架构

​		微服务是一种架构风格，**是一种提倡将大型应用程序开发为小服务集合的架构方式**，每个服务都是围绕着各自的业务能力进行构建，拥有独立的进程，服务之间可以通过轻量级的通信机制（如HTTP方式）进行通信，只有一个较小的中心化管理，并可以进行自动化部署、可以使用不同的语言和数据存储机制来实现，微服务可以在不影响客户使用的情况下对应用程序<u>进行频繁更新</u>。

## 3. RPC

​        RPC（Remote Procedure Call）- 远程过程调用，它是一种通过网络从**远程计算机**的程序上请求服务，而不需要了解底层的网络技术协议。RPC协议假定某些传输协议的存在，如TCP/IP或UDP，为通信程序之间携带信息数据。

​        通俗的说，RPC可以让我们像调用本地方法一样调用远程计算机提供的服务；

### 3.1 RPC的简单原理

<img src="imgs\3.RPC原理图-16850993555194.png" alt="3.RPC原理图" style="zoom:60%;" /> 

1. **客户端以本地调用的方式调用远程服务**
2. client stub接收到调用后，将**方法名、参数、参数类型**等组装成能够进行网络传输的消息；
3. client stub查找服务地址，找到之后，将消息发送到服务端；
4. server stub收到消息之后，对收到的消息进行解码；
5. server stub根据解码结果，使用**反射的方式**调用本地服务；
6. 服务端执行完成之后将结果返回给Server stub；
7. server stub将<u>返回结果</u>打包成消息并发送给客户端；
8. client stub收到消息后，对结果进行解码；

## 4. Dubbo架构

1. **架构中的角色**

   Provider：服务提供者

   Consumer：服务消费者

   Registry：服务注册和发现的中心

   Monitor：监控中心，用于统计服务调用情况

   Container：Dubbo容器

   <img src="imgs\2.Dubbo架构图-16850993555225.png" alt="2.Dubbo架构图" style="zoom:50%;" />  

2. **调用关系**

   0、**容器**负责<u>启动、加载、运行</u>服务提供者、消费者；

   1、服务提供者在启动时，向注册中心注册自己提供的服务；

   2、服务消费者在启动时，向注册中心订阅自己需要的服务；

   3、注册中心返回服务提供者的地址列表给消费者；

   ​	  如果有服务变更(服务的上线或下线)，注册中心会基于**长连接的方式**推送变更给消费者；

   4、服务消费者从地址列表中，基于`软件负载均衡算法`，选择一个服务提供者进行调用，如果调用失败，可以重试其它提供者；

   5、服务消费者和提供者，在内存中累计调用时间和调用次数，每分钟向监控中心发送一次统计数据；

## 4. 安装单机Zookeeper

### 4.1 Windows版本

1. 解压zookeeper-3.4.12.zip到当前目录

2. 在zookeeper-3.4.12目录下创建`data`目录

   <img src="imgs\image-20211007170713742-16466136470911-16850993555239.png" alt="image-20211007170713742" style="zoom:60%;" /> 

   

3. 将`zookeeper-3.4.12/conf`目录下的`zoo_sample.cfg`文件重命名为`zoo.cfg`

   <img src="imgs\image-20211007170754187-16466136470912-16850993555226.png" alt="image-20211007170754187" style="zoom:60%;" /> 

   

4. 修改`zoo.cfg`文件中的`dataDir`选项

   <img src="imgs\image-20211007170906783-16466136470913-16850993555227.png" alt="image-20211007170906783" style="zoom:50%;" /> 

   

5. 启动Zookeeper服务端

   双击`zookeeper-3.4.12/bin/zkServer.cmd`

   Linux、Mac OS：`./zkServer.sh start`

   

6. 启动Zookeeper的客户端

   双击`zookeeper-3.4.12/bin/zkCli.cmd`

   

7. **注意**：zookeeper的端口号默认是**2181**

### 4.2 Linux版本

1. 将zookeeper-3.4.12.tar.gz上传到centos的/opt目录下

2. 解压zookeeper-3.4.12.tar.gz到当前文件夹下(即/opt)

   `tar -zxf zookeeper-3.4.12.tar.gz -C /opt`

3. 进入zookeeper-3.4.12，创建一个data目录

   ```bash
   cd zookeeper-3.4.12
   mkdir data
   ```

4. 进入zookeeper-3.4.12/conf目录，将zoo_sample.cfg修改zoo.cfg

   ```bash
   cd conf
   mv zoo_sample.cfg zoo.cfg
   ```

5. 编辑zoo.cfg文件

   ```bash
   # 将dataDir修改为../data
   dataDir=../data
   ```

6. 启动ZK server

   ```bash
   cd bin
   ./zkServer.sh start
   ```

7. 启动ZK客户端

   ```bash
   ./zkCli.sh
   ```

## 5. Dubbo控制台安装

1. Dubbo控制台**主要用于服务治理**，可以设置负载均衡策略、权重调节、服务降级策略、访问控制等
2. 可以通过控制台查看所有服务（提供者、消费者）
3. 在dubbo-admin.jar所在目录执行 `java -jar dubbo-admin.jar [-Dserver.port=9090] `即可
4. Dubbo控制台默认访问地址：`http://localhost:7001`
5. Dubbo<u>默认</u>的用户名/密码：root/root

## 6. 搭建Dubbo工程 - Hello World

​		[快速开始](https://cn.dubbo.apache.org/zh/docsv2.7/user/quick-start/)

1. dubbo-hello                   父工程
2. dubbo-interface             服务接口工程：HelloService
3. hello-provider                HelloService服务提供者（实现服务接口）
4. hello-consumer             HelloService服务消费者

## 7. Dubbo详解

### 7.1 Dubbo的注册中心有哪些

1. Multicast注册中心

   Multicast注册中心不需要启动任何中心节点，只要广播地址一样，就可以互相发现。

2. **Zookeeper注册中心**

   [Zookeeper](http://zookeeper.apache.org/) 是 Apache Hadoop 的子项目，是一个树型的目录服务，`支持变更推送`，适合作为 Dubbo 服务的注册中心，工业强度较高，可用于生产环境，并推荐使用。

3. Redis注册中心

4. Simple注册中心（开发者自己开发Dubbo服务）

5. **Nacos注册中心**

### 7.2 Dubbo支持的协议

​		`dubbo`、rmi、hessian、http、webservice、`rest`、thrift、memcached、redis、grpc

​		推荐使用 dubbo 协议

### 7.3 Dubbo服务在ZK中的存储结构

<img src="imgs\zookeeper-16850993555228.png" style="zoom:50%; margin-left:60px" />  

### 7.4 启动时检查

​		Dubbo**默认情况下会在启动时检查依赖的服务是否可用**，不可用时会抛出异常，**阻止 Spring 初始化完成**，以便上线时，能及早发现问题，默认 `check="true"`。

#### 7.4.1 关闭单个服务的启动检查 - 消费方的接口配置

​		可以通过<dubbo:reference `check="false"`> 关闭检查，比如，测试时，有些服务不关心，或者出现了循环依赖，必须有一方先启动；

```xml
<dubbo:reference id="helloService" 
  interface="com.etoak.service.HelloService" 
  check="false">
</dubbo:reference>
```

#### 7.4.2 关闭所有服务的启动检查 - 消费方的全局配置

```xml
<dubbo:consumer check="false" />
```

#### 7.4.3 关闭注册中心的启动检查

```xml
<dubbo:registry check="false" address="zookeeper://127.0.0.1:2181" />
```

### 7.5. Dubbo配置加载流程

​		[文档地址](https://dubbo.apache.org/zh/docsv2.7/user/configuration/configuration-load-process/)

​		此篇文档主要讲在**应用启动阶段，Dubbo框架如何将所需要的配置采集起来**（包括应用配置、注册中心配置、服务配置等），以完成服务的暴露和引用流程。

​		Dubbo可以在哪里进行配置，如果在多个配置文件配置相同的配置项，Dubbo启动时会使用哪一个配置项。

#### 7.5.1 配置来源

- JVM System Properties，-D参数

- Externalized Configuration，外部化配置（将配置项配置在应用外部）

- ServiceConfig、ReferenceConfig等编程接口采集的配置、**Spring配置**

- 本地配置文件`dubbo.properties`(写在src/main/resources)，配置在classpath根目录


#### 7.5.2 配置加载的优先级

​	`jvm参数 > 外部配置 > Spring配置、编程配置 > dubbo.properties`

### 7.6. 不同粒度配置的覆盖关系 - Spring配置为例

- **方法级优先，接口级次之，全局配置再次之。**
- **如果级别一样**，则<u>消费方优先，提供方次之。</u>

1. 配置级别

   > 方法级配置：`<dubbo:method />`
   >
   > 接口级配置：`<dubbo:service />` 、`<dubbo:reference />`
   >
   > 全局级配置：`<dubbo:consumer />` 、`<dubbo:provider />`

<img src="imgs\5.dubbo不同粒度覆盖关系-168509935552310.jpg" alt="不同粒度配置的覆盖关系" style="zoom:70%; margin-left:60px" />

### 7.7. 直连提供者

​		在开发及测试环境下，经常需要**绕过注册中心**，只测试指定服务提供者，这时候需要点对点直连；

​		**点对点直连方式**，以服务接口为单位，忽略注册中心的提供者列表，A 接口配置点对点，不影响 B 接口从注册中心获取列表。

- **消费方直连提供者配置方式**

  ```xml
    <dubbo:reference id="helloService"
        url="dubbo://127.0.0.1:20880"
        interface="com.etoak.service.HelloService">
    </dubbo:reference>
  ```

### 7.8. 本地存根

​		提供方有些时候想在客户端也执行部分逻辑，比如：做 ThreadLocal 缓存，提前验证参数，调用失败后伪造容错数据等等。

1. 在接口旁开发一个Stub实现类，类名：`服务名Stub`，例如HelloServiceStub

2. **配置Stub - （两种配置方式）**

   1、**本地存根名称**是`服务名Stub`，例如：`HelloServiceStub`

   ```xml
   <dubbo:service ref="helloService"
                  retries="1"
                  stub="true"
                  interface="com.etoak.service.HelloService">
     <dubbo:method name="hello" timeout="2500"  />
   </dubbo:service>
   ```

   

   2、本地存根名称不是`服务名Stub`，例如：`HelloServiceXxxx`，<u>stub配置方式</u>必须是`包名+类名`

   ```xml
   <dubbo:service ref="helloService"
                  retries="1"
                  stub="com.etoak.service.HelloServiceXxxx"
                  interface="com.etoak.service.HelloService">
     <dubbo:method name="hello" timeout="2500"  />
   </dubbo:service>
   ```

### 7.9. 服务分组

​	使用服务分组区分<u>服务接口</u>的不同实现，当一个接口有多种实现时，可以用 **group** 区分。

1. **提供方配置**

   ```xml
   <!-- 第一个服务实现 -->
   <dubbo:service ref="helloService" 
                  group="hello"
                  interface="com.etoak.service.HelloService">
   </dubbo:service>
   <bean id="helloService" class="com.etoak.service.impl.HelloServiceImpl" />
   
   <!-- 第二个服务实现 -->
   <dubbo:service ref="service2" 
                  group="hello2" 
                  interface="com.etoak.service.HelloService" />
   <bean id="service2" class="com.etoak.service.impl.HelloServiceImpl2" />
   ```

2. **消费方配置**

   ```xml
   <dubbo:reference id="helloService"
                    group="hello2"
                    interface="com.etoak.service.HelloService">
   </dubbo:reference>
   ```

### 7.10. 服务多版本 - 服务升级

​		当一个接口实现出现**不兼容**需要升级时，可以用<u>版本号过渡</u>，<u>版本号不同的服务</u>相互间不引用。

​		可以按照以下的步骤进行版本迁移：

1. 在低压力时间段，先升级一半提供者为新版本
2. 再将所有消费者升级为新版本
3. 然后将剩下的一半提供者升级为新版本

- **提供方配置**

  ```xml
  <dubbo:service ref="helloService" version="2.0"
                 interface="com.etoak.service.HelloService">
  </dubbo:service>
  ```

- **消费方配置**

  ```xml
  <dubbo:reference id="helloService" retries="1"
                   version="2.0"
                   interface="com.etoak.service.HelloService">
  </dubbo:reference>
  ```

### 7.11. 负载均衡

​	Dubbo 提供了多种均衡策略，默认为 `random` 随机调用。

**1. Random LoadBalance**
        **随机**，按<u>权重</u>设置随机概率。
        在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。

**2. RoundRobin LoadBalance**
        **轮询**，按`公约后的权重`设置轮询比率。
        存在慢的提供者累积请求的问题，比如：第二台机器很慢，但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。

**3. LeastActive LoadBalance**
        最少活跃调用数，相同活跃数的、随机，`活跃数指调用前后计数差`。
        使慢的提供者收到更少请求，因为<u>越慢的提供者</u>的调用前后<u>计数差</u>会越大。

**4. ConsistentHash LoadBalance**
        一致性 Hash，`相同参数的请求总是发到同一提供者`。
        当某一台提供者宕机时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。
        算法参见：http://en.wikipedia.org/wiki/Consistent_hashing
        默认只对第一个参数 Hash，如果要修改，请配置 <dubbo:parameter key="hash.arguments" value="0,1" />
        默认用 160 份虚拟节点，如果要修改，请配置 <dubbo:parameter key="hash.nodes" value="320" />

### 7.12. 服务降级

​		可以通过`服务降级`功能临时屏蔽某个出错的非关键服务，并定义降级后的返回策略。

- `mock=force:return+null` 表示消费方对该服务的方法调用都直接返回 null 值，不发起远程调用。用来屏蔽不重要服务不可用时对调用方的影响。

- 还可以改为 `mock=fail:return+null` 表示消费方对该服务的方法调用失败后，再返回 null 值，不抛异常。用来容忍不重要服务不稳定时对调用方的影响。

  <img src="imgs\image-20230306164308999-168509935552311.png" alt="image-20230306164308999" style="zoom:40%;" />  





---





# day19()















