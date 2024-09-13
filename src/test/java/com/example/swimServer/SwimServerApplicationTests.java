package com.example.swimServer;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
class SwimServerApplicationTests {
	@MockBean
	AuthenticationManagerResolver<?> authenticationManagerResolver;

	@Container
	private static final MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:latest");

	@DynamicPropertySource
	static void setup(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mariaDBContainer::getUsername);
		registry.add("spring.datasource.password", mariaDBContainer::getPassword);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void test1() {
		assertEquals(1, 1);
	}

}
