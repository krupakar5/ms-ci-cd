package com.account.web.resttemplate;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.msa.authserver.model.User;



public class RestTemplateTest {

	public static final String POST_SAVE_URL = "http://localhost:9000/user/adduser";
	public static final String POST_LOGIN_URL = "http://localhost:8080/user/login";
	public static final String POST_UPDATE_PWD_URL = "http://localhost:8080/user/setpassword";
	public static final String GET_BY_ID_URL = "http://localhost:8080/user/getuserbyid/1";
	public static final String GET_BY_NAME_URL = "http://localhost:8080/user/getuserbyname/kishore";
	public static final String GET_FIND_ALL_URL = "http://localhost:8080/user/listofusers";
	public static final String PUT_BY_ID_URL = "http://localhost:8080/user/update/1";
	public static final String DELETE_BY_ID_URL = "http://localhost:8080/user/delete/1";

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		RestTemplateTest template = new RestTemplateTest();
		template.addAccountDetails();
		// template.loginCredentials();
		// template.getAccountId();
		// template.getAccountName();
		// template.listOfAccountDetails();
		// template.updateAccountDetails();
		// template.deleteAccountDetails();
	}

	private static void addAccountDetails() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		User userDetails = new User();
		userDetails.setUsername("kishore");
		userDetails.setPassword("09090");
		userDetails.setEmail("kishore@gmail.com");
		userDetails.setPhone("9876543221");
		userDetails.setCity("Bangalore");
		userDetails.setState("KA");
		userDetails.setPinCode("4500343");
		HttpEntity<User> reqEntity = new HttpEntity<User>(userDetails, headers);
		ResponseEntity<String> resEntity = restTemplate.exchange(POST_SAVE_URL, HttpMethod.POST, reqEntity,
				String.class);
		HttpStatus statusCode = resEntity.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		System.out.println(resEntity.getBody());
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private static void loginCredentials() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "kishore");
		jsonObject.put("password", "09090");
		HttpEntity<String> reqEntity = new HttpEntity<String>(jsonObject.toString(), headers);
		ResponseEntity<String> resEntity = restTemplate.exchange(POST_LOGIN_URL, HttpMethod.POST, reqEntity,
				String.class);
		HttpStatus statusCode = resEntity.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		System.out.println(resEntity.getBody());

	}

	@SuppressWarnings("unused")
	private static void getAccountId() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<User> reqEntity = new HttpEntity<User>(headers);
		ResponseEntity<User> resEntity = restTemplate.exchange(GET_BY_ID_URL, HttpMethod.GET, reqEntity, User.class);
		HttpStatus statusCode = resEntity.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		User userDetails = resEntity.getBody();
		System.out.println(userDetails);

	}

	@SuppressWarnings("unused")
	private static void getAccountName() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<User> reqEntity = new HttpEntity<User>(headers);
		ResponseEntity<User> resEntity = restTemplate.exchange(GET_BY_NAME_URL, HttpMethod.GET, reqEntity, User.class);
		HttpStatus statusCode = resEntity.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		User userDetails = resEntity.getBody();
		System.out.println(userDetails);

	}

	@SuppressWarnings("unused")
	private static void listOfAccountDetails() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> reqEntity = new HttpEntity<String>(headers);
		ResponseEntity<String> resEntity = restTemplate.exchange(GET_FIND_ALL_URL, HttpMethod.GET, reqEntity,
				String.class);
		HttpStatus statusCode = resEntity.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		System.out.println(resEntity.getBody());

	}

	@SuppressWarnings("unused")
	private static void updateAccountDetails() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		User userDetails = new User();
		userDetails.setUsername("hari");
		userDetails.setPassword("123456");
		userDetails.setEmail("hari@gmail.com");
		userDetails.setPhone("9876543221");
		userDetails.setCity("Bangalore");
		userDetails.setState("KA");
		userDetails.setPinCode("4500343");
		userDetails.setCreateDate(new Date());
		HttpEntity<User> reqEntity = new HttpEntity<User>(userDetails, headers);
		ResponseEntity<String> resEntity = restTemplate.exchange(PUT_BY_ID_URL, HttpMethod.PUT, reqEntity,
				String.class);
		HttpStatus statusCode = resEntity.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		System.out.println(resEntity.getBody());
	}

	@SuppressWarnings("unused")
	private static void deleteAccountDetails() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<User> reqEntity = new HttpEntity<User>(headers);
		ResponseEntity<Void> resEntity = restTemplate.exchange(DELETE_BY_ID_URL, HttpMethod.DELETE, reqEntity,
				Void.class);
		HttpStatus statusCode = resEntity.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		System.out.println(resEntity.getBody());

	}

}
