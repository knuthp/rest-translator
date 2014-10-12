package com.herokuapp.resttranslator.controller;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.herokuapp.resttranslator.controller.MainController;

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
		
		assertTrue(json.startsWith("{\"reisetider\": {"));
	}

}
