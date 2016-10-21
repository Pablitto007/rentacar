package com.rentacar.mvc.controller;


import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentacar.mvc.service.impl.AuthenticationService;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String welcome(Model model) throws IOException {
		boolean isAuthorized = AuthenticationService.isUserLogged();
		
		model.addAttribute("greeting", "Welcome to rentacar application");
		
		if (isAuthorized == true) {
			model.addAttribute("logged", AuthenticationService.getLoggedUsername());
		} else {
			model.addAttribute("logged", "anonymous");
		}
        
		return "welcome";
	}
}
