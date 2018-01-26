package com.cgi.bootcamp.survey.ui;

import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi.bootcamp.survey.ui.models.SurveyFieldsAttributes;
import com.cgi.bootcamp.survey.ui.rest.SurveyClient;
import com.cgi.bootcamp.survey.ui.rest.model.Survey;

@Controller
@RequestMapping("/fields/{id}")
public class FieldsController {
	
	@Autowired
	private SurveyClient surveyClient;
	
	
	private static final String FIELDS_ATTRIBUTES = "fieldsAttributes";
	private static final String TEMPLATE_FIELDS = "fields";
	private static final Logger LOGGER = LoggerFactory.getLogger(FieldsController.class);

	@GetMapping()
	public String greetingForm(Model model, @PathVariable("id") String id, @RequestHeader("Authorization") String auth) {
		Survey survey = surveyClient.getValue(id, auth);
		SurveyFieldsAttributes attributes = new SurveyFieldsAttributes();
		attributes.setTitle(survey.getTitle());
		model.addAttribute(FIELDS_ATTRIBUTES, attributes);
		return TEMPLATE_FIELDS;
	}

	@PostMapping()
	public String greetingSubmit(@ModelAttribute(name = FIELDS_ATTRIBUTES) @Valid SurveyFieldsAttributes fields, BindingResult bindungResult) {
		LOGGER.info("base Submit with {}", fields);
		if (bindungResult.hasErrors()) {
			return TEMPLATE_FIELDS;
		} else {
			Survey survey = new Survey();
			survey.setId(UUID.randomUUID().toString());
			survey.setTitle(fields.getTitle());
			LOGGER.info("SURVEY, fields part: {}", survey);
//			surveyClient.store(survey);
			return TEMPLATE_FIELDS;
		}
	}

}
