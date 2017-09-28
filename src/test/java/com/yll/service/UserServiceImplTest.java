package com.yll.service;

import com.yll.sharde.config.Config;
import com.yll.sharde.entity.User;
import com.yll.sharde.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/6/29 16:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = Config.class)//注解配置
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})//xml配置，推荐
public class UserServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
    @Autowired
    private IUserService userService;

    @Test
    public void testGetAllUser(){
        List<User> users = userService.getAllUser();
//        List<User> users = userService.queryAllUser();
        if (users == null || users.size() == 0) {
            logger.info("count is 0");
            return;
        } else {
            logger.info("count is " + users.size());
//            for (User user : users) {
//                logger.info(user.getUserId() + ", " + user.getName() + ", " + user.getAge());
//            }
        }
    }

    @Test
    public void testGetUserCount() {
        int count = userService.getUserCount();
        logger.info("count is " + count);
    }

    @Test
    public void testGetUserById(){
        User user = userService.getUserById(115548128408502272l);
        if (user != null) {
            logger.info(user.getUserId() + ", " + user.getName() + ", " + user.getAge());
        }
    }

    @Test
    public void testGetUserByIds() {
        List<User> users = userService.getUserByIds(Arrays.asList(115548128731463680l, 115548128731463681l, 115548128408502272l));
        if (users == null || users.size() == 0) {
            logger.info("count is 0");
            return;
        } else {
            logger.info("count is " + users.size());
        }
    }

    @Test
    public void testGetUserByName() {
        List<User> users = userService.getUserByName("user1");
        if (users == null || users.size() == 0) {
            logger.info("users count: 0");
        } else {
            logger.info("users count:" + users.size());
        }
    }

    @Test
    public void testGetUserByIdAndName(){
        List<User> users = userService.getUserByIdAndName(1l, "user1");
    }

    @Test
    public void testInsertOneUser() {
        User user = new User();
        user.setUserId(5l);
        user.setName("user1");
        user.setAge(26);
        user.setAddress("beijing");
        userService.insert(user);
    }

    @Test
    public void testInsertUser() {
        for (int i=1;i<=10;i++) {
            User user = new User();
            user.setName("user" + i);
            user.setAge(26);
            user.setAddress("beijing");
            try {
                userService.insert(user);
            } catch (Exception e) {
                logger.error("create user failed: userId=" + i + ", error：" + e);
            }
        }
    }

    /**
     * sharding-jdbc目前不支持批量插入数据
     * 这里使用表名替换策略实现批量插入数据
     */
    @Test
    public void testInsertBatchUser() {
        List<User> users = new LinkedList<User>();
        for (int i=0;i<10;i++) {
            User user = new User();
            user.setUserId(Long.valueOf(i + 1));
            user.setName("user" + i);
            user.setAge(26);
            user.setAddress("beijing");
            users.add(user);
        }

        userService.insertBatch(users);
    }

    @Test
    public void testUpdateUser() {
        User user = userService.getUserById(1l);
        user.setName("duomu");
        userService.update(user);
    }

    @Test
    public void testUpdateBatchUser() {
        List<User> users = userService.getAllUser();
        if (users != null && users.size() > 0) {
            for (User user : users) {
                user.setName("duomu");
            }
            userService.updateBatch(users);
        }
    }

    @Test
    public void testDeleteOneUser() {
        userService.delete(2l);
    }

    @Test
    public void testDeleteBatchUser() {
        userService.deleteBatch(Arrays.asList(1l, 2l, 3l));
    }
}
