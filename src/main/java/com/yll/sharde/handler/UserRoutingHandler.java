package com.yll.sharde.handler;

import com.yll.sharde.entity.User;
import com.yll.sharde.util.ReflectionUtil;
import com.yll.sharde.util.ShardingUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2017/9/16 17:34
 */
@Component
public class UserRoutingHandler implements RoutingHandler {
    public void route(StatementHandler statementHandler) {
        BoundSql boundSql = statementHandler.getBoundSql();
        Object paramObject = boundSql.getParameterObject();
        User user = (User) paramObject;
        if (user != null) {
            String originalSql = boundSql.getSql();
            String logicTable = ShardingUtil.LogicTablePair.get(User.class);
            if (!originalSql.contains(logicTable)) {
                return;
            }

            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            if (parameterMappings == null || parameterMappings.size() == 0) {
                return;
            }
            for (ParameterMapping pm : parameterMappings) {
                if (pm.getProperty().equals(RoutingConstant.SHARD_PROPERTY)) {
                    String actualTable = ShardingUtil.getTableName(logicTable, user.getUserId());
                    ReflectionUtil.setFieldValue(boundSql, "sql", originalSql.replace(logicTable, actualTable));
                    break;
                }
            }
        }
    }
}
