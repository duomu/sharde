package com.yll.sharde.repository;

import com.yll.sharde.entity.User;
import com.yll.sharde.entity.UserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    List<User> selectAll();

    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 只查询必要的表
     * @param parameters
     * @return
     */
    List<User> selectAll(Map<String, Object> parameters);

    int countAll(Map<String, Object> parameters);
    
    List<User> selectByName(Map<String, Object> parameters);

    int insertBatchUser(Map<String, Object> parameters);

    int updateBatchUser(Map<String, Object> parameters);

    int deleteBatchUser(Map<String, Object> parameters);

}