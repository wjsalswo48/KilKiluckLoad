package mz.sixsense.road.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import mz.sixsense.road.entity.Hotel;
import mz.sixsense.road.service.JSONService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class JSONServiceImpl implements JSONService {
    @Override
    public String JSONRead(String url, String serviceKey, String page, String parseKeyword) throws IOException {

        StringBuilder urlBuilder = new StringBuilder(url); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(page, "UTF-8")); /*검색건수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("resultType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON방식으로 호출 시 파라미 resultType=json 입력*/
        URL inserturl = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) inserturl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        String result = sb.toString();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);

        String realresult = element.getAsJsonObject().get(parseKeyword).getAsJsonObject().
                get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").toString().
                replace('[', ' ');
        return realresult;
    }
}
