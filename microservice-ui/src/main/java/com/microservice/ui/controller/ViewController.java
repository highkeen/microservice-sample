package com.microservice.ui.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.microservice.ui.controller.model.LoginModel;
import com.microservice.ui.controller.model.RegistrationModel;
import com.microservice.ui.service.UserService;

@Controller
public class ViewController {
	@Autowired
	private UserService userService;

	@GetMapping("/index")
	String loginView(Model model,@RequestParam(required = false)String message) {
		model.addAttribute("login", new LoginModel());
		model.addAttribute("message",message);
		return "login_view";
	}
	
	@PostMapping("/doLogin")
	RedirectView doLogin(@ModelAttribute LoginModel loginModel, Model model) {
		
		Map<String,Object> data=userService.login(loginModel);
		if(data !=null) {
			String token=data.get("token").toString();
			return new RedirectView("/welcome?token="+token,true);
		}
		else {
			return new RedirectView("/login?message=invalid credential",true);
		}
		
			
	}

	@GetMapping("/registration")
	String registrationView(Model model,@RequestParam(required = false) String message) {
		model.addAttribute("registration", new RegistrationModel());
		model.addAttribute("message", message);
		return "registration_view";
	}

	@PostMapping("/doRegistration")
	RedirectView doRegistraation(@ModelAttribute RegistrationModel registration, Model model) {
		String message=null;
		if(userService.register(registration)) {
			message="Registration Success";	
		}
		else{
			message="Registration failed";	
		}
		return new RedirectView("/registration?message="+message,true);	
	}
	
	@GetMapping("/welcome")
	String welcomeView(Model model,@RequestParam(required = false)String token) {
		
		if(StringUtils.isEmpty(token)) {
			return "redirect:/index";
		}
		else if(!userService.verity(token)) {
			return "redirect:/index";
		}
		return "welcome_view";
	}
	
	
}
