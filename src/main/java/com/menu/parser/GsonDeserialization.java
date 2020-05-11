package com.menu.parser;

import java.io.FileReader;

import com.google.gson.Gson;
import com.menu.models.MenuContent;

public class GsonDeserialization {

	public static MenuContent getMenuContent(String path) {
		Gson gson = new Gson();
		try {
			return gson.fromJson(new FileReader(path), MenuContent.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}

}
