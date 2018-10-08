package com.home.web.service;

import com.alibaba.fastjson.JSONObject;
import com.home.web.builder.UserCustomerBuilder;
import com.home.web.builder.UserInfoBuilder;
import com.home.web.dao.UserCustomerDAO;
import com.home.web.dto.LoginReponse;
import com.home.web.logger.BaseService;
import com.home.web.model.UserCustomer;
import com.home.web.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

/**
 * @Author: liuhao
 * @Date: 2018/9/13 15:13
 * @Description:
 **/
@Service
public class UserCustomerService extends BaseService {

    public static final String USER_CUSTOMER = "USER_CUSTOMER";

    @Autowired
    private UserCustomerDAO userCustomerDAO;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @CacheForHash(operation = CacheHashOperationType.GET, key = "user", hashKey = "#openid")
    public void login(LoginReponse loginReponse) {

        // 缓存获取数据
        String loginUserInfo = UserInfoBuilder.builder(loginReponse);
        String cacheUserCustomerStr = (String) stringRedisTemplate.opsForHash().get(USER_CUSTOMER, loginReponse.getOpenid());

        String cacheUserInfo = null;
        if (StringUtils.isNotBlank(cacheUserCustomerStr)) {
            JSONObject cacheUserCustomerExpire = JSONObject.parseObject(cacheUserCustomerStr);
            String expire = cacheUserCustomerExpire.getString("expire");

            if (checkNotExpire(expire)) {
                String cacheUserCustomer = cacheUserCustomerExpire.getString("userCustomer");
                cacheUserInfo  = UserInfoBuilder.builder(cacheUserCustomer);
            }
        }

        if (StringUtils.equals(cacheUserInfo, loginUserInfo)) {
            // 直接返回
        } else {
            boolean flag = false;
            // 数据库获取
            // 先查询
            UserCustomer userCustomer = userCustomerDAO.findById(loginReponse.getOpenid());
            if (userCustomer == null) {
                // insert
                userCustomer = UserCustomerBuilder.builder(loginReponse);
                userCustomerDAO.insert(userCustomer);
                flag = true;
            } else {
                String dbUserInfo = UserInfoBuilder.builder(userCustomer);
                if (!StringUtils.equals(dbUserInfo, loginUserInfo)) {
                    userCustomer = UserCustomerBuilder.builder(loginReponse);
                    // update
                    userCustomerDAO.updateByOpenId(userCustomer);
                    flag = true;
                }
            }
            if (flag || StringUtils.isBlank(cacheUserCustomerStr)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("expire", DateUtils.format("yyyy-MM-dd HH:mm:ss:sss", DateUtils.addMinutes(new Date(), 30)));
                jsonObject.put("userCustomer", userCustomer);
                stringRedisTemplate.opsForHash().put(USER_CUSTOMER, loginReponse.getOpenid(), jsonObject.toJSONString());
            }
        }
    }

    private boolean checkNotExpire(String expire){
        Date date = new Date();

        Date expireDate = null;
        try {
            expireDate = DateUtils.parseDate(expire, "yyyy-MM-dd HH:mm:ss:sss");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.before(expireDate);
    }

    public UserCustomer findByOpenid(String openid) {
        UserCustomer userCustomer = null;
        String cacheUserCustomerStr = (String) stringRedisTemplate.opsForHash().get(USER_CUSTOMER, openid);

        if (StringUtils.isNotBlank(cacheUserCustomerStr)) {
            JSONObject cacheUserCustomerExpire = JSONObject.parseObject(cacheUserCustomerStr);
            String expire = cacheUserCustomerExpire.getString("expire");

            if (checkNotExpire(expire)) {
                String cacheUserCustomer = cacheUserCustomerExpire.getString("userCustomer");
                userCustomer = JSONObject.parseObject(cacheUserCustomer, UserCustomer.class);
            }
        }

        if (userCustomer == null) {
            userCustomer = userCustomerDAO.findById(openid);
        }

        return userCustomer;
    }

    public Integer updateByOpenId(UserCustomer userCustomer) {
        return userCustomerDAO.updateByOpenId(userCustomer);
    }

    public Integer insert(UserCustomer userCustomer) {
        return userCustomerDAO.insert(userCustomer);
    }

}
