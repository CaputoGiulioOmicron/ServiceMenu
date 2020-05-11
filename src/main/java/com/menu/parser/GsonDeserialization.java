package com.menu.parser;

import java.io.FileReader;

import com.google.gson.Gson;
import com.menu.models.MenuContent;

public class GsonDeserialization {
	private MenuContent mc;

	public GsonDeserialization() {
		super();
		Gson gson = new Gson();
		try {
			mc = gson.fromJson(new FileReader("input/ServiceMenu.json"), MenuContent.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public MenuContent getMc() {
		return mc;
	}
	
}
