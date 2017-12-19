package com.liuhao.task.monitor;

import com.liuhao.common.utils.dto.ResponseResult;
import com.liuhao.spi.api.SPIRequest;
import com.liuhao.spi.core.SPILoader;
import com.liuhao.task.entity.TimeTaskConfig;
import com.liuhao.task.monitor.impl.RedisMonitor;
import com.liuhao.task.provider.TimeTaskProvider;
import com.liuhao.task.time.TimeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: liuhao
 * @Date: 2017/12/15 10:38
 * @Description:
 **/
public abstract class AbstractMonitor implements Monitor {

    private static final Logger logger = LoggerFactory.getLogger(RedisMonitor.class);

    private static ExecutorService exec = null;

    static {
        exec = Executors.newCachedThreadPool();
    }

    /**
     * @Author: liuhao
     * @Date: 2017/12/14 17:00
     * @param config
     * @Description:
     * submit返回执行是否成功
     */
    public void acquire(SPIRequest req, TimeTaskConfig config) {

        logger.info("-----------------acquire.start TimeTaskConfig = {}-----------------------------------", config);

        TimeTask<ResponseResult> task = TimeTaskProvider.parseTimeTask(config);
        // 先将数据加入到数据库
        publish(req, task);

//        Future<ResponseResult> future =
        exec.submit(task);
//
//        ResponseResult result = null;
//        try {
//            result = future.get(60, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        return ResponseResult.isSuccess(result);
        logger.info("-----------------acquire.end TimeTaskConfig = {}-----------------------------------", task);
    }


}
