package com.formacion.block5properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Start implements CommandLineRunner {

	@Value("${greeting}")
	private String greeting;

	@Value("${my.number}")
	private int mynumber;

	/*@Value("${new.property: new.property no tiene valor}")
	private String newProperty;*/

	@Value("#{systemEnvironment['new_property']}")
	private String newProperty;

	@Value("${MYURL2:NO_tengo_valor}")
	private String myUrl2;

	@Autowired
	private Environment environment;


	public static void main(String[] args) {
		SpringApplication.run(Start.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		System.out.println("El valor de greeting es: " + greeting);
		System.out.println("EL valor de my.number es: "+ mynumber);
		System.out.println("EL valor de newProperty es: "+ newProperty);
		String myUrl = environment.getProperty("MYURL");
		System.out.println("El valor de MYURL es: " + myUrl);
		System.out.println("El valor de MYURL2 es: " + myUrl2);

	}
}
