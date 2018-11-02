package edu.fzu.lbs.util;

import com.google.gson.Gson;
import edu.fzu.lbs.entity.dto.ResultDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 用于发送HTTP GET请求
 */
public class HttpPostUtil {
    private String url;
    private StringBuilder params = new StringBuilder();

    /**
     * @param urlStr 请求目的URL字符串
     */
    public HttpPostUtil(String urlStr) {
        this.url = urlStr;
    }

    /**
     * 增加请求参数
     *
     * @param key   参数名
     * @param value 参数值
     */
    public HttpPostUtil addParam(String key, String value) {
        if (params.length() > 0) {
            params.append('&');
        }
        params.append(key).append('=').append(value);
        return this;
    }

    /**
     * 发送HTTP　POST请求并返回Json字符串
     *
     * @return Json字符串结果
     */
    public String postJson() throws IOException {
        byte[] dataBytes = params.toString().getBytes(StandardCharsets.UTF_8);

        URL httpUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(dataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(dataBytes);

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

        String string;
        StringBuilder stringBuilder = new StringBuilder();
        while ((string = reader.readLine()) != null) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    /**
     * 发送HTTP　POST请求并返回对象
     *
     * @return Json字符串结果对应的Result对象
     */
    public ResultDTO post() throws IOException {
        String jsonRes = postJson();
        System.out.println(jsonRes);
        Gson gson = new Gson();
        return gson.fromJson(jsonRes, ResultDTO.class);
    }
}
