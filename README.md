# ontologyserver

## Introduction


## 接口列表
 
1. `getmyplacebetbypage`    GET

`/getmyplacebetbypage/{address}/{pageSize}/{pageNumber}`


根据用户地址和页查询 用户的赌注信息


请求例子

```http
http://127.0.0.1:8585/api/v1/bets/getmyplacebetbypage/sss/3/1
```

响应结果
```json
{
	"Action": "getmyplacebetbypage",
	"Error": 0,
	"Desc": "SUCCESS",
	"Version": "1.0",
	"Result": {
		"Result": [{
			"time": "2018-12-19 10:09:48.0",
			"txHash": "2.0",
			"address": "sss",
			"gameID": 1,
			"HorV": "h",
			"amount": 2.0100,
			"horV": "h"
		}]
	}
}
```

2. `getmywithdrawbypage`   GET

`/getmywithdrawbypage/{address}/{pageSize}/{pageNumber}`

查询用户提现记录

请求例子

```http request
http://127.0.0.1:8585/api/v1/bets/getmywithdrawbypage/sss/3/1
```

响应结果

```json
{
	"Action": "gethugewinstopbynum",
	"Error": 0,
	"Desc": "SUCCESS",
	"Version": "1.0",
	"Result": {
		"Result": [{
			"address": "sss",
			"amount": 2.0100,
			"txHash": "2.0",
			"time": "2018-12-19 10:09:40.0"
		}]
	}
}
```

3. `placebet`   POST


保存用户赌注信息

请求例子
```http request
http://127.0.0.1:8585/api/v1/bets/placebet
```
参数
```json
{
  "txHash": "2.0",
  "address": "sss",
  "gameID": 1,
  "HorV": "h",
  "amount":2.01
}
```

响应结果
```json
{
    "Action": "placebet",
    "Error": 0,
    "Desc": "SUCCESS",
    "Version": "1.0",
    "Result": {
        "Result": 1
    }
}
```

4. withdraw    POST

保存用户提现信息

请求例子

```http request
http://127.0.0.1:8585/api/v1/bets/withdraw
```

参数
```json
{
  "txHash": "2.0",
  "address": "sss",
  "amount":2.01
}
```

响应结果
```json
{
    "Action": "withdraw",
    "Error": 0,
    "Desc": "SUCCESS",
    "Version": "1.0",
    "Result": {
        "Result": 1
    }
}
```
