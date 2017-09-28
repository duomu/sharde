package com.yll.sharde.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @author：linlin.yang
 * @date：2017/8/30 19:39
 */
public class DefaultDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

    public String doEqualSharding(Collection<String> databaseNames, ShardingValue<Integer> shardingValue) {
        return databaseNames.isEmpty() ? null : databaseNames.iterator().next();
    }

    public Collection<String> doInSharding(Collection<String> databaseNames, ShardingValue<Integer> shardingValue) {
        return databaseNames;
    }

    public Collection<String> doBetweenSharding(Collection<String> databaseNames, ShardingValue<Integer> shardingValue) {
        return databaseNames;
    }
}
