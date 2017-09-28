package com.yll.sharde.repository;

import com.yll.sharde.entity.UserJob;
import com.yll.sharde.entity.UserJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserJobMapper {
    int countByExample(UserJobExample example);

    int deleteByExample(UserJobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserJob record);

    int insertSelective(UserJob record);

    List<UserJob> selectByExample(UserJobExample example);

    UserJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserJob record, @Param("example") UserJobExample example);

    int updateByExample(@Param("record") UserJob record, @Param("example") UserJobExample example);

    int updateByPrimaryKeySelective(UserJob record);

    int updateByPrimaryKey(UserJob record);
}