package com.home.web.constant;

/**
 * Administrator liuhao
 * 2018/9/11 1:46
 * 小程序URL
 **/
public interface URL {

    /**
     *
      */
    public static final String CUSTOMER_ROOT = "/customer";

    public static final String CUSTOMER_INDEX = CUSTOMER_ROOT + "/index";

    /**
     * 用户登录
     */
    public static final String LOGIN_ROOT = CUSTOMER_ROOT + "/login";

    public static final String LOGIN_WX_CODE = "/wxLoginCode";

    public static final String LOGIN_FINDUSER = "/findUser";


    /**
     * 超市
     */
    public static final String MERCHANT_ROOT = CUSTOMER_ROOT + "/merchant";

    public static final String MERCHANT_INFO = MERCHANT_ROOT + "/info";

    public static final String MERCHANT_QUERY = "/queryNearest";
}
