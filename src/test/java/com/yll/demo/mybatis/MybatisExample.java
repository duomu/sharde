package com.yll.demo.mybatis;

import com.yll.sharde.entity.User;
import com.yll.sharde.entity.UserExample;
import com.yll.sharde.repository.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis原生使用
 * @author：linlin.yang
 * @date：2017/9/16 10:22
 */
public class MybatisExample {
    public static void main(String[] args) throws IOException {
        //SqlSessionFactoryBuilder
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        //SqlSessionFactory
        SqlSessionFactory factory = builder.build(inputStream);

        //SqlSession
        SqlSession sqlSession = factory.openSession();

        //simple select
        UserExample example = new UserExample();
        List<User> users = sqlSession.selectList("selectByExample", example);
        if (users == null || users.size() == 0) {
            System.out.println("select 0");
        } else {
            System.out.println("select " + users.size());
        }

        //mapper select
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        users = userMapper.selectByExample(example);
        if (users == null || users.size() == 0) {
            System.out.println("select 0");
        } else {
            System.out.println("select " + users.size());
        }

    }
}
