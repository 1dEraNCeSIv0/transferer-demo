package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserService {
	private UserTransferer userTransferer;
	private UserRepository userRepository;
	private UuidProvider uuidProvider;
	private EventBus eventBus;

	void publishUser(String username) {
		UserEntity entity = userRepository.findByUsername(username).orElseThrow();

		User user = new User();
		user.setId(uuidProvider.randomUUID());
		userTransferer.transferToUser(entity, user);
		user.setPassword(encryptPassword(user.getPassword()));

		eventBus.fireUserCreatedEvent(user);
	}

	private String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

}
