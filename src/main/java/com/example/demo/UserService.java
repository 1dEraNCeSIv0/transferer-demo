package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserService {
	private UserTransferer userTransferer;
	private UserMapper userMapper;
	private UserRepository userRepository;
	private UuidProvider uuidProvider;
	private EventBus eventBus;

	void publishUserViaTransferer(String username) {
		UserEntity entity = userRepository.findByUsername(username).orElseThrow();

		User user = new User();
		user.setId(uuidProvider.randomUUID());
		userTransferer.transferToUser(entity, user);
		user.setPassword(encryptPassword(user.getPassword()));

		eventBus.fireUserCreatedEvent(user);
	}

	// Equivalent-ish code to transferer
	void publishUserViaMapper(String username) {
		UserEntity entity = userRepository.findByUsername(username).orElseThrow();

		UUID id = uuidProvider.randomUUID();
		User user = userMapper.fromEntity(entity, id);
		user.setPassword(encryptPassword(user.getPassword()));

		eventBus.fireUserCreatedEvent(user);
	}

	void publishUserViaMyDreams(String username) {
		UserEntity entity = userRepository.findByUsername(username).orElseThrow();
		UUID id = uuidProvider.randomUUID();
		String encryptedPassword = encryptPassword(entity.getPassword());

		User user = userMapper.fromEntity(entity, id, encryptedPassword);

		eventBus.fireUserCreatedEvent(user);
	}

	private String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

}
