package com.startup.enginizer.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by dragos.triteanu on 10/31/15.
 */
@Configuration
@EnableTransactionManagement
@Import(DatasourceConfig.class)
@PropertySource({"classpath:conf/hibernate.properties","classpath:conf/application.properties"})
public class HibernateConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] { "com.startup.enginizer.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        boolean shouldInsertDummyData = Boolean.parseBoolean(environment.getRequiredProperty("hibernate.dummyData"));
        boolean inMemoryDb = Boolean.parseBoolean(environment.getRequiredProperty("app.datasource.inMemoryDb"));

        Properties properties = new Properties();
        if (inMemoryDb) {
            properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        } else {
            properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        }
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        if(shouldInsertDummyData){
            properties.put("hibernate.hbm2ddl.import_files",environment.getRequiredProperty("hibernate.hbm2ddl.import_files"));
        }
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setRollbackOnCommitFailure(true);
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
