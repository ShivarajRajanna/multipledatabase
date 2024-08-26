package com.management.multidatabase.db2configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "bookentityManagerFactory",
transactionManagerRef="bookTransactionManager",
basePackages = {"com.management.multidatabase.db2repository"})
public class Db2Config {
	
	@Bean(name="bookdatasource")
	@ConfigurationProperties(prefix="spring.db2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name="bookentityManagerFactory")
	public LocalContainerEntityManagerFactoryBean bookentityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("bookdatasource") DataSource datasource)
	{
		HashMap<String, Object> properties=new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto","update");
		return builder.dataSource(datasource)
				       .properties(properties)
				       .packages("com.management.multidatabase.db2entity")
				       .persistenceUnit("book")
				       .build();
		
		
	}
	
	@Bean(name="bookTransactionManager")
	public PlatformTransactionManager booktransactionManager(@Qualifier("bookentityManagerFactory") EntityManagerFactory bookentityManagerFactory)
	{
		return new JpaTransactionManager(bookentityManagerFactory);
	}


}


