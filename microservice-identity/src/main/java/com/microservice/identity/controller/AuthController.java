package com.microservice.identity.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.identity.dao.UserRepository;
import com.microservice.identity.dto.LoginRequest;
import com.microservice.identity.entity.User;
import com.microservice.identity.exception.MicroserviceIdentityException;
import com.microservice.identity.util.JwtUtil;

@RestController
@RequestMapping(value="/auth")
public class AuthController {

	private String jwtIssuer = "JWT Demo";
	private int jwtTimeToLive = 800000;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	@Transactional
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
		User user = userRepository.findByUsername(loginRequest.getUsername())
				.filter(u -> Objects.equals(u.getPassword(), loginRequest.getPassword()))
				.orElseThrow(() -> new MicroserviceIdentityException("user not found", HttpStatus.NOT_FOUND.value()));

		Map<String, Object> map = new HashMap<>();
		String token = JwtUtil.createJWT(UUID.randomUUID().toString(), jwtIssuer, user.getUsername(), jwtTimeToLive);
		map.put("token", token);
		map.put("username", user.getUsername());
		user.setToken(token);

		userRepository.save(user);

		return ResponseEntity.ok(map);

	}

	@PostMapping("/register")
	@Transactional
	public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
		user.setId(null);
		userRepository.save(user);
		Map<String, Object> map = new HashMap<>();
		map.put("status", "ok");
		return ResponseEntity.ok(map);

	}

	@GetMapping("/verify")
	@Transactional(readOnly = true)
	public ResponseEntity<Map<String, Object>> isLoggedIn(@RequestHeader("Authorization") @NotNull String autHeader) {
		userRepository.findByToken(autHeader)
				.orElseThrow(() -> new MicroserviceIdentityException("user not found", HttpStatus.NOT_FOUND.value()));

		Date expDate = JwtUtil.decodeJWT(autHeader).getExpiration();
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		Map<String, Object> map = new HashMap<>();
		map.put("status", expDate.after(now));
		return ResponseEntity.ok(map);

	}

}
