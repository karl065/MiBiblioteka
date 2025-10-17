package com.mibiblioteka.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		 try {
            SpringApplication.run(ApiApplication.class, args);
        } catch (IllegalStateException e) {
            // Mostrar solo tu mensaje definido
            System.err.println("❌ ERROR: " + e.getMessage());
            System.exit(1); // termina la aplicación
        }
	}

}
