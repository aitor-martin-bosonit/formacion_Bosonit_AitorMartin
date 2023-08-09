package com.formacion.block5logging;

import ch.qos.logback.classic.filter.ThresholdFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.*;

@SpringBootApplication
public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {SpringApplication.run(Main.class, args);

		configureLogging();

		logger.log(Level.SEVERE, "Este es un mensaje de error");
		logger.log(Level.WARNING, "Este es un mensaje de advertencia");
		logger.log(Level.INFO, "Este es un mensaje de información");
		logger.log(Level.CONFIG, "Este es un mensaje de configuración");
		logger.log(Level.FINE, "Este es un mensaje detallado");

	}
	private static void configureLogging() {
		try {
			// Crear el manejador para el archivo de registro
			Handler fileHandler = new FileHandler("error.log");
			fileHandler.setLevel(Level.ALL); // Establecer el nivel de registro del manejador en ALL

			// Crear el manejador para la consola
			Handler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.ALL); // Establecer el nivel de registro del manejador en ALL

			/* Crear el filtro ThresholdFilter
			Filter thresholdFilter = new ThresholdFilter(); // Establecer el nivel de filtrado

			// Asignar el filtro a los manejadores
			fileHandler.setFilter(thresholdFilter);
			consoleHandler.setFilter(thresholdFilter);*/

			// Agregar los manejadores al logger global
			Logger globalLogger = Logger.getLogger("");
			globalLogger.addHandler(fileHandler);
			globalLogger.addHandler(consoleHandler);

			// Deshabilitar el uso del manejador predeterminado para evitar la duplicación de registros
			globalLogger.setUseParentHandlers(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}






