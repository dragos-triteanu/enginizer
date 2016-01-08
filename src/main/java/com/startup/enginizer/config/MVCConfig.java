package com.startup.enginizer.config;

import freemarker.ext.beans.BeansWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:conf/application.properties")
public class MVCConfig extends WebMvcConfigurerAdapter{


    @Value("${cv.upload.maxFileSize}")
    private int maxFileUploadSize;
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Bean
	public BeansWrapper beansWrapper(){
		BeansWrapper beansWrapper = new BeansWrapper();
		beansWrapper.setExposeFields(true);
		return beansWrapper;
	}

	@Bean
	public FreeMarkerConfigurer freemarkerConfig(){
		FreeMarkerConfigurer freemarkerConfig = new FreeMarkerConfigurer();
		Map<String,Object> properties = new HashMap<>();
		properties.put("objectWrapper", beansWrapper());
		freemarkerConfig.setFreemarkerVariables(properties);
		freemarkerConfig.setTemplateLoaderPath("/WEB-INF/views/freemarker");
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

    @Bean(name="multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(maxFileUploadSize);
        return multipartResolver;
    }
}
