package com.sandbox.controller;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class MainControllerTest {

	@Test
	public void testHome() {
		MainController mainController = new MainController();
		
		Map<String, Object> home = mainController.home();
		
		assertEquals("ok", home.get("result"));
	}

	
	@Test
	public void testReisetider() {
		MainController mainController = new MainController();
		
		String json = mainController.reisetider();
		
		assertEquals("xml", json);
	}

}
