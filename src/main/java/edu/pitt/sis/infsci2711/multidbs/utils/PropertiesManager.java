package edu.pitt.sis.infsci2711.multidbs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class implements Singleton pattern and is used to manage properties.
 */
public class PropertiesManager {
	private final static PropertiesManager INSTANCE = new PropertiesManager();
	
	Properties properties;
	
	private PropertiesManager() {
		properties = new Properties();
	}
	
	public static PropertiesManager getInstance() {
		return INSTANCE;
	}
	
	public void loadProperties(final File propertiesFile) throws FileNotFoundException, IOException {
		try (FileInputStream is = new FileInputStream(propertiesFile)) {
			properties.load(is);
		}
	}
	
	public String getStringProperty(final String propertyName) {
		return properties.getProperty(propertyName);
	}
	
	public String getStringProperty(final String propertyName, final String defaultValue) {
		return properties.getProperty(propertyName, defaultValue);
	}
	
	public int getIntProperty(final String propertyName) {
		return Integer.parseInt(properties.getProperty(propertyName));
	}
	
	public int getIntProperty(final String propertyName, final int defaultValue) {
		return Integer.parseInt(properties.getProperty(propertyName, String.valueOf(defaultValue)));
	}
}
