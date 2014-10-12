package com.herokuapp.resttranslator.controller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	private static Log LOG = LogFactory.getLog(MainController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> home() {
		LOG.info("Handling a request");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", "ok");
		return response;
	}

	@RequestMapping("/reisetider/reisetider")
	@ResponseBody
	public String reisetider() {
		String url = "http://www.reisetider.no/xml/reisetider.xml";
		return getUrlAndConvertToJson(url);
	}

	
	@RequestMapping("/reisetider/strekninger")
	@ResponseBody
	public String strekninger() {
		String url = "http://www.reisetider.no/xml/strekninger.xml";
		return getUrlAndConvertToJson(url);
	}
	
	
	//http://www.yr.no/place/Norway/Oslo/Oslo/Lysaker/forecast.xml
	@RequestMapping("/yr/place/{country}/{county}/{muncipal}/{place}/forecast")
	@ResponseBody
	public String forecast(@PathVariable String country, @PathVariable String county, @PathVariable String muncipal, @PathVariable String place) {
		String url = "http://www.yr.no/place/" + country + "/" + county + "/" + muncipal + "/" + place + "/forecast.xml";
		return getUrlAndConvertToJson(url);		
	}
	
	private String getUrlAndConvertToJson(String url) {
		LOG.info("Handling xml to json CORS : " + url);
		try {
			InputStream in = new URL(
					url).openStream();
			String xml = IOUtils.toString(in);
			JSONObject xmlJSONObj = XML.toJSONObject(xml);
			return xmlJSONObj.toString(4);
		} catch (MalformedURLException e) {
			LOG.error("Trouble with url: " + url, e);
		} catch (IOException e) {
			LOG.error("Trouble with IO: " + url, e);
		}
		return "";
	}
}
