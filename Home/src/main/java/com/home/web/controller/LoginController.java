package com.home.web.controller;

import com.home.web.constant.URL;
import com.home.web.dto.LoginReponse;
import com.home.web.dto.LoginRequest;
import com.home.web.logger.BaseService;
import com.home.web.service.UserCustomerService;
import com.home.web.service.external.WeixinApiService;
import com.home.web.utils.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * Administrator liuhao
 * 2018/9/11 1:51
 * 此登录需要包括,用户，商户，骑手
 **/
@Controller
@RequestMapping(URL.LOGIN_ROOT)
public class LoginController extends BaseService {

    @Autowired
    private WeixinApiService weixinApiService;
    @Autowired
    private UserCustomerService userCustomerService;

    @RequestMapping(URL.LOGIN_WX_CODE)
    @ResponseBody
    public String wxLoginCode(@Param("code") String code) {
        logger.info("获取用户code,code:{}", code);
        String result = "";
        if (StringUtils.isBlank(code)) {
            return result;
        }

        try {
            java.net.URL url = new java.net.URL("https://api.weixin.qq.com/sns/jscode2session?appid=wx7d426233f53a47b1&secret=40cda6ceb34d8537f329401714964b80&js_code="+code+"&grant_type=authorization_code");
            InputStream input = url.openStream();
            byte[] b = new byte[1024];
            while (input.read(b) != -1) {
                String s = new String(b);
                result += s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("获取用户code,result:{}", result);
        return result;
    }

    /**
     * 解密用户敏感数据获取用户信息
     *
     * @return
     * */
    @RequestMapping(URL.LOGIN_FINDUSER)
    @ResponseBody
    public LoginReponse findUser(HttpServletRequest request, LoginRequest loginRequest) {
        // 解析微信登录信息
        LoginReponse loginReponse = weixinApiService.analysis(loginRequest);

        SpringUtil.setSession("openid", loginReponse.getOpenid());

        userCustomerService.login(loginReponse);

        return loginReponse;
    }

}