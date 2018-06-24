#JAVAEE网上书城项目接口文档

###1.用户模块接口
#### 1-1. 注册

**接口地址**

http://localhost:8080/shop/register

**请求方式**

HTTP-POST

**请求参数**

| 参数名称  | 参数介绍 | 参数格式 | 示例              | 是否必须 | 其他         |
| --------- | -------- | -------- | ----------------- | -------- | ------------ |
| username  | 用户账号 | 字符串   | zhangsan          | Y        | 可以用来登陆 |
| password  | 用户密码 | 字符串   | 123456Aa@         | Y        | 唯一         |
| email     | 用户邮箱 | 字符串   | 626626626@163.com | Y        | 可以用来登陆 |
| telephone | 手机号码 | 字符串   | 13012011110       | Y        | 可以用来登陆 |
| gender    | 用户性别 | 单字符串 | 男                | Y        | 无           |
| introduce | 个人介绍 | 字符串   | 我是一位小哥哥    | N        | 无           |

**返回参数**

注册成功:

网址:registersuccess 

附带用户信息,会自动登录



注册失败:

网址:register



#### 1-2. 注册后台验证

**接口地址**

http://localhost:8080/shop/checkuserinfo

**请求方式**

HTTP-GET

**请求参数**

| 参数名称 | 参数介绍                  | 参数格式 | 示例     | 是否必须 | 其他               |
| -------- | ------------------------- | -------- | -------- | -------- | ------------------ |
| userInfo | 用户验证信息,分别使用     | 字符串   | zhangsan | Y        | 用户名/邮箱/手机号 |
| type     | 用户验证信息类型,分别使用 | 字符串   | username | Y        | 唯一               |

**返回参数**

字符串 true 验证成功

字符串 false 验证失败



#### 1-3. 注册验证码图片

**接口地址**

http://localhost:8080/shop/imageCode

**请求方式**

HTTP-POST

**请求参数**

无

**返回参数**

图片的src路径



#### 1-4. 注册验证码文本
**接口地址**
http://localhost:8080/shop/getcodeword

**请求方式**

HTTP-GET

**请求参数**

无

**返回参数**

长度为4的字符串



#### 1-5. 登录

**接口地址**

http://localhost:8080/shop/login

**请求方式**

HTTP-POST

**请求参数**

| 参数名称 | 参数介绍           | 参数格式 | 示例      | 是否必须 | 其他               |
| -------- | ------------------ | -------- | --------- | -------- | ------------------ |
| userInfo | 用户名/邮箱/手机号 | 字符串   | zhangsan  | Y        | 用户名/邮箱/手机号 |
| password | 用户唯一密码       | 字符串   | 123456Zz@ | Y        | 唯一               |

**返回参数**

登录成功:

用户信息加入Session中

返回JSON类型:user.getRole=user或者admin两种登录状态

```json
{
	"role": "user.getRole()"
}
```

登录失败:

返回JSON类型:

```json
{
	"role": "error"
}
```



#### 1-5. 修改登录信息

**接口地址**

http://localhost:8080/shop/updateUser

**请求方式**

HTTP-POST

**请求参数**

| 参数名称  | 参数介绍     | 参数格式 | 示例        | 是否必须 | 其他 |
| --------- | ------------ | -------- | ----------- | -------- | ---- |
| password  | 用户唯一密码 | 字符串   | 123456Zz@   | Y        | 唯一 |
| gender    | 性别         | 单字符串 | 男          | Y        | 无   |
| telephone | 手机号码     | 字符串   | 13012012120 | Y        | 无   |

**返回参数**

修改成功:

网址: modifyUserInfoSuccess

登录失败:

网址:modifyUserInfo



#### 1-6. 退出登录(用户)

**接口地址**

http://localhost:8080/shop/loginOut

**请求方式**

HTTP-GET

**请求参数**

无

**返回参数**

网址: login



###2. (管理员)商品模块接口

#### 2-1. 添加商品

**接口地址**

http://localhost:8080/shop/admin/insertProduct

**请求方式**

HTTP-POST

**请求参数**

| 参数名称    | 参数介绍 | 参数格式 | 示例                 | 是否必须 | 其他 |
| ----------- | -------- | -------- | -------------------- | -------- | ---- |
| name        | 商品名   | 字符串   | Thinking java        | Y        | 无   |
| price       | 商品价格 | 字符串   | 98.00                | Y        | 无   |
| pnum        | 库存     | 字符串   | 10                   | Y        | 无   |
| productType | 商品类型 | 字符串   | 计算机               | Y        | 无   |
| description | 商品介绍 | 字符串   | 新书特价,8折出售     | Y        | 无   |
| img_url     | 封面src  | 字符串   | /images/12aadef.jpeg | Y        | 无   |

**返回参数**

添加成功:

商品信息:request域对象products

网址:products/list



添加失败:

网址:products/error



#### 2-2. 图片上传

**接口地址**

http://localhost:8080/shop/admin/fileupload

**请求方式**

HTTP-POST

**请求参数**

| 参数名称 | 参数介绍 | 参数格式      | 示例 | 是否必须 | 其他               |
| -------- | -------- | ------------- | ---- | -------- | ------------------ |
| file     | 上传图片 | MultipartFile | 无   | Y        | 表单io提交即可接受 |

**返回参数**

字符串: 图片src地址



#### 2-3. 模糊查找

**接口地址**

http://localhost:8080/shop/admin/insertProduct

**请求方式**

HTTP-POST

**请求参数**

| 参数名称 | 参数介绍 | 参数格式 | 示例          | 是否必须 | 其他 |
| -------- | -------- | -------- | ------------- | -------- | ---- |
| id       | 商品编号 | 字符串   | 1             | N        | 无   |
| name     | 商品名   | 字符串   | Thinking java | N        | 无   |
| category | 商品类型 | 字符串   | 计算机        | N        | 无   |
| minPrice | 最低价格 | 字符串   | 0             | N        | 无   |
| maxPrice | 最高价格 | 字符串   | 999999        | N        | 无   |

**返回参数**

request域对象products

网址: products/list



#### 2-4. 前往编辑页面

**接口地址**

http://localhost:8080/shop/admin/goeditPage

**请求方式**

HTTP-GET

**请求参数**

| 参数名称 | 参数介绍 | 参数格式 | 示例 | 是否必须 | 其他 |
| -------- | -------- | -------- | ---- | -------- | ---- |
| id       | 商品编号 | 字符串   | 1    | N        | 无   |

**返回参数**

request域对象products

网址: products/edit



#### 2-5. 编辑商品

**接口地址**

http://localhost:8080/shop/admin/updateProduct

**请求方式**

HTTP-POST

**请求参数**

| 参数名称    | 参数介绍 | 参数格式 | 示例                 | 是否必须 | 其他 |
| ----------- | -------- | -------- | -------------------- | -------- | ---- |
| id          | 商品编号 | 字符串   | 1                    | Y        | 无   |
| name        | 商品名   | 字符串   | Thinking java        | Y        | 无   |
| price       | 商品价格 | 字符串   | 98.00                | Y        | 无   |
| pnum        | 库存     | 字符串   | 10                   | Y        | 无   |
| category    | 商品类型 | 字符串   | 计算机               | Y        | 无   |
| description | 商品介绍 | 字符串   | 新书特价,8折出售     | Y        | 无   |
| img_url     | 封面src  | 字符串   | /images/12aadef.jpeg | Y        | 无   |

**返回参数**

添加成功:

商品信息:request域对象products

网址:products/list



添加失败:

网址:products/error



#### 2-6. 删除商品

**接口地址**

http://localhost:8080/shop/admin/deleteProduct

**请求方式**

HTTP-GET

**请求参数**

| 参数名称 | 参数介绍 | 参数格式 | 示例 | 是否必须 | 其他 |
| -------- | -------- | -------- | ---- | -------- | ---- |
| id       | 商品编号 | 字符串   | 1    | Y        | 无   |

**返回参数**

删除成功:

商品信息:request域对象products

网址:products/list



添加失败:

网址:products/error



### 3. (用户)商品模块接口

#### 3-1. 前往分页商品列表(全部)

**接口地址**

http://localhost:8080/shop/goproductList1

**请求方式**

HTTP-GET

**请求参数**

无

**返回参数**

request域对象products

网址: product_list



#### 3-2.分页查看商品列表(全部)

**接口地址**

http://localhost:8080/shop/showProductPage

**请求方式**

HTTP-GET

**请求参数**

| 参数名称 | 参数介绍   | 参数格式 | 示例 | 是否必须 | 其他 |
| -------- | ---------- | -------- | ---- | -------- | ---- |
| pageNo   | 页码       | int      | 1    | Y        | 无   |
| pageSize | 每一页容量 | int      | 8    | Y        | 固定 |

**返回参数**

JSON类型:

```json
{
	"pageNo": 1,
	"pageCount": 4,
	"pageSize": 8,
	"items": [{
		"id": 1,
		"name": "雪山飞狐",
		"price": 65.00,
		"pnum": 20,
		"productType": "文学",
		"description": "新书上架,立减三元",
		"img_url": "/oimages\\019b6538590c4bcaaaa4387a13affea6.jpg"
	}, {
		"id": 2,
		"name": "连城诀",
		"price": 65.00,
		"pnum": 20,
		"productType": "文学",
		"description": "连城诀是也",
		"img_url": ""
	}, {
		"id": 3,
		"name": "天龙八部",
		"price": 53.00,
		"pnum": 10,
		"productType": "文学",
		"description": "天龙八部",
		"img_url": "/oimages\\51993ea6735040d383810ca34391b19d.png"
	}, {
		"id": 4,
		"name": "射雕英雄传",
		"price": 68.00,
		"pnum": 20,
		"productType": "文学",
		"description": "射雕英雄传",
		"img_url": "/oimages\\acd27f591140483ea48332a02e55bafc.jpg"
	}, {
		"id": 27,
		"name": "MySQL1",
		"price": 100.00,
		"pnum": 20,
		"productType": "计算机",
		"description": "MySQL操作指南",
		"img_url": "/oimages/019b6538590c4bcaaaa4387a13affea6.jpg"
	}, {
		"id": 28,
		"name": "MySQL2",
		"price": 100.00,
		"pnum": 20,
		"productType": "计算机",
		"description": "MySQL操作指南",
		"img_url": "/oimages/019b6538590c4bcaaaa4387a13affea6.jpg"
	}, {
		"id": 29,
		"name": "MySQL3",
		"price": 100.00,
		"pnum": 20,
		"productType": "计算机",
		"description": "MySQL操作指南",
		"img_url": "/oimages/019b6538590c4bcaaaa4387a13affea6.jpg"
	}, {
		"id": 30,
		"name": "MySQL4",
		"price": 100.00,
		"pnum": 20,
		"productType": "计算机",
		"description": "MySQL操作指南",
		"img_url": "/oimages/019b6538590c4bcaaaa4387a13affea6.jpg"
	}]
}
```





#### 3-3.前往分页商品列表(类型)

**接口地址**

http://localhost:8080/shop/showProductPage2

**请求方式**

HTTP-GET

**请求参数**

| 参数名称 | 参数介绍   | 参数格式 | 示例   | 是否必须 | 其他 |
| -------- | ---------- | -------- | ------ | -------- | ---- |
| category | 商品类型   | 字符串   | 计算机 | Y        | 无   |
| pageNo   | 页码       | int      | 1      | Y        | 无   |
| pageSize | 每一页容量 | int      | 8      | Y        | 固定 |

**返回参数**

JSON类型:

```json
{
	"pageNo": 1,
	"pageCount": 1,
	"pageSize": 8,
	"items": [{
		"id": 1,
		"name": "雪山飞狐",
		"price": 65.00,
		"pnum": 20,
		"productType": "文学",
		"description": "新书上架,立减三元",
		"img_url": "/oimages\\019b6538590c4bcaaaa4387a13affea6.jpg"
	}, {
		"id": 2,
		"name": "连城诀",
		"price": 65.00,
		"pnum": 20,
		"productType": "文学",
		"description": "连城诀是也",
		"img_url": ""
	}, {
		"id": 3,
		"name": "天龙八部",
		"price": 53.00,
		"pnum": 10,
		"productType": "文学",
		"description": "天龙八部",
		"img_url": "/oimages\\51993ea6735040d383810ca34391b19d.png"
	}, {
		"id": 4,
		"name": "射雕英雄传",
		"price": 68.00,
		"pnum": 20,
		"productType": "文学",
		"description": "射雕英雄传",
		"img_url": "/oimages\\acd27f591140483ea48332a02e55bafc.jpg"
	}]
}
```





#### 3-4. 跳转商品详情页

**接口地址**

http://localhost:8080/shop/goProductInfo

**请求方式**

HTTP-GET

**请求参数**

| 参数名称 | 参数介绍 | 参数格式 | 示例 | 是否必须 | 其他 |
| -------- | -------- | -------- | ---- | -------- | ---- |
| pid      | 商品编号 | 字符串   | 1    | Y        | 无   |

**返回参数**

商品信息:request域对象product

网址: product_info



### 4. (用户)购物车模块接口

#### 4-1. 前往购物车页面

**接口地址**

http://localhost:8080/shop/goToCar

**请求方式**

HTTP-GET

**请求参数**

无

**返回参数**

跳转成功:

request域对象:shoppingCar

网页:cart



跳转失败:

网页:login



#### 4-2. 添加购物车商品

**接口地址**

http://localhost:8080/shop/addToCar

**请求方式**

HTTP-GET

**请求参数**

****

| 参数名称 | 参数介绍 | 参数格式 | 示例 | 是否必须 | 其他 |
| -------- | -------- | -------- | ---- | -------- | ---- |
| pid      | 商品编号 | 字符串   | 1    | Y        | 无   |

**返回参数**

跳转成功:

request域对象:shoppingCar

网页:cart



跳转失败:

网页:error



#### 4-3. 编辑购物车(前往商品列表)

**接口地址**

http://localhost:8080/shop/editandgolist

**请求方式**

HTTP-GET

**请求参数**

------

| 参数名称 | 参数介绍     | 参数格式  | 示例    | 是否必须 | 其他 |
| -------- | ------------ | --------- | ------- | -------- | ---- |
| pid      | 商品编号数组 | Long[]    | [1,2,3] | Y        | 无   |
| count    | 商品件数数组 | Integer[] | [1,2,3] | Y        | 无   |

**返回参数**

跳转成功:

网页:product_list



#### 4-4. 编辑购物车(前往订单列表)

**接口地址**

http://localhost:8080/shop/editandgoorder

**请求方式**

HTTP-GET

**请求参数**

------

| 参数名称 | 参数介绍     | 参数格式  | 示例    | 是否必须 | 其他 |
| -------- | ------------ | --------- | ------- | -------- | ---- |
| pid      | 商品编号数组 | Long[]    | [1,2,3] | Y        | 无   |
| count    | 商品件数数组 | Integer[] | [1,2,3] | Y        | 无   |

**返回参数**

跳转成功:

request域对象:shoppingCar

网页:order



###5. (用户)订单模块接口

#### 5-1. 添加订单

**接口地址**

http://localhost:8080/shop/insertToOrder

**请求方式**

HTTP-GET

**请求参数**

------

| 参数名称        | 参数介绍       | 参数格式 | 示例                         | 是否必须 | 其他 |
| --------------- | -------------- | -------- | ---------------------------- | -------- | ---- |
| receiverAddress | 收件地址       | 字符串   | 上海市静安区静安寺xxx路xxx号 | Y        | 无   |
| receiverName    | 收件人         | 字符串   | 谢永强                       | Y        | 无   |
| receiverPhone   | 收件人联系方式 | 字符串   | 13012505050                  | Y        | 无   |

**返回参数**

跳转成功:

request域对象:orderInfo

网页:orderInfo



#### 5-2. 查看订单列表

**接口地址**

http://localhost:8080/shop/goOrderlist

**请求方式**

HTTP-GET

**请求参数**

无

**返回参数**

跳转成功:

request域对象:orders

网页:orderlist



#### 5-3. 查看指定订单

**接口地址**

http://localhost:8080/shop/goOneOrder

**请求方式**

HTTP-GET

**请求参数**

| 参数名称 | 参数介绍 | 参数格式 | 示例           | 是否必须 | 其他 |
| -------- | -------- | -------- | -------------- | -------- | ---- |
| orderid  | 订单列表 | 字符串   | 15297567621998 | Y        | 无   |

**返回参数**

跳转成功:

request域对象:orders

网页:orderlist



#### 5-4. 删除指定订单

**接口地址**

http://localhost:8080/shop/deleteOrder

**请求方式**

HTTP-GET

**请求参数**

| 参数名称 | 参数介绍 | 参数格式 | 示例           | 是否必须 | 其他 |
| -------- | -------- | -------- | -------------- | -------- | ---- |
| orderid  | 订单列表 | 字符串   | 15297567621998 | Y        | 无   |

**返回参数**

跳转成功:

request域对象:orders

网页:orderlist





### 6. 支付模块接口

#### 6-1. 前往支付页面

**接口地址**

http://localhost:8080/shop/pay

**请求方式**

HTTP-GET

**请求参数**

------

| 参数名称 | 参数介绍 | 参数格式 | 示例           | 是否必须 | 其他 |
| -------- | -------- | -------- | -------------- | -------- | ---- |
| orderId  | 订单编号 | 字符串   | 15297251026538 | Y        | 无   |

**返回参数**

跳转成功:

将网页/订单信息/二维码地址通过url一起返回

网页:http://localhost:8080/shop/payment.jsp?order_id=15297479364828&url=weixin%3A%2F%2Fwxpay%2Fbizpayurl%3Fpr%3DAHX824D



#### 6-2. 生成支付二维码

**接口地址**

http://localhost:8080/shop/image

**请求方式**

HTTP-GET

**请求参数**

无

**返回参数**

字符串: 二维码src



#### 6-3. 轮询查看支付状态

**接口地址**

http://localhost:8080/shop/getorderstate

**请求方式**

HTTP-POST

**请求参数**

------

| 参数名称 | 参数介绍 | 参数格式 | 示例           | 是否必须 | 其他 |
| -------- | -------- | -------- | -------------- | -------- | ---- |
| orderId  | 订单编号 | 字符串   | 15297251026538 | Y        | 无   |

**返回参数**

字符串: "支付成功"

成功后跳转网址: shop/orderlist















