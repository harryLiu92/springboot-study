package com.home.web.dto;

import java.util.List;

/**
 * Administrator liuhao
 * 2018/9/25 23:44
 * 百度地图批量算路api
 * http://lbsyun.baidu.com/index.php?title=webapi/route-matrix-api-v2
 **/
public class BaiduApiBatchRoadResponse extends BaseObject {

    /**
     * 状态码
     */
    private int status;
    private String message;
    private List<BatchRoadResult> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BatchRoadResult> getResult() {
        return result;
    }

    public void setResult(List<BatchRoadResult> result) {
        this.result = result;
    }

    public static class BatchRoadResult extends BaseObject {
        /**
         * 路线距离
         */
        private ResultParam distance;
        private ResultParam duration;

        public ResultParam getDistance() {
            return distance;
        }

        public void setDistance(ResultParam distance) {
            this.distance = distance;
        }

        public ResultParam getDuration() {
            return duration;
        }

        public void setDuration(ResultParam duration) {
            this.duration = duration;
        }
    }

    public static class ResultParam extends BaseObject {
        private String text;
        private Double value;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }
    }
}
