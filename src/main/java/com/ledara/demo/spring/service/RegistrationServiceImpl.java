/**
 * 
 */
package com.ledara.demo.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledara.demo.spring.dao.RegistrationDao;
import com.ledara.demo.spring.model.UserRegistration;

/**
 * @author ledara
 *
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationDao registerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ledara.demo.spring.service.RegistrationService#createRegistration(
	 * java.lang.Object)
	 */
	@Override
	public <T> void createRegistration(T t) {
		registerDao.createRegistration(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ledara.demo.spring.service.RegistrationService#updateRegistration(
	 * java.lang.Object)
	 */
	@Override
	public <T> void updateRegistration(T t) {
		registerDao.updateRegistration(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ledara.demo.spring.service.RegistrationService#deleteRegistartion(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean deleteRegistartion(String firstName, String lastName, String email) {
		
		return registerDao.deleteRegistartion(firstName, lastName, email);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ledara.demo.spring.service.RegistrationService#getAllRegistredUsers()
	 */
	@Override
	public <T> List<T> getAllRegistredUsers() {

		return registerDao.getAllRegistredUsers();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ledara.demo.spring.service.RegistrationService#getRegisteredUser(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getRegisteredUser(String firstName, String lastName, String email) {
		UserRegistration registredUser = registerDao.getRegisteredUser(firstName, lastName, email);
		return (T) registredUser;
	}

}
