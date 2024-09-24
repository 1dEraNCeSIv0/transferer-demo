package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidProvider {

	UUID randomUUID() {
		return UUID.randomUUID();
	}

}
