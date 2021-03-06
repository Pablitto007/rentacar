package com.rentacar.test.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.servlet.view.InternalResourceView;
import com.rentacar.mvc.controller.RegistrationController;
import com.rentacar.mvc.doamin.Customer;
import com.rentacar.mvc.service.CustomerService;

/**
 * I have done integration tests for Repositories so I don't need to test their
 * behavior in details here
 */

public class RegistrationControllerTest {

	private CustomerService mockService;
	private RegistrationController sut;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockService = mock(CustomerService.class);
		sut = new RegistrationController(mockService);
		mockMvc = standaloneSetup(sut)
				.setSingleView(new InternalResourceView("/WEB-INF/views/registration.jsp"))
				.build();
	}

	@Test
	public void shouldShowRegistration() throws Exception {
		mockMvc.perform(get("/registration")).andExpect(view().name("registration"));
	}

	@Test
	public void shouldProcessReistrationFormNoErrors() throws Exception {

		Customer person = new Customer(1, "Some Street 2", "Warsaw", "09-222", null, null, "James", "Bond",
				"AUH12345", "PERS", "jamesb@kissme.pl", "passMe");

		this.mockService.addCustomerPerson(person);

		RequestBuilder request = post("/registration")
				.param("email", person.getEmail())
				.param("city", person.getCity());

		mockMvc.perform(request).andExpect(view().name("redirect:/login"));

		verify(mockService, atLeastOnce()).addCustomerPerson(person);
	}

	@Test
	public void shouldStayTheSamePageWhenUserExists() throws Exception {
		/*
		 * Just use any(Some.class) method - does not have to create any Customer instance
		 */
		doThrow(new DuplicateKeyException("email")).when(mockService).addCustomerPerson(any(Customer.class));

		mockMvc.perform(post("/registration")).andExpect(view().name("registration"));

	}
	
	/*
	 * Annotation validation test
	 * With unauthorized input data should stay at registration site
	 */
	@Test
	public void shouldBeValidatedWithError() throws Exception{

	RequestBuilder request = post("/registration")
			.param("email", "notemail") //@Email validation
			.param("city", "") //@Size (min =1) 
			.param("postalCode", "AA-000"); //@Pattern(regexp = "\\d{2}-\\d{3}")

	mockMvc.perform(request).andExpect(view().name("registration"));
		
	}
	
	/*
	 * All data fine - should be registered and redirected to login site
	 */
	
	@Test
	public void shouldBeValidatedNoError() throws Exception{
	
	RequestBuilder request = post("/registration")
			.param("email", "email@com") //@Email validation
			.param("city", "NY") //@Size (min =1) 
			.param("postalCode", "00-000"); //@Pattern(regexp = "\\d{2}-\\d{3}")

	mockMvc.perform(request).andExpect(view().name("redirect:/login"));
		
	}

}
