package com.cgi.bootcamp.survey.domain;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Survey {

	@Id
	private String id;
	private String title;
	private String headerText;
	private String invitationText;
	private List<Page> pages;
	private String creatorEmail;
	private Set<String> invitedEmails;
	

}
