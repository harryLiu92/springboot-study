package com.home.web.dto;

/**
 * @Author: liuhao
 * @Date: 2018/9/12 17:19
 * @Description:
 **/
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BaseObject implements Serializable {
    private static final long serialVersionUID = 4539343756018673380L;

    public BaseObject() {
    }

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other, new String[0]);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[0]);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}