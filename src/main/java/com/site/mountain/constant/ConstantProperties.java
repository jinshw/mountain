package com.site.mountain.constant;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.file")
@PropertySource("classpath:env/${spring.profiles.active}/constant.properties")
public class ConstantProperties {
    private String imgUploadPath;
    private String imgUrl;
    private String fileUploadPath;

    public String getImgUploadPath() {
        return imgUploadPath;
    }

    public void setImgUploadPath(String imgUploadPath) {
        this.imgUploadPath = imgUploadPath;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }
}
