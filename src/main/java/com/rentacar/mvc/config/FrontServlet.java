package com.rentacar.mvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Equivalent for XML configuration in /webcontext/FrontServlet-context.xml
 * I wrote both XML and Java class for better understanding Spring mixed configurations rules
 */
@Configuration
@EnableWebMvc
public class FrontServlet extends WebMvcConfigurerAdapter{
	
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = 
				new ResourceBundleMessageSource();
		messageSource.setBasename("messages_en");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	
	@Bean
	public CommonsMultipartResolver multiPartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSizePerFile(10240000);
		return multipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry){
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	@Override
	public Validator getValidator(){
		LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
		validatorFactory.setValidationMessageSource(messageSource());
		validatorFactory.afterPropertiesSet();
		return validatorFactory;
	}

}
