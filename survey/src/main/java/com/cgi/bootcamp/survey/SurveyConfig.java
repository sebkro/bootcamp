package com.cgi.bootcamp.survey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.cgi.bootcamp.survey.security.JwtFilter;

@Configuration
@EnableMongoRepositories
public class SurveyConfig {
	
	@Value("${jwt.publicKey}")
	private String publicKey;

	@Value("${jwt.issuer}")
	private String issuer;

	
	@Bean
	public FilterRegistrationBean someFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(jwtFilter());
	    registration.addUrlPatterns("/*");
	    registration.setName("jwtFilter");
	    registration.setOrder(1);
	    return registration;
	} 

	public JwtFilter jwtFilter() {
	    JwtFilter filter = new JwtFilter(publicKey, issuer);
	    filter.init();
	    return filter;
	}

}
