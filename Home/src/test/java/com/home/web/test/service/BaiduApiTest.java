package com.home.web.test.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.home.web.dto.BaiduApiBatchRoadResponse;
import com.home.web.service.external.BaiduApiService;
import com.home.web.test.BaseSpringBootTest;
import javafx.util.Pair;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

/**
 * Administrator liuhao
 * 2018/9/20 20:04
 **/
public class BaiduApiTest extends BaseSpringBootTest {

    @Autowired
    private BaiduApiService baiduApiService;

    @Test
    public void road() throws MalformedURLException {

//        String url = "{\"status\":0,\"result\":[{\"distance\":{\"text\":\"18.8公里\",\"value\":18770},\"duration\":{\"text\":\"1.6小时\",\"value\":5685}},{\"distance\":{\"text\":\"20.7公里\",\"value\":20721},\"duration\":{\"text\":\"1.7小时\",\"value\":6276}}],\"message\":\"成功\"}";
//        BaiduApiBatchRoadResponse response = JSONObject.parseObject(url, BaiduApiBatchRoadResponse.class);

        List<Pair<String, String>> origins = Lists.newArrayList();
        List<Pair<String, String>> destinations = Lists.newArrayList();
//        origins.add(new Pair("113.93045", "22.53339"));
//        // 40.34,116.45|40.35,116.46
//        destinations.add(new Pair("113.93041", "22.53332"));
        origins.add(new Pair("40.45", "116.34"));
        // 40.34,116.45|40.35,116.46
        destinations.add(new Pair("22.53", "113.93"));
//        origins.add(new Pair("40.45", "116.34"));
//        // 40.34,116.45|40.35,116.46
//        destinations.add(new Pair("40.46", "116.34"));
//        destinations.add(new Pair("40.46", "116.35"));
        System.out.println(baiduApiService.distance(origins, destinations));
    }
}
