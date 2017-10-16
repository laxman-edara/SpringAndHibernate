package com.ledara.demo.spring.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ledara.demo.spring.config.ApplicationConfig;
import com.ledara.demo.spring.model.UserRegistration;
import com.ledara.demo.spring.service.RegistrationService;

/**
 * @author ledara
 *
 */
public class RunMainApp {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.register(ApplicationConfig.class);
		applicationContext.refresh();
		RegistrationService registerService = applicationContext.getBean(RegistrationService.class);

		createNewUserRegistration(registerService);
		updateUserRegistration(registerService);
		getRegisteredUser(registerService);
		getAllRegistredUsers(registerService);
		deleteRegistredUser(registerService);

		applicationContext.close();
	}

	private static void getAllRegistredUsers(RegistrationService registerService) {

		List<UserRegistration> registeredUsers = registerService.getAllRegistredUsers();
		registeredUsers.forEach(user -> {
			System.out.println(user.toString());
		});
	}

	private static void createNewUserRegistration(RegistrationService registerService) {
		UserRegistration userRegistration1 = getUserRegistration1();
		UserRegistration userRegistration2 = getUserRegistration2();
		registerService.createRegistration(userRegistration1);
		registerService.createRegistration(userRegistration2);
	}

	private static UserRegistration getUserRegistration2() {

		UserRegistration userRegistration2 = new UserRegistration();
		userRegistration2.setFirstName("Chandu");
		userRegistration2.setLastName("Eadara");
		userRegistration2.setEmail("echandu007@gmail.com");
		userRegistration2.setGender("M");
		userRegistration2.setPhone("(361) 205 4798");
		try {
			userRegistration2.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/19/1990"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return userRegistration2;
	}

	private static UserRegistration getUserRegistration1() {
		UserRegistration userRegistration1 = new UserRegistration();
		userRegistration1.setFirstName("Laxman");
		userRegistration1.setLastName("Edara");
		userRegistration1.setEmail("elaxman009@gmail.com");
		userRegistration1.setGender("M");
		userRegistration1.setPhone("(111) 111 1111");
		try {
			userRegistration1.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse("03/19/1990"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return userRegistration1;
	}

	private static void updateUserRegistration(RegistrationService registerService) {
		UserRegistration userRegistration = new UserRegistration();
		userRegistration.setEmail("echandu007@gmail.com");
		registerService.updateRegistration(userRegistration);
	}

	private static void getRegisteredUser(RegistrationService registerService) {
		registerService.getRegisteredUser("Laxman", "Edara", "elaxman.java@gmail.com").toString();
	}

	private static void deleteRegistredUser(RegistrationService registerService) {
		registerService.deleteRegistartion("Kumar", "Kiran", "kiran.kumar@gmail.com");
	}

}
