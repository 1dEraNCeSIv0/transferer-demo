package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
	User fromEntity(UserEntity entity, UUID id) {
		User user = new User();
		user.setId(id);
		user.setName(entity.getUsername());
		user.setPassword(entity.getPassword());
		user.setEmail(entity.getEmail());
		return user;
	}

	User fromEntity(UserEntity entity, UUID id, String encryptedPassword) {
		User user = new User();
		user.setId(id);
		user.setName(entity.getUsername());
		user.setPassword(encryptedPassword);
		user.setEmail(entity.getEmail());
		return user;
	}
}
