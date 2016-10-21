package com.rentacar.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.rentacar.mvc.controller.HomeController;
import com.rentacar.mvc.service.impl.AuthenticationService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/*
 * Used Power Mockito to mock static methods from AuthenticationService class
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(AuthenticationService.class) 
public class HomeControllerTest {
	
	@Test
	public void shouldGetWelcomeNotLoggedUser() throws Exception{
		
		PowerMockito.mockStatic(AuthenticationService.class);
		when(AuthenticationService.isUserLogged()).thenReturn(false);
				
		HomeController sut = new HomeController();
		MockMvc mockMvc = standaloneSetup(sut).build();
		mockMvc.perform(get("/"))
		.andExpect(view().name("welcome"))
		.andExpect(model().attributeExists("greeting"))
		.andExpect(model().attribute("logged", "anonymous"));	
	}
	
	@Test
	public void shouldGetWelcomeLoggedUser() throws Exception{
		
		PowerMockito.mockStatic(AuthenticationService.class);
		when(AuthenticationService.isUserLogged()).thenReturn(true);
		when(AuthenticationService.getLoggedUsername()).thenReturn("user123");
				
		HomeController sut = new HomeController();
		MockMvc mockMvc = standaloneSetup(sut).build();
		mockMvc.perform(get("/"))
		.andExpect(view().name("welcome"))
		.andExpect(model().attributeExists("greeting"))
		.andExpect(model().attribute("logged", "user123"));	
	}
	
}
