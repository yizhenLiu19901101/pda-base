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
 * 财务控制器测试类
 * @author milo
 */
@SpringBootTest(classes = PdaApplication.class)
@WebAppConfiguration
public class FinanceDetailControllerTest extends AbstractTestNGSpringContextTests {
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
    private final Logger logger = LoggerFactory.getLogger(DictionaryControllerTest.class);

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
    public void insertFinanceDetail() throws Exception{
        logger.info("插入财务信息");
        //设置查询条件
        JSONObject financeDetailCondition = new JSONObject();
        financeDetailCondition.put("costMoney",10);
        financeDetailCondition.put("itemId",3);
        financeDetailCondition.put("note","炒面");
        logger.info("插入财务信息条件: " + financeDetailCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/finance/insertFinanceDetail")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(financeDetailCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateFinanceDetail() throws Exception{
        logger.info("修改财务信息");
        //设置查询条件
        JSONObject financeDetailCondition = new JSONObject();
        financeDetailCondition.put("id","fa17977a028a4489ad4e2dae354bff53");
        financeDetailCondition.put("costMoney",10.00);
        financeDetailCondition.put("itemId",3);
        financeDetailCondition.put("note","扁豆焖面");
        logger.info("修改财务信息条件: " + financeDetailCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/finance/updateFinanceDetail")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(financeDetailCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteFinanceDetail() throws Exception{
        logger.info("删除财务信息");
        //设置查询条件
        JSONObject financeDetailCondition = new JSONObject();
        financeDetailCondition.put("id","fa17977a028a4489ad4e2dae354bff53");
        financeDetailCondition.put("reversion","17");
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/finance/deleteFinanceDetail")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(financeDetailCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryByPage() throws Exception{
        logger.info("分页查询财务信息");
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("current",1);
        userCondition.put("peopleId",3);
        userCondition.put("offset",10);
        logger.info("分页查询财务信息 条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/finance/queryFinanceDetailByPage")
                .header("token", Constant.TEST_EXAMPLE_FLAG)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryByToken() throws Exception{
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("timeType",1);
        userCondition.put("queryType","3");
        userCondition.put("startDate",null);
        userCondition.put("endDate",null);
        logger.info("查询财务信息");
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/api/finance/getDetailDate")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODAyODg0NzU0NDksInBheWxvYWQiOiJcIjI1Yjk1NzkwOGExZDQ2MTI4MDdlMGE4ZjZmMGU4OGU1XCIifQ.bPMjHAGvrFW9RMeto69EpJYvu34LIMNRMKd3E9JAoVQ")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
