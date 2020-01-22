package com.jiaxin.pda.controller;

import cn.hutool.core.io.FileUtil;
import com.baidu.aip.util.Base64Util;
import com.jiaxin.pda.constant.Constant;
import com.jiaxin.pda.util.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * demo
 * 测试文字识别
 * @author milo
 */
public class TextDistinguishController {
    /**
     * API_KEY
     */
    private static final String API_KEY = "4FBYZeB5RkgMeAdjX78xU2ei";
    /**
     * SECRET
     */
    private static final String SECRET_KEY = "f1w0oIrcTFEeLsoO7k57pZNF5DvsHa41";

    /**
     * 文字识别网址
     */
    private static final String TEXT_DISTINGUISH_KEY = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";

    /**
     * 鉴权网址
     */
    private static final String AUTH_HOST = "https://aip.baidubce.com/oauth/2.0/token?";
    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args) {
        String filePath = "//Users//mac//pictures//a.jpg";
        try {
            String param = "image=" + URLEncoder.encode(Base64Util.encode(FileUtil.readBytes(filePath)), "UTF-8");
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取,有效期为30天
            String accessToken = TextDistinguishController.getAuth(API_KEY,SECRET_KEY);
            String result = HttpUtil.post(TEXT_DISTINGUISH_KEY, accessToken, param);
            JSONObject jsonResult = new JSONObject(result);
            JSONArray wordsList = (JSONArray) jsonResult.get("words_result");
            // 返回数据处理
            if(null != wordsList && Constant.EMPTY_INTEGER_VALUE < wordsList.length()){
                for(int i = Constant.EMPTY_INTEGER_VALUE;i < wordsList.length();i++){
                    JSONObject words = (JSONObject) wordsList.get(i);
                    System.out.println(words.get("words"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取鉴权信息
     * @return
     */
    private static String getAuth(String ak, String sk) {
        String getAccessTokenUrl = AUTH_HOST + "grant_type=client_credentials" + "&client_id=" + ak + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer result = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            JSONObject jsonObject = new JSONObject(result.toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }
}
