package com.cgi.bootcamp.survey.ui;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi.bootcamp.survey.ui.models.SurveyFieldsAttributes;
import com.cgi.bootcamp.survey.ui.rest.SurveyClient;
import com.cgi.bootcamp.survey.ui.rest.model.PageElement;
import com.cgi.bootcamp.survey.ui.rest.model.Survey;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping("/surveyOverview/{id}")
public class OverviewController {
	
	@Autowired
	private SurveyClient surveyClient;
	
	
	private static final String FIELDS_ATTRIBUTES = "fieldsAttributes";
	private static final String TEMPLATE_OVERVIEW = "surveyOverview";
	private static final Logger LOGGER = LoggerFactory.getLogger(OverviewController.class);

	@GetMapping()
	public String greetingForm(Model model, @PathVariable("id") String id) {
		Survey survey = surveyClient.getValue(id, "");
		SurveyFieldsAttributes attributes = new SurveyFieldsAttributes();
		attributes.setTitle(survey.getTitle());
		List<PageElement> pageElements = survey.getPageElements();
		attributes.setPageElements(pageElements);
		model.addAttribute(FIELDS_ATTRIBUTES, attributes);
		return TEMPLATE_OVERVIEW;
	}


}
