package com.menu.main;

import com.menu.models.MenuContent;

import com.menu.parser.ExcelWriter;
import com.menu.parser.GsonDeserialization;

public class ServiceMenuApplication {

	public static void main(String[] args) {
		MenuContent mc = new GsonDeserialization().getMc();
		ExcelWriter ew = new ExcelWriter(mc);
	}
}
