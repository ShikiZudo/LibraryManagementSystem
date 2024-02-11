package com.hexaware.lms;

import com.hexaware.lms.entity.Authentication;
import com.hexaware.lms.repository.AuthenticationRepository;
import com.hexaware.lms.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class LmsApplication {

	@Autowired
	private UserRepository userRepository;

	public final static Logger LOGGER=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Autowired
	private AuthenticationRepository authenticationRepository;

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

	@GetMapping(path = "/deleteUser")
	@Transactional
	public void deleteUser(){

//		LOGGER.info(""+authenticationRepository.findById(1L).get().getPassword());
//		LOGGER.info(""+userRepository.findById(1L).get().getFirstName());

		userRepository.deleteById(1L);

		LOGGER.info(""+authenticationRepository.findById(1L).isPresent());
		LOGGER.info(""+userRepository.findById(1L).isPresent());
	}

	@GetMapping(path = "/deleteAuth")
	@Transactional
	public void deleteAuth(){

		LOGGER.info(""+authenticationRepository.findById(1L).get().getPassword());
		LOGGER.info(""+userRepository.findById(1L).get().getFirstName());

		authenticationRepository.deleteById(1L);

//		Optional<Authentication> authenticationOptional = authenticationRepository.findById(1L);
//
//		authenticationOptional.ifPresent(authentication -> {
//			authentication.getUser().setAuthentication(null);
//			authenticationRepository.deleteById(authentication.getId());
//		});

		LOGGER.info(""+authenticationRepository.findById(1L).isPresent());
		LOGGER.info(""+userRepository.findById(1L).isPresent());
	}

}
