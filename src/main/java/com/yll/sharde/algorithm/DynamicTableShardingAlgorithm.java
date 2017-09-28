package com.yll.sharde.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

import java.util.Collection;

/**
 * @author：linlin.yang
 * @date：2017/8/31 13:10
 */
public class DynamicTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm {

    public String doEqualSharding(Collection collection, ShardingValue shardingValue) {
        return null;
    }

    public Collection<String> doInSharding(Collection collection, ShardingValue shardingValue) {
        return null;
    }

    public Collection<String> doBetweenSharding(Collection collection, ShardingValue shardingValue) {
        return null;
    }
}
