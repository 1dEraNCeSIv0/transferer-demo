package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {
	@Id
	private UUID id;
	private String username;
	private String password;
	private String email;
}
