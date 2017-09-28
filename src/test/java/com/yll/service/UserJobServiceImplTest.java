package com.yll.service;

import com.yll.sharde.config.Config;
import com.yll.sharde.entity.UserJob;
import com.yll.sharde.service.IUserJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author：linlin.yang
 * Date：2017/6/29 16:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class UserJobServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(UserJobServiceImplTest.class);
    @Autowired
    private IUserJobService userJobService;

    @Test
    public void testGetAllUserJOb(){
        logger.info("userJobs：" + userJobService.getAllUserJob());
    }

    @Test
    public void testGetUserJobByUserId(){
        logger.info("userJobs：" + userJobService.getUserJobByUserId(1l));
    }

    @Test
    public void testInsertUserJob() {
        for (int i=0;i<50;i++) {
            UserJob userJob = new UserJob();
            userJob.setUserId(Long.valueOf(i));
            userJob.setCompany("xxx");
            userJob.setAddress("beijing");
            userJob.setPosition("developer");
            userJobService.insert(userJob);
        }
    }

}
