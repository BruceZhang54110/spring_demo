package com.test.designPattern.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {

	public Map<String, String> getProperties() {
		Properties properties = new Properties();
		InputStream in = getClass().getResourceAsStream("type.properties");
		Map<String, String> map = new HashMap<String, String>();
		try {
			properties.load(in);
			Enumeration en = properties.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String prop = properties.getProperty(key);
				map.put(key, prop);
			}
			return map;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
