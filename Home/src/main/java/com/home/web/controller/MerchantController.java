package com.home.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.home.web.constant.URL;
import com.home.web.dto.BaiduApiBatchRoadResponse;
import com.home.web.dto.MerchantDTO;
import com.home.web.logger.BaseService;
import com.home.web.model.Merchant;
import com.home.web.model.UserCustomer;
import com.home.web.service.MerchantService;
import com.home.web.service.external.BaiduApiService;
import com.home.web.service.UserCustomerService;
import com.home.web.utils.CollectionUtil;
import com.home.web.utils.SpringUtil;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Administrator liuhao
 * 2018/9/20 15:15
 **/
@Controller
@RequestMapping(URL.MERCHANT_ROOT)
public class MerchantController extends BaseService {

    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private BaiduApiService baiduApiService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param latitude 纬度
     * @param longitude 经度
     * @return
     */
    @RequestMapping(URL.MERCHANT_QUERY)
    @ResponseBody
    public List<MerchantDTO> queryNearestByOrientation(@Param("latitude") Double latitude, @Param("longitude") Double longitude) {

        List<MerchantDTO> merchantDTOS = Lists.newArrayList();

        String openid  = (String) SpringUtil.getSession("openid");

        // 使用缓存,避免频繁请求api
        if (StringUtils.isNotBlank(openid)) {
//            UserCustomer userCustomer = userCustomerService.findByOpenid(openid);

            String merchantCache = stringRedisTemplate.opsForValue().get(openid+String.valueOf(latitude)+"|"+String.valueOf(longitude));

            List<MerchantDTO> merchants = JSONArray.parseArray(merchantCache, MerchantDTO.class);

            merchantDTOS.addAll(merchants);

            if (CollectionUtil.isEmpty(merchantDTOS)) {
                return merchantDTOS;
            }
        }

        List<Merchant> merchantList = merchantService.queryNearestByLongitudeAndLatitude(latitude, longitude);
        List<Pair<String, String>> origin = Lists.newArrayList();
        origin.add(new Pair(String.valueOf(latitude), String.valueOf(longitude)));

        List<Pair<String, String>> destination = Lists.newArrayList();
        for (Merchant merchant : merchantList) {
            destination.add(new Pair(String.valueOf(merchant.getLatitude()), String.valueOf(merchant.getLongitude())));
        }
        BaiduApiBatchRoadResponse response = baiduApiService.distance(origin, destination);

        merchantDTOS.addAll(merchantService.buildDTO(merchantList, response));

        if (StringUtils.isNotBlank(openid)) {
            stringRedisTemplate.opsForValue().set(openid + String.valueOf(latitude) + "|" + String.valueOf(longitude), JSONArray.toJSONString(merchantDTOS));
        }

        return merchantDTOS;
    }

}
