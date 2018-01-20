package com.cgi.bootcamp.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.bootcamp.survey.domain.Survey;
import com.cgi.bootcamp.survey.domain.SurveyRepository;

@RestController
@RefreshScope
public class SurveyController {
	
	@Autowired
	private SurveyRepository repository;
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json", path="", consumes="application/json")
	public void storeValue(@RequestBody Survey survey) {
		repository.save(survey);
	}	

	@RequestMapping(method=RequestMethod.GET, produces="application/json", path="/{id}")
	public Survey getValue(@PathVariable(name="id") String id) {
		return repository.findOneById(id);
	}	

}
