package com.home.web.test.service;

import com.google.common.collect.Maps;
import com.home.web.dto.BaiduApiBatchRoadResponse;
import com.home.web.model.Merchant;
import com.home.web.service.MerchantService;
import com.home.web.service.external.BaiduApiService;
import com.home.web.test.BaseSpringBootTest;
import javafx.util.Pair;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Administrator liuhao
 * 2018/9/26 0:43
 **/
public class MerchatServiceTest extends BaseSpringBootTest {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private BaiduApiService baiduApiService;

    @Test
    public void tt() {
        Double longitude = 40.45d;
        Double latitude = 116.34d;
        // 使用缓存,避免频繁请求api
        List<Merchant> merchantList = merchantService.queryNearestByLongitudeAndLatitude(longitude, latitude);
        List<Pair<String, String>> origin = Lists.newArrayList();
        origin.add(new Pair(String.valueOf(longitude), String.valueOf(latitude)));

        List<Pair<String, String>> destination = Lists.newArrayList();
        for (Merchant merchant : merchantList) {
            destination.add(new Pair(String.valueOf(merchant.getLongitude()), String.valueOf(merchant.getLatitude())));
        }
        BaiduApiBatchRoadResponse response = baiduApiService.distance(origin, destination);

        System.out.println(merchantService.buildDTO(merchantList, response));
    }
}
