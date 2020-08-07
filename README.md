# 这是一个基于uniapp+Springboot+jpa实现的微信小程序简单登录框架

## 简介

本框架提供初学者学习，具有可用性，没有做token拦截和过多的封装，可根据自己需求更换jpa为mybatis等

## 使用

* 先把项目拉取到本地
`git clone https://github.com/zsptsf/uniapp.git`

* 后端代码为unidemo文件夹，把文件夹unidemo\src\main\java\com\zspt\demo\util下的WechatUtil.java的小程序appId和小程序secret修改成你自己的，运行

* 前端端代码为unilogin文件夹，把根目录下的manifest.json的uni-app应用识别appid(方便以后使用云插件)和小程序appId修改成你自己的，并在HBuilder中运行

* 更多详细 参考我的文章 [https://blog.csdn.net/qq_42783654/article/details/107862585](https://blog.csdn.net/qq_42783654/article/details/107862585)