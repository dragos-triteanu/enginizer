package com.startup.enginizer.context;

import com.startup.enginizer.config.HibernateConfig;
import com.startup.enginizer.config.SecurityConfig;
import com.startup.enginizer.config.WebSocketConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages={"com.startup.enginizer.*", "com.startup.enginizer.repository"})
@Import(value={HibernateConfig.class, SecurityConfig.class, WebSocketConfig.class})
public class ServiceAppContext extends WebMvcConfigurerAdapter  {
	
    @Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
