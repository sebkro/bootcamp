package com.cgi.bootcamp.survey.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

	private static final String TEMPLATE_START = "start";

	@GetMapping("/")
	public String start(Model model) {
		return TEMPLATE_START;
	}

}
