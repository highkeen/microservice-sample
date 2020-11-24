package com.microservice.ui.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.ui.controller.model.LoginModel;
import com.microservice.ui.controller.model.RegistrationModel;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTempalte;

	public boolean register(RegistrationModel registrationModel) {
		RequestEntity<RegistrationModel> request;
		try {
			request = RequestEntity.post(new URI("http://microservice-identity/auth/register"))
					.accept(MediaType.APPLICATION_JSON).body(registrationModel);

			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> response = restTempalte.exchange(request, Map.class);

			return response.getStatusCode().is2xxSuccessful();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Map<String,Object> login(LoginModel loginModel) {
		RequestEntity<LoginModel> request;
		try {
			request = RequestEntity.post(new URI("http://microservice-identity/auth/login"))
					.accept(MediaType.APPLICATION_JSON).body(loginModel);

			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> response = restTempalte.exchange(request, Map.class);

			if(response.getStatusCode().is2xxSuccessful()) {
				return response.getBody();
			}
			else {
				throw new RuntimeException("login failed");
			}
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean verity(String token) {
		RequestEntity<Void> request;
		try {
			request = RequestEntity.get(new URI("http://microservice-identity/auth/verify"))
					.accept(MediaType.APPLICATION_JSON).header("Authorization", token).build();

			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> response = restTempalte.exchange(request, Map.class);
			
			if(response.getStatusCode().is2xxSuccessful()) {
				return Boolean.getBoolean(response.getBody().get("status").toString()); 
			}
			
			return false;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
