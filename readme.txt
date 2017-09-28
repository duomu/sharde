分表策略：
按照userId取模：
假设要分N张表：t_0, t_1, t_2, t_3,…,t_(N-1)，则表序号为userId%N
这种分表策略比较简单常用，缺点是每张表的大小不可控，水平扩展性不好，需要预估数据增长速度，选择合适的表个数

使用以上策略将user和user_job分表分成10个表，即
user_0,user_1,user_2,...,user_9
user_job_0,user_job_1,user_job_2,...,user_job_9

路由策略：
1.mybatis拦截器
2.替换sql表名占位符
3.sharding-jdbc

由于sharding-jdbc不能满足我的一些需求，故选择策略1+2，且所有不传分片值的sql操作都采用策略2处理

FAQ:
1.表规则设定的逻辑表名要和sql中的表名一致

2.执行sql报异常：字段"name"是未知的
如果sql和数据表中的字段是正确的，查看表规则配置是否正确，包括逻辑表名、实际表名等

3.无法读取方案文档 'http://www.dangdang.com/schema/ddframe/rdb/rdb.xsd', 原因为 1) 无法找到文档; 2) 无法读取文档; 3) 文档的根元素不是 xsd:schema
如果已经依赖了相关jar包依然报此错误，查看jar有没有添加到classpath中，web项目需要查看相关jar包是否正确部署到了WEB-INF/lib目录
