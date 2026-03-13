### 基于SpringBoot + Vue的众包配送接单小程序.

同城货运、众包配送、车货匹配平台、物流抢单、运力调度、闪送系统。

###### 管理员：
公告信息 、用户地址 、优惠券管理 、订单评价 、兑换记录 、物品管理 、订单管理 、员工管理 、员工收益 、用户管理 、提现记录 、员工审核 、接单中心 、投诉记录 、消息通知 、社区论坛 、贴子回复 、价格规则 、配送排行、数据统计。

###### 用户/配送员：
小程序账户注册登录、个人信息修改、优惠券兑换、地址管理、我的订单、我的收益、消息通知、社区论坛、发帖回复、积分兑换、我的优惠券、下单配送计算、在线沟通、申请配送员、在线接单、订单评价、订单投诉。

##### 账户管理与准入审核
###### 用户/员工管理： 统一管控配送员与客户账号，支持多角色注册及基本资料维护。

###### 员工审核/申请： 严格把控配送员入驻资质，通过后台审核机制确保运力团队合规。

###### 地址管理： 提供多地址录入与地图选址，为配送路径规划提供精准的坐标支撑。

##### 核心业务与调度中心
###### 下单/接单中心： 用户在线提交配送需求，系统实时推送到接单大厅供配送员抢单。

###### 订单/价格规则： 灵活设定计费策略与里程计价，实现自动算费与全流程订单状态追踪。

###### 配送排行： 实时展示配送效率与单量排行，通过竞争激励机制提升运力响应速度。

##### 金融激励与运营体系
###### 优惠券/兑换： 建立积分兑换与优惠促销体系，通过营销手段提升用户黏性与下单率。

###### 收益/提现记录： 自动结算配送员每单收益，提供明细查询与安全的资金提现流程。

###### 数据统计： 聚合交易额、订单量等核心指标，通过可视化图表辅助经营策略调整。

##### 互动沟通与服务保障
###### 社区论坛/贴子： 提供师生/邻里交流版块，支持发帖讨论，构建活跃的物流生态社区。

###### 在线沟通/通知： 实现用户与配送员实时互联，通过消息推送确保关键信息即时送达。

###### 投诉/评价管理： 记录用户真实反馈并处理服务纠纷，保障配送服务质量与平台声誉。

#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 默认后台账户密码
[管理员]
admin
1234qwer

[用户]
微信登录

#### 项目截图
暂无

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/f0aae20c-0515-4bba-b0d2-e8ba34321cd8.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/17936bec-d97b-4e93-97e8-d2c635279193.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/ed7dfc29-baeb-4c89-8985-5bc1f36bee6d.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/410dbf7e-87be-4e04-a72b-21596e8e5d72.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/e1707f76-25ce-4f5c-a9ef-afdc9e3c9cd7.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/236b98a4-b5c5-4a00-939a-d360876f86f6.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/e82bf34b-5ab4-49d9-b240-9f34a6c7b622.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/167fd88b-dea6-4cb3-8445-b68503be6112.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/db6dfb84-1ba5-4607-9752-13139fb08a6c.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/85c27f73-60ae-488e-9624-3fac70ff6f9c.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/d05fd435-34cc-4839-9607-98ee1450146d.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/063c5404-857b-458a-811b-d38a596c4522.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/c4a82bac-17c8-4a87-a358-d09407cb5bb1.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/03e0f4d5-7e36-46d4-a768-45d4c2031a8f.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/99734fec-0640-4d51-acc4-5cdacf9e39fc.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/fa261320-6ddd-4062-bfa7-2428ac51e867.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/77866c9f-07d8-42ea-8f1d-e60893611b32.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/f8b8155d-9792-43e8-99a2-446e2b52cda8.png) |

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/5895f2b0-eead-4750-96e3-295132c74a46.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/ffb261a9-8fc2-445a-8a89-d48a9c881d55.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/4305d395-23e2-40ea-8594-5467b1c3bbbe.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/db14dcf3-b919-4607-898f-cc8fdb000233.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/932f8e6c-b09b-4673-932c-78a8afff9062.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/b54cfd86-5843-48a6-8016-48bb9f9ca39a.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/89b86181-eb7a-4a13-a4eb-681fdd91dc24.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/a6e218ec-7bdb-4f95-b6a2-d71f6729498b.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/004a760d-16c3-4ee6-b5af-227005f2c87d.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/91432dd2-a237-4b80-b3d6-5e04efbce207.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/0d5417e1-4e2a-454c-bbbb-1323980a2d85.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/68231e46-283f-46c1-b6ae-dab14bfd0ca0.png) |

![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/work/936e9baf53eb9a217af4f89c616dc19.png)

#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

#### 其它资源

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)

#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)

<p><img align="center" src="https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/%E5%90%88%E4%BD%9C%E7%89%A9%E6%96%99%E6%A0%B7%E5%BC%8F%20(3).png" alt="fankekeke" /></p>
