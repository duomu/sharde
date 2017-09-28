package com.yll.sharde.service.impl;

import com.yll.sharde.entity.UserJob;
import com.yll.sharde.entity.UserJobExample;
import com.yll.sharde.repository.UserJobMapper;
import com.yll.sharde.service.IUserJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2017/8/31 13:26
 */
@Service("userJobService")
public class UserJobServiceImpl implements IUserJobService {
    @Autowired
    private UserJobMapper userJobMapper;

    public List<UserJob> getAllUserJob() {
        UserJobExample example = new UserJobExample();
        return userJobMapper.selectByExample(example);
    }

    public List<UserJob> getUserJobByUserId(Long userId) {
        UserJobExample example = new UserJobExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userJobMapper.selectByExample(example);
    }

    public void insert(UserJob userJob) {
        userJobMapper.insert(userJob);
    }

    public void delete(Long id) {
        userJobMapper.deleteByPrimaryKey(id);
    }
}
