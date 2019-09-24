package com.jiaxin.pad.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiaxin.pda.PdaApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 用户控制器测试类
 * @author milo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PdaApplication.class)
@WebAppConfiguration
public class UserControllerTest {
    /**
     * 注入网络应用环境
     */
    @Autowired
    private WebApplicationContext wac;
    /**
     * Mock
     */
    private MockMvc mockMvc;

    /**
     * 日志对象
     */
    private final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    /**
     * 测试之前需要完成的动作
     */
    @Before
    public void setup() {
        //构造MockMvc
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void firstCase() throws Exception{
        logger.info("根据ID查询用户信息");
        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.add("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Njg2OTExNDMwNzcsInBheWxvYWQiOiIxIn0.SVtRojo_PXhMskZGLS8TqcrKeZ2d6sQTIqWNKwOVjps");
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.get("/user/findById/27d98c37195e448193639c9c382235ef")
                .headers(httpHeaders)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void secondCase() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userName","vivian");
        logger.info("插入用户信息条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/user/insertUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void thirdCase() throws Exception{
        logger.info("根据ID删除信息");
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteById/ed2109f4642a4a4490e6e2ddc224d3b0")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void fourCase() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","27d98c37195e448193639c9c382235ef");
        userCondition.put("userName","lele");
        userCondition.put("reversion",1);
        logger.info("修改用户姓名条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/user/updateUserName")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void fiveCase() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","80bc9bf1702040f884a52fa0c4c0b4e9");
        userCondition.put("password","999999");
        userCondition.put("reversion",2);
        logger.info("修改用户密码条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/user/updateUserPassword")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void sixCase() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userName","xuexue");
        userCondition.put("password","999999");
        logger.info("登录条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void sevenCase() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userToken","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Njg2OTA5NTUwNjgsInBheWxvYWQiOiIxIn0.lBt7srIZJ-YuvAirDo36kwjuk8BWs73gtJQqrk12AG4");
        logger.info("退出条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/user/logout")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void eightCase() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("current",1);
        userCondition.put("offset",10);
        logger.info("分页查询条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/user/queryUserListByPage")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void nineCase() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userId",8);
        userCondition.put("roleId",1);
        logger.info("插入添加用户权限条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/user/insertUserPrivilege")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void tenCase() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userId",1);
        userCondition.put("roleId",1);
        logger.info("删除用户权限条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteUserPrivilege")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void elevenCase() throws Exception{
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.get("/user/queryRoleByUserId/8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
