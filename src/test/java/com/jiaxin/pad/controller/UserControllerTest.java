package com.jiaxin.pad.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiaxin.pda.PdaApplication;
import com.jiaxin.pda.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 用户控制器测试类
 * @author milo
 */
@SpringBootTest(classes = PdaApplication.class)
@WebAppConfiguration
public class UserControllerTest extends AbstractTestNGSpringContextTests {
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
    @BeforeClass
    public void setup() {
        //构造MockMvc
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    /**
     * 使用1个线程执行1000次
     * @throws Exception
     */
    @Test(threadPoolSize = 100,invocationCount = 7000)
    public void queryUserInfoById() throws Exception{
        logger.info("根据ID查询用户信息");
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.get("/user/findById/fb8529492ace4c749081a1803bbdabd6")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void insertUser() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userName","<name>嘉义<name>");
        userCondition.put("password","999999");
        logger.info("插入用户信息条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/user/registerUser")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteUser() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","a8f67c704e9f4dc981698084dffe4ce8");
        userCondition.put("reversion",1);
        logger.info("删除用户信息条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteUser")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void updateUserName() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","27d98c37195e448193639c9c382235ef");
        userCondition.put("userName","lele");
        userCondition.put("reversion",2);
        logger.info("修改用户姓名条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/user/updateUserName")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateUserPassword() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","80bc9bf1702040f884a52fa0c4c0b4e9");
        userCondition.put("password","999999");
        userCondition.put("reversion",2);
        logger.info("修改用户密码条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/user/updateUserPassword")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void userLogin() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userName","lele");
        userCondition.put("password","123456");
        logger.info("登录条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void userLogout() throws Exception{
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.get("/user/logout")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NzcwOTI4MzQ4MjEsInBheWxvYWQiOiJcIjhmMzZhOGYyZjQ0ZjQ2ZGE4MTc0OWZiOGZhMjY4NjllXCIifQ.pnKzFRkSr6XsSDnUUKUjV1psCmrn9iVthQc_LD7r0L8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryUserInfoByPage() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("current",1);
        userCondition.put("offset",10);
        logger.info("分页查询条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/user/queryUserListByPage")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void giveUserRole() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userId",8);
        userCondition.put("roleId",1);
        logger.info("插入添加用户权限条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/user/insertUserRole")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void cancelUserRole() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("userId",1);
        userCondition.put("roleId",1);
        logger.info("删除用户权限条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/deleteUserRole")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryRoleByUserId() throws Exception{
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.get("/user/queryRoleByUserId/8")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryMenuListByUserToken() throws Exception{
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/queryUserPrivileges")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NzcwOTMwMjcxMzIsInBheWxvYWQiOiJcIjhmMzZhOGYyZjQ0ZjQ2ZGE4MTc0OWZiOGZhMjY4NjllXCIifQ.W0mqGcoAJBrLlPV2keIC473EQrv8W25A-cRuzPUxaJE")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
