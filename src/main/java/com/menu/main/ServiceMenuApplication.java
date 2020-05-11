package com.menu.main;

import java.io.InputStream;
import java.util.Properties;

import com.menu.models.MenuContent;

import com.menu.parser.ExcelWriter;
import com.menu.parser.GsonDeserialization;

public class ServiceMenuApplication {

	public static void main(String[] args) {
		InputStream stream = ServiceMenuApplication.class.getClassLoader().getResourceAsStream("dev.properties");
		Properties prop = new Properties();
		try {
			prop.load(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MenuContent mc = GsonDeserialization.getMenuContent(prop.getProperty("input"));
		ExcelWriter ew = new ExcelWriter(mc, prop.getProperty("output"));
		ew.parse();
	}
}
