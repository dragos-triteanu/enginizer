package com.startup.enginizer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource({"classpath:conf/datasource.properties","classpath:conf/application.properties"})
@EnableTransactionManagement
public class DatasourceConfig {

	@Autowired
	private Environment environment;

	@Bean(name="crowdfundingJdbcTemplate")
	public JdbcTemplate crowdfundingJdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

	@Bean(name="dataSource")
	public DataSource dataSource(){
		boolean inMemoryDb = Boolean.parseBoolean(environment.getProperty("app.datasource.inMemoryDb"));
		DataSource dataSource;
		if(inMemoryDb){
			dataSource = embeddedDataSource();
		}else{
			dataSource = datasourceCreator();
		}
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
		simpleDriverDataSource.setUrl(environment.getProperty("crowdfunding.datasource.url"));
		simpleDriverDataSource.setUsername(environment.getProperty("crowdfunding.datasource.username"));
		simpleDriverDataSource.setPassword(environment.getProperty("crowdfunding.datasource.password"));
		try{
			simpleDriverDataSource.setDriverClass((Class<? extends Driver>) Class.forName(environment.getProperty("crowdfunding.datasource.class")));
		}catch(Exception e){
			
		}
		return simpleDriverDataSource;
	}

	public DataSource embeddedDataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.H2) //.H2
				.build();
		return db;
	}

}
