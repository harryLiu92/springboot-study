package com.home.web.service;

import com.alibaba.fastjson.JSONObject;
import com.home.web.builder.UserInfoBuilder;
import com.home.web.dao.UserCustomerDAO;
import com.home.web.dto.LoginReponse;
import com.home.web.dto.LoginRequest;
import com.home.web.logger.BaseService;
import com.home.web.model.UserCustomer;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @Author: liuhao
 * @Date: 2018/9/12 17:14
 * @Description:
 **/
@Service
public class LoginService extends BaseService {

}
