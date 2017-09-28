package com.yll.sharde.plugin;

import com.yll.sharde.factory.RoutingFactory;
import com.yll.sharde.handler.RoutingHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author：linlin.yang
 * @date：2017/9/15 20:19
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class TableRoutingInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(TableRoutingInterceptor.class);
    /**
     * 处理拦截内容
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        Object obj = boundSql.getParameterObject();
        if(obj == null){
            return invocation.proceed();
        }
        RoutingHandler routingHandler = RoutingFactory.getRoutingInterceptor(obj);
        if (routingHandler == null) {
            logger.info("actual sql：" + statementHandler.getBoundSql().getSql());
            return invocation.proceed();
        }
        //路由实际表名
        routingHandler.route(statementHandler);

        logger.info("actual sql：" + statementHandler.getBoundSql().getSql());
        return invocation.proceed();
    }

    /**
     *决定是否触发intercept()方法，s使用jdk动态代理，给target创建一个代理对象，以实现方法拦截和增强功能，此对象会回调intercept()方法
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 给自定义的拦截器传递xml配置的属性参数
     * @param properties
     */
    public void setProperties(Properties properties) {

    }
}
