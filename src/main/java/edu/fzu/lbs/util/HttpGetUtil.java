package edu.fzu.lbs.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fzu.lbs.entity.dto.ResultDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用于发送HTTP GET请求
 */
public class HttpGetUtil {
    private StringBuilder urlBuilder;
    private boolean hasParam = false;

    /**
     * @param urlStr 请求目的URL字符串
     */
    public HttpGetUtil(String urlStr) {
        this.urlBuilder = new StringBuilder(urlStr);
    }

    /**
     * 增加请求参数
     *
     * @param key   参数名
     * @param value 参数值
     */
    public HttpGetUtil addParam(String key, String value) {
        if (value == null || value.isEmpty()) {
            return this;
        }

        if (hasParam) {
            urlBuilder.append('&');
        } else {
            urlBuilder.append('?');
            hasParam = true;
        }
        urlBuilder.append(key).append('=').append(value);
        return this;
    }

    /**
     * 发送HTTP　GET请求并返回Json字符串
     *
     * @return Json字符串结果
     */
    public String getJson() throws IOException {
        System.out.println(urlBuilder.toString());
        URL httpUrl = new URL(urlBuilder.toString());
        HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(50000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String string;
        StringBuilder stringBuilder = new StringBuilder();

        while ((string = reader.readLine()) != null) {
            stringBuilder.append(string);
        }

        return stringBuilder.toString();
    }

    /**
     * 发送HTTP　GET请求并返回对象
     *
     * @return Json字符串结果对应的Result对象
     */
    public ResultDTO get() throws IOException {
        String jsonRes = getJson();
        System.out.println(jsonRes);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonRes, ResultDTO.class);
    }

}
