package com.liuhao.task.monitor;

import com.liuhao.spi.annotation.SPI;
import com.liuhao.spi.annotation.SPIAdaptive;
import com.liuhao.spi.api.SPIRequest;
import com.liuhao.task.entity.TimeTaskConfig;
import com.liuhao.task.time.TimeTask;

@SPI(name = "redis")
public interface Monitor {

    @SPIAdaptive
    void start(SPIRequest req);

    void acquire(SPIRequest req, TimeTaskConfig config);

    @SPIAdaptive
    void publish(SPIRequest req, TimeTask task);
}
