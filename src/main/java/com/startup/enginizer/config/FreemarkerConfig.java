package com.startup.enginizer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created by Dragos on 2/7/2016.
 */
@Configuration
public class FreemarkerConfig {

    @Bean
    public FreeMarkerConfig freeMarkerConfigurer(){
        FreeMarkerConfigurer freemarkerConfig = new FreeMarkerConfigurer();
        freemarkerConfig.setTemplateLoaderPath("/views/freemarker");
        return freemarkerConfig;
    }

    @Bean
    public FreeMarkerViewResolver viewResolver(){
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");
        viewResolver.setRequestContextAttribute("rc");
        viewResolver.setExposeSpringMacroHelpers(true);
        viewResolver.setExposeSessionAttributes(true);
        return viewResolver;
    }
}
