package com.yll.sharde.service;

import com.yll.sharde.entity.User;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/6/29 15:59
 */
public interface IUserService {

    /**
     * 查询用户总数【路由到多个表】
     * @return
     */
    int getUserCount();

    /**
     * 查询所有用户【未路由，查询逻辑表】
     * @return
     */
    List<User> queryAllUser();

    /**
     * 查询所有用户【路由到多个表】
     * @return
     */
    List<User> getAllUser();

    /**
     * 根据userId查询用户【路由到单个表】
     * @param userId
     * @return
     */
    User getUserById(Long userId);

    /**
     * 查询指定userId的用户【路由到多个表】
     * @param userIds
     * @return
     */
    List<User> getUserByIds(List<Long> userIds);

    /**
     * 根据name查询用户【路由到多个表】
     * @param name
     * @return
     */
    List<User> getUserByName(String name);

    /**
     * 根据userId和name查询用户【路由到单个表】
     * @param userId
     * @param name
     * @return
     */
    List<User> getUserByIdAndName(Long userId, String name);

    /**
     * 插入单个用户【路由到单个表】
     * @param user
     */
    void insert(User user);

    /**
     * 更新单个用户【路由到单个表】
     * @param user
     */
    void update(User user);

    /**
     * 删除单个用户【路由到单个表】
     * @param userId
     */
    void delete(Long userId);

    /**
     * 批量插入用户【路由到多个表】
     * @param users
     */
    void insertBatch(List<User> users);

    /**
     * 批量更新用户【路由到多个表】
     * @param users
     */
    void updateBatch(List<User> users);

    /**
     * 批量删除用户【路由到多个表】
     * @param userIds
     */
    void deleteBatch(List<Long> userIds);
}
