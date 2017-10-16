package com.ledara.demo.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ledara.demo.spring.model.UserRegistration;

/**
 * @author ledara
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.ledara.demo.spring")
@PropertySources(value = { @PropertySource(value = "environment.properties") })
public class ApplicationConfig {

	@Autowired
	Environment env;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("driverClassName"));
		dataSource.setUrl(env.getProperty("driverURL"));
		dataSource.setUsername(env.getProperty("dbUserName"));
		dataSource.setPassword(env.getProperty("dbPassword"));
		return dataSource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
		LocalSessionFactoryBuilder lsfb = new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("show_sql"));
		hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		lsfb.addProperties(hibernateProperties);
		lsfb.addAnnotatedClasses(UserRegistration.class);
		return lsfb.buildSessionFactory();
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager transacrionManager() {
		HibernateTransactionManager txnManager = new HibernateTransactionManager();
		txnManager.setSessionFactory(getSessionFactory());
		return txnManager;
	}

}
