package io.msa.context.services;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.msa.context.models.Context;
import io.msa.context.repositories.ContextRepository;

@Service
public class ContextService {
	@Autowired
	private ContextRepository contextRepository;

	public Context saveContext(Context context) {
		return contextRepository.save(context);
	}

	public Context findById(Long id) {
		return contextRepository.findById(id);
	}

	public Iterable<Context> findAllCtxs() {
		return contextRepository.findAll();
	}
	
	public void deleteById(Long id) {
		contextRepository.deleteById(id);
	}

	@SuppressWarnings("unchecked")
	public JSONObject getCtx(String systemIP) throws UnknownHostException, ParseException {
		String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
		if (!systemIP.matches(PATTERN))
			systemIP = "180.151.35.213";
		HashMap<String, String> mParams = new HashMap<String, String>();
		String userId = mParams.get("userId");
		String userName = mParams.get("userName");
		String deviceId = mParams.get("deviceId");
		String deviceInfo = mParams.get("deviceInfo");
		System.out.println("getLoc");
		JSONObject jsonSLoc = getLoc(systemIP);

		String sLat = jsonSLoc.get("latitude").toString();
		String sLon = jsonSLoc.get("longitude").toString();
		JSONObject jsonSWet = new JSONObject();
		System.out.println("getWeather");
		jsonSWet = getWeather(sLat, sLon);
		JSONObject jsonLocWet = new JSONObject();
		jsonLocWet.putIfAbsent("userId", userId);
		jsonLocWet.putIfAbsent("userName", userName);
		jsonLocWet.putIfAbsent("deviceId", deviceId);
		jsonLocWet.putIfAbsent("deviceInfo", deviceInfo);

		for (Object key : jsonSLoc.keySet()) {
			String keyStr = (String) key;
			Object keyvalue = jsonSLoc.get(keyStr);
			try {
				if (keyStr.equalsIgnoreCase("ip"))
					jsonLocWet.putIfAbsent("ip", keyvalue);
				if (keyStr.equalsIgnoreCase("latitude"))
					jsonLocWet.putIfAbsent("latitude", keyvalue);
				if (keyStr.equalsIgnoreCase("longitude"))
					jsonLocWet.putIfAbsent("longitude", keyvalue);
				if (keyStr.equalsIgnoreCase("time_zone"))
					jsonLocWet.putIfAbsent("timeZone", keyvalue);
				if (keyStr.equalsIgnoreCase("city"))
					jsonLocWet.putIfAbsent("city", keyvalue);
				if (keyStr.equalsIgnoreCase("region_name"))
					jsonLocWet.putIfAbsent("regionName", keyvalue);
				if (keyStr.equalsIgnoreCase("country_code"))
					jsonLocWet.putIfAbsent("countryCode", keyvalue);
				if (keyStr.equalsIgnoreCase("country_name"))
					jsonLocWet.putIfAbsent("countryName", keyvalue);
				if (keyStr.equalsIgnoreCase("zip_code"))
					jsonLocWet.putIfAbsent("zipCode", keyvalue);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error processing ---   " + keyStr + " ---   " + keyvalue);
			}

		}

		if ((jsonLocWet.containsKey("city") && jsonLocWet.get("city").equals(""))
				&& jsonLocWet.containsKey("regionName") && jsonLocWet.containsKey("countryCode")) {
			jsonLocWet.put("city", jsonLocWet.get("regionName") + ", " + jsonLocWet.get("countryCode"));
		}

		for (Object key : jsonSWet.keySet()) {
			String keyStr = (String) key;

			Object keyvalue = jsonSWet.get(keyStr);
			if (keyStr.equalsIgnoreCase("weather")) {
				jsonLocWet.putIfAbsent("weatherMain",
						((JSONObject) ((JSONArray) keyvalue).get(0)).get("main").toString());
				jsonLocWet.putIfAbsent("weatherDesc", ((JSONObject) ((JSONArray) keyvalue).get(0)).get("description"));
			}
			if (keyStr.equalsIgnoreCase("wind")) {
				jsonLocWet.putIfAbsent("windSpeed", ((JSONObject) keyvalue).get("speed"));
			}

			if (keyStr.equalsIgnoreCase("snow")) {
				jsonLocWet.putIfAbsent("snowQty", ((JSONObject) keyvalue).get("snow.3h"));
			}

			if (keyStr.equalsIgnoreCase("rain")) {
				jsonLocWet.putIfAbsent("rainQty", ((JSONObject) keyvalue).get("rain.3h"));
			}

			if (keyStr.equalsIgnoreCase("main")) {
				String kelvin = (((JSONObject) keyvalue).get("temp_max")).toString();
				int celsius = Integer.parseInt(kelvin.substring(0, kelvin.indexOf("."))) - 273;
				System.out.println("\n" + kelvin + "K = " + celsius + "C");
				int fahrenhiet = (int) Math.round((celsius * 9.0 / 5.0) + 32.0);
				jsonLocWet.putIfAbsent("mainTempMax", celsius + "°C/" + fahrenhiet + "°F");

				kelvin = (((JSONObject) keyvalue).get("temp_min")).toString();
				celsius = Integer.parseInt(kelvin.substring(0, kelvin.indexOf("."))) - 273;
				System.out.println("\n" + kelvin + "K = " + celsius + "C");
				fahrenhiet = (int) Math.round((celsius * 9.0 / 5.0) + 32.0);
				jsonLocWet.putIfAbsent("mainTempMin", celsius + "°C/" + fahrenhiet + "°F");

				kelvin = (((JSONObject) keyvalue).get("temp")).toString();
				celsius = Integer.parseInt(kelvin.substring(0, kelvin.indexOf("."))) - 273;
				System.out.println("\n" + kelvin + "K = " + celsius + "C");
				fahrenhiet = (int) Math.round((celsius * 9.0 / 5.0) + 32.0);
				jsonLocWet.putIfAbsent("mainTemp", celsius + "°C/" + fahrenhiet + "°F");
			}

			if (keyStr.equalsIgnoreCase("clouds")) {
				jsonLocWet.putIfAbsent("cloudsAll", ((JSONObject) keyvalue).get("all"));
			}
			if (keyStr.equalsIgnoreCase("id")) {
				jsonLocWet.putIfAbsent("cityid", keyvalue.toString());
			}
		}
		return jsonLocWet;
	}

	/*
	 * getWeather
	 * 
	 */
	public JSONObject getWeather(String sLat, String sLon) throws ParseException {

		HttpClient httpclient = new HttpClient();
		String jsonresponse = "";

		String requestUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + sLat + "&lon=" + sLon + "&APPID="
				+ "766009c5a71d577e392c3004f0258740";
		GetMethod get = new GetMethod(requestUrl);
		get.addRequestHeader("Content-Type", "application/json");

		try {
			httpclient.executeMethod(get);
			jsonresponse = get.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		JSONObject JSONObj = (JSONObject) parser.parse(jsonresponse);
		return JSONObj;
	}

	/*
	 * getLoc
	 * 
	 */
	public JSONObject getLoc(String ipaddress) throws ParseException {
		HttpClient httpclient = new HttpClient();
		String jsonresponse = "";

		// String requestUrl = "https://ipstack.com/json/" + ipaddress;
		String requestUrl = "http://api.ipstack.com/49.207.61.53?access_key=1a41dd77c569af4fe0940a8a609bf36d";
		GetMethod get = new GetMethod(requestUrl);
		get.addRequestHeader("Content-Type", "application/json");

		try {
			httpclient.executeMethod(get);
			jsonresponse = get.getResponseBodyAsString();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONParser parser = new JSONParser();
		JSONObject JSONObj = (JSONObject) parser.parse(jsonresponse);

		return JSONObj;

	}

	public Object toBean(JSONObject jobject, Object object) throws IllegalArgumentException {
		for (Field field : object.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				System.out.println(jobject.toString() + "--------------" + "/n" + field.getName());
				field.set(object, (jobject.get(field.getName())).toString());
			} catch (IllegalAccessException | NullPointerException e) {

				e.printStackTrace();
			}
		}
		return object;
	}

}
