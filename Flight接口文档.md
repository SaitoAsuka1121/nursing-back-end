# Flight接口文档

# 1.用户模块

## 1.1获取用户信息(参考)

接口url：/user/getinfo

请求方式:POST

请求参数:

| 参数名称 | 参数类型 | 说明     |
| -------- | -------- | -------- |
| username | int      | 用户账户 |
|          |          |          |

返回数据:

```json
{
    "success":true | false,
    "code": 200,
    "msg":"success",
    "data":{
        "id":1,
        "username":"一般为手机，唯一凭证",
        "is_activation": true|false,
        "name":"小刘",
        "phone":"手机",
        "sex":"男"|"女"
    }
}
```

## 1.2登录(未测试)

接口url: /user/login

请求方式：POST

请求参数:

| 参数名称 | 参数类型 | 说明     |
| -------- | -------- | -------- |
| account  | String   | 用户账户 |
| password | password | 用户密码 |

返回数据:

```json
{
    "success": true,
    "code": 200,
    "msg": "success",
    "data": "token"
}
```

