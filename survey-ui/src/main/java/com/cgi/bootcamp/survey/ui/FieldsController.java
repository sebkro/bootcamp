package com.cgi.bootcamp.survey.ui;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi.bootcamp.survey.ui.models.SurveyFieldsAttributes;
import com.cgi.bootcamp.survey.ui.rest.SurveyClient;
import com.cgi.bootcamp.survey.ui.rest.model.PageElement;
import com.cgi.bootcamp.survey.ui.rest.model.Survey;

@Controller
@RequestMapping(value= {"/fields/{id}", "/fields/{id}?state={state}"})
public class FieldsController {
	
	@Autowired
	private SurveyClient surveyClient;
	
	
	private static final String FIELDS_ATTRIBUTES = "fieldsAttributes";
	private static final String TEMPLATE_FIELDS = "fields";
	private static final String TEMPLATE_OVERVIEW = "surveyOverview";
	private static final Logger LOGGER = LoggerFactory.getLogger(FieldsController.class);

	@GetMapping()
	public String greetingForm(Model model, @PathVariable("id") String id, @CookieValue("Authentication") String authCookie) {//, @RequestHeader("Authorization") String auth) {
		LOGGER.info("GET with cookie 'Authentication': {}", authCookie);
		String auth = authCookie;
		Survey survey = surveyClient.getValue(id, "Bearer "+auth);
		SurveyFieldsAttributes attributes = new SurveyFieldsAttributes();
		attributes.setSurveyId(survey.getId());
		attributes.setTitle(survey.getTitle());
		attributes.setPageElements(survey.getPageElements());		
		model.addAttribute(FIELDS_ATTRIBUTES, attributes);
		return TEMPLATE_FIELDS;
	}


	@PostMapping()
	public String greetingSubmit(@ModelAttribute(name = FIELDS_ATTRIBUTES) @Valid SurveyFieldsAttributes fields, BindingResult bindungResult, 
			@PathVariable("id") String id, 
			@CookieValue("Authentication") String authCookie, 
			@PathVariable("state") Optional<String> state) {
		LOGGER.info("base Submit with {}", fields);
		if (bindungResult.hasErrors()) {
			LOGGER.error("Number of bind errors: ", bindungResult.getErrorCount());
			return TEMPLATE_FIELDS;
		} else {
			LOGGER.info("HERE");
			Survey survey = surveyClient.getValue(id, "Bearer "+authCookie);
//			SurveyFieldsAttributes attributes = new SurveyFieldsAttributes();
			fields.setSurveyId(survey.getId());
			fields.setTitle(survey.getTitle());
			LOGGER.info("SURVEY, fields part: {}", survey);
			LOGGER.info("New question : {}", fields.getQuestion());
			LOGGER.info("New question, example : {}", fields.getExample());

			PageElement e = new PageElement();
			e.setId(UUID.randomUUID().toString());
			e.setSurveyId(survey.getId());
			e.setText(fields.getQuestion());
			e.setType(PageElement.PageElementType.QUESTION_FREETEXT);
			survey.getPageElements().add(e);
			LOGGER.info("Created page element", e);
			
			fields.setPageElements(survey.getPageElements());;
			fields.setQuestion("");
			fields.setExample("");
			
			//LOGGER.info("Fields size, : {}", fields.getElems().size());
			surveyClient.store(survey, "Bearer "+authCookie);
			String whereTo = TEMPLATE_FIELDS;
			if(state.isPresent()) {
				if("done".equals(state.get())) {
					// For unknown reasons I had to add a / here. (this was not necessary in BaseController. WHY?)
					whereTo = "redirect:/"+TEMPLATE_OVERVIEW+"/"+survey.getId();
					LOGGER.info("Redirecting to {}.", whereTo);
				} else {
					LOGGER.warn("Path contained unknown state: {}", state.get());
				}
			}
			return whereTo;
		}
	}

}
