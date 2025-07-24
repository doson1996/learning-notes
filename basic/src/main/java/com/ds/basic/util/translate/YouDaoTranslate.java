package com.ds.basic.util.translate;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * 网易有道智云翻译服务api调用demo
 * api接口: https://openapi.youdao.com/api
 *
 * @author ds
 */
public class YouDaoTranslate {
    // 您的应用ID
    private static final String APP_KEY = "";

    // 您的应用密钥
    private static final String APP_SECRET = "";

    public static void main(String[] args) {
        System.out.println(translate("你好"));
    }

    public static String translate(String txt) {
        try {
            // 添加请求参数
            Map<String, String[]> params = createRequestParams(txt);
            // 添加鉴权相关参数
            AuthV3Util.addAuthParams(APP_KEY, APP_SECRET, params);
            // 请求api服务
            byte[] resultStr = HttpUtil.doPost("https://openapi.youdao.com/api", null, params, "application/json");
            // 打印返回结果
            if (resultStr != null) {
                JSONObject resultObj = JSON.parseObject(resultStr, JSONObject.class);
                JSONArray translationArr = resultObj.getJSONArray("translation");
                return translationArr.getString(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Map<String, String[]> createRequestParams(String txt) {
        /*
         * note: 将下列变量替换为需要请求的参数
         * 取值参考文档: https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
         */

        String from = "zh-CHS";
        String to = "en";
        String vocabId = "您的用户词表ID";

        return new HashMap<String, String[]>() {{
            put("q", new String[]{txt});
            put("from", new String[]{from});
            put("to", new String[]{to});
            put("vocabId", new String[]{vocabId});
        }};
    }
}
