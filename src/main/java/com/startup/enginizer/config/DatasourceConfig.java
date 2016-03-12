package com.startup.enginizer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@PropertySource("classpath:conf/datasource.properties")
@EnableTransactionManagement
public class DatasourceConfig {
	
	@Value("${crowdfunding.datasource.url}")
	private String dbUrl;
	
	@Value("${crowdfunding.datasource.username}")
	private String dbUsername;
	
	@Value("${crowdfunding.datasource.password}")
	private String dbPassword;
	
	@Value("${crowdfunding.datasource.class}")
	private String dbClassName;

	@Bean(name="crowdfundingJdbcTemplate")
	public JdbcTemplate crowdfundingJdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

	@Bean(name="dataSource")
	public DataSource dataSource(){
		DataSource dataSource = datasourceCreator();
		return dataSource;
	}
	
	private DatabasePopulator datasourcePopulator(){
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(false);
		return databasePopulator;
	}

	@SuppressWarnings("unchecked")
	private SimpleDriverDataSource datasourceCreator(){
		SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
		simpleDriverDataSource.setUrl(dbUrl);
		simpleDriverDataSource.setUsername(dbUsername);
		simpleDriverDataSource.setPassword(dbPassword);
		try{
			simpleDriverDataSource.setDriverClass((Class<? extends Driver>) Class.forName(dbClassName));
		}catch(Exception e){
			
		}
		return simpleDriverDataSource;
	}

	public DataSource embeddedDataSource() {
		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.HSQL) //.H2
				.build();
		return db;
	}

}
