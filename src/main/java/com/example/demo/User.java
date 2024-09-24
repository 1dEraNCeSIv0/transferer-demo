package com.example.demo;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private UUID id;
	private String name;
	private String password;
	private String email;
}
