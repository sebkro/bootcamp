package com.cgi.bootcamp.survey.ui.rest.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Survey {

	private String id;
	private String title;
	private String headerText;
	private String invitationText;
	private List<PageElement> pageElements;
	private String creatorEmail;
	private Set<String> invitedEmails;
	

}
