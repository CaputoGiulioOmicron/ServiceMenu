package com.menu.main;

import java.io.FileReader;
import java.util.Properties;

import com.menu.models.MenuContent;

import com.menu.parser.ExcelWriter;
import com.menu.parser.GsonDeserialization;

public class ServiceMenuApplication {

	public static void main(String[] args) {
		try {
			FileReader props=new FileReader("prop.properties");
			Properties prop=new Properties();
			prop.load(props);
			MenuContent mc = GsonDeserialization.getMenuContent(prop.getProperty("input"));
			ExcelWriter ew = new ExcelWriter(mc, prop.getProperty("output"));
			ew.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
