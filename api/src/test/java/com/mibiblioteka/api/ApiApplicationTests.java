package com.mibiblioteka.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() {
		  try {
            // Esto inicializa el contexto de Spring
        } catch (IllegalStateException e) {
            System.err.println("❌ ERROR durante tests: " + e.getMessage());
            // opcional: fail() si quieres marcar el test como fallido
        }
	}

}
