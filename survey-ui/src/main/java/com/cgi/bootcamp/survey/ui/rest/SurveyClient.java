package com.cgi.bootcamp.survey.ui.rest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgi.bootcamp.survey.ui.rest.model.Survey;

@FeignClient(name="cgi-bootcamp-survey")
public interface SurveyClient {
	
	@RequestMapping(method=RequestMethod.GET, path="/{id}")
	public Survey getValue(@PathVariable(name="id")String id);

	@RequestMapping(method=RequestMethod.POST, path="/")
	public void store(@RequestBody Survey survey);
}

