# springboot-rabbitmq
springboot+rabbitmq+MySQL实现消息同步，传输java对象字节流

## 背景
为了实现多数据中心，异地消息同步，测试使用消息队列，和不使用消息队列的不同效果
## 简介
主要实现springboot 使用rabbitMQ 进行数据插入，多线程测试直接调用数据库插入和调用发送消息，通过消息消费者插入

## 测试结果
5000条数据，消息队列9秒钟全部入队，程序执行完毕   大概八九分钟全部消费完毕，项目停止后，再启动未被消费的可以继续消费，
5000条数据， 数据库直接插入，全部插入大概要100s

## 计划
- 增加rabbitMQ高可用配置，使用Federation插件集群，尝试异地消息同步
- 增加canal使用，监控数据库变化，实时发送消息




