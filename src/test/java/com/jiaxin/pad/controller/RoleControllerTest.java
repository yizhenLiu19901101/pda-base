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
 * 角色控制器测试类
 * @author milo
 */
@SpringBootTest(classes = PdaApplication.class)
@WebAppConfiguration
public class RoleControllerTest extends AbstractTestNGSpringContextTests {
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

    @Test
    public void insertRole() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("roleName","超级管理员");
        logger.info("插入角色信息条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/role/insertRole")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateRoleName() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","0061b24449754b2780771691c19fa250");
        userCondition.put("roleName","系统管理员");
        userCondition.put("reversion",1);
        logger.info("修改角色名称条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/role/updateRoleName")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteRole() throws Exception{
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","1f2e572cb99344459ed9d7e600e19574");
        userCondition.put("reversion",2);
        logger.info("删除角色");
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/role/deleteRole")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryRoleByPage() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("offset",10);
        userCondition.put("current",1);
        userCondition.put("roleName","模块管理员");
        logger.info("分页查询角色列表条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/role/selectRoleByPage")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givePrivilege() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("menuId",3);
        userCondition.put("roleId",1);
        logger.info("角色授权条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/role/givePrivilege")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void cancelPrivilege() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("menuId",3);
        userCondition.put("roleId",1);
        logger.info("角色授权条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/role/cancelPrivilege")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryPrivilegeByRoleId() throws Exception{
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.get("/role/queryPrivilegeByRoleId/1")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
