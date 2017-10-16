package com.ledara.spring.demo.integrationtest;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ledara.demo.spring.dao.RegistrationDao;
import com.ledara.demo.spring.dao.RegistrationDaoImpl;
import com.ledara.demo.spring.service.RegistrationService;
import com.ledara.demo.spring.service.RegistrationServiceImpl;

@Configuration
public class TestConfig {

	@Bean
	public RegistrationService registrationService() {
		return new RegistrationServiceImpl();
	}

	@Bean
	public RegistrationDao registrationDao() {
		return new RegistrationDaoImpl();
	}

	@Bean
	public SessionFactory sessionFactory() {
		SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = Mockito.mock(DataSource.class);
		return dataSource;
	}

}
