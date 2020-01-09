package com.site.mountain.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer  {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/dist/");
        registry.addResourceHandler("/picture/**").addResourceLocations("file:D:/workspace/idea/mountain/src/main/resources/picture/");
    }

}
