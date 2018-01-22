package com.cgi.bootcamp.survey.ui;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi.bootcamp.survey.ui.models.SurveyBaseAttributes;
import com.cgi.bootcamp.survey.ui.rest.SurveyClient;
import com.cgi.bootcamp.survey.ui.rest.model.Survey;

@Controller
@RequestMapping("/base")
public class BaseController {
	
	@Autowired
	private SurveyClient surveyClient;
	
	
	private static final String BASE_ATTRIBUTES = "baseAttributes";
	private static final String TEMPLATE_BASE = "base";
	private static final String TEMPLATE_FIELDS = "fields";
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	@GetMapping()
	public String greetingForm(Model model) {
		model.addAttribute(BASE_ATTRIBUTES, new SurveyBaseAttributes());
		return TEMPLATE_BASE;
	}

	@PostMapping()
	public String greetingSubmit(@ModelAttribute(name = BASE_ATTRIBUTES) @Valid SurveyBaseAttributes base, BindingResult bindungResult) {
		LOGGER.info("base Submit with {}", base);
		if (bindungResult.hasErrors()) {
			return TEMPLATE_BASE;
		} else {
			Survey survey = new Survey();
			survey.setId(UUID.randomUUID().toString());
			survey.setTitle(base.getTitle());
			survey.setCreatorEmail(base.getCreatorEmail());
			survey.setInvitationText(base.getInvitationText());
			survey.setHeaderText(base.getHeaderText());
			try {
				survey.setNumberOfQuestions(Integer.parseInt(base.getNumberOfQuestionsStr()));
			}catch(NumberFormatException ex) {
				LOGGER.warn("{} is not a number.", base.getNumberOfQuestionsStr());
			}
			if (base.getEmails() != null) {
				Set<String> invitedEmails = Arrays.stream(base.getEmails().split("\n"))
						.map(elem -> elem.trim())
						.filter(elem -> StringUtils.isNotBlank(elem))
						.collect(Collectors.toSet());
				survey.setInvitedEmails(invitedEmails);
			}
			LOGGER.info("SURVEY, general part: {}", survey);
			surveyClient.store(survey);
			return "redirect:"+TEMPLATE_FIELDS+"/"+survey.getId(); //TODO hier dann auf die Seite zur Anlage der Fragen
		}
	}

}
