# ItChat-Java-Shell

Java用socket通信，封装python版ItChat

***好好好。微信大佬说要关闭微信网页版登录。这个项目刚开始就跪了。大家散了吧。***

## 来源

由于目前在Java上移植ItChat的项目都存在移植不完全的问题，所以想自己实现一个java的版本。

考虑到后期维护问题，因此采用java封装一层接口，直接socket与python版本的ItChat做通讯。

这样减少了后期新功能维护的复杂度和耗时度。



## ItChat

[itchat](https://github.com/littlecodersh/ItChat)是一个开源的微信个人号接口，使用python调用微信从未如此简单。

使用不到三十行的代码，你就可以完成一个能够处理所有信息的微信机器人。

如今微信已经成为了个人社交的很大一部分，希望这个项目能够帮助你扩展你的个人的微信号、方便自己的生活。

## 目录结构

    .
    ├── README.md
    └── src
        ├── main
        |   ├── java/com/chiho/itchat/shell
        |   |   ├── enums       //枚举
        |   |   ├── exceptions      //自定义异常
        |   |   ├── interfaces      //接口
        |   |   ├── model       //数据模型
        |   |   ├── utils       //工具
        |   |   ├── Client.java     //建立客户端socket，建立接收消息和发送心跳包线程，发送消息等
        |   |   ├── Server.java     //调用pyserver.py，重定向输出
        |   |   └── Shell       //API封装，对参数和返回数据封箱解箱
        |   └── resources
        |       ├── log4j.properties
        |       └── pyserver.py     
        |           //python版ItChat封装，包含监听端口、收发消息等
        └── test/java
                └── SocketTest.java     //API使用demo



## 通讯

           +---------------------------------+     +------------------------------------+  
           |              python             |     |                Java                | 
           +---------------------------------+     +------------------------------------+ 
                                                   +------------------------------------+
                                                   |                                    |
                                                   |                Shell               |
                                                   |                                    |
                                                   +--------+-------------------^-------+
                                                            |                   |
            +--------------+                        +-------v-------+   +-------+--------+
            |              |                        |               |   |                |
            | Receive CMD  <---------+--------------+    Send CMD   |   |  Call Callback |
            |              |         ‖              |               |   |                |    
            +-------+------+         ‖              +---------------+   +-------^--------+    
                    |                ‖           Waiting For Return             |             
                    |                ==========================================>|             
                    |                                                           |             
            +-------v------+                                           +--------+-------+     
            |              |                                           |                |     
            | Call Method  |                                           |  Parse Return  |
            |              |                                           |                |
            +-------+------+                                           +-------^--------+
                    |                                                          |         
                    |                                                          |         
                    |                                                          |         
            +-------v------+  +--------------+                         +-------+--------+
            |              |  |              |                         |                |
            | Send Return  |  | Msg Callback |                         |Receive Response|
            |              |  |              |                         |                |
            +-------+------+  +-------+------+                         +--------^-------+
                    |                 |                                         |
                    +-----------------------------------------------------------+



## API

### 启动server和client

> boolean start()

使用任何其他API之前必须先调用start()，🌰

```java
Shell shell = Shell.getInstance();
shell.start();
```



### 添加好友消息监听

> void addFriendMessageCallback(MessageTypeEnum messageType, Callback<MessageDO> callback)

添加监听好友发送的消息，🌰

```java
Shell shell = Shell.getInstance()
shell.addFriendMessageCallback(MessageTypeEnum.TEXT, param -> System.out.println(
            "Friend_MAP: " + JSON.toJSON(param)));
```

|     参数名     |                    类型                    |  备注  |
| :---------: | :--------------------------------------: | :--: |
| messageType | 枚举，TEXT, MAP, CARD, NOTE, SHARING, PICTURE, RECORDING, ATTACHMENT, VIDEO, FRIENDS, SYSTEM | 消息类型 |
|  callback   |           Callback<MessageDO>            | 回调方法 |



### 添加群聊消息监听

> void addGroupMessageCallback(MessageTypeEnum messageType, Callback<MessageDO> callback)

添加监听群聊发送的消息，🌰

```java
Shell shell = Shell.getInstance();
shell.addGroupMessageCallback(MessageTypeEnum.TEXT, param -> System.out.println(
   "Group_TEXT: " + JSON.toJSON(param)));
```

|     参数名     |                    类型                    |  备注  |
| :---------: | :--------------------------------------: | :--: |
| messageType | 枚举，TEXT, MAP, CARD, NOTE, SHARING, PICTURE, RECORDING, ATTACHMENT, VIDEO, FRIENDS, SYSTEM | 消息类型 |
|  callback   |           Callback<MessageDO>            | 回调方法 |



### 添加公众号消息监听

> void addMpMessageCallback(MessageTypeEnum messageType, Callback<MessageDO> callback)

添加监听群聊发送的消息，🌰

```java
Shell shell = Shell.getInstance();
shell.addMpMessageCallback(MessageTypeEnum.TEXT, param -> System.out.println(
   "Group_TEXT: " + JSON.toJSON(param)));
```

|     参数名     |                    类型                    |  备注  |
| :---------: | :--------------------------------------: | :--: |
| messageType | 枚举，TEXT, MAP, CARD, NOTE, SHARING, PICTURE, RECORDING, ATTACHMENT, VIDEO, FRIENDS, SYSTEM | 消息类型 |
|  callback   |           Callback<MessageDO>            | 回调方法 |



### 登录

> void login( Boolean enableCmdQR, String picDir, Callback<QrCodeDO> qrCallback, Runnable loginCallback, Runnable exitCallback )

调用登录微信，将自动获取uuid及二维码，可以用[获取uuid](https://github.com/ChiHoc/ItChat-Java-Shell/blob/master/README.md#获取uuid)、[获取二维码](https://github.com/ChiHoc/ItChat-Java-Shell/blob/master/README.md#获取二维码)、[检查登录状态](https://github.com/ChiHoc/ItChat-Java-Shell/blob/master/README.md#检查登录状态)、[初始化同步数据](https://github.com/ChiHoc/ItChat-Java-Shell/blob/master/README.md#初始化同步数据)、[手机显示登录标志](https://github.com/ChiHoc/ItChat-Java-Shell/blob/master/README.md#手机显示登录标志)、[开始接收消息](https://github.com/ChiHoc/ItChat-Java-Shell/blob/master/README.md#开始接收消息)六个接口代替，🌰

```java
Shell shell = Shell.getInstance();
shell.login(
    false,
    null, 
    qrCodeResponse -> System.out.println(JSON.toJSON(qrCodeResponse)), 
    () -> System.out.println("loginCallback"), 
    () -> System.out.println("exitCallback"));
```

|      参数名      |         类型         |                    备注                    |
| :-----------: | :----------------: | :--------------------------------------: |
|  enableCmdQR  |      Boolean       |        命令行显示二维码，Nullable，默认：false        |
|    picDir     |       String       | 二维码存储位置，Nullable，默认：src/main/resources/QR.png |
|  qrCallback   | Callback<QrCodeDO> | 回调接收uuid、status、qrcode，Nullable，若设置，则不会自动显示二维码 |
| loginCallback |      Runnable      |             登录成功回调，Nullable              |
| exitCallback  |      Runnable      |              登出回调，Nullable               |



### 获取uuid

> String getQRUuid()

获取uuid（非自定义登录不用调用），🌰

```java
Shell shell = Shell.getInstance();
uuid = shell.getQRUuid();
```



### 获取二维码

> String getQR( String uuid, Boolean enableCmdQR, String picDir, Callback<QrCodeDO> qrCallback )

根据uuid获取二维码（非自定义登录不用调用），🌰

```java
Shell shell = Shell.getInstance()
shell.getQR(uuid, false, null, qrCodeResponse -> System.out.println(JSON.toJSON(qrCodeResponse)));
```

|     参数名     |         类型         |                    备注                    |
| :---------: | :----------------: | :--------------------------------------: |
|    uuid     |       String       |        uuid，Nullable，默认：最后请求的uuid        |
| enableCmdQR |      Boolean       |        命令行显示二维码，Nullable，默认：false        |
|   picDir    |       String       | 二维码存储位置，Nullable，默认：src/main/resources/QR.png |
| qrCallback  | Callback<QrCodeDO> | 回调接收uuid、status、qrcode，Nullable，若设置，则不会自动显示二维码 |



### 检查登录状态

> String checkLogin( String uuid )

根据uuid检查登录状态（非自定义登录不用调用），🌰

```java
Shell shell = Shell.getInstance();
String statusCode = shell.checkLogin(uuid);
```

| 参数名  |   类型   |             备注             |
| :--: | :----: | :------------------------: |
| uuid | String | uuid，Nullable，默认：最后请求的uuid |



### 初始化同步数据

> WebInitDO webInit()

初始化同步数据，返回用户信息，好友列表，syncKey（非自定义登录不用调用），🌰

```java
Shell shell = Shell.getInstance();
WebInitDO webInitDO = shell.webInit();
```



### 手机显示登录标志

> ShowMobileLoginDO showMobileLogin()

手机显示登录标志，即使不手动调用，在之后调用其他方法也会自动在手机上显示网页登录标志（非自定义登录不用调用），🌰

```java
Shell shell = Shell.getInstance();
ShowMobileLoginDO showMobileLoginDO = shell.showMobileLogin();
```



### 开始接收消息

> void startReceiving( Runnable exitCallback )

开始接收消息，可以使用轮询调用获取消息接口代替（非自定义登录不用调用），🌰

```java
Shell shell = Shell.getInstance();
shell.startReceiving(() -> System.out.println("exitCallback"));
```

|     参数名      |    类型    |      备注       |
| :----------: | :------: | :-----------: |
| exitCallback | Runnable | 登出回调，Nullable |



### 获取消息

> FetchMessageDO getMsg()

获取最新消息（非自定义接收消息不用调用），🌰

```java
Shell shell = Shell.getInstance();
FetchMessageDO fetchMessageDO = shell.getMsg();
```



### 登出

> StatusResponseDO logout()

登出，🌰

```java
Shell shell = Shell.getInstance();
StatusResponseDO response = shell.logout();
```



### 更新群聊

> ContactDO updateChatroom( @NotNull String userName, Boolean detailedMember )

更新群聊，🌰

```java
Shell shell = Shell.getInstance();
ContactDO contactDO = shell.updateChatroom("@@35sdaf902lsddqfscbnmds", true);
```

|      参数名       |   类型    |              备注              |
| :------------: | :-----: | :--------------------------: |
|    userName    | String  |      群聊username，NotNull      |
| detailedMember | Boolean | 是否需要获取群聊成员，Nullable，默认：false |



### 更新好友

> ContactDO updateFriend( @NotNull String userName )

更新好友，🌰

```java
Shell shell = Shell.getInstance();
ContactDO contactDO = shell.updateFriend("@fdguiwjnur3jds832vd");
```

|   参数名    |   类型   |         备注         |
| :------: | :----: | :----------------: |
| userName | String | 好友username，NotNull |



### 获取联系人

> List<ContactDO> getContact( Boolean update )

获取联系人，这个接口在python版本就设计得有点奇怪。

虽然是getContact，但是只是返回群聊列表（不建议自己调用，使用其他获取接口代替），🌰

```java
Shell shell = Shell.getInstance();
List<ContactDO> contactList = shell.getContact(false);
```

|  参数名   |   类型    |                    备注                    |
| :----: | :-----: | :--------------------------------------: |
| update | Boolean | 是否联网更新联系人列表，Nullable，默认：false<br />**注意：若update=true，则返回本地所有群聊列表，若update=false，则返回收藏的群聊列表** |



### 获取好友列表

> List<ContactDO> getFriends( Boolean update )

获取好友列表，🌰

```java
Shell shell = Shell.getInstance();
List<ContactDO> contactList = shell.getFriends(false);
```

|  参数名   |   类型    |              备注              |
| :----: | :-----: | :--------------------------: |
| update | Boolean | 是否联网更新好友列表，Nullable，默认：false |



### 获取公众号列表

> List<ContactDO> getMps( Boolean update )

获取公众号列表，🌰

```java
Shell shell = Shell.getInstance();
List<ContactDO> contactList = shell.getMps(false);
```

|  参数名   |   类型    |              备注               |
| :----: | :-----: | :---------------------------: |
| update | Boolean | 是否联网更新公众号列表，Nullable，默认：false |



### 设置备注

> StatusResponseDO setAlias( @NotNull String userName, @NotNull String remark )

设置备注，🌰

```java
Shell shell = Shell.getInstance();
StatusResponseDO responseDO = shell.setAlias("@fdguiwjnur3jds832vd", "自动备注");
```

|   参数名    |   类型   |                备注                 |
| :------: | :----: | :-------------------------------: |
| userName | String | 好友username（不能为filehelper），NotNull |
|  remark  | String |            备注名，NotNull            |



### 置顶好友

> StatusResponseDO setPinned( @NotNull String userName, Boolean isPinned )

置顶好友，🌰

```java
Shell shell = Shell.getInstance();
StatusResponseDO responseDO = shell.setPinned("@fdguiwjnur3jds832vd", true);
```

|   参数名    |   类型    |          备注           |
| :------: | :-----: | :-------------------: |
| userName | String  |  好友username，NotNull   |
| isPinned | Boolean | 是否备注，Nullable，默认：true |



### 添加好友

> StatusResponseDO addFriend( @NotNull String userName, AddFriendStatusEnum status, String verifyContent, Boolean autoUpdate )

添加好友，🌰

```java
Shell shell = Shell.getInstance();
StatusResponseDO responseDO = shell.addFriend("@fdguiwjnur3jds832vd", AddFriendStatusEnum.ADD, "verifyContent", true);
```

|      参数名      |         类型          |           备注            |
| :-----------: | :-----------------: | :---------------------: |
|   userName    |       String        |   好友username，NotNull    |
|    status     | AddFriendStatusEnum |  添加好友为ADD，接受添加为ACCEPT   |
| verifyContent |       String        |      验证信息，Nullable      |
|  autoUpdate   |       Boolean       | 是否自动更新，Nullable，默认：true |



### 获取头像

> StatusResponseDO addFriend( @NotNull String userName, AddFriendStatusEnum status, String verifyContent, Boolean autoUpdate )

获取头像，🌰

```java
Shell shell = Shell.getInstance();
HeadImgDO getHeadImg( String userName, String chatroomUserName, String picDir );
```

|       参数名        |   类型   |                    备注                    |
| :--------------: | :----: | :--------------------------------------: |
|     userName     | String |         好友username，与userName二选一          |
| chatroomUserName | String |     群聊username，与chatroomUserName二选一      |
|      picDir      | String | 存储目录，Nullable，默认：src/main/resources/headImg.png |



### 建立群聊

> CreateChatroomDO createChatroom( @NotNull List<ContactDO> memberList, String topic )

建立群聊，🌰

```java
Shell shell = Shell.getInstance();
CreateChatroomDO createChatroomDO = shell.createChatroom(new ArrayList<ContactDO>() {{
                        ContactDO contactDO = new ContactDO();
                        contactDO.setUserName("@633cead849896f858ed14ae242748fb9");
                        add(contactDO);
                        contactDO = new ContactDO();
                        contactDO.setUserName("@348di893298f83id8uf29fsd3fdf");
                        add(contactDO);
                    }}, "topic");
```

|    参数名     |       类型        |                   备注                   |
| :--------: | :-------------: | :------------------------------------: |
| memberList | List<ContactDO> | 用户列表（length>=2，且不能有filehelper），NotNull |
|   topic    |     String      |           群聊名，Nullable，默认：''           |



### 修改群聊名

> ModifyChatroomDO setChatroomName( @NotNull String chatroomUserName, @NotNull String name )

修改群聊名，🌰

```java
Shell shell = Shell.getInstance();
ModifyChatroomDO modifyChatroomDO = shell.setChatroomName("@@4234abs8987987asd98w98989", "name");
```

|       参数名        |   类型   |         备注         |
| :--------------: | :----: | :----------------: |
| chatroomUserName | String | 群聊username，NotNull |
|       name       | String |    群聊名，NotNull     |



### 踢出群聊成员

> ModifyChatroomDO deleteMemberFromChatroom( @NotNull String chatroomUserName, @NotNull List<ContactDO> memberList )

踢出群聊成员，🌰

```java
Shell shell = Shell.getInstance();
ModifyChatroomDO modifyChatroomDO = shell.deleteMemberFromChatroom("@@bd79da9a86aa407fef4faf575dcff5cfde953b449e6af9a181fa5dcf0f7050db", new ArrayList<ContactDO>() {{
                        ContactDO contactDO = new ContactDO();
                        contactDO.setUserName("@4234abs8987987asd98w98989");
                        add(contactDO);
                    }});
```

|       参数名        |       类型        |         备注         |
| :--------------: | :-------------: | :----------------: |
| chatroomUserName |     String      | 群聊username，NotNull |
|    memberList    | List<ContactDO> |    用户列表，NotNull    |



### 添加群聊成员

> ModifyChatroomDO addMemberIntoChatroom( @NotNull String chatroomUserName, @NotNull List<ContactDO> memberList, Boolean useInvitation )

添加群聊成员，🌰

```java
Shell shell = Shell.getInstance();
ModifyChatroomDO modifyChatroomDO = shell.addMemberIntoChatroom("@@bd79da9a86aa407fef4faf575dcff5cfde953b449e6af9a181fa5dcf0f7050db", new ArrayList<ContactDO>() {{
                        ContactDO contactDO = new ContactDO();
                        contactDO.setUserName("filehelper");
                        add(contactDO);
                    }}, false);
```

|       参数名        |       类型        |                    备注                    |
| :--------------: | :-------------: | :--------------------------------------: |
| chatroomUserName |     String      |            群聊username，NotNull            |
|    memberList    | List<ContactDO> |               用户列表，NotNull               |
|  useInvitation   |     Boolean     | 是否需要请求用户通过，40人以上群聊必须为true，Nullable，默认：false |



### 发送文字消息

> SendMsgDO sendMsg( String msg, @NotNull String toUserName )

发送文字消息，🌰

```java
Shell shell = Shell.getInstance();
SendMsgDO sendMsgDO = shell.sendMsg("msg", "@633cead849896f858ed14ae242748fb9");
```

|    参数名     |   类型   |              备注               |
| :--------: | :----: | :---------------------------: |
|    msg     | String | 消息内容，Nullable，默认：Test Message |
| toUserName | String |     目标联系人username，NotNull     |



### 上传文件

> UploadFileDO uploadFile( @NotNull String fileDir, Boolean isPicture, Boolean isVideo, @NotNull String toUserName )

上传文件消息，微信的发送视频图片等消息，需要先上传文件到服务器，获取mediaId，🌰

```java
Shell shell = Shell.getInstance();
UploadFileDO uploadFileDO = shell.uploadFile("xxx/a.jpg", true, false, "@4234abs8987987asd98w98989");
```

|    参数名     |   类型    |          备注           |
| :--------: | :-----: | :-------------------: |
|  fileDir   | String  |     文件目录，NotNull      |
| isPicture  | Boolean |   是否图片，与isVideo二选一    |
|  isVideo   | Boolean |  是否视频，与isPicture二选一   |
| toUserName | String  | 目标联系人username，NotNull |



### 发送文件消息

> SendMsgDO sendFile( @NotNull String fileDir, @NotNull String toUserName, String mediaId )

发送文件消息，🌰

```java
Shell shell = Shell.getInstance();
SendMsgDO sendMsgDO = shell.sendFile("xxx/a.jpg", "@4234abs8987987asd98w98989", null);
```

|    参数名     |   类型   |                   备注                   |
| :--------: | :----: | :------------------------------------: |
|  fileDir   | String |              文件目录，NotNull              |
| toUserName | String |         目标联系人username，NotNull          |
|  mediaId   | String | mediaId，不传则内部先调用 uploadFile上传，Nullable |



### 发送图片消息

> SendMsgDO sendImage( @NotNull String fileDir, @NotNull String toUserName, String mediaId )

发送图片消息，🌰

```java
Shell shell = Shell.getInstance();
SendMsgDO sendMsgDO = shell.sendImage("sxxx/a.jpg", "@4234abs8987987asd98w98989", null);
```

|    参数名     |   类型   |                   备注                   |
| :--------: | :----: | :------------------------------------: |
|  fileDir   | String |              文件目录，NotNull              |
| toUserName | String |         目标联系人username，NotNull          |
|  mediaId   | String | mediaId，不传则内部先调用 uploadFile上传，Nullable |



### 发送视频消息

> SendMsgDO sendVideo( @NotNull String fileDir, @NotNull String toUserName, String mediaId )

发送视频消息，🌰

```java
Shell shell = Shell.getInstance();
SendMsgDO sendMsgDO = shell.sendVideo("sxxx/a.mp4", "@4234abs8987987asd98w98989", null);
```

|    参数名     |   类型   |                   备注                   |
| :--------: | :----: | :------------------------------------: |
|  fileDir   | String |              文件目录，NotNull              |
| toUserName | String |         目标联系人username，NotNull          |
|  mediaId   | String | mediaId，不传则内部先调用 uploadFile上传，Nullable |



### 撤回消息

> RevokeDO revoke( @NotNull String msgId, @NotNull String toUserName, @NotNull String localId )

撤回消息，🌰

```java
Shell shell = Shell.getInstance();
RevokeDO revokeDO = shell.revoke("5961489659369359345", "filehelper", "15105439316990");
```

|    参数名     |   类型   |          备注           |
| :--------: | :----: | :-------------------: |
|   msgId    | String |     消息Id，NotNull      |
| toUserName | String | 目标联系人username，NotNull |
|  localId   | String |    localId，NotNull    |



### 自动登录

> void autoLogin( Boolean hotReload, String statusStorageDir, Boolean enableCmdQR, String picDir, Callback<QrCodeDO> qrCallback, Runnable loginCallback, Runnable exitCallback )

登录的终极接口，可以先读取本地登录状态信息判断登录状态，再决定是否重新登录，🌰

```java
Shell shell = Shell.getInstance();
shell.autoLogin(true, null, false, null, param -> QRCodeTools.showLoginCode(param.getQrcode()), () -> QRCodeTools.dismissLoginCode(), null);
```

|       参数名        |         类型         |                    备注                    |
| :--------------: | :----------------: | :--------------------------------------: |
|    hotReload     |      Boolean       |       是否读取本地登录状态，Nullable，默认：false       |
| statusStorageDir |       String       | 登录信息位置，Nullable，默认：src/main/resources/login.sav |
|   enableCmdQR    |      Boolean       |        命令行显示二维码，Nullable，默认：false        |
|      picDir      |       String       | 二维码存储位置，Nullable，默认：src/main/resources/QR.png |
|    qrCallback    | Callback<QrCodeDO> | 回调接收uuid、status、qrcode，Nullable，若设置，则不会自动显示二维码 |
|  loginCallback   |      Runnable      |             登录成功回调，Nullable              |
|   exitCallback   |      Runnable      |              登出回调，Nullable               |



### dump登录状态

> void dumpLoginStatus( String fileDir )

dump登录信息，用于重新加载登录状态，🌰

```java
Shell shell = Shell.getInstance();
shell.dumpLoginStatus(null);
```

|   参数名   |   类型   |                    备注                    |
| :-----: | :----: | :--------------------------------------: |
| fileDir | String | 登录信息位置，Nullable，默认：src/main/resources/login.sav |



### 加载登录状态

> StatusResponseDO loadLoginStatus( String fileDir, Runnable loginCallback, Runnable exitCallback )

重新加载登录dump下来的登录状态，🌰

```java
Shell shell = Shell.getInstance();
StatusResponseDO statusResponseDO = shell.loadLoginStatus(null, () -> {
                        System.out.println("loginCallback");
                    }, () -> {
                        System.out.println("exitCallback");
                    });
```

|      参数名      |    类型    |                    备注                    |
| :-----------: | :------: | :--------------------------------------: |
|    fileDir    |  String  | 登录信息位置，Nullable，默认：src/main/resources/login.sav |
| loginCallback | Runnable |             登录成功回调，Nullable              |
| exitCallback  | Runnable |              登出回调，Nullable               |



### 搜索好友

> List<ContactDO> searchFriends( String name, String userName, String remarkName, String wechatAccount )

搜索好友，🌰

```java
Shell shell = Shell.getInstance();
List<ContactDO> contactList = shell.searchFriends("手机传输助手", "filehelper", null, "wechatAccount");
```

|      参数名      |   类型   |               备注               |
| :-----------: | :----: | :----------------------------: |
|     name      | String |          昵称，Nullable           |
|   userName    | String |      联系人userName，Nullable      |
|  remarkName   | String |          备注名，Nullable          |
| wechatAccount | String | 微信账号（基本用不上😓，因为人家不给你），Nullable |



### 搜索群聊

> List<ContactDO> searchChatrooms( String name, String userName )

搜索群聊，🌰

```java
Shell shell = Shell.getInstance();
List<ContactDO> contactList = shell.searchChatrooms("广场舞", "@@sad9s8d98sd98df89s989sd89f89");
```

|   参数名    |   类型   |          备注          |
| :------: | :----: | :------------------: |
|   name   | String |     昵称，Nullable      |
| userName | String | 联系人userName，Nullable |



### 搜索公众号

> List<ContactDO> searchMps( String name, String userName )

搜索群聊，🌰

```java
Shell shell = Shell.getInstance();
List<ContactDO> contactList = shell.searchChatrooms("连岳", "@sa89df9sd89sd9898jsd9sd9");
```

|   参数名    |   类型   |          备注          |
| :------: | :----: | :------------------: |
|   name   | String |     昵称，Nullable      |
| userName | String | 联系人userName，Nullable |

