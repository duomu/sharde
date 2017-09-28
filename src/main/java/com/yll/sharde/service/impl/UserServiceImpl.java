package com.yll.sharde.service.impl;

import com.yll.sharde.entity.User;
import com.yll.sharde.entity.UserExample;
import com.yll.sharde.repository.UserMapper;
import com.yll.sharde.service.IUserService;
import com.yll.sharde.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Author：linlin.yang
 * Date：2017/6/29 16:00
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    public int getUserCount() {
        return userMapper.countAll(getPrameters());
    }

    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }

    public List<User> getAllUser() {
        return userMapper.selectAll(getPrameters());

        //未路由
        //UserExample example = new UserExample();
        //return userMapper.selectByExample(example);
    }

    public User getUserById(Long userId) {
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(example);
        if ((users == null || users.size() == 0)) {
            return null;
        }
        return users.get(0);

        //未分表
        //return userMapper.selectByPrimaryKey(userId);

    }

    public List<User> getUserByIds(List<Long> userIds) {
        if (userIds == null || userIds.size() == 0) {
            return null;
        }
        return userMapper.selectAll(getPrameters(userIds));
    }

    public List<User> getUserByName(String name) {
        return userMapper.selectByName(getPrameters(name));

        //未路由
//        UserExample example = new UserExample();
//        example.createCriteria().andNameEqualTo(name);
//        return userMapper.selectByExample(example);
    }

    public List<User> getUserByIdAndName(Long userId, String name) {
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(userId).andNameEqualTo(name);
        return userMapper.selectByExample(example);
    }

    public void insert(User user) {
//        int result= userMapper.insert(user);
        int result= userMapper.insertSelective(user);
        logger.info("userId：" + user.getUserId() + ", excute result：" + result);
    }

    public void insertBatch(List<User> users) {
        List<Map<String, Object>> parameterMaps = getBatchPrameters(users);
        if (parameterMaps == null || parameterMaps.size() == 0) {
            return;
        }

        for (Map<String, Object> parameters : parameterMaps) {
            int result = userMapper.insertBatchUser(parameters);
            logger.info("insertBatch, excute result：" + result);
        }
    }

    public void update(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        logger.info("update, excute result：" + result);
    }

    public void updateBatch(List<User> users) {
        List<Map<String, Object>> parameterMaps = getBatchPrameters(users);
        if (parameterMaps == null || parameterMaps.size() == 0) {
            return;
        }

        for (Map<String, Object> parameters : parameterMaps) {
            int result = userMapper.updateBatchUser(parameters);
            logger.info("updateBatch, excute result：" + result);
        }
    }

    @Transactional
    public void delete(Long userId) {
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        int result = userMapper.deleteByExample(example);
        logger.info("delete, excute result：" + result);
    }

    public void deleteBatch(List<Long> userIds) {
        List<Map<String, Object>> parameterMaps = getBatchPrametersById(userIds);
        if (parameterMaps == null || parameterMaps.size() == 0) {
            return;
        }

        for (Map<String, Object> parameters : parameterMaps) {
            int result = userMapper.deleteBatchUser(parameters);
            logger.info("deleteBatch, excute result：" + result);
        }
    }

    private Map<String, Object> getPrameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        List<String> tableNames = ShardingUtil.getTableNames(ShardingUtil.userLogicTable, null);
        parameters.put("tableNameList", tableNames);
        return parameters;
    }

    private Map<String, Object> getPrameters(List<Long> userIds) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        List<String> tableNames = ShardingUtil.getTableNames(ShardingUtil.userLogicTable, userIds);
        parameters.put("tableNameList", tableNames);
        if (userIds != null && userIds.size() > 0) {
            parameters.put("userIds", convertToInClause(userIds));
        }

        return parameters;
    }

    private Map<String, Object> getPrameters(String name) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        List<String> tableNames = ShardingUtil.getTableNames(ShardingUtil.userLogicTable, null);
        parameters.put("tableNameList", tableNames);
        parameters.put("name", name);
        return parameters;
    }

    private List<Map<String, Object>> getBatchPrameters(List<User> users) {
        if (users == null || users.size() == 0) {
            return null;
        }
        List<Long> userIds = new LinkedList<Long>();
        for (User user : users) {
            userIds.add(user.getUserId());
        }

        Map<Long, String> tableNameMap = ShardingUtil.getBatchTableNames(ShardingUtil.userLogicTable, userIds);
        if (tableNameMap == null || tableNameMap.size() == 0) {
            return null;
        }
        //获取批量插入映射到相同表的记录Map
        Map<String, List<User>> batchUsers = new HashMap<String, List<User>>();
        for (User user : users) {
            String tableName = tableNameMap.get(user.getUserId());
            if (batchUsers.containsKey(tableName)) {
                batchUsers.get(tableName).add(user);
            }else{
                List<User> userList = new LinkedList<User>();
                userList.add(user);
                batchUsers.put(tableName, userList);
            }
        }

        if (batchUsers == null || batchUsers.size() == 0) {
            return null;
        }

        List<Map<String, Object>> parameterMaps = new LinkedList<Map<String, Object>>();
        for (Map.Entry<String, List<User>> entry : batchUsers.entrySet()) {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("tableName", entry.getKey());
            parameterMap.put("users", entry.getValue());
            parameterMaps.add(parameterMap);
        }

        return parameterMaps;
    }

    private List<Map<String, Object>> getBatchPrametersById(List<Long> userIds) {
        Map<Long, String> tableNameMap = ShardingUtil.getBatchTableNames(ShardingUtil.userLogicTable, userIds);
        if (tableNameMap == null || tableNameMap.size() == 0) {
            return null;
        }
        //获取批量插入映射到相同表的记录Map
        Map<String, List<Long>> batchUserIds = new HashMap<String, List<Long>>();
        for (Long userId : userIds) {
            String tableName = tableNameMap.get(userId);
            if (batchUserIds.containsKey(tableName)) {
                batchUserIds.get(tableName).add(userId);
            }else{
                List<Long> userList = new LinkedList<Long>();
                userList.add(userId);
                batchUserIds.put(tableName, userList);
            }
        }

        if (batchUserIds == null || batchUserIds.size() == 0) {
            return null;
        }

        List<Map<String, Object>> parameterMaps = new LinkedList<Map<String, Object>>();
        for (Map.Entry<String, List<Long>> entry : batchUserIds.entrySet()) {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("tableName", entry.getKey());
            parameterMap.put("userIds", convertToInClause(entry.getValue()));
            parameterMaps.add(parameterMap);
        }

        return parameterMaps;
    }

    private String convertToInClause0(List<Long> userIds) {
        StringBuilder sb = new StringBuilder();
        for (Long userId : userIds) {
            sb.append(userId).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);//去除最后一个逗号
        return sb.toString();
    }

    /**
     * 将list转换成逗号分隔的字符串
     * @param values
     * @param <T>
     * @return
     */
    private <T> String convertToInClause(List<T> values) {
        if (values == null || values.size() == 0) {//这种情况不应出现
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (T value : values) {
            builder.append(value).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);//去除最后一个逗号

        return builder.toString();
    }
}
