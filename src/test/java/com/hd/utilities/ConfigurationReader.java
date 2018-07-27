package com.hd.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
	private static Properties configFile;
//	Sinse Static block runs first and only once in java to be able to run first our Properties file we should definitely use a static block. 
	static {

		try {
			String path = "configuration.properties";
			FileInputStream input = new FileInputStream(path);

			configFile = new Properties();
			configFile.load(input);

			input.close();
			} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static String getProperty(String keyName) {
		return configFile.getProperty(keyName);
	}
}
