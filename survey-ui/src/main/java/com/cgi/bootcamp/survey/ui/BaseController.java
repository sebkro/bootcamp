package com.cgi.bootcamp.survey.ui;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgi.bootcamp.survey.ui.models.SurveyBaseAttributes;

@Controller
@RequestMapping("/base")
public class BaseController {
	
	
	private static final String BASE_ATTRIBUTES = "baseAttributes";
	private static final String TEMPLATE_BASE = "base";
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	@GetMapping()
	public String greetingForm(Model model) {
		model.addAttribute(BASE_ATTRIBUTES, new SurveyBaseAttributes());
		return TEMPLATE_BASE;
	}

	@PostMapping()
	public String greetingSubmit(@ModelAttribute(name = BASE_ATTRIBUTES) @Valid SurveyBaseAttributes base, BindingResult bindungResult) {
		LOGGER.info("base Submit with {}", base);
		base.setHeaderText("neu" + base.getHeaderText());
		return TEMPLATE_BASE;
	}

}
