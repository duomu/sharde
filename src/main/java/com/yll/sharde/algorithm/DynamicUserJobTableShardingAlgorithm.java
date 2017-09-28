package com.yll.sharde.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import com.yll.sharde.entity.UserJob;
import com.yll.sharde.util.ShardingUtil;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @author：linlin.yang
 * @date：2017/8/30 19:39
 */
public class DynamicUserJobTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        return ShardingUtil.getTableName(UserJob.class, shardingValue.getValue());
    }

    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        for (Long value : shardingValue.getValues()) {
            result.add(ShardingUtil.getTableName(UserJob.class, value));
        }
        return result;
    }

    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<String>(tableNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            result.add(ShardingUtil.getTableName(UserJob.class, i));
        }
        return result;
    }
}
