# easiness-framework
easiness-framework 是基于 springboot 进行的整合；它致力于把程序员从代码逻辑中解放出来，把主要精力投入到业务逻辑中去；


## 短信管理

目前集成了两家短信接口：

1. submail的短信接口
2. 阿里云的短信接口

各家短信接口之间支付不停机的热切换；

未来根据自身业务发展还会陆续集成其他短信接口。

在短信管理上目前完成的业务：

1. 定时短信发送与管理
2. 短信发送记录


## 存储管理

目前集成了两种存储方式：

1. 本地存储
2. 阿里云 OSS 存储

各种存储方式之间可以进行不停机的热切换；

未来根据自身业务发展还会陆续集成其他存储方式。

在存储管理上目前完成的业务：

1. 文件上传和统一管理

## 系统配置管理

一个灵活的系统 在 不改动源代码 或 不重启的情况下 应该具有一点的可调节性；这就需要系统有一些可选择的配置项。

系统配置管理模块，可以为开发者节省一些重复开发“系统配置功能”的时间；

这里是集成了 redis 做配置缓存的，所以获取配置的“效率”大可不用担心。

## 身份认证、权限管理

目前 身份认证 及 权限管理 相关的模块已经趋于完善，但也并不能满足所有开发者的需求。所以这里的身份认证 和 权限管理 最大限度的保证了可扩展性。

这里已经集成了 shiro 作为默认的安全管理组件。未来可能还会再集成其他可选的安全管理组件（如：Spring Security 等），如果业务上有需求的话。

## 支付集成

目前已经针对两种支付场景集成了两家支付接口；

1. 支付宝
    1. 电脑网站支付
    2. 手机网站支付
    3. APP支付
    4. 当面付（用于商家扫用户付款码进行支付的场景，和用户扫商家动态码进行支付的场景）
1. 微信支付
    1. Native支付（扫码支付，可用于电脑网站支付场景）
    2. H5支付（用于手机网站支付场景）
    3. JSAPI支付（用于公众号内的支付场景）
    4. 小程序支付（用于微信小程序内的支付场景）
    5. APP支付
    6. 付款码支付（用于商家扫用户的付款码进行支付的场景）
    
    
未来根据自身业务发展还会陆续集成其他支付场景和支付接口。

## 事件总线

在此之前，我用过多个事件总线实现；我使用事件总线的目的是把程序中的多个模块解耦，使用事件在各个模块之间传递信息；

在使用“事件总线”解耦模块的过程中，我遇到一个问题，目前我使用的所有事件总线都不支持事务传递；也就是说，在触发事件的对象中开的事务，无法传递到“事件监听器”中，“事件监听器”抛出的异常无法促使事务回滚。

而我需要这样的“事件总线”，它可以把事务从触发事件的对象传递到“事件监听器”中，在“事件监听器”中抛出异常可以使事务回滚。我目前找不到这样“事件总线”实现，所以只能自己动手写一个。

这个事件总线的特性：

1. 支持事务传递
2. 支付多线程并发事件（与“事务”冲突）
3. 支付自定义注解（借助了Spring的注解功能）
4. 开发者可以选择嵌套事件的调用顺序（目前支持深度优先和广度优先两种策略）；

注：目前不支持事件死循环的检测和处理，需要用户自己避免出现事件死循环。

## 加密解密和编码解码

java 原生对加密解密的支持是很全面的，但是由于 java 原生实现中都是以 byte 数组作为数据交换方式，而开发中最常使用的是String类型（如：十六进制，Base64编码等。）。

所以在“加密解密”工具类中，对java原生的JCA做了些封装。

1. 数字摘要算法：DigestUtil
2. 消息认证码算法：MacUtil
3. 随机数工具：RandomUtil
4. 非对称加密RSA算法：RsaUtil
5. 对象序列化工具：SerializationUtil

## 数据备份

很多重要数据，需要在修改之前进行备份；如果全都由开发者手码完成，将造成大量时间和精力的浪费。

## 持久化封装 和 数据库查询

基本 spring-data-jpa 做了扩展，得到了一些基础的持久化操作类（如：model，repository,service 等）。开发者以这些持久化操作类为基础来构建自己的持久化模块会更加的统一和高效。

这里还集成了对 MyBatis 支持，用于帮助开发者完成大多数复杂的数据库查询。

高度的可扩展性 向开发者赋予了完成模块尚未支持的高级查询功能 的能力。

## 区域管理 （行政区划分管理）

一个面向全国（或全球）的应用程序，可能会有针对不同地理区域的业务，这时就需要用到行政区划分数据（区域管理）。

这里已经把国内的行政区域初始化进行数据库中了，开发者可以直接使用。

注：初始数据可能已经过时，请用户自己调整。


