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
 * 字典控制器测试类
 * @author milo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PdaApplication.class)
@WebAppConfiguration
public class DictionaryControllerTest {
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
        logger.info("插入字典类型信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("typeName","餐饮");
        logger.info("插入字典信息条件: " + dictionaryTypeCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/dictionary/insertDictionaryType")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void secondCase() throws Exception{
        logger.info("修改字典类型信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("typeName","交通");
        dictionaryTypeCondition.put("id","df6513c16d9e4069936ae223591a1227");
        dictionaryTypeCondition.put("reversion",2);
        logger.info("修改字典信息条件: " + dictionaryTypeCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/dictionary/updateDictionaryType")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
