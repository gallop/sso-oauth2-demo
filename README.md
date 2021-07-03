# sso-oauth2-demo
本项目主要内容是 给出一个sso单点登录，从前后端分离的应用，到统一认证服务端一个完整的登录流程。

- gallop-common 公用类模块
- gallop-user-center 应用的后端模块
- gallop-vue-demo 应用的前端模块，给出一个vue 实现的简单前端
- sso-spring-oauth2-server 基于oauth2的统一认证服务端的实现

注意点：

- 前端的单点登录调用的接口都统一由后端中转，避免让前端管理单点登入的相关账户和参数；
- 因为前后端分离的应用，所以获取code的操作要由前端来发起，使用location.href=url 的方式来请求接口地址；
- 前端需要提供一个回调地址，并在这个地址对应的页面处理获取code值，并根据code值请求获取token的接口；
- 统一认证服务端使用的是spring security oauth2 来实现，因为已经基本封装好，所以做些配置操作即可；
- 认证服务端 使用mysql数据库存储clientId 信息和用户帐户信息；
- 认证服务端使用redis 存储session 和 单点登入生成的token（redis 使用哨兵模式，增加redis的可靠性）；

整个前后端分离应用的单点登录流程大概如下（来自网络上的图）：

<img src="https://media.mygallop.cn/images/md/sso-flow-chart.png" style="zoom:75%; " />



测试步骤：

1、启动mysql、redis服务

2、启动统一认证服务（sso-spring-oauth2-server）

3、启动应用后端服务（gallop-user-center）

4、启动应用前端服务（gallop-user-center），命令 npm run dev

5、从打印日志及浏览器控制台可以查看单点登录获取的相关数据及token
