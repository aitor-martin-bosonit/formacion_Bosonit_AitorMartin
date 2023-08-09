package com.formacion.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication

public class ClaseInicial implements CommandLineRunner {


	@PostConstruct
	public void saludoUno() {
		System.out.println("Hola desde clase inicial");
	}

	@Bean
	public void saludoDos(){
		System.out.println("Hola desde clase secundaria");
	}

	/*@Bean
	public void saludoTres(){
		System.out.println("Soy la tercera clase");
	}*/

	@Bean
	public void saludoTres(){
		System.out.println("Soy la tercera clase");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduzca los valores a continuacion: ");
		String input = scanner.nextLine();

		String[] valores = input.split(" ");

		if (valores.length > 0)
		{
			System.out.print("Valor introducido: ");
			for (String valor : valores)
			{
				System.out.println(valor);
			}
		}
		else {
			System.out.println("El valor no puede ser NULL");
			}




	}

	public static void main(String[] args) {SpringApplication.run(ClaseInicial.class, args);


	}


	@Override
	public void run(String... args) throws Exception {

	}
}







