# day01

### 前端 上课|开发 规范

1. 命名不包括以下几种字符

     + 中文

     + 空格

     + 数字开头

     + 特殊字符

     + 前端工程名不要出现大写字母

     + 可以使用 - _

2. 所有源码放置在 **src** 目录下

3. 所有的样式放置在 **css** 目录下

4. 所有的脚本放置在 **js** 目录下

5. 所有的静态资源,例如图片等放置在 **assets** 目录下

6. 几种命名方式

     + **小驼峰**  etoakDemoProject

     + **大驼峰**  EtoakDemoProject

     + **连字符**  etoak-demo-project 连字符格式中绝对不会出现大写字母  

7. 在 body 标签内开始,子标签一定要在父标签向右一个制表符(tab),两种规范都是对的,一个制表符相当于 **4 个空格**或者 2 个空格

   

---

### **表单提交时 get 和 post 的区别**



+ **get**

+ 默认提交方式,速度快,毫无安全性,通过**浏览器地址栏传递值**,格式为?key=value&key2=value2&keyN=valueN,不支持中文,最多传递 2000 个字符,链接提交肯定是 get

+ **post**

+ 速度慢,安全性高,通过**请求的消息体中的载荷传递值**,我们可以通过浏览器的 f12 开发人员工具中的 **network 网络选项卡**查看传值情况,支持中文,没有大小限制,上传操作必须使用 **post**,<u>链接只能使用 get</u> 无法使用 post

   

---

### day01

#### 课程内容

---



#### **HTML**

**## Hyper Text Markup Language**

**## 超文本标记语言**



> 前端三大基础之一,用来渲染页面的结构和样式(样式已被css取代),不被列入编程语言,更像是一种规范



> **超文本**:在后端中存在一句话叫做 ‘**万物皆对象**’，那么在前端页面中有句话则是‘**万物皆为超文本**’，超文本是指不局限于文本，还包括页面的图像、音频、视频、图表等一系列要素。



> **标记**:英文称之为tag，更多翻译做标签，由尖括号组成，在html规范中规定使用各种标记（标签）可以渲染页面中的所有超文本，浏览器内置标签解析器与脚本解析器，使用标签渲染之后浏览器从上往下使用标签解析器编译执行，如果书写无误，则浏览器根据用户书写的标签编译之后在页面渲染出相应的结构和样式，这些标签就像乐队的指挥一样告诉浏览器整个页面哪里需要抑扬顿挫



> 诞生于1993年，由w3c世界互联网组织发布了第一版的html规范，专门用来渲染页面的结构和样式（样式于1996年被css技术取代），html规范存在根据浏览器引擎的不同存在差异性，目前已经发展到html5版本。





**### 标签的种类**

+ 开闭合标签

+ <tagName>被嵌套的内容</tagName>

+ <em>文本倾斜</e m>

+ <b>文本加粗</ b>

+ 整合标签

+ <br>

+ <h r>

+ 标签的属性

+ <tagName 属性名="属性值" 属性名="属性值" 属性名="属性值">

+ 标签与它内部嵌套的内容又被称之为 xxx 元素例如

+ <em>文本倾斜</e m> 又被称之为 em 元素(element)

+ <b>文本加粗</ b> 又被称之为 b 元素

#### **CSS**

**## Cascading Style Sheet**

**## 层叠样式表**



> 前端三大基础之二,用来渲染页面的样式



> 1996年由w3c发布了第一版的css，目前最新的版本为css4，其中更新最大，使用最多的是css3版本，css诞生的目的是将页面的结构和样式解耦，使两者不再耦合在一起，之前使用html会同时渲染结构和样式导致页面混乱



```html
<del>

  <ins>

​    <em>

​      <*font* face="楷体" size="20px" color="coral">

​        <h2>

​          二级标题

​        </h2>

​      </*font*>

​    </em>

  </ins>

</del>

<!-- 如果结构和样式耦合在一起,则页面冗余标签严重,浏览解析缓慢,毫无可读性,同时也没有实现复用 -->
```

```css
selector{

  样式名:样式值;

  样式名:样式值;

  样式名:样式值;

}

/* 后写的如果和之前写的出现冲突,则覆盖 */
```



---

### 1-html表单.html

---

```html
<!-- 使用 alt + shift + a 可以直接书写注释
    上课要求
        1:独立完成,不要依赖同位
        2:不要跟着我敲注释,只跟着我敲代码
        3:有问题要问
        4:每天记一条 sql 语句
        5:自己弄一个网络的笔记的软件 印象笔记 notion 有道云笔记
    
    html 文件以 .html 和 .htm 为后缀结尾,全文不区分大小写,具有一定的
    容错性,如果我们书写了严重的错误,浏览器停止解析,没有报错功能
    以下首行为 DTD 信息 全称(Document Type Definition 文档类型定义)
    用来提示浏览器使用何种语法规范来解析,以下首行为 html5 规范
-->
<!DOCTYPE html>
<!-- html:根标签,在 html 和 xml 中,全文只有一个根标签,其它标签
都被嵌套在根标签内部,称之为子标签,孙辈标签等... 
lang:表示当前页面的语言类型,默认为 en 英文网页,注意如果用户的地区语言
与此处不符,google 浏览器会弹出窗口询问是否需要翻译 -->
<html lang="en">
<!-- head:头信息,用来设置全文的编码,标题等,引入外部资源 -->
<head>
    <!-- 
        meta:用来设置全文的编码,主要有以下几种编码
            iso-8859-1
            utf-8
            gbk
    -->
    <meta charset="UTF-8">
    <!-- 此处设置兼容性 ie 和 edge -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 设置页面的宽度缩放比例默认为 100%,也就是不缩放,使用 ctrl + +
    可以放大网页 ctrl + - 缩小网页 ctrl + 0 恢复默认也就是这里设置的
    100% -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 设置网页标题 -->
    <title>1:表单</title>
</head>
<!-- body:表示网页的正文部分,我们可以将 body 理解为浏览器的边缘 -->
<body>
    <!-- h1-h6:共有六级标题,随着序号的增大,字体不断减小,自带换行,文本加粗 
    在书写 html 标签时,一定要使用 emmet 插件快速书写标签 -->
    <h1>用户注册</h1>
    <hr>
    <!-- form:表示一个表单,在表单内部可以书写各种表单项,这些表单项
    可以通过各种方式收集用户的信息,最终以键值对的方式提交到 action 设置
    的后端地址 
        action:表单最终提交到的目的地,一般为后端,这里由于没有后端
        仅仅书写一个静态页面,书写路径存在以下两种方式
            1:绝对路径:以/开头
            2:相对路径:不以/开头
                ./:表示从当前目录下寻找
                ../:表示从上一级目录下寻找
        method:表单提交方式,存在 get 和 post 两种,如果不写则默认是 get
        ---------------
        String name = request.getParameter("myname")
        String pass = request.getParameter("mypass")

        User u = new User(null,name,pass)
        使用 ORM(Object Mapping Relation ) 
        对象映射关系工具将这个对象持久化到数据库
        boolean flag = dao.add(u)
        boolean flag = dao.remove(5)
        dao.login(name,pass)
    -->
    <form action="./success.html" method="get">
        <!-- 1:单行文本输入框 
                type="text"
                name:表示键值对的键,可以随意书写
                value:表示键值对的值,一般不写,用户填写什么 value
                就是什么
                required:表示表单项必填,如果不写,则表单无法提交
                autofocus:自动获取焦点,光标闪烁
                placeholder:悬浮文本,书写内容立刻消失
                title:验证提示文本
                pattern:正则表达式
                如果不书写 type,则默认就是单行文本输入框
                autocomplete="off" 关闭自动完成功能
        -->
        用户姓名: <input type="text" name="myname"
        value="" required autofocus placeholder="请输入用户姓名"
        title="请输入姓名" autocomplete="off"> <br>
        <!-- 2:单行文本密码框
                type="password"
        -->
        用户密码: <input type="password" name="mypass"
        required placeholder="请输入用户密码" title="请输入密码"
        autocomplete="off"> <br>
        <!-- 4:单选框 
                type="radio"
                checked:表示默认选中
        -->
        性别: <input type="radio" name="gender" value="0"
        checked>女
        <input type="radio" name="gender" value="1">男 <br>
        <!-- 5:复选框 
                type="checkbox"
                复选框是唯一一个一个键对着多个值的元素
        -->
        爱好: 
        <input type="checkbox" name="hobby" value="soccer">足球
        <input type="checkbox" name="hobby" value="running"
        checked>跑步
        <input type="checkbox" name="hobby" value="game">游戏
        <input type="checkbox" name="hobby" value="shopping">购物
        <br>
        <!-- 6:下拉列表框 
                selected:默认选中
        -->
        归属地:
        <select name="location">
            <option value="1">济南</option>
            <option value="2">青岛</option>
            <option value="3" selected>淄博</option>
            <option value="4">德州</option>
            <option value="5">济宁</option>
        </select> <br>
        <!-- 7:邮箱输入框
                type="email" 注意必须输入一个邮箱格式
                但是无法验证这个邮箱的真伪,只能验证格式正确与否
        -->
        邮箱地址: <input type="email" name="email" 
        placeholder="请输入邮箱地址" required> <br>
        <!-- 8:日期验证 
                type="date" 这里供用户选择一个日期,格式肯定是
                yyyy-MM-dd
        -->
        出生日期: <input type="date" name="birth" required>
        <br>

        <!-- 3:提交和取消按钮 
            注意 由于按钮不传递值,所以仅书写 value 属性
            提交按钮可以提交表单到 action 的目的地
            取消按钮可以重置填写的内容
            disabled:禁用,每个元素都有此属性,只要书写,则
            元素被禁用
        -->
        <input type="submit" value="提交">
        <input type="reset" value="取消" disabled>
    </form>
</body>
</html>
```



---

### 2-html链接视图列表.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>2:链接视图列表</title>
</head>
<body>
    <!-- a:链接,其实就是简化版的表单,点击链接,则可以
    提交,肯定是 get 方式,链接自带样式,蓝色字体,自带下划线
        href:就表示链接提交到的目的地
    -->
    <a href="./success.html">点我试试!</a>
    <a href="./success.html?thisiskey=thisisvalue">链接传值</a>
    <!-- target="_blank" 目标页与当前页共存,如果不写,则
    target="_self" 目标页取代当前页 -->
    <a href="./success.html" target="_blank">再点我试试!</a>
    <!-- img:视图,引入一张图片到页面
            src:source 的简写,表示图片的源
            title:鼠标悬浮时显示的文本
            width:设置宽度,注意宽度属于样式,由于我们还未学习 css 因此
            此处使用 html 设置宽度
    -->
    <img src="./assets/etoak.jpg" title="癸卯年来了!"
    width="300px">
    <!-- 链接中可以嵌套图片 -->
    <a href="./success.html">
        <img src="./assets/etoak.jpg" width="200px">
    </a>

    <!-- ul:无序列表
            li:列表项
    -->
    <ul>
        <li>无序列表1</li>
        <li>无序列表2</li>
        <li>无序列表3</li>
        <li>无序列表4</li>
    </ul>
    <ol>
        <li><a href="#">有序列表1</a></li>
        <li>有序列表2</li>
        <li>有序列表3</li>
        <li>有序列表4</li>
    </ol>
</body>
</html>
```



---

### 3-css引入方式.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>3:css引入方式</title>
    <!-- 
        css独立文件以.css为后缀,严格区分大小写,毫无容错性,
        没有报错功能,浏览器如果解析错误,则无法渲染样式
        1:内嵌式
            直接将 css 代码书写在 head 标签内style 中
            这种方式仅仅初步的将结构和样式解耦,解耦不彻底
            复用性依然较差
    -->
    <style>
        /* 这是 css 的注释方式,不得使用 html 注释,也不能在这里
        使用标签 */
        h2{
            /* 设置字体 */
            font-family:喜鹊招牌体;
            /* 设置字体大小 */
            font-size: 20px;
            /* 设置颜色,主要有三种方式 
                1:直接使用英文单词
                red blue green 也可以使用 潘通(pantone)流行色
                coral:珊瑚橘
                cornflowerblue:矢车菊蓝
                crimson:鲱鱼红
                2:使用色号  #ffffff - #000000
                #ff770f:爱马仕橙
                长春花蓝 #6667AB
                勃艮第红 #81001e
                克莱因蓝 #002ea6
                蒂芙尼蓝 #82d8cf
                3:rgb(x,y,z)
                x|y|z:0-255的整数
            */
            color:coral;
        }
        div{
            background-color: purple;
        }
    </style>
    <!-- 2:外链式
        链接外部独立的 css 文件到本页面
        rel="stylesheet" 表示引入的是一个样式表
        href:引入的独立 css 文件路径
        外链式使用最为频繁,复用性较好,将结构和样式彻底解耦
    -->
    <link rel="stylesheet" href="./css/mystyle.css">
</head>
<body>
    <h2>你感染流感了吗?</h2>
    <!-- #:表示提示浏览器不发送任何请求 -->
    <a href="#">点击购买奥司他韦</a>
    <!-- 3:行内式
    直接将 css 书写在标签内,这种书写方式严重违背结构和样式解耦的初衷,但是由于其优先级极高
    所以使用依然较多-->
    <p style="background-color: pink;color:whitesmoke">请佩戴口罩,抵御甲型和乙型流感</p>
    
    <!-- div:开始和结尾自带换行,一般用来划定一个区域,和段落类似
    但是不会再空一行 -->
    <!-- 三种引入方式的优先级
        行内式 > 外链式和内嵌式 谁放在后面出现冲突听谁的
    -->
    <div>看看我听谁的</div>
</body>
</html>
```



---

### mystyle.css

```css
/* 设置 css 文件编码,默认 utf-8,如果不写就是此编码 */
@charset "utf-8";

/* 设置 a 连接默认的样式 */
a{
    /* 设置连接颜色 */
    color:#82d8cf;
    /* 去掉链接自带的下划线 */
    text-decoration: none;
}
/* 
    添加伪类,用来表示某个元素某种状态
    选择器:伪类{

    }
    :hover:表示元素被鼠标滑过时的样式
*/
a:hover{
    /* 鼠标滑过时的颜色 */
    color:#ff770f;
    /* 添加下划线 */
    text-decoration: underline;
}
/* 被访问过的样式 */
a:visited{
    /* 设置连接被访问过后显示蕾贝卡紫 */
    color:#663399;
}

div{
    background-color: aqua;
}
```

# day02

### 4-css基本选择器.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4:css基本选择器</title>
    <style>
        /* 1:标记选择器
            tagName{
                直接使用标签名作为选择元素的依据,这种引入方式极易引起
                误操作
            }
        */
        span{
            background-color: yellow;
        }
        /* 2:类别选择器
            .class{
                在元素中添加 class 属性,以.class 作为选择元素的依据
                注意,class 全文不唯一,一个元素可以存在多个 class 属性
            }
        */
        .test2{
            background-color: blueviolet;
        }
        /* 3:id 选择器
            #id{
                每个元素可以添加一个 id 属性,注意这个 id 属性全文唯一
                一个元素也只能添加一个,以 #id 作为选择元素依据
            }
        */
        #test3{
            background-color: blue;
        }
    </style>
</head>
<body>
    <!-- span 与 label 被称之为便签标签,结束没有换行,一般搭配
    js 使用,单独使用几乎没有任何效果 -->
    <span>测试1</span>
    <span class="test2" >测试2</span>
    <span id="test3" class="test2" >测试3</span>
    <!-- 
        如果三种基本选择器出现冲突 优先级如下
        标记选择器 < 类别选择器 < id 选择器
        如果还存在行内式,则一切以行内式为准
    -->
    <span id="test3" class="test2"
    style="background-color: crimson;">测试4</span>
</body>
</html>
```

### 5-css复合选择器.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>5:css复合选择器</title>
    <style>
        /* 1:交集选择器
            tagName.class{}
            tagName#id{}
            由一个标记后面紧跟类别或者 id,必须同时满足两个条件
            才可以成功选取
        */
        span.test2{
            /* 文本倾斜 */
            font-style: italic;
        }
        /* 2:并集选择器
            sel1,sel2,sel3,selN{
                由多个基本或者复合选择器用逗号隔开,只要满足其中任意一个
                就可以成功选取
            }
        */
        label.test2,span.test2,h3.title,h4{
            background-color: tomato;
        }
        /* 3:后代选择器
            根据左祖先右后代的层级关系,选择具有特定层级关系的
            最右侧的子元素
            sel1 sel2 selN{

            }
        */
        div#outter em#inner{
            color:teal;
        }
        /* 4:全选选择器 
            *{
                相当于 ctrl+a 选择所有元素
            }
        */
        *{
            font-weight: 800;
        }
    </style>
</head>
<body>
    <span>测试 1</span>
    <label class="test2">测试 2</label>
    <span class="test2">测试 3</span>
    <h3 class="title">三级标题</h3>
    <h4 id="etoak">四级标题</h4>
    <div id="outter">济南的<em id="inner">春</em>天开始了</div>
</body>
</html>
```

### 6-css元素类型.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>6:Css元素类型</title>
    <style>
        span{
            background-color: pink;
            /* 设置宽度 */
            width:300px;
            /* 设置高度 */
            height:400px;
            /* 设置内部文本对齐方式,默认居左,此处为居中对齐 */
            text-align: center;
        }
        div{
            background-color: lightblue;
            /* 设置宽度 */
            width:300px;
            /* 设置高度 */
            height:400px;
            /* 设置内部文本对齐方式,默认居左,此处为居中对齐 */
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- 
        Css元素类型
            在 css 中,将元素分为很多种类型,其中主要差异较大的有以下
            两种
            1:块元素(block 元素)
                eg: div p h1-h6 ul ol li table 布局元素
                块元素是页面中的主流元素,基本上页面都是由块元素主要组成
                块元素结尾都自带换行,一行只能书写一个,从上到下排列,设置
                盒子模型有效,可以设置行高,设置对齐方式
                块元素在不主动设置宽度的前提下和父元素同宽,如果没有父元素
                则与浏览器同宽
            2:内联元素(inline 元素,行内元素)
                eg: span label a img input 等等
                内联元素在页面中主要用来进行信息的提示灯,结尾没有换行,
                从左往右排列,一行可以排列多个,设置盒子模型无效,设置对齐方式
                无效
                img input:又被称之为 内联块元素(inline-block)
                虽然是内联元素,但是设置盒子模型有效

    -->
    <!-- style="display: block;" 以块元素显示元素 -->
    <span style="display: block;">
        我是 span我是 span我是 span我是 span我是 span我是 span
        我是 span我是 span我是 span我是 span我是 span我是 span
        我是 span我是 span我是 span我是 span我是 span我是 span
    </span>
    <hr>
    <!-- style="display: inline;" 以内联元素显示元素 -->
    <div style="display: inline;">
        我是 div我是 div我是 div我是 div我是 div我是 div
        我是 div我是 div我是 div我是 div我是 div我是 div
        我是 div我是 div我是 div我是 div我是 div我是 div
    </div>
    <!-- display:none;隐藏元素 -->
    <div style="display: none;">
        我是 div我是 div我是 div我是 div我是 div我是 div
        我是 div我是 div我是 div我是 div我是 div我是 div
        我是 div我是 div我是 div我是 div我是 div我是 div
    </div>
</body>
</html>
```

### 7-css盒子模型.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>7:Css盒子模型</title>
    <style>
        /* 
            标准文本流(文档流 document normal flow)
                其实就是指一种默认的状态,页面中主要由块元素组成,这些元素
            结尾自带换行,因此默认情况下在没有任何 css 渲染的前提下,这些元素
            从上往下排列,这种状态类似水流一样,当我们挪走其中的某个元素,后面
            的元素会递补,继续保持水流从上往下,在书写页面时应该首先书写 html 结构
            当所有的结构都书写完毕,呈现标准文本流之后,才开始书写 css,最终
            添加 js,切忌边写 html 边写 css
            盒子模型
                在css 中将各种元素看做一个一个矩形的盒子,这些盒子具有四个
            边框,从上往下摆放在页面中,通过设置 border 属性可以渲染盒子的边框
            通过设置 margin 属性可以设置盒子边框以外的空间位置,通过设置
            padding 属性可以设置盒子边框以内的空间位置,元素的宽度通过 width
            来设置 高度则有 height 来进行设置,最终共有 14 个参数,这 14 个参数
            被称之为盒子模型,其中大部分元素只有块元素才可以设置
        */
        *{
            /* 去掉默认留白部分 */
            margin:0;
            padding:0;
        }

        /* 谷歌和火狐存在差异 双方一个认为 html 是正文
        一个认为 body 是正文,因此这里两边使用并集 */
        html,body{
            /* 设置高度参照物 */
            height:100%;
        }

        .container{
            /* 渲染边框:边框类型 边框粗细 边框颜色 
                边框类型 solid double dotted
            */
            border:solid 2px red;
            /* 
                1:网页整体居中,使用具体像素
                margin:0 auto;
                width:xxxpx;
                height:xxxpx;
                2:网页整体居中,使用百分比,注意这个百分比
                永远是与父元素进行比较,如果没有父元素,则与
                浏览器进行比较,注意如果设置百分比,则必须设置
                宽度和高度的参照物,其中宽度的参照物在 head 标签
                内已经存在
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                所以宽度默认浏览器就是百分之百,但是注意高度并没有
                参照物,因此如果我们不设置,高度使用百分比无效  
                设置高度参照物 
                    html,body{
                        height:100%;
                    }
                3:网页整体居中,使用视口单位
                vw:viewport width
                vh:viewport height
                表示占据当前设备视口的宽度和高度,不需要设置参照物
                注意也不是与父元素比较,而是与当前设备比较
            */
            margin:0 auto;
            /* width:700px;
            height:800px; */
            /* width:80%;
            height:150%; */
            width:80vw;
            height:100vh;
        }
        h2{
            border:solid 2px blue;
            /* 设置外边距 */
            /* margin-top: 50px; */
            /* 设置左外边距 */
            /* margin-left: 20px;
            margin-bottom:30px;
            margin-right:100px; */
            /* 简化写法
                margin:上 右 下 左;
                margin:上 (右左) 下;
                margin:(上下) (右左);
                margin:(上右下左);
            */
            margin:50px 100px 30px 20px;
            padding-top: 20px;
            padding-left: 50px;
            padding-bottom: 10px;
            /* padding 依然可以使用缩略写法 */
            border-top: double 10px hotpink;
            border-right: dotted 15px yellowgreen;
            border-bottom: dotted 8px black;
        }
        ul{
            border:solid 2px aqua;
        }
        li{
            border:solid 2px purple;
        }
        p{
            border:solid 2px yellow;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>二级标题</h2>
        <ul>
            <li>列表1</li>
            <li>列表2</li>
            <li>列表3</li>
        </ul>
        <p>我是段落</p>
    </div>   
</body>
</html>
```

### 8-css浮动.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>8:浮动</title>
    <style>
        /* 
            浮动:
            元素在设置浮动之后,开始朝着浮动方向不断缩小,当缩小到无法再次
            缩小时,漂浮在页面上,之后的元素为了维持标准文本流递补到之前元素
            的位置,如果多个元素都设置浮动则同时漂浮在页面上,从左往右或者从右往左
            排列,这也是最简单的一种打破页面标准文本流的方式
            同时元素也可以设置 clear 属性来屏蔽因为受到其他元素浮动影响造成的影响
        */
        html,body{
            margin:0;
            padding:0;
        }

        .container{
            margin:0 auto;
            width:80vw;
            height:100vh;
            background-color: yellow;
            border:solid 1px yellow;
        }

        .box1{
            margin-top: 10px;
            height: 100px;
            background-color: pink;
            /* 设置左浮动 */
            float: left;
        }
        .box2{
            margin-top: 10px;
            height:120px;
            background-color: hotpink;
            float: left;
        }
        .box3{
            margin-top: 10px;
            height:140px;
            background-color: deeppink;
            float: left;
        }
        p{
            background-color: lightblue;
            /* 设置元素不受其他元素浮动影响 
                clear:left|right|both;
            */
            clear: left;
        }
        ul{
            /* 设置去掉列表徽记 */
            list-style-type: none;
            margin-top: 100px;
        }
        ul li{
            /* 设置列表项浮动 */
            float: left;
            margin-right: 50px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="box1">盒子1</div>
        <div class="box2">盒子2</div>
        <div class="box3">盒子3</div>
        <p>
            我是段落我是段落我是段落我是段落我是段落我是段落
            我是段落我是段落我是段落我是段落我是段落我是段落
        </p>
        <ul>
            <li>列表1</li>
            <li>列表2</li>
            <li>列表3</li>
            <li>列表4</li>
            <li>列表5</li>
        </ul>
    </div>
</body>
</html>
```

### 9-css定位.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>9:定位</title>
    <style>
        .container{
            border:solid 1px black;
            position: relative;
            margin:0 auto;
            width:80vw;
            height:100vh;
        }
        .sub1{
            border:solid 2px crimson;
            /* 
                1:相对定位
                元素根据原先所在位置的左上角进行定位,偏移量
                top bottom left 和 right ,定位之后元素不改变原先的
                类型,原来的位置依然被占用
            */
            position: relative;
            /* 向下偏移 200 像素 */
            top:200px;
            /* 向右偏移 300 像素 */
            left:300px;
        }
        .sub2{
            border:solid 2px coral;
            /* 
                2:绝对定位
                    元素根据其距离最近的定位过的祖先元素的左上角进行定位
                    定位之后元素不再保持原先的类型,原来的位置被其他元素占用
                    偏移量与相对定位一直,top bottom left right
                    如果元素的所有祖先元素都没有定位,则根据 body 也就是浏览器
                    左上角进行定位,如果在设置全局居中时,则有可能出现问题
            */
            position:absolute;
            top:400px;
            left:100px;
        }
        .sub3{
            border:solid 2px yellow;
        }
        .sub4{
            border:solid 2px yellowgreen;
        }
        .sub5{
            border:solid 2px aqua;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="sub1">层1</div>
        <div class="sub2">层2</div>
        <div class="sub3">层3</div>
        <div class="sub4">层4</div>
        <div class="sub5">层5</div>
    </div>
</body>
</html>
```



---



# day03

---

### flex弹性盒子的**名词解释**是重点其中



<img src=".\img\QQ图片20230314190508.png" style="zoom:50%;" />

* **flex-container**

  采⽤ Flex 布局的元素，称为 **Flex 容器**（**flex container**），简称"**容器**"。

* **item**

  它的所有⼦元素⾃动成为容器成员，称为 **Flex 项⽬**（**flex item**），简称"**项⽬**"。

* **主轴**

   容器<u>默认</u>存在<u>两根轴</u>：**⽔平的主轴（main axis）**和**垂直的交叉轴（cross axis）**。主轴的开始位置（与边 框的交叉点）叫做 **main start** ，结束位置叫做 **main end** ；

* **交叉轴**

  **交叉轴**的开始位置叫做 **cross start** ，结束位置叫做 **cross end** 。 项⽬<u>默认</u>沿主轴排列。单个项⽬占据的主轴空间叫做 **main size** ，占据的交叉轴空间叫做 **cross size** 

### **flex-direction:重点**

flex-direction 属性**决定主轴**的⽅向（即项⽬的排列⽅向）。如果未显式设置 flex-direction 属性，Flex容器则会采⽤其**默认值 row** 。

<img src=".\img\QQ图片20230314191906.png" style="zoom:50%;" />

### **justify-content:重点**

justify-content 属性定义了项⽬在**主轴**上的对⻬⽅式。

<img src=".\img\QQ图片20230314192340.png" style="zoom: 80%;" />

在Flex容器中使⽤ <u>justify-content</u> 来控制Flex项⽬在Flex容器主轴⽅向的对⻬⽅式，也可以⽤来分 配Flex容器中主轴⽅向的剩余空间。使⽤ justify-content 分配Flex容器剩余空间，主要是将剩余空 间按不同的对⻬⽅式，将剩余空间分配给Flex项⽬的两侧，即控制Flex项⽬与Flex项⽬之间的**间距**。 

<img src=".\img\QQ图片20230314192559.png" style="zoom: 50%;" />

它可能取5个值，具体对⻬⽅式与轴的⽅向有关。下⾯假设主轴为从左到右。 

**flex-start （默认值**）：左对⻬ 

**flex-end ：**右对⻬ center ： 居中 

**space-between ：**两端对⻬，项⽬之间的间隔都相等。 

**space-around ：**每个项⽬两侧的间隔相等。

所以，项⽬之间的间隔⽐项⽬与边框的间隔⼤⼀ 倍。 注意后<u>三种排列的区别</u> 

<img src=".\img\QQ图片20230314193542.png" style="zoom:67%;" />

* **space-between** 会让第⼀个Flex项⽬的盒⼦起始边缘与Flex容器主轴起点相稳合，最后⼀个Flex 项⽬的盒⼦结束边缘与Flex容器主轴终点相稳合，其它相邻Flex项⽬之间间距相等。当Flex容器中只 有⼀个Flex项⽬时，其表现⾏为和 **flex-start** 等同 

* **space-around** 会让第⼀个Flex项⽬的盒⼦起始边缘与Flex容器主轴起点间距和最后⼀个Flex项⽬ 的盒⼦结束边缘与Flex容器主轴终点间距相等，并且等于其他相邻两个Flex项⽬之间间距的⼀半。 当Flex容器中只有⼀个Flex项⽬时，其表现⾏为和 **center** 等同 

* **space-evenly** 会让第⼀个Flex项⽬的盒⼦起始边缘与Flex容器主轴起点间距和最后⼀个Flex项⽬ 的盒⼦结束边缘与Flex容器主轴终点间距相等，并且等于其他相邻两个Flex项⽬之间间距。当Flex容 器中只有⼀个Flex项⽬时，其表现⾏为和 center 等同 

如果Flex容器没有额外的剩余空间，或者说剩余空间为负值时， justify-content 的值表现形式： 

​	**flex-start** 会让Flex项⽬在Flex容器主轴结束点处溢出 

​	**flex-end** 会让Flex项⽬在Flex容器主轴起点处溢出 

​	**center** 会让Flex项⽬在Flex容器两端溢出 

   **space-between** 和 flex-start 相同 

   **space-around** 和 center 相同 

   **space-evenly** 和 center 相同



### **align-items:重点**

align-items 属性定义项⽬在**交叉轴**上如何对⻬。

<img src=".\img\QQ图片20230314193746.png" style="zoom:67%;" />

<img src=".\img\QQ图片20230314193844.png" style="zoom:50%;" />

它可能取5个值。具体的对⻬⽅式与交叉轴的⽅向有关，下⾯假设交叉轴从上到下。 

* flex-start ：交叉轴的起点对⻬。 

* flex-end ：交叉轴的终点对⻬。 

* center ：交叉轴的中点对⻬。

* baseline : 项⽬的第⼀⾏⽂字的基线对⻬。 

* .box { align-items: flex-start | flex-end | center | baseline | stretch; } 1 2 3 

* stretch （默认值）：如果项⽬未设置⾼度或设为auto，将占满整个容器的⾼度。 

> align-items 的默认值是 stretch ，但只有Flex项⽬示显式设置 height (或 width )值，Flex项 ⽬才会被拉伸填满整个Flex容器。如果Flex容器没有剩余空间或剩余空间为负值是： 

* flex-start 会让Flex项⽬在Flex容器侧轴终点处溢出 

*　flex-end 会让Flex项⽬在Flex容器侧轴起点处溢出 
*　center 会让Flex项⽬在Flex容器侧轴两侧溢出 

* baseline 会让Flex项⽬在Flex容器侧轴终点溢出，有点类似于 flex-start

### **flex:1;重点**

<img src=".\img\QQ图片20230314195024.png" style="zoom:80%;" />

---







