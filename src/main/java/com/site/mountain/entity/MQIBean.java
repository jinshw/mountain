package com.site.mountain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MQI对象",description = "养护指数对象")
public class MQIBean {

    private String id;
    private String scale;
    @ApiModelProperty(value = "道路编码",name = "roadname",example = "G575")
    private String roadcode;
    private String ldlx;
    @ApiModelProperty(value = "养护指数值",name = "mqi",example = "92")
    private String mqi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getRoadcode() {
        return roadcode;
    }

    public void setRoadcode(String roadcode) {
        this.roadcode = roadcode;
    }

    public String getLdlx() {
        return ldlx;
    }

    public void setLdlx(String ldlx) {
        this.ldlx = ldlx;
    }

    public String getMqi() {
        return mqi;
    }

    public void setMqi(String mqi) {
        this.mqi = mqi;
    }
}
