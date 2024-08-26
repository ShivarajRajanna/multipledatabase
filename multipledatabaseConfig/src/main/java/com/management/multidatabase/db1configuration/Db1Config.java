package com.management.multidatabase.db1configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    
    entityManagerFactoryRef = "entityManagerFactory",
    		transactionManagerRef="transactionmanager",
    		basePackages = "com.management.multidatabase.repository"
)
public class Db1Config {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.db1.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("com.management.multidatabase.db1entiry")
                .persistenceUnit("db1")
                .build();
    }

  
    @Primary
	@Bean(name="transactionmanager")
	public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory)
	{
		return new JpaTransactionManager(entityManagerFactory);
	}

}
