package com.example.Block13Testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Block13TestingApplicationTests {


	@Autowired
	private Block13TestingApplication application;

	@Test
	void contextLoads() {

		assert(application != null);

	}


}
