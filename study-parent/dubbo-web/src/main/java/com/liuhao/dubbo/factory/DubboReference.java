package com.liuhao.dubbo.factory;

import java.io.Serializable;

/**
 *  @author  liuhao 
 *  @time    2017年7月7日
 *  @comment DubboReference参数对象
 */
public class DubboReference implements Serializable{

    private String registryProtocol;
    private String registryAddress;
    private String applicationName;

    public DubboReference() {};

    public DubboReference(String registryProtocol, String registryAddress, String applicationName) {
        super();
        this.registryProtocol = registryProtocol;
        this.registryAddress = registryAddress;
        this.applicationName = applicationName;
    }

    public String getRegistryProtocol() {
        return registryProtocol;
    }

    public void setRegistryProtocol(String registryProtocol) {
        this.registryProtocol = registryProtocol;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

}
