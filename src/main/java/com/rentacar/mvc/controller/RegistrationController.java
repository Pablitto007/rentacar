package com.rentacar.mvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.rentacar.mvc.doamin.Customer;
import com.rentacar.mvc.service.CustomerService;

@Controller
public class RegistrationController {

	private CustomerService customerService;

	@Autowired
	public RegistrationController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		Customer newCustomerPerson = new Customer();
		model.addAttribute("newCustomerPerson", newCustomerPerson);
		model.addAttribute("register", "Registration new Customer - Person");
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String processRegistrationForm(
			@ModelAttribute("newCustomerPerson") 
		@Valid Customer customerToAdd, BindingResult result) {
		if(result.hasErrors()){
			return "registration";
		}
		try {
			customerService.addCustomerPerson(customerToAdd);
		} catch (DuplicateKeyException e) {
			result.rejectValue("email", "message", "User already exists");
			return "registration";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/registrationCompany", method = RequestMethod.GET)
	public String getRegistrationCompanyForm(Model model) {
		model.addAttribute("register", "Registration new Customer - Company");
		model.addAttribute("newCustomerCompany", new Customer());
		return "registrationCompany";
	}

	@RequestMapping(value = "/registrationCompany", method = RequestMethod.POST)
		public String processRegistrationCompanyForm(@ModelAttribute("newCustomerCompany") 
		@Valid Customer customerToAdd, BindingResult result) {
		if(result.hasErrors()){
			return "registrationCompany";
		}
		try {
			customerService.addCustomerCompany(customerToAdd);
		} catch (DuplicateKeyException e) {
			result.rejectValue("email", "message", "User already exists");
			return "registrationCompany";
		}
		return "redirect:/login";
	}
}
