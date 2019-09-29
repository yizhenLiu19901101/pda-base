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
 * 财务控制器测试类
 * @author milo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PdaApplication.class)
@WebAppConfiguration
public class FinanceDetailControllerTest {
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
    @Before
    public void setup() {
        //构造MockMvc
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    public void firstCase() throws Exception{
        logger.info("插入财务信息");
        //设置查询条件
        JSONObject financeDetailCondition = new JSONObject();
        financeDetailCondition.put("costMoney",10);
        financeDetailCondition.put("itemId",3);
        financeDetailCondition.put("note","炒面");
        logger.info("插入财务信息条件: " + financeDetailCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/finance/insertFinanceDetail")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(financeDetailCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void secondCase() throws Exception{
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
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(financeDetailCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void thirdCase() throws Exception{
        logger.info("分页查询财务信息");
        //设置查询条件
        JSONObject userCondition = new JSONObject();
        userCondition.put("current",1);
        userCondition.put("peopleId",3);
        userCondition.put("offset",10);
        logger.info("分页查询财务信息 条件: " + userCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/finance/queryFinanceDetailByPage")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(userCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
