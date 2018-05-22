package com.yll.sharde.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author：linlin.yang
 * @date：2018/4/12 18:43
 */
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class}
)})
public class MyPlugin implements Interceptor {
    /**
     * 拦截被代理对象的目标方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable {
//        return simpleIntercept(invocation);
        return complexIntercept(invocation);
    }

    /**
     * 生成代理对象
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        System.err.println("生成代理对象......");
        return Plugin.wrap(target, this);
    }

    /**
     * 设置插件配置的属性
     * @param properties
     */
    public void setProperties(Properties properties) {

    }

    /**
     * 简单的拦截
     * @param invocation
     * @return
     * @throws Throwable
     */
    private Object simpleIntercept(Invocation invocation) throws Throwable {
        System.err.println("before......");
        Object result = invocation.proceed();
        System.err.println("after......");
        return result;
    }

    /**
     * 复杂的拦截，将sql中的表名user_0改成user
     * @param invocation
     * @return
     * @throws Throwable
     */
    private Object complexIntercept(Invocation invocation) throws Throwable {
        StatementHandler stmtHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStmtHandler = SystemMetaObject.forObject(stmtHandler);
        String sql = (String) metaStmtHandler.getValue("delegate.boundSql.sql");
        System.out.println(sql);
        if (sql.indexOf("user_") != -1) {
            String newSql = sql.trim();
            newSql = newSql.replace("user_0", "user");
            metaStmtHandler.setValue("delegate.boundSql.sql", newSql);
        }

        return invocation.proceed();
    }
}
