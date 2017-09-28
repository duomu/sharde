package com.yll.sharde.handler;

import com.yll.sharde.entity.UserExample;
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
public class UserExampleRoutingHandler implements RoutingHandler {
    public void route(StatementHandler statementHandler) {
        BoundSql boundSql = statementHandler.getBoundSql();
        Object paramObject = boundSql.getParameterObject();
        UserExample userExample = (UserExample) paramObject;
        if (userExample != null) {
            String originalSql = boundSql.getSql();
            String logicTable = ShardingUtil.LogicTablePair.get(UserExample.class);
            if (!originalSql.contains(logicTable)) {
                return;
            }

            //所有XXXExample都设且只设一个Criterion
            List<UserExample.Criteria> criterias = userExample.getOredCriteria();
            if (criterias == null || criterias.size() == 0) {
                return;
            }

            List<UserExample.Criterion> criterions = userExample.getOredCriteria().get(0).getAllCriteria();
            if (criterions == null || criterions.size() == 0) {
                return;
            }
            for (UserExample.Criterion criterion : criterions) {
                //如果sql包括分片字段，则路由实际表名
                //TODO 插入操作，利用主键自增，不设userId，将无法路由，所以分片键最好用户自己提供自增值
                if (criterion.getCondition().contains(RoutingConstant.SHARD_COLUMN) && criterion.getValue() != null) {
                    String actualTable = ShardingUtil.getTableName(logicTable, (Long) criterion.getValue());
                    ReflectionUtil.setFieldValue(boundSql, "sql", originalSql.replace(logicTable, actualTable));
                    break;
                }
            }
        }
    }
}
