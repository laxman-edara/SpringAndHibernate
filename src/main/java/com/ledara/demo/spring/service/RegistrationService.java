/**
 * 
 */
package com.ledara.demo.spring.service;

import java.util.List;

/**
 * @author ledara
 *
 */
public interface RegistrationService {

	public <T> void createRegistration(T t);

	public <T> void updateRegistration(T t);

	public boolean deleteRegistartion(String firstName, String lastName, String email);

	public <T> List<T> getAllRegistredUsers();

	public <T> T getRegisteredUser(String firstName, String lastName, String email);

}
