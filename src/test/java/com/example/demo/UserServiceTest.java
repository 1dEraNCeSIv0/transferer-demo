package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

class UserServiceTest {

	private static final UUID ID = randomUUID();
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String PASSWORD_ENCRYPTED = "drowssap";
	private static final String EMAIL = "example@email.com";

	private UserTransferer userTransferer = mock(UserTransferer.class);
	private UserRepository userRepository = mock(UserRepository.class);
	private UuidProvider uuidProvider = mock(UuidProvider.class);
	private EventBus eventBus = mock(EventBus.class);

	private UserService classUnderTest = new UserService(userTransferer, userRepository, uuidProvider, eventBus);

	@Test
	void transfererIsComplicated() {
		UserEntity entity = defaultUserEntity();

		doReturn(Optional.of(entity)).when(userRepository).findByUsername(USERNAME);
		doReturn(ID).when(uuidProvider).randomUUID();
		doAnswer(invocation -> {
			User user = invocation.getArgument(1, User.class);
			user.setName(USERNAME);
			user.setPassword(PASSWORD);
			user.setEmail(EMAIL);
			return user;
		}).when(userTransferer).transferToUser(entity, userWithIdOnly(ID));
		doNothing().when(eventBus).fireUserCreatedEvent(defaultUser());

		classUnderTest.publishUser(USERNAME);

		verify(eventBus).fireUserCreatedEvent(defaultUser());
	}

	private User defaultUser() {
		return new User(ID, USERNAME, PASSWORD_ENCRYPTED, EMAIL);
	}

	private User userWithIdOnly(UUID id) {
		User user = new User();
		user.setId(id);
		return user;
	}

	private UserEntity defaultUserEntity() {
		return new UserEntity(ID, USERNAME, PASSWORD, EMAIL);
	}
}