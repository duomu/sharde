package com.yll.sharde.handler;

import com.yll.sharde.entity.UserJobExample;
import com.yll.sharde.util.ReflectionUtil;
import com.yll.sharde.util.ShardingUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2017/9/16 17:34
 */
@Component
public class UserJobExampleRoutingHandler implements RoutingHandler {
    public void route(StatementHandler statementHandler) {
        BoundSql boundSql = statementHandler.getBoundSql();
        Object paramObject = boundSql.getParameterObject();
        UserJobExample userJobExample = (UserJobExample) paramObject;
        if (userJobExample != null) {
            String originalSql = boundSql.getSql();
            String logicTable = ShardingUtil.LogicTablePair.get(UserJobExample.class);
            if (!originalSql.contains(logicTable)) {
                return;
            }

            //所有XXXExample都设且只设一个Criterion
            List<UserJobExample.Criterion> criterions = userJobExample.getOredCriteria().get(0).getAllCriteria();
            if (criterions == null || criterions.size() == 0) {
                return;
            }

            for (UserJobExample.Criterion criterion : criterions) {
                //如果sql包括分片字段，则路由实际表名
                if (criterion.getCondition().contains(RoutingConstant.SHARD_COLUMN) && criterion.getValue() != null) {
                    String actualTable = ShardingUtil.getTableName(logicTable, (Long) criterion.getValue());
                    ReflectionUtil.setFieldValue(boundSql, "sql", originalSql.replace(logicTable, actualTable));
                    break;
                }
            }
        }
    }
}
