package com.home.web.logger;


import com.home.web.utils.IdentityIdHolder;

/**
 * @Author: liuhao
 * @Date: 2018/4/17 11:42
 * @Description:
 **/
public class InterLoggerFactory implements Logger {

    private org.slf4j.Logger logger;

    public InterLoggerFactory(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String msg) {
        logger.debug(IdentityIdHolder.getLog() + msg);
    }

    public void debug(String format, Object... arguments) {
        logger.debug(IdentityIdHolder.getLog() + format, arguments);
    }

    @Override
    public void catching(Throwable var1) {

    }

    @Override
    public <T extends Throwable> T throwing(T var1) {
        return null;
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(IdentityIdHolder.getLog() + msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        logger.info(IdentityIdHolder.getLog() + msg);
    }


    @Override
    public void info(String format, Object... arguments) {
        logger.info(IdentityIdHolder.getLog() + format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(IdentityIdHolder.getLog() + msg, t);
    }


    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        logger.warn(IdentityIdHolder.getLog() + msg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(IdentityIdHolder.getLog() + format, arguments);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(IdentityIdHolder.getLog() + msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        logger.error(IdentityIdHolder.getLog() + msg);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(IdentityIdHolder.getLog() + format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(IdentityIdHolder.getLog() + msg, t);
    }
}
