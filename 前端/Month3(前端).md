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

在Flex容器中使⽤ **<u>justify-content</u>** 来控制Flex项⽬在Flex容器**主轴⽅向**的对⻬⽅式，也可以⽤来分配Flex容器中主轴⽅向的**剩余空间**。使⽤ justify-content 分配Flex容器剩余空间，主要是将剩余空间按不同的对⻬⽅式，将剩余空间分配给Flex项⽬的两侧，即控制Flex项⽬与Flex项⽬之间的**间距**。 

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



### day03

###  10-用户登录.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>10:用户登录</title>
    <!-- 引入全局 css -->
    <link rel="stylesheet" href="./css/global.css">
    <!-- 引入登录的 css -->
    <link rel="stylesheet" href="./css/login.css">
    <!-- 引入图标 -->
    <link rel="icon" href="./assets/favicon.ico">
</head>
<body>
    <div class="login-container">
        <form action="./12-圣杯布局.html" class="login-form">
            
            <h1>用户登录</h1>
            
            <input type="text" name="username" 
            placeholder="请输入用户姓名" required autocomplete="off"
            autofocus>

            <input type="password" name="password"
            placeholder="请输入用户密码" required autocomplete="off">
            
            <div>
                <input type="submit" value="登录">
                
                <input type="reset" value="取消">
                
                <input type="button" value="注册">
            </div>
        </form>
    </div>
    <script>
        document.querySelector('input[type=button]')
        .onclick=()=>{
            location.href = './12-圣杯布局.html'
        }
    </script>
</body>
</html>
```

#### login.css

```css
@charset "utf-8";
/* 设置登录的样式 */
/* 设置最外侧大容器 */
.login-container{
    /* 设置容器绝对定位 */
    position: absolute;
    /* 设置容器宽度 */
    width:100vw;
    /* 设置容器高度 */
    height:100vh;
    /* 设置容器背景图片 no-repeat:表示如果图片大小不合适
    则不会重叠摆放 */
    background: url("../assets/bg1.jpeg") no-repeat;
    /* 设置背景图尺寸 宽度 高度 */
    background-size: 100vw 100vh;
    /* 设置背景图不会随着缩放变动 */
    background-attachment: fixed;
    /* 容器开启弹性盒子 */
    display: flex;
    /* 设置项目,也就是表单 主轴 交叉轴居中 此时默认 水平是主轴
    纵向是交叉轴 */
    justify-content: center;
    align-items: center;
}

/* 设置表单 */
.login-form{
    /* 设置表单宽度 */
    width:350px;
    /* 设置表单背景色 和 透明度 
    rgba(红色,绿色,蓝色,透明度)
    红绿蓝为 0-255 的整数 如果都是 255 则是白色
    透明度 0 完全透明 1 完全不透明 */
    background-color: rgba(255, 255, 255, 0.6);
    /* 设置边框为圆角 数字越大越圆,如果设置为 50%则变为圆球 
    表格不能设置 */
    border-radius: 30px;
    /* 给表单开启弹性盒子 */
    display: flex;
    /* 设置表单内的项目从上往下排列,默认是 row 从左往右 */
    flex-direction:column;
    /* 设置交叉轴水平居中摆放,注意这里从左往右是交叉轴了,从上往下
    才是主轴 因为排列顺序是从上往下 */
    align-items: center;
}

/* 
    设置单行文本输入框和单行文本密码框 
    [属性名=属性值]{}
    拿取一个元素,其中属性名等于属性值,注意必须精确匹配
    这叫属性选择器
*/
input[type=text],
input[type=password]{
    /* 去掉输入框边框 */
    border:none;
    /* 去掉获取焦点后边框 */
    outline: none;
    /* 设置输入框为圆角 */
    border-radius: 10px;
    /* 设置输入框外边距 */
    margin:5px;
    /* 设置输入框内边距 */
    padding:5px;
    /* 添加阴影 */
    box-shadow: 5px 5px 5px silver;
}

/* 
    设置三个按钮
    [属性名^=属性值开头]{}
    [属性名*=包含属性值]{}
    [属性名$=属性值结尾]{}
*/
input[type^=sub],
input[type*=ese],
input[type$=tton]{
    /* 设置按钮外边距 */
    margin:10px;
    /* 去掉按钮边框 */
    border:none;
    /* 更改为圆角 */
    border-radius: 10px;
    /* 设置按钮宽度 */
    width:50px;
    /* 添加按钮背景色 */
    background-color: cornflowerblue;
    /* 添加按钮字体颜色 */
    color:whitesmoke;
    /* 添加阴影 前三个参数为阴影偏移量 第四个参数为阴影颜色 */
    box-shadow: 5px 5px 5px gray;
}
```

#### global.css(全局布置)

```css
/* 此处为全局 css 设置的位置 */
@charset "utf-8";

html,body{
    /* 去掉块元素留白部分 */
    margin:0;
    padding:0;
    /* 设置全文使用的字体 */
    font-family: 喜鹊招牌体;
    /* 设置百分比参照物,否则全文无法使用高度百分比单位 */
    height:100%;
}
```

### 11-用户注册.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>11:用户注册</title>
    <link rel="stylesheet" href="./css/global.css">
    <link rel="stylesheet" href="./css/register.css">
    <link rel="icon" href="./assets/favicon.ico">
</head>
<body>
    <div class="reg-container">
        <form action="./10-用户登录.html" class="reg-form">
            
            <h1>用户注册</h1>

            <input type="text" name="username" 
            placeholder="请输入用户姓名" autofocus 
            autocomplete="off" required>
            
            <input type="password" name="password"
            placeholder="请输入用户密码"
            autocomplete="off" required>

            <input type="text" name="realname" 
            placeholder="请输入真实姓名"
            autocomplete="off" required>
            
            <input type="text" name="email" 
            placeholder="请输入邮箱地址" required>

            <input type="tel" name="phone"
            placeholder="请输入电话号码" required>

            <div>
                性别: <input type="radio" name="gender" value="0"
                checked>男
                <input type="radio" name="gender" value="1">女 
            </div>

            <div>
                权限: <input type="radio" name="role" value="0" 
                checked>用户
                <input type="radio" name="role" value="1">管理员
            </div>

            <div>
                <input type="submit" value="注册">
                <input type="reset" value="取消">
            </div>
        </form>
    </div>
</body>
</html>
```

#### register.css

```css
@charset "utf-8";

/* 设置最外侧大容器 */
.reg-container {
    /* 设置容器绝对定位 */
    position: absolute;
    /* 设置容器宽度 */
    width: 100vw;
    /* 设置容器高度 */
    height: 100vh;
    /* 设置容器背景图片 no-repeat:表示如果图片大小不合适
    则不会重叠摆放 */
    background: url("../assets/bg5.jpeg") no-repeat;
    /* 设置背景图尺寸 宽度 高度 */
    background-size: 100vw 100vh;
    /* 设置背景图不会随着缩放变动 */
    background-attachment: fixed;
    /* 容器开启弹性盒子 */
    display: flex;
    /* 设置项目,也就是表单 主轴 交叉轴居中 此时默认 水平是主轴
    纵向是交叉轴 */
    justify-content: center;
    align-items: center;
}

/* 设置表单 */
.reg-form {
    /* 设置表单宽度 */
    width: 350px;
    /* 设置表单背景色 和 透明度 
    rgba(红色,绿色,蓝色,透明度)
    红绿蓝为 0-255 的整数 如果都是 255 则是白色
    透明度 0 完全透明 1 完全不透明 */
    background-color: rgba(255, 255, 255, 0.6);
    /* 设置边框为圆角 数字越大越圆,如果设置为 50%则变为圆球 
    表格不能设置 */
    border-radius: 30px;
    /* 给表单开启弹性盒子 */
    display: flex;
    /* 设置表单内的项目从上往下排列,默认是 row 从左往右 */
    flex-direction: column;
    /* 设置交叉轴水平居中摆放,注意这里从左往右是交叉轴了,从上往下
    才是主轴 因为排列顺序是从上往下 */
    align-items: center;
}

input[type=text],
input[type=password],
input[type=email],
input[type=tel] {
    /* 去掉输入框边框 */
    border: none;
    /* 去掉获取焦点后边框 */
    outline: none;
    /* 设置输入框为圆角 */
    border-radius: 10px;
    /* 设置输入框外边距 */
    margin: 5px;
    /* 设置输入框内边距 */
    padding: 5px;
    /* 添加阴影 */
    box-shadow: 5px 5px 5px silver;
}

input[type^=sub],
input[type*=ese] {
    /* 设置按钮外边距 */
    margin: 10px;
    /* 去掉按钮边框 */
    border: none;
    /* 更改为圆角 */
    border-radius: 10px;
    /* 设置按钮宽度 */
    width: 50px;
    /* 添加按钮背景色 */
    background-color: cornflowerblue;
    /* 添加按钮字体颜色 */
    color: whitesmoke;
    /* 添加阴影 前三个参数为阴影偏移量 第四个参数为阴影颜色 */
    box-shadow: 5px 5px 5px gray;
}
```

### 12-圣杯布局.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>13:表格</title>
    <link rel="stylesheet" href="./css/global.css">
    <link rel="stylesheet" href="./css/table.css">
    <link rel="icon" href="./assets/favicon.ico">
</head>
<body>
    <div class="table-container">
        <!-- 
            table:表示渲染一个表格
                tr:表示表格中的行
                td:表示表格中的列
                横行竖列,注意表格最简化必须存在 table tr td
                嵌套关系不能变
        -->
        <table class="tb">
            <!-- caption:表格的标题,不是必须 -->
            <caption>表格标题</caption>
            <!-- thead:表头,一般嵌套表格的首行,多搭配 css 使用 -->
            <thead>
                <tr>
                    <!-- th:表示列,效果同 td,但是使用在表头中
                    内部文字居中 加粗,多使用在表头中 -->
                    <th>列1</th>
                    <th>列2</th>
                    <th>列3</th>
                    <th>列4</th>
                    <th>列5</th>
                </tr>
            </thead>
            <!-- tbody:一般嵌套表格主体,多搭配 css 使用
            注意如果我们不书写,则自动添加 tbody -->
            <tbody>
                <tr>
                    <td>列1</td>
                    <td>列2</td>
                    <td>列3</td>
                    <td>列4</td>
                    <td>列5</td>
                </tr>
                <tr>
                    <td>列1</td>
                    <td>列2</td>
                    <td>列3</td>
                    <td>列4</td>
                    <td>列5</td>
                </tr>
                <tr>
                    <td>列1</td>
                    <td>列2</td>
                    <td>列3</td>
                    <td>列4</td>
                    <td>列5</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
```

#### table.css

```css
@charset "utf-8";

/* 设置外侧容器 */
.table-container{
    margin:0 auto;
    width:80vw;
    display: flex;
    justify-content: center;
    /* 从交叉轴起始位置排列 */
    align-items: flex-start;
}

/* 设置表格 */
.tb{
    /* 设置表格宽度 */
    width:60vw;
    /* 向下偏移 100 像素 */
    margin-top: 100px;
    /* 存在多条边框时合并为一根 */
    border-collapse: collapse;
    /* 设置表格内部文本居中 */
    text-align: center;
}

/* 设置表格边框 */
.tb,tr,td,th{
    /* 设置表格的边框类型 */
    border:solid 1px #ddd;
}

/* 设置表头 */
.tb thead{
    background-color: coral;
    color: navy;
}
/* 设置表格主体 */
.tb tbody{
    background-color: azure;
    color:gray;
}
```

### 13-表格.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>13:表格</title>
    <link rel="stylesheet" href="./css/global.css">
    <link rel="stylesheet" href="./css/table.css">
    <link rel="icon" href="./assets/favicon.ico">
</head>
<body>
    <div class="table-container">
        <!-- 
            table:表示渲染一个表格
                tr:表示表格中的行
                td:表示表格中的列
                横行竖列,注意表格最简化必须存在 table tr td
                嵌套关系不能变
        -->
        <table class="tb">
            <!-- caption:表格的标题,不是必须 -->
            <caption>表格标题</caption>
            <!-- thead:表头,一般嵌套表格的首行,多搭配 css 使用 -->
            <thead>
                <tr>
                    <!-- th:表示列,效果同 td,但是使用在表头中
                    内部文字居中 加粗,多使用在表头中 -->
                    <th>列1</th>
                    <th>列2</th>
                    <th>列3</th>
                    <th>列4</th>
                    <th>列5</th>
                </tr>
            </thead>
            <!-- tbody:一般嵌套表格主体,多搭配 css 使用
            注意如果我们不书写,则自动添加 tbody -->
            <tbody>
                <tr>
                    <td>列1</td>
                    <td>列2</td>
                    <td>列3</td>
                    <td>列4</td>
                    <td>列5</td>
                </tr>
                <tr>
                    <td>列1</td>
                    <td>列2</td>
                    <td>列3</td>
                    <td>列4</td>
                    <td>列5</td>
                </tr>
                <tr>
                    <td>列1</td>
                    <td>列2</td>
                    <td>列3</td>
                    <td>列4</td>
                    <td>列5</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
```

#### teble.css

```css
@charset "utf-8";

/* 设置外侧容器 */
.table-container{
    margin:0 auto;
    width:80vw;
    display: flex;
    justify-content: center;
    /* 从交叉轴起始位置排列 */
    align-items: flex-start;
}

/* 设置表格 */
.tb{
    /* 设置表格宽度 */
    width:60vw;
    /* 向下偏移 100 像素 */
    margin-top: 100px;
    /* 存在多条边框时合并为一根 */
    border-collapse: collapse;
    /* 设置表格内部文本居中 */
    text-align: center;
}

/* 设置表格边框 */
.tb,tr,td,th{
    /* 设置表格的边框类型 */
    border:solid 1px #ddd;
}

/* 设置表头 */
.tb thead{
    background-color: coral;
    color: navy;
}
/* 设置表格主体 */
.tb tbody{
    background-color: azure;
    color:gray;
}
```



---



### Javascript基本语法

#### ES规范

> 完全遵循 ECMA 的 ES 规范,ES5 和 ES6 差异较大,没有兼容性问题,每个浏览器都支持

> Java 基本数据类型:byte short char int float long double boolean

#### 基本数据类型(值数据类型)

+ **string**:字符串

  ```javascript
  //ES5
  var str = "etoak";
  var count = 100;
  //ES6 使用 let 和 const 取代 var,作为标识符
  //let:用来设置变量
  //const:用来设置常量
  //ES6语句具有 ASI 特性,语句结尾自动添加分号,我们不需要书写,但是要注意,语句必须是最终的结尾
  //在 ES6 推荐使用单引号取代双引号
  let str2 = 'etoak'
  const count = 100
  ```

+ **number**:表示数字,相当于 java 中的 int+float,范围是 正负2^53-1

  + **NaN**:not a number 的简写,表示不是一个数字
  + **Infinity**:正无穷
  + **-Infinity**:负无穷
  + **number() parseInt()  -0 +**

+ **boolean**:true 和 false,在布尔中存在**真假值**一说

  + **0 null NaN undefined false** '' 这些被称之为**假值**

  + 除去以上的数据,被称之为**真值**

  + 在流程控制时,真假值就可以当成 true 和 false 使用

    ```javascript
    let str = ''
    if(!str){
        //执行这里
    }
    const arr = [1,2,3,4]
    //只要数组长度不为 0 就执行分支内
    if(arr.length){
        //执行这里
    }
    ```

+ **null**

  + type of 数据类型为 'Object'

+ **undefined**:表示未定义,或者没有数据类型

  ```javascript
  //java
  String str = "etoak";
  int count;
  //js
  let str  //undefined
  ```

+ **bigint(ES8)**:范围超过了 2^53-1 用来弥补number对于超大数据的局限

  ```javascript
  let count = 100	//number
  let count2 = 100n //bigint
  ```

+ **symbol(ES6)**:表示一个独一无二的数据,底层开辟内存地址

#### 复杂数据类型(地址数据类型,引用数据类型)

+ **Object**:对象
  + **Array**:数组
  + **Function**:函数
    + <u>函数不管在任何情况下都有返回值,要么就是我们显式书写的 return ,要么返回 undefined</u>
  + **RegExp**:正则表达式
  + **Math**:内置对象,用来进行数学运算
  + **Date**:内置对象,用来进行日期运算

#### BOM(Browser Object Model 浏览器对象模型)

> 提供了一组内置的对象,用来与浏览器进行交互,存在一定的兼容性问题

+ **window**:表示整个浏览器窗口,顶层对象,全局变量
  + **document**:表示页面的正文部分,通过 document 可以借助 DOM 来操纵页面的结构和样式
  + **location**:表示浏览器的地址栏,一般可以获取地址信息,控制跳转等
  + **history**:表示浏览器的历史记录,缓存等信息
  + **navigator**:表示当前浏览器的信息,例如版本,内核,品牌等等
  + **screen**:捕捉当前页面屏幕信息,例如解析度,分辨率,色彩,鼠标指针
  + **frame**:页面的一个框架,是 **iframe** 的前身,已经不推荐使用

#### DOM**(Document Object Model 文档对象模型)**

> 提供了一个模型,js 可以借助 DOM 对页面的结构和样式进行操作,这个模型由以下这些节点组成
>
> DOM存在严重的兼容性问题

+ **文档节点**:就是指页面的根元素,在 html 中就是指 html 节点,一个 html 只有一个文档节点
+ **元素节点**:就是指页面中的各种元素,js 存在多种选择器,通过这些选择器可以使用 js 获取元素节点
    + ES5
        + **Node**  document.<u>getElement</u>**ById**('id')
        + **NodeList**     document.getElements**ByTagName**('tagName')
        + **NodeList**     document.getElements**ByName**('name')
        + **NodeList**     document.getElements**ByClassName**('class')
    + ES6
        + Node  document.**querySelector**('sel')
        + NodeList    document.**querySelectorAll**('sel')
+ **属性节点**:一般存在于元素节点上   <tagName 属性名="属性值" />
    + **元素节点**.**set**Attribute('属性名','属性值')
    + 元素节点.**get**Attribute('属性名')
    + 元素节点.**remove**Attribute('属性名')
+ **文本节点**:就是指页面中的文本
+ **注释节点**:就是指页面中的注释

---

# day04

### js能够直接修改页面的结构和样式吗?如果不能为什么?

js不能直接修改页面的结构和样式,当浏览器从上往下解析,如果解析无误,全部解析结束之后,会生成一个 **DOM**(Document Object Model文档对象模型)这个模型结构与样式与用户书写的完全一致,由各种节点组成,它是根在上方的树,大致如下

```text
                  document
                     |
                    html
                     |
                -----------
                |         |
               head      body
                |         | 
            -------      --------
            |     |      |      |
           title meta   button  table
                                   |
                                   tr
                                   |
                                   td
```

模型生成之后与当前页面完全一致,当使用 js 要修改页面时,<u>一般是通过 document 对象来修改DOM模型</u>,我们书写的任意的脚本都是对模型进行 **crud 操作**,当修改结束之后,模型与页面不再保持一致,**则浏览器重新渲染页面使页面与模型保持一致**,所以说并不是 js 直接去修改了 html 和 css,而是 **js 修改了 DOM 模型**,浏览器为了与 DOM 保持一致,重新渲染页面

第一个页面的例子,如果我们注释掉 window.onload 则会因为 模型根本没有创建完毕,就去模型中根据 id 属性 btn 来获取元素节点失败,从而绑定单击事件失败

---

### var 为什么被 let 和 const 取代了

> 以下是 var 存在的一些问题,以下例子为恶例

#### 无视块级作用域

```javascript
    function demo(){
        var count = 100
    }
    console.log(count)  //100
    demo()
```

#### 可以重复赋值

```javascript
    var count = 100
    ....
    ....
    var count = true
    ....
    ....
    var count = 'etoak'
```

#### 可以先调用,后声明

```javascript
    function demo(){
        count++
        count--
        count += '!!!'
        count += 'etoak'
        var count = 10
    }
    demo()
```

---

### 1-初始js.html

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1:初识 js</title>
    <!-- 类似 css 内嵌式,将 js 脚本直接书写在 script 标签 -->
    <script>
        /* js 注释与 java 完全一致,在此处不能书写 html 与 css 
        
            window:BOM七对象之首,表示当前整个浏览器窗口
            onload:激发事件,所有的激发事件都以 on 开头,此处表示
            窗口载入无误,此处表示 窗口载入没有任何问题,之后激发一个
            函数 etoak
        */
        window.onload = etoak

        /* 
            函数的格式

            function 函数名(实参){
                函数体
            }
        */
        function etoak() {
            console.log('这是无所不能的控制台打印!!!')
            //console.info('这是无所不能的控制台打印2!!!')
            //console.error('自己吓唬自己!!')
            /* 弹出一个对话框 */
            //alert('济南降温了!!!')
            /* 根据 id 从正文中获取一个元素节点,这个元素节点数据类型为
            对象 */
            let nodeBtn = document.getElementById('btn')
            /* 给元素节点绑定的一个事件,事件激发执行一个匿名函数 */
            nodeBtn.onclick = function () {
                //alert('测试!!!!')
                /* 获取表格的元素节点 */
                let nodeTb = document.getElementById('tb')
                //debugger
                /* 
                    创建一个 tr 元素节点 
                    <tr></tr>
                */
                let nodeTr = document.createElement('tr')
                /* 
                    修改 tr 元素节点的结构
                    innerHTML:向元素中插入超文本(支持标签)
                    修改前: <tr></tr>
                    修改后: <tr><td>添加的一行</td></tr>
                */
                nodeTr.innerHTML = '<td>添加的一行</td>'
                /* 向现有元素中插入元素节点,后面的元素节点作为子元素
                如果现有元素中已经存在子元素,则插入的子元素,在现有子元素
                之后 
                    <table id="tb" border="1px">
                        <tr>
                            <td>默认一行</td>
                        </tr>
                        <tr><td>添加的一行</td></tr>
                        <tr><td>添加的一行</td></tr>
                    </table>
                */
                nodeTb.append(nodeTr)
                /* 生成 0-255的随机整数 */
                let r = Math.floor(Math.random() * 256)
                let g = Math.floor(Math.random() * 256)
                let b = Math.floor(Math.random() * 256)
                /* 
                    使用 js 渲染元素节点的样式
                    元素节点.style.样式名 = 样式值
                    样式名必须使用小驼峰格式,如果原先为连字符,则自己转换为
                    小驼峰
                    background-color => backgroundColor
                    font-size => fontSize
                */
                //nodeTb.style.backgroundColor = 'rgb('+r+','+g+','+b+')'
                //ES6新特性 模板字符串 `${要输出的数据}` 可以避免字符串繁琐的拼接
                nodeTb.style.backgroundColor = `rgb(${r},${g},${b})`
            }
        }

    </script>
</head>

<body>
    <!-- 
        js如何调错 
        1:独立完成,不要依赖同位
        2:出错第一时间查看浏览器 f12 开发人员工具的控制台
        总结自己出过的错误
        3:多使用 console.log() 控制台打印,这个打印功能异常强大
        没有打印不了的数据
        4:添加断点 debugger
    -->
    <button id="btn">点我试试!</button>

    <table id="tb" border="1px">
        <tr>
            <td>默认一行</td>
        </tr>
    </table>
    <script>
        /* 
            将 js 脚本书写在文末有以下好处
            1:优先加载 html 和 css
            2:保证模型创建完毕
        */
    </script>
</body>

</html>
```

### 2-引入js的方式和函数的执行.html

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>2:js引入方式和函数得执行</title>
</head>
<!-- 1:类似 css 行内式,不推荐使用 -->
<!-- <body onload="javascript:alert('测试!')"> -->
<!-- <body onload="alert('测试!')"> -->
<!-- 这种书写方式可以使用,这里函数 etoak 被调用 传递了
三个实参 -->

<body onload="etoak(100,true,'测试')">

    <script>
        /* 此处函数中三个为形参 可以随意书写 */
        function etoak(arg1, arg2, arg3) {
            console.log(arg1, arg2, arg3)
        }

        /* 函数要么绑定事件,要直接调用 */
        function test() {
            console.log('测试---------')
            let SEASON = 3
            if (SEASON >= 1 && SEASON <= 3) {
                console.log('春天来了')
            } else if (SEASON >= 4 && SEASON <= 6) {
                console.log('夏天来了')
            } else if (SEASON >= 7 && SEASON <= 9) {
                console.log('秋天来了')
            } else if (SEASON >= 10 && SEASON <= 12) {
                console.log('冬天来了')
            }

            /* 弹出对话框,内部可填写内容,如果不写就是后面的
            默认值 */
            let val = prompt('现在是几月?为什么这么冷?', 3)
            console.log(typeof +val, typeof (val - 0))
            /* 
                =:赋值
                ==:比较两个数据是否一致,如果不是同一种类型,则
                转换为同一种类型,再进行比较
                ===:比较两个数据是否一致,如果数据类型不一致,立刻
                返回 false,如果一致,再进行比较
            */
            if (+val === 3) {
                console.log('春天就要来了,再等一周就热了!!')
            }

            /* 直接从页面输出,支持标签 */
            let i = 100
            let j = 'etoak'
            let x = true
            let y = 1.1
            let z = null

            document.write(`${i}+${j}=${i + j}<br>`)
            document.write(`${i}+${y}=${i + y}<br>`)
            document.write(`${i}+${z}=${i + z}<br>`)
            document.write(`${x}+${j}=${x + j}<br>`)
            document.write(`${y}+${j}=${y + j}<br>`)
            document.write(`${x}+${y}=${x + y}<br>`)
            document.write(`${y}+${z}=${y + z}<br>`)

            /* 使用 document.write 从页面输出 99 乘法表 */
            let str = ''
            for(let a = 1;a<=9;a++){
                for(let b = 1;b<=a;b++){
                    str += `${b}*${a}=${b*a}\t`
                }
                str += '<br>'
            }
            document.write(str)
        }

        test()

        /* 使用函数表达式来书写函数 */
        /* 
            ES6新特性 箭头函数
            如果出现匿名函数,则可以省略 function 单词
            在参数之后添加 =>,如果只有一个参数,则小括号
            可以省略,如果没有或者一个以上参数,则小括号不能省略
            如果函数体只有一句,或者直接书写的 return 语句,则
            大括号 return 都可以省略
        */
        let test2 = () => console.log('thisistest2---------')

        //console.log(test2)
        test2()
    </script>
</body>

</html>
```

### 3-字符串函数.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>3:字符串函数</title>
</head>
<body>
    <script>
        let str = 'http://www.etoak.com'

        /* 
            Java:
                String          length()
                Array           length
                List            size()
            Js:
                length
        */
        console.log(`字符串长度是---->${str.length}`)
        console.log(`将 e 替换为 a---->${str.replace('e','a')}`)
        console.log(`从左往右第一个 w 的索引是---->${str.indexOf('w')}`)
        console.log(`从左往右最后一个 w 的索引是---->${str.lastIndexOf('w')}`)
        console.log(`获取索引是 8 的字符---->${str.charAt(8)}`)
        /* 
            substring(x,y)
                x:表示从索引值 x 开始截取(包含)
                y:截取到索引值 y(不包含) 
            substr(x,length)
                x:表示从索引值 x 开始截取(包含)
                length:表示截取的长度
            以上两个函数参数可以只写一个,用法相同,不能是负值
            slice(x,y):与 substring 完全一致,但是 x,y可以设置
            负值,从右往左算
        */
        console.log(`截取字符串---->${str.substring(3,5)}`)
        console.log(`截取字符串---->${str.substr(3,5)}`)
        /* split():分割字符串,在括号内设置的分隔符处开始分割 */
        let knife = str.split('.')
        for(let i = 0;i<knife.length;i++){
            console.log(knife[i])
        }    
    </script>
</body>
</html>
```

### 4-数组.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4:数组</title>
</head>
<body>
    <script>
        /* 1:直接通过复杂类型提供的构造器创建 */
        const arr = new Array()
        arr[0] = 100
        arr[1] = true
        arr[2] = 'etoak'
        arr[3] = null
        /* ES5 for in 循环 */
        for(let index in arr){
            console.log(arr[index])
        }
        /* 2:直接创建数组 */
        const arr1 = [1,2,3,4,5]
        console.log(arr1)
        /* push():从数组结尾追加数据 */
        arr1.push(99)
        console.log(arr1)
        /* unshift():从数组头部添加数据 */
        arr1.unshift(0)
        console.log(arr1)
        /* shift():从数组头部删除数据 */
        arr1.shift()
        console.log(arr1)
        /* pop():从数组结尾删除数据 */
        arr1.pop()
        console.log(arr1)
        /* reverse():逆序排列 */
        arr1.reverse()
        console.log(arr1)
        /* sort():正序排列 */
        arr1.sort()
        /* splice():删除
            splice(起始索引,删除个数,替换的值)
        */
        arr1.splice(0,1,777)
        //[777, 2, 3, 4, 5]
        console.log(arr1)
        /* 
            push() unshift() shift() pop() sort()
            reverse() splice()
            以上七个函数可以对原数组进行修改
            -------------------------------
            以下三个函数对原数组没有任何影响,会生成新的数组

            filter():过滤函数
            数组.filter(function(alias){
                return 过滤条件
            })
            只要符合过滤条件,则返回,最终生成一个新的数组,
            原数组不受影响
        */
        const newArr = arr1.filter( a => a>=4 )
        console.log(newArr)
        /* 
            find():与 filter()几乎完全一致,仅仅返回
            第一个符合条件的
        */
        let val = arr1.find( a => a>=4 )
        console.log(val)
        /* 
            map():多使用在对象中,如果使用在数组中,
            如果书写表达式,则仅仅返回一个数组,符合表达式
            则返回 true 不符合 返回 false
        */
        const newArr2 = arr1.map( a => a>=4 )
        console.log(newArr2)

        /* indexOf():查询字符的索引
        如果查询不到则返回-1 */
        let value = arr1.indexOf(8888)
        console.log(value)

        /* 
            对于基本数据类型来说,let 赋值则是变量
            const 赋值为常量,数据不能存在任何变动,但是
            对于复杂类型来说,如果使用 let 则每次变动都要
            重新进行寻址,内存要开辟地址,而如果使用 const 则
            事先划定好了地址,不会进行频繁的寻址,非常节省资源
        */
            
        const demo = [1,2,3,4,5]
        /* ES6 for of 迭代 */
        for(let value of demo){
            console.log(value)
        }

        /* ES6 forEach */
        demo.forEach((a,index) => 
        console.log(`第${index}个元素是${a}`))
    </script>
</body>
</html>
```



#### 常用的几个函数push() unshift() shift() pop() sort() reverse() splice() ||filter() find() map()

#### let const

​	   对于基本数据类型来说,**let** 赋值则是**变量**

​      **const** 赋值为**常量**,数据不能存在任何变动,但是

​      对于复杂类型来说,<u>如果使用 let 则每次变动都要</u>

​      <u>重新进行寻址</u>,内存要开辟地址,而如果使用 const 则

​      事先划定好了地址,不会进行频繁的寻址,非常节省资源

#### for遍历的几种方式

```html
/* ES5 for in 循环 */
for(let index in arr){
	console.log(arr[index])
}
        /* ES6 for of 迭代 */
        for(let value of demo){
            console.log(value)
        }

/* ES6 forEach */
demo.forEach((a,index) => 
	console.log(`第${index}个元素是${a}`))
```

### 5-对象.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>5:对象</title>
</head>
<body>
    <script>
        /* 1:使用对象字面量创建对象
            let|const 对象名 = {
                属性名:属性值,
                属性名:属性值,
                属性名:属性值,
            } 
            属性名:没有引号,就表示一个属性,浏览器在解析时
            会将其解析为字符串,但是我们书写时,不要书写引号
            属性值:根据数据类型决定
        */
        let name = '胡桃'

        const person = {
            /* 
                ES6新特性
                如果属性名与属性值恰好重名,则仅仅书写属性名
            */
            name,
            age:17,
            hobby:['逛街','恶作剧'],
            info:{
                address:'璃月',
            }
        }
        console.log(person)
        /* 
            使用对象自带的两个迭代
            迭代所有的属性名
        */
        console.log(Object.keys(person))
        /* 迭代所有的属性值 */
        console.log(Object.values(person))
        /* 
            获取某个属性值
            属性值 = 对象.属性名
            属性值 = 对象['属性名']
        */
        console.log(person.name,person['name']
        ,person.hobby[0],person.info.address)

        /* 删除属性和属性值 delete 对象.属性名
        如果删除成功则返回 true 失败 false */
        console.log(delete person.age)

        /* 
            添加属性
            对象.属性名 = 属性值
        */
        person.myAge = 17

        /* 2:直接使用构造器构造 */
        const obj = new Object()
        obj.name = '张三'
        obj.age = 30
        console.log(obj)
    </script>
</body>
</html>
```

#### 对象自带的两个迭代

```html
let person = {
	name,
	age,
	hobby,
}
		/* 
            使用对象自带的两个迭代
            迭代所有的属性名
        */
        console.log(Object.keys(person))

        /* 迭代所有的属性值 */
        console.log(Object.values(person))
```

#### 添加对象属性

```html
        /* 
            添加属性
            对象.属性名 = 属性值
        */
        person.myAge = 17

        /* 2:直接使用构造器构造 */
        const obj = new Object()
        obj.name = '张三'
        obj.age = 30
        console.log(obj)
```

### 6-动态表格.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>6:动态表格</title>
    <link rel="stylesheet" href="./css/dynamicTable.css">
</head>
<body>
    <div class="container">
        <header class="header">
            <input type="text" placeholder="请输入姓名"
            autofocus autocomplete="off">
            性别: <input type="radio" name="gender" value="0"
            checked>女
            <input type="radio" name="gender" value="1">男
            归属地: 
            <select name="location" id="location">
                <option value="济南">济南</option>
                <option value="青岛">青岛</option>
                <option value="淄博">淄博</option>
                <option value="济宁">济宁</option>
            </select>
            <input type="button" value="添加"
            onclick="add()">
        </header>
        <section class="main"></section>
    </div>
    <!-- 引入外部独立的 js 文件,注意存在先后顺序,从上往下先引
    外部资源 -->
    <script src="./js/datas.js"></script>
    <script>
        /* 函数 A:此函数用来组装动态表格 */
        function query(){
            let table = 
            `<table class="tb">
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>居住地</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>`
            
            empList.forEach((emp,index)=>{
                table += 
                `<tr>
                    <td>${index+1}</td>
                    <td>${emp.name}</td>
                    <td>${emp.gender===0?'女':'男'}</td>
                    <td>${emp['location']}</td>
                    <td><span style="cursor:pointer"
                    onclick="remove(${index})">删除</span></td>
                </tr>`
            })

            table += '</tbody></table>'

            document.querySelector('.main').innerHTML = table
        }

        query()

        /* 函数 B:此函数用来进行删除操作 */
        function remove(index){
            /* 弹出一个可选择式对话框 */
            if(confirm('您确定删除本条记录吗?')){
                /* 删除 */
                empList.splice(index,1)
                /* 回过头来重新查询,这种操作被称之为:回显
                这里复用函数 A */
                query()
            }
        }
    
        /* 函数 C:此函数用来进行添加操作 */
        function add(){
            /* 
                1:获取用户姓名 
                trim():去掉字符串两侧空格
            */
            let name = 
            document.querySelector('input[type=text]').value.trim()

            if(!name){
                alert('请输入有效内容...')
                return
            }

            /* 2:获取性别 */
            /* 设置性别默认值 */
            let gender = 0

            let nodeRadios = 
            document.getElementsByName('gender')

            nodeRadios.forEach(nodeRadio=>{
                /* 如果某一个被选中了 */
                if(nodeRadio.checked){
                    gender = (nodeRadio.value-0)
                }
            })

            /* 3:获取归属地 */
            let location = 
            document.getElementById('location').value

            /* 添加进数组 */
            empList.push({
                /* 注意此处由于没有真正的主键,因此直接使用数组
                长度+1 这是非常不标准的书写方式 */
                id:empList.length+1,
                name,
                gender,
                location,
            })
            
            /* 回显 */
            query()
        }
    
    </script>
</body>
</html>
```

---
---
> 
>
> 

---



#### substring-substr-slice区别

![](G:\ET2301\JavaStudy\Java-professional-programmer\前端\img\substring-substr-slice区别.png)



---

# day05

### 7-分离运算符.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>7:分离运算符</title>
</head>
<body>
    <script>
        /* 
            ES6 新特性 分离运算符 可以使用在 字符串 数组 对象中 
            ...:用三个点表示分离运算符
            就是将...后面的字符串 数组 或者对象 分离
            又被称之为 spread syntax 分离语法
            
            1:字符串
                将字符串分为一个一个字符
        */
        let str = 'etoak'
        console.log(...str,[...str])
        /* 
            2:数组
                将数组中的数据从中括号中取出来
        */
        const arr = [1,2,3,4,5]
        console.log(...arr)
        const arr2 = [...arr,99,true]
        console.log(arr,arr2)
        
        /* 
            const test = function(a,b,c,d,e){
                return a+b+c+d+e
            }
        */
        const test = (a,b,c,d,e) => a+b+c+d+e
        console.log(test(...arr))

        /* 3:对象
            ...对象:就是将对象中的属性名 属性值从对象中取出
        */
        const obj = {
            name:'张三',
            age:20,
        }

        const obj2 = {...obj,address:'济南',}
        console.log(obj,obj2)
        

    </script>
</body>
</html>
```

### 8-深拷贝和浅拷贝.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>8:浅拷贝和深拷贝</title>
</head>
<body>
    <script>
        /* 
            深拷贝:拷贝之后,修改其中任意一个,另一个不受影响,即所谓,你变我不变
            浅拷贝:拷贝之后,修改其中任意一个,则另一个也会被修改,即所谓,你变我也变

            1:等号
                基本数据类型,则是深拷贝
                复杂数据类型,则是浅拷贝
        */
        let str1 = 'etoak'
        let str2 = str1
        console.log(str1,str2)
        str2 += '!!!!'
        console.log(str1, str2)

        const arr1 = [1,2,3,4,5]
        const arr2 = arr1
        console.log(arr1,arr2)
        arr2.push(99)
        console.log(arr1, arr2)
        /* 
            2:分离运算符 Array.from() slice() Object.assign()
                对于基本数据类型,分离运算符肯定是深拷贝
                对于复杂类型,则要具体分析
                    如果修改的表层数据为基本类型,则深拷贝
                    如果修改的表层数据为复杂类型,则浅拷贝
                注意在打印时,对于复杂类型,点开箭头要查看复杂类型
                内部结构时,浏览器会重新寻址进行一次查询,保证数据是最新
                的,因此这里头两次打印 也会变为最新的 '学习'
        */
        const obj1 = {
            name:'胡桃',
            age:17,
            hobby:['逛街','恶作剧'],
            info:{
                address:'济南',
            },
        }

        const obj2 = {...obj1}
        console.log(obj1,obj2)
        obj2.name = '甘雨'
        obj2.age = 26
        obj2.hobby[0] = '学习'
        console.log(obj1, obj2)

        /* 
            3:  JSON.parse(JSON.stringify())
            肯定是深拷贝,又名万能转换器
        */

    </script>
</body>
</html>
```

---

### jQuery（NODE | NPM）

#### Write Less Do More

> 由美国人John Resig 独立发布Javascript前端类库，jQuery可以使用更简短的代码通过对js的封装实现更多功能，完全借鉴了css选择器机制，可以通过许多手段更快速更精确的拿取元素，实现了代码链机制，基本解决了浏览器差异性问题

#### <u>使用jQuery类库的步骤</u>

1. 安装Node
2. 安装Node之后NPM平台就安装成功了
3. 进入终端 输入 npm -v 可以查看npm平台版本号
4. 使用npm初始化本工程，相当于用npm接管我们的前端工程
5. npm init -y
6. 更改国内节点 npm set registry https://registry.npm.taobao.org/
7. 下载jQuery依赖  npm i jquery
8. 在 node_modules中找到jquery/dist/jquery.js 引入页面即可使用 dist:distribution的简写，表示发布版

---

### 1-Jquery选择器.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1:jquery选择器</title>
</head>
<body>
    <div class="container">
        <span>段落之前的 span</span>
        <p id="pra">
            <span>段落内部的 span</span>
        </p>
        <span>段落之后紧邻的 span</span>
        <span>段落之后不紧邻的 span</span>
    </div>
    <!-- 
        引入 jQuery 依赖,注意一定要先引入,再写我们自己的 
        dist:distribution的简写,表示发布版
    -->
    <script src="../node_modules/jquery/dist/jquery.js"></script>
    <script>
        /* 
            # jQuery 元素与 js 的元素节点是同一种元素吗?如果不是为什么?两者
            如何进行转换?

            <span id="etoak">测试</span>

            js:
                let nodeSp = document.getElementById('etoak')
                <span id="etoak">测试</span>
            
            jQuery:
                let $sp = $('#etoak')
                [<span id="etoak">测试</span>]

            jQuery元素与 js 元素节点不是同一种元素,它是对 js 节点的一个
            轻度的封装,jQuery元素只能使用自己的 函数 方法 属性 同样 js 节点
            也只能使用自己的函数 方法 属性,两者不能通用

            nodeSp.innerHTML = XXXX
            $sp.html(XXXX)

            两者如何转换
            jQuery => js节点
            $sp.get(0)
            $sp[0]

            js节点 => jQuery
            $(nodeSp)
            $(document)
            $(location)
            $(history)
            -------------------------------------------
            jQuery选择器
            1:  $('tagName')
            2:  $('.class')
            3:  $('#id')
            4:  $('tagName.class')
                $('tagName#id')
            5:  $('sel1,sel2,selN')
            6:  $('sel1 sel2 selN')
            7:  $('*')
            8:  $('[属性名=属性值]')
            9:  $('[属性名^=属性值开头]')
            10: $('[属性名$=属性值结尾]')
            11: $('[属性名*=包含属性值]')

            12: $('sel1 > sel2')
            获取特定元素的子元素,注意仅仅只能获取子元素

            使用 jquery 渲染样式
            sel.css('样式名','样式值')
            sel.css({
                样式名:样式值,
                样式名:样式值,
                样式名:样式值,
            })
            注意没有引号的写法中,样式名必须使用小驼峰格式
        */
        $('.container > span').css({
            backgroundColor:'coral',
            color:'whitesmoke',
        })
        /* 
            13: $('sel1 + sel2')
                以下三个条件必须全部满足
                a:向下选取
                b:必须紧邻
                c:互为兄弟
        */
        $('p#pra + span').css('border','solid 2px purple')
        /* 
            14: $('sel1 ~ sel2')
                以下两个条件必须全部满足
                a:向下选取
                b:互为兄弟
        */
        $('p#pra ~ span').css('font-style','italic')
    </script>
</body>
</html>
```

### jQuery 元素与 js 的元素节点是同一种元素吗?如果不是为什么?两者如何进行转换?
​      jQuery元素与 js 元素节点**不是**同一种元素,它是对 js 节点的一个

​      **轻度的封装**,jQuery元素只能使用自己的 **函数 方法 属性** 同样 js 节点

​      也只能使用自己的**函数 方法 属性**,<u>两者不能通用</u>

### 2-事件动作.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>2:事件动作</title>
    <style>
        .red{
            background-color: crimson;
        }
        .blue{
            background-color: cornflowerblue;
        }
    </style>
</head>
<body>
    <button>你点我试试!</button>
    
    <input type="button" value="添加一行">

    <table border="1px" class="tb">
        <tr>
            <td>默认的一行</td>
        </tr>
    </table>

    <div style="width:100px;height:100px;border:solid 1px #ddd">
        测试域
    </div>

    <ul class="myul">
        <li>列表1</li>
        <li>列表2</li>
        <li>列表3</li>
        <li>列表4</li>
        <li>列表5</li>
    </ul>

    <script src="../node_modules/jquery/dist/jquery.js"></script>
    <script>
        /* 表示全文加载无误之后执行 ready()内部的回调函数
        ready()非常类似 js 中得 window.onload,不同的是
        ready()可以书写多个,从上往下执行,window.onload 全文只能书写一次
        ready()还对内部的脚本起到的保护的作用,效果等同于 js 中的
        自调用函数 */
        $(document).ready(function(){
            /* 1:给特定 jQuery 元素绑定多个事件
                sel.on('事件1 事件2 事件N',function(){

                })

                $(':contains(text)'):获取包含特定文本的
                指定元素
                jQuery的事件就是 js 的事件去掉 on 前缀
                mouseout:鼠标滑出
                mouseover:鼠标滑过
            */
            $('button:contains(点我)').on('click mouseout',
            function(){
                /* 
                    $(this):表示已经获取的元素列表 
                    attr(属性名,属性值):把元素的属性名更改为属性值
                */
                $(this).attr('disabled',true)
            })

            /* 
                2:给元素绑定特定事件
                sel.事件(function(){

                })
            */
            $(document).dblclick(function(){
                console.log('谁双击我了!!!')
            })

            $('input[type=button]').click(function(){
                /* append():向元素中追加子元素 */
                $('.tb').append('<tr><td>添加的一行</td></tr>')
            })

            /* 3:鼠标滑过元素
            
                sel.hover(
                    function(){
                        //鼠标滑过执行此函数
                    },
                    function(){
                        //鼠标滑出执行此函数
                    }
                )
            */
            $('div:contains(测试域)').hover(
                function(){
                    /* 
                        addClass():添加 class 属性
                        removeClass():删除 class 属性
                        toggleClass():如果存在则删除,如果不存在则添加
                        一个元素可以添加多个class
                        注意 id 无上面任何操作
                    */
                    $(this).removeClass().addClass('red')
                },
                function(){
                    $(this).removeClass().addClass('blue')
                }
            )

            /* 
                4:迭代                 
                sel.each(function(index,alias){

                })
            */
            $('.myul li').each(function(index){
                if(index===3){
                    $(this).addClass('red')
                }
            })
        })
    </script>
</body>
</html>
```

---

---

### 第一周复习

​              

1.请简单作答js中Function是否肯定存在返回值,如果不是为什么?如果存在返回什么?(10分)

肯定存在返回值,要么是return的数据要么是undefined

肯定存在返回值，要么是return的数据 要么是undefined

2.请简述JavaScript中基本数据类型有哪些?复杂数据类型有哪些(仅写出类型即可,单词拼写错误不得分10分)

基本数据类型 string number null undefined boolean symbol bigint

复杂数据类型 Object Array Function RegExp Math Date

基本数据类型 string number null undefined boolean symbol bigint
复杂数据类型 Object Array Function RegExp Math Date

3.请说出flex弹性布局中,什么是弹性容器?什么是项目?什么是主轴?什么是交叉轴? (请画图表示10分)

见课件pdf图片

 

4.请画图表示什么是盒子模型(共14个参数,单词拼写错误 少些 漏写均不得分10分)

见课件图片

 

5.请详细简述CssFlex弹性盒子中 flex:1 是什么意思? (注意单词拼写错误不得分10分)

注意此参数仅仅针对项目,完全版书写如下,flex:flex-grow flex-shrink flex-basis;

默认值 flex:0 1 auto;

flex-grow:默认是0,如果弹性容器具有剩余空间,则本项目也不放大,如果更改为1,则如果弹性容器具有剩余空间,则本项目放大装满整个容器

flex-shrink:默认是1,如果弹性容器缩小,剩余空间减少,则项目会随之减小,如果设置为0,则项目不会变小,溢出容器

flex-basis:默认是auto,就是设置本项目的盒子模型参数,但是优先级较本身的设置低,所以这里一般不设置盒子模型,使用默认值auto,那么根据以上三个参数最终书写为

flex:0 1 auto;默认值可以省略,所以更改为 flex:0;

我们现在需要项目的特性是如果容器存在剩余空间,则项目放大,如果没有剩余空间,则项目缩小,所以这里要将0改为1

最终 flex:1;

 

6.请说出三种Css可以使用页面整体居中的单位书写方式 (10分)

px 像素

%百分比 注意必须设置 html,body{height:100%}

vw,vh 

px:像素,固定单位

%:百分比,与自己的父元素进行比较

vw:当前视口的宽度 vh:当前视口的高度,

 

7.现有数组const arr = [3,16,22,78,55,0],请写出脚本获取一个新数组,要求数据必须大于等于40,原数组不能受到任何影响,请写出脚本;要求返回第一个大于等于70的数字,请写出脚本(两问各5分,仅写出关键脚本即可,不需要书写函数调用等,单词拼写错误不得分 )

const newArr = arr.filter(a => a >=40)

let val = arr.find(a => a>=70)

 

8.根据以下要求书写脚本: 使用对象字面量创建Js对象,对象名为 stu ,属性名分别为 username realname gender email,属性值分别为 'tom' '张三' 0 'et@et.com',方法 run(),调用方法在控制台输出 'tom的真实姓名是张三,邮箱地址为et@et.com ,请写出关键脚本(单词拼写错误不得分10分)

const stu = {

username:'tom',

realname:'张三’,

gender:0,

email:’et@et.com’,

run(){

console.log(`${this.name}的真实姓名是${this.realname},邮箱地址是${this.email}`)

}

 

9.请说出Js数组中七个修改数组的函数的作用(仅写出函数名,简述其作用即可,单词拼写错误不得分10分)

push():数组末尾增 unshift():数组头部增 shift():数组头部删 pop():数组尾部删 sort():正序排列 reverse():逆序排列splice():根据传入的索引 删除或者替换

 

10.请说出几种你所知道的ES6规范新特性,不得少于5个(10分)

 for of

 forEach

let const

箭头函数

模板字符串

对象属性简略写法

symbol

方法简略写法

document.querySelector()

document.querySelectorAll()

---



### NPM使用指南

> **NPM使用指南.pdf  仔细查阅,要求能够非常熟练的使用 npm <u>初始化工程</u>,<u>下载依赖</u>,能够分清什么是 <u>生产依赖</u> 什么是 <u>开发依赖</u>** 

```nginx
##初始化项目
命令: npm init 
##全部由系统决定，不再⾃⼰填写相关信息
命令: npm init -y 
##查看全局安装的⽬录
命令: npm root -g 
##修改全局安装的⽬录地址
命令: npm config set prefix "c:\XXX" 
##查看全局已经安装的模块
命令: npm list -g 
##⽣产环境安装 || ⽣产环境安装将依赖信息添加进package.json⽂件的dependencies中（⽣产环境依赖）
命令:  npm i modelName@版本号 --save
 	  npm i modelName@版本号 -S
##开发环境安装 || 开发环境安装将依赖信息添加进package.json⽂件的devDependencies中（开发环境依赖）
命令:  npm i modelName@版本号 --save-dev
	  npm i modelName@版本号 -D
##
##其他常用指令
##
命令: npm install 
##如果在现成的⼯程中只有package.json⽂件，⽽没有node_Moduels⽂件夹，这是我们需要通过此命令下
##载这些js库，输⼊此命令后，会⾃动根据package.json中的依赖来下载js类库和其它依赖

命令: npm list 
##查看本地安装的所有模块

命令: npm view modelName version 
##查看远程模块最新版本
##eg: npm view jquery version

命令: npm uninstall modelName 
##卸载局部模块

命令: npm uninstall -g modelName 
##卸载全局模块

命令: npm get registry 
##查看当前使⽤的镜像

命令: npm config set registry https://registry.npm.taobao.org
##更换淘宝镜像，⽹络状况较为稳定
```



---























































