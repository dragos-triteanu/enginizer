package com.startup.enginizer.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Created by Dragos on 2/24/2016.
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.startup.enginizer.datarest.repository" })
@EntityScan(basePackages = "com.startup.enginizer.model.entities")
public class DataRestConf extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/api");
    }
}
