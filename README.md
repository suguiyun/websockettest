# h1 websockettest

这是一个测试jhipster生成的spring websocket的demo



## 使用到的技术

用到的技术包括：

1. [spring][]: 依赖spring.
   
2. [websocket][]: spring封装的websocket，遵循websocket的协议


    yarn install


参考了几篇 `博客` and `官网` 来完成了这个demo.


### 使用谷歌控制台连接websocket服务器

* js代码

```html
<script>
    //建立websocket连接
    function openWs(){
        websocket = new SockJS("http://localhost:8080/websocket/tracker?access_token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTUyNDEzMzI5Nn0.27co1fKw8KYVw-_5Ie5bXf7YgMNCCvGpWQYLveKtHzdNMQHb2If2tUk2YmczTY92jGWjKPMoSqmeMAQeXTRx6w");
    
        var stompClient = Stomp.over(websocket);
        stompClient.connect({}, function(frame) {
            stompClient.subscribe('/topic/tracker',  function(data) { //订阅消息
                console.log("收到topic消息："+data.body);//body中为具体消息内容
            });
             stompClient.subscribe('/user/' + 1 + '/message', function(message){
                 console.log("收到session消息："+message.body);//body中为具体消息内容
             });
        });
    
    //     document.getElementById("sendws").onclick = function() {
    //         stompClient.send("/app/message", {}, JSON.stringify({
    //             name: "nane",
    //             msg: "发送的消息aaa"
    //         }));
    //     }
    }
    //关闭连接
    openWs();
    
  
    var importJs=document.createElement('script')  //在页面新建一个script标签
    importJs.setAttribute("type","text/javascript")  //给script标签增加type属性
    importJs.setAttribute("src", 'http://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js') //给script标签增加src属性， url地址为cdn公共库里的
    document.getElementsByTagName("head")[0].appendChild(importJs) //把importJs标签添加在页面
   
    var importJs=document.createElement('script')  //在页面新建一个script标签
    importJs.setAttribute("type","text/javascript")  //给script标签增加type属性
    importJs.setAttribute("src", 'http://cdn.jsdelivr.net/sockjs/1.0.1/sockjs.min.js') //给script标签增加src属性， url地址为cdn公共库里的
    document.getElementsByTagName("head")[0].appendChild(importJs) //把importJs标签添加在页面
</script>
```


* 导入stomp的javascript
* 通过sockjs完成客户端和服务器端的连接


Edit [src/main/webapp/app/vendor.ts](src/main/webapp/app/vendor.ts) file:
~~~
import 'leaflet/dist/leaflet.js';
~~~

Edit [src/main/webapp/content/css/vendor.css](src/main/webapp/content/css/vendor.css) file:
~~~
@import '~leaflet/dist/leaflet.css';
~~~


访问网址[http://localhost:8080](http://localhost:8080) in your browser.


 
js路径 [src/main/docker](src/main/docker) 

||||
|:-----:|:---|------:|
|居中|左对齐|右对齐|
|========|===========|=========|


[spring]: https://spring.io/
[websocket]: http://www.ruanyifeng.com/blog/2017/05/websocket.html
