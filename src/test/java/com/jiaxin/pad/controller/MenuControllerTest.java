package com.jiaxin.pad.controller;

import com.alibaba.fastjson.JSONObject;
import com.jiaxin.pda.PdaApplication;
import com.jiaxin.pda.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
 * 菜单控制器测试类
 * @author milo
 */

@SpringBootTest(classes = PdaApplication.class)
@WebAppConfiguration
public class MenuControllerTest extends AbstractTestNGSpringContextTests {

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
    private final Logger logger = LoggerFactory.getLogger(MenuControllerTest.class);

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
    public void insertMenu() throws Exception{
        logger.info("插入菜单信息");
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("menuName","我的");
        userCondition.put("parentMenuId",0);
        userCondition.put("menuPath","/my");
        userCondition.put("menuNo",4);
        userCondition.put("menuLevel",1);
        logger.info("插入菜单信息条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/menu/insertMenu")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateMenuName() throws Exception{
        logger.info("修改菜单名称");
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","bb6d90c3778b46e6b67f8748966a15b0");
        userCondition.put("menuName","小看版");
        userCondition.put("reversion",1);
        logger.info("修改菜单名称条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/menu/updateMenuName")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteMenu() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("id","ccecd5d6b0294c7d97a6aa6654dd0390");
        userCondition.put("reversion",16);
        logger.info("删除菜单");
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/menu/delete")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryByPage() throws Exception{
        logger.info("分页查询菜单对象");
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("current",1);
        userCondition.put("offset",10);
        logger.info("分页查询菜对象 条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/menu/queryMenuListByPage")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
