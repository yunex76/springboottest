package com.kyoborealco.kreps.config;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableJpaRepositories
(
	basePackages = "com.kyoborealco.kreps.repository", 
	entityManagerFactoryRef = "krepsEntityManagerFactory", 
	transactionManagerRef = "krepsTransactionManager"
)
public class DbConfig {

	@Inject
	DataSourceProperties dataSourceProperties;
	
	@Primary
	@Bean(name="krepsDataSource")
	DataSource hikariDataSource() throws SQLException {
		
		log.info("★★★★★ Configuration dataSource {}", dataSourceProperties.getUrl());
		
        HikariConfig hikariConfig = new HikariConfig();
        
        hikariConfig.setDriverClassName(this.dataSourceProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(this.dataSourceProperties.getUrl()); 
        hikariConfig.setUsername(this.dataSourceProperties.getUsername());
        hikariConfig.setPassword(this.dataSourceProperties.getPassword());

        hikariConfig.setMinimumIdle(5);  					// 최소 connection pool 개수
        hikariConfig.setMaximumPoolSize(10); 				// 최대 connection pool 개수
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setLeakDetectionThreshold(30000);		// enable detection of leaks
        hikariConfig.setPoolName("krepsCP");
		
		return new HikariDataSource(hikariConfig);
	}
	
	@Primary
	@Bean(name = "krepsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getFactoryBean(EntityManagerFactoryBuilder builder) throws SQLException
	{
		return builder.dataSource(hikariDataSource()).packages("com.kyoborealco.kreps.model.entity").build();
	}
 
	@Primary
	@Bean(name = "krepsTransactionManager")
	PlatformTransactionManager getTransactionManager(EntityManagerFactoryBuilder builder) throws SQLException
	{
		return new JpaTransactionManager(getFactoryBean(builder).getObject());
	}    
}
