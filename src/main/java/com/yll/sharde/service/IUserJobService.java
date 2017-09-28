package com.yll.sharde.service;

import com.yll.sharde.entity.UserJob;

import java.util.List;

/**
 * @author：linlin.yang
 * @date：2017/8/31 13:24
 */
public interface IUserJobService {
    List<UserJob> getAllUserJob();

    List<UserJob> getUserJobByUserId(Long userId);

    void insert(UserJob userJob);

    void delete(Long id);
}
