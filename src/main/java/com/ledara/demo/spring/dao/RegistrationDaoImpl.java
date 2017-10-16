package com.ledara.demo.spring.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ledara.demo.spring.model.UserRegistration;

/**
 * @author ledara
 *
 */
@Repository
public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public <T> void createRegistration(T t) {
		sessionFactory.getCurrentSession().save(t);

	}

	@Override
	@Transactional
	public <T> void updateRegistration(T t) {
		UserRegistration user = null;
		Session session = sessionFactory.getCurrentSession();
		if (t instanceof UserRegistration) {
			user = (UserRegistration) t;
		}
		Query query = session.getNamedQuery("getRegistrationByEmail");
		query.setString("email", user.getEmail());
		user = (UserRegistration) query.list().get(0);
		// update date of birth here
		try {
			user.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("10/08/1980"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		session.saveOrUpdate(user);
	}

	@Override
	@Transactional
	public boolean deleteRegistartion(String firstName, String lastName, String email) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("deleteRegisteredUser");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.setParameter("email", email);
		int value = query.executeUpdate();
		return (value == 1) ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public <T> List<T> getAllRegistredUsers() {
		Session session = sessionFactory.getCurrentSession();
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -30);
		Query query = session.getNamedQuery("getAllRegisteredUsers");
		query.setParameter("dateCreatedBack", calendar.getTime());
		List<UserRegistration> registeredUsers = query.list();
		return (List<T>) registeredUsers;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public <T> T getRegisteredUser(String firstName, String lastName, String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("getRegistredUser");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.setParameter("email", email);
		if (query.list() != null && query.list().size() == 0) {
			System.out.println("No records found with " + firstName + ", " + lastName + "and " + email);
			return null;
		}
		return (T) query.list().get(0);
	}

}
