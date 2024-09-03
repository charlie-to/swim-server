package com.example.swimServer;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
@TestPropertySource(locations = "classpath:application-test.properties")
class SwimServerApplicationTests {

	@Rule
	public MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>();



	@Test
	void contextLoads() {
	}

	@Test
	void test1() {
		assertEquals(1, 1);
	}

}
