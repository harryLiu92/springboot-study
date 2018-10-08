package com.home.web.service.external;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.home.web.dto.BaiduApiBatchRoadResponse;
import com.home.web.logger.BaseService;
import com.home.web.utils.CollectionUtil;
import com.home.web.utils.HttpClientUtil;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Administrator liuhao
 * 2018/9/21 20:37
 * 这里需要后台的api
 * 百度api
 **/
@Service
public class BaiduApiService extends BaseService {

    /**
     * 步行
     */
    private static final String BAIDU_WALK_CALC_URL = "https://api.map.baidu.com/routematrix/v2/walking";
    /**
     * 骑行
     */
    private static final String BAIDU_RIDE_CALC_URL = "https://api.map.baidu.com/routematrix/v2/riding";
    /**
     * 驾车
     */
    private static final String BAIDU_CAR_CALC_URL = "https://api.map.baidu.com/routematrix/v2/driving";

    private static final String BAIDU_AK = "cRNYrrkVx59ukuZIuCbx7CI8";
    // http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=40.45,116.34|40.54,116.35&destinations=40.34,116.45|40.35,116.46&ak=您的AK

    //origin:出发地地名，如:余杭区
    //destination:目的地地名，如:黄浦区
    //返回两地的行车距离，如:234.56公里

    /**
     * K纬度,V经度|K纬度,V经度
     * @param origin
     * @param destination
     * @return
     */
    public BaiduApiBatchRoadResponse distance(List<Pair<String, String>> origin, List<Pair<String,String>> destination) {
        Map<String, String> param = Maps.newHashMap();
        param.put("output", "json");
        param.put("ak", BAIDU_AK);
        param.put("origins", parseAddress(origin));
        param.put("destinations", parseAddress(destination));

        String distance = "";
        try {
            distance = HttpClientUtil.doGet(BAIDU_RIDE_CALC_URL, param);
        } catch (Throwable throwable) {
            logger.error("调用百度api计算距离失败origin:"+ origin +",destination:" + destination, throwable);
            new RuntimeException("调用百度api计算距离失败");
        }
        BaiduApiBatchRoadResponse response = JSONObject.parseObject(distance, BaiduApiBatchRoadResponse.class);
        if (response == null || response.getStatus() != 0 || CollectionUtil.isEmpty(response.getResult())) {
            logger.error("调用百度api计算距离返回结果错误--origin:{},destination:{},response:{}", origin, destination, response);
            new RuntimeException("调用百度api计算距离返回结果错误");
        }
        return response;
    }

    private String parseAddress(List<Pair<String,String>> addressMap) {
        StringBuilder address = new StringBuilder();
        for (Pair<String,String> entry : addressMap) {
            address.append(entry.getKey()).append(",").append(entry.getValue()).append("|");
        }

        String result = "";
        if (StringUtils.isNotBlank(address)) {
            result = address.substring(0, address.length() - 1);
        }

        return result;
    }
}
