# tcp-unimodule
基于java开发的uniapp原生android tcp插件

生成aar文件可用于uniapp在android环境下TCP通讯

下面我自己写了个小deme，上效果

![1631072291077.gif](http://ww1.sinaimg.cn/large/007TQE1Sgy1gu92qrgerag60hs0c01lk02.gif)

具体怎么引入uniapp使用这里就不详细说了，直接抛出来方法吧

开始连接TCP

```
MobaiTCP.connect({
  ip: '192.168.0.0', // 具体以自己需要对接的为准
  port: 8888
}, result => {
  // 这里就是服务端返回给你的数据啦
})
```

发送字符给服务端

```
MobaiTCP.sendStr({
  message: '发送的内容'
});
```

-----后续将更新进制数据传输、编码等内容
