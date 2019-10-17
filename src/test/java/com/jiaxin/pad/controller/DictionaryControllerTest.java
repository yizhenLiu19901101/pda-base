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
    public void insertDictionaryType() throws Exception{
        logger.info("插入字典类型信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("typeName","交通");
        logger.info("插入字典信息条件: " + dictionaryTypeCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/dictionary/insertDictionaryType")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateDictionaryType() throws Exception{
        logger.info("修改字典类型信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("typeName","交通");
        dictionaryTypeCondition.put("id","27ffdd89da5f447bb3f6c89ba53c912a");
        dictionaryTypeCondition.put("reversion",2);
        logger.info("修改字典信息条件: " + dictionaryTypeCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/dictionary/updateDictionaryType")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteDictionaryType() throws Exception{
        logger.info("删除字典类型信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("id","404e5b9ed6154ff78d0a0d531af55950");
        dictionaryTypeCondition.put("reversion",1);
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/dictionary/deleteDictionaryType")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryDictionaryTypeByPage() throws Exception{
        logger.info("分页查找字典类型信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("current","1");
        dictionaryTypeCondition.put("offset","10");
        logger.info("分页查找字典信息条件: " + dictionaryTypeCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/dictionary/queryDictionaryTypeByPage")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void insertDictionaryItem() throws Exception{
        logger.info("插入字典信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("itemName","饮料");
        dictionaryTypeCondition.put("orderNo",3);
        dictionaryTypeCondition.put("dictionaryTypeId",3);
        logger.info("插入字典信息条件: " + dictionaryTypeCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/dictionary/insertDictionary")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateDictionaryItem() throws Exception{
        logger.info("修改字典信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("itemName","饮料");
        dictionaryTypeCondition.put("orderNo",1);
        dictionaryTypeCondition.put("id","048d9d027d2e4ec9bb1c5e0493705260");
        dictionaryTypeCondition.put("reversion",15);
        logger.info("修改字典信息条件: " + dictionaryTypeCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.put("/dictionary/updateDictionary")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteDictionaryItem() throws Exception{
        logger.info("删除字典信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("id","048d9d027d2e4ec9bb1c5e0493705260");
        dictionaryTypeCondition.put("reversion",15);
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.delete("/dictionary/deleteDictionaryItem")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryDictionaryByPage() throws Exception{
        logger.info("分页查找字典信息");
        //设置查询条件
        JSONObject dictionaryTypeCondition = new JSONObject();
        dictionaryTypeCondition.put("current","1");
        dictionaryTypeCondition.put("offset","10");
        logger.info("分页查找字典信息条件: " + dictionaryTypeCondition.toJSONString());
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.post("/dictionary/queryDictionaryByPage")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").content(dictionaryTypeCondition.toString().getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void queryDictionaryByTypeId() throws Exception{
        logger.info("根据typeId查找字典信息");
        //执行测试
        mockMvc.perform(MockMvcRequestBuilders.get("/dictionary/queryDictionaryByTypeId/2")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
