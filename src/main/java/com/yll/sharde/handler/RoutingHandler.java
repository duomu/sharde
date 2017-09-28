package com.yll.sharde.handler;

import org.apache.ibatis.executor.statement.StatementHandler;

/**
 * Author：linlin.yang
 * Date：2017/9/16 17:32
 */
public interface RoutingHandler {
    /**
     * 路由表名
     * @param statementHandler
     */
    void route(StatementHandler statementHandler);
}
