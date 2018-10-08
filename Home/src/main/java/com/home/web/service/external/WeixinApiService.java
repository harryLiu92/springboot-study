package com.home.web.service.external;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.home.web.dto.LoginReponse;
import com.home.web.dto.LoginRequest;
import com.home.web.logger.BaseService;
import com.home.web.utils.HttpClientUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.Security;
import java.util.Map;

/**
 * Administrator liuhao
 * 2018/9/21 20:38
 * 微信外部api
 **/
@Service
public class WeixinApiService extends BaseService {

    public static boolean initialized = false;

    private static final String WEIXIN_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String APPID = "wx7d426233f53a47b1";
    private static final String SECRET = "40cda6ceb34d8537f329401714964b80";

    public LoginReponse analysis(LoginRequest req) {
        LoginReponse loginReponse = new LoginReponse();
        logger.info("用户登录-获取 req:{}", req);

        Map<String, String> param = Maps.newHashMap();
        param.put("appid", APPID);
        param.put("secret", SECRET);
        param.put("js_code", req.getCode());
        param.put("grant_type", "authorization_code");

        String sessionData = "";
        try {
            sessionData = HttpClientUtil.doGet(WEIXIN_LOGIN_URL, param);
        } catch (Throwable t) {
            logger.error("用户登录-调用微信登录失败,code:"+ req.getCode(), t);
            new RuntimeException("调用微信登录失败");
        }
        logger.info("用户登录-调用微信返回 sessionData = "+ sessionData);
        JSONObject jsonObj = JSONObject.parseObject(sessionData);
        String openId = jsonObj.getString("openid");
        String sessionKey = jsonObj.getString("session_key");
        BeanUtils.copyProperties(req, loginReponse);
        loginReponse.setSessionKey(sessionKey);
        loginReponse.setOpenid(openId);

//            String signature = HmacUtil.SHA1(req.getRawData()+sessionKey);
//            if(!StringUtils.equals(signature, req.getSignature())) {
//                logger.error(" 签名数据错误 signature:{}. req.getSignature():{}", signature, req.getSignature());
//                throw new RuntimeException("签名数据错误");
//            }
//            byte[] resultByte = null;
//            try {
//                resultByte = decrypt(Base64.decode(req.getEncryptedData()), Base64.decode(sessionKey), Base64.decode(req.getIv()));
//            } catch (Exception e) {
//                logger.error(" 解码数据异常", e);
//                throw new RuntimeException("解码数据错误");
//            }
//            if(null != resultByte && resultByte.length > 0) {
//                String userInfoStr = "";
//                try {
//                    userInfoStr = new String(resultByte, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    logger.error(e.getMessage());
//                }
            /* 解密数据格式 openid和unionid是敏感数据
            {
            "openId": "OPENID",
                    "nickName": "NICKNAME",
                    "gender": GENDER,
                    "city": "CITY",
                    "province": "PROVINCE",
                    "country": "COUNTRY",
                    "avatarUrl": "AVATARURL",
                    "unionId": "UNIONID",
                    "watermark":
            {
                "appid":"APPID",
                    "timestamp":TIMESTAMP
            }*/

//                logger.info("userInfo = "+ userInfoStr);
//            } else {
//                logger.error(" 用户数据错误");
//                throw new RuntimeException("用户数据错误");
//            }

        return loginReponse;
}

    private byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void initialize(){
        if (initialized) return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

    //生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }

}
