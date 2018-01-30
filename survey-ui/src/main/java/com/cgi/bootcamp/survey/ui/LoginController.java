package com.cgi.bootcamp.survey.ui;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/{auth}")
public class LoginController {
	
	
	private static final String TEMPLATE_BASE = "base";
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@GetMapping()
	public String login(HttpServletResponse response, HttpSession session, @PathVariable("auth") String auth) {
		LOGGER.info("Login with auth {}.", auth);
		Cookie authCookie = new Cookie("Authentication", auth); 
		authCookie.setMaxAge(60*60); // in seconds
		authCookie.setDomain("localhost"); // TODO this doesn't generalize well
		authCookie.setPath("/");
		response.addCookie(authCookie);
		session.setAttribute("Authentication", auth);
		return "redirect:/"+TEMPLATE_BASE;
	}

}
