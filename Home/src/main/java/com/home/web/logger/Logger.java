package com.home.web.logger;

/**
 * @Author: liuhao
 * @Date: 2018/9/12 11:19
 * @Description:
 **/

public interface Logger {
    void error(String var1);

    void warn(String var1);

    void info(String var1);

    void debug(String var1);

    void error(String var1, Throwable var2);

    void warn(String var1, Throwable var2);

    void info(String var1, Throwable var2);

    void debug(String var1, Throwable var2);

    void error(String var1, Object... var2);

    void warn(String var1, Object... var2);

    void info(String var1, Object... var2);

    void debug(String var1, Object... var2);

    void catching(Throwable var1);

    <T extends Throwable> T throwing(T var1);

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    boolean isErrorEnabled();
}
