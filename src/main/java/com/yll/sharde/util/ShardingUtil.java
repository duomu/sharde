package com.yll.sharde.util;

import com.yll.sharde.entity.User;
import com.yll.sharde.entity.UserExample;
import com.yll.sharde.entity.UserJob;
import com.yll.sharde.entity.UserJobExample;
import com.yll.sharde.handler.UserExampleRoutingHandler;
import com.yll.sharde.handler.UserJobExampleRoutingHandler;
import com.yll.sharde.handler.UserJobRoutingHandler;
import com.yll.sharde.handler.UserRoutingHandler;
import com.yll.sharde.repository.UserJobMapper;
import com.yll.sharde.repository.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：linlin.yang
 * @date：2017/8/30 18:25
 */
public class ShardingUtil {
    private static final Logger logger = LoggerFactory.getLogger(ShardingUtil.class);

    public static final String userLogicTable = "user_0";
    public static final String userJobLogicTable = "user_job_0";
    public static final String shardColumn = "user_id";
    private static final Integer TABLE_USER_AMOUNT = 10;//10个表
    private static final String DEFAULT_ISOLATOR = "_";
    private static Map<Class, String> TableNamePrefixPair = new ConcurrentHashMap<Class, String>();
    private static Map<String, String> ActualTableNamePrefixPair = new ConcurrentHashMap<String, String>();
    public static final Map<Class, String> LogicTablePair = new ConcurrentHashMap<Class, String>();
    public static final Map<Class, Class> RoutingInterceptorPair = new ConcurrentHashMap<Class, Class>();

    private static final Map<String, List<String>> actualTables = new HashMap<String, List<String>>();

    static {
        TableNamePrefixPair.put(User.class, "user");
        TableNamePrefixPair.put(UserMapper.class, "user");
        TableNamePrefixPair.put(UserJob.class, "user_job");
        TableNamePrefixPair.put(UserJobMapper.class, "user_job");
    }

    static {
        LogicTablePair.put(User.class, "user_0");
        LogicTablePair.put(UserExample.class, "user_0");
        LogicTablePair.put(UserJob.class, "user_job_0");
        LogicTablePair.put(UserJobExample.class, "user_job_0");
    }

    static {
        ActualTableNamePrefixPair.put(userLogicTable, "user");
        ActualTableNamePrefixPair.put(userLogicTable, "user");
        ActualTableNamePrefixPair.put(userJobLogicTable, "user_job");
        ActualTableNamePrefixPair.put(userJobLogicTable, "user_job");
    }

    static {
        RoutingInterceptorPair.put(User.class, UserRoutingHandler.class);
        RoutingInterceptorPair.put(UserExample.class, UserExampleRoutingHandler.class);
        RoutingInterceptorPair.put(UserJob.class, UserJobRoutingHandler.class);
        RoutingInterceptorPair.put(UserJobExample.class, UserJobExampleRoutingHandler.class);
    }

    /**
     * 初始化物理表名
     */
    static {
        final List<String> userActualTables = new LinkedList<String>();
        final List<String> userJobActualTables = new LinkedList<String>();

        for (int i = 0; i <= 9; i++) {
            userActualTables.add("user_" + i);
        }

        for (int i = 0; i <= 9; i++) {
            userJobActualTables.add("user_job_" + i);
        }

        actualTables.put(userLogicTable, userActualTables);
        actualTables.put(userJobLogicTable, userJobActualTables);
    }

    public static String getTableName(Class entity, Long userId) {
        if (entity == null) {
            return "";
        }

        String tableNamePrefix = TableNamePrefixPair.get(entity);
        if (tableNamePrefix == null) {
            return "";
        }

//        Long tableId = userId / TABLE_USER_AMOUNT + 1;//对userId取商获取表名，设定每个表存放相等的最大数据量TABLE_USER_AMOUNT
        Long tableId = userId % TABLE_USER_AMOUNT;//对userId取模获取表名

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tableNamePrefix).append(DEFAULT_ISOLATOR).append(tableId);
//        logger.info("userId：" + userId + ", " + stringBuilder.toString());

        return stringBuilder.toString();

    }

    /**
     * 获取1个表名
     * @param logicTable
     * @param userId
     * @return
     */
    public static String getTableName(String logicTable, Long userId) {
        if (logicTable == null || userId == null) {
            return "";
        }

        String tableNamePrefix = ActualTableNamePrefixPair.get(logicTable);
        if (tableNamePrefix == null) {
            return "";
        }

//        Long tableId = userId / TABLE_USER_AMOUNT + 1;//对userId取商获取表名，设定每个表存放相等的最大数据量TABLE_USER_AMOUNT
        Long tableId = userId % TABLE_USER_AMOUNT;//对userId取模获取表名

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tableNamePrefix).append(DEFAULT_ISOLATOR).append(tableId);
        logger.info("userId：" + userId + ", " + stringBuilder.toString());

        return stringBuilder.toString();

    }


    /**
     * 获取多个表名
     * @param logicTable
     * @param userIds
     * @return
     */
    public static List<String> getTableNames(String logicTable, List<Long> userIds) {
        if (userIds == null || userIds.size() == 0) {
            return actualTables.get(logicTable);
        }

        Set<String> tableNameSet = new HashSet<String>();
        for (Long userId : userIds) {
            String tableName = getTableName(logicTable, userId);
            if (tableName != null && !tableName.equals("")) {
                tableNameSet.add(tableName);
            }
        }

        return new ArrayList<String>(tableNameSet);
    }

    /**
     * 获取userId对应的表名map
     * @param logicTable
     * @param userIds
     * @return
     */
    public static Map<Long,String> getBatchTableNames(String logicTable, List<Long> userIds) {
        if (userIds == null || userIds.size() == 0) {
            return null;
        }

        Map<Long, String> tableNameMap = new HashMap<Long, String>();
        for (Long userId : userIds) {
            String tableName = getTableName(logicTable, userId);
            if (tableName != null && !tableName.equals("")) {
                tableNameMap.put(userId, tableName);
            }
        }

        return tableNameMap;
    }
}
