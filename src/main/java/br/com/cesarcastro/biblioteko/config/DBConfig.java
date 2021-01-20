package br.com.cesarcastro.biblioteko.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import br.com.cesarcastro.biblioteko.model.LivroModel;

@Configuration
public class DBConfig {

	/*
	 * public static HikariConfig datasourceConfig(DataSourceProperties
	 * dataSourceProperties) { HikariConfig config = new HikariConfig();
	 * config.setUsername(dataSourceProperties.getUsername());
	 * config.setJdbcUrl(dataSourceProperties.determineUrl());
	 * config.setPassword(dataSourceProperties.getPassword());
	 * config.setMaximumPoolSize(100); config.setMinimumIdle(5);
	 * config.setIdleTimeout(5 * 60 * 1000); config.setMaxLifetime(10 * 60 * 1000);
	 * return config; }
	 * 
	 * 
	 * @Bean
	 * 
	 * @Primary
	 * 
	 * @ConfigurationProperties("spring.datasource") public DataSourceProperties
	 * defaultDataSourceProperties() { return new DataSourceProperties(); }
	 * 
	 * @Bean(name = "dataSource")
	 * 
	 * @ConfigurationProperties("spring.datasource")
	 * 
	 * @Primary public DataSource defaultDataSource() { return new
	 * HikariDataSource(datasourceConfig(defaultDataSourceProperties())); }
	 * 
	 * @Primary
	 * 
	 * @Bean(name = "entityManagerFactory") public
	 * LocalContainerEntityManagerFactoryBean
	 * defaultEntityManager(EntityManagerFactoryBuilder builder,
	 * 
	 * @Qualifier("dataSource") DataSource dataSource) {
	 * 
	 * Map<String, Object> mapJpa = new HashMap<>(); // TODO verificar o
	 * interceptador // mapJpa.put("hibernate.ejb.interceptor", new
	 * HibernateInterceptor(service));
	 * 
	 * try { return builder.dataSource(dataSource)
	 * .persistenceUnit("cadastro").jta(false) .properties(mapJpa) .packages(
	 * LivroModel.class ) .build(); } catch (Exception e) { throw new
	 * RuntimeException(e); } }
	 * 
	 * 
	 * @Primary
	 * 
	 * @Bean(name = "transactionManager") public PlatformTransactionManager
	 * defaultTransactionManager(
	 * 
	 * @Qualifier("entityManagerFactory") EntityManagerFactory
	 * entityManagerFactoryBean) { JpaTransactionManager jpaTransactionManager = new
	 * JpaTransactionManager(entityManagerFactoryBean);
	 * jpaTransactionManager.setPersistenceUnitName("cadastro"); return
	 * jpaTransactionManager; }
	 */
}
