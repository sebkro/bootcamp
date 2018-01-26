package com.cgi.bootcamp.survey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SurveyController.class);
	
	@Value("${info.value:no value}")
	private String special;

	@Value("${info.bla:no value}")
	private String bla;
	
	@Autowired
	private SurveyRepository repository;
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json", path="", consumes="application/json")
	public void storeValue(@RequestBody Survey survey) {
		LOGGER.info("store survey");
		repository.save(survey);
	}	

	@RequestMapping(method=RequestMethod.GET, produces="application/json", path="/{id}")
	public Survey getValue(@PathVariable(name="id") String id) {
		return repository.findOneById(id);
	}

	@RequestMapping(method=RequestMethod.GET, produces="application/json", path="/congig/{value}")
	public String getConfig(@PathVariable(name="value") String value) {
		if ("bla".equals(value)) {
			return bla;
		}
		return special;
	}
	
	

}
