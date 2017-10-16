package com.ledara.spring.demo.integrationtest;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ledara.demo.spring.model.UserRegistration;
import com.ledara.demo.spring.service.RegistrationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SpringHibernateIntTest {

	@Autowired
	private RegistrationService service;

	@MockBean
	private SessionFactory sessionfactory;

	@MockBean
	private DataSource dataSource;

	@Before
	public void beforeTest() {
		List<UserRegistration> usersList = new ArrayList<UserRegistration>();
		usersList.add(new UserRegistration());
		Session session = Mockito.mock(Session.class);
		Query query = Mockito.mock(Query.class);
		BDDMockito.given(sessionfactory.getCurrentSession()).willReturn(session);
		BDDMockito.given(session.getNamedQuery(BDDMockito.anyString())).willReturn(query);
		BDDMockito.given(query.list()).willReturn(usersList);
		BDDMockito.given(query.executeUpdate()).willReturn(new Integer(1));

	}

	@Test
	public void testCreateRegistration() {
		service.createRegistration(new UserRegistration());
	}

	@Test
	public void testUpdateRegistration() {
		service.updateRegistration(new UserRegistration());
	}

	@Test
	public void testDeleteRegistration() {
		service.deleteRegistartion("test", "test", "test");
	}

	@Test
	public void testGetRegistredUser() {
		service.getRegisteredUser("test", "test", "test");
	}

}
