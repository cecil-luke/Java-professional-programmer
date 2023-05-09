## Spring概述
1. Spring框架是构建企业应用程序轻量级的解决方案；

2. Spring是模块化的，应用程序可以按需导入模块；

3. Spring的两大核心是`IOC`(Inversion Of Control：控制翻转)和`AOP`(Aspect Oriented Programming：面向切面编程)；

4. Spring提供了与第三方框架（如：持久层框架Hibernate等、消息队列、缓存等）的无缝集成；


## Spring作者简介
1. **作者**：`Rod Johnson`

   Rod Johnson在悉尼大学不仅获得了计算机学位，同时还获得了音乐学的博士学位。

   Rod Johnson曾经是JSR 154专家组成员，这个专家组负责定义Servlet 2.4规范；

   Rod Johnson是澳大利亚人，很长一段时间生活和工作都在伦敦；

2. **另一个作者**：`Juergen Hoeller`

   <img src="./4.Juergen Hoeller.png" alt="Juergen Hoeller" style="zoom:40%; margin-left:0px" />

## Spring发展历程
1. 2002年10月，Rod Johnson出版了《Expert One-On-One J2EE Design And Development》，书中的代码后来变成了Spring框架基础代码；

2. 2003年6月，发布Spring Framework 0.9；

3. **2004年5月，发布Spring Framework 1.0；**很快，它被大量Java开发人员使用了；

4. 2004年8月，Rod Johnson(罗德·约翰逊)、Juergen Hoeller(于尔根·霍勒)、Keith Donald(基思·唐纳德)和Colin Sampalanu(科林·萨姆帕利亚努)共同创立了Spring咨询公司，名字叫Interface21；

5. 2006年10月，发布Spring 2.0；Spring 2.0具有简化XML配置、支持JDK5.0、支持动态语言等特点;

6. **2007年11月，发布Spring 2.5；这时，公司名称由Interface21变更为SpringSource；**

   **Spring 2.5增加了注解支持等特性；**

7. 2009年8月，SpringSource被VMWare以4.2亿美元收购。

8. **2009年12月，Spring 3.0发布；**

   Spring 3.0提供了Spring表达式语言

   基于Java代码的Bean配置(Java Config)和JavaEE6的支持；

   重大增强：在Spring MVC中对RESTful的支持；

9. **2012年7月，Rod Johnson离开了Spring团队**，Spring框架的流行极大地影响了Java EE平台的发展

10. 2013年4月，VMWare、EMC和GE(美国通用电气公司)合资建立了Pivotal，所有Spring应用程序都被转移到这个新公司；

11. 2013年12月，发布Spring 4.0，主要功能包括支持JDK8，Groovy DLS定义bean的方式和WebSockets.

12. 2017年7月， 发布Spring 5.0，支持响应式编程模型和Java EE 7；

13. 2021年9月2日，Spring宣布了Spring Framework 6 和 Spring Boot 3的开发计划

    [Spring Framework 6正式版计划于2022 年第四季度发布](https://spring.io/blog/2021/09/02/a-java-17-and-jakarta-ee-9-baseline-for-spring-framework-6)

    **Spring Framework 6 将基于 JDK 17 和 Jakarta EE 9（Tomcat 10.0 +）；**

    <img src="./Spring 6.png" style="zoom:50%; margin-left:0px" >

18. 2022年11月16日，Spring发布6.0正式版本

    [spring-framework-6-0-goes-ga](https://spring.io/blog/2022/11/16/spring-framework-6-0-goes-ga)

## Pivotal公司简介
1. 1989年，Rob Mee创立的Pivotal Labs；
2. 2012年，Pivotal Labs被EMC收购；
3. 2013年，EMC和VMWare分拆旗下业务成立Pivotal Software新公司，股东是EMC、VMware和GE（通用电气）；
4. 2014年，成立Cloud Foundry基金会，发布Spring Boot；
5. 2015年，发布Spring Cloud，Pivotal Cloud Foundry实现了AWS和Azure支持；
6. 2016年，获得福特和微软投资，母公司EMC被戴尔收购；
7. Pivotal公司的开源产品有：
   - **Spring 以及 Spring 衍生产品；**
   - **缓存中间件Redis**
   - **消息中间件 RabbitMQ**
   - 平台即服务的Cloud Foundry
   - Greenplum数据引擎
   - GemFire（12306系统解决方案组件之一）