package com.home.web.model;

import com.home.web.dto.BaseObject;

/**
 * Administrator liuhao
 * 2018/9/25 22:06
 **/
public class GoodsCatalogueConfig extends BaseObject {
  private Integer id;
  private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
