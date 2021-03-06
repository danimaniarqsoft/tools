package com.danimaniarqsoft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase principal del Jar
 * 
 * @author Daniel Pichardo
 *
 */
public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.info("Plantilla para una aplicación java empaquetada en un jar");
		LOG.debug("Plantilla para una aplicación java empaquetada en un jar");
		LOG.warn("Plantilla para una aplicación java empaquetada en un jar");
		LOG.error("Plantilla para una aplicación java empaquetada en un jar");
	}
}
