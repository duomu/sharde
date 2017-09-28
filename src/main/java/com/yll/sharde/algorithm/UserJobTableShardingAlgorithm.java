package com.yll.sharde.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import com.yll.sharde.entity.User;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @author：linlin.yang
 * @date：2017/8/30 19:39
 */
public class UserJobTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {
    private static final Integer TABLE_USER_AMOUNT = 10;//每个表存放10个useId的数据
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        for (String each : tableNames) {
            if (each.endsWith((shardingValue.getValue() / TABLE_USER_AMOUNT + 1) + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String each : tableNames) {
                if (each.endsWith((shardingValue.getValue() / TABLE_USER_AMOUNT + 1) + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                if (each.endsWith((shardingValue.getValue() / TABLE_USER_AMOUNT + 1) + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
