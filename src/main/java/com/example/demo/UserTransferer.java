package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class UserTransferer {
	void transferToUser(UserEntity entity, User user) {
		user.setName(entity.getUsername());
		user.setPassword(entity.getPassword());
		user.setEmail(entity.getEmail());
	}
}
