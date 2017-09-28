package com.yll.controller;

import com.yll.sharde.config.Config;
import com.yll.sharde.config.SpringMvcConfig;
import com.yll.sharde.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Author：linlin.yang
 * Date：2017/6/29 16:45
 */
@RunWith(SpringJUnit4ClassRunner.class)//调用Spring单元测试类
@WebAppConfiguration//调用javaWEB的组件，比如自动注入ServletContext Bean等等
//@ContextConfiguration(classes = {Config.class, SpringMvcConfig.class})//加载Spring配置文件
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml","classpath:spring/springmvcContext.xml"})//加载Spring配置文件
public class UserControllerTest {
    private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    MockMvc mockMvc;
    @Autowired
    private ServletContext context;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testQueryUserCount() throws Exception {
        //准备参数
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/user/getUserById")
                .param("userId","1"))
                .andExpect(status().isOk())
                .andDo(print());//输出信息
        MvcResult mvcResult = resultActions.andReturn();
        Object result = mvcResult.getRequest().getAttribute("message");//返回给客户端的数据，在request里面
        logger.info("message: " + result);
    }

}
