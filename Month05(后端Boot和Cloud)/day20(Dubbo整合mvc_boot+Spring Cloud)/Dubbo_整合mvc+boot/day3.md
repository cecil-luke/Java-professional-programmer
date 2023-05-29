## 今天内容

1. Spring MVC中使用Dubbo
2. Spring Boot整合Dubbo

## 1. Spring MVC中使用Dubbo

- 创建工程，工程间依赖关系如下图

  <img src="Dubbo-MVC.png" style="zoom:50%;" > 

### 整合Spring MVC时出现的问题

1. 整合出现的问题：找不到`Root WebApplicationContext`（找不到父容器）

   <img src="imgs/image-20230529111525612.png" alt="image-20230529111525612" style="zoom:50%;" /> 

2. 错误原因：导入了Dubbo框架导致的

   为什么导入Dubbo框架就报错？为什么Dubbo启动不找Spring MVC容器呢？

   Dubbo不想与Spring MVC强耦合

## 2. Spring Boot整合Dubbo

<img src="Dubbo-Boot.png" style="zoom:50%;" > 